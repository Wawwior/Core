package me.wawwior.core.item;

import me.wawwior.core.core.CorePlugin;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class ItemFactory {
	
	CorePlugin plugin;
	
	NamespacedKey idKey;
	
	public ItemFactory(CorePlugin plugin) {
		this.plugin = plugin;
		idKey = new NamespacedKey(plugin, "id");
	}
	
	public CoreItem make(String id, ItemStack itemStack) {
		return new CoreItem(identifier(id), itemStack, idKey);
	}
	
	private String identifier(String name) {
		return name.replaceAll("[^a-z]", "_");
	}
	
}
