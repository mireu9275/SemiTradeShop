package kr.eme.prcShop.objects.guis

import kr.eme.prcShop.managers.GUIManager
import kr.eme.prcShop.utils.ItemStackUtil
import kr.eme.prcShop.utils.SoundUtil
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent

class ShopGUI(player: Player) : GUI(player, "§f\\u340F\\u3411", 6) {
    override fun setFirstGUI() {
        for (row in 0 until 4) {
            // 왼쪽 (클릭 시 SHOP 화면 이동)
            val rowStart = row * 9
            for (slot in rowStart until rowStart + 3) {
                setItem(slot, ItemStackUtil.build(Material.GLASS_PANE) { meta ->
                    meta.setDisplayName("§aMineral 이동")
                    meta.setCustomModelData(1)
                })
            }

            for (slot in rowStart + 3 until rowStart + 6) {
                setItem(slot, ItemStackUtil.build(Material.GLASS_PANE) { meta ->
                    meta.setDisplayName("§cFarming 이동")
                    meta.setCustomModelData(1)
                })
            }

            for  (slot in rowStart + 6 until rowStart + 9) {
                setItem(slot, ItemStackUtil.build(Material.GLASS_PANE) { meta ->
                    meta.setDisplayName("§6Others 이동")
                    meta.setCustomModelData(1)
                })
            }

            setItem(49, ItemStackUtil.build(Material.GLASS_PANE) { meta ->
                meta.setDisplayName("§f메인으로 이동")
                meta.setCustomModelData(1)
            })
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
            "§aMineral 이동" -> {
                val mineralShopPage1GUI = MineralShopPage1GUI(player)
                mineralShopPage1GUI.setFirstGUI()
                GUIManager.setGUI(player.uniqueId, mineralShopPage1GUI) // GUIManager 에 등록
                mineralShopPage1GUI.open()
                SoundUtil.click(player)
                return
            }

            "§cFarming 이동" -> {
                val farmingShopPage1GUI = FarmingShopPage1GUI(player)
                farmingShopPage1GUI.setFirstGUI()
                GUIManager.setGUI(player.uniqueId, farmingShopPage1GUI)
                farmingShopPage1GUI.open()
                SoundUtil.click(player)
                return
            }

            "§6Others 이동" -> {
                val otherShopPage1GUI = OtherShopPage1GUI(player)
                otherShopPage1GUI.setFirstGUI()
                GUIManager.setGUI(player.uniqueId, otherShopPage1GUI)
                otherShopPage1GUI.open()
                SoundUtil.click(player)
                return
            }

            "§f메인으로 이동" -> {
                val initShopGUI = InitShopGUI(player)
                initShopGUI.setFirstGUI()
                GUIManager.setGUI(player.uniqueId, initShopGUI)
                initShopGUI.open()
                SoundUtil.click(player)
                return
            }
        }
    }

    override fun InventoryDragEvent.dragEvent() {
        isCancelled = true
    }

    override fun InventoryCloseEvent.closeEvent() {

    }
}