package kr.eme.semiTradeShop.objects.guis

import kr.eme.semiTradeShop.managers.GUIManager
import kr.eme.semiTradeShop.objects.ShopItems
import kr.eme.semiTradeShop.semiMoney
import kr.eme.semiTradeShop.utils.ItemStackUtil
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

        setItem(13, clickedItem)
        itemPrice = extractSellPrice(clickedItem)
        
        ItemStackUtil.createMainButton(this)
        ItemStackUtil.createEpButton(this, player.uniqueId)

        updateQtyAndPrice()
    }

    override fun InventoryClickEvent.clickEvent() {
        isCancelled = true
        val clickedSlotItem = currentItem ?: return
        val itemMeta = clickedSlotItem.itemMeta ?: return
        val itemDisplayName = itemMeta.displayName ?: return

        when (itemDisplayName) {
            "§f메인으로 이동" -> {
                val shopGUI = ShopGUI(player)
                shopGUI.setFirstGUI()
                GUIManager.setGUI(player.uniqueId, shopGUI)
                shopGUI.open()
                return
            }
            "§f판매하기" -> {
                if (!sellProcess(player, totalSellQty, itemPrice, clickedItem)) return
                GUIManager.setGUI(player.uniqueId, returnPage)
                returnPage.setFirstGUI()
                returnPage.open()
                return
            }
            "§f1개 빼기" -> totalSellQty = maxOf(1, totalSellQty - 1)
            "§f32개 빼기" -> totalSellQty = maxOf(1, totalSellQty - 32)
            "§f64개 빼기" -> totalSellQty = maxOf(1, totalSellQty - 64)
            "§f1개 추가" -> totalSellQty += 1
            "§f32개 추가" -> totalSellQty += 32
            "§f64개 추가" -> totalSellQty += 64
        }
        updateQtyAndPrice()
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
    }

    private fun sellProcess(player: Player, totalSellQty: Int, sellPrice: Int, clickedItem: ItemStack): Boolean {
        val uuid = player.uniqueId
        val moneyManager = semiMoney.getMoneyManager()
        val playerInventory = player.inventory

        // 플레이어 인벤토리에서 아이템 확인
        var totalOwnedQty = 0
        val matchingItems = mutableListOf<ItemStack>()
        for (item in playerInventory.contents) {
            if (item != null && item.type == clickedItem.type) {
                val itemMeta = item.itemMeta
                val clickedMeta = clickedItem.itemMeta
                // CustomModelData 같은지 확인
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
                playerInventory.remove(item)
            }
        }

        // 돈 지급
        val totalEarnings = sellPrice * totalSellQty
        moneyManager?.addMoney(uuid, totalEarnings)
        val itemName = ItemStackUtil.cutColorCodes(clickedItem.itemMeta?.displayName ?: "아이템")
        player.sendMessage("§a${itemName}을(를) $totalSellQty 개 판매하여 $totalEarnings EP를 획득하였습니다.")
        return true
    }
}