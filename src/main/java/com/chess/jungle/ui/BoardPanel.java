package com.chess.jungle.ui;

import com.chess.jungle.viewModel.GameViewModel;
import java.awt.Dimension;
import javax.swing.JLayeredPane;

/**
 *
 * @author Chengjie Luo
 */
public class BoardPanel extends JLayeredPane {

    protected final static int SQUARE_SIZE = 75;

    private final GameViewModel viewModel = GameViewModel.get();

    private final BoardComponent boardComponent;
    private final PieceListComponent pieceListComponent;

    public BoardPanel(Dimension parent) {
        boardComponent = new BoardComponent(parent);
        pieceListComponent = new PieceListComponent(parent);
        init();
    }

    private void init() {
        setLayout(null);
        update();
    }

    public void update() {
        add(boardComponent, new Integer(0));
        add(pieceListComponent, new Integer(1));
    }
}
