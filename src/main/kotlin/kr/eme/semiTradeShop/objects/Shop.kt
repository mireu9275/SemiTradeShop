package kr.eme.semiTradeShop.objects

data class Shop(
    val name: String,
    val description: String,
    val items: List<ShopItem>
)
