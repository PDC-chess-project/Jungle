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
public class CustomLayout implements LayoutManager {

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    Orientation orientation = Orientation.HORIZONTAL;

    public CustomLayout() {
    }

    public CustomLayout(Orientation orientation) {
        this.orientation = orientation;
    }

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

    protected Dimension getMinimumSize(Container parent) {
        int minWidth = 0;
        int minHeight = 0;
        for (Component component : parent.getComponents()) {
            Dimension d = component.getPreferredSize();
            if (d != null) {
                if (orientation == Orientation.HORIZONTAL) {
                    minWidth += d.width;
                    if (minHeight < d.height) {
                        minHeight = d.height;
                    }
                } else {
                    minHeight += d.height;
                    if (minWidth < d.width) {
                        minWidth = d.width;
                    }
                }
            }
        }
        return new Dimension(minWidth, minHeight);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        Dimension d = getMinimumSize(parent);
        Insets insets = parent.getInsets();
        return new Dimension(d.width + insets.left + insets.right, d.height + insets.top + insets.bottom);
    }

    @Override
    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();
        int currentPosition;

        int slice;
        if (orientation == Orientation.HORIZONTAL) {
            currentPosition = insets.left;
            slice = parent.getWidth() - getMinimumSize(parent).width;
        } else {
            currentPosition = insets.top;
            slice = parent.getHeight() - getMinimumSize(parent).height;
        }
        int nonFixedComponentsCount = 0;
        for (Component component : parent.getComponents()) {
            if (component.getPreferredSize() == null) {
                nonFixedComponentsCount++;
            }
        }
        slice /= nonFixedComponentsCount == 0 ? 1 : nonFixedComponentsCount;
        if (slice < 0) {
            slice = 0;
        }

        for (Component component : parent.getComponents()) {
            int x = insets.left, y = insets.top, width = parent.getWidth(), height = parent.getHeight();
            Dimension d = component.getPreferredSize();
            if (orientation == Orientation.HORIZONTAL) {
                width = d != null ? d.width : slice;
                x = currentPosition;
                currentPosition += width;
            } else {
                height = d != null ? d.height : slice;
                y = currentPosition;
                currentPosition += height;
            }
            component.setBounds(x, y, width, height);
        }
    }

}
