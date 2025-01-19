package kr.eme.semiTradeShop.objects

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
        addItem("MineralShop", ShopItem("§f리튬", 25, 15, "", Material.WARPED_FUNGUS_ON_A_STICK, 1, 9, 1))
        addItem("MineralShop", ShopItem("§f마그네슘", 10, 5, "", Material.WARPED_FUNGUS_ON_A_STICK, 1, 10, 4))
        addItem("MineralShop", ShopItem("§f니켈", 100, 50, "", Material.WARPED_FUNGUS_ON_A_STICK, 1, 11, 7))
        addItem("MineralShop", ShopItem("§f백금", 100, 60, "", Material.WARPED_FUNGUS_ON_A_STICK, 1, 12, 10))
        addItem("MineralShop", ShopItem("§f알루미늄", 50, 30, "", Material.WARPED_FUNGUS_ON_A_STICK, 1, 13, 13))
        addItem("MineralShop", ShopItem("§f금", 200, 100, "", Material.WARPED_FUNGUS_ON_A_STICK, 1, 18, 16))
        addItem("MineralShop", ShopItem("§f구리", 30, 20, "", Material.WARPED_FUNGUS_ON_A_STICK, 1, 19, 19))
        addItem("MineralShop", ShopItem("§f철", 15, 10, "", Material.WARPED_FUNGUS_ON_A_STICK, 1, 20, 22))
        addItem("MineralShop", ShopItem("§f티타늄", 300, 150, "", Material.WARPED_FUNGUS_ON_A_STICK, 1, 21, 25))


        // 광물 상점 (Page 2)
        addItem("MineralShop", ShopItem("§f리튬 파우더", 25, 15, "", Material.WARPED_FUNGUS_ON_A_STICK, 2, 9, 2))
        addItem("MineralShop", ShopItem("§f마그네슘 파우더", 20, 10, "", Material.WARPED_FUNGUS_ON_A_STICK, 2, 10, 5))
        addItem("MineralShop", ShopItem("§f니켈 파우더", 30, 15, "", Material.WARPED_FUNGUS_ON_A_STICK, 2, 11, 8))
        addItem("MineralShop", ShopItem("§f백금 파우더", 50, 25, "", Material.WARPED_FUNGUS_ON_A_STICK, 2, 12, 11))
        addItem("MineralShop", ShopItem("§f알루미늄 파우더", 15, 10, "", Material.WARPED_FUNGUS_ON_A_STICK, 2, 13, 14))
        addItem("MineralShop", ShopItem("§f금 파우더", 200, 100, "", Material.WARPED_FUNGUS_ON_A_STICK, 2, 18, 17))
        addItem("MineralShop", ShopItem("§f구리 파우더", 30, 20, "", Material.WARPED_FUNGUS_ON_A_STICK, 2, 19, 20))
        addItem("MineralShop", ShopItem("§f철 파우더", 50, 25, "", Material.WARPED_FUNGUS_ON_A_STICK, 2, 20, 23))
        addItem("MineralShop", ShopItem("§f티타늄 파우더", 300, 150, "", Material.WARPED_FUNGUS_ON_A_STICK, 2, 21, 26))
        addItem("MineralShop", ShopItem("§f합금 주괴", 500, 250, "§f알루미늄 + 구리", Material.WARPED_FUNGUS_ON_A_STICK, 2, 22, 28))
        addItem("MineralShop", ShopItem("§f합금 주괴", 400, 200, "§f알루미늄 + 마그네슘", Material.WARPED_FUNGUS_ON_A_STICK, 2, 27, 29))
        addItem("MineralShop", ShopItem("§f합금 주괴", 350, 175, "§f알루미늄 + 리튬", Material.WARPED_FUNGUS_ON_A_STICK, 2, 28, 30))
        addItem("MineralShop", ShopItem("§f합금 주괴", 450, 225, "§f구리 + 금", Material.WARPED_FUNGUS_ON_A_STICK, 2, 29, 31))
        addItem("MineralShop", ShopItem("§f합금 주괴", 600, 300, "§f니켈 + 철", Material.WARPED_FUNGUS_ON_A_STICK, 2, 30, 32))
        addItem("MineralShop", ShopItem("§f합금 주괴", 800, 400, "§f티타늄 + 백금 + 금", Material.WARPED_FUNGUS_ON_A_STICK, 2, 31, 33))


        // 광물 상점 (Page 3)

        // 작물 상점 (Page 1) - 씨앗 상점
        addItem("FarmingShop", ShopItem("§f감자", 100, 100, "§f봄 작물, §f수확일 3일", Material.BLACK_DYE, 1, 9, 31))
        addItem("FarmingShop", ShopItem("§f양배추 씨앗", 100, 150, "§f봄 작물, §f수확일 7일", Material.BLACK_DYE, 1, 10, 32))
        addItem("FarmingShop", ShopItem("§f오이 씨앗", 100, 100, "§f봄 작물, §f수확일 5일", Material.BLACK_DYE, 1, 11, 33))
        addItem("FarmingShop", ShopItem("§f커피콩", 100, 20, "§f봄/여름 작물, §f수확일 10일", Material.BLACK_DYE, 1, 12, 34))
        addItem("FarmingShop", ShopItem("§f토마토 씨앗", 30, 15, "§f여름 작물, §f수확일 3일", Material.BLACK_DYE, 1, 13, 35))
        addItem("FarmingShop", ShopItem("§f옥수수 씨앗", 60, 30, "§f여름 작물, §f수확일 5일", Material.BLACK_DYE, 1, 18, 36))
        addItem("FarmingShop", ShopItem("§f밀 씨앗", 100, 100, "§f여름/가을 작물, §f수확일 9일", Material.BLACK_DYE, 1, 19, 37))
        addItem("FarmingShop", ShopItem("§f크랜베리 씨앗", 100, 100, "§f가을 작물, §f수확일 3일", Material.BLACK_DYE, 1, 20, 38))
        addItem("FarmingShop", ShopItem("§f비트 씨앗", 50, 10, "§f가을 작물, §f수확일 3일", Material.BLACK_DYE, 1, 21, 39))
        addItem("FarmingShop", ShopItem("§f호박 씨앗", 100, 100, "§f가을 작물, §f수확일 8일", Material.BLACK_DYE, 1, 22, 40))

        // 작물 상점 (Page 2)
        addItem("FarmingShop", ShopItem("§f감자", -1, 10, "§f봄 작물, §f수확일 3일", Material.BLACK_DYE, 2, 9, 1))
        addItem("FarmingShop", ShopItem("§f양배추", -1, 150, "§f봄 작물, §f수확일 7일", Material.BLACK_DYE, 2, 10, 2))
        addItem("FarmingShop", ShopItem("§f오이", -1, 25, "§f봄 작물, §f수확일 5일", Material.BLACK_DYE, 2, 11, 3))
        addItem("FarmingShop", ShopItem("§f커피콩", -1, 10, "§f봄/여름 작물, §f수확일 10일", Material.BLACK_DYE, 2, 12, 4))
        addItem("FarmingShop", ShopItem("§f토마토", -1, 16, "§f여름 작물, §f수확일 3일", Material.BLACK_DYE, 2, 13, 5))
        addItem("FarmingShop", ShopItem("§f옥수수", -1, 30, "§f여름 작물, §f수확일 5일", Material.BLACK_DYE, 2, 18, 6))
        addItem("FarmingShop", ShopItem("§f밀", -1, 60, "§f여름/가을 작물, §f수확일 9일", Material.BLACK_DYE, 2, 19, 7))
        addItem("FarmingShop", ShopItem("§f크랜베리", -1, 10, "§f가을 작물, §f수확일 3일", Material.BLACK_DYE, 2, 20, 8))
        addItem("FarmingShop", ShopItem("§f비트", -1, 100, "§f가을 작물, §f수확일 3일", Material.BLACK_DYE, 2, 21, 9))
        addItem("FarmingShop", ShopItem("§f호박", -1, 200, "§f가을 작물, §f수확일 8일", Material.BLACK_DYE, 2, 22, 10))

        // 작물 상점 (Page 3)
        addItem("FarmingShop", ShopItem("§f감자", -1, 11, "§f봄 작물, §f수확일 3일", Material.BLACK_DYE, 3, 9, 11))
        addItem("FarmingShop", ShopItem("§f양배추", -1, 165, "§f봄 작물, §f수확일 7일", Material.BLACK_DYE, 3, 10, 12))
        addItem("FarmingShop", ShopItem("§f오이", -1, 28, "§f봄 작물, §f수확일 5일", Material.BLACK_DYE, 3, 11, 13))
        addItem("FarmingShop", ShopItem("§f커피콩", -1, 11, "§f봄/여름 작물, §f수확일 10일", Material.BLACK_DYE, 3, 12, 14))
        addItem("FarmingShop", ShopItem("§f토마토", -1, 17, "§f여름 작물, §f수확일 3일", Material.BLACK_DYE, 3, 13, 15))
        addItem("FarmingShop", ShopItem("§f옥수수", -1, 33, "§f여름 작물, §f수확일 5일", Material.BLACK_DYE, 3, 18, 16))
        addItem("FarmingShop", ShopItem("§f밀", -1, 66, "§f여름/가을 작물, §f수확일 9일", Material.BLACK_DYE, 3, 19, 17))
        addItem("FarmingShop", ShopItem("§f크랜베리", -1, 11, "§f가을 작물, §f수확일 3일", Material.BLACK_DYE, 3, 20, 18))
        addItem("FarmingShop", ShopItem("§f비트", -1, 110, "§f가을 작물, §f수확일 3일", Material.BLACK_DYE, 3, 21, 19))
        addItem("FarmingShop", ShopItem("§f호박", -1, 220, "§f가을 작물, §f수확일 8일", Material.BLACK_DYE, 3, 22, 20))

        // 작물 상점 (Page 4)
        addItem("FarmingShop", ShopItem("§f감자", -1, 13, "§f봄 작물, §f수확일 3일", Material.BLACK_DYE, 4, 9, 21))
        addItem("FarmingShop", ShopItem("§f양배추", -1, 195, "§f봄 작물, §f수확일 7일", Material.BLACK_DYE, 4, 10, 22))
        addItem("FarmingShop", ShopItem("§f오이", -1, 30, "§f봄 작물, §f수확일 5일", Material.BLACK_DYE, 4, 11, 23))
        addItem("FarmingShop", ShopItem("§f커피콩", -1, 13, "§f봄/여름 작물, §f수확일 10일", Material.BLACK_DYE, 4, 12, 24))
        addItem("FarmingShop", ShopItem("§f토마토", -1, 18, "§f여름 작물, §f수확일 3일", Material.BLACK_DYE, 4, 13, 25))
        addItem("FarmingShop", ShopItem("§f옥수수", -1, 39, "§f여름 작물, §f수확일 5일", Material.BLACK_DYE, 4, 18, 26))
        addItem("FarmingShop", ShopItem("§f밀", -1, 78, "§f여름/가을 작물, §f수확일 9일", Material.BLACK_DYE, 4, 19, 27))
        addItem("FarmingShop", ShopItem("§f크랜베리", -1, 13, "§f가을 작물, §f수확일 3일", Material.BLACK_DYE, 4, 20, 28))
        addItem("FarmingShop", ShopItem("§f비트", -1, 130, "§f가을 작물, §f수확일 3일", Material.BLACK_DYE, 4, 21, 29))
        addItem("FarmingShop", ShopItem("§f호박", -1, 260, "§f가을 작물, §f수확일 8일", Material.BLACK_DYE, 4, 22, 30))

        // 기타 상점 (Page 1)
        addItem("OtherShop", ShopItem("§f영양 캡슐", 10, 5, "§f더 높은 등급의 작물이 나올 확률이 증가합니다.", Material.SADDLE, 1, 9, 1))
        addItem("OtherShop", ShopItem("§f성장 캡슐", 7, 5, "§f작물의 수확일을 줄여줍니다.", Material.SADDLE, 1, 10, 2))
        addItem("OtherShop", ShopItem("§f제초 캡슐", 50, 25, "§f잡초를 제거합니다.", Material.SADDLE, 1, 11, 3))
        addItem("OtherShop", ShopItem("§f커피잔", 100, 100, "§fJava", Material.SADDLE, 1, 12, 4))

        // 구매 GUI
        addItem("BuyGUI", ShopItem("§f", 0, 0, "", Material.BROWN_DYE, 1, 10))
        // 판매 GUI


    }
}
