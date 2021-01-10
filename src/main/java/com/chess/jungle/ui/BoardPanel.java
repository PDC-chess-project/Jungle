package com.chess.jungle.ui;

import com.chess.jungle.logic.Board;
import com.chess.jungle.viewModel.GameViewModel;
import com.chess.jungle.logic.Board.SquareType;
import com.chess.jungle.logic.JungleGame;
import com.chess.jungle.logic.Piece;
import com.chess.jungle.utils.ImageReader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Collection;
import javax.swing.JLayeredPane;

/**
 *
 * @author Chengjie Luo
 */
public class BoardPanel extends JLayeredPane {
    
    private final GameViewModel viewModel = GameViewModel.get();
    
    private final BoardCompnent board = new BoardCompnent();
    
    public BoardPanel() {
        init();
    }
    
    private void init() {
        setLayout(null);
    }
    
    public void update() {
        JungleGame jungleGame = viewModel.getCurrentJungleGame().get();
        if (jungleGame != null) {
            paintPieceList(jungleGame.getPieceList());
        }
    }
    
    private final static int SQUARE_SIZE = 75;
    
    public void paintPieceList(Collection<Piece> pieceList) {
        try {
            removeAll();
            add(board, new Integer(0));
            board.setSize(board.getSize());
            for (Piece piece : pieceList) {
                String path = null;
                switch (piece.getType()) {
                    case MOUSE:
                        path = "pieces/mouse.png";
                        break;
                    case CAT:
                        path = "pieces/cat.png";
                        break;
                    case WOLF:
                        path = "pieces/wolf.png";
                        break;
                    case DOG:
                        path = "pieces/dog.png";
                        break;
                    case LEOPARD:
                        path = "pieces/leopard.png";
                        break;
                    case TIGER:
                        path = "pieces/tiger.png";
                        break;
                    case LION:
                        path = "pieces/lion.png";
                        break;
                    case ELEPHANT:
                        path = "pieces/elephant.png";
                        break;
                    default:
                        throw new NullPointerException("Piece type: " + piece.getType() + " can not be recognized");
                }
                PieceComponent pieceComponent = new PieceComponent(path);
                add(pieceComponent, new Integer(1));
                pieceComponent.setBounds(piece.getX() * SQUARE_SIZE, piece.getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                pieceComponent.addMouseListener(new MouseAdapter() {
                    
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        viewModel.setSelectedPiece(piece);
                    }
                });
            }
        } catch (IOException | NullPointerException e) {
            viewModel.setError(e);
        }
    }
    
    protected class BoardCompnent extends BaseComponent {
        
        protected final static float SQUARE_PADDING = 1 / 15f;
        private final static int SQUARE_RADIUS = 10;
        private final static float SQUARE_CONTENT_PADDING = 1 / 5f;
        
        @Override
        protected void paintComponent(Graphics2D g) {
            Color lightGreen = new Color(118, 255, 3);
            Color darkGreen = new Color(100, 221, 23);
            
            try {
                Element backgroundElement = new Element(SQUARE_RADIUS, SQUARE_PADDING);
                Element foregroundElement = new Element(0, SQUARE_CONTENT_PADDING);
                
                SquareType[][] grid = viewModel.getCurrentJungleGame().get().getBoard().getGrid();
                for (int i = 0; i < grid.length; i++) {
                    SquareType[] column = grid[i];
                    for (int j = 0; j < column.length; j++) {
                        SquareType squareType = grid[i][j];
                        if (squareType == SquareType.RIVER) {
                            backgroundElement.setImage(ImageReader.read("board/river.png"));
                            backgroundElement.draw(g, i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                        } else {
                            backgroundElement.setImage(null);
                            backgroundElement.setColor((i + j) % 2 > 0 ? darkGreen : lightGreen);
                            backgroundElement.draw(g, i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                            Image image = null;
                            switch (squareType) {
                                case TRAP:
                                    image = ImageReader.read("board/trap.png");
                                    break;
                                case DEN:
                                    image = ImageReader.read("board/den.png");
                                    break;
                            }
                            if (image != null) {
                                foregroundElement.setImage(image);
                                foregroundElement.draw(g, i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                viewModel.setError(e);
            }
        }
        
        @Override
        public Dimension getSize() {
            Board board = viewModel.getCurrentJungleGame().get().getBoard();
            return new Dimension(board.getWidth() * SQUARE_SIZE, board.getHeight() * SQUARE_SIZE);
        }
    }
}
