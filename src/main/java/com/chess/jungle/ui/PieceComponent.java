package com.chess.jungle.ui;

import com.chess.jungle.utils.ImageReader;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import com.chess.jungle.ui.BaseComponent.Element;
import com.chess.jungle.utils.Animation;

/**
 *
 * @author Chengjie Luo
 */
public class PieceComponent extends BaseComponent {

    private final static float IMAGE_PADDING = 1 / 5f;
    private final static int RADIUS = 10;
    private final static int ANIMATION_TIME = 100;

    private final Animation animation = new Animation(0, 15, this::setShadowPixels);

    private final Element backgroundElement = new Element(Color.RED, RADIUS, BoardComponent.SQUARE_PADDING);
    private final Element foregroundImage = new Element(0, IMAGE_PADDING);

    private int shadowPixels = 0;

    public PieceComponent(String path) throws IOException {
        foregroundImage.setImage(ImageReader.read(path));
        backgroundElement.setShadow(shadowPixels);
    }

    @Override
    protected void paintComponent(Graphics2D g) {
        backgroundElement.draw(g, 0, 0, getWidth(), getWidth());
        foregroundImage.draw(g, 0, 0, getWidth(), getWidth());
    }

    public void setIsElevated(boolean isElevated) {
        animation.play(isElevated, ANIMATION_TIME);
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
