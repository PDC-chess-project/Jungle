package com.chess.jungle.logic;

/**
 *
 * @author Chengjie Luo
 */
public class Board {

    public enum SquareType {
        NORMAL,
        DEN,
        TRAP,
        RIVER
    }

    protected SquareType[][] grid = new SquareType[7][];

    public Board() {
        for (int i = 0; i < grid.length; i++) {
            SquareType[] column = new SquareType[9];
            grid[i] = column;
            for (int j = 0; j < column.length; j++) {
                column[j] = SquareType.NORMAL;
            }
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

    public SquareType[][] getGrid() {
        return grid;
    }

    public int getWidth() {
        return grid.length;
    }

    public int getHeight() {
        return getWidth() > 0 ? grid[0].length : 0;
    }
}
