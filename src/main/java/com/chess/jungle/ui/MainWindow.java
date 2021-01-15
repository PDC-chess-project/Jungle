package com.chess.jungle.ui;

import com.chess.jungle.ui.layout.CustomLayout;
import com.chess.jungle.utils.Colors;
import com.chess.jungle.utils.ImageReader;
import com.chess.jungle.viewModel.ErrorViewModel;
import com.chess.jungle.viewModel.GameViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * @author Chengjie Luo
 */
public final class MainWindow extends JFrame {

    private final GameViewModel viewModel = GameViewModel.get();
    private final ErrorViewModel errorViewModel = ErrorViewModel.get();

    private final WinnerOverlayPanel winnerOverlayPanel = new WinnerOverlayPanel();

    public MainWindow() {
        initErrorObserver();
        startNewGame();
        initMenuBar();
        initWindow();
        initContent();
        initWinnerObserver();
    }

    /**
     * Start a new game
     */
    private void startNewGame() {
        viewModel.startNewGame();
    }

    private void initErrorObserver() {
        errorViewModel.getError().observe(e -> {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Error occurred",
                    JOptionPane.ERROR_MESSAGE);
            dispose();
        });
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        gameMenu.add(createJMenuItem("Start new game", this::startNewGame));
        gameMenu.add(createJMenuItem("Exit", this::dispose));
        JMenu viewMenu = new JMenu("View");
        JMenu helpMenu = new JMenu("help");
        menuBar.add(gameMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
    }

    private JMenuItem createJMenuItem(String name, Runnable callback) {
        return new JMenuItem(new AbstractAction(name) {
            @Override
            public void actionPerformed(ActionEvent e) {
                callback.run();
            }
        });
    }

    private void initWindow() {
        setTitle("Jungle game");
        setSize(1000, 800);
        setMinimumSize(new Dimension(560, 750));
        setLocationByPlatform(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(new CustomPanel());
    }

    private void initContent() {
        // set background
        try {
            add(new ImageComponent(ImageReader.read("board/background.png"), Colors.LIGHT_YELLOW), CustomLayout.Constraints.ABSOLUTE);
        } catch (IOException e) {
            errorViewModel.setError(e);
        }

        BoardPanel gameBoard = new BoardPanel();
        add(gameBoard);

        winnerOverlayPanel.setVisible(false);
        add(winnerOverlayPanel, CustomLayout.Constraints.ABSOLUTE_CENTER);
    }

    private void initWinnerObserver() {
        viewModel.getWinSide().observe(winSide -> {
            winnerOverlayPanel.setSide(winSide);
            winnerOverlayPanel.setVisible(true);
        });
        viewModel.getCurrentJungleGame().observe(game -> winnerOverlayPanel.setVisible(false));
    }
}
