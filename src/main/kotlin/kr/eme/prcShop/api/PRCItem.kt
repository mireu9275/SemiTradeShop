package kr.eme.prcShop.api

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * 커스텀 아이템 정의 클래스.
 * PRCItems object에서 val로 선언되어 사용됩니다.
 *
 * 사용 예시:
 * ```kotlin
 * val item = PRCItems.IRON_INGOT.create(5)
 * if (PRCItems.IRON_INGOT.matches(someStack)) { ... }
 * ```
 */
class PRCItem internal constructor(
    val displayName: String,
    val material: Material,
    val customModelData: Int,
    val description: String = "",
    val eatable: Boolean = false
) {
    /**
     * 이 아이템 정의를 기반으로 ItemStack을 생성합니다.
     */
    fun create(amount: Int = 1): ItemStack {
        val item = ItemStack(material, amount)
        val meta = item.itemMeta ?: return item
        meta.setDisplayName(displayName)
        meta.setCustomModelData(customModelData)
        if (description.isNotBlank()) {
            meta.lore = description.split(",").map { it.trim() }
        }
        item.itemMeta = meta
        return item
    }

    /**
     * 주어진 ItemStack이 이 아이템 정의와 일치하는지 확인합니다.
     */
    fun matches(item: ItemStack?): Boolean {
        if (item == null) return false
        val meta = item.itemMeta ?: return false
        return item.type == material
                && meta.hasCustomModelData()
                && meta.customModelData == customModelData
    }

    override fun toString(): String = "PRCItem($displayName, $material, cmd=$customModelData)"
}
