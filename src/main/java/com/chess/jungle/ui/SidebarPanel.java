package com.chess.jungle.ui;

import com.chess.jungle.ui.layout.CustomLayout;
import com.chess.jungle.utils.Animation;
import com.chess.jungle.utils.ImageReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * @author Chengjie Luo
 */
public class SidebarPanel extends JPanel {

    private final static int WIDTH = 300;
    private final static int PADDING = 20;
    private final static int TITLE_HEIGHT = 50;
    private final static int ANIMATION_TIME = 100;
    private final static int ALPHA = 180;

    private final JLabel title = new JLabel();
    private JComponent content;

    private final Animation openAnimation = new Animation(0, WIDTH, this::setWidth);

    public SidebarPanel() {
        setLayout(null);
        setBackground(new Color(0, 0, 0, ALPHA));
        title.setForeground(Color.WHITE);
        title.setFont(title.getFont().deriveFont(20f));
        title.setBounds(PADDING, PADDING, 100, TITLE_HEIGHT);
        add(title);
        JLabel closeButton = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(ImageReader.read("close.png", 30, 30));
            closeButton.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                close();
            }
        });
        closeButton.setBounds(WIDTH - PADDING - 30, PADDING, 30, 50);
        add(closeButton);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
    }

    public void open(String name, JComponent content) {
        title.setText(name);
        if (this.content != null)
            remove(this.content);
        content.setBounds(PADDING, PADDING * 2 + TITLE_HEIGHT, WIDTH - 2 * PADDING, content.getPreferredSize().height);
        add(content);
        this.content = content;
        openAnimation.play(true, ANIMATION_TIME);
    }

    protected void setWidth(float width, Animation animation) {
        size = new Dimension((int) width, CustomLayout.MATCH_PARENT);
        getParent().revalidate();
    }

    public void close() {
        openAnimation.play(false, ANIMATION_TIME);
    }

    private Dimension size = new Dimension(0, CustomLayout.MATCH_PARENT);

    @Override
    public Dimension getPreferredSize() {
        return size;
    }
}
