package kr.eme.semiTradeShop.extensions

import kr.eme.semiTradeShop.objects.ShopItem
import org.bukkit.inventory.ItemStack

fun ShopItem.toBukkitItem(): ItemStack {
    val item = ItemStack(material)
    val meta = item.itemMeta
    meta?.setDisplayName(name)

    // 아이템 설명 및 가격 정보를 로어에 추가.
    meta?.lore = mutableListOf<String>().apply {
        add(description)
        if (buyPrice > 0) add("§6§l구매가 : $buyPrice EP") else add("§c§l구매불가")
        if (sellPrice > 0) add("§6§l판매가 : $sellPrice EP") else add("§c§l판매불가")    }
    item.itemMeta = meta
    return item
}
fun ShopItem.toBukkitItemWithoutPrice(): ItemStack {
    val item = ItemStack(material)
    val meta = item.itemMeta
    meta?.setDisplayName(name)

    // 로어에서 구매가/판매가를 제거하고 설명만 유지
    val loreWithoutPrice = mutableListOf<String>().apply {
        add(description) // 설명은 유지
    }

    meta?.lore = loreWithoutPrice
    item.itemMeta = meta
    return item
}
