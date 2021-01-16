package com.chess.jungle.utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * A utility class to read image from specified path.
 *
 * @author Chengjie Luo
 */
public class ImageReader {

    private final static Map<String, Image> cache = new HashMap<>();

    public static java.awt.Image read(String path) throws IOException {
        if (cache.containsKey(path))
            return cache.get(path);
        else {
            Image image = ImageIO.read(new File("src/main/java/com/chess/jungle/assets/" + path));
            cache.put(path, image);
            return image;
        }
    }

    public static java.awt.Image read(String path, int width, int height) throws IOException {
        return read(path).getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
    }
}
