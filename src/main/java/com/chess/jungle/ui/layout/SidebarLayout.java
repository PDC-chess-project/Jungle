package com.chess.jungle.ui.layout;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Chengjie Luo
 */
public class SidebarLayout implements LayoutManager2 {

    private final static int PADDING = 20;
    private final static int TITLE_HEIGHT = 50;
    private final static int CLOSE_WIDTH = 30;

    private final int width;

    public enum Constraints {
        TITLE,
        CLOSE,
        CONTENT
    }

    private final Map<Constraints, Component> componentMap = new HashMap<>();

    public SidebarLayout(int width) {
        this.width = width;
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        if (constraints instanceof Constraints)
            componentMap.put((Constraints) constraints, comp);
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
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return parent.getSize();
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return parent.getSize();
    }

    @Override
    public void layoutContainer(Container parent) {
        componentMap.get(Constraints.TITLE).setBounds(PADDING, PADDING, width - PADDING - CLOSE_WIDTH, TITLE_HEIGHT);
        componentMap.get(Constraints.CLOSE).setBounds(width - PADDING - CLOSE_WIDTH, PADDING, CLOSE_WIDTH, TITLE_HEIGHT);
        if (componentMap.containsKey(Constraints.CONTENT)) {
            int height = parent.getHeight() - 3 * PADDING - TITLE_HEIGHT;
            Component content = componentMap.get(Constraints.CONTENT);
            if (content.getPreferredSize() != null) {
                height = content.getPreferredSize().height;
            }
            componentMap.get(Constraints.CONTENT).setBounds(PADDING, PADDING * 2 + TITLE_HEIGHT, width - 2 * PADDING, height);
        }
    }
}
