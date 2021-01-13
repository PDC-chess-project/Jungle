package com.chess.jungle.ui;

import com.chess.jungle.logic.Piece;
import com.chess.jungle.utils.Animation;
import com.chess.jungle.utils.ImageReader;
import com.chess.jungle.utils.SequentialAnimation;

import java.awt.*;
import java.io.IOException;

import static com.chess.jungle.ui.BoardPanel.SQUARE_SIZE;

/**
 * Represent one single piece.
 * @author Chengjie Luo
 */
public class PieceComponent extends BaseComponent {

    private final static float IMAGE_PADDING = 1 / 5f;
    private final static int RADIUS = 10;
    private final static int ANIMATION_TIME = 100;
    protected final static int MAX_SHADOW = 7;
    protected final static int MIN_SHADOW = 0;

    private final Animation shadowAnimation = new Animation(MIN_SHADOW, MAX_SHADOW, this::setShadowPixels);
    private final Animation moveAnimation = new Animation(0, 1);
    private final SequentialAnimation sequentialAnimation = new SequentialAnimation();

    {
        sequentialAnimation.add("Shadow", shadowAnimation);
        sequentialAnimation.add("Move", moveAnimation);
    }

    private final Piece piece;

    private final Element backgroundElement = new Element(Color.RED, RADIUS, BoardComponent.SQUARE_PADDING);
    private final Element foregroundImage = new Element(0, IMAGE_PADDING);

    private boolean isElevated = false;
    private int shadowPixels = MIN_SHADOW;

    private boolean isSelected = false;

    public PieceComponent(Piece piece) throws IOException {
        this.piece = piece;
        String path;
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
        foregroundImage.setImage(ImageReader.read(path));
        backgroundElement.setColor(piece.getSide() == Piece.Side.BLUE ? Color.BLUE : Color.RED);
        backgroundElement.setShadow(shadowPixels);
        setPosition(piece.getX() * SQUARE_SIZE, piece.getY() * SQUARE_SIZE);
    }

    /**
     * Update the position of the component according to the piece reference.
     */
    public void updatePosition() {
        int x = getX(), y = getY();
        int xDistance = piece.getX() * SQUARE_SIZE - x;
        int yDistance = piece.getY() * SQUARE_SIZE - shadowPixels - y;
        if (xDistance == 0 && yDistance == 0) return;
        moveAnimation.setCallback((v, anim) -> setPosition((int) (xDistance * v + x), (int) (yDistance * v + y + shadowPixels)));
        sequentialAnimation.play("Move", true, ANIMATION_TIME);
    }

    public final void setPosition(int x, int y) {
        setBounds(x, y, SQUARE_SIZE, SQUARE_SIZE);
    }

    @Override
    protected void paintComponent(Graphics2D g) {
        backgroundElement.draw(g, 0, 0, getWidth(), getWidth());
        foregroundImage.draw(g, 0, 0, getWidth(), getWidth());
    }

    /**
     * Elevate the component by changing position and shadow.
     * @param isElevated whether it should be elevated
     */
    public void setIsElevated(boolean isElevated) {
        if (this.isElevated != isElevated) {
            this.isElevated = isElevated;
            sequentialAnimation.play("Shadow", isElevated, ANIMATION_TIME);
        }
    }

    protected void setShadowPixels(float currentValue, Animation animation1) {
        int original = shadowPixels;
        this.shadowPixels = (int) currentValue;
        if (shadowPixels == original) {
            return;
        }
        backgroundElement.setShadow(shadowPixels);
        setBounds(x, y, width, height);
        repaint();
    }

    /**
     * Set the state of selected. Take effect Immediately.
     * @param isSelected whether it is selected
     */
    public void setIsSelected(boolean isSelected) {
        if (this.isSelected != isSelected) {
            this.isSelected = isSelected;
            if (isSelected) {
                backgroundElement.setBorder(new Element.Border(Color.yellow, 6));
            } else {
                backgroundElement.setBorder(null);
            }
            repaint();
        }
    }

    public Piece getPiece() {
        return piece;
    }

    private int x, y, width, height;

    @Override
    public void setBounds(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        super.setBounds(x, y - shadowPixels, width, height + shadowPixels);
    }
}
