package kr.eme.semiTradeShop

import kr.eme.semiMoney.SemiMoney
import kr.eme.semiTradeShop.commands.ShopCommand
import kr.eme.semiTradeShop.listeners.GUIListener
import kr.eme.semiTradeShop.listeners.ShopGUIListener
import org.bukkit.plugin.java.JavaPlugin

class SemiTradeShop : JavaPlugin() {
    override fun onEnable() {
        main = this
        val semiMoneyPlugin = server.pluginManager.getPlugin("SemiMoney")
        if (semiMoneyPlugin != null && semiMoneyPlugin is SemiMoney) {
            semiMoney = semiMoneyPlugin
            logger.info("정상적으로 SemiMoney 플러그인을 불러왔습니다. : SemiTradeShop")
        } else {
            logger.info("SemiMoney 플러그인을 발견하지 못하여 플러그인을 비활성화 합니다.")
            server.pluginManager.disablePlugin(this)
            return
        }
        this.getCommand("shop")?.setExecutor(ShopCommand)
        server.pluginManager.registerEvents(ShopGUIListener, this)
        server.pluginManager.registerEvents(GUIListener, this)
        logger.info("SemiTradeShop 플러그인이 활성화되었습니다.")
    }
    override fun onDisable() {
        logger.info("SemiTradeShop 플러그인이 비활성화되었습니다.")
    }
}