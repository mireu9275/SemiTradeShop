package kr.eme.semiTradeShop.listeners

import kr.eme.semiTradeShop.managers.GUIManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent

object GUIListener : Listener {
    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        val currentTitle = event.view.title
        val gui = GUIManager.getGUI(event.whoClicked.uniqueId) ?: return
        if (currentTitle != gui.title) return
        gui.onClick(event)
    }

    @EventHandler
    fun onDrag(event: InventoryDragEvent) {
        val currentTitle = event.view.title
        val gui = GUIManager.getGUI(event.whoClicked.uniqueId) ?: return
        if (currentTitle != gui.title) return
        gui.onDrag(event)
    }

    @EventHandler
    fun onClose(event: InventoryCloseEvent) {
        val currentTitle = event.view.title
        val gui = GUIManager.getGUI(event.player.uniqueId) ?: return
        if (currentTitle != gui.title) return
        gui.onClose(event)
    }
}