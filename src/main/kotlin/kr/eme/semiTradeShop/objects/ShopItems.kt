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
        addItem("MineralShop", ShopItem("§f§l철", 15, 6, "§f§l평범한 철", Material.IRON_ORE))
        addItem("MineralShop", ShopItem("§f§l금", 60, 25, "§f§l평범한 금", Material.GOLD_ORE))

        // 작물 상점 아이템 추가
        addItem("CropShop", ShopItem("§f§l감자", 60, 25, "§f§l평범한 감자", Material.POTATO))
    }
}
