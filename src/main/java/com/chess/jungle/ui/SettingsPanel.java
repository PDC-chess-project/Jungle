package com.chess.jungle.ui;

import com.chess.jungle.utils.Colors;
import com.chess.jungle.viewModel.SettingsViewModel;

import javax.swing.*;
import java.awt.*;

/**
 * @author Chengjie Luo
 */
public class SettingsPanel extends JPanel {

    private final String[] colorStringList = new String[]{"Grey", "Blue", "Yellow", "Indigo"};
    private final Color[] colorList = new Color[]{Colors.LIGHT_GREY, Colors.LIGHT_BLUE, Colors.LIGHT_YELLOW, Colors.LIGHT_INDIGO};

    public SettingsPanel() {
        setLayout(new GridLayout(0, 2));
        setBackground(Colors.TRANSPARENT);
        JLabel desc = new JLabel("Theme color");
        desc.setForeground(Color.WHITE);
        add(desc);
        JComboBox<String> comboBox = new JComboBox<>(colorStringList);
        comboBox.addActionListener(e -> SettingsViewModel.get().setThemeColor(colorList[comboBox.getSelectedIndex()]));
        add(comboBox);
    }
}
