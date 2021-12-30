/*
 * Copyright (c) 2021. Wawwior
 * All Rights Reserved.
 */

package me.wawwior.core.pack;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import me.wawwior.core.Core;
import me.wawwior.core.core.CorePlugin;
import me.wawwior.core.item.CoreItem;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class PackLoader {

    private static List<Map.Entry<Integer, CorePlugin>> packs = new ArrayList<>();

    public static void register(CorePlugin plugin, int priority) {
        packs.add(new AbstractMap.SimpleEntry<>(priority, plugin));
        packs.sort(Comparator.comparingInt(Map.Entry::getKey));
        packs.forEach(p -> savePack(p.getValue()));
    }

    private static void savePack(CorePlugin plugin) {
        File file = new File("./plugins/pack/pack.zip");
        if (file.exists()) {
            file.delete();
        }
        try {
            plugin.getResourceUtil().withPath("./plugins/pack/").copyFromJar("/pack", "pack");
            ZipUtil.pack(new File("./plugins/pack/pack"), new File("./plugins/pack/pack.zip"));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    private static HttpServer httpServer;

    public static void start() {
        int port;
        try {
            port = Core.CORE.getYamlConfig().getInt("resources.port");
        } catch (Exception ex) {
            Core.CORE.getLogger().warning("Invalid port configured in the config.yml");
            return;
        }
        try {
            httpServer = Vertx.vertx().createHttpServer();
            httpServer.requestHandler(httpServerRequest -> httpServerRequest.response().sendFile(getFileLocation()));
            httpServer.listen(port);
        }catch(Exception ex){
            Core.CORE.getLogger().warning("Invalid port configured in the config.yml");
            ex.printStackTrace();
        }
    }

    public static void stop() {
        httpServer.close();
    }

    public static String getFileLocation() {
        return "./plugins/pack/pack.zip";
    }

}
