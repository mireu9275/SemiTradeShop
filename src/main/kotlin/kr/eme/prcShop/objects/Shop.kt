package kr.eme.prcShop.objects

data class Shop(
    val name: String,
    val description: String,
    val items: List<ShopItem>
)
