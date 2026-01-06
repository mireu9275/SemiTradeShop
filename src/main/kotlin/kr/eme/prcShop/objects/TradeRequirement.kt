package kr.eme.prcShop.objects

import org.bukkit.Material

data class TradeRequirement(
    val material: Material,
    val amount: Int,
    val customModelData: Int = 0,  // 0이면 무시
    val itemName: String
)
