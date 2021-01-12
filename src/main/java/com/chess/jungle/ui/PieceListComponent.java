package com.chess.jungle.ui;

import com.chess.jungle.logic.Coordinate;
import com.chess.jungle.logic.Piece;
import com.chess.jungle.viewModel.GameViewModel;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JComponent;

/**
 *
 * @author Chengjie Luo
 */
public class PieceListComponent extends JComponent {

    private final GameViewModel viewModel = GameViewModel.get();

    private Collection<Piece> pieceList;

    private List<PieceComponent> pieceComponentList = new ArrayList<>();

    private SelectedComponentGroup group = new SelectedComponentGroup();

    public PieceListComponent() {
        viewModel.getCurrentJungleGame().stickyObserve((game) -> {
            if (game == null) {
                return;
            }
            this.pieceList = game.getPieceList();
            update();
        });
        viewModel.getCurrentSide().stickyObserve((side) -> {
            for (PieceComponent pieceComponent : pieceComponentList) {
                if (pieceComponent.getPiece().getSide() == side) {
                    pieceComponent.setIsElevated(true);
                } else {
                    pieceComponent.setIsElevated(false);
                }
            }
        });
        viewModel.getSelectedPiece().observe(pieceComponent -> {
            group.setPieceComponent(pieceComponent);
            Coordinate[] possibleMovesList = viewModel.getCurrentJungleGame().get().getPossibleMove(pieceComponent.getPiece());
            for (Coordinate coordinate : possibleMovesList) {
                group.addCandidateIndicator(new CandidateIndicator(coordinate.getX(), coordinate.getY()));
            }
            repaint();
        });
    }

    protected void update() {
        if (pieceList == null) {
            return;
        }

        try {
            if (!pieceComponentList.isEmpty()) {
                pieceComponentList.forEach(pieceComponent -> {
                    if (pieceList.contains(pieceComponent.getPiece())) {
                        pieceComponent.updatePosition();
                    } else {
                        remove(pieceComponent);
                    }
                });
            } else {
                for (Piece piece : pieceList) {
                    PieceComponent pieceComponent = new PieceComponent(piece);
                    pieceComponentList.add(pieceComponent);
                    add(pieceComponent);
                    pieceComponent.addMouseListener(new MouseAdapter() {

                        @Override
                        public void mouseClicked(MouseEvent e) {
                            viewModel.setSelectedPiece(pieceComponent);
                        }
                    });
                }
            }
        } catch (IOException | NullPointerException e) {
            viewModel.setError(e);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return null;
    }

    private class SelectedComponentGroup {

        private PieceComponent pieceComponent;
        private final List<CandidateIndicator> candidateIndicatorList = new ArrayList<>();

        public void setPieceComponent(PieceComponent pieceComponent) {
            if (this.pieceComponent != null) {
                this.pieceComponent.setIsSelected(false);
            }
            this.pieceComponent = pieceComponent;
            if (pieceComponent != null) {
                this.pieceComponent.setIsSelected(true);
            }
            for (CandidateIndicator candidateIndicator : candidateIndicatorList) {
                remove(candidateIndicator);
            }
            candidateIndicatorList.clear();
        }

        public void addCandidateIndicator(CandidateIndicator indicator) {
            candidateIndicatorList.add(indicator);
            add(indicator);
        }
    }
}
