package kr.eme.semiTradeShop.managers

import kr.eme.semiTradeShop.extensions.toBukkitItem
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

object ShopGUIManager {

    private const val ITEMS_PER_PAGE = 45 // 각 페이지에 표시할 최대 아이템 수

    fun createShopInventory(shopName: String, page: Int): Inventory? {
        val shop = ShopManager.getShop(shopName) ?: return null
        val inventory = Bukkit.createInventory(null, 54, "${shop.name} - Page $page")

        val shopItems = shop.items
        val startIndex = (page - 1) * ITEMS_PER_PAGE
        val endIndex = (page * ITEMS_PER_PAGE).coerceAtMost(shopItems.size)

        for (i in startIndex until endIndex) {
            val item = shopItems[i]
            inventory.addItem(item.toBukkitItem())
        }

        // 페이지 이동 버튼 추가
        if (page > 1) {
            val prevPageItem = ItemStack(Material.ARROW)
            val meta = prevPageItem.itemMeta
            meta?.setDisplayName("${ChatColor.GREEN}Previous Page")
            prevPageItem.itemMeta = meta
            inventory.setItem(45, prevPageItem)  // 이전 페이지 버튼
        }

        if (endIndex < shopItems.size) {
            val nextPageItem = ItemStack(Material.ARROW)
            val meta = nextPageItem.itemMeta
            meta?.setDisplayName("${ChatColor.GREEN}Next Page")
            nextPageItem.itemMeta = meta
            inventory.setItem(53, nextPageItem)  // 다음 페이지 버튼
        }
        return inventory
    }

    fun createQuantitySelectionInventory(player: Player, isPurchase: Boolean): Inventory {
        val title = if (isPurchase) "구매 수량 선택" else "판매 수량 선택"
        val inventory = Bukkit.createInventory(null, 27, title)

        // 유리판 아이템
        val glassPane = ItemStack(Material.WHITE_STAINED_GLASS_PANE)
        val glassMeta: ItemMeta? = glassPane.itemMeta
        glassMeta?.setDisplayName(" ")
        glassPane.itemMeta = glassMeta

        // 빈 칸에 유리판 설정
        for (i in 0 until 27) {
            if (i !in listOf(4, 11, 12, 13, 14, 15, 16)) {
                inventory.setItem(i, glassPane)
            }
        }

        // 플레이어 머리 아이템
        val playerHead = createButton("§6§l${player.name} 님의 머리",Material.PLAYER_HEAD, player)
        inventory.setItem(4, playerHead)

        // 추가 (1, 32, 64) 버튼
        val addButton1 = createButton("§a§l1개 추가",Material.LIME_CONCRETE)
        inventory.setItem(10, addButton1)

        val addButton2 = createButton("§a§l32개 추가",Material.LIME_CONCRETE)
        inventory.setItem(11, addButton2)

        val addButton3 = createButton("§a§l64개 추가",Material.LIME_CONCRETE)
        inventory.setItem(12, addButton3)

        val itemPrice = ShopItemManager.getSelectedItemPrice(player, isPurchase)
        val priceText = if (isPurchase) "§6§l지불액 : $itemPrice EP" else "§6§l지급액 : $itemPrice EP"
        val currentQuantity = createButton(
            "§f§l현재 선택 수량 : 1개",
            Material.GREEN_CONCRETE,
            priceText,
            "§a§l완료 하려면 클릭해주세요"
        )
        inventory.setItem(13, currentQuantity)

        // 차감 (1, 32, 64) 버튼
        val subtractButton1 = createButton("§c§l1개 차감",Material.RED_CONCRETE)
        inventory.setItem(14, subtractButton1)

        val subtractButton2 = createButton("§c§l32개 차감",Material.RED_CONCRETE)
        inventory.setItem(15, subtractButton2)

        val subtractButton3 = createButton("§c§l64개 차감",Material.RED_CONCRETE)
        inventory.setItem(16, subtractButton3)

        return inventory
    }

    fun createButton(name: String, material: Material, player: Player? = null): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta

        // 플레이어 머리일 경우 스킨 적용
        if (material == Material.PLAYER_HEAD && player != null) {
            val skullMeta = meta as? org.bukkit.inventory.meta.SkullMeta
            skullMeta?.owningPlayer = Bukkit.getOfflinePlayer(player.uniqueId) // 플레이어 스킨 적용
            skullMeta?.setDisplayName(name)
            item.itemMeta = skullMeta
        } else {
            // 일반 아이템일 경우
            meta?.setDisplayName(name)
            item.itemMeta = meta
        }

        return item
    }

    fun createButton(name: String, material: Material, vararg lore: String): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta
        meta?.setDisplayName(name)
        meta.lore = lore.toList()
        item.itemMeta = meta
        return item
    }
}