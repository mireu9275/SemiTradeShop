package kr.eme.semiTradeShop.objects.guis

import kr.eme.semiTradeShop.managers.GUIManager
import kr.eme.semiTradeShop.objects.ShopItems
import kr.eme.semiTradeShop.utils.ItemStackUtil
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent

class FarmingShopPage1GUI(player: Player) : GUI(player, "§f\\u340F\\u3415" ,6){
    override fun setFirstGUI() {
        val items = ShopItems.getShopItems("FarmingShop", 1)
        for (item in items) {
            ItemStackUtil.createSlotItem(this, item)
        }
        ItemStackUtil.createRightButton(this)
        ItemStackUtil.createMainButton(this)
        ItemStackUtil.createEpButton(this, player.uniqueId)

    }

    override fun InventoryClickEvent.clickEvent() {
        isCancelled = true
        val clickedItem = currentItem ?: return // 클릭된 아이템이 없는 경우 무시
        val itemMeta = clickedItem.itemMeta ?: return // 아이템 메타가 없는 경우 무시
        val itemDisplayName = clickedItem.itemMeta?.displayName ?: return // 아이템 이름이 없는 경우 무시
        val lore = itemMeta.lore ?: return

        when (itemDisplayName) {
            "§f오른쪽으로 이동" -> {
                val farmingShopPage2GUI = FarmingShopPage2GUI(player)
                farmingShopPage2GUI.setFirstGUI()
                GUIManager.setGUI(player.uniqueId, farmingShopPage2GUI)
                farmingShopPage2GUI.open()
            }
            "§f메인으로 이동" -> {
                val shopGUI = ShopGUI(player)
                shopGUI.setFirstGUI()
                GUIManager.setGUI(player.uniqueId, shopGUI)
                shopGUI.open()
            }
            else -> {
                if (isLeftClick) {
                    val buyPrice = lore.firstOrNull { it.startsWith("§6구매가:") }
                    if (buyPrice == null || buyPrice.contains("§c구매 불가")) {
                        player.sendMessage("§c이 아이템은 구매할 수 없습니다!")
                        return
                    }
                    val buyGUI = BillBuyGUI(player)
                    buyGUI.setFirstGUI()
                    GUIManager.setGUI(player.uniqueId, buyGUI)
                    buyGUI.open()
                } else if (isRightClick) {
                    val sellPrice = lore.firstOrNull { it.startsWith("§3판매가:") }
                    if (sellPrice == null || sellPrice.contains("§c판매 불가")) {
                        player.sendMessage("§c이 아이템은 판매할 수 없습니다!")
                        return
                    }
                    val sellGUI = BillSellGUI(player)
                    sellGUI.setFirstGUI()
                    GUIManager.setGUI(player.uniqueId, sellGUI)
                    sellGUI.open()
                }
            }
        }
    }

    override fun InventoryDragEvent.dragEvent() {
        isCancelled = true
    }

    override fun InventoryCloseEvent.closeEvent() {
        TODO("Not yet implemented")
    }
}