package com.chess.jungle.logic;

import java.util.Objects;

/**
 * 
 * @author Liangwei Chen
 */
public class Coordinate {
    protected int x;
    protected int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * The coordinates after moving one grid in a certain direction
     * @param direction direction
     * @return new coordinate
     */
    public Coordinate nextPace(Direction direction){
        int nextX = x;
        int nextY = y;
        switch (direction){
            case UP:
                nextY--;break;
            case DOWN:
                nextY++;break;
            case LEFT:
                nextX--;break;
            case RIGHT:
                nextX++;
        }
        return new Coordinate(nextX,nextY);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

}
