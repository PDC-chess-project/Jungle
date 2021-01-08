package com.chess.jungle.logic;

import com.chess.jungle.ui.Drawable;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author CommA
 */
public class JungleGame implements Drawable {
    
    public static class Position {
        
        protected int x;
        protected int y;
        
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int hashCode() {
            return x + y * 100;
        }
    }
    
    protected Board board = new Board();
    protected Map<Position, Piece> pieceList = new HashMap<>();
    
    public Piece getPiece(int x, int y) {
        return pieceList.get(new Position(x, y));
    }
    
    @Override
    public void draw(Graphics2D g) {
        board.draw(g);
    }
}
