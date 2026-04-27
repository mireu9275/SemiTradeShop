package kr.eme.prcShop.commands

import kr.eme.prcMission.api.MissionAPI
import kr.eme.prcMission.api.events.MissionEvent
import kr.eme.prcMission.enums.MissionVersion
import kr.eme.prcMission.objects.const.MissionTargets
import kr.eme.prcMission.objects.const.MissionTypes
import kr.eme.prcShop.managers.GUIManager
import kr.eme.prcShop.managers.ShopGUIManager
import kr.eme.prcShop.managers.ShopManager
import kr.eme.prcShop.objects.guis.AdminItemGUI
import kr.eme.prcShop.objects.guis.InitShopGUI
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

object ShopCommand : TabExecutor {

    // 🔹 const val 전용 리플렉션
    private fun getConstList(obj: Any): List<String> {
        return obj::class.java.declaredFields.mapNotNull { field ->
            try {
                if (java.lang.reflect.Modifier.isStatic(field.modifiers)) {
                    field.isAccessible = true
                    field.get(null) as? String
                } else null
            } catch (_: Exception) {
                null
            }
        }
    }

    private val TYPE_CANDIDATES by lazy { getConstList(MissionTypes) }
    private val TARGET_CANDIDATES by lazy { getConstList(MissionTargets) }

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (sender !is Player) {
            sender.sendMessage("이 명령어는 플레이어만 사용할 수 있습니다.")
            return true
        }
        val player: Player = sender

        if (args.isEmpty()) {
            val initShopGUI = InitShopGUI(player)
            initShopGUI.setFirstGUI()
            GUIManager.setGUI(player.uniqueId, initShopGUI)
            initShopGUI.open()
            return true
        }

        // ✅ 관리자 아이템 GUI
        if (args[0].equals("admin", ignoreCase = true)) {
            if (!player.isOp) {
                player.sendMessage("§c권한이 없습니다.")
                return true
            }
            val adminGui = AdminItemGUI(player)
            adminGui.setFirstGUI()
            GUIManager.setGUI(player.uniqueId, adminGui)
            adminGui.open()
            return true
        }

        // ✅ MissionAPI 디버그 (현재 미션 조회 / 시작)
        if (args[0].equals("debugapi", ignoreCase = true)) {
            if (args.size < 2) {
                player.sendMessage("§c사용법: /shop debugapi <current|start> [v1|v2]")
                return true
            }

            when (args[1].lowercase()) {
                "current" -> {
                    val active = MissionAPI.getActiveMission()
                    if (active == null) {
                        player.sendMessage("§7[API] 활성 미션 없음 (모두 완료 또는 미수락)")
                    } else {
                        player.sendMessage("§a[API] 활성 미션: ${active.first.name} #${active.second}")
                    }
                    for (v in MissionVersion.entries) {
                        val num = MissionAPI.getCurrentMissionNumber(v)
                        val status = when (num) {
                            0 -> "미수락"
                            -1 -> "완료"
                            else -> "미션 #$num 진행 중"
                        }
                        player.sendMessage("§7  ${v.name}: $status")
                    }
                }
                "start" -> {
                    val versionArg = args.getOrNull(2)?.uppercase() ?: "V1"
                    val missionVersion = runCatching { MissionVersion.valueOf(versionArg) }.getOrNull()
                    if (missionVersion == null) {
                        player.sendMessage("§c잘못된 버전: $versionArg (V1 또는 V2)")
                        return true
                    }
                    val started = MissionAPI.startMission(missionVersion)
                    if (started) {
                        player.sendMessage("§a[API] ${missionVersion.name} 첫 미션 시작됨")
                    } else {
                        player.sendMessage("§c[API] ${missionVersion.name} 시작 실패 (이미 시작됐거나 이전 버전 미클리어)")
                    }
                }
                else -> player.sendMessage("§c사용법: /shop debugapi <current|start> [v1|v2]")
            }
            return true
        }

        // ✅ 디버그 명령어
        if (args[0].equals("debug", ignoreCase = true)) {
            if (args.size < 4) {
                player.sendMessage("§c사용법: /shop debug <version[1,2]> <type> <target> [value]")
                return true
            }

            val version = args[1]
            val type = args[2]
            val target = args[3]
            val value = if (args.size >= 5) args[4].toIntOrNull() ?: 1 else 1
            val missionVersion = when (version) {
                "1" -> MissionVersion.V1
                "2" -> MissionVersion.V2
                else -> {
                    player.sendMessage("§c잘못된 버전: $version (1 또는 2만 가능)")
                    return true
                }
            }

            Bukkit.getPluginManager().callEvent(
                MissionEvent(player, missionVersion, type, target, value)
            )
            player.sendMessage("§a[디버그] MissionEvent(version=$version, type=$type, target=$target, value=$value) 이벤트를 보냈습니다.")
            return true
        }

        // 일반 상점 열기
        if (!player.isOp) return true

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
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> {
        if (sender !is Player || !sender.isOp) return mutableListOf()

        if (args.size == 1) {
            return listOf("debug", "debugapi", "admin").filter { it.startsWith(args[0], ignoreCase = true) }.toMutableList()
        }

        if (args[0].equals("debugapi", ignoreCase = true)) {
            return when (args.size) {
                2 -> listOf("current", "start").filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
                3 -> if (args[1].equals("start", ignoreCase = true))
                    listOf("v1", "v2").filter { it.startsWith(args[2], ignoreCase = true) }.toMutableList()
                else mutableListOf()
                else -> mutableListOf()
            }
        }

        if (args[0].equals("debug", ignoreCase = true)) {
            return when (args.size) {
                2 -> listOf("1", "2").filter { it.startsWith(args[1]) }.toMutableList()
                3 -> TYPE_CANDIDATES.filter { it.startsWith(args[2], ignoreCase = true) }.toMutableList()
                4 -> TARGET_CANDIDATES.filter { it.startsWith(args[3], ignoreCase = true) }.toMutableList()
                5 -> (1..64).map { it.toString() }
                    .filter { it.startsWith(args[4]) }
                    .toMutableList()
                else -> mutableListOf()
            }
        }

        return mutableListOf()
    }
}
