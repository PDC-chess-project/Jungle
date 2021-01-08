package com.chess.jungle.logic;

import com.chess.jungle.ui.Drawable;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 *
 * @author CommA
 */
public class Board implements Drawable {

    enum SquareType {
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

    @Override
    public void draw(Graphics2D g) {
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int d = metrics.getAscent();

        for (int i = 0; i < grid.length; i++) {
            SquareType[] column = grid[i];
            for (int j = 0; j < column.length; j++) {
                g.setColor(Color.red);
                g.drawRect(i * 50, j * 50, 50, 50);
                SquareType squareType = grid[i][j];
                switch (squareType) {
                    case TRAP:
                        g.drawString("阱", i * 50, j * 50 + d);
                        break;
                    case DEN:
                        g.drawString("营", i * 50, j * 50 + d);
                        break;
                    case RIVER:
                        g.drawString("河", i * 50, j * 50 + d);
                        break;
                }
            }
        }
    }
}
