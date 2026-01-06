package kr.eme.semiTrade.extensions

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataContainer

fun ItemStack.hasNBT(key: String): Boolean = itemMeta.hasNBT(key)
fun ItemStack.getIntNBT(key: String): Int? = itemMeta.getIntNBT(key)
fun ItemStack.getStringNBT(key: String): String? = itemMeta.getStringNBT(key)

fun ItemStack.setIntNBT(key: String, value: Int) { itemMeta.setIntNBT(key, value) }
fun ItemStack.setStringNBT(key: String, value: String) { itemMeta.setStringNBT(key, value) }

fun ItemStack.editCustomNBT(block: (PersistentDataContainer) -> Unit) {
    block(itemMeta.persistentDataContainer)
}

fun ItemStack.editItemMeta(block: (ItemMeta) -> Unit): ItemStack {
    val meta = itemMeta
    block(meta)
    itemMeta = meta
    return this
}