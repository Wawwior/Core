package me.wawwior.core.util;

public class NameUtil {

    public static String identifier(String name) {
        return name.toLowerCase().replaceAll("[^a-z]", "_");
    }

}
