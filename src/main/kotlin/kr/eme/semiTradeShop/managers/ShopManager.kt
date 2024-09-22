package kr.eme.semiTradeShop.managers

import kr.eme.semiTradeShop.objects.Shop
import kr.eme.semiTradeShop.objects.ShopItems

object ShopManager {
    private val shops = mutableListOf<Shop>()

    fun addShop(shop: Shop) {
        if (shops.contains(shop)) return
        shops.add(shop)
    }
    fun removeShop(shopName: String) {
        shops.removeIf { it.name == shopName }
    }
    fun getShop(shopName: String): Shop? = shops.find { it.name == shopName }
    fun getAllShops(): List<Shop> = shops

    // 플레이어가 Shop 목록을 임의로 삭제하거나 수정할 수 없음.
    init {
        addShop(
            Shop(
                name = "MineralShop",
                description = "광물을 사고 파는 상점",
                items = ShopItems.getShopItems("MineralShop")
            )
        )
        addShop(
            Shop(
                name = "CropShop",
                description = "작물을 사고 파는 상점",
                items = ShopItems.getShopItems("CropShop")
            )
        )
    }
}