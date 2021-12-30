/*
 * Copyright (c) 2021. Wawwior
 * All Rights Reserved.
 */

package me.wawwior.core.util;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;

public class ResourceUtil {

    private String path;
    private String relPath;

    public ResourceUtil(JavaPlugin plugin) {
        relPath = "./plugins/" + plugin.getName() + "/";
        path = relPath;
    }

    public ResourceUtil withPath(String path) {
        this.path = path;
        return this;
    }

    public void copyFromJar(String source, final String path) throws URISyntaxException, IOException {

        Path target = Paths.get(this.path + path);

        URI resource = getClass().getResource("").toURI();
        FileSystem fileSystem = FileSystems.newFileSystem(
                resource,
                Collections.<String, String>emptyMap()
        );


        final Path jarPath = fileSystem.getPath(source);

        Files.walkFileTree(jarPath, new SimpleFileVisitor<Path>() {

            private Path currentTarget;

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                currentTarget = target.resolve(jarPath.relativize(dir).toString());
                Files.createDirectories(currentTarget);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.copy(file, target.resolve(jarPath.relativize(file).toString()), StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }

        });

        this.path = relPath;
    }

}
