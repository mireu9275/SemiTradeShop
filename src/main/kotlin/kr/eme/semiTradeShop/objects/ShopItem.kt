package kr.eme.semiTradeShop.objects

import org.bukkit.Material

data class ShopItem(
    val name: String,
    val buyPrice: Int,
    val sellPrice: Int,
    val description: String,
    val material: Material
)
