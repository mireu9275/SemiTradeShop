package kr.eme.semiTradeShop.utils

import kr.eme.semiTradeShop.objects.guis.GUI
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

object ItemStackUtil {
    fun build(material: Material, block: (ItemMeta) -> Unit): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta
        block(meta)
        item.itemMeta = meta
        return item
    }
    fun build(material: Material, block: (ItemStack, ItemMeta) -> Unit): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta
        block(item, meta)
        item.itemMeta = meta
        return item
    }
    fun createLeftButton(gui: GUI) {
        gui.setItem(36, build(Material.MUSIC_DISC_BLOCKS) { meta ->
            meta.setDisplayName("§f왼쪽으로 이동")
            meta.setCustomModelData(1)
        })
    }
    fun createRightButton(gui: GUI) {
        gui.setItem(44, build(Material.MUSIC_DISC_BLOCKS) { meta ->
            meta.setDisplayName("§f오른쪽으로 이동")
            meta.setCustomModelData(2)
        })
    }
    fun createMainButton(gui: GUI) {
        gui.setItem(49, build(Material.GLASS_PANE) { meta ->
            meta.setDisplayName("§f메인으로 이동")
            meta.setCustomModelData(1)
        })
    }
}