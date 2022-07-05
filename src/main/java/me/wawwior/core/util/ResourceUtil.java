package me.wawwior.core.util;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

public class ResourceUtil {
	
	private String path;
	private final String relPath;
	
	public ResourceUtil(JavaPlugin plugin) {
		relPath = "./plugins/" + plugin.getName() + "/";
		path = relPath;
	}
	
	public ResourceUtil(String path) {
		relPath = path;
		this.path = path;
	}
	
	public ResourceUtil withPath(String path) {
		this.path = path;
		return this;
	}
	
	// Copy sub-directory from current jar to work directory
	
	public void copyFromJar(String subDir, String toDir) throws URISyntaxException, IOException {
		URI uri = Objects.requireNonNull(getClass().getClassLoader().getResource(subDir)).toURI();
		Path source = Paths.get(uri);
		Path target = Paths.get(path + toDir);
		
		
		Files.walkFileTree(source, new SimpleFileVisitor<>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.copy(file, target.resolve(source.relativize(file.getParent())), StandardCopyOption.REPLACE_EXISTING);
				return FileVisitResult.CONTINUE;
			}
			
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				Files.copy(dir, target.resolve(source.relativize(dir.getParent())), StandardCopyOption.REPLACE_EXISTING);
				return FileVisitResult.CONTINUE;
			}
		});
	}
	
}
