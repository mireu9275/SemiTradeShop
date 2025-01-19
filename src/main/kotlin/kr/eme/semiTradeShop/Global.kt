package kr.eme.semiTradeShop

import kr.eme.semiMoney.SemiMoney
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

internal lateinit var main: JavaPlugin
internal lateinit var semiMoney: SemiMoney
internal fun getNamespacedKey(key: String): NamespacedKey = NamespacedKey(main, key)