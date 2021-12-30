/*
 * Copyright (c) 2021. Wawwior
 * All Rights Reserved.
 */

package me.wawwior.core.core;

import me.wawwior.config.ConfigProvider;
import me.wawwior.core.item.ItemFactory;
import me.wawwior.core.util.ResourceUtil;
import net.forthecrown.grenadier.command.AbstractCommand;
import net.forthecrown.grenadier.command.BrigadierCommand;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class CorePlugin extends JavaPlugin {

    protected ConfigProvider configProvider = new ConfigProvider("./plugins/" + this.getName());

    protected ItemFactory itemFactory = new ItemFactory(this);

    protected ResourceUtil resourceUtil = new ResourceUtil(this);

    @Override
    public final void onEnable() {
        load();
        saveDefaultConfig();
        configProvider.load();
        enable();
        new AbstractCommand(this.getName(), this) {
            @Override
            protected void createCommand(BrigadierCommand builder) {
                builder.executes(c -> {
                    c.getSource().sendMessage(ChatColor.GRAY + "§oRunning " + this.getName() + " " + version());
                    return 1;
                });
            }
        }.register();
    }

    @Override
    public final void onDisable() {
        disable();
        configProvider.save();
    }

    protected void enable() {
    }

    protected void load() {
    }

    protected void disable() {
    }

    public String version() {
        return "1.2-DEV";
    }

    public ConfigProvider getConfigProvider() {
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
