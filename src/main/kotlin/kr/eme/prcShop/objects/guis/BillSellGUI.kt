package kr.eme.prcShop.objects.guis

import kr.eme.prcMission.api.events.MissionEvent
import kr.eme.prcMission.enums.MissionVersion
import kr.eme.prcMoney.managers.MoneyManager
import kr.eme.prcShop.managers.GUIManager
import kr.eme.prcShop.objects.ShopItems
import kr.eme.prcShop.utils.ItemStackUtil
import kr.eme.prcShop.utils.SoundUtil
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.inventory.ItemStack

class BillSellGUI(player: Player, private val clickedItem: ItemStack, private val returnPage: GUI) : GUI(player, "§f\\u340F\\u3423", 6) {

    private var totalSellQty = 1 // 초기 구매 수량 1
    private var itemPrice = 0 // 아이템 개당 가격

    override fun setFirstGUI() {
        val items = ShopItems.getShopItems("SellGUI", 1)
        for (item in items) {
            ItemStackUtil.createSlotItemBuyOrSell(this, item)
        }

        itemPrice = extractSellPrice(clickedItem)
        
        ItemStackUtil.createMainButton(this)
        ItemStackUtil.createEpButton(this, player.uniqueId)

        updateQtyAndPrice()
    }

    override fun InventoryClickEvent.clickEvent() {
        isCancelled = true
        val clickedSlotItem = currentItem ?: run {
            SoundUtil.error(player)
            return
        }
        val itemMeta = clickedSlotItem.itemMeta ?: run {
            SoundUtil.error(player)
            return
        }
        val itemDisplayName = itemMeta.displayName ?: run {
            SoundUtil.error(player)
            return
        }

        when (itemDisplayName) {
            "§f메인으로 이동" -> {
                val shopGUI = ShopGUI(player)
                shopGUI.setFirstGUI()
                GUIManager.setGUI(player.uniqueId, shopGUI)
                shopGUI.open()
                SoundUtil.click(player)
                return
            }
            "§f판매하기" -> {
                if (!sellProcess(player, totalSellQty, itemPrice, clickedItem)) return
                GUIManager.setGUI(player.uniqueId, returnPage)
                returnPage.setFirstGUI()
                returnPage.open()
                SoundUtil.click(player)
                return
            }
            "§f1개 빼기" -> {
                totalSellQty = maxOf(1, totalSellQty - 1)
                SoundUtil.click(player)
            }
            "§f32개 빼기" -> {
                totalSellQty = maxOf(1, totalSellQty - 32)
                SoundUtil.click(player)
            }
            "§f64개 빼기" -> {
                totalSellQty = maxOf(1, totalSellQty - 64)
                SoundUtil.click(player)
            }
            "§f1개 추가" -> {
                totalSellQty = minOf(99, totalSellQty + 1)
                SoundUtil.click(player)
            }
            "§f32개 추가" -> {
                totalSellQty = minOf(99, totalSellQty + 32)
                SoundUtil.click(player)
            }
            "§f64개 추가" -> {
                totalSellQty = minOf(99, totalSellQty + 64)
                SoundUtil.click(player)
            }
        }
        updateQtyAndPrice()
        open()
    }

    override fun InventoryDragEvent.dragEvent() {
        isCancelled = true
    }

    override fun InventoryCloseEvent.closeEvent() {

    }

    private fun extractSellPrice(item: ItemStack): Int {
        val lore = item.itemMeta?.lore ?: return 0
        val buyPriceLine = lore.firstOrNull { it.startsWith("§3판매가:")} ?: return 0
        return buyPriceLine.substringAfter("§3판매가: ").substringBefore(" EP").trim().toIntOrNull() ?: 0
    }

    private fun updateQtyAndPrice() {
        val totalPrice = itemPrice * totalSellQty
        ItemStackUtil.createQtyIcon(this, totalSellQty)
        ItemStackUtil.createPriceIcon(this, totalPrice, "SELL")

        // slot 13 미리보기 아이템에 수량 반영
        val previewItem = clickedItem.clone()
        val previewMeta = previewItem.itemMeta
        previewMeta?.setMaxStackSize(99)
        previewItem.itemMeta = previewMeta
        previewItem.amount = totalSellQty
        setItem(13, previewItem)
    }

    private fun sellProcess(player: Player, totalSellQty: Int, sellPrice: Int, clickedItem: ItemStack): Boolean {
        val uuid = player.uniqueId
        val playerInventory = player.inventory

        // 플레이어 인벤토리에서 아이템 확인
        var totalOwnedQty = 0
        val matchingItems = mutableListOf<ItemStack>()
        for (item in playerInventory.contents) {
            if (item != null && item.type == clickedItem.type) {
                val itemMeta = item.itemMeta
                val clickedMeta = clickedItem.itemMeta
                // CustomModelData 같은지 확인
//                val itemCMD = if (itemMeta != null && itemMeta.hasCustomModelData()) itemMeta.customModelData else null
//                val clickedCMD = if (clickedMeta != null && clickedMeta.hasCustomModelData()) clickedMeta.customModelData else null
                val itemCMD = itemMeta?.customModelData ?: -1
                val clickedCMD = clickedMeta?.customModelData ?: -1
                // 디버깅 메시지 출력 (비교 값 확인)

                if (itemCMD == clickedCMD) {
                    matchingItems.add(item)
                    totalOwnedQty += item.amount
                }
            }
        }

        // 보유량이 판매량보다 많거나 같은지 확인
        if (totalOwnedQty < totalSellQty) {
            player.sendMessage("§c판매할 아이템의 수량이 부족합니다. (보유 수량: $totalOwnedQty 개)")
            SoundUtil.error(player)
            return false
        }

        // 판매할 개수만큼 아이템을 제거함
        var remainingToRemove = totalSellQty
        for (item in matchingItems) {
            if (remainingToRemove <= 0) break
            if (item.amount > remainingToRemove) {
                item.amount -= remainingToRemove
                remainingToRemove = 0
            } else {
                remainingToRemove -= item.amount
                item.amount = 0
            }
        }

        // 돈 지급
        val totalEarnings = sellPrice * totalSellQty
        MoneyManager.addMoney(totalEarnings, "SHOP_SELL:${ItemStackUtil.cutColorCodes(clickedItem.itemMeta?.displayName ?: "아이템")}", player.name)
        val itemName = ItemStackUtil.cutColorCodes(clickedItem.itemMeta?.displayName ?: "아이템")
        player.sendMessage("§a${itemName}을(를) $totalSellQty 개 판매하여 $totalEarnings EP를 획득하였습니다.")
        SoundUtil.click(player)

        val soldItemNameClean = ItemStackUtil.cutColorCodes(clickedItem.itemMeta?.displayName ?: "")

        val isMineralItem = ShopItems.getShopItems("MineralShop").any { shopItem ->
            val shopItemNameClean = shopItem.name?.let { ItemStackUtil.cutColorCodes(it) }
            shopItemNameClean == soldItemNameClean
        }

        val isFarmingShop = ShopItems.getShopItems("FarmingShop").any { shopItem ->
            val shopItemNameClean = shopItem.name?.let { ItemStackUtil.cutColorCodes(it) }
            shopItemNameClean == soldItemNameClean
        }

        if (isMineralItem) {
            Bukkit.getPluginManager().callEvent(
                MissionEvent(player, MissionVersion.V1, "TRADE", "trade_module", 1)
            )
        } else if (isFarmingShop) {
            Bukkit.getPluginManager().callEvent(
                MissionEvent(player, MissionVersion.V1, "TRADE", "trade_crop_module", 1)
            )
        }

        return true
    }
}
