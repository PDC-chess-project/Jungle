package com.chess.jungle.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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
            URL url = ImageReader.class.getClassLoader().getResource(path);
            if (url == null) throw new IOException("File does not exist!");
            Image image = ImageIO.read(url);
            cache.put(path, image);
            return image;
        }
    }

    public static java.awt.Image read(String path, int width, int height) throws IOException {
        return read(path).getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
    }
}
