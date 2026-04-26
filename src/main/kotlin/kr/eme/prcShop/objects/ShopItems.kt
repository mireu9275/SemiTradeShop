package kr.eme.prcShop.objects

import kr.eme.prcShop.api.PRCItems

/**
 * 상점별 아이템 등록 레지스트리.
 * 각 상점은 nested object로 분리되어 있고, 아이템은 val로 선언됩니다.
 */
object ShopItems {

    // ═════════════════════════════════════════════
    //  광물 상점
    // ═════════════════════════════════════════════
    object Mineral {
        private val _items = mutableListOf<ShopItem>()
        private fun s(prcItem: kr.eme.prcShop.api.PRCItem, buy: Int, sell: Int, page: Int, slot: Int,
                      trade: List<TradeRequirement> = emptyList()) =
            ShopItem(prcItem, buy, sell, page, slot, trade).also { _items.add(it) }

        // Page 1 - 원석
        val MAGNESIUM        = s(PRCItems.MAGNESIUM, buy = 100, sell = 20, page = 1, slot = 9)
        val ALUMINUM         = s(PRCItems.ALUMINUM, buy = 110, sell = 30, page = 1, slot = 10)
        val IRON             = s(PRCItems.IRON, buy = 150, sell = 60, page = 1, slot = 11)
        val COPPER           = s(PRCItems.COPPER, buy = 200, sell = 70, page = 1, slot = 12)
        val LITHIUM          = s(PRCItems.LITHIUM, buy = 250, sell = 150, page = 1, slot = 13)
        val GOLD             = s(PRCItems.GOLD, buy = 600, sell = 250, page = 1, slot = 18)
        val PLATINUM         = s(PRCItems.PLATINUM, buy = 1000, sell = 300, page = 1, slot = 19)
        val NICKEL           = s(PRCItems.NICKEL, buy = 1000, sell = 300, page = 1, slot = 20)
        val TITANIUM         = s(PRCItems.TITANIUM, buy = 1300, sell = 450, page = 1, slot = 21)

        // Page 2 - 파우더
        val MAGNESIUM_POWDER = s(PRCItems.MAGNESIUM_POWDER, buy = -1, sell = 22, page = 2, slot = 9)
        val ALUMINUM_POWDER  = s(PRCItems.ALUMINUM_POWDER, buy = -1, sell = 33, page = 2, slot = 10)
        val IRON_POWDER      = s(PRCItems.IRON_POWDER, buy = -1, sell = 66, page = 2, slot = 11)
        val COPPER_POWDER    = s(PRCItems.COPPER_POWDER, buy = -1, sell = 77, page = 2, slot = 12)
        val LITHIUM_POWDER   = s(PRCItems.LITHIUM_POWDER, buy = -1, sell = 165, page = 2, slot = 13)
        val GOLD_POWDER      = s(PRCItems.GOLD_POWDER, buy = -1, sell = 275, page = 2, slot = 18)
        val PLATINUM_POWDER  = s(PRCItems.PLATINUM_POWDER, buy = -1, sell = 330, page = 2, slot = 19)
        val NICKEL_POWDER    = s(PRCItems.NICKEL_POWDER, buy = -1, sell = 330, page = 2, slot = 20)
        val TITANIUM_POWDER  = s(PRCItems.TITANIUM_POWDER, buy = -1, sell = 495, page = 2, slot = 21)

