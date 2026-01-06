package kr.eme.prcShop.objects.guis

import kr.eme.prcMission.objects.guis.MissionInitGUI
import kr.eme.prcShop.managers.GUIManager
import kr.eme.prcShop.utils.ItemStackUtil
import kr.eme.prcShop.utils.SoundUtil
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent

class InitShopGUI(player: Player) : GUI(player, "§f\\u340F\\u3410", 6) {
    override fun setFirstGUI() {
        for (row in 0 until 4) {
            // 왼쪽 (클릭 시 SHOP 화면 이동)
            val rowStart = row * 9
            for (slot in rowStart  until rowStart + 4) {
                setItem(slot, ItemStackUtil.build(Material.GLASS_PANE) { meta ->
                    meta.setDisplayName("§aSHOP 이동")
                    meta.setCustomModelData(1)
                })
            }

            for  (slot in rowStart + 5 until rowStart + 9) {
                setItem(slot, ItemStackUtil.build(Material.GLASS_PANE) { meta ->
                    meta.setDisplayName("§6MISSION 이동")
                    meta.setCustomModelData(1)
                })
            }
        }
    }

    override fun InventoryClickEvent.clickEvent() {
        isCancelled = true
        val clickedItem = currentItem ?: run {
            // 클릭된 아이템이 없는 경우 무시
            SoundUtil.error(player)
            return
        }
        val itemDisplayName = clickedItem.itemMeta?.displayName ?: run {
            // 아이템 이름이 없는 경우 무시
            SoundUtil.error(player)
            return
        }

        when (itemDisplayName) {
            "§aSHOP 이동" -> {
                val shopGUI = ShopGUI(player)
                shopGUI.setFirstGUI()
                GUIManager.setGUI(player.uniqueId, shopGUI)
                shopGUI.open()
                SoundUtil.click(player)
            }
            "§6MISSION 이동" -> {
                MissionInitGUI(player).apply {
                    setFirstGUI()
                    open()
                }
                SoundUtil.click(player)
            }
        }
    }

    override fun InventoryCloseEvent.closeEvent() {

    }

    override fun InventoryDragEvent.dragEvent() {
        isCancelled = true
    }
}