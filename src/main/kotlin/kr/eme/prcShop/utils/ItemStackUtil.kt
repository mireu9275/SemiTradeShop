package kr.eme.prcShop.utils

import kr.eme.prcMoney.managers.MoneyManager
import kr.eme.prcShop.objects.ShopItem
import kr.eme.prcShop.objects.guis.GUI
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.*

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
        val item = if (shopItem.prcItem != null) {
            shopItem.prcItem.create(1)
        } else {
            ItemStack(shopItem.material)
        }
        val meta = item.itemMeta ?: return
        meta.setDisplayName(shopItem.name)

        meta.lore = buildList {
            if (shopItem.tradeRequirements.isNotEmpty()) {
                add("§7[교환]")
                for (req in shopItem.tradeRequirements) {
                    add("${req.displayName} x${req.amount}")
                }
                add("§8────────────────────")
            } else {
                add(if (shopItem.buyPrice > 0) "§6구매가: ${shopItem.buyPrice} EP" else "§c구매 불가")
                add(if (shopItem.sellPrice > 0) "§3판매가: ${shopItem.sellPrice} EP" else "§c판매 불가")
            }

            if (shopItem.description.isNotBlank()) {
                shopItem.description.split(",").forEach { add(it.trim()) }
            }
        }

        shopItem.customModelData?.let { meta.setCustomModelData(it) }
        item.itemMeta = meta
        gui.setItem(shopItem.slot, item)
    }

    fun createSlotItemBuyOrSell(gui: GUI, shopItem: ShopItem) {
        gui.setItem(shopItem.slot, build(shopItem.material) { meta ->
            meta.setDisplayName(shopItem.name)
            shopItem.customModelData?.let { meta.setCustomModelData(it) }
        })
    }

    fun createQtyIcon(gui: GUI, qty: Int) {
        gui.setItem(36, build(Material.BROWN_DYE) { meta ->
            meta.setDisplayName("§f현재 선택 수량: $qty 개")
            meta.setCustomModelData(10)
        })
        gui.setItem(37, build(Material.BROWN_DYE) { meta ->
            meta.setDisplayName("§f현재 선택 수량: $qty 개")
            meta.setCustomModelData(11)
        })
    }

    fun createPriceIcon(gui: GUI, price: Int, type: String) {
        gui.setItem(38, build(Material.BROWN_DYE) { meta ->
            if (type == "BUY") {
                meta.setDisplayName("§f총 구매가: $price EP")
            } else if (type == "SELL") {
                meta.setDisplayName("§f총 판매가: $price EP")
            }
            meta.setCustomModelData(12)
        })
        gui.setItem(39, build(Material.BROWN_DYE) { meta ->
            if (type == "BUY") {
                meta.setDisplayName("§f총 구매가: $price EP")
            } else if (type == "SELL") {
                meta.setDisplayName("§f총 판매가: $price EP")
            }
            meta.setCustomModelData(13)
        })
    }

    fun createLeftButton(gui: GUI) {
        gui.setItem(36, build(Material.BROWN_DYE) { meta ->
            meta.setDisplayName("§f왼쪽으로 이동")
            meta.setCustomModelData(1)
        })
    }
    fun createRightButton(gui: GUI) {
        gui.setItem(44, build(Material.BROWN_DYE) { meta ->
            meta.setDisplayName("§f오른쪽으로 이동")
            meta.setCustomModelData(2)
        })
    }
    fun createMainButton(gui: GUI) {
        gui.setItem(49, build(Material.GLASS_PANE) { meta ->
            meta.setDisplayName("§f이전 페이지로 이동")
            meta.setCustomModelData(1)
        })
    }
    fun createEpButton(gui: GUI, uuid: UUID) {
        // uuid는 더 이상 사용하지 않지만, 기존 호출부 호환을 위해 파라미터는 유지
        val current = MoneyManager.getMoney()
        val text = "§f보유 EP : §a$current"
        gui.setItem(8, build(Material.LIME_DYE) { meta ->
            meta.setDisplayName(text)
            meta.lore = listOf("§7EP")
        })
    }

    fun cleanItemLore(originalItem: ItemStack): ItemStack {
        val newItem = originalItem.clone()
        val meta = newItem.itemMeta ?: return newItem
        val originalLore = meta.lore

        if (originalLore.isNullOrEmpty()) {
            meta.lore = null
        }
        else {
            val filteredLore = originalLore.drop(2).ifEmpty { null }
            meta.lore = filteredLore
        }
        newItem.itemMeta = meta
        return newItem
    }
    fun cutColorCodes(text: String): String {
        return text.replace(Regex("§."),"")
    }
}
