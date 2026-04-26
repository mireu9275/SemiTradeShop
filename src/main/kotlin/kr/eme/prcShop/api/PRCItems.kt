@file:Suppress("UnstableApiUsage")

package kr.eme.prcShop.api

import kr.eme.prcShop.main
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.inventory.EquipmentSlotGroup
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.meta.CompassMeta
import org.bukkit.inventory.meta.ItemMeta

/**
 * PRCShop 커스텀 아이템 레지스트리.
 * 모든 아이템이 val로 선언되어 있어, 외부 플러그인에서 직접 참조할 수 있습니다.
 *
 * 사용 예시:
 * ```kotlin
 * val item = PRCItems.IRON_INGOT.create(5)
 * if (PRCItems.IRON_INGOT.matches(stack)) { ... }
 * val found = PRCItems.getItem(stack)
 * ```
 */
object PRCItems {

    private val _registry = mutableListOf<PRCItem>()

    private fun item(
        itemName: String? = null,
        material: Material,
        customModelData: Int,
        description: String = "",
        eatable: Boolean = false,
        metaModifier: ((ItemMeta) -> Unit)? = null
    ): PRCItem = PRCItem(itemName, material, customModelData, description, eatable, metaModifier)
        .also { _registry.add(it) }

    private fun compass(customModelData: Int, x: Int, y: Int, z: Int): PRCItem =
        item(null, Material.COMPASS, customModelData, metaModifier = { meta ->
            meta.displayName(
                Component.text("신호 추적 레이더")
                    .color(TextColor.color(0xCFFF70))
                    .decoration(TextDecoration.ITALIC, false)
            )
            if (meta is CompassMeta) {
                val world = Bukkit.getWorld("world") ?: Bukkit.getWorlds().firstOrNull()
                if (world != null) {
                    meta.lodestone = Location(world, x + 0.5, y.toDouble(), z + 0.5)
                    meta.isLodestoneTracked = false
                }
            }
        })

    // ═══════════════════════════════════════════
    //  광물 - 원석
    // ═══════════════════════════════════════════
    /** 마그네슘 */
    val MAGNESIUM        = item("§f마그네슘", Material.RED_DYE, 1)
    /** 알루미늄 */
    val ALUMINUM         = item("§f알루미늄", Material.RED_DYE, 2)
    /** 철 */
    val IRON             = item("§f철", Material.RED_DYE, 3)
    /** 구리 */
    val COPPER           = item("§f구리", Material.RED_DYE, 4)
    /** 리튬 */
    val LITHIUM          = item("§f리튬", Material.RED_DYE, 5)
    /** 금 */
    val GOLD             = item("§f금", Material.RED_DYE, 6)
    /** 백금 */
    val PLATINUM         = item("§f백금", Material.RED_DYE, 7)
    /** 니켈 */
    val NICKEL           = item("§f니켈", Material.RED_DYE, 8)
    /** 티타늄 */
    val TITANIUM         = item("§f티타늄", Material.RED_DYE, 9)

    // ═══════════════════════════════════════════
    //  광물 - 파우더
    // ═══════════════════════════════════════════
    /** 마그네슘 파우더 */
    val MAGNESIUM_POWDER = item("§f마그네슘 파우더", Material.RED_DYE, 10)
    /** 알루미늄 파우더 */
    val ALUMINUM_POWDER  = item("§f알루미늄 파우더", Material.RED_DYE, 11)
    /** 철 파우더 */
    val IRON_POWDER      = item("§f철 파우더", Material.RED_DYE, 12)
    /** 구리 파우더 */
    val COPPER_POWDER    = item("§f구리 파우더", Material.RED_DYE, 13)
    /** 리튬 파우더 */
    val LITHIUM_POWDER   = item("§f리튬 파우더", Material.RED_DYE, 14)
    /** 금 파우더 */
    val GOLD_POWDER      = item("§f금 파우더", Material.RED_DYE, 15)
    /** 백금 파우더 */
    val PLATINUM_POWDER  = item("§f백금 파우더", Material.RED_DYE, 16)
    /** 니켈 파우더 */
    val NICKEL_POWDER    = item("§f니켈 파우더", Material.RED_DYE, 17)
    /** 티타늄 파우더 */
    val TITANIUM_POWDER  = item("§f티타늄 파우더", Material.RED_DYE, 18)

