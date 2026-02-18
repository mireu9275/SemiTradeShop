package kr.eme.prcShop.api

import org.bukkit.Material

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
        displayName: String,
        material: Material,
        customModelData: Int,
        description: String = "",
        eatable: Boolean = false
    ): PRCItem = PRCItem(displayName, material, customModelData, description, eatable)
        .also { _registry.add(it) }

    // ═══════════════════════════════════════════
    //  광물 - 원석
    // ═══════════════════════════════════════════
    val MAGNESIUM        = item("§f마그네슘", Material.RED_DYE, 1)
    val ALUMINUM         = item("§f알루미늄", Material.RED_DYE, 2)
    val IRON             = item("§f철", Material.RED_DYE, 3)
    val COPPER           = item("§f구리", Material.RED_DYE, 4)
    val LITHIUM          = item("§f리튬", Material.RED_DYE, 5)
    val GOLD             = item("§f금", Material.RED_DYE, 6)
    val PLATINUM         = item("§f백금", Material.RED_DYE, 7)
    val NICKEL           = item("§f니켈", Material.RED_DYE, 8)
    val TITANIUM         = item("§f티타늄", Material.RED_DYE, 9)

    // ═══════════════════════════════════════════
    //  광물 - 파우더
    // ═══════════════════════════════════════════
    val MAGNESIUM_POWDER = item("§f마그네슘 파우더", Material.RED_DYE, 10)
    val ALUMINUM_POWDER  = item("§f알루미늄 파우더", Material.RED_DYE, 11)
    val IRON_POWDER      = item("§f철 파우더", Material.RED_DYE, 12)
    val COPPER_POWDER    = item("§f구리 파우더", Material.RED_DYE, 13)
    val LITHIUM_POWDER   = item("§f리튬 파우더", Material.RED_DYE, 14)
    val GOLD_POWDER      = item("§f금 파우더", Material.RED_DYE, 15)
    val PLATINUM_POWDER  = item("§f백금 파우더", Material.RED_DYE, 16)
    val NICKEL_POWDER    = item("§f니켈 파우더", Material.RED_DYE, 17)
    val TITANIUM_POWDER  = item("§f티타늄 파우더", Material.RED_DYE, 18)

    // ═══════════════════════════════════════════
    //  광물 - 주괴
    // ═══════════════════════════════════════════
    val MAGNESIUM_INGOT  = item("§f마그네슘 주괴", Material.RED_DYE, 19)
    val ALUMINUM_INGOT   = item("§f알루미늄 주괴", Material.RED_DYE, 20)
    val IRON_INGOT       = item("§f철 주괴", Material.RED_DYE, 21)
    val COPPER_INGOT     = item("§f구리 주괴", Material.RED_DYE, 22)
    val LITHIUM_INGOT    = item("§f리튬 주괴", Material.RED_DYE, 23)
    val GOLD_INGOT       = item("§f금 주괴", Material.RED_DYE, 24)
    val PLATINUM_INGOT   = item("§f백금 주괴", Material.RED_DYE, 25)
    val NICKEL_INGOT     = item("§f니켈 주괴", Material.RED_DYE, 26)
    val TITANIUM_INGOT   = item("§f티타늄 주괴", Material.RED_DYE, 27)

    // ═══════════════════════════════════════════
    //  합금 주괴
    // ═══════════════════════════════════════════
    val AL_CU_ALLOY_INGOT     = item("§f합금 주괴", Material.RED_DYE, 28, "§fAl-Cu 합금 주괴")
    val AL_MG_ALLOY_INGOT     = item("§f합금 주괴", Material.RED_DYE, 29, "§fAl-Mg 합금 주괴")
    val AL_LI_ALLOY_INGOT     = item("§f합금 주괴", Material.RED_DYE, 30, "§fAl-Li 합금 주괴")
    val CU_AU_ALLOY_INGOT     = item("§f합금 주괴", Material.RED_DYE, 31, "§fCu-Au 합금 주괴")
    val NI_FE_ALLOY_INGOT     = item("§f합금 주괴", Material.RED_DYE, 32, "§fNi-Fe 합금 주괴")
    val TI_PT_AU_ALLOY_INGOT  = item("§f합금 주괴", Material.RED_DYE, 33, "§fTi-Pt-Au 합금 주괴")

    // ═══════════════════════════════════════════
    //  작물 - 씨앗
    // ═══════════════════════════════════════════
    val POTATO_SEED    = item("§f감자 씨앗", Material.BLACK_DYE, 31, "§f봄 작물, §f수확일 3일")
    val CABBAGE_SEED   = item("§f양배추 씨앗", Material.BLACK_DYE, 32, "§f봄 작물, §f수확일 7일")
    val CUCUMBER_SEED  = item("§f오이 씨앗", Material.BLACK_DYE, 33, "§f봄 작물, §f수확일 5일")
    val COFFEE_BEAN_SEED = item("§f커피콩", Material.BLACK_DYE, 34, "§f봄/여름 작물, §f수확일 10일")
    val TOMATO_SEED    = item("§f토마토 씨앗", Material.BLACK_DYE, 35, "§f여름 작물, §f수확일 3일")
    val CORN_SEED      = item("§f옥수수 씨앗", Material.BLACK_DYE, 36, "§f여름 작물, §f수확일 5일")
    val WHEAT_SEED     = item("§f밀 씨앗", Material.BLACK_DYE, 37, "§f여름/가을 작물, §f수확일 9일")
    val CRANBERRY_SEED = item("§f크랜베리 씨앗", Material.BLACK_DYE, 38, "§f가을 작물, §f수확일 3일")
    val BEET_SEED      = item("§f비트 씨앗", Material.BLACK_DYE, 39, "§f가을 작물, §f수확일 3일")
    val PUMPKIN_SEED   = item("§f호박 씨앗", Material.BLACK_DYE, 40, "§f가을 작물, §f수확일 8일")

    // ═══════════════════════════════════════════
    //  작물 - 수확물 (은 등급 ★)
    // ═══════════════════════════════════════════
    val POTATO_G1    = item("§f감자", Material.BLACK_DYE, 1, "§f봄 작물, §f수확일 3일", eatable = true)
    val CABBAGE_G1   = item("§f양배추", Material.BLACK_DYE, 2, "§f봄 작물, §f수확일 7일", eatable = true)
    val CUCUMBER_G1  = item("§f오이", Material.BLACK_DYE, 3, "§f봄 작물, §f수확일 5일", eatable = true)
    val COFFEE_BEAN_G1 = item("§f커피콩", Material.BLACK_DYE, 4, "§f봄/여름 작물, §f수확일 10일", eatable = true)
    val TOMATO_G1    = item("§f토마토", Material.BLACK_DYE, 5, "§f여름 작물, §f수확일 3일", eatable = true)
    val CORN_G1      = item("§f옥수수", Material.BLACK_DYE, 6, "§f여름 작물, §f수확일 5일", eatable = true)
    val WHEAT_G1     = item("§f밀", Material.BLACK_DYE, 7, "§f여름/가을 작물, §f수확일 9일", eatable = true)
    val CRANBERRY_G1 = item("§f크랜베리", Material.BLACK_DYE, 8, "§f가을 작물, §f수확일 3일", eatable = true)
    val BEET_G1      = item("§f비트", Material.BLACK_DYE, 9, "§f가을 작물, §f수확일 3일", eatable = true)
    val PUMPKIN_G1   = item("§f호박", Material.BLACK_DYE, 10, "§f가을 작물, §f수확일 8일", eatable = true)

    // ═══════════════════════════════════════════
    //  작물 - 수확물 (금 등급 ★★)
    // ═══════════════════════════════════════════
    val POTATO_G2    = item("§f감자", Material.BLACK_DYE, 11, "§f봄 작물, §f수확일 3일", eatable = true)
    val CABBAGE_G2   = item("§f양배추", Material.BLACK_DYE, 12, "§f봄 작물, §f수확일 7일", eatable = true)
    val CUCUMBER_G2  = item("§f오이", Material.BLACK_DYE, 13, "§f봄 작물, §f수확일 5일", eatable = true)
    val COFFEE_BEAN_G2 = item("§f커피콩", Material.BLACK_DYE, 14, "§f봄/여름 작물, §f수확일 10일", eatable = true)
    val TOMATO_G2    = item("§f토마토", Material.BLACK_DYE, 15, "§f여름 작물, §f수확일 3일", eatable = true)
    val CORN_G2      = item("§f옥수수", Material.BLACK_DYE, 16, "§f여름 작물, §f수확일 5일", eatable = true)
    val WHEAT_G2     = item("§f밀", Material.BLACK_DYE, 17, "§f여름/가을 작물, §f수확일 9일", eatable = true)
    val CRANBERRY_G2 = item("§f크랜베리", Material.BLACK_DYE, 18, "§f가을 작물, §f수확일 3일", eatable = true)
    val BEET_G2      = item("§f비트", Material.BLACK_DYE, 19, "§f가을 작물, §f수확일 3일", eatable = true)
    val PUMPKIN_G2   = item("§f호박", Material.BLACK_DYE, 20, "§f가을 작물, §f수확일 8일", eatable = true)

    // ═══════════════════════════════════════════
    //  작물 - 수확물 (다이아 등급 ★★★)
    // ═══════════════════════════════════════════
    val POTATO_G3    = item("§f감자", Material.BLACK_DYE, 21, "§f봄 작물, §f수확일 3일", eatable = true)
    val CABBAGE_G3   = item("§f양배추", Material.BLACK_DYE, 22, "§f봄 작물, §f수확일 7일", eatable = true)
    val CUCUMBER_G3  = item("§f오이", Material.BLACK_DYE, 23, "§f봄 작물, §f수확일 5일", eatable = true)
    val COFFEE_BEAN_G3 = item("§f커피콩", Material.BLACK_DYE, 24, "§f봄/여름 작물, §f수확일 10일", eatable = true)
    val TOMATO_G3    = item("§f토마토", Material.BLACK_DYE, 25, "§f여름 작물, §f수확일 3일", eatable = true)
    val CORN_G3      = item("§f옥수수", Material.BLACK_DYE, 26, "§f여름 작물, §f수확일 5일", eatable = true)
    val WHEAT_G3     = item("§f밀", Material.BLACK_DYE, 27, "§f여름/가을 작물, §f수확일 9일", eatable = true)
    val CRANBERRY_G3 = item("§f크랜베리", Material.BLACK_DYE, 28, "§f가을 작물, §f수확일 3일", eatable = true)
    val BEET_G3      = item("§f비트", Material.BLACK_DYE, 29, "§f가을 작물, §f수확일 3일", eatable = true)
    val PUMPKIN_G3   = item("§f호박", Material.BLACK_DYE, 30, "§f가을 작물, §f수확일 8일", eatable = true)

    // ═══════════════════════════════════════════
    //  기타 - 캡슐 / 도구
    // ═══════════════════════════════════════════
    val NUTRITION_CAPSULE  = item("§f영양 캡슐", Material.ORANGE_DYE, 2, "§f더 높은 등급의 작물이 나올 확률이 증가합니다.")
    val GROWTH_CAPSULE     = item("§f성장 캡슐", Material.ORANGE_DYE, 3, "§f작물의 수확일을 줄여줍니다.")
    val HERBICIDE_CAPSULE  = item("§f제초 캡슐", Material.ORANGE_DYE, 4, "§f잡초를 제거합니다.")
    val MUG                = item("§f머그잔", Material.BOWL, 1, "§fJava")
    val SPANNER            = item("§f스패너", Material.WOODEN_SHOVEL, 13)

    // ═══════════════════════════════════════════
    //  기타 - 빌드형 모듈
    // ═══════════════════════════════════════════
    val BASIC_TOOL_MODULE      = item("§f기본 연장 모듈", Material.SADDLE, 1)
    val T_TOOL_MODULE          = item("§fT 연장 모듈", Material.SADDLE, 2)
    val CROSS_TOOL_MODULE      = item("§f십자 연장 모듈", Material.SADDLE, 3)
    val STORAGE_MODULE_MEDIUM  = item("§f스토리지 모듈 [중]", Material.SADDLE, 4)
    val STORAGE_MODULE_LARGE   = item("§f스토리지 모듈 [대]", Material.SADDLE, 5)
    val MINE_MODULE            = item("§f광산 모듈", Material.SADDLE, 6)
    val FARM_MODULE_MEDIUM     = item("§f농사 모듈 [중]", Material.SADDLE, 7)
    val FARM_MODULE_LARGE      = item("§f농사 모듈 [대]", Material.SADDLE, 8)
    val GATE_MODULE            = item("§f출입 모듈", Material.SADDLE, 9)

    // ═══════════════════════════════════════════
    //  기타 - 설치형 모듈
    // ═══════════════════════════════════════════
    val GRINDER_MODULE         = item("§f분쇄기 모듈", Material.IRON_HORSE_ARMOR, 2)
    val PRINTER_MODULE         = item("§f프린트 모듈", Material.IRON_HORSE_ARMOR, 3)
    val COFFEE_MACHINE_MODULE  = item("§f커피머신 모듈", Material.IRON_HORSE_ARMOR, 4)
    val FURNACE_MODULE         = item("§f용광로 모듈", Material.IRON_HORSE_ARMOR, 7)

    // ═══════════════════════════════════════════
    //  기타 - 업그레이드 / 부품
    // ═══════════════════════════════════════════
    val FURNACE_SLOT_UPGRADE         = item("§f용광로 용해 슬롯 확장", Material.SADDLE, 17)
    val ALLOY_COOLING_MOLD_CU_AU     = item("§f합금 냉각 몰드", Material.SADDLE, 18, "§fCu-Au")
    val ALLOY_TORCH_NI_FE            = item("§f합금 토치", Material.SADDLE, 19, "§fNi-Fe")
    val GRINDER_CAPACITY_UPGRADE     = item("§f분쇄기 용량 확장", Material.SADDLE, 20)
    val ALLOY_GEAR_AL_CU             = item("§f합금 기어", Material.SADDLE, 21, "§fAl-Cu")
    val ALLOY_DRILL_TI_PT_AU         = item("§f합금 드릴", Material.SADDLE, 22, "§fTi-Pt-Au")

    // ═══════════════════════════════════════════
    //  기타 - 레시피
    // ═══════════════════════════════════════════
    val RECIPE_MAGNESIUM_INGOT  = item("§f마그네슘 주괴 레시피", Material.SADDLE, 23)
    val RECIPE_ALUMINUM_INGOT   = item("§f알루미늄 주괴 레시피", Material.SADDLE, 24)
    val RECIPE_IRON_INGOT       = item("§f철 주괴 레시피", Material.SADDLE, 25)
    val RECIPE_COPPER_INGOT     = item("§f구리 주괴 레시피", Material.SADDLE, 26)
    val RECIPE_LITHIUM_INGOT    = item("§f리튬 주괴 레시피", Material.SADDLE, 27)
    val RECIPE_GOLD_INGOT       = item("§f금 주괴 레시피", Material.SADDLE, 28)
    val RECIPE_PLATINUM_INGOT   = item("§f백금 주괴 레시피", Material.SADDLE, 29)
    val RECIPE_NICKEL_INGOT     = item("§f니켈 주괴 레시피", Material.SADDLE, 30)
    val RECIPE_TITANIUM_INGOT   = item("§f티타늄 주괴 레시피", Material.SADDLE, 31)
    val RECIPE_AL_CU_ALLOY      = item("§fAl-Cu 합금 레시피", Material.SADDLE, 32, "§f알루미늄 + 구리")
    val RECIPE_AL_MG_ALLOY      = item("§fAl-Mg 합금 레시피", Material.SADDLE, 33, "§f알루미늄 + 마그네슘")
    val RECIPE_AL_LI_ALLOY      = item("§fAl-Li 합금 레시피", Material.SADDLE, 34, "§f알루미늄 + 리튬")
    val RECIPE_CU_AU_ALLOY      = item("§fCu-Au 합금 레시피", Material.SADDLE, 35, "§f구리 + 금")
    val RECIPE_NI_FE_ALLOY      = item("§fNi-Fe 합금 레시피", Material.SADDLE, 36, "§f니켈 + 철")
    val RECIPE_TI_PT_AU_ALLOY   = item("§fTi-Pt-Au 합금 레시피", Material.SADDLE, 37, "§f티타늄 + 백금 + 금")

    // ═══════════════════════════════════════════
    //  유틸리티 메서드
    // ═══════════════════════════════════════════

    /**
     * ItemStack에서 매칭되는 PRCItem을 역추적합니다.
     */
    fun getItem(item: org.bukkit.inventory.ItemStack?): PRCItem? {
        if (item == null) return null
        val meta = item.itemMeta ?: return null
        if (!meta.hasCustomModelData()) return null
        return _registry.find { it.material == item.type && it.customModelData == meta.customModelData }
    }
}
