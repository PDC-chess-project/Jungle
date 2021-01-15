package com.chess.jungle;

import com.chess.jungle.ui.MainWindow;
import com.chess.jungle.utils.UiUtils;
import com.chess.jungle.viewModel.ErrorViewModel;

import javax.swing.*;
import java.awt.*;

/**
 * Entry of this program.
 *
 * @author Chengjie Luo
 */
public class Game {

    public static void main(String[] args) {
        try {
            UiUtils.setGlobalFont(new Font("Arial", Font.PLAIN, 18));
            UiUtils.setSystemLookAndFeel();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            ErrorViewModel.get().setError(e);
        }
        new MainWindow().setVisible(true);
    }
}
