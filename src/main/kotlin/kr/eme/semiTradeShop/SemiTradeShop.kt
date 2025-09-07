package kr.eme.semiTradeShop

import kr.eme.semiTradeShop.commands.ShopCommand
import kr.eme.semiTradeShop.listeners.GUIListener
import kr.eme.semiTradeShop.listeners.ShopGUIListener
import org.bukkit.plugin.java.JavaPlugin

class SemiTradeShop : JavaPlugin() {
    override fun onEnable() {
        main = this
        registerCommands()
        registerEvents()
        logger.info("SemiTradeShop 플러그인이 활성화되었습니다.")
    }
    override fun onDisable() {
        logger.info("SemiTradeShop 플러그인이 비활성화되었습니다.")
    }

    private fun registerCommands() {
        getCommand("shop")?.setExecutor(ShopCommand)
    }

    private fun registerEvents() {
        server.pluginManager.registerEvents(ShopGUIListener, this)
        server.pluginManager.registerEvents(GUIListener, this)
    }
}