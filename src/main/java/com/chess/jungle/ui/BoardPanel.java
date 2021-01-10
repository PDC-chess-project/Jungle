package com.chess.jungle.ui;

import com.chess.jungle.logic.Board;
import com.chess.jungle.viewModel.GameViewModel;
import java.awt.Graphics;
import javax.swing.JComponent;
import com.chess.jungle.logic.Board.SquareType;
import com.chess.jungle.logic.JungleGame;
import com.chess.jungle.logic.Piece;
import com.chess.jungle.utils.ImageReader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Collection;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 *
 * @author Chengjie Luo
 */
public class BoardPanel extends JLayeredPane {
    
    protected GameViewModel viewModel = GameViewModel.get();
    
    private BoardCompnent board = new BoardCompnent();
    
    public BoardPanel() {
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
                JLabel pieceLabel = new JLabel(new ImageIcon(ImageReader.read(path, SQUARE_SIZE, SQUARE_SIZE)));
                add(pieceLabel, new Integer(1));
                pieceLabel.setSize(SQUARE_SIZE, SQUARE_SIZE);
                pieceLabel.setBounds(piece.getX() * SQUARE_SIZE, piece.getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                pieceLabel.addMouseListener(new MouseAdapter() {
                    
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
    
    protected class BoardCompnent extends JComponent {
        
        @Override
        protected void paintComponent(Graphics g) {
            try {
                SquareType[][] grid = viewModel.getCurrentJungleGame().get().getBoard().getGrid();
                for (int i = 0; i < grid.length; i++) {
                    SquareType[] column = grid[i];
                    for (int j = 0; j < column.length; j++) {
                        g.setColor(Color.BLACK);
                        g.drawImage(ImageReader.read((i + j) % 2 > 0 ? "board/grass_2.jpg" : "board/grass_5.jpg"), i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, null);
                        SquareType squareType = grid[i][j];
                        Image image = null;
                        switch (squareType) {
                            case TRAP:
                                image = ImageReader.read("board/trap_2.png");
                                break;
                            case DEN:
                                image = ImageReader.read("board/nest_1.png");
                                break;
                            case RIVER:
                                image = ImageReader.read("board/river_5.png");
                                break;
                        }
                        if (image != null) {
                            g.drawImage(image, i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, null);
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
