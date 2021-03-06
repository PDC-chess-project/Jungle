package com.chess.jungle.ui;

import com.chess.jungle.logic.Board;
import com.chess.jungle.logic.JungleGame;
import com.chess.jungle.utils.Colors;
import com.chess.jungle.utils.ImageReader;
import com.chess.jungle.utils.LiveData;
import com.chess.jungle.viewModel.ErrorViewModel;
import com.chess.jungle.viewModel.GameViewModel;

import java.awt.*;
import java.io.IOException;

import static com.chess.jungle.ui.BoardPanel.SQUARE_SIZE;

/**
 * Draw a board to screen.
 *
 * @author Chengjie Luo
 */
class BoardComponent extends BaseComponent {

    protected static final float SQUARE_PADDING = 1 / 15f;
    private static final int SQUARE_RADIUS = 10;
    private static final float SQUARE_CONTENT_PADDING = 1 / 5f;

    private Board board;

    private final Element backgroundElement = new Element(SQUARE_RADIUS, SQUARE_PADDING);
    private final Element foregroundElement = new Element(0, SQUARE_CONTENT_PADDING);

    public BoardComponent() {
        GameViewModel viewModel = GameViewModel.get();
        viewModel.getCurrentJungleGame().stickyObserve((game) -> {
            if (game == null) return;
            this.board = game.getBoard();
            repaint();
        });
    }

    @Override
    protected void paintComponent(Graphics2D g) {
        if (board == null) {
            return;
        }

        try {
            Board.SquareType[][] grid = board.getGrid();
            for (int i = 0; i < grid.length; i++) {
                Board.SquareType[] column = grid[i];
                for (int j = 0; j < column.length; j++) {
                    Board.SquareType squareType = grid[i][j];
                    if (squareType == Board.SquareType.RIVER) {
                        backgroundElement.setImage(ImageReader.read("board/river.png"));
                        backgroundElement.draw(g, i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                    } else {
                        backgroundElement.setImage(null);
                        backgroundElement.setColor((i + j) % 2 > 0 ? Colors.DARK_GREEN : Colors.LIGHT_GREEN);
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
            ErrorViewModel.get().setError(e);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (board == null) return null;
        return new Dimension(board.getWidth() * SQUARE_SIZE, board.getHeight() * SQUARE_SIZE);
    }
}
