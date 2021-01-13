package com.chess.jungle.ui;

import com.chess.jungle.logic.JungleGame;
import com.chess.jungle.logic.Piece;
import com.chess.jungle.ui.layout.CustomLayout;
import com.chess.jungle.viewModel.GameViewModel;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Chengjie Luo
 */
public final class MainWindow extends JFrame {

    private final GameViewModel viewModel = GameViewModel.get();

    public MainWindow() {
        startNewGame();
        initErrorObserver();
        initWindow();
        initContent();
        initWinObserver();
    }

    /**
     * Start a new game
     */
    private void startNewGame() {
        viewModel.setCurrentGame(new JungleGame());
        viewModel.setCurrentSide(Piece.Side.RED);
    }

    private void initErrorObserver() {
        viewModel.getError().observe(e -> JOptionPane.showMessageDialog(this,
                e.getMessage(),
                "Error occurred",
                JOptionPane.ERROR_MESSAGE));
    }

    private void initWindow() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            viewModel.setError(e);
        }
        setTitle("Jungle game");
        setSize(1000, 800);
        setMinimumSize(new Dimension(800, 720));
        setLocationByPlatform(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new CustomLayout());
    }

    private void initContent() {
        add(new BoardPanel());
        JPanel leaderboard = new JPanel();
        leaderboard.setPreferredSize(new Dimension(200, 0));
        add(leaderboard);
    }

    private void initWinObserver() {
        viewModel.getWinSide().observe(winSide -> JOptionPane.showMessageDialog(this,
                winSide == Piece.Side.BLUE ? "Blue win!" : "Red win!",
                "Game over",
                JOptionPane.PLAIN_MESSAGE));
    }
}
