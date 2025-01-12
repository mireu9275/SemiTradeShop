package kr.eme.semiTradeShop.objects

import org.bukkit.Material

object ShopItems {

    private val shopItemMap = HashMap<String, MutableList<ShopItem>>()

    fun addItem(shopName: String, item: ShopItem) {
        val items = shopItemMap.getOrPut(shopName) { mutableListOf() }
        items.add(item)
    }

    fun getShopItems(shopName: String): List<ShopItem> {
        return shopItemMap[shopName] ?: emptyList()
    }

    // 아이템 추가 로직을 init 블록으로 분리하여 순서대로 실행되도록 함
    init {
        // 광물 상점 아이템 추가
        addItem("MineralShop", ShopItem("§f§l마그네슘", 10, 2, "§f§lDescription", Material.RED_CONCRETE))
        addItem("MineralShop", ShopItem("§f§l알루미늄", 11, 3, "§f§lDescription", Material.BLUE_CONCRETE))
        addItem("MineralShop", ShopItem("§f§l철", 15, 6, "§f§lDescription", Material.LIME_CONCRETE))
        addItem("MineralShop", ShopItem("§f§l구리", 20, 6, "§f§lDescription", Material.BLACK_CONCRETE))
        addItem("MineralShop", ShopItem("§f§l리튬", 25, 15, "§f§lDescription", Material.LIGHT_BLUE_CONCRETE))
        addItem("MineralShop", ShopItem("§f§l금", 60, 25, "§f§lDescription", Material.PURPLE_CONCRETE))
        addItem("MineralShop", ShopItem("§f§l백금", 100, 30, "§f§lDescription", Material.GRAY_CONCRETE))
        addItem("MineralShop", ShopItem("§f§l니켈", 100, 30, "§f§lDescription", Material.BROWN_CONCRETE))
        addItem("MineralShop", ShopItem("§f§l티타늄", 100, 45, "§f§lDescription", Material.CYAN_CONCRETE))

        // 작물 상점 아이템 추가
        addItem("CropShop", ShopItem("§f§l감자", 3, 3, "§f§lDescription", Material.POTATO))
        addItem("CropShop", ShopItem("§f§l양배추", 4, 4, "§f§lDescription", Material.GRASS))
        addItem("CropShop", ShopItem("§f§l오이", 2, 3, "§f§lDescription", Material.GREEN_CONCRETE))
        addItem("CropShop", ShopItem("§f§l커피콩", 10, 2, "§f§lDescription", Material.COCOA_BEANS))
        addItem("CropShop", ShopItem("§f§l토마토", 5, 4, "§f§lDescription", Material.RED_CONCRETE))
        addItem("CropShop", ShopItem("§f§l밀", 2, 2, "§f§lDescription", Material.WHEAT))
        addItem("CropShop", ShopItem("§f§l옥수수", 3, 5, "§f§lDescription", Material.POISONOUS_POTATO))
        addItem("CropShop", ShopItem("§f§크랜베리", 6, 4, "§f§lDescription", Material.SWEET_BERRIES))
        addItem("CropShop", ShopItem("§f§l비트", 3, 4, "§f§lDescription", Material.BEETROOT))
        addItem("CropShop", ShopItem("§f§l호박", 7, 11, "§f§lDescription", Material.PUMPKIN))

    }
}