    // ═══════════════════════════════════════════
    //  광물 - 주괴
    // ═══════════════════════════════════════════
    /** 마그네슘 주괴 */
    val MAGNESIUM_INGOT  = item("§f마그네슘 주괴", Material.RED_DYE, 19)
    /** 알루미늄 주괴 */
    val ALUMINUM_INGOT   = item("§f알루미늄 주괴", Material.RED_DYE, 20)
    /** 철 주괴 */
    val IRON_INGOT       = item("§f철 주괴", Material.RED_DYE, 21)
    /** 구리 주괴 */
    val COPPER_INGOT     = item("§f구리 주괴", Material.RED_DYE, 22)
    /** 리튬 주괴 */
    val LITHIUM_INGOT    = item("§f리튬 주괴", Material.RED_DYE, 23)
    /** 금 주괴 */
    val GOLD_INGOT       = item("§f금 주괴", Material.RED_DYE, 24)
    /** 백금 주괴 */
    val PLATINUM_INGOT   = item("§f백금 주괴", Material.RED_DYE, 25)
    /** 니켈 주괴 */
    val NICKEL_INGOT     = item("§f니켈 주괴", Material.RED_DYE, 26)
    /** 티타늄 주괴 */
    val TITANIUM_INGOT   = item("§f티타늄 주괴", Material.RED_DYE, 27)

    // ═══════════════════════════════════════════
    //  합금 주괴
    // ═══════════════════════════════════════════
    /** 합금 주괴 (Al-Cu) */
    val AL_CU_ALLOY_INGOT     = item("§f합금 주괴", Material.RED_DYE, 29, "§fAl-Cu 합금 주괴")
    /** 합금 주괴 (Al-Mg) */
    val AL_MG_ALLOY_INGOT     = item("§f합금 주괴", Material.RED_DYE, 28, "§fAl-Mg 합금 주괴")
    /** 합금 주괴 (Al-Li) */
    val AL_LI_ALLOY_INGOT     = item("§f합금 주괴", Material.RED_DYE, 30, "§fAl-Li 합금 주괴")
    /** 합금 주괴 (Cu-Au) */
    val CU_AU_ALLOY_INGOT     = item("§f합금 주괴", Material.RED_DYE, 31, "§fCu-Au 합금 주괴")
    /** 합금 주괴 (Ni-Fe) */
    val NI_FE_ALLOY_INGOT     = item("§f합금 주괴", Material.RED_DYE, 32, "§fNi-Fe 합금 주괴")
    /** 합금 주괴 (Ti-Pt-Au) */
    val TI_PT_AU_ALLOY_INGOT  = item("§f합금 주괴", Material.RED_DYE, 33, "§fTi-Pt-Au 합금 주괴")

    // ═══════════════════════════════════════════
    //  작물 - 씨앗
    // ═══════════════════════════════════════════
    /** 감자 씨앗 */
    val POTATO_SEED    = item("§f감자 씨앗", Material.BLACK_DYE, 31, "§f수확일 3일")
    /** 양배추 씨앗 */
    val CABBAGE_SEED   = item("§f양배추 씨앗", Material.BLACK_DYE, 32, "§f수확일 6일")
    /** 오이 씨앗 */
    val CUCUMBER_SEED  = item("§f오이 씨앗", Material.BLACK_DYE, 33, "§f수확일 4일")
    /** 커피콩 */
    val COFFEE_BEAN_SEED = item("§f커피콩", Material.BLACK_DYE, 34, "§f수확일 3일")
    /** 토마토 씨앗 */
    val TOMATO_SEED    = item("§f토마토 씨앗", Material.BLACK_DYE, 35, "§f수확일 3일")
    /** 옥수수 씨앗 */
    val CORN_SEED      = item("§f옥수수 씨앗", Material.BLACK_DYE, 36, "§f수확일 6일")
    /** 밀 씨앗 */
    val WHEAT_SEED     = item("§f밀 씨앗", Material.BLACK_DYE, 37, "§f수확일 7일")
    /** 크랜베리 씨앗 */
    val CRANBERRY_SEED = item("§f크랜베리 씨앗", Material.BLACK_DYE, 38, "§f수확일 3일")
    /** 비트 씨앗 */
    val BEET_SEED      = item("§f비트 씨앗", Material.BLACK_DYE, 39, "§f수확일 4일")
    /** 호박 씨앗 */
    val PUMPKIN_SEED   = item("§f호박 씨앗", Material.BLACK_DYE, 40, "§f수확일 7일")

