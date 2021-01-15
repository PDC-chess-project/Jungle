package com.chess.jungle.ui;

import com.chess.jungle.ui.layout.CustomLayout;
import com.chess.jungle.utils.Colors;

import javax.swing.*;
import java.awt.*;

/**
 * This panel will add new component to front by default.
 *
 * @author Chengjie Luo
 */
public class CustomPanel extends JPanel {

    public CustomPanel() {
        init();
    }

    private void init() {
        setLayout(new CustomLayout());
        setOpaque(false);
        setBackground(Colors.TRANSPARENT);
    }

    @Override
    protected void addImpl(Component comp, Object constraints, int index) {
        super.addImpl(comp, constraints, 0);
    }
}
