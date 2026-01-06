package kr.eme.prcShop.objects

import org.bukkit.Material

object ShopItems {

    private val shopItemMap = HashMap<String, MutableList<ShopItem>>()

    fun addItem(shopName: String, item: ShopItem) {
        val items = shopItemMap.getOrPut(shopName) { mutableListOf() }
        items.add(item)
    }

    fun getShopItems(shopName: String, page: Int? = null): List<ShopItem> {
        val items = shopItemMap[shopName] ?: return emptyList()
        return if (page != null) items.filter { it.page == page }.toList() else items.toList()
    }

    // 아이템 추가 로직을 init 블록으로 분리하여 순서대로 실행되도록 함
    init {
        // 광물 상점 (Page 1)
        addItem("MineralShop", ShopItem("§f마그네슘", 100, 20, "", Material.RED_DYE, 1, 9, 1))
        addItem("MineralShop", ShopItem("§f알루미늄", 110, 30, "", Material.RED_DYE, 1, 10, 2))
        addItem("MineralShop", ShopItem("§f철", 150, 60, "", Material.RED_DYE, 1, 11, 3))
        addItem("MineralShop", ShopItem("§f구리", 200, 70, "", Material.RED_DYE, 1, 12, 4))
        addItem("MineralShop", ShopItem("§f리튬", 250, 150, "", Material.RED_DYE, 1, 13, 5))
        addItem("MineralShop", ShopItem("§f금", 600, 250, "", Material.RED_DYE, 1, 18, 6))
        addItem("MineralShop", ShopItem("§f백금", 1000, 300, "", Material.RED_DYE, 1, 19, 7))
        addItem("MineralShop", ShopItem("§f니켈", 1000, 300, "", Material.RED_DYE, 1, 20, 8))
        addItem("MineralShop", ShopItem("§f티타늄", 1300, 450, "", Material.RED_DYE, 1, 21, 9))

        // 광물 상점 (Page 2)
        // 광물 상점 (Page 2)
        addItem("MineralShop", ShopItem("§f마그네슘 파우더", -1, 22, "", Material.RED_DYE, 2, 9, 10))
        addItem("MineralShop", ShopItem("§f알루미늄 파우더", -1, 33, "", Material.RED_DYE, 2, 10, 11))
        addItem("MineralShop", ShopItem("§f철 파우더", -1, 66, "", Material.RED_DYE, 2, 11, 12))
        addItem("MineralShop", ShopItem("§f구리 파우더", -1, 77, "", Material.RED_DYE, 2, 12, 13))
        addItem("MineralShop", ShopItem("§f리튬 파우더", -1, 165, "", Material.RED_DYE, 2, 13, 14))
        addItem("MineralShop", ShopItem("§f금 파우더", -1, 275, "", Material.RED_DYE, 2, 18, 15))
        addItem("MineralShop", ShopItem("§f백금 파우더", -1, 330, "", Material.RED_DYE, 2, 19, 16))
        addItem("MineralShop", ShopItem("§f니켈 파우더", -1, 330, "", Material.RED_DYE, 2, 20, 17))
        addItem("MineralShop", ShopItem("§f티타늄 파우더", -1, 495, "", Material.RED_DYE, 2, 21, 18))



        // 광물 상점 (Page 3)
        // 광물 상점 (Page 3)
        addItem("MineralShop", ShopItem("§f마그네슘 주괴", 500, 120, "", Material.RED_DYE, 3, 9, 19))
        addItem("MineralShop", ShopItem("§f알루미늄 주괴", 550, 170, "", Material.RED_DYE, 3, 10, 20))
        addItem("MineralShop", ShopItem("§f철 주괴", 750, 300, "", Material.RED_DYE, 3, 11, 21))
        addItem("MineralShop", ShopItem("§f구리 주괴", 1000, 350, "", Material.RED_DYE, 3, 12, 22))
        addItem("MineralShop", ShopItem("§f리튬 주괴", 1200, 750, "", Material.RED_DYE, 3, 13, 23))
        addItem("MineralShop", ShopItem("§f금 주괴", -1, 1250, "", Material.RED_DYE, 3, 18, 24))
        addItem("MineralShop", ShopItem("§f백금 주괴", -1, 1500, "", Material.RED_DYE, 3, 19, 25))
        addItem("MineralShop", ShopItem("§f니켈 주괴", -1, 1500, "", Material.RED_DYE, 3, 20, 26))
        addItem("MineralShop", ShopItem("§f티타늄 주괴", -1, 2250, "", Material.RED_DYE, 3, 21, 27))
        addItem("MineralShop", ShopItem("§f합금 주괴", -1, -1, "§fAl-Cu 합금 주괴", Material.RED_DYE, 3, 22, 28))
        addItem("MineralShop", ShopItem("§f합금 주괴", -1, -1, "§fAl-Mg 합금 주괴", Material.RED_DYE, 3, 27, 29))
        addItem("MineralShop", ShopItem("§f합금 주괴", -1, -1, "§fAl-Li 합금 주괴", Material.RED_DYE, 3, 28, 30))
        addItem("MineralShop", ShopItem("§f합금 주괴", -1, -1, "§fCu-Au 합금 주괴", Material.RED_DYE, 3, 29, 31))
        addItem("MineralShop", ShopItem("§f합금 주괴", -1, -1, "§fNi-Fe 합금 주괴", Material.RED_DYE, 3, 30, 32))
        addItem("MineralShop", ShopItem("§f합금 주괴", -1, -1, "§fTi-Pt-Au 합금 주괴", Material.RED_DYE, 3, 31, 33))


        // 작물 상점 (Page 1) - 씨앗 상점
        addItem("FarmingShop", ShopItem("§f감자 씨앗", 20, 4, "§f봄 작물, §f수확일 3일", Material.BLACK_DYE, 1, 9, 31))
        addItem("FarmingShop", ShopItem("§f양배추 씨앗", 100, 20, "§f봄 작물, §f수확일 7일", Material.BLACK_DYE, 1, 10, 32))
        addItem("FarmingShop", ShopItem("§f오이 씨앗", 50, 10, "§f봄 작물, §f수확일 5일", Material.BLACK_DYE, 1, 11, 33))
        addItem("FarmingShop", ShopItem("§f커피콩", 100, 20, "§f봄/여름 작물, §f수확일 10일", Material.BLACK_DYE, 1, 12, 34))
        addItem("FarmingShop", ShopItem("§f토마토 씨앗", 30, 6, "§f여름 작물, §f수확일 3일", Material.BLACK_DYE, 1, 13, 35))
        addItem("FarmingShop", ShopItem("§f옥수수 씨앗", 60, 12, "§f여름 작물, §f수확일 5일", Material.BLACK_DYE, 1, 18, 36))
        addItem("FarmingShop", ShopItem("§f밀 씨앗", 120, 24, "§f여름/가을 작물, §f수확일 9일", Material.BLACK_DYE, 1, 19, 37))
        addItem("FarmingShop", ShopItem("§f크랜베리 씨앗", 20, 4, "§f가을 작물, §f수확일 3일", Material.BLACK_DYE, 1, 20, 38))
        addItem("FarmingShop", ShopItem("§f비트 씨앗", 50, 10, "§f가을 작물, §f수확일 3일", Material.BLACK_DYE, 1, 21, 39))
        addItem("FarmingShop", ShopItem("§f호박 씨앗", 120, 24, "§f가을 작물, §f수확일 8일", Material.BLACK_DYE, 1, 22, 40))


        // 작물 상점 (Page 2)
        addItem("FarmingShop", ShopItem("§f감자", 50, 10, "§f봄 작물, §f수확일 3일", Material.BLACK_DYE, 2, 9, 1))
        addItem("FarmingShop", ShopItem("§f양배추", 750, 150, "§f봄 작물, §f수확일 7일", Material.BLACK_DYE, 2, 10, 2))
        addItem("FarmingShop", ShopItem("§f오이", 125, 25, "§f봄 작물, §f수확일 5일", Material.BLACK_DYE, 2, 11, 3))
        addItem("FarmingShop", ShopItem("§f커피콩", 100, 10, "§f봄/여름 작물, §f수확일 10일", Material.BLACK_DYE, 2, 12, 4))
        addItem("FarmingShop", ShopItem("§f토마토", 75, 15, "§f여름 작물, §f수확일 3일", Material.BLACK_DYE, 2, 13, 5))
        addItem("FarmingShop", ShopItem("§f옥수수", 150, 30, "§f여름 작물, §f수확일 5일", Material.BLACK_DYE, 2, 18, 6))
        addItem("FarmingShop", ShopItem("§f밀", 300, 60, "§f여름/가을 작물, §f수확일 9일", Material.BLACK_DYE, 2, 19, 7))
        addItem("FarmingShop", ShopItem("§f크랜베리", 50, 10, "§f가을 작물, §f수확일 3일", Material.BLACK_DYE, 2, 20, 8))
        addItem("FarmingShop", ShopItem("§f비트", 500, 100, "§f가을 작물, §f수확일 3일", Material.BLACK_DYE, 2, 21, 9))
        addItem("FarmingShop", ShopItem("§f호박", 1000, 200, "§f가을 작물, §f수확일 8일", Material.BLACK_DYE, 2, 22, 10))


        // 작물 상점 (Page 3)
        addItem("FarmingShop", ShopItem("§f감자", 55, 11, "§f봄 작물, §f수확일 3일", Material.BLACK_DYE, 3, 9, 11))
        addItem("FarmingShop", ShopItem("§f양배추", 825, 165, "§f봄 작물, §f수확일 7일", Material.BLACK_DYE, 3, 10, 12))
        addItem("FarmingShop", ShopItem("§f오이", 140, 28, "§f봄 작물, §f수확일 5일", Material.BLACK_DYE, 3, 11, 13))
        addItem("FarmingShop", ShopItem("§f커피콩", 55, 11, "§f봄/여름 작물, §f수확일 10일", Material.BLACK_DYE, 3, 12, 14))
        addItem("FarmingShop", ShopItem("§f토마토", 85, 17, "§f여름 작물, §f수확일 3일", Material.BLACK_DYE, 3, 13, 15))
        addItem("FarmingShop", ShopItem("§f옥수수", 165, 33, "§f여름 작물, §f수확일 5일", Material.BLACK_DYE, 3, 18, 16))
        addItem("FarmingShop", ShopItem("§f밀", 330, 66, "§f여름/가을 작물, §f수확일 9일", Material.BLACK_DYE, 3, 19, 17))
        addItem("FarmingShop", ShopItem("§f크랜베리", 55, 11, "§f가을 작물, §f수확일 3일", Material.BLACK_DYE, 3, 20, 18))
        addItem("FarmingShop", ShopItem("§f비트", 550, 110, "§f가을 작물, §f수확일 3일", Material.BLACK_DYE, 3, 21, 19))
        addItem("FarmingShop", ShopItem("§f호박", 1100, 220, "§f가을 작물, §f수확일 8일", Material.BLACK_DYE, 3, 22, 20))


        // 작물 상점 (Page 4)
        addItem("FarmingShop", ShopItem("§f감자", 65, 13, "§f봄 작물, §f수확일 3일", Material.BLACK_DYE, 4, 9, 21))
        addItem("FarmingShop", ShopItem("§f양배추", 975, 195, "§f봄 작물, §f수확일 7일", Material.BLACK_DYE, 4, 10, 22))
        addItem("FarmingShop", ShopItem("§f오이", 150, 30, "§f봄 작물, §f수확일 5일", Material.BLACK_DYE, 4, 11, 23))
        addItem("FarmingShop", ShopItem("§f커피콩", 65, 13, "§f봄/여름 작물, §f수확일 10일", Material.BLACK_DYE, 4, 12, 24))
        addItem("FarmingShop", ShopItem("§f토마토", 90, 18, "§f여름 작물, §f수확일 3일", Material.BLACK_DYE, 4, 13, 25))
        addItem("FarmingShop", ShopItem("§f옥수수", 195, 39, "§f여름 작물, §f수확일 5일", Material.BLACK_DYE, 4, 18, 26))
        addItem("FarmingShop", ShopItem("§f밀", 390, 78, "§f여름/가을 작물, §f수확일 9일", Material.BLACK_DYE, 4, 19, 27))
        addItem("FarmingShop", ShopItem("§f크랜베리", 65, 13, "§f가을 작물, §f수확일 3일", Material.BLACK_DYE, 4, 20, 28))
        addItem("FarmingShop", ShopItem("§f비트", 650, 130, "§f가을 작물, §f수확일 3일", Material.BLACK_DYE, 4, 21, 29))
        addItem("FarmingShop", ShopItem("§f호박", 1300, 260, "§f가을 작물, §f수확일 8일", Material.BLACK_DYE, 4, 22, 30))


        // 기타 상점 (Page 1)
        addItem("OtherShop", ShopItem("§f영양 캡슐", 10, 5, "§f더 높은 등급의 작물이 나올 확률이 증가합니다.", Material.ORANGE_DYE, 1, 9, 2))
        addItem("OtherShop", ShopItem("§f성장 캡슐", 7, 5, "§f작물의 수확일을 줄여줍니다.", Material.ORANGE_DYE, 1, 10, 3))
        addItem("OtherShop", ShopItem("§f제초 캡슐", 50, 25, "§f잡초를 제거합니다.", Material.ORANGE_DYE, 1, 11, 4))
        addItem("OtherShop", ShopItem("§f머그잔", 5, -1, "§fJava", Material.BOWL, 1, 12, 1))

        // 기타 상점 (Page 2)
        addItem("OtherShop", ShopItem("§f기본 연장 모듈", -1, -1,
            "§f", Material.SADDLE, 2, 9, 1,
            listOf(TradeRequirement(Material.RED_DYE, 5, 21, "§f철 주괴"))))

        addItem("OtherShop", ShopItem("§fT 연장 모듈", -1, -1,
            "§f", Material.SADDLE, 2, 10, 2,
            listOf(TradeRequirement(Material.RED_DYE, 5, 21, "§f철 주괴"))))

        addItem("OtherShop", ShopItem("§f십자 연장 모듈", -1, -1,
            "§f", Material.SADDLE, 2, 11, 3,
            listOf(TradeRequirement(Material.RED_DYE, 5, 21, "§f철 주괴"))))

        addItem("OtherShop", ShopItem("§f스토리지 모듈 [중]", -1, -1,
            "§f", Material.SADDLE, 2, 12, 4,
            listOf(TradeRequirement(Material.RED_DYE, 35, 21, "§f철 주괴"))))

        addItem("OtherShop", ShopItem("§f스토리지 모듈 [대]", -1, -1,
            "§f", Material.SADDLE, 2, 13, 5,
            listOf(TradeRequirement(Material.RED_DYE, 50, 21, "§f철 주괴"))))

        addItem("OtherShop", ShopItem("§f광산 모듈", -1, -1,
            "§f", Material.SADDLE, 2, 18, 6,
            listOf(TradeRequirement(Material.RED_DYE, 5, 21, "§f철 주괴"))))

        addItem("OtherShop", ShopItem("§f농사 모듈 [중]", -1, -1,
            "§f", Material.SADDLE, 2, 19, 7,
            listOf(
                TradeRequirement(Material.RED_DYE, 20, 21, "§f철 주괴"),
                TradeRequirement(Material.RED_DYE, 10, 23, "§f리튬 주괴")
            )))

        addItem("OtherShop", ShopItem("§f농사 모듈 [대]", -1, -1,
            "§f", Material.SADDLE, 2, 20, 8,
            listOf(
                TradeRequirement(Material.RED_DYE, 30, 21, "§f철 주괴"),
                TradeRequirement(Material.RED_DYE, 10, 23, "§f리튬 주괴"),
                TradeRequirement(Material.RED_DYE, 5, 27, "§f티타늄 주괴")
            )))

        addItem("OtherShop", ShopItem("§f출입 모듈", -1, -1,
            "§f", Material.SADDLE, 2, 21, 9,
            listOf(TradeRequirement(Material.RED_DYE, 50, 21, "§f철 주괴"))))

        // 기타 상점 (Page 3)
        addItem("OtherShop", ShopItem("§f스패너", 130, -1,
            "§f", Material.WOODEN_SHOVEL, 3, 9, 13))
        
        addItem("OtherShop", ShopItem("§f분쇄기 모듈", 500, -1,
            "§f", Material.IRON_HORSE_ARMOR, 3, 10, 2))

        addItem("OtherShop", ShopItem("§f프린트 모듈", 500, -1,
            "§f", Material.IRON_HORSE_ARMOR, 3, 11, 3))

        addItem("OtherShop", ShopItem("§f커피머신 모듈", 2000, -1,
            "§f", Material.IRON_HORSE_ARMOR, 3, 12, 4))

        addItem("OtherShop", ShopItem("§f용광로 모듈", 2000, -1,
            "§f", Material.IRON_HORSE_ARMOR, 3, 13, 6))

        addItem("OtherShop", ShopItem("§f분쇄기 용량 확장", -1, -1,
            "§f", Material.SADDLE, 3, 18, 20,
            listOf(
                TradeRequirement(Material.RED_DYE,30,21,"§f철 주괴"),
                TradeRequirement(Material.RED_DYE,20,22,"§f구리 주괴")
            )))

        addItem("OtherShop", ShopItem("§f합금 기어", -1, -1,
            "§fAl-Cu", Material.SADDLE, 3, 19, 21,
            listOf(
                TradeRequirement(Material.RED_DYE,25, 28, "§f합금 주괴"),
                TradeRequirement(Material.RED_DYE,10,23,"§f리튬 주괴")
            )))

        addItem("OtherShop", ShopItem("§f합금 드릴", -1, -1,
            "§fTi-Pt-Au", Material.SADDLE, 3, 20, 22,
            listOf(
                TradeRequirement(Material.RED_DYE, 15, 33, "§f합금 주괴"),
                TradeRequirement(Material.RED_DYE, 10, 3, "§f철"),
                TradeRequirement(Material.RED_DYE, 10, 4, "§f구리")
            )))

        addItem("OtherShop", ShopItem("§f용광로 용해 슬롯 확장", -1, -1,
            "§f", Material.SADDLE, 3, 21, 17,
            listOf(
                TradeRequirement(Material.RED_DYE, 10, 21, "§f철 주괴"),
                TradeRequirement(Material.RED_DYE, 10, 22, "§f구리 주괴")
            )))

        addItem("OtherShop", ShopItem("§f합금 냉각 몰드", -1, -1,
            "§fCu-Au", Material.SADDLE, 3, 22, 18,
            listOf(
                TradeRequirement(Material.RED_DYE, 20, 31, "§f합금 주괴")
            )))

        addItem("OtherShop", ShopItem("§f합금 토치", -1, -1,
            "§fNi-Fe", Material.SADDLE, 3, 27, 19,
            listOf(
                TradeRequirement(Material.RED_DYE, 10, 32, "§f합금 주괴"),
                TradeRequirement(Material.RED_DYE, 10, 26, "§f니켈 주괴"),
                TradeRequirement(Material.RED_DYE, 10, 27, "§f티타늄 주괴")
            )))

        // 기타 상점 (Page 4)
        addItem("OtherShop", ShopItem("§f마그네슘 주괴 레시피", 100, -1, "§f", Material.SADDLE, 4, 9, 23))
        addItem("OtherShop", ShopItem("§f알루미늄 주괴 레시피", 100, -1, "§f", Material.SADDLE, 4, 10, 24))
        addItem("OtherShop", ShopItem("§f철 주괴 레시피", 100, -1, "§f", Material.SADDLE, 4, 11, 25))
        addItem("OtherShop", ShopItem("§f구리 주괴 레시피", 300, -1, "§f", Material.SADDLE, 4, 12, 26))
        addItem("OtherShop", ShopItem("§f리튬 주괴 레시피", 300, -1, "§f", Material.SADDLE, 4, 13, 27))
        addItem("OtherShop", ShopItem("§f금 주괴 레시피", 300, -1, "§f", Material.SADDLE, 4, 18, 28))
        addItem("OtherShop", ShopItem("§f백금 주괴 레시피", 500, -1, "§f", Material.SADDLE, 4, 19, 29))
        addItem("OtherShop", ShopItem("§f니켈 주괴 레시피", 500, -1, "§f", Material.SADDLE, 4, 20, 30))
        addItem("OtherShop", ShopItem("§f티타늄 주괴 레시피", 500, -1, "§f", Material.SADDLE, 4, 21, 31))
        addItem("OtherShop", ShopItem("§fAl-Cu 합금 레시피", 400, -1, "§f알루미늄 + 구리", Material.SADDLE, 4, 22, 32))
        addItem("OtherShop", ShopItem("§fAl-Mg 합금 레시피", 400, -1, "§f알루미늄 + 마그네슘", Material.SADDLE, 4, 27, 33))
        addItem("OtherShop", ShopItem("§fAl-Li 합금 레시피", 400, -1, "§f알루미늄 + 리튬", Material.SADDLE, 4, 28, 34))
        addItem("OtherShop", ShopItem("§fCu-Au 합금 레시피", 700, -1, "§f구리 + 금", Material.SADDLE, 4, 29, 35))
        addItem("OtherShop", ShopItem("§fNi-Fe 합금 레시피", 700, -1, "§f니켈 + 철", Material.SADDLE, 4, 30, 36))
        addItem("OtherShop", ShopItem("§fTi-Pt-Au 합금 레시피", 1500, -1, "§f티타늄 + 백금 + 금", Material.SADDLE, 4, 31, 37))


        // 구매 GUI
        addItem("BuyGUI", ShopItem("§f64개 빼기", 0, 0, "", Material.BROWN_DYE, 1, 19, 16))
        addItem("BuyGUI", ShopItem("§f32개 빼기", 0, 0, "", Material.BROWN_DYE, 1, 20, 15))
        addItem("BuyGUI", ShopItem("§f1개 빼기", 0, 0, "", Material.BROWN_DYE, 1, 21, 14))
        addItem("BuyGUI", ShopItem("§f1개 추가", 0, 0, "", Material.BROWN_DYE, 1, 23, 17))
        addItem("BuyGUI", ShopItem("§f32개 추가", 0, 0, "", Material.BROWN_DYE, 1, 24, 18))
        addItem("BuyGUI", ShopItem("§f64개 추가", 0, 0, "", Material.BROWN_DYE, 1, 25, 19))
        addItem("BuyGUI", ShopItem("§f현재 선택 수량: 1 개", 0, 0, "", Material.BROWN_DYE, 1, 36, 10))
        addItem("BuyGUI", ShopItem("§f현재 선택 수량: 1 개", 0, 0, "", Material.BROWN_DYE, 1, 37, 11))
        addItem("BuyGUI", ShopItem("§f총 구매가: 100 EP", 0, 0, "", Material.BROWN_DYE, 1, 38, 12))
        addItem("BuyGUI", ShopItem("§f총 구매가: 100 EP", 0, 0, "", Material.BROWN_DYE, 1, 39, 13))
        addItem("BuyGUI", ShopItem("§f구매하기", 0, 0, "", Material.BROWN_DYE, 1, 43, 22))
        addItem("BuyGUI", ShopItem("§f구매하기", 0, 0, "", Material.BROWN_DYE, 1, 44, 23))

        // 판매 GUI
        addItem("SellGUI", ShopItem("§f64개 빼기", 0, 0, "", Material.BROWN_DYE, 1, 19, 16))
        addItem("SellGUI", ShopItem("§f32개 빼기", 0, 0, "", Material.BROWN_DYE, 1, 20, 15))
        addItem("SellGUI", ShopItem("§f1개 빼기", 0, 0, "", Material.BROWN_DYE, 1, 21, 14))
        addItem("SellGUI", ShopItem("§f1개 추가", 0, 0, "", Material.BROWN_DYE, 1, 23, 17))
        addItem("SellGUI", ShopItem("§f32개 추가", 0, 0, "", Material.BROWN_DYE, 1, 24, 18))
        addItem("SellGUI", ShopItem("§f64개 추가", 0, 0, "", Material.BROWN_DYE, 1, 25, 19))
        addItem("SellGUI", ShopItem("§f현재 선택 수량: 1 개", 0, 0, "", Material.BROWN_DYE, 1, 36, 10))
        addItem("SellGUI", ShopItem("§f현재 선택 수량: 1 개", 0, 0, "", Material.BROWN_DYE, 1, 37, 11))
        addItem("SellGUI", ShopItem("§f총 판매가: 100 EP", 0, 0, "", Material.BROWN_DYE, 1, 38, 12))
        addItem("SellGUI", ShopItem("§f총 판매가: 100 EP", 0, 0, "", Material.BROWN_DYE, 1, 39, 13))
        addItem("SellGUI", ShopItem("§f판매하기", 0, 0, "", Material.BROWN_DYE, 1, 43, 20))
        addItem("SellGUI", ShopItem("§f판매하기", 0, 0, "", Material.BROWN_DYE, 1, 44, 21))
    }
}
