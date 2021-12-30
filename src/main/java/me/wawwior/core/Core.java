/*
 * Copyright (c) 2021. Wawwior
 * All Rights Reserved.
 */

package me.wawwior.core;

import me.wawwior.config.ConfigProvider;
import me.wawwior.core.command.CommandRegistry;
import me.wawwior.core.core.CorePlugin;
import me.wawwior.core.event.ResourceEventHandler;
import me.wawwior.core.pack.PackLoader;
import net.forthecrown.royalgrenadier.RoyalGrenadier;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends CorePlugin {

    public static Core CORE;

    @Override
    protected void load() {
        CORE = this;

        PackLoader.start();

        RoyalGrenadier.initialize();

        Bukkit.getPluginManager().registerEvents(new ResourceEventHandler(), this);

        getLogger().info(
                "\n\u001b[36;1m \n" +
                        " #####  #####  ####   #####\n" +
                        " ##     ## ##  #####  #####\n" +
                        " #####  #####  ##  #  #####\n" +
                        "\n" +
                        " - Wawwior's Backend Core\n"
        );
    }

    @Override
    protected void disable() {
        PackLoader.stop();
    }
}
