package com.chess.jungle.utils;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * A utility class to read image from specified path.
 *
 * @author Chengjie Luo
 */
public class ImageReader {

    public static java.awt.Image read(String path) throws IOException {
        return ImageIO.read(new File("src/main/java/com/chess/jungle/assets/" + path));
    }

    public static java.awt.Image read(String path, int width, int height) throws IOException {
        return ImageIO.read(new File("src/main/java/com/chess/jungle/assets/" + path)).getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
    }
}
