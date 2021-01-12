package com.chess.jungle.logic;


import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.Arrays;


/**
 *
 * @author CommA
 */
public class Board{

    public enum SquareType {
        NORMAL,
        DEN,
        TRAP,
        RIVER
    }

    SquareType[][] grid = new SquareType[7][];

    private static Board instance;

    private Board() {

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
    public static synchronized Board getInstance(){
        if(instance == null){
            instance = new Board();
        }
        return instance;
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


    /**
     * 根据坐标判断是否河流
     * @param x x坐标
     * @param y y坐标
     * @return 是否河流
     */
    public boolean isRiver(int x,int y){
        return grid[x][y] == SquareType.RIVER;
    }

    /**
     * 如果棋子靠近河流，获取河对岸的坐标。如果不靠近则返回朝这个方向移动后的坐标
     * @param x 横坐标
     * @param y 纵坐标
     * @param direction 方向
     * @return 河对岸坐标
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
