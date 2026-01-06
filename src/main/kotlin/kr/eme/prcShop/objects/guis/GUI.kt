package kr.eme.prcShop.objects.guis

import kr.eme.prcShop.coroutine.sync
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

abstract class GUI {
    val player: Player
    val size: Int
    val title: String
    private val inventory: Inventory

    constructor(player: Player, title: String, row: Int) {
        this.player = player
        this.size = row * 9
        this.title = decodeUnicode(title)
        inventory = Bukkit.createInventory(null, row * 9, decodeUnicode(title))
    }

    constructor(player: Player, title: String, type: InventoryType) {
        this.player = player
        this.size = type.defaultSize
        this.title = decodeUnicode(title)
        inventory = Bukkit.createInventory(null, type, decodeUnicode(title))
    }

    fun getItem(slot: Int): ItemStack? = inventory.getItem(slot)
    fun setItem(slot: Int, itemStack: ItemStack?) = inventory.setItem(slot, itemStack)
    fun clear() {
        for (slot in 0 until size) {
            setItem(slot, null)
        }
    }

    fun firstOpen() {
        TODO()
    }

    fun open() {
        sync {
            val currentView = player.openInventory
            val topInventory = currentView.topInventory

            if (topInventory.size == size) {
                topInventory.contents = inventory.contents

                currentView.setTitle(title)
            } else {
                player.openInventory(inventory)
            }
        }
    }

    fun close() {
        sync { player.closeInventory() }
    }

    fun onClick(event: InventoryClickEvent) {
        event.clickEvent()
    }

    fun onDrag(event: InventoryDragEvent) {
        event.dragEvent()
    }

    fun onClose(event: InventoryCloseEvent) {
        event.closeEvent()
    }

    abstract fun setFirstGUI()
    abstract fun InventoryClickEvent.clickEvent()
    abstract fun InventoryDragEvent.dragEvent()
    abstract fun InventoryCloseEvent.closeEvent()

    companion object {
        fun decodeUnicode(input: String): String {
            return input.replace("""\\u([0-9a-fA-F]{4})""".toRegex()) {
                it.groupValues[1].toInt(16).toChar().toString()
            }
        }
    }
}