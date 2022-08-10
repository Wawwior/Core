package me.wawwior.core.item;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class CoreItem {
	
	private final ItemStack item;
	
	private final NamespacedKey idKey;
	
	private final String id;
	
	CoreItem(String identifier, ItemStack item, NamespacedKey key) {
		this.id = identifier;
		this.idKey = key;
		ItemMeta meta = item.getItemMeta();
		meta.getPersistentDataContainer().set(idKey, PersistentDataType.STRING, identifier);
		item.setItemMeta(meta);
		this.item = item.asOne();
	}
	
	public boolean equals(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		try {
			return Objects.requireNonNull(meta.getPersistentDataContainer().get(idKey, PersistentDataType.STRING)).equalsIgnoreCase(id);
		} catch (NullPointerException e) {
			return false;
		}
	}
	
	public ItemStack getItem() {
		return item.clone();
	}
}
