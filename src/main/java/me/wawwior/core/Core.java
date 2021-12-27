/*
 * Copyright (c) 2021. Wawwior
 * All Rights Reserved.
 */

package me.wawwior.core;

import me.wawwior.config.ConfigProvider;
import me.wawwior.core.command.CommandRegistry;
import net.forthecrown.royalgrenadier.RoyalGrenadier;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Core extends JavaPlugin {

    public static Core CORE;
    public static Logger LOGGER = Bukkit.getLogger();

    public static ConfigProvider CONFIGPROVIDER;

    public static String version = "1.0-DEV";


    @Override
    public void onEnable() {

        RoyalGrenadier.initialize();

        CONFIGPROVIDER = getDefaultProvider(this);

        CORE = this;

        LOGGER.info(
                "\n\u001b[36;1m \n" +
                        " #####  #####  ####   #####\n" +
                        " ##     ## ##  #####  #####\n" +
                        " #####  #####  ##  #  #####\n" +
                        "\n" +
                        " - Wawwior's Backend Core\n"
        );

        CommandRegistry.registerDefaults();

        CONFIGPROVIDER.load();

    }

    @Override
    public void onDisable() {
        CONFIGPROVIDER.save();
    }

    public static ConfigProvider getDefaultProvider(JavaPlugin plugin) {
        return new ConfigProvider("./plugins/" + plugin.getName());
    }

    public static void enable(String name) {

    }

    public static void disable(String name) {

    }
}
