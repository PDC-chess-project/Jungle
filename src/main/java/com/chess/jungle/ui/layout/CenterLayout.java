package com.chess.jungle.ui.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

/**
 *
 * @author Chengjie Luo
 */
public class CenterLayout extends AbsoluteLayout {

    @Override
    public void layoutContainer(Container parent) {
        Insets inserts = parent.getInsets();
        Dimension parentD = parent.getSize();
        for (Component component : parent.getComponents()) {
            Dimension d = component.getPreferredSize();
            if (d != null) {
                int x = (parent.getWidth() - d.width) / 2, y = (parent.getHeight() - d.height) / 2;
                component.setBounds(x, y, d.width, d.height);
            } else {
                component.setBounds(inserts.left, inserts.top, parentD.width, parentD.height);
            }
        }
    }

}
