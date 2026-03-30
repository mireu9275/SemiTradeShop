package kr.eme.prcShop.objects.guis

import kr.eme.prcMission.api.events.MissionEvent
import kr.eme.prcMission.enums.MissionVersion
import kr.eme.prcMission.objects.const.MissionTargets
import kr.eme.prcMission.objects.const.MissionTypes
import kr.eme.prcMoney.managers.MoneyManager
import kr.eme.prcShop.extensions.toBukkitItemWithoutPrice
import kr.eme.prcShop.managers.GUIManager
import kr.eme.prcShop.objects.ShopItem
import kr.eme.prcShop.objects.ShopItems
import kr.eme.prcShop.utils.ItemStackUtil
import kr.eme.prcShop.utils.SoundUtil
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.inventory.ItemStack

class BillBuyGUI(player: Player, private val clickedItem: ItemStack, private val returnPage: GUI, private val shopItem: ShopItem? = null) : GUI(player, "§f\\u340F\\u3424", 6) {

    private var totalBuyQty = 1 // 초기 구매 수량 1
    private var itemPrice = 0 // 아이템 개당 가격

    override fun setFirstGUI() {
        val items = ShopItems.getShopItems("BuyGUI", 1)
        for (item in items) {
            ItemStackUtil.createSlotItemBuyOrSell(this, item)
        }

        itemPrice = extractBuyPrice(clickedItem)

        ItemStackUtil.createMainButton(this)
        ItemStackUtil.createEpButton(this, player.uniqueId)

        updateQtyAndPrice()
    }

    override fun InventoryClickEvent.clickEvent() {
        isCancelled = true
        val clickedItem = currentItem ?: run {
            SoundUtil.error(player)
            return
        }
        val itemMeta = clickedItem.itemMeta ?: run {
            SoundUtil.error(player)
            return
        }
        val itemDisplayName = itemMeta.displayName ?: run {
            SoundUtil.error(player)
            return
        }
        val totalPrice = totalBuyQty * itemPrice

        when (itemDisplayName) {
            "§f메인으로 이동" -> {
                val shopGUI = ShopGUI(player)
                shopGUI.setFirstGUI()
                GUIManager.setGUI(player.uniqueId, shopGUI)
                shopGUI.open()
                SoundUtil.click(player)
                return
            }
            "§f구매하기" -> {
                if (!buyProcess(player, totalPrice)) return
                GUIManager.setGUI(player.uniqueId, returnPage)
                returnPage.setFirstGUI()
                returnPage.open()
                SoundUtil.click(player)
                return
            }
            "§f1개 빼기" -> {
                totalBuyQty = maxOf(1, totalBuyQty - 1)
                SoundUtil.click(player)
            }
            "§f5개 빼기" -> {
                totalBuyQty = maxOf(1, totalBuyQty - 5)
                SoundUtil.click(player)
            }
            "§f32개 빼기" -> {
                totalBuyQty = maxOf(1, totalBuyQty - 32)
                SoundUtil.click(player)
            }
            "§f1개 추가" -> {
                totalBuyQty = minOf(99, totalBuyQty + 1)
                SoundUtil.click(player)
            }
            "§f5개 추가" -> {
                totalBuyQty = minOf(99, totalBuyQty + 5)
                SoundUtil.click(player)
            }
            "§f32개 추가" -> {
                totalBuyQty = minOf(99, totalBuyQty + 32)
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

    private fun extractBuyPrice(item: ItemStack): Int {
        val lore = item.itemMeta?.lore ?: return 0
        val buyPriceLine = lore.firstOrNull { it.startsWith("§6구매가:")} ?: return 0
        return buyPriceLine.substringAfter("§6구매가: ").substringBefore(" EP").trim().toIntOrNull() ?: 0
    }

    private fun updateQtyAndPrice() {
        val totalPrice = itemPrice * totalBuyQty
        ItemStackUtil.createQtyIcon(this, totalBuyQty)
        ItemStackUtil.createPriceIcon(this, totalPrice, "BUY")

        // slot 13 미리보기 아이템에 수량 반영
        val previewItem = clickedItem.clone()
        val previewMeta = previewItem.itemMeta
        previewMeta?.setMaxStackSize(99)
        previewItem.itemMeta = previewMeta
        previewItem.amount = totalBuyQty
        setItem(13, previewItem)
    }

    private fun buyProcess(player: Player, buyPrice: Int): Boolean {
        val uuid = player.uniqueId
        val current = MoneyManager.getMoney()
        if (current < buyPrice) {
            player.sendMessage("§cEP가 부족하여 구매에 실패했습니다. (필요: $buyPrice EP)")
            SoundUtil.error(player)
            return false
        }
        // ✅ 전역 EP 차감 (uuid 없음)
        if (!MoneyManager.subtractMoney(buyPrice, player)) {
            player.sendMessage("§c시스템 오류로 인해 결제에 실패했습니다.")
            SoundUtil.error(player)
            return false
        }

        val itemToGive = if (shopItem != null) {
            shopItem.toBukkitItemWithoutPrice()
        } else {
            ItemStackUtil.cleanItemLore(clickedItem)
        }

        // 지급 아이템의 maxStackSize를 기본값으로 리셋 (다른 아이템과 정상 합쳐지도록)
        val giveMeta = itemToGive.itemMeta
        giveMeta?.setMaxStackSize(itemToGive.type.maxStackSize)
        itemToGive.itemMeta = giveMeta
        itemToGive.amount = totalBuyQty
        val leftover = player.inventory.addItem(itemToGive)
        val failedQty = leftover.values.sumOf { it.amount }
        if (failedQty > 0) {
            val refundAmount = (buyPrice / totalBuyQty) * failedQty
            MoneyManager.addMoney(refundAmount, "SHOP_BUY_ROLLBACK", player.name)
            player.sendMessage("§c인벤토리 공간이 부족하여 $failedQty 개를 지급하지 못했습니다. EP가 복구되었습니다. (복구 EP: $refundAmount EP)")
            SoundUtil.error(player)
        }
        val successQty = totalBuyQty - failedQty
        if (successQty < 0) {
            player.sendMessage("§c알수없는 오류가 발생하였습니다.")
            SoundUtil.error(player)
            return false
        }
        val itemName = ItemStackUtil.cutColorCodes(clickedItem.itemMeta?.displayName ?: "아이템")
        player.sendMessage("§a${itemName}을(를) $successQty 개 구매했습니다. (총 비용: ${buyPrice - (failedQty * (buyPrice / totalBuyQty))} EP)")
        SoundUtil.click(player)

        val buyItemNameClean = ItemStackUtil.cutColorCodes(clickedItem.itemMeta?.displayName ?: "")

        when (buyItemNameClean) {
            "커피머신 모듈" -> {
                Bukkit.getPluginManager().callEvent(
                    MissionEvent(player, MissionVersion.V1, MissionTypes.TRADE, MissionTargets.COFFEE_MODULE, 1)
                )
            }
            "커피콩" -> {
                Bukkit.getPluginManager().callEvent(
                    MissionEvent(player, MissionVersion.V1, MissionTypes.TRADE, MissionTargets.COFFEE_MODULE, 2)
                )
            }
        }

        return true
    }
}