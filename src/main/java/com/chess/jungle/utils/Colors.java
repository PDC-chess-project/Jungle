package com.chess.jungle.utils;

import java.awt.*;

/**
 * Define Common Colors
 *
 * @author Chengjie Luo
 */
public class Colors {

    public final static Color TRANSPARENT = new Color(255, 255, 255, 0);

    public final static Color LIGHT_GREEN = new Color(118, 255, 3);

    public final static Color DARK_GREEN = new Color(100, 221, 23);

    public final static Color LIGHT_GREY = new Color(236, 239, 241);

    public final static Color LIGHT_BLUE = new Color(227, 242, 253);

    public final static Color LIGHT_YELLOW = new Color(255, 248, 225);

    public final static Color LIGHT_INDIGO = new Color(232, 234, 246);

    public static Color from(Color color, int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }
}
