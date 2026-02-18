package kr.eme.prcShop

import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

lateinit var main: JavaPlugin
fun getNamespacedKey(key: String): NamespacedKey = NamespacedKey(main, key)