        // Page 3 - 주괴 & 합금
        val MAGNESIUM_INGOT      = s(PRCItems.MAGNESIUM_INGOT, buy = 500, sell = 120, page = 3, slot = 9)
        val ALUMINUM_INGOT       = s(PRCItems.ALUMINUM_INGOT, buy = 550, sell = 170, page = 3, slot = 10)
        val IRON_INGOT           = s(PRCItems.IRON_INGOT, buy = 750, sell = 300, page = 3, slot = 11)
        val COPPER_INGOT         = s(PRCItems.COPPER_INGOT, buy = 1000, sell = 350, page = 3, slot = 12)
        val LITHIUM_INGOT        = s(PRCItems.LITHIUM_INGOT, buy = 1200, sell = 750, page = 3, slot = 13)
        val GOLD_INGOT           = s(PRCItems.GOLD_INGOT, buy = -1, sell = 1250, page = 3, slot = 18)
        val PLATINUM_INGOT       = s(PRCItems.PLATINUM_INGOT, buy = -1, sell = 1500, page = 3, slot = 19)
        val NICKEL_INGOT         = s(PRCItems.NICKEL_INGOT, buy = -1, sell = 1500, page = 3, slot = 20)
        val TITANIUM_INGOT       = s(PRCItems.TITANIUM_INGOT, buy = -1, sell = 2250, page = 3, slot = 21)
        val AL_MG_ALLOY_INGOT    = s(PRCItems.AL_MG_ALLOY_INGOT, buy = -1, sell = 435, page = 3, slot = 22)
        val AL_CU_ALLOY_INGOT    = s(PRCItems.AL_CU_ALLOY_INGOT, buy = -1, sell = 780, page = 3, slot = 27)
        val AL_LI_ALLOY_INGOT    = s(PRCItems.AL_LI_ALLOY_INGOT, buy = -1, sell = 1380, page = 3, slot = 28)
        val CU_AU_ALLOY_INGOT    = s(PRCItems.CU_AU_ALLOY_INGOT, buy = -1, sell = 2400, page = 3, slot = 29)
        val NI_FE_ALLOY_INGOT    = s(PRCItems.NI_FE_ALLOY_INGOT, buy = -1, sell = 2700, page = 3, slot = 30)
        val TI_PT_AU_ALLOY_INGOT = s(PRCItems.TI_PT_AU_ALLOY_INGOT, buy = -1, sell = 10000, page = 3, slot = 31)

        val all: List<ShopItem> get() = _items.toList()
    }

    // ═════════════════════════════════════════════
    //  작물 상점
    // ═════════════════════════════════════════════
    object Farming {
        private val _items = mutableListOf<ShopItem>()
        private fun s(prcItem: kr.eme.prcShop.api.PRCItem, buy: Int, sell: Int, page: Int, slot: Int) =
            ShopItem(prcItem, buy, sell, page, slot).also { _items.add(it) }

        // Page 1 - 씨앗
        val POTATO_SEED    = s(PRCItems.POTATO_SEED, buy = 20, sell = 4, page = 1, slot = 9)
        val CABBAGE_SEED   = s(PRCItems.CABBAGE_SEED, buy = 100, sell = 20, page = 1, slot = 10)
        val CUCUMBER_SEED  = s(PRCItems.CUCUMBER_SEED, buy = 50, sell = 10, page = 1, slot = 11)
        val COFFEE_BEAN_SEED = s(PRCItems.COFFEE_BEAN_SEED, buy = 100, sell = 20, page = 1, slot = 12)
        val TOMATO_SEED    = s(PRCItems.TOMATO_SEED, buy = 30, sell = 6, page = 1, slot = 13)
        val CORN_SEED      = s(PRCItems.CORN_SEED, buy = 60, sell = 12, page = 1, slot = 18)
        val WHEAT_SEED     = s(PRCItems.WHEAT_SEED, buy = 120, sell = 24, page = 1, slot = 19)
        val CRANBERRY_SEED = s(PRCItems.CRANBERRY_SEED, buy = 20, sell = 4, page = 1, slot = 20)
        val BEET_SEED      = s(PRCItems.BEET_SEED, buy = 50, sell = 10, page = 1, slot = 21)
        val PUMPKIN_SEED   = s(PRCItems.PUMPKIN_SEED, buy = 120, sell = 24, page = 1, slot = 22)

