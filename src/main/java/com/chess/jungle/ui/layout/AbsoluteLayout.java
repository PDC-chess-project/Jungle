package com.chess.jungle.ui.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

/**
 *
 * @author Chengjie Luo
 */
public class AbsoluteLayout implements LayoutManager {

    @Override
    public void addLayoutComponent(String name, Component comp) {
    }

    @Override
    public void removeLayoutComponent(Component comp) {
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return parent.getSize();
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return preferredLayoutSize(parent);
    }

    @Override
    public void layoutContainer(Container parent) {
        Insets inserts = parent.getInsets();
        Dimension parentD = parent.getSize();
        for (Component component : parent.getComponents()) {
            Dimension d = component.getPreferredSize();
            if (d != null) {
                component.setBounds(inserts.left, inserts.top, d.width, d.height);
            } else {
                component.setBounds(inserts.left, inserts.top, parentD.width, parentD.height);
            }
        }
    }
}
