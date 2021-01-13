package com.chess.jungle.ui;

import java.awt.*;

/**
 * @author Chengjie Luo
 */
public class ImageComponent extends BaseComponent {

    private final Image image;
    private final Color color;

    public ImageComponent(Image image, Color color) {
        this.image = image;
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics2D g) {
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
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

    @Override
    public Dimension getPreferredSize() {
        return null;
    }
}