        // Page 2 - 은 등급 ★ (기존 가격 * 1.5)
        val POTATO_G1    = s(PRCItems.POTATO_G1, buy = -1, sell = (10 * 9 + 1) / 2, page = 2, slot = 9)
        val CABBAGE_G1   = s(PRCItems.CABBAGE_G1, buy = -1, sell = (150 * 9 + 1) / 2, page = 2, slot = 10)
        val CUCUMBER_G1  = s(PRCItems.CUCUMBER_G1, buy = -1, sell = (25 * 9 + 1) / 2, page = 2, slot = 11)
        val COFFEE_BEAN_G1 = s(PRCItems.COFFEE_BEAN_G1, buy = -1, sell = (10 * 9 + 1) / 2, page = 2, slot = 12)
        val TOMATO_G1    = s(PRCItems.TOMATO_G1, buy = -1, sell = (15 * 9 + 1) / 2, page = 2, slot = 13)
        val CORN_G1      = s(PRCItems.CORN_G1, buy = -1, sell = (30 * 9 + 1) / 2, page = 2, slot = 18)
        val WHEAT_G1     = s(PRCItems.WHEAT_G1, buy = -1, sell = (60 * 9 + 1) / 2, page = 2, slot = 19)
        val CRANBERRY_G1 = s(PRCItems.CRANBERRY_G1, buy = -1, sell = (10 * 9 + 1) / 2, page = 2, slot = 20)
        val BEET_G1      = s(PRCItems.BEET_G1, buy = -1, sell = (100 * 9 + 1) / 2, page = 2, slot = 21)
        val PUMPKIN_G1   = s(PRCItems.PUMPKIN_G1, buy = -1, sell = (200 * 9 + 1) / 2, page = 2, slot = 22)

        // Page 3 - 금 등급 ★★ (기존 가격 * 1.5)
        val POTATO_G2    = s(PRCItems.POTATO_G2, buy = -1, sell = (11 * 9 + 1) / 2, page = 3, slot = 9)
        val CABBAGE_G2   = s(PRCItems.CABBAGE_G2, buy = -1, sell = (165 * 9 + 1) / 2, page = 3, slot = 10)
        val CUCUMBER_G2  = s(PRCItems.CUCUMBER_G2, buy = -1, sell = (28 * 9 + 1) / 2, page = 3, slot = 11)
        val COFFEE_BEAN_G2 = s(PRCItems.COFFEE_BEAN_G2, buy = -1, sell = (11 * 9 + 1) / 2, page = 3, slot = 12)
        val TOMATO_G2    = s(PRCItems.TOMATO_G2, buy = -1, sell = (17 * 9 + 1) / 2, page = 3, slot = 13)
        val CORN_G2      = s(PRCItems.CORN_G2, buy = -1, sell = (33 * 9 + 1) / 2, page = 3, slot = 18)
        val WHEAT_G2     = s(PRCItems.WHEAT_G2, buy = -1, sell = (66 * 9 + 1) / 2, page = 3, slot = 19)
        val CRANBERRY_G2 = s(PRCItems.CRANBERRY_G2, buy = -1, sell = (11 * 9 + 1) / 2, page = 3, slot = 20)
        val BEET_G2      = s(PRCItems.BEET_G2, buy = -1, sell = (110 * 9 + 1) / 2, page = 3, slot = 21)
        val PUMPKIN_G2   = s(PRCItems.PUMPKIN_G2, buy = -1, sell = (220 * 9 + 1) / 2, page = 3, slot = 22)

