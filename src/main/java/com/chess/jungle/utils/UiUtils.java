package com.chess.jungle.utils;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

/**
 * @author Chengjie Luo
 */
public class UiUtils {

    public static void setGlobalFont(Font font) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, font.deriveFont(((float) ((Font) UIManager.get(key)).getSize())));
            }
        }
    }

    public static void setSystemLookAndFeel() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
}
