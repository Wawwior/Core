package me.wawwior.core.item;

import com.google.gson.Gson;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JsonRecipe {
	
	public static class ItemKey {
		
		public String item;
		public int count = 1;
		
		@Override
		public String toString() {
			return "ItemKey{" +
					"item='" + item + '\'' +
					'}';
		}
	}
	
	public String type;
	public String[] pattern;
	public List<ItemKey> ingredients;
	public Map<Character, ItemKey> key;
	public ItemKey result;
	
	@Override
	public String toString() {
		return "me.wawwior.core.item.JsonRecipe{" +
				"type='" + type + '\'' +
				", pattern=" + Arrays.toString(pattern) +
				", key=" + key +
				'}';
	}
	
	public static JsonRecipe getFromPath(String path) {
		try {
			return new Gson().fromJson(new FileReader(path), JsonRecipe.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new JsonRecipe();
	}
	
	public <T extends Recipe> T getRecipe(JavaPlugin plugin, String name) {
		
		if (result.item == null) {
			return getRecipe(plugin, name, new ItemStack(Material.STONE));
		} else {
			ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(result.item.replace("minecraft:", "").toUpperCase())));
			item.setAmount(result.count);
			return getRecipe(plugin, name, item);
		}
		
	}
	
	public <T extends Recipe> T getRecipe(JavaPlugin plugin, String name, ItemStack result) {
		
		if (type.equalsIgnoreCase("minecraft:crafting_shaped")) {
			ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, name), result);
			
			recipe.shape(pattern);
			key.forEach((c, i) -> {
				recipe.setIngredient(c, Objects.requireNonNull(Material.getMaterial(i.item.replace("minecraft:", "").toUpperCase())));
			});
			
			return (T) recipe;
			
		} else if (type.equalsIgnoreCase("minecraft:crafting_shapeless")) {
			ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(plugin, name), result);
			
			ingredients.forEach(i -> recipe.addIngredient(Objects.requireNonNull(Material.getMaterial(i.item.replace("minecraft:", "").toUpperCase()))));
			
			return (T) recipe;
		}
		
		return null;
	}
}
