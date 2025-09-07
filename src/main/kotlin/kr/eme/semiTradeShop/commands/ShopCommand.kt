package kr.eme.semiTradeShop.commands

import kr.eme.semiMission.objects.events.MissionEvent
import kr.eme.semiTradeShop.managers.GUIManager
import kr.eme.semiTradeShop.managers.ShopGUIManager
import kr.eme.semiTradeShop.managers.ShopManager
import kr.eme.semiTradeShop.objects.guis.InitShopGUI
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object ShopCommand : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        return try {
            if (sender !is Player) {
                sender.sendMessage("이 명령어는 플레이어만 사용할 수 있습니다.")
                return true
            }
            val player: Player = sender

            if (args.isEmpty()) { // /shop 만 입력
                val initShopGUI = InitShopGUI(player)
                initShopGUI.setFirstGUI()
                GUIManager.setGUI(player.uniqueId, initShopGUI)
                initShopGUI.open()
                return true
            }

            // ✅ 테스트 명령어: /shop debug <type> <target> [need]
            if (args[0].equals("debug", ignoreCase = true)) {
                if (args.size < 3) {
                    player.sendMessage("§c사용법: /shop debug <type> <target> [need]")
                    return true
                }

                val type = args[1]
                val target = args[2]
                val need = if (args.size >= 4) args[3].toIntOrNull() ?: 1 else 1

                Bukkit.getPluginManager().callEvent(
                    MissionEvent(player, type, target, need)
                )

                player.sendMessage("§a[디버그] MissionEvent(type=$type, target=$target, need=$need) 이벤트를 보냈습니다.")
                return true
            }

            if (!player.isOp) {
                return true
            }

            val shopName = args[0]
            val shop = ShopManager.getShop(shopName)
            if (shop == null) {
                player.sendMessage("존재하지 않는 상점입니다. !$shopName")
                return true
            }

            val inventory = ShopGUIManager.createShopInventory(shopName, 1)
            if (inventory == null) {
                player.sendMessage("상점을 여는 데 실패하였습니다.")
                return true
            }
            player.openInventory(inventory)
            true
        } catch (ex: Exception) {
            true
        }
    }

    private fun usage(player: Player) {
        player.sendMessage("사용법: /shop <상점이름>")
    }
}
