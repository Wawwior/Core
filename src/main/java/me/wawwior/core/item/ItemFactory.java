package me.wawwior.core.item;

import me.wawwior.core.core.CorePlugin;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import static me.wawwior.core.util.NameUtil.identifier;

public class ItemFactory {
	
	CorePlugin plugin;
	
	NamespacedKey idKey;
	
	public ItemFactory(CorePlugin plugin) {
		this.plugin = plugin;
		idKey = new NamespacedKey(plugin, "id");
	}
	
	public CoreItem make(String name, ItemStack itemStack) {
		return new CoreItem(identifier(name), itemStack, idKey);
	}
}
