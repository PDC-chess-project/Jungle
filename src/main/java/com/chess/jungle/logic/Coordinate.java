package com.chess.jungle.logic;

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
     * 向某一方向移动一格后的坐标
     * @param direction 方向
     * @return 新坐标
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
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
