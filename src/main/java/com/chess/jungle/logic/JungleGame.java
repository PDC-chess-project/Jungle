package com.chess.jungle.logic;


import com.chess.jungle.viewModel.IJungleGame;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides some functions that need to be used when the game is running.
 * These functions will determine the movement of the pieces, whether the game is over
 * and other operations according to the rules of the game.
 * @author Liangwei Chen
 */
public class JungleGame implements IJungleGame {

    protected Board board;
    protected List<Piece> pieceList;
    private Piece.Side winner;
    private int blueNum,redNum;

    public JungleGame() {
        board = new Board();
        pieceList = new ArrayList<>();
        winner = null;
        blueNum = 8;
        redNum = 8;
        resetPieces();
    }

    @Override
    public List<Piece> getPieceList() {
        return pieceList;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    /**
     * Get winner
     * @return null, if don't have winner. Otherwise return winner's side
     */
    @Override
    public Piece.Side getWinner() {
        return winner;
    }

    /**
     * Reset Pieces to their original position
     */
    public void resetPieces(){
        pieceList = new ArrayList<>();

        /* Pieces' original coordinates.
            Its order is consistent with Piece.Type
         */
        Coordinate[] c = new Coordinate[]{
                new Coordinate(0,2),
                new Coordinate(5,1),
                new Coordinate(1,1),
                new Coordinate(4,2),
                new Coordinate(2,2),
                new Coordinate(6,0),
                new Coordinate(0,0),
                new Coordinate(6,2),
        };

        //add pieces in red side
        for(Piece.Type type : Piece.Type.values()){
            pieceList.add(new Piece(type,Piece.Side.RED,c[type.ordinal()]));
        }
        //add pieces in blue side
        for(Piece.Type type: Piece.Type.values()){
            pieceList.add(new Piece(
                    type,
                    Piece.Side.BLUE,
                    board.getWidth() - c[type.ordinal()].getX() - 1,
                    board.getHeight() - c[type.ordinal()].y - 1
            ));
        }
    }

    /**
     * All the coordinates this piece can be moved to
     * @param piece piece
     * @return coordinates
     */
    @Override
    public Coordinate[] getPossibleMove(Piece piece) {
        ArrayList<Coordinate> res = new ArrayList<>();
        Coordinate tmp;
        if((tmp = isMovable(piece,Direction.UP)) != null){
            res.add(tmp);
        }
        if((tmp = isMovable(piece,Direction.DOWN)) != null){
            res.add(tmp);
        }
        if((tmp = isMovable(piece,Direction.LEFT)) != null){
            res.add(tmp);
        }
        if((tmp = isMovable(piece,Direction.RIGHT)) != null){
            res.add(tmp);
        }

        return res.toArray(new Coordinate[0]);
    }


    /**
     * Determine whether this piece can move in one direction
     * @param piece piece
     * @param direction direction
     * @return if this piece can move in this direction, return the new coordinates of
     *          this piece after moving in this direction, otherwise return null
     */
    private Coordinate isMovable(Piece piece, Direction direction){
        Board board = getBoard();
        //the new coordinate if this piece move in this direction
        Coordinate next = new Coordinate(piece.getX(),piece.getY()).nextPace(direction);

        if(!board.isInBoard(next)){
            return null;
        }
        if(board.isRiver(next)){
            //if new coordinate is river
            if(piece.type == Piece.Type.LION || piece.type == Piece.Type.TIGER){
                //if is tiger or lion
                if(hasBlockInRiver(piece.getCoordinate(), direction)){
                    //if there is a mouse block in the river, cannot jump over the river
                    return null;
                }
                //get coordinate of opposite shore
                next = board.getOppositeShore(piece.getCoordinate(),direction);
            }else if(piece.type != Piece.Type.MOUSE){
                //if this piece is not mouse,lion or tiger
                return null;
            }
        }
        if(board.isDen(next)){
            if(!isDenAccessible(next, piece.side)) return null;
        }

        Piece target = findPieceByPos(next);
        if(target != null && !animalAbleToFight(piece,target)){
            //if target exists and this piece cannot defeat it
            return null;
        }

        return next;
    }

    /**
     * Determine if this move is valid
     * @param piece moved piece
     * @param coordinate target coordinate
     * @return true if valid, otherwise false
     */
    private boolean isMoveValid(Piece piece,Coordinate coordinate){
        for(Coordinate c : getPossibleMove(piece)){
            if(c.equals(coordinate)) return true;
        }
        return false;
    }

    /**
     * Move piece, if there is a piece in target coordinate,
     * that piece will be removed
     * @param piece piece
     * @param coordinate target coordinate
     */
    @Override
    public void movePiece(Piece piece,Coordinate coordinate){
        if(!isMoveValid(piece,coordinate)) return;

        Piece target = findPieceByPos(coordinate);
        if(target != null){
            pieceList.remove(target);
            if(target.side == Piece.Side.BLUE){
                if (--blueNum == 0){
                    winner = Piece.Side.RED;
                }
            }else {
                if(--redNum == 0){
                    winner = Piece.Side.BLUE;
                }
            }
        }
        piece.setCoordinate(coordinate);
        if(board.isDen(coordinate)){
            winner = piece.side;
        }
    }

    /**
     * find animal by coordinate
     * @param c coordinate
     * @return piece, if there is no animal in this coordinate, return null
     */
    public Piece findPieceByPos(Coordinate c){
        for(Piece p : pieceList){
            if(p.getCoordinate().equals(c)){
                return p;
            }
        }
        return null;
    }

    /**
     * find animal(both side) by type
     * @param type animal type
     * @return piece(Array)，if not find return empty array
     */
    public Piece[] findPiecesByType(Piece.Type type){
        ArrayList<Piece> res = new ArrayList<>();
        for(Piece p : pieceList){
            if(p.type == type){
                res.add(p);
            }
        }
        return res.toArray(new Piece[0]);
    }

    /**
     * Determine if there is a mouse in the middle of the river
     * blocking a lion or tiger from jumping
     * @param thisC lion or tiger's coordinate
     * @param direction direction
     * @return true if lion or tiger blocked, otherwise false
     */
    public boolean hasBlockInRiver(Coordinate thisC,Direction direction){
        Coordinate oppoC = getBoard().getOppositeShore(thisC,direction);
        Piece[] pieces = findPiecesByType(Piece.Type.MOUSE);
        if(pieces.length == 0){
            return false;
        }

        for(Piece mouse : pieces){
            if(direction == Direction.UP || direction == Direction.DOWN){
                //judgment in the vertical direction
                if(mouse.getX() == thisC.x &&
                        mouse.getY() > Math.min(thisC.y, oppoC.y) &&
                        mouse.getY() < Math.max(thisC.y,oppoC.y)){
                    return true;
                }
            }else {
                //judgment in the horizontal direction
                if(mouse.getY() == thisC.y &&
                        mouse.getX() > Math.min(thisC.x,oppoC.x) &&
                        mouse.getX() < Math.max(thisC.x,oppoC.x)){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Determine if den is accessible or not, animal cannot go in
     * den belong to its side
     * @param c den's coordinate
     * @param side animal's side
     * @return true if den is accessible, otherwise false
     */
    public boolean isDenAccessible(Coordinate c,Piece.Side side){
        if(board.isDen(c)){
            //if this block is den

            //if den is belong to its opponent's side, return true
            if(c.y < board.getWidth() / 2 && side == Piece.Side.BLUE){
                return true;
            }else if(c.y > board.getWidth() / 2 && side == Piece.Side.RED){
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if the animal is trapped, only opponent's trap
     * can trap the animals in this side
     * @param piece piece
     * @return true if the animal is trapped, otherwise false
     */
    public boolean isPieceTrapped(Piece piece){
        if(board.isTrap(piece.getCoordinate())){
            //if piece is in trap

            //determine whether the trap belongs to the side of this piece
            if(piece.getY() < board.getWidth() / 2 && piece.side == Piece.Side.BLUE){
                return true;
            }else if(piece.getY() > board.getWidth() / 2 && piece.side == Piece.Side.RED){
                return true;
            }
        }
        return false;
    }

    /**
     * Determine whether the attacker can attack and defeat the defender.
     * Logically the defender should be in a position where the attacker
     * can move within one step, but this method does not make an if judgment for this
     * @param attacker attacker
     * @param defender defender
     * @return true if attacker is able to defeat defender, otherwise false
     */
    public boolean animalAbleToFight(Piece attacker,Piece defender){
        if(attacker.side == defender.side){
            //if they are in the same side
            return false;
        }
        if(board.isRiver(attacker.getCoordinate()) ^ board.isRiver(defender.getCoordinate())){
            //if one is on land and another is in the river
            return false;
        }
        if(isPieceTrapped(defender)){
            //if defender is trapped, the attacker can defeat it
            return true;
        }
        return attacker.isBiggerThan(defender);
    }


}
