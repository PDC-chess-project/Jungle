package com.chess.jungle.logic;

/**
 *
 * @author Chengjie Luo
 */
public class Piece {

    public enum Type {
        MOUSE,
        CAT,
        WOLF,
        DOG,
        LEOPARD,
        TIGER,
        LION,
        ELEPHANT
    }

    protected Type type;
    protected int x;
    protected int y;

    public Piece(Type type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
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
}