        // Page 4 - 다이아 등급 ★★★ (기존 가격 * 1.5)
        val POTATO_G3    = s(PRCItems.POTATO_G3, buy = -1, sell = (13 * 9 + 1) / 2, page = 4, slot = 9)
        val CABBAGE_G3   = s(PRCItems.CABBAGE_G3, buy = -1, sell = (195 * 9 + 1) / 2, page = 4, slot = 10)
        val CUCUMBER_G3  = s(PRCItems.CUCUMBER_G3, buy = -1, sell = (30 * 9 + 1) / 2, page = 4, slot = 11)
        val COFFEE_BEAN_G3 = s(PRCItems.COFFEE_BEAN_G3, buy = -1, sell = (13 * 9 + 1) / 2, page = 4, slot = 12)
        val TOMATO_G3    = s(PRCItems.TOMATO_G3, buy = -1, sell = (18 * 9 + 1) / 2, page = 4, slot = 13)
        val CORN_G3      = s(PRCItems.CORN_G3, buy = -1, sell = (39 * 9 + 1) / 2, page = 4, slot = 18)
        val WHEAT_G3     = s(PRCItems.WHEAT_G3, buy = -1, sell = (78 * 9 + 1) / 2, page = 4, slot = 19)
        val CRANBERRY_G3 = s(PRCItems.CRANBERRY_G3, buy = -1, sell = (13 * 9 + 1) / 2, page = 4, slot = 20)
        val BEET_G3      = s(PRCItems.BEET_G3, buy = -1, sell = (130 * 9 + 1) / 2, page = 4, slot = 21)
        val PUMPKIN_G3   = s(PRCItems.PUMPKIN_G3, buy = -1, sell = (260 * 9 + 1) / 2, page = 4, slot = 22)

        val all: List<ShopItem> get() = _items.toList()
    }

    // ═════════════════════════════════════════════
    //  기타 상점
    // ═════════════════════════════════════════════
    object Other {
        private val _items = mutableListOf<ShopItem>()
        private fun s(prcItem: kr.eme.prcShop.api.PRCItem, buy: Int, sell: Int, page: Int, slot: Int,
                      trade: List<TradeRequirement> = emptyList(), label: String? = null) =
            ShopItem(prcItem, buy, sell, page, slot, trade).copy(label = label).also { _items.add(it) }

        // Page 1 - 도구
        val NUTRITION_CAPSULE  = s(PRCItems.NUTRITION_CAPSULE, buy = 10 * 3, sell = 5, page = 1, slot = 9)
        val GROWTH_CAPSULE     = s(PRCItems.GROWTH_CAPSULE, buy = 7 * 3, sell = 5, page = 1, slot = 10)
        val HERBICIDE_CAPSULE  = s(PRCItems.HERBICIDE_CAPSULE, buy = 50 * 3, sell = 25, page = 1, slot = 11)
        val MUG                = s(PRCItems.MUG, buy = 5, sell = -1, page = 1, slot = 12)
        val FOOD_CAPSULE       = s(PRCItems.FOOD_CAPSULE, buy = 35, sell = -1, page = 1, slot = 13)

        val CHAINMAIL_HELMET     = s(PRCItems.CHAINMAIL_HELMET, buy = 50, sell = -1, page = 1, slot = 18, label = "폴리머 우주복")
        val CHAINMAIL_CHESTPLATE = s(PRCItems.CHAINMAIL_CHESTPLATE, buy = 50, sell = -1, page = 1, slot = 19, label = "폴리머 우주복")
        val CHAINMAIL_LEGGINGS   = s(PRCItems.CHAINMAIL_LEGGINGS, buy = 50, sell = -1, page = 1, slot = 20, label = "폴리머 우주복")
        val CHAINMAIL_BOOTS      = s(PRCItems.CHAINMAIL_BOOTS, buy = 50, sell = -1, page = 1, slot = 21, label = "폴리머 우주복")

        val ROTTEN_FLESH    = s(PRCItems.ROTTEN_FLESH, buy = -1, sell = 1, page = 1, slot = 22, label = "알 수 없는 살점")

