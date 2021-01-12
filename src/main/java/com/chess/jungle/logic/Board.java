package com.chess.jungle.logic;

import java.util.Arrays;

public class Board{

    public enum SquareType {
        NORMAL,
        DEN,
        TRAP,
        RIVER
    }

    SquareType[][] grid = new SquareType[7][];


    public Board() {

        for (int i = 0; i < grid.length; i++) {
            SquareType[] column = new SquareType[9];
            grid[i] = column;
            Arrays.fill(column, SquareType.NORMAL);
        }
        for (int i = 0; i < grid[0].length / 2 + 1; i++) {
            for (int j = 0; j < 2; j++) {
                int k = 2 * j - 1;
                int y = 4 - 4 * k;
                grid[2][y] = SquareType.TRAP;
                grid[3][y] = SquareType.DEN;
                grid[4][y] = SquareType.TRAP;
                grid[3][y + k] = SquareType.TRAP;
                grid[1][y + 3 * k] = SquareType.RIVER;
                grid[2][y + 3 * k] = SquareType.RIVER;
                grid[4][y + 3 * k] = SquareType.RIVER;
                grid[5][y + 3 * k] = SquareType.RIVER;
                grid[1][y + 4 * k] = SquareType.RIVER;
                grid[2][y + 4 * k] = SquareType.RIVER;
                grid[4][y + 4 * k] = SquareType.RIVER;
                grid[5][y + 4 * k] = SquareType.RIVER;
            }
        }
    }

    public SquareType[][] getGrid(){
        return grid;
    }

    public int getWidth(){
        return grid.length;
    }

    public int getHeight() {
        return getWidth() > 0 ? grid[0].length : 0;
    }

    public boolean isInBoard(Coordinate coordinate){
        return isInBoard(coordinate.x,coordinate.y);
    }

    public boolean isInBoard(int x, int y){
        if(x < 0 || x > getWidth() - 1){
            //if out of board width
            return false;
        }
        if(y < 0 || y > getHeight() - 1){
            //if out of board length
            return false;
        }
        return true;
    }


    public boolean isRiver(int x,int y){
        return grid[x][y] == SquareType.RIVER;
    }

    public boolean isTrap(int x,int y){
        return grid[x][y] == SquareType.TRAP;
    }

    public boolean isDen(int x,int y){
        return grid[x][y] == SquareType.DEN;
    }

    /**
     * If the chess piece is close to the river, get the coordinates
     * on the other side of the river. If not close, return the coordinates
     * after moving in this direction
     * @param x x
     * @param y y
     * @param direction direction
     * @return new coordinate
     */
    public Coordinate getOppositeShore(int x, int y, Direction direction){
        Coordinate res = new Coordinate(x,y).nextPace(direction);
        if(isRiver(res.x,res.y)){
            return getOppositeShore(res.x,res.y,direction);
        }else {
            return res;
        }
    }
}
