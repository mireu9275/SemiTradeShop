package kr.eme.semiTradeShop.listeners

import kr.eme.semiTradeShop.managers.ShopGUIManager
import kr.eme.semiTradeShop.managers.ShopItemManager
import kr.eme.semiTradeShop.managers.ShopItemManager.getSelectedItemPrice
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

object ShopGUIListener : Listener {

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val player = event.whoClicked as? Player ?: return
        val inventory = event.clickedInventory ?: return
        val itemStack = event.currentItem ?: return

        // 플레이어의 인벤토리를 클릭한 경우 무시
        if (inventory == player.inventory) {
            return
        }

        val title = event.view.title
        val itemDisplayName = itemStack.itemMeta?.displayName ?: return

        // 상점 페이지 이동 처리
        if (title.contains("Page") && (itemDisplayName.contains("이전 페이지로") || itemDisplayName.contains("다음 페이지로")) ) {
            handleShopNavigationClick(player, event)
            return
        }

        // 수량 선택 guis 처리
        if (title == "구매 수량 선택" || title == "판매 수량 선택") {
            handleQuantitySelectionClick(player, itemStack, title == "구매 수량 선택")
            event.isCancelled = true
            return
        }

        // 상점 아이템 클릭 시 구매/판매 수량 선택으로 이동
        if (title.startsWith("MineralShop") || title.startsWith("CropShop") || title.startsWith("OtherShop")) {
            handleCustomTradeOrMoneyClick(player, event)
            return
        }
    }

    private fun handleCustomTradeOrMoneyClick(player: Player, event: InventoryClickEvent) {
        val itemStack = event.currentItem ?: return
        val shopName = event.view.title.split(" - ")[0]
        val itemName = itemStack.itemMeta?.displayName ?: return

        val shop = kr.eme.semiTradeShop.managers.ShopManager.getShop(shopName) ?: return
        val clickedItem = shop.items.find { it.name == itemName } ?: return

        if (clickedItem.tradeRequirements.isNotEmpty()) {
            val playerInventory = player.inventory
            val missingRequirement = clickedItem.tradeRequirements.firstOrNull { req ->
                val matchCount = playerInventory.contents.filterNotNull().count {
                    it.type == req.material &&
                            it.itemMeta?.customModelData == req.customModelData
                }
                matchCount < req.amount
            }

            if (missingRequirement != null) {
                player.sendMessage("§c[교환 실패] 필요한 아이템이 부족합니다.")
                event.isCancelled = true
                return
            }

            // 아이템 제거
            clickedItem.tradeRequirements.forEach { req ->
                var toRemove = req.amount
                val contents = playerInventory.contents
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
            val result = ItemStack(clickedItem.material).apply {
                amount = 1
                itemMeta = itemMeta?.apply {
                    setDisplayName(clickedItem.name)
                    if (clickedItem.customModelData != null)
                        setCustomModelData(clickedItem.customModelData)
                }
            }
            player.inventory.addItem(result)
            player.sendMessage("§a[교환 성공] ${clickedItem.name} 을 획득했습니다.")
            event.isCancelled = true
            return
        }

        // 돈 기반인 경우 기존 로직 수행
        handleShopItemClick(player, event)
    }


    private fun handleShopNavigationClick(player: Player, event: InventoryClickEvent) {
        val itemStack = event.currentItem ?: return
        val titleParts = event.view.title.split(" - ")
        val shopName = titleParts[0]
        val currentPage = titleParts.getOrNull(1)?.substringAfter("Page ")?.toIntOrNull() ?: 1

        if (itemStack.type == Material.ARROW) {
            val displayName = itemStack.itemMeta.displayName
            when (displayName) {
                "§f§l이전 페이지로" -> {
                    ShopGUIManager.createShopInventory(shopName, currentPage - 1)?.let { inventory ->
                        player.openInventory(inventory)
                    }
                }

                "$§f§l다음 페이지로" -> {
                    ShopGUIManager.createShopInventory(shopName, currentPage + 1)?.let { inventory ->
                        player.openInventory(inventory)
                    }
                }
            }
        }
        event.isCancelled = true
    }

    private fun handleShopItemClick(player: Player, event: InventoryClickEvent) {
        val itemStack = event.currentItem ?: return
        val shopName = event.view.title.split(" - ")[0]
        val itemName = itemStack.itemMeta?.displayName ?: return

        when (event.click) {
            ClickType.LEFT -> {
                // 구매 수량 선택 guis 열기
                ShopItemManager.selectedItems[player.uniqueId] = Pair(shopName, itemName)
                val purchaseInventory = ShopGUIManager.createQuantitySelectionInventory(player, true)
                player.openInventory(purchaseInventory)
            }
            ClickType.RIGHT -> {
                // 판매 수량 선택 guis 열기
                ShopItemManager.selectedItems[player.uniqueId] = Pair(shopName, itemName)
                val sellInventory = ShopGUIManager.createQuantitySelectionInventory(player, false)
                player.openInventory(sellInventory)
            }
            else -> {

            }
        }
        event.isCancelled = true
    }

    fun handleQuantitySelectionClick(player: Player, clickedItem: ItemStack, isPurchase: Boolean) {
        var quantity = getQuantityFromInventory(player.openInventory.topInventory)
        val itemPrice = getSelectedItemPrice(player, isPurchase) // 선택된 아이템의 가격을 가져오는 함수

        when (clickedItem.itemMeta?.displayName) {
            "§a§l1개 추가" -> quantity += 1
            "§a§l32개 추가" -> quantity += 32
            "§a§l64개 추가" -> quantity += 64
            "§c§l1개 차감" -> quantity = (quantity - 1).coerceAtLeast(1)
            "§c§l32개 차감" -> quantity = (quantity - 32).coerceAtLeast(1)
            "§c§l64개 차감" -> quantity = (quantity - 64).coerceAtLeast(1)
            "§f§l현재 선택 수량 : ${quantity}개" -> {
                // 수량 선택 완료 처리
                val selectedItem = ShopItemManager.selectedItems[player.uniqueId] ?: return
                val (shopName, itemName) = selectedItem

                if (isPurchase) {
                    ShopItemManager.purchaseItem(player, shopName, itemName, quantity)
                } else {
                    ShopItemManager.sellItem(player, shopName, itemName, quantity)
                }
                player.closeInventory()
            }
        }

        // 선택된 수량과 가격 업데이트
        updateQuantityDisplay(player.openInventory.topInventory, quantity, itemPrice, isPurchase)
    }

    fun updateQuantityDisplay(inventory: Inventory, quantity: Int, itemPrice: Int, isPurchase: Boolean) {
        val currentQuantityItem = inventory.getItem(13) ?: return
        val meta = currentQuantityItem.itemMeta
        val totalPrice = itemPrice * quantity
        val priceText = if (isPurchase) "§6§l지불액 : $totalPrice EP" else "§6§l지급액 : $totalPrice EP"

        meta?.setDisplayName("§f§l현재 선택 수량 : ${quantity}개")
        meta?.lore = listOf(priceText, "§a§l완료 하려면 클릭해주세요")
        currentQuantityItem.itemMeta = meta
        inventory.setItem(13, currentQuantityItem)
    }


    private fun getQuantityFromInventory(inventory: org.bukkit.inventory.Inventory): Int {
        val currentQuantityItem = inventory.getItem(13) ?: return 1
        val displayName = currentQuantityItem.itemMeta?.displayName ?: return 1
        val regex = Regex("""§f§l현재 선택 수량 : (\d+)개""")
        val match = regex.find(displayName) ?: return 1
        return match.groups[1]?.value?.toIntOrNull() ?: 1
    }
}