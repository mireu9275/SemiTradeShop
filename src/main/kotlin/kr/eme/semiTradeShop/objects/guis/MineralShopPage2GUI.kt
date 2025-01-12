package kr.eme.semiTradeShop.objects.guis

import kr.eme.semiTradeShop.managers.GUIManager
import kr.eme.semiTradeShop.utils.ItemStackUtil
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent

class MineralShopPage2GUI(player: Player) : GUI(player, "§f\\u340F\\u3414", 6) {

    override fun setFirstGUI() {
        for (row in 0 until 6) {
            // 왼쪽 (클릭 시 SHOP 화면 이동)
            //val rowStart = row * 9

            setItem(9, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f리튬 파우더")
                meta.setCustomModelData(2)
            })

            setItem(10, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f마그네슘 파우더")
                meta.setCustomModelData(5)
            })

            setItem(11, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f니켈 파우더")
                meta.setCustomModelData(8)
            })

            setItem(12, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f백금 파우더")
                meta.setCustomModelData(11)
            })

            setItem(13, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f알루미늄 파우더")
                meta.setCustomModelData(14)
            })

            setItem(18, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f금 파우더")
                meta.setCustomModelData(17)
            })

            setItem(19, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f구리 파우더")
                meta.setCustomModelData(20)
            })

            setItem(20, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f철 파우더")
                meta.setCustomModelData(23)
            })

            setItem(21, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f티타늄 파우더")
                meta.setCustomModelData(26)
            })

            setItem(22, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f합금 주괴")
                meta.lore = listOf("§f알루미늄 + 구리")
                meta.setCustomModelData(28)
            })

            setItem(27, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f합금 주괴")
                meta.lore = listOf("§f알루미늄 + 마그네슘")
                meta.setCustomModelData(29)
            })

            setItem(28, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f합금 주괴")
                meta.lore = listOf("§f알루미늄 + 리튬")
                meta.setCustomModelData(30)
            })

            setItem(29, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f합금 주괴")
                meta.lore = listOf("§f구리 + 금")
                meta.setCustomModelData(31)
            })

            setItem(30, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f합금 주괴")
                meta.lore = listOf("§f니켈 + 철")
                meta.setCustomModelData(32)
            })

            setItem(31, ItemStackUtil.build(Material.WARPED_FUNGUS_ON_A_STICK) { meta ->
                meta.setDisplayName("§f합금 주괴")
                meta.lore = listOf("§f티타늄 + 백금 + 금")
                meta.setCustomModelData(33)
            })

            ItemStackUtil.createMainButton(this)
            ItemStackUtil.createLeftButton(this)
        }
    }

    override fun InventoryClickEvent.clickEvent() {
        isCancelled = true
        val clickedItem = currentItem ?: return // 클릭된 아이템이 없는 경우 무시
        val itemDisplayName = clickedItem.itemMeta?.displayName ?: return // 아이템 이름이 없는 경우 무시

        when (itemDisplayName) {
            "§f왼쪽으로 이동" -> {
                val mineralShopPage1GUI = MineralShopPage1GUI(player)
                mineralShopPage1GUI.setFirstGUI()
                GUIManager.setGUI(player.uniqueId, mineralShopPage1GUI)
                mineralShopPage1GUI.open()
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