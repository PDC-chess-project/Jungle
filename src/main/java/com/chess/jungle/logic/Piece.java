package com.chess.jungle.logic;

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
    protected int x;
    protected int y;
    protected Side side;

    public Piece(Type type,Side side,Coordinate coordinate){
        this.type = type;
        this.side = side;
        this.x = coordinate.getX();
        this.y = coordinate.getY();
    }

    public Piece(Type type, Side side, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.side = side;
    }

    public Type getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Side getSide() {
        return side;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.x = coordinate.x;
        this.y = coordinate.y;
    }

    public void setCoordinate(int x,int y){
        this.x = x;
        this.y = y;
    }

    public Coordinate getCoordinate(){
        return new Coordinate(x,y);
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
        return "Piece{"
                + "type=" + type
                + ", x=" + x
                + ", y=" + y
                + '}';
    }
}
