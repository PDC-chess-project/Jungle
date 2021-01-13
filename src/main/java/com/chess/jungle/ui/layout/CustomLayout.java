package com.chess.jungle.ui.layout;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Chengjie Luo
 */
public class CustomLayout implements LayoutManager2 {

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    public enum Constraints {
        ABSOLUTE,
        ABSOLUTE_CENTER
    }

    private Orientation orientation = Orientation.HORIZONTAL;

    private final Map<Component, Constraints> constrainedComponentList = new HashMap<>();

    public CustomLayout() {
    }

    public CustomLayout(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        if (constraints != null) constrainedComponentList.put(comp, (Constraints) constraints);
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return target.getSize();
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0;
    }

    @Override
    public void invalidateLayout(Container target) {
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        constrainedComponentList.remove(comp);
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return parent.getSize();
    }

    protected Dimension getMinimumSize(Container parent) {
        int minWidth = 0;
        int minHeight = 0;
        for (Component component : parent.getComponents()) {
            if (constrainedComponentList.containsKey(component)) continue;
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

        // Calculate remaining space
        int slice;
        Dimension minDimension = getMinimumSize(parent);
        if (orientation == Orientation.HORIZONTAL) {
            currentPosition = insets.left;
            slice = parent.getWidth() - minDimension.width;
        } else {
            currentPosition = insets.top;
            slice = parent.getHeight() - minDimension.height;
        }
        int nonFixedComponentsCount = 0;
        for (Component component : parent.getComponents()) {
            if (constrainedComponentList.containsKey(component)) continue;
            if (component.getPreferredSize() == null) {
                nonFixedComponentsCount++;
            }
        }
        slice /= nonFixedComponentsCount == 0 ? 1 : nonFixedComponentsCount;
        if (slice < 0) {
            slice = 0;
        }

        for (Component component : parent.getComponents()) {
            if (constrainedComponentList.containsKey(component)) {
                absoluteLayout(parent, component);
                continue;
            }
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

    protected void absoluteLayout(Container parent, Component component) {
        Insets inserts = parent.getInsets();
        Dimension parentD = parent.getSize();
        Dimension d = component.getPreferredSize();
        if (d == null) {
            component.setBounds(inserts.left, inserts.top, parentD.width, parentD.height);
            return;
        }
        switch (constrainedComponentList.get(component)) {
            case ABSOLUTE:
                component.setBounds(inserts.left, inserts.top, d.width, d.height);
                break;
            case ABSOLUTE_CENTER:
                int x = (parent.getWidth() - d.width) / 2, y = (parent.getHeight() - d.height) / 2;
                component.setBounds(x, y, d.width, d.height);
        }
    }
}
