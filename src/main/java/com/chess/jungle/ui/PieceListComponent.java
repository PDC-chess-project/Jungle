package com.chess.jungle.ui;

import com.chess.jungle.logic.Coordinate;
import com.chess.jungle.logic.Piece;
import com.chess.jungle.viewModel.ErrorViewModel;
import com.chess.jungle.viewModel.GameViewModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represent all pieces on the board
 *
 * @author Chengjie Luo
 */
public class PieceListComponent extends JLayeredPane {

    private final GameViewModel viewModel = GameViewModel.get();

    private Collection<Piece> pieceList;

    private final List<PieceComponent> pieceComponentList = new ArrayList<>();

    private final SelectedIndicatorGroup selectedIndicatorGroup = new SelectedIndicatorGroup();

    public PieceListComponent() {
        viewModel.getCurrentJungleGame().stickyObserve((game) -> {
            if (game == null) {
                return;
            }
            this.pieceList = game.getPieceList();
            pieceComponentList.clear();
            update();
        });
        viewModel.getCurrentSide().stickyObserve((side) -> {
            for (PieceComponent pieceComponent : pieceComponentList) {
                pieceComponent.setIsElevated(pieceComponent.getPiece().getSide() == side);
            }
        });
    }

    /**
     * Update the position of pieces.
     */
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
                removeAll();
                for (Piece piece : pieceList) {
                    PieceComponent pieceComponent = new PieceComponent(piece);
                    pieceComponentList.add(pieceComponent);
                    add(pieceComponent);
                    pieceComponent.addMouseListener(new MouseAdapter() {

                        @Override
                        public void mouseReleased(MouseEvent e) {
                            if (viewModel.getCurrentSide().get() == pieceComponent.getPiece().getSide())
                                setSelectedPiece(pieceComponent);
                        }
                    });
                }
            }
        } catch (IOException | NullPointerException e) {
            ErrorViewModel.get().setError(e);
        }
    }

    protected void setSelectedPiece(PieceComponent pieceComponent) {
        selectedIndicatorGroup.setPieceComponent(pieceComponent);
        Coordinate[] possibleMovesList = viewModel.getCurrentJungleGame().get().getPossibleMove(pieceComponent.getPiece());
        for (Coordinate coordinate : possibleMovesList) {
            CandidateIndicator indicator = new CandidateIndicator(coordinate.getX(), coordinate.getY());
            selectedIndicatorGroup.addCandidateIndicator(indicator);
            indicator.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseReleased(MouseEvent e) {
                    viewModel.movePiece(pieceComponent.getPiece(), coordinate);
                    selectedIndicatorGroup.clear();
                    repaint();
                    update();
                    viewModel.flipCurrentSide();
                }
            });
        }
        repaint();
    }

    /**
     * A group of components indict current selected piece and its possible move.
     */
    private class SelectedIndicatorGroup {

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
            add(indicator, Integer.valueOf(1));
        }

        public void clear() {
            if (this.pieceComponent != null) {
                pieceComponent.setIsSelected(false);
            }
            pieceComponent = null;
            for (CandidateIndicator candidateIndicator : candidateIndicatorList) {
                remove(candidateIndicator);
            }
            candidateIndicatorList.clear();
        }
    }
}
