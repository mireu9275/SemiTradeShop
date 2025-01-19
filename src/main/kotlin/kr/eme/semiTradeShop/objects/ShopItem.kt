package kr.eme.semiTradeShop.objects

import org.bukkit.Material

data class ShopItem(
    val name: String,
    val buyPrice: Int,
    val sellPrice: Int,
    val description: String,
    val material: Material,
    val page: Int,
    val slot: Int,
    val customModelData: Int? = null // 입력하지 않는다면 커스텀 모델 데이터를 설정하지 않음.
)
