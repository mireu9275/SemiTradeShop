package kr.eme.semiTradeShop.utils

import kr.eme.semiTradeShop.objects.ShopItem
import kr.eme.semiTradeShop.objects.guis.GUI
import kr.eme.semiTradeShop.semiMoney
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.UUID

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
    fun createSlotItem(gui: GUI, shopItem: ShopItem) {
        gui.setItem(shopItem.slot, build(shopItem.material) { meta ->
            meta.setDisplayName(shopItem.name)
            meta.lore = buildList {
                add(if(shopItem.buyPrice > 0) "§6구매가: ${shopItem.buyPrice} EP" else "§c구매 불가")
                add(if(shopItem.sellPrice > 0) "§3판매가: ${shopItem.sellPrice} EP" else "§c판매 불가")
                if (shopItem.description.isNotBlank()) {
                    shopItem.description.split(",").forEach { add(it.trim()) }
                }
            }
            shopItem.customModelData?.let { meta.setCustomModelData(it) }
        })
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
    fun createEpButton(gui: GUI, uuid: UUID) {
        var text: String
        var playerMoney: Int? = null
        val moneyManager = semiMoney.getMoneyManager()
        if (moneyManager != null) {
            playerMoney = moneyManager.getMoney(uuid) ?: null
        }
        if (playerMoney == null) {
            text = "§c시스템 오류(버그)"
        } else {
            text = "§f보유 EP : §a$playerMoney"
        }
        gui.setItem(8, build(Material.LIME_DYE) { meta ->
            meta.setDisplayName(text)
            meta.lore = listOf(
                "§f설명란"
            )
        })
    }
}