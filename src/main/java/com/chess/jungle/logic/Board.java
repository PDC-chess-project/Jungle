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
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
    }

    public boolean isRiver(Coordinate c){
        return grid[c.x][c.y] == SquareType.RIVER;
    }

    public boolean isTrap(Coordinate c){
        return grid[c.x][c.y] == SquareType.TRAP;
    }

    public boolean isDen(Coordinate c){
        return grid[c.x][c.y] == SquareType.DEN;
    }

    /**
     * If the piece is close to the river, get the coordinates
     * on the other side of the river. If not close, return the coordinates
     * after moving in this direction
     * @param c this piece's coordinate
     * @param direction direction
     * @return new coordinate
     */
    public Coordinate getOppositeShore(Coordinate c, Direction direction){
        Coordinate res = c.nextPace(direction);
        if(isRiver(res)){
            return getOppositeShore(res,direction);
        }else {
            return res;
        }
    }
}
