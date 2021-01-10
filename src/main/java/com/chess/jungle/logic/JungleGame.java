package com.chess.jungle.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Chengjie Luo
 */
public class JungleGame {

    protected Board board = new Board();
    protected List<Piece> pieceList = new ArrayList<>();

    public JungleGame() {
        pieceList.add(new Piece(Piece.Type.MOUSE, 0, 2));
        pieceList.add(new Piece(Piece.Type.CAT, 5, 1));
        pieceList.add(new Piece(Piece.Type.WOLF, 4, 2));
        pieceList.add(new Piece(Piece.Type.DOG, 1, 1));
    }

    public Collection<Piece> getPieceList() {
        return pieceList;
    }

    public Board getBoard() {
        return board;
    }
}
