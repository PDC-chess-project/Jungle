package com.chess.jungle.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;

/**
 *
 * @author Chengjie Luo
 */
public abstract class BaseComponent extends JComponent {
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        paintComponent(g2d);
    }
    
    protected abstract void paintComponent(Graphics2D g);
    
    public static class Element {
        
        public static class Border {
            
            public final Color color;
            public final float size;
            
            public Border(Color color, float size) {
                this.color = color;
                this.size = size;
            }
        }
        
        protected Color color = Color.WHITE;
        protected Image image = null;
        protected int radius;
        protected float padding;
        protected int shadow = 0;
        protected Border border = null;
        
        public Element(Color color, int radius, float padding) {
            this(radius, padding);
            this.color = color;
        }
        
        public Element(int radius, float padding) {
            this.radius = radius;
            this.padding = padding;
        }

        public void draw(Graphics2D g, int x, int y, int width, int height) {
            int realX, realY, realWidth, realHeight;
            int horizontalPadding = (int) (width * padding);
            int verticalPadding = (int) (height * padding);
            realX = x + horizontalPadding;
            realY = y + verticalPadding;
            realWidth = width - 2 * horizontalPadding;
            realHeight = height - 2 * verticalPadding;
            if (shadow > 0) {
                drawShadow(g, realX, realY, realWidth, realHeight);
            }
            g.setColor(color);
            if (image != null) {
                g.setClip(new RoundRectangle2D.Float(realX, realY, realWidth, realHeight, radius, radius));
                g.drawImage(image, realX, realY, realWidth, realHeight, null);
                g.setClip(null);
            } else {
                g.fillRoundRect(realX, realY, realWidth, realHeight, radius, radius);
            }
            if (border != null) {
                g.setColor(border.color);
                g.setStroke(new BasicStroke(border.size));
                g.drawRoundRect(realX, realY, realWidth, realHeight, radius, radius);
            }
        }
        
        protected void drawShadow(Graphics g, int x, int y, int width, int height) {
            int maxOpacity = 120;
            double total = Math.pow(1.1, shadow) - 1;
            g.setColor(new Color(0, 0, 0, maxOpacity));
            g.fillRect(x, y + height - radius, width, radius);
            for (int i = 0; i < shadow; i++) {
                int alpha = (int) (maxOpacity * (1 - (Math.pow(1.1, i) - 1) / total));
                g.setColor(new Color(0, 0, 0, alpha));
                g.fillRect(x, y + height + i, width, 1);
            }
        }
        
        public void setColor(Color color) {
            this.color = color;
        }
        
        public void setImage(Image image) {
            this.image = image;
        }
        
        public void setRadius(int radius) {
            this.radius = radius;
        }
        
        public void setPadding(float padding) {
            this.padding = padding;
        }
        
        public void setShadow(int shadow) {
            this.shadow = shadow;
        }
        
        public void setBorder(Border border) {
            this.border = border;
        }
    }
}
