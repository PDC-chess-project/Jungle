package com.chess.jungle.ui;

import com.chess.jungle.ui.layout.AbsoluteLayout;
import com.chess.jungle.ui.layout.CenterLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Chengjie Luo
 */
public final class BoardPanel extends JPanel {

    protected final static int SQUARE_SIZE = 75;

    public BoardPanel() {
        setLayout(new CenterLayout());
        add(new CenterPanel());
    }

    @Override
    public Dimension getPreferredSize() {
        return null;
    }

    static final class CenterPanel extends JPanel {

        private final BoardComponent boardComponent = new BoardComponent();
        private final PieceListComponent pieceListComponent = new PieceListComponent();

        public CenterPanel() {
            setLayout(new AbsoluteLayout());
            add(pieceListComponent);
            add(boardComponent);
        }

        @Override
        public Dimension getPreferredSize() {
            return boardComponent.getPreferredSize();
        }
    }
}
