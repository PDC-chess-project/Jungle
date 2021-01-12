package com.chess.jungle.ui;

import com.chess.jungle.logic.JungleGame;
import com.chess.jungle.viewModel.GameViewModel;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Chengjie Luo
 */
public class MainWindow extends JFrame {

    private final GameViewModel viewModel = GameViewModel.get();

    public MainWindow() {
        viewModel.getError().observe(e -> {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Error occurred",
                    JOptionPane.ERROR_MESSAGE);
        });

        initWindow();
        initCanvas();

        viewModel.setCurrentGame(new JungleGame());
    }

    private void initWindow() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            viewModel.setError(e);
        }
        setTitle("Jungle game");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void initCanvas() {
        BoardPanel boardPanel = new BoardPanel(getSize());
        add(boardPanel, BorderLayout.CENTER);
    }
}