    // ═══════════════════════════════════════════
    //  작물 - 수확물 (은 등급 ★)
    // ═══════════════════════════════════════════
    /** 감자 (★) */
    val POTATO_G1    = item("§f감자", Material.BLACK_DYE, 1, "§f수확일 3일", eatable = true)
    /** 양배추 (★) */
    val CABBAGE_G1   = item("§f양배추", Material.BLACK_DYE, 2, "§f수확일 6일", eatable = true)
    /** 오이 (★) */
    val CUCUMBER_G1  = item("§f오이", Material.BLACK_DYE, 3, "§f수확일 4일", eatable = true)
    /** 커피콩 (★) */
    val COFFEE_BEAN_G1 = item("§f커피콩", Material.BLACK_DYE, 4, "§f수확일 3일", eatable = true)
    /** 토마토 (★) */
    val TOMATO_G1    = item("§f토마토", Material.BLACK_DYE, 5, "§f수확일 3일", eatable = true)
    /** 옥수수 (★) */
    val CORN_G1      = item("§f옥수수", Material.BLACK_DYE, 6, "§f수확일 6일", eatable = true)
    /** 밀 (★) */
    val WHEAT_G1     = item("§f밀", Material.BLACK_DYE, 7, "§f수확일 7일", eatable = true)
    /** 크랜베리 (★) */
    val CRANBERRY_G1 = item("§f크랜베리", Material.BLACK_DYE, 8, "§f수확일 3일", eatable = true)
    /** 비트 (★) */
    val BEET_G1      = item("§f비트", Material.BLACK_DYE, 9, "§f수확일 4일", eatable = true)
    /** 호박 (★) */
    val PUMPKIN_G1   = item("§f호박", Material.BLACK_DYE, 10, "§f수확일 7일", eatable = true)

    // ═══════════════════════════════════════════
    //  작물 - 수확물 (금 등급 ★★)
    // ═══════════════════════════════════════════
    /** 감자 (★★) */
    val POTATO_G2    = item("§f감자", Material.BLACK_DYE, 11, "§f수확일 3일", eatable = true)
    /** 양배추 (★★) */
    val CABBAGE_G2   = item("§f양배추", Material.BLACK_DYE, 12, "§f수확일 6일", eatable = true)
    /** 오이 (★★) */
    val CUCUMBER_G2  = item("§f오이", Material.BLACK_DYE, 13, "§f수확일 4일", eatable = true)
    /** 커피콩 (★★) */
    val COFFEE_BEAN_G2 = item("§f커피콩", Material.BLACK_DYE, 14, "§f수확일 3일", eatable = true)
    /** 토마토 (★★) */
    val TOMATO_G2    = item("§f토마토", Material.BLACK_DYE, 15, "§f수확일 3일", eatable = true)
    /** 옥수수 (★★) */
    val CORN_G2      = item("§f옥수수", Material.BLACK_DYE, 16, "§f수확일 6일", eatable = true)
    /** 밀 (★★) */
    val WHEAT_G2     = item("§f밀", Material.BLACK_DYE, 17, "§f수확일 7일", eatable = true)
    /** 크랜베리 (★★) */
    val CRANBERRY_G2 = item("§f크랜베리", Material.BLACK_DYE, 18, "§f수확일 3일", eatable = true)
    /** 비트 (★★) */
    val BEET_G2      = item("§f비트", Material.BLACK_DYE, 19, "§f수확일 4일", eatable = true)
    /** 호박 (★★) */
    val PUMPKIN_G2   = item("§f호박", Material.BLACK_DYE, 20, "§f수확일 7일", eatable = true)

