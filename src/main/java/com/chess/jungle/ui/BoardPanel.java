package com.chess.jungle.ui;

import com.chess.jungle.ui.layout.CustomLayout;

import javax.swing.*;
import java.awt.*;

/**
 * A panel to display the jungle chess.
 * @author Chengjie Luo
 */
public final class BoardPanel extends JPanel {

    protected final static int SQUARE_SIZE = 75;

    public BoardPanel() {
        setLayout(new CustomLayout());

        PieceListComponent pieceListComponent = new PieceListComponent();
        add(pieceListComponent, CustomLayout.Constraints.ABSOLUTE_CENTER);

        BoardComponent boardComponent = new BoardComponent();
        add(boardComponent, CustomLayout.Constraints.ABSOLUTE_CENTER);

        pieceListComponent.setPreferredSize(boardComponent.getPreferredSize());
    }

    @Override
    public Dimension getPreferredSize() {
        return null;
    }
}
