package kr.eme.prcShop.utils

import kr.eme.prcShop.objects.ShopItem
import kr.eme.prcShop.objects.ShopItems
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object ExchangeUtil {

    fun attemptExchange(player: Player, shopItem: ShopItem): Boolean {
        val inventory = player.inventory

        // 조건 부족한지 확인
        val missing = shopItem.tradeRequirements.firstOrNull { req ->
            val count = inventory.contents.filterNotNull().sumOf {
                val meta = it.itemMeta
                val hasCMD = meta?.hasCustomModelData() == true
                val typeMatch = it.type == req.material
                val cmdMatch = hasCMD && meta.customModelData == req.customModelData
                val matched = typeMatch && cmdMatch

                // 디버그 로그
//                println("== 인벤토리 아이템 검사 ==")
//                println("아이템: ${it.type}, CMD: ${if (hasCMD) meta.customModelData else "없음"}, 이름: ${meta?.displayName}")
//                println("필요한 것: ${req.material}, CMD: ${req.customModelData}, 이름: ${req.itemName}")
//                println("결과: ${if (matched) "일치" else "불일치"}")
//                println("-------------------------")

                if (matched) it.amount else 0
            }

            count < req.amount
        }


        if (missing != null) {
            player.sendMessage("§c[교환 실패] 필요한 아이템이 부족합니다.")
            SoundUtil.error(player)
            return false
        }

        // 조건 아이템 제거
        shopItem.tradeRequirements.forEach { req ->
            var toRemove = req.amount
            val contents = inventory.contents
            for (i in contents.indices) {
                val item = contents[i] ?: continue
                if (item.type == req.material && item.itemMeta?.customModelData == req.customModelData) {
                    val removeAmount = minOf(item.amount, toRemove)
                    item.amount -= removeAmount
                    toRemove -= removeAmount
                    if (item.amount <= 0) contents[i] = null
                    if (toRemove <= 0) break
                }
            }
        }

        // 결과 아이템 지급
        val result = ItemStack(shopItem.material).apply {
            amount = 1
            itemMeta = itemMeta?.apply {
                setDisplayName(shopItem.name)
                setItemName(shopItem.name)
                shopItem.customModelData?.let { setCustomModelData(it) }
            }
        }
        player.inventory.addItem(result)
        player.sendMessage("§a[교환 성공] ${shopItem.name} 을 획득했습니다.")
        SoundUtil.click(player)
        return true
    }

    fun tryTrade(player: Player, shopName: String, page: Int, itemDisplayName: String): TradeResult {
        val shopItem = ShopItems.getShopItems(shopName, page)
            .find { ItemStackUtil.cutColorCodes(it.name ?: return@find false) == ItemStackUtil.cutColorCodes(itemDisplayName) }
            ?: return TradeResult.NotTradeItem

        if (shopItem.tradeRequirements.isEmpty()) return TradeResult.NotTradeItem

        val success = attemptExchange(player, shopItem)
        return if (success) TradeResult.Success else TradeResult.MissingItems
    }

    sealed class TradeResult {
        object NotTradeItem : TradeResult()
        object Success : TradeResult()
        object MissingItems : TradeResult()
    }
}
