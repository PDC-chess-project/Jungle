package com.chess.jungle.ui;

import static com.chess.jungle.ui.BoardPanel.SQUARE_SIZE;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A circle to indicate one candidate square
 *
 * @author Chengjie Luo
 */
public final class CandidateIndicator extends BaseComponent {

    protected final static int POINT_SIZE = 10;

    private final Element pointElement = new Element(POINT_SIZE, 0);

    public CandidateIndicator(int x, int y) {
        pointElement.setBorder(new Element.Border(Color.yellow, 2));
        setBounds(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    @Override
    protected void paintComponent(Graphics2D g) {
        pointElement.draw(g, (SQUARE_SIZE - POINT_SIZE) / 2, (SQUARE_SIZE - POINT_SIZE) / 2, POINT_SIZE, POINT_SIZE);
    }
}
