package com.chess.jungle.ui;

import com.chess.jungle.logic.JungleGame;
import com.chess.jungle.utils.LiveData;
import com.chess.jungle.viewModel.GameViewModel;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author CommA
 */
public class MainWindow extends JFrame {
    
    GameViewModel viewModel = GameViewModel.get();
    
    public MainWindow() {
        initWindow();
        initCanvas();
        
        viewModel.setCurrentGame(new JungleGame());
    }
    
    public void initWindow() {
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(new JPanel());
        setLayout(new BorderLayout());
    }
    
    public void initCanvas() {
        LiveData<JungleGame> currentGame = viewModel.getCurrentChessGame();
        JComponent canvas = new JComponent() {
            
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setFont(g2d.getFont().deriveFont(20f));
                if (currentGame.get() != null) {
                    currentGame.get().draw(g2d);
                }
            }
        };
        add(canvas);
        currentGame.observe(value -> {
            canvas.repaint();
        });
    }
}
