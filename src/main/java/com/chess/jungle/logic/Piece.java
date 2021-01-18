package com.chess.jungle.logic;

import java.util.Objects;

/**
 * Piece.
 * Attributes of piece:
 *      type: what kind of animal
 *      side: it belongs to which camp
 *      coordinate
 * @author Liangwei Chen
 */
public class Piece {

    public enum Type {
        MOUSE,
        CAT,
        DOG,
        WOLF,
        LEOPARD,
        TIGER,
        LION,
        ELEPHANT
    }
    public enum Side {
        BLUE, RED
    }

    protected Type type;
    protected Side side;
    protected Coordinate coordinate;

    public Piece(Type type,Side side,Coordinate coordinate){
        this.type = type;
        this.side = side;
        this.coordinate = coordinate;
    }

    public Piece(Type type, Side side, int x, int y) {
        this.type = type;
        this.side = side;
        this.coordinate = new Coordinate(x,y);
    }

    public Type getType() {
        return type;
    }

    public int getX() {
        return coordinate.x;
    }

    public int getY() {
        return coordinate.y;
    }

    public Side getSide() {
        return side;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate(){
        return coordinate;
    }

    /**
     * Judge whether this animal is bigger than another animal
     * (when the animals are the same, consider this animal to be bigger)
     * @param target another animal
     * @return true if this piece bigger than target, otherwise false
     */
    public boolean isBiggerThan(Piece target) {
        if (this.type == Type.MOUSE && target.type == Type.ELEPHANT) {
            return true;
        } else if (this.type == Type.ELEPHANT && target.type == Type.MOUSE) {
            return false;
        } else {
            return this.type.compareTo(target.type) >= 0;
        }
    }


    @Override
    public String toString() {
        return "Piece{" +
                "type=" + type +
                ", side=" + side +
                ", coordinate=" + coordinate +
                '}';
    }
}
