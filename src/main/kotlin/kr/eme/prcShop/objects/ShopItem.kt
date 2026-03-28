package kr.eme.prcShop.objects

import kr.eme.prcShop.api.PRCItem
import org.bukkit.Material

data class ShopItem(
    val name: String? = null,
    val buyPrice: Int,
    val sellPrice: Int,
    val description: String,
    val material: Material,
    val page: Int,
    val slot: Int,
    val customModelData: Int? = null,
    val tradeRequirements: List<TradeRequirement> = emptyList(),
    val prcItem: PRCItem? = null
) {
    /**
     * PRCItem을 기반으로 ShopItem을 생성하는 보조 생성자.
     */
    constructor(
        prcItem: PRCItem,
        buyPrice: Int,
        sellPrice: Int,
        page: Int,
        slot: Int,
        tradeRequirements: List<TradeRequirement> = emptyList()
    ) : this(
        name = prcItem.displayName,
        buyPrice = buyPrice,
        sellPrice = sellPrice,
        description = prcItem.description,
        material = prcItem.material,
        page = page,
        slot = slot,
        customModelData = prcItem.customModelData,
        tradeRequirements = tradeRequirements,
        prcItem = prcItem
    )
}
