package com.chess.jungle.ui;

import com.chess.jungle.utils.Animation;

import java.awt.*;

/**
 * Draw images with background color to screen. Automatically resize image to fit into component.
 *
 * @author Chengjie Luo
 */
public class ImageComponent extends BaseComponent {

    private final static int ANIMATION_TIME = 200;

    private Image image;
    private Color color = Color.WHITE;

    private final Animation colorChangeAnimation = new Animation(0, 1);

    public ImageComponent() {
    }

    public ImageComponent(Image image, Color color) {
        this.image = image;
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics2D g) {
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (image == null) return;
        int width, height;
        int imageWidth = image.getWidth(null), imageHeight = image.getHeight(null);
        int comWidth = getWidth(), comHeight = getHeight();
        float widthRatio = imageWidth / (float) comWidth;
        float heightRatio = imageHeight / (float) comHeight;
        if (widthRatio > heightRatio) {
            height = comHeight;
            width = (int) (height / (float) imageHeight * imageWidth);
        } else {
            width = comWidth;
            height = (int) (width / (float) imageWidth * imageHeight);
        }
        g.drawImage(image, 0, 0, width, height, null);
    }

    public void setImage(Image image) {
        this.image = image;
        repaint();
    }

    public void setColor(Color color) {
        Color origin = this.color;
        int rDiff = color.getRed() - origin.getRed();
        int gDiff = color.getGreen() - origin.getGreen();
        int bDiff = color.getBlue() - origin.getBlue();
        colorChangeAnimation.setCallback((value, anime) -> {
            this.color = new Color((int) (origin.getRed() + rDiff * value), (int) (origin.getGreen() + gDiff * value), (int) (origin.getBlue() + bDiff * value));
            repaint();
        });
        colorChangeAnimation.play(true, ANIMATION_TIME);
    }

    @Override
    public Dimension getPreferredSize() {
        return null;
    }
}
