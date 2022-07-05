package me.wawwior.core.core;

import me.wawwior.config.ConfigProvider;
import me.wawwior.config.Configurable;
import me.wawwior.config.IConfig;
import me.wawwior.config.io.AdapterInfo;
import me.wawwior.config.io.impl.FileInfo;
import me.wawwior.config.io.impl.JsonFileAdapter;
import me.wawwior.core.item.ItemFactory;
import me.wawwior.core.util.ResourceUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CorePlugin extends JavaPlugin {
	
	protected ConfigProvider<FileInfo> configProvider = new ConfigProvider<>(new JsonFileAdapter("./plugins/" + this.getName()), false);
	
	protected List<Configurable<? extends IConfig, ? extends AdapterInfo>> configurables = new ArrayList<>();
	
	protected ItemFactory itemFactory = new ItemFactory(this);
	
	protected ResourceUtil resourceUtil = new ResourceUtil(this);
	
	@Override
	public final void onEnable() {
		
		load();
		
		saveDefaultConfig();
		
		configurables.forEach(Configurable::load);
		
		enable();
	}
	
	@Override
	public final void onDisable() {
		disable();
		configurables.forEach(Configurable::save);
	}
	
	protected void enable() {
	}
	
	protected void load() {
	}
	
	protected void disable() {
	}
	
	public String version() {
		return "1.3.7-DEV";
	}
	
	public ConfigProvider<FileInfo> getConfigProvider() {
		return configProvider;
	}
	
	public FileConfiguration getYamlConfig() {
		return getConfig();
	}
	
	@Override
	public @NotNull FileConfiguration getConfig() {
		return super.getConfig();
	}
	
	public ResourceUtil getResourceUtil() {
		return resourceUtil;
	}
}
