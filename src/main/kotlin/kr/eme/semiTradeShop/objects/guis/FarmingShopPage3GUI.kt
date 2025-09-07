package kr.eme.semiTradeShop.objects.guis

import kr.eme.semiTradeShop.managers.GUIManager
import kr.eme.semiTradeShop.objects.ShopItems
import kr.eme.semiTradeShop.utils.ItemStackUtil
import kr.eme.semiTradeShop.utils.SoundUtil
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent

class FarmingShopPage3GUI(player: Player) : GUI(player, "§f\\u340F\\u3417" ,6){
    override fun setFirstGUI() {
        val items = ShopItems.getShopItems("FarmingShop", 3)
        for (item in items) {
            ItemStackUtil.createSlotItem(this, item)
        }
        ItemStackUtil.createRightButton(this)
        ItemStackUtil.createLeftButton(this)
        ItemStackUtil.createMainButton(this)
        ItemStackUtil.createEpButton(this, player.uniqueId)
    }

    override fun InventoryClickEvent.clickEvent() {
        isCancelled = true

        // 클릭된 아이템과 메타 정보 확인
        val clickedItem = currentItem ?: run {
            // 클릭된 아이템이 없는 경우 무시
            SoundUtil.error(player)
            return
        }
        val itemMeta = clickedItem.itemMeta ?: run {
            SoundUtil.error(player)
            return
        }
        val lore = itemMeta.lore
        val itemDisplayName = clickedItem.itemMeta?.displayName ?: run {
            // 아이템 이름이 없는 경우 무시
            SoundUtil.error(player)
            return
        }

        when (itemDisplayName) {
            "§f왼쪽으로 이동" -> {
                val farmingShopPage2GUI = FarmingShopPage2GUI(player)
                farmingShopPage2GUI.setFirstGUI()
                GUIManager.setGUI(player.uniqueId, farmingShopPage2GUI)
                farmingShopPage2GUI.open()
                SoundUtil.click(player)
                return
            }
            "§f오른쪽으로 이동" -> {
                val farmingShopPage4GUI = FarmingShopPage4GUI(player)
                farmingShopPage4GUI.setFirstGUI()
                GUIManager.setGUI(player.uniqueId, farmingShopPage4GUI)
                farmingShopPage4GUI.open()
                SoundUtil.click(player)
                return
            }
            "§f메인으로 이동" -> {
                val shopGUI = ShopGUI(player)
                shopGUI.setFirstGUI()
                GUIManager.setGUI(player.uniqueId, shopGUI)
                shopGUI.open()
                SoundUtil.click(player)
                return
            }
        }
        // 고정된 이름이 아닌 경우 구매 및 판매 처리
        if (lore.isNullOrEmpty()) return

        if (isLeftClick) {
            // 구매가 처리
            val buyPrice = lore.firstOrNull { it.startsWith("§6구매가:") }
            if (buyPrice == null || buyPrice.contains("§c구매 불가")) {
                player.sendMessage("§c이 아이템은 구매할 수 없습니다!")
                SoundUtil.error(player)
                return
            }
            val buyGUI = BillBuyGUI(player, clickedItem.clone(), this@FarmingShopPage3GUI)
            buyGUI.setFirstGUI()
            GUIManager.setGUI(player.uniqueId, buyGUI)
            buyGUI.open()
            SoundUtil.click(player)
        } else if (isRightClick) {
            // 판매가 처리
            val sellPrice = lore.firstOrNull { it.startsWith("§3판매가:") }
            if (sellPrice == null || sellPrice.contains("§c판매 불가")) {
                player.sendMessage("§c이 아이템은 판매할 수 없습니다!")
                SoundUtil.error(player)
                return
            }
            val sellGUI = BillSellGUI(player, clickedItem.clone(), this@FarmingShopPage3GUI)
            sellGUI.setFirstGUI()
            GUIManager.setGUI(player.uniqueId, sellGUI)
            sellGUI.open()
            SoundUtil.click(player)
        }
    }

    override fun InventoryDragEvent.dragEvent() {
        isCancelled = true
    }

    override fun InventoryCloseEvent.closeEvent() {

    }
}