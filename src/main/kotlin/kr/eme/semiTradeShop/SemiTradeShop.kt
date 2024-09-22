package kr.eme.semiTradeShop

import kr.eme.semiTradeShop.commands.ShopCommand
import kr.eme.semiTradeShop.listeners.ShopGUIListener
import org.bukkit.plugin.java.JavaPlugin

class SemiTradeShop : JavaPlugin() {
    override fun onEnable() {
        this.getCommand("shop")?.setExecutor(ShopCommand)
        server.pluginManager.registerEvents(ShopGUIListener, this)
        logger.info("SemiTradeShop 플러그인이 활성화되었습니다.")
    }
    override fun onDisable() {
        logger.info("SemiTradeShop 플러그인이 비활성화되었습니다.")
    }
}