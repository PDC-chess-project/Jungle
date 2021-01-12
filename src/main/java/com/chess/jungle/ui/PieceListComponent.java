package com.chess.jungle.ui;

import com.chess.jungle.logic.Piece;
import static com.chess.jungle.ui.BoardPanel.SQUARE_SIZE;
import com.chess.jungle.viewModel.GameViewModel;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Collection;
import javax.swing.JComponent;

/**
 *
 * @author Chengjie Luo
 */
public class PieceListComponent extends JComponent {

    private final GameViewModel viewModel = GameViewModel.get();

    private Collection<Piece> pieceList;

    public PieceListComponent() {
        viewModel.getCurrentJungleGame().stickyObserve((game) -> {
            if (game == null) {
                return;
            }
            this.pieceList = game.getPieceList();
            update();
        });
    }

    protected void update() {
        if (pieceList == null) {
            return;
        }

        try {
            removeAll();
            for (Piece piece : pieceList) {
                String path = null;
                switch (piece.getType()) {
                    case MOUSE:
                        path = "pieces/mouse.png";
                        break;
                    case CAT:
                        path = "pieces/cat.png";
                        break;
                    case WOLF:
                        path = "pieces/wolf.png";
                        break;
                    case DOG:
                        path = "pieces/dog.png";
                        break;
                    case LEOPARD:
                        path = "pieces/leopard.png";
                        break;
                    case TIGER:
                        path = "pieces/tiger.png";
                        break;
                    case LION:
                        path = "pieces/lion.png";
                        break;
                    case ELEPHANT:
                        path = "pieces/elephant.png";
                        break;
                    default:
                        throw new NullPointerException("Piece type: " + piece.getType() + " can not be recognized");
                }
                PieceComponent pieceComponent = new PieceComponent(path);
                add(pieceComponent);
                pieceComponent.setBounds(piece.getX() * SQUARE_SIZE, piece.getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                pieceComponent.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        viewModel.setSelectedPiece(piece);
                    }
                });
            }
        } catch (IOException | NullPointerException e) {
            viewModel.setError(e);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return null;
    }
}
