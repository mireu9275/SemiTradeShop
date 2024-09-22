package kr.eme.semiTradeShop.commands

import kr.eme.semiTradeShop.managers.ShopGUIManager
import kr.eme.semiTradeShop.managers.ShopManager
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
                true
            }
            val player: Player = sender as Player

            if (args.isEmpty()) {
                usage(player)
                true
            }

            val shopName = args[0]
            val shop = ShopManager.getShop(shopName)
            if (shop == null) {
                player.sendMessage("존재하지 않는 상점입니다. !$shopName")
                true
            }

            val inventory = ShopGUIManager.createShopInventory(shopName, 1)
            if (inventory == null) {
                player.sendMessage("상점을 여는 데 실패하였습니다.")
                true
            }
            player.openInventory(inventory!!)
            //정상 작동 시에는 메시지를 출력하지 않음.
            true
        } catch (ex: Exception) {
            true
        }
    }
    private fun usage(player: Player) {
        player.sendMessage("사용법: /shop <상점이름>")
    }
}