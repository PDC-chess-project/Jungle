package com.chess.jungle.viewModel;

import com.chess.jungle.logic.Board;
import com.chess.jungle.logic.Coordinate;
import com.chess.jungle.logic.Piece;

import java.util.List;

/**
 * @author Chengjie Luo
 */
public interface IJungleGame {

    List<Piece> getPieceList();

    Board getBoard();

    /**
     * Move piece, if there is a piece in target coordinate,
     * that piece will be removed
     *
     * @param piece      piece
     * @param coordinate target coordinate
     */
    void movePiece(Piece piece, Coordinate coordinate);

    /**
     * All the coordinates this piece can be moved to
     *
     * @param piece piece
     * @return coordinates
     */
    Coordinate[] getPossibleMove(Piece piece);

    /**
     * Get winner
     * @return null, if don't have winner. Otherwise return winner's side
     */
    Piece.Side getWinner();
}
