package me.wawwior.core.util;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.logging.Level;

public class ResourceUtil {
	
	private String path;
    private final JavaPlugin plugin;


    public ResourceUtil(JavaPlugin plugin) {
        this.plugin = plugin;
        path = "./plugins/" + plugin.getName() + "/";
	}
	
	public ResourceUtil withPath(String path) {
		this.path = path;
		return this;
	}
	
	// Copy subdirectory from current jar to work directory
	public void copyFromJar(String dir, String toDir, boolean replace) {

        if (dir == null || dir.equals("")) {
            throw new IllegalArgumentException("dir cannot be null or empty");
        }

        dir = dir.replace('\\', '/');
        InputStream in = plugin.getResource(dir);
        if (in == null) {
            throw new IllegalArgumentException("The embedded resource '" + dir + "' cannot be found in jar");
        }

        File outFile = new File(path, toDir);
        int lastIndex = toDir.lastIndexOf('/');
        File outDir = new File(path, toDir.substring(0, Math.max(lastIndex, 0)));

        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        try {
            if (!outFile.exists() || replace) {
                OutputStream out = new FileOutputStream(outFile);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.close();
                in.close();
            } else {
                plugin.getLogger().log(Level.WARNING, "Could not save " + outFile.getName() + " to " + outFile + " because " + outFile.getName() + " already exists.");
            }
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not save " + outFile.getName() + " to " + outFile, ex);
        }

    }
	
}
