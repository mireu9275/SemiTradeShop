package kr.eme.prcShop

import kr.eme.prcShop.commands.ShopCommand
import kr.eme.prcShop.listeners.GUIListener
import kr.eme.prcShop.listeners.ShopGUIListener
import org.bukkit.plugin.java.JavaPlugin

class PRCShop : JavaPlugin() {
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