        val IRON_HELMET     = s(PRCItems.IRON_HELMET, buy = 100, sell = -1, page = 1, slot = 27, label = "합금 섬유 우주복")
        val IRON_CHESTPLATE = s(PRCItems.IRON_CHESTPLATE, buy = 100, sell = -1, page = 1, slot = 28, label = "합금 섬유 우주복")
        val IRON_LEGGINGS   = s(PRCItems.IRON_LEGGINGS, buy = 100, sell = -1, page = 1, slot = 29, label = "합금 섬유 우주복")
        val IRON_BOOTS      = s(PRCItems.IRON_BOOTS, buy = 220, sell = -1, page = 1, slot = 30, label = "합금 섬유 우주복")


        // Page 2 - 빌드형 모듈
        val BASIC_TOOL_MODULE     = s(PRCItems.BASIC_TOOL_MODULE, buy = -1, sell = -1, page = 2, slot = 9,
            trade = listOf(TradeRequirement(PRCItems.IRON_INGOT, 5)))
        val T_TOOL_MODULE         = s(PRCItems.T_TOOL_MODULE, buy = -1, sell = -1, page = 2, slot = 10,
            trade = listOf(TradeRequirement(PRCItems.IRON_INGOT, 5)))
        val CROSS_TOOL_MODULE     = s(PRCItems.CROSS_TOOL_MODULE, buy = -1, sell = -1, page = 2, slot = 11,
            trade = listOf(TradeRequirement(PRCItems.IRON_INGOT, 5)))
        val STORAGE_MODULE_MEDIUM = s(PRCItems.STORAGE_MODULE_MEDIUM, buy = -1, sell = -1, page = 2, slot = 12,
            trade = listOf(TradeRequirement(PRCItems.IRON_INGOT, 35)))
        val STORAGE_MODULE_LARGE  = s(PRCItems.STORAGE_MODULE_LARGE, buy = -1, sell = -1, page = 2, slot = 13,
            trade = listOf(TradeRequirement(PRCItems.IRON_INGOT, 50)))
        val MINE_MODULE           = s(PRCItems.MINE_MODULE, buy = -1, sell = -1, page = 2, slot = 18,
            trade = listOf(TradeRequirement(PRCItems.IRON_INGOT, 10)))
        val FARM_MODULE_MEDIUM    = s(PRCItems.FARM_MODULE_MEDIUM, buy = -1, sell = -1, page = 2, slot = 19,
            trade = listOf(
                TradeRequirement(PRCItems.IRON_INGOT, 20),
                TradeRequirement(PRCItems.LITHIUM_INGOT, 10)))
        val FARM_MODULE_LARGE     = s(PRCItems.FARM_MODULE_LARGE, buy = -1, sell = -1, page = 2, slot = 20,
            trade = listOf(
                TradeRequirement(PRCItems.IRON_INGOT, 30),
                TradeRequirement(PRCItems.LITHIUM_INGOT, 10),
                TradeRequirement(PRCItems.TITANIUM_INGOT, 5)))
        val GATE_MODULE           = s(PRCItems.GATE_MODULE, buy = -1, sell = -1, page = 2, slot = 21,
            trade = listOf(TradeRequirement(PRCItems.IRON_INGOT, 40)))

