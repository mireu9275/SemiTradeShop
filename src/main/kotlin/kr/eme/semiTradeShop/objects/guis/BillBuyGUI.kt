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

class BillBuyGUI(player: Player, private val clickedItem: ItemStack, private val returnPage: GUI) : GUI(player, "§f\\u340F\\u3424", 6) {

    private var totalBuyQty = 1 // 초기 구매 수량 1
    private var itemPrice = 0 // 아이템 개당 가격

    override fun setFirstGUI() {
        val items = ShopItems.getShopItems("BuyGUI", 1)
        for (item in items) {
            ItemStackUtil.createSlotItemBuyOrSell(this, item)
        }

        setItem(13, clickedItem)
        itemPrice = extractBuyPrice(clickedItem)

        ItemStackUtil.createMainButton(this)
        ItemStackUtil.createEpButton(this, player.uniqueId)

        updateQtyAndPrice()
    }

    override fun InventoryClickEvent.clickEvent() {
        isCancelled = true
        val clickedItem = currentItem ?: return
        val itemMeta = clickedItem.itemMeta ?: return
        val itemDisplayName = itemMeta.displayName ?: return
        val totalPrice = totalBuyQty * itemPrice

        when (itemDisplayName) {
            "§f메인으로 이동" -> {
                val shopGUI = ShopGUI(player)
                shopGUI.setFirstGUI()
                GUIManager.setGUI(player.uniqueId, shopGUI)
                shopGUI.open()
                return
            }
            "§f구매하기" -> {
                if (!buyProcess(player, totalPrice)) return
                GUIManager.setGUI(player.uniqueId, returnPage)
                returnPage.setFirstGUI()
                returnPage.open()
                return
            }
            "§f1개 빼기" -> totalBuyQty = maxOf(1, totalBuyQty - 1)
            "§f32개 빼기" -> totalBuyQty = maxOf(1, totalBuyQty - 32)
            "§f64개 빼기" -> totalBuyQty = maxOf(1, totalBuyQty - 64)
            "§f1개 추가" -> totalBuyQty += 1
            "§f32개 추가" -> totalBuyQty += 32
            "§f64개 추가" -> totalBuyQty += 64
        }
        updateQtyAndPrice()
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
    }

    private fun buyProcess(player: Player, buyPrice: Int): Boolean {
        val uuid = player.uniqueId
        var playerMoney: Int? = null
        val moneyManager = semiMoney.getMoneyManager()
        if (moneyManager != null) playerMoney = moneyManager.getMoney(uuid)
        if (playerMoney == null) {
            player.sendMessage("§cEP를 불러오는데 실패하였습니다. (시스템 오류)")
            return false
        }
        if (playerMoney < buyPrice) {
            player.sendMessage("§cEP가 부족하여 구매에 실패하였습니다.")
            return false
        }
        moneyManager.subtractMoney(uuid, buyPrice)

        val itemToGive = ItemStackUtil.cleanItemLore(clickedItem)
        itemToGive.amount = totalBuyQty
        val leftover = player.inventory.addItem(itemToGive)
        val failedQty = leftover.values.sumOf { it.amount }
        if (failedQty > 0) {
            val refunAmount = (buyPrice / totalBuyQty) * failedQty
            moneyManager.addMoney(uuid, refunAmount)
            player.sendMessage("§c인벤토리 공간이 부족하여 $failedQty 개를 지급하지 못했습니다. EP가 복구되었습니다. (복구 EP: $refunAmount EP)")
        }
        val successQty = totalBuyQty - failedQty
        if (successQty < 0) {
            player.sendMessage("§c알수없는 오류가 발생하였습니다.")
            return false
        }
        val itemName = ItemStackUtil.cutColorCodes(clickedItem.itemMeta?.displayName ?: "아이템")
        player.sendMessage("§a${itemName}을(를) $successQty 개 구매했습니다. (총 비용: ${buyPrice - (failedQty * (buyPrice / totalBuyQty))} EP)")
        return true
    }
}