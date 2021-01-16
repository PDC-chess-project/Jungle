package com.chess.jungle.ui;

import com.chess.jungle.ui.layout.CustomLayout;
import com.chess.jungle.utils.ImageReader;
import com.chess.jungle.viewModel.ErrorViewModel;
import com.chess.jungle.viewModel.GameViewModel;
import com.chess.jungle.viewModel.LeaderBoardViewModel;
import com.chess.jungle.viewModel.SettingsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * @author Chengjie Luo
 */
public final class MainWindow extends JFrame {

    private final GameViewModel gameViewModel = GameViewModel.get();
    private final ErrorViewModel errorViewModel = ErrorViewModel.get();
    private final LeaderBoardViewModel leaderBoardViewModel = LeaderBoardViewModel.get();

    private final ImageComponent background = new ImageComponent();
    private final WinnerOverlayPanel winnerOverlayPanel = new WinnerOverlayPanel();
    private final SidebarPanel sidebarPanel = new SidebarPanel();
    private final SettingsPanel settingsPanel = new SettingsPanel();
    private final LeaderboardPanel leaderboardPanel = new LeaderboardPanel();

    public MainWindow() {
        initErrorObserver();
        startNewGame();
        initMenuBar();
        initWindow();
        initContent();
        initGameObserver();
        initWinnerObserver();
        initSettingsObserver();
    }

    private void startNewGame() {
        gameViewModel.startNewGame();
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
        gameMenu.add(createJMenuItem("Current player", () -> JOptionPane.showMessageDialog(this,
                "Red:" + leaderBoardViewModel.getRedPlayerName() + "\n" + "Blue:" + leaderBoardViewModel.getBluePlayerName(),
                "Current player",
                JOptionPane.INFORMATION_MESSAGE)));
        gameMenu.add(createJMenuItem("Exit", this::dispose));

        JMenu viewMenu = new JMenu("View");
        viewMenu.add(createJMenuItem("Settings", () -> sidebarPanel.open("Settings", settingsPanel)));
        viewMenu.add(createJMenuItem("Leaderboard", () -> sidebarPanel.open("Leaderboard", leaderboardPanel)));

        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(createJMenuItem("Rules", this::openRuleDialog));
        helpMenu.add(createJMenuItem("About", this::openAboutDialog));

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
            background.setImage(ImageReader.read("board/background.png"));
            add(background, CustomLayout.Constraints.ABSOLUTE);
        } catch (IOException e) {
            errorViewModel.setError(e);
        }

        add(new BoardPanel());

        add(sidebarPanel, CustomLayout.Constraints.ABSOLUTE);

        winnerOverlayPanel.setVisible(false);
        add(winnerOverlayPanel, CustomLayout.Constraints.ABSOLUTE_CENTER);
    }

    /**
     * Request player name each time game starts.
     */
    private void initGameObserver() {
        gameViewModel.getCurrentJungleGame().stickyObserve(game -> SwingUtilities.invokeLater(() -> {
            int reply = JOptionPane.NO_OPTION;
            if (leaderBoardViewModel.validatePlayerName())
                reply = JOptionPane.showConfirmDialog(this, "Keep the same players?", "Keep players", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.NO_OPTION) {
                String redPlayerName, bluePlayerName;
                boolean isFirstRun = true;
                do {
                    if (!isFirstRun)
                        JOptionPane.showMessageDialog(this,
                                "Please reenter the player names.",
                                "Your input is invalid",
                                JOptionPane.ERROR_MESSAGE);
                    redPlayerName = JOptionPane.showInputDialog(this, "Enter the red player name:", "Red player name", JOptionPane.PLAIN_MESSAGE);
                    bluePlayerName = JOptionPane.showInputDialog(this, "Enter the blue player name:", "Blue player name", JOptionPane.PLAIN_MESSAGE);
                    isFirstRun = false;
                } while (!leaderBoardViewModel.setPlayerNames(redPlayerName, bluePlayerName));
            }
        }));
    }

    private void initWinnerObserver() {
        gameViewModel.getWinSide().observe(winSide -> {
            winnerOverlayPanel.setSide(winSide);
            winnerOverlayPanel.setVisible(true);
        });
        gameViewModel.getCurrentJungleGame().observe(game -> winnerOverlayPanel.setVisible(false));
    }

    private void initSettingsObserver() {
        SettingsViewModel.get().getThemeColor().stickyObserve(background::setColor);
    }

    private void openRuleDialog() {
        JOptionPane.showMessageDialog(this,
                "The player who is first to maneuver any one of their pieces into the opponent's den wins the game.\n\n" +
                        "Ranking: Mouse > Elephant > Lion > Tiger > Leopard > Dog > Wolf > Cat > Mouse.\n" +
                        "The mouse is the only animal that may go onto a water square.\n" +
                        "The lion and tiger can jump over a river horizontally or vertically.\n" +
                        "A piece that enters one of the opponent's trap squares is reduced in rank to 0.",
                "Basic rules",
                JOptionPane.PLAIN_MESSAGE);
    }

    private void openAboutDialog() {
        JOptionPane.showMessageDialog(this,
                "This is a simple jungle game.\n\n" +
                        "Developed by\n" +
                        "Chengjie Luo\n" +
                        "Liangwei Chen\n" +
                        "Chenfan Wang",
                "About",
                JOptionPane.PLAIN_MESSAGE);
    }
}