    // ═══════════════════════════════════════════
    //  작물 - 수확물 (다이아 등급 ★★★)
    // ═══════════════════════════════════════════
    /** 감자 (★★★) */
    val POTATO_G3    = item("§f감자", Material.BLACK_DYE, 21, "§f수확일 3일", eatable = true)
    /** 양배추 (★★★) */
    val CABBAGE_G3   = item("§f양배추", Material.BLACK_DYE, 22, "§f수확일 6일", eatable = true)
    /** 오이 (★★★) */
    val CUCUMBER_G3  = item("§f오이", Material.BLACK_DYE, 23, "§f수확일 4일", eatable = true)
    /** 커피콩 (★★★) */
    val COFFEE_BEAN_G3 = item("§f커피콩", Material.BLACK_DYE, 24, "§f수확일 3일", eatable = true)
    /** 토마토 (★★★) */
    val TOMATO_G3    = item("§f토마토", Material.BLACK_DYE, 25, "§f수확일 3일", eatable = true)
    /** 옥수수 (★★★) */
    val CORN_G3      = item("§f옥수수", Material.BLACK_DYE, 26, "§f수확일 6일", eatable = true)
    /** 밀 (★★★) */
    val WHEAT_G3     = item("§f밀", Material.BLACK_DYE, 27, "§f수확일 7일", eatable = true)
    /** 크랜베리 (★★★) */
    val CRANBERRY_G3 = item("§f크랜베리", Material.BLACK_DYE, 28, "§f수확일 3일", eatable = true)
    /** 비트 (★★★) */
    val BEET_G3      = item("§f비트", Material.BLACK_DYE, 29, "§f수확일 4일", eatable = true)
    /** 호박 (★★★) */
    val PUMPKIN_G3   = item("§f호박", Material.BLACK_DYE, 30, "§f수확일 7일", eatable = true)

    // ═══════════════════════════════════════════
    //  기타 - 캡슐 / 도구
    // ═══════════════════════════════════════════
    /** 성장 캡슐 */
    val GROWTH_CAPSULE     = item("§f성장 캡슐", Material.ORANGE_DYE, 3, "§f작물의 수확일을 줄여줍니다.")
    /** 영양 캡슐 */
    val NUTRITION_CAPSULE  = item("§f영양 캡슐", Material.ORANGE_DYE, 4, "§f더 높은 등급의 작물이 나올 확률이 증가합니다.")
    /** 제초 캡슐 */
    val HERBICIDE_CAPSULE  = item("§f제초 캡슐", Material.ORANGE_DYE, 2, "§f잡초를 제거합니다.")
    
    /** 머그잔 */
    val MUG                = item("§f머그잔", Material.BOWL, 1, "§fJava")
    /** 스패너 */
    val SPANNER            = item("§f스패너", Material.WOODEN_SHOVEL, 13)

    val CHEQUE             = item("§f토큰", Material.SADDLE, 38)
    val ENDING_ITEM        = item("§7???", Material.ORANGE_DYE, 10)
    val FOOD_CAPSULE       = item("§f식량 캡슐" ,Material.GOLDEN_CARROT, 0)

//    val UNKNOWN_FLESH = item(material = Material.ROTTEN_FLESH, customModelData = null)
    val ROTTEN_FLESH         = item(material = Material.ROTTEN_FLESH, customModelData = 0)
    val OLD_DOG_TAG          = item(material = Material.IRON_INGOT, customModelData = 0)

    val CHAINMAIL_HELMET     = item(material = Material.CHAINMAIL_HELMET, customModelData = 0)
    val CHAINMAIL_CHESTPLATE = item(material = Material.CHAINMAIL_CHESTPLATE, customModelData = 0)
    val CHAINMAIL_LEGGINGS   = item(material = Material.CHAINMAIL_LEGGINGS, customModelData = 0)
    val CHAINMAIL_BOOTS      = item(material = Material.CHAINMAIL_BOOTS, customModelData = 0)

    val IRON_HELMET     = item(material = Material.IRON_HELMET, customModelData = 0)
    val IRON_CHESTPLATE = item(material = Material.IRON_CHESTPLATE, customModelData = 0)
    val IRON_LEGGINGS   = item(material = Material.IRON_LEGGINGS, customModelData = 0)
    val IRON_BOOTS      = item(material = Material.IRON_BOOTS, customModelData = 0, description = "§7높은 지형을 오르는 걸 용이하게 해줍니다.", metaModifier = { meta ->
        meta.addAttributeModifier(Attribute.STEP_HEIGHT, AttributeModifier(
            NamespacedKey(main, "step_height"),
            1.0,
            AttributeModifier.Operation.ADD_NUMBER,
            EquipmentSlotGroup.FEET
        ))
        meta.addAttributeModifier(Attribute.ARMOR, AttributeModifier(
            NamespacedKey(main, "iron_boots_armor"),
            0.0,
            AttributeModifier.Operation.ADD_NUMBER,
            EquipmentSlotGroup.FEET
        ))
    })

