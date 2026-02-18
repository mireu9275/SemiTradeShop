package kr.eme.prcShop.extensions

import kr.eme.prcShop.objects.ShopItem
import org.bukkit.inventory.ItemStack

fun ShopItem.toBukkitItem(): ItemStack {
    // PRCItem이 있으면 API를 통해 생성
    if (prcItem != null) {
        val item = prcItem.create()
        val meta = item.itemMeta ?: return item
        meta.lore = mutableListOf<String>().apply {
            if (buyPrice > 0) add("§6§l구매가 : $buyPrice EP") else add("§c§l구매불가")
            if (sellPrice > 0) add("§6§l판매가 : $sellPrice EP") else add("§c§l판매불가")
            if (description.isNotBlank()) {
                description.split(",").forEach { add(it.trim()) }
            }
        }
        item.itemMeta = meta
        return item
    }

    // 기존 방식 (GUI 전용 아이템 등)
    val item = ItemStack(material)
    val meta = item.itemMeta
    meta?.setDisplayName(name)
    meta?.lore = mutableListOf<String>().apply {
        add(description)
        if (buyPrice > 0) add("§6§l구매가 : $buyPrice EP") else add("§c§l구매불가")
        if (sellPrice > 0) add("§6§l판매가 : $sellPrice EP") else add("§c§l판매불가")
    }
    item.itemMeta = meta
    return item
}

fun ShopItem.toBukkitItemWithoutPrice(): ItemStack {
    // PRCItem이 있으면 API를 통해 생성
    if (prcItem != null) {
        val item = prcItem.create()
        if (description.isNotBlank()) {
            val meta = item.itemMeta ?: return item
            meta.lore = description.split(",").map { it.trim() }
            item.itemMeta = meta
        }
        return item
    }

    // 기존 방식
    val item = ItemStack(material)
    val meta = item.itemMeta
    meta?.setDisplayName(name)
    val loreWithoutPrice = mutableListOf<String>().apply {
        add(description)
    }
    meta?.lore = loreWithoutPrice
    item.itemMeta = meta
    return item
}
