package kr.eme.prcShop.objects.guis

import kr.eme.prcShop.api.PRCItems
import kr.eme.prcShop.managers.GUIManager
import kr.eme.prcShop.utils.SoundUtil
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.inventory.ItemStack

/**
 * 관리자용 PRCItems 전체 목록 GUI.
 *
 * - 상단 5행 (45칸): 아이템 목록
 * - 하단 1행: 네비게이션 (이전/닫기/다음)
 *
 * 클릭 동작:
 * - 좌클릭: 1개 지급
 * - 쉬프트+좌클릭: 한 스택 지급 (maxStackSize)
 */
class AdminItemGUI(
    player: Player,
    val page: Int = 0
) : GUI(player, "§c[관리자] PRC 아이템 목록 §7(페이지 ${page + 1})", 6) {

    companion object {
        const val PER_PAGE = 45
        const val PREV_SLOT = 48
        const val CLOSE_SLOT = 49
        const val NEXT_SLOT = 50
    }

    private fun totalPages(): Int {
        val size = PRCItems.all.size
        if (size == 0) return 1
        return (size + PER_PAGE - 1) / PER_PAGE
    }

    override fun setFirstGUI() {
        val items = PRCItems.all
        val start = page * PER_PAGE
        val end = (start + PER_PAGE).coerceAtMost(items.size)

        for (i in start until end) {
            val slot = i - start
            setItem(slot, items[i].create(1))
        }

        val pages = totalPages()
        if (page > 0) setItem(PREV_SLOT, navItem(Material.ARROW, "§f이전 페이지"))
        setItem(CLOSE_SLOT, navItem(Material.BARRIER, "§c닫기"))
        if (page < pages - 1) setItem(NEXT_SLOT, navItem(Material.ARROW, "§f다음 페이지"))
    }

    private fun navItem(material: Material, name: String): ItemStack {
        val stack = ItemStack(material)
        val meta = stack.itemMeta
        meta.setDisplayName(name)
        stack.itemMeta = meta
        return stack
    }

    override fun InventoryClickEvent.clickEvent() {
        isCancelled = true

        // 플레이어 인벤토리 클릭은 무시
        if (rawSlot >= size) return

        when (rawSlot) {
            PREV_SLOT -> {
                if (page > 0) openPage(page - 1)
                return
            }
            NEXT_SLOT -> {
                if (page < totalPages() - 1) openPage(page + 1)
                return
            }
            CLOSE_SLOT -> {
                close()
                SoundUtil.click(player)
                return
            }
        }

        // 아이템 영역 클릭 → 지급
        if (rawSlot >= PER_PAGE) return
        val clicked = currentItem ?: return
        if (clicked.type == Material.AIR) return

        val maxStack = clicked.maxStackSize
        val amount = when {
            isShiftClick && isLeftClick -> maxStack
            isLeftClick -> 1
            else -> return
        }

        val give = clicked.clone().also { it.amount = amount }
        val leftover = player.inventory.addItem(give)
        if (leftover.isNotEmpty()) {
            leftover.values.forEach { player.world.dropItemNaturally(player.location, it) }
        }
        SoundUtil.click(player)
    }

    private fun openPage(p: Int) {
        val next = AdminItemGUI(player, p)
        next.setFirstGUI()
        GUIManager.setGUI(player.uniqueId, next)
        next.open()
        SoundUtil.click(player)
    }

    override fun InventoryDragEvent.dragEvent() {
        isCancelled = true
    }

    override fun InventoryCloseEvent.closeEvent() {}
}
