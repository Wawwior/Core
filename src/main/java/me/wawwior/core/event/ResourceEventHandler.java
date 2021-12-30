/*
 * Copyright (c) 2021. Wawwior
 * All Rights Reserved.
 */

package me.wawwior.core.event;

import me.wawwior.core.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ResourceEventHandler implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        try {
            String  ip = InetAddress.getLocalHost().getHostAddress();
            event.getPlayer().setResourcePack("http://" + ip + ":" + Core.CORE.getYamlConfig().getInt("resources.port"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
