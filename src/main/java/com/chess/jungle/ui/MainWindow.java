package com.chess.jungle.ui;

import com.chess.jungle.logic.JungleGame;
import com.chess.jungle.viewModel.GameViewModel;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Chengjie Luo
 */
public class MainWindow extends JFrame {
    
    private GameViewModel viewModel = GameViewModel.get();
    
    public MainWindow() {
        initWindow();
        initCanvas();
        
        viewModel.setCurrentGame(new JungleGame());
    }
    
    public void initWindow() {
        setTitle("Jungle game");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }
    
    public void initCanvas() {
        BoardPanel boardPanel = new BoardPanel();
        add(boardPanel);
        viewModel.getCurrentJungleGame().observe(value -> {
            boardPanel.update();
        });
    }
}
