package com.chess.jungle.ui;

import com.chess.jungle.ui.layout.CustomLayout;
import com.chess.jungle.ui.layout.SidebarLayout;
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
    private final static int ANIMATION_TIME = 200;
    private final static int ALPHA = 180;

    private final JLabel title = new JLabel();
    private JComponent content;

    private final Animation openAnimation = new Animation(0, WIDTH, this::setWidth);

    public SidebarPanel() {
        setLayout(new SidebarLayout(WIDTH));
        setBackground(new Color(0, 0, 0, ALPHA));
        title.setForeground(Color.WHITE);
        title.setFont(title.getFont().deriveFont(20f));
        add(title, SidebarLayout.Constraints.TITLE);
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
        add(closeButton, SidebarLayout.Constraints.CLOSE);
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
        add(content, SidebarLayout.Constraints.CONTENT);
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
