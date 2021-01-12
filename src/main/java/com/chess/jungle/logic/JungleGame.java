package com.chess.jungle.logic;


import java.util.ArrayList;
import java.util.List;


public class JungleGame {

    protected Board board;
    protected List<Piece> pieceList;

    public JungleGame() {
        board = Board.getInstance();
        pieceList = new ArrayList<>();

        resetPieces();
    }

    public List<Piece> getPieceList() {
        return pieceList;
    }

    public Board getBoard() {
        return board;
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
                new Coordinate(6,2),
                new Coordinate(0,0),
                new Coordinate(6,0),
                new Coordinate(2,2),
                new Coordinate(4,2),
                new Coordinate(1,1),
                new Coordinate(5,1),
                new Coordinate(0,2)
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

    public void movePiece(Piece piece,int x,int y){
        
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
        Coordinate next = new Coordinate(piece.x,piece.y).nextPace(direction);

        if(next.x < 0 || next.x > board.getWidth() - 1){
            //if out of board width
            return null;
        }
        if(next.y < 0 || next.y > board.getHeight() - 1){
            //if out of board length
            return null;
        }
        if(board.isRiver(next.x, next.y)){
            //if new coordinate is river
            if(piece.type == Piece.Type.LION || piece.type == Piece.Type.TIGER){
                //if is tiger or lion
                if(hasBlockInRiver(piece.x, piece.y, direction)){
                    //if there is a mouse block in the river, cannot jump over the river
                    return null;
                }
                //get coordinate of opposite shore
                next = board.getOppositeShore(piece.x,piece.y,direction);
            }else if(piece.type != Piece.Type.MOUSE){
                //if this piece is not mouse,lion or tiger
                return null;
            }
        }

        Piece target = getPieceByPos(next.x, next.y);
        if(target != null && !piece.isEdible(target)){
            //if target exists and this piece cannot defeat it
            return null;
        }

        return next;
    }

    /**
     * find animal by coordinate
     * @param x x
     * @param y y
     * @return piece, if there is no animal in this coordinate, return null
     */
    public Piece getPieceByPos(int x,int y){
        for(Piece p : pieceList){
            if(p.getX() == x && p.getY() == y){
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
    public Piece[] getPiecesByType(Piece.Type type){
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
     * @param x lion's or tiger's x coordinate
     * @param y lion's or tiger's y coordinate
     * @param direction direction
     * @return if lion or tiger blocked
     */
    public boolean hasBlockInRiver(int x,int y,Direction direction){
        Coordinate c = getBoard().getOppositeShore(x,y,direction);
        Piece[] pieces = getPiecesByType(Piece.Type.MOUSE);
        if(pieces.length == 0){
            return false;
        }

        for(Piece mouse : pieces){
            if(direction == Direction.UP || direction == Direction.DOWN){
                //judgment in the vertical direction
                if(mouse.x == x &&
                        mouse.y > Math.min(y,c.y) &&
                        mouse.y < Math.max(y,c.y)){
                    return true;
                }
            }else {
                //judgment in the horizontal direction
                if(mouse.y == y &&
                        mouse.x > Math.min(x,c.x) &&
                        mouse.x < Math.max(x,c.x)){
                    return true;
                }
            }
        }

        return false;

    }


}
