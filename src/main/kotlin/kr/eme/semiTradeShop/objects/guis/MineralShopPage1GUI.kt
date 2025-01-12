package kr.eme.semiTradeShop.objects.guis

import kr.eme.semiTradeShop.managers.GUIManager
import kr.eme.semiTradeShop.utils.ItemStackUtil
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent

class MineralShopPage1GUI(player: Player) : GUI(player, "§f\\u340F\\u3412", 6) {

    override fun setFirstGUI() {
        for (row in 0 until 6) {
            // 왼쪽 (클릭 시 SHOP 화면 이동)
            //val rowStart = row * 9

            setItem(9, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f리튬")
                meta.setCustomModelData(1)
            })

            setItem(10, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f마그네슘")
                meta.setCustomModelData(4)
            })

            setItem(11, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f니켈")
                meta.setCustomModelData(7)
            })

            setItem(12, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f백금")
                meta.setCustomModelData(10)
            })

            setItem(13, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f알루미늄")
                meta.setCustomModelData(13)
            })

            setItem(18, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f금")
                meta.setCustomModelData(16)
            })

            setItem(19, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f구리")
                meta.setCustomModelData(19)
            })

            setItem(20, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f철")
                meta.setCustomModelData(22)
            })

            setItem(21, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f티타늄")
                meta.setCustomModelData(25)
            })

            ItemStackUtil.createMainButton(this)
            ItemStackUtil.createRightButton(this)
        }
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