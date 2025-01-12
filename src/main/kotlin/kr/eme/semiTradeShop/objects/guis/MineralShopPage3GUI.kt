package kr.eme.semiTradeShop.objects.guis

import kr.eme.semiTradeShop.utils.ItemStackUtil
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent

class MineralShopPage3GUI(player: Player) : GUI(player, "§f\\u340F\\u3414", 6) {

    override fun setFirstGUI() {
        for (row in 0 until 6) {
            // 왼쪽 (클릭 시 SHOP 화면 이동)
            val rowStart = row * 9
            for (slot in rowStart  until rowStart + 9) {
                setItem(slot, ItemStackUtil.build(Material.GLASS_PANE) { meta ->
                    meta.setDisplayName("§c선택 불가")
                    meta.setCustomModelData(1)
                })
            }
        }
    }

    override fun InventoryClickEvent.clickEvent() {
        isCancelled = true
    }

    override fun InventoryDragEvent.dragEvent() {
        isCancelled = true
    }

    override fun InventoryCloseEvent.closeEvent() {
        TODO("Not yet implemented")
    }
}