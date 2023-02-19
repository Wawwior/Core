package me.wawwior.core.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginUtil {
	
	public void disable(JavaPlugin plugin) {
		Bukkit.getPluginManager().disablePlugin(plugin);
	}
	
	public void enable(JavaPlugin plugin) {
		Bukkit.getPluginManager().enablePlugin(plugin);
	}
	
	public void reload(JavaPlugin plugin) {
		disable(plugin);
		enable(plugin);
	}
	
}
