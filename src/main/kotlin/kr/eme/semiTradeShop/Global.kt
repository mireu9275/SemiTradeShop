package kr.eme.semiTradeShop

import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

internal lateinit var main: JavaPlugin
internal fun getNamespacedKey(key: String): NamespacedKey = NamespacedKey(main, key)