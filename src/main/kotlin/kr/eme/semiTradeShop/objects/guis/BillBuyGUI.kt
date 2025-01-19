package kr.eme.semiTradeShop.objects.guis

import kr.eme.semiTradeShop.utils.ItemStackUtil
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent

class BillBuyGUI(player: Player) : GUI(player, "§f\\u340F\\u3421", 6) {
    override fun setFirstGUI() {
        ItemStackUtil.createEpButton(this, player.uniqueId)
        ItemStackUtil.createMainButton(this)
    }

    override fun InventoryClickEvent.clickEvent() {
        TODO("Not yet implemented")
    }

    override fun InventoryDragEvent.dragEvent() {
        TODO("Not yet implemented")
    }

    override fun InventoryCloseEvent.closeEvent() {
        TODO("Not yet implemented")
    }
}