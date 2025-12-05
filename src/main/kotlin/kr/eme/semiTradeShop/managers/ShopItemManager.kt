package kr.eme.semiTradeShop.managers

import kr.eme.semiMission.api.events.MissionEvent
import kr.eme.semiMission.enums.MissionVersion
import kr.eme.semiMoneyGlobal.managers.MoneyManager
import kr.eme.semiTradeShop.extensions.toBukkitItemWithoutPrice
import kr.eme.semiTradeShop.objects.ShopItem
import kr.eme.semiTradeShop.objects.ShopItems
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

object ShopItemManager {
    // 플레이어별 선택된 상점과 아이템을 저장하는 맵
    val selectedItems = mutableMapOf<UUID, Pair<String, String>>()

    fun purchaseItem(player: Player, shopName: String, itemName: String, quantity: Int) {
        val item = findShopItem(shopName, itemName)
        if (item == null || item.buyPrice <= 0) {
            player.sendMessage("구매할 수 있는 아이템이 아닙니다!")
            return
        }
        val uuid: UUID = player.uniqueId
        val totalPrice = item.buyPrice * quantity
        if (!MoneyManager.subtractMoney(totalPrice)) {
            player.sendMessage("보유중인 EP가 부족합니다!")
            return
        }

        // 아이템 구매 (수량 반영)
        val itemStack = item.toBukkitItemWithoutPrice().apply { amount = quantity }
        val leftover = player.inventory.addItem(itemStack)

        // 인벤토리 공간 부족 시 남은 아이템을 드롭
        if (leftover.isNotEmpty()) {
            player.sendMessage("인벤토리에 공간이 부족하여 일부 아이템이 드롭되었습니다.")
            leftover.values.forEach { player.world.dropItemNaturally(player.location, it) }
        }

        player.sendMessage("정상적으로 ${item.name}§f을/를 구매하였습니다. (사용금액: $totalPrice EP)")
    }

    fun sellItem(player: Player, shopName: String, itemName: String, quantity: Int) {
        val item = findShopItem(shopName, itemName)
        if (item == null || item.sellPrice <= 0) {
            player.sendMessage("판매할 수 있는 아이템이 아닙니다!")
            return
        }
        val uuid: UUID = player.uniqueId
        val itemStack = item.toBukkitItemWithoutPrice().apply { amount = quantity }

        // 인벤토리에 충분한 아이템이 있는지 확인
        if (!player.inventory.containsAtLeast(itemStack, quantity)) {
            player.sendMessage("판매할 수 있는 아이템이 부족합니다.")
            return
        }

        // 아이템 판매
        player.inventory.removeItem(itemStack)
        MoneyManager.addMoney(item.sellPrice * quantity)
        player.sendMessage("정상적으로 ${item.name}§f을/를 판매하였습니다. (판매금액: ${item.sellPrice * quantity} EP)")

        // =========================================================================
        // [DEBUG & 코드 시작]
        // =========================================================================
        player.sendMessage("§e[DEBUG] ShopItemManager - 상점 이름: $shopName")

        if (shopName == "MineralShop") {
//            player.sendMessage("§e[DEBUG] MineralShop 조건 만족! 미션 이벤트 발생.")
            Bukkit.getPluginManager().callEvent(
                MissionEvent(player, MissionVersion.V1, "TRADE", "trade_module", 1)
            )
        } else {
//            player.sendMessage("§e[DEBUG] MineralShop이 아님 ($shopName). 이벤트 패스.")
        }
        // =========================================================================

    }

    fun findShopItem(shopName: String, itemName: String): ShopItem? {
        return ShopItems.getShopItems(shopName).find { it.name == itemName }
    }

    fun getSelectedItemPrice(player: Player, isPurchase: Boolean): Int {
        val selectedItem = selectedItems[player.uniqueId] ?: return 0
        val (shopName, itemName) = selectedItem
        val item = findShopItem(shopName, itemName)
        return if (isPurchase) item?.buyPrice ?: 0 else item?.sellPrice ?: 0
    }
}