    // ═══════════════════════════════════════════
    //  기타 - 빌드형 모듈
    // ═══════════════════════════════════════════
    /** 기본 연장 모듈 */
    val BASIC_TOOL_MODULE      = item("§f기본 연장 모듈", Material.SADDLE, 1)
    /** T 연장 모듈 */
    val T_TOOL_MODULE          = item("§fT 연장 모듈", Material.SADDLE, 2)
    /** 십자 연장 모듈 */
    val CROSS_TOOL_MODULE      = item("§f십자 연장 모듈", Material.SADDLE, 3)
    /** 스토리지 모듈 [중] */
    val STORAGE_MODULE_MEDIUM  = item("§f스토리지 모듈 [중]", Material.SADDLE, 4)
    /** 스토리지 모듈 [대] */
    val STORAGE_MODULE_LARGE   = item("§f스토리지 모듈 [대]", Material.SADDLE, 5)
    /** 광산 모듈 */
    val MINE_MODULE            = item("§f광산 모듈", Material.SADDLE, 6)
    /** 농사 모듈 [중] */
    val FARM_MODULE_MEDIUM     = item("§f농사 모듈 [중]", Material.SADDLE, 7)
    /** 농사 모듈 [대] */
    val FARM_MODULE_LARGE      = item("§f농사 모듈 [대]", Material.SADDLE, 8)
    /** 출입 모듈 */
    val GATE_MODULE            = item("§f출입 모듈", Material.SADDLE, 9)

    // ═══════════════════════════════════════════
    //  기타 - 설치형 모듈
    // ═══════════════════════════════════════════
    /** 분쇄기 모듈 */
    val GRINDER_MODULE         = item("§f분쇄기 모듈", Material.IRON_HORSE_ARMOR, 2, metaModifier = ::hideAttribute)
    /** 프린트 모듈 */
    val PRINTER_MODULE         = item("§f프린트 모듈", Material.IRON_HORSE_ARMOR, 3, metaModifier = ::hideAttribute)
    /** 커피머신 모듈 */
    val COFFEE_MACHINE_MODULE  = item("§f커피머신 모듈", Material.IRON_HORSE_ARMOR, 4, metaModifier = ::hideAttribute)
    /** 용광로 모듈 */
    val FURNACE_MODULE         = item("§f용광로 모듈", Material.IRON_HORSE_ARMOR, 7, metaModifier = ::hideAttribute)

    // ═══════════════════════════════════════════
    //  기타 - 업그레이드 / 부품
    // ═══════════════════════════════════════════
    /** 용광로 용해 슬롯 확장 */
    val FURNACE_SLOT_UPGRADE         = item("§f용광로 용해 슬롯 확장", Material.SADDLE, 17)
    /** 합금 냉각 몰드 */
    val ALLOY_COOLING_MOLD_CU_AU     = item("§f합금 냉각 몰드", Material.SADDLE, 18, "§fCu-Au")
    /** 합금 토치 */
    val ALLOY_TORCH_NI_FE            = item("§f합금 토치", Material.SADDLE, 19, "§fNi-Fe")
    /** 분쇄기 용량 확장 */
    val GRINDER_CAPACITY_UPGRADE     = item("§f분쇄기 용량 확장", Material.SADDLE, 20)
    /** 합금 기어 */
    val ALLOY_GEAR_AL_CU             = item("§f합금 기어", Material.SADDLE, 21, "§fAl-Cu")
    /** 합금 드릴 */
    val ALLOY_DRILL_TI_PT_AU         = item("§f합금 드릴", Material.SADDLE, 22, "§fTi-Pt-Au")

