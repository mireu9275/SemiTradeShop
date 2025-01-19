package kr.eme.semiTradeShop.objects.guis

import kr.eme.semiTradeShop.managers.GUIManager
import kr.eme.semiTradeShop.objects.ShopItems
import kr.eme.semiTradeShop.utils.ItemStackUtil
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent

class MineralShopPage1GUI(player: Player) : GUI(player, "§f\\u340F\\u3412", 6) {

    override fun setFirstGUI() {
        val items = ShopItems.getShopItems("MineralShop", 1)
        for (item in items) {
            ItemStackUtil.createSlotItem(this, item)
        }
        ItemStackUtil.createMainButton(this)
        ItemStackUtil.createRightButton(this)
        ItemStackUtil.createEpButton(this, player.uniqueId)
    }

    override fun InventoryClickEvent.clickEvent() {
        isCancelled = true
        val clickedItem = currentItem ?: return // 클릭된 아이템이 없는 경우 무시
        val itemDisplayName = clickedItem.itemMeta?.displayName ?: return // 아이템 이름이 없는 경우 무시

        when (itemDisplayName) {
            "§f오른쪽으로 이동" -> {
                val mineralShopPage2GUI = MineralShopPage2GUI(player)
                mineralShopPage2GUI.setFirstGUI()
                GUIManager.setGUI(player.uniqueId, mineralShopPage2GUI)
                mineralShopPage2GUI.open()
            }
            "§f메인으로 이동" -> {
                val shopGUI = ShopGUI(player)
                shopGUI.setFirstGUI()
                GUIManager.setGUI(player.uniqueId, shopGUI)
                shopGUI.open()
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