        // Page 3 - 설치형 모듈 & 부품
        val SPANNER              = s(PRCItems.SPANNER, buy = 130, sell = -1, page = 3, slot = 9)
        val GRINDER_MODULE       = s(PRCItems.GRINDER_MODULE, buy = 500, sell = -1, page = 3, slot = 10)
        val PRINTER_MODULE       = s(PRCItems.PRINTER_MODULE, buy = 500, sell = -1, page = 3, slot = 11)
        val COFFEE_MACHINE_MODULE = s(PRCItems.COFFEE_MACHINE_MODULE, buy = 2000, sell = -1, page = 3, slot = 12)
        val FURNACE_MODULE       = s(PRCItems.FURNACE_MODULE, buy = 1300, sell = -1, page = 3, slot = 13)
        val GRINDER_CAPACITY     = s(PRCItems.GRINDER_CAPACITY_UPGRADE, buy = -1, sell = -1, page = 3, slot = 18,
            trade = listOf(
                TradeRequirement(PRCItems.IRON_INGOT, 15),
                TradeRequirement(PRCItems.COPPER_INGOT, 10)))
        val ALLOY_GEAR           = s(PRCItems.ALLOY_GEAR_AL_CU, buy = -1, sell = -1, page = 3, slot = 19,
            trade = listOf(
                TradeRequirement(PRCItems.AL_CU_ALLOY_INGOT, 5),
                TradeRequirement(PRCItems.LITHIUM_INGOT, 7)))
        val ALLOY_DRILL          = s(PRCItems.ALLOY_DRILL_TI_PT_AU, buy = -1, sell = -1, page = 3, slot = 20,
            trade = listOf(
                TradeRequirement(PRCItems.TI_PT_AU_ALLOY_INGOT, 15),
                TradeRequirement(PRCItems.PLATINUM_INGOT, 5),
                TradeRequirement(PRCItems.NICKEL_INGOT, 5)))
        val FURNACE_SLOT         = s(PRCItems.FURNACE_SLOT_UPGRADE, buy = -1, sell = -1, page = 3, slot = 21,
            trade = listOf(
                TradeRequirement(PRCItems.IRON_INGOT, 10),
                TradeRequirement(PRCItems.COPPER_INGOT, 10)))
        val ALLOY_MOLD           = s(PRCItems.ALLOY_COOLING_MOLD_CU_AU, buy = -1, sell = -1, page = 3, slot = 22,
            trade = listOf(
                TradeRequirement(PRCItems.CU_AU_ALLOY_INGOT, 10),
                TradeRequirement(PRCItems.PLATINUM_INGOT, 5)))
        val ALLOY_TORCH          = s(PRCItems.ALLOY_TORCH_NI_FE, buy = -1, sell = -1, page = 3, slot = 27,
            trade = listOf(
                TradeRequirement(PRCItems.NI_FE_ALLOY_INGOT, 10),
                TradeRequirement(PRCItems.NICKEL_INGOT, 10),
                TradeRequirement(PRCItems.TITANIUM_INGOT, 5)))

        // Page 4 - 레시피
        val RECIPE_MAGNESIUM = s(PRCItems.RECIPE_MAGNESIUM_INGOT, buy = 100, sell = -1, page = 4, slot = 9)
        val RECIPE_ALUMINUM  = s(PRCItems.RECIPE_ALUMINUM_INGOT, buy = 100, sell = -1, page = 4, slot = 10)
        val RECIPE_IRON      = s(PRCItems.RECIPE_IRON_INGOT, buy = 100, sell = -1, page = 4, slot = 11)
        val RECIPE_COPPER    = s(PRCItems.RECIPE_COPPER_INGOT, buy = 300, sell = -1, page = 4, slot = 12)
        val RECIPE_LITHIUM   = s(PRCItems.RECIPE_LITHIUM_INGOT, buy = 300, sell = -1, page = 4, slot = 13)
        val RECIPE_GOLD      = s(PRCItems.RECIPE_GOLD_INGOT, buy = 300, sell = -1, page = 4, slot = 18)
        val RECIPE_PLATINUM  = s(PRCItems.RECIPE_PLATINUM_INGOT, buy = 500, sell = -1, page = 4, slot = 19)
        val RECIPE_NICKEL    = s(PRCItems.RECIPE_NICKEL_INGOT, buy = 500, sell = -1, page = 4, slot = 20)
        val RECIPE_TITANIUM  = s(PRCItems.RECIPE_TITANIUM_INGOT, buy = 500, sell = -1, page = 4, slot = 21)
        val RECIPE_AL_MG     = s(PRCItems.RECIPE_AL_MG_ALLOY, buy = 400, sell = -1, page = 4, slot = 22)
        val RECIPE_AL_CU     = s(PRCItems.RECIPE_AL_CU_ALLOY, buy = 400, sell = -1, page = 4, slot = 27)
        val RECIPE_AL_LI     = s(PRCItems.RECIPE_AL_LI_ALLOY, buy = 400, sell = -1, page = 4, slot = 28)
        val RECIPE_CU_AU     = s(PRCItems.RECIPE_CU_AU_ALLOY, buy = 700, sell = -1, page = 4, slot = 29)
        val RECIPE_NI_FE     = s(PRCItems.RECIPE_NI_FE_ALLOY, buy = 700, sell = -1, page = 4, slot = 30)
        val RECIPE_TI_PT_AU  = s(PRCItems.RECIPE_TI_PT_AU_ALLOY, buy = 1500, sell = -1, page = 4, slot = 31)