    // ═══════════════════════════════════════════
    //  기타 - 레시피
    // ═══════════════════════════════════════════
    /** 마그네슘 주괴 레시피 */
    val RECIPE_MAGNESIUM_INGOT  = item("§f마그네슘 주괴 레시피", Material.SADDLE, 23)
    /** 알루미늄 주괴 레시피 */
    val RECIPE_ALUMINUM_INGOT   = item("§f알루미늄 주괴 레시피", Material.SADDLE, 24)
    /** 철 주괴 레시피 */
    val RECIPE_IRON_INGOT       = item("§f철 주괴 레시피", Material.SADDLE, 25)
    /** 구리 주괴 레시피 */
    val RECIPE_COPPER_INGOT     = item("§f구리 주괴 레시피", Material.SADDLE, 26)
    /** 리튬 주괴 레시피 */
    val RECIPE_LITHIUM_INGOT    = item("§f리튬 주괴 레시피", Material.SADDLE, 27)
    /** 금 주괴 레시피 */
    val RECIPE_GOLD_INGOT       = item("§f금 주괴 레시피", Material.SADDLE, 28)
    /** 백금 주괴 레시피 */
    val RECIPE_PLATINUM_INGOT   = item("§f백금 주괴 레시피", Material.SADDLE, 29)
    /** 니켈 주괴 레시피 */
    val RECIPE_NICKEL_INGOT     = item("§f니켈 주괴 레시피", Material.SADDLE, 30)
    /** 티타늄 주괴 레시피 */
    val RECIPE_TITANIUM_INGOT   = item("§f티타늄 주괴 레시피", Material.SADDLE, 31)
    /** Al-Cu 합금 레시피 */
    val RECIPE_AL_CU_ALLOY      = item("§fAl-Cu 합금 레시피", Material.SADDLE, 33, "§f알루미늄 + 구리")
    /** Al-Mg 합금 레시피 */
    val RECIPE_AL_MG_ALLOY      = item("§fAl-Mg 합금 레시피", Material.SADDLE, 32, "§f알루미늄 + 마그네슘")
    /** Al-Li 합금 레시피 */
    val RECIPE_AL_LI_ALLOY      = item("§fAl-Li 합금 레시피", Material.SADDLE, 34, "§f알루미늄 + 리튬")
    /** Cu-Au 합금 레시피 */
    val RECIPE_CU_AU_ALLOY      = item("§fCu-Au 합금 레시피", Material.SADDLE, 35, "§f구리 + 금")
    /** Ni-Fe 합금 레시피 */
    val RECIPE_NI_FE_ALLOY      = item("§fNi-Fe 합금 레시피", Material.SADDLE, 36, "§f니켈 + 철")
    /** Ti-Pt-Au 합금 레시피 */
    val RECIPE_TI_PT_AU_ALLOY   = item("§fTi-Pt-Au 합금 레시피", Material.SADDLE, 37, "§f티타늄 + 백금 + 금")

    // ═══════════════════════════════════════════
    //  기타 - 로드스톤 나침반
    // ═══════════════════════════════════════════
    /** 신호 추적 레이더 1 */
    val COMPASS_1 = compass(1, 485, -20, 862)
    /** 신호 추적 레이더 2 */
    val COMPASS_2 = compass(2, 128,  89, 816)
    /** 신호 추적 레이더 3 */
    val COMPASS_3 = compass(3, 650,  -1, 256)
    /** 신호 추적 레이더 4 */
    val COMPASS_4 = compass(4, 232, 141, 494)

    // ═══════════════════════════════════════════
    //  유틸리티 메서드
    // ═══════════════════════════════════════════

    /**
     * 등록된 모든 PRCItem 의 읽기 전용 목록.
     */
    val all: List<PRCItem> get() = _registry.toList()

    /**
     * ItemStack에서 매칭되는 PRCItem을 역추적합니다.
     */
    fun getItem(item: org.bukkit.inventory.ItemStack?): PRCItem? {
        if (item == null) return null
        val meta = item.itemMeta ?: return null
        if (!meta.hasCustomModelData()) return null
        return _registry.find { it.material == item.type && it.customModelData == meta.customModelData }
    }

    private fun hideAttribute(meta: ItemMeta) {
        meta.addAttributeModifier(Attribute.ARMOR, AttributeModifier(
            NamespacedKey(main, "hide_attribute"),
            0.0,
            AttributeModifier.Operation.ADD_NUMBER,
            EquipmentSlotGroup.ANY
        ))
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
    }
}
