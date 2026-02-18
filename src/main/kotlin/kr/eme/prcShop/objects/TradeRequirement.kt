package kr.eme.prcShop.objects

import kr.eme.prcShop.api.PRCItem
import org.bukkit.Material

data class TradeRequirement(
    val material: Material,
    val amount: Int,
    val customModelData: Int = 0,  // 0이면 무시
    val itemName: String
) {
    /**
     * PRCItem을 기반으로 TradeRequirement를 생성하는 보조 생성자.
     */
    constructor(item: PRCItem, amount: Int) : this(
        material = item.material,
        amount = amount,
        customModelData = item.customModelData,
        itemName = item.displayName
    )
}