        val all: List<ShopItem> get() = _items.toList()
    }

    // ═════════════════════════════════════════════
    //  구매/판매 GUI (내부 전용)
    // ═════════════════════════════════════════════
    private object BuyGUI {
        private val _items = mutableListOf<ShopItem>()
        private fun g(name: String, slot: Int, cmd: Int) =
            ShopItem(name, 0, 0, "", org.bukkit.Material.BROWN_DYE, 1, slot, cmd).also { _items.add(it) }

        val SUB_32   = g("§f32개 빼기", 19, 16)
        val SUB_5    = g("§f5개 빼기", 20, 15)
        val SUB_1    = g("§f1개 빼기", 21, 14)
        val ADD_1    = g("§f1개 추가", 23, 17)
        val ADD_5    = g("§f5개 추가", 24, 18)
        val ADD_32   = g("§f32개 추가", 25, 19)
        val QTY_1    = g("§f현재 선택 수량: 1 개", 36, 10)
        val QTY_2    = g("§f현재 선택 수량: 1 개", 37, 11)
        val PRICE_1  = g("§f총 구매가: 100 EP", 38, 12)
        val PRICE_2  = g("§f총 구매가: 100 EP", 39, 13)
        val BUY_1    = g("§f구매하기", 43, 22)
        val BUY_2    = g("§f구매하기", 44, 23)

        val all: List<ShopItem> get() = _items.toList()
    }

    private object SellGUI {
        private val _items = mutableListOf<ShopItem>()
        private fun g(name: String, slot: Int, cmd: Int) =
            ShopItem(name, 0, 0, "", org.bukkit.Material.BROWN_DYE, 1, slot, cmd).also { _items.add(it) }

        val SUB_32   = g("§f32개 빼기", 19, 16)
        val SUB_5    = g("§f5개 빼기", 20, 15)
        val SUB_1    = g("§f1개 빼기", 21, 14)
        val ADD_1    = g("§f1개 추가", 23, 17)
        val ADD_5    = g("§f5개 추가", 24, 18)
        val ADD_32   = g("§f32개 추가", 25, 19)
        val QTY_1    = g("§f현재 선택 수량: 1 개", 36, 10)
        val QTY_2    = g("§f현재 선택 수량: 1 개", 37, 11)
        val PRICE_1  = g("§f총 판매가: 100 EP", 38, 12)
        val PRICE_2  = g("§f총 판매가: 100 EP", 39, 13)
        val SELL_1   = g("§f판매하기", 43, 20)
        val SELL_2   = g("§f판매하기", 44, 21)

        val all: List<ShopItem> get() = _items.toList()
    }

    // ═════════════════════════════════════════════
    //  기존 인터페이스 유지 (외부에서 getShopItems 호출)
    // ═════════════════════════════════════════════
    private val shopMap: Map<String, List<ShopItem>> by lazy {
        mapOf(
            "MineralShop" to Mineral.all,
            "FarmingShop" to Farming.all,
            "OtherShop"   to Other.all,
            "BuyGUI"      to BuyGUI.all,
            "SellGUI"     to SellGUI.all
        )
    }

    fun getShopItems(shopName: String, page: Int? = null): List<ShopItem> {
        val items = shopMap[shopName] ?: return emptyList()
        return if (page != null) items.filter { it.page == page } else items
    }
}
