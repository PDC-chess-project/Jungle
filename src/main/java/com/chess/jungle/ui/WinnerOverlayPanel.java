package com.chess.jungle.ui;

import com.chess.jungle.logic.Piece;
import com.chess.jungle.ui.layout.CustomLayout;
import com.chess.jungle.utils.Colors;
import com.chess.jungle.viewModel.GameViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Chengjie Luo
 */
public class WinnerOverlayPanel extends CustomPanel {

    private final static int OPACITY = 160;

    private final CenterPanel centerPanel = new CenterPanel();

    public WinnerOverlayPanel() {
        super();
        add(centerPanel, CustomLayout.Constraints.ABSOLUTE_CENTER);
        // A empty mouse listener to prevent event pass through.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(0, 0, 0, OPACITY));
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void setSide(Piece.Side side) {
        centerPanel.setSide(side);
    }

    static class CenterPanel extends JPanel {

        private final Dimension size = new Dimension(280, 100);

        private final JLabel winSideLabel = createJLabel("BLUE", Color.BLUE);

        CenterPanel() {
            setLayout(null);
            setBackground(Colors.TRANSPARENT);
            add(winSideLabel, 0, 0, Align.LEFT);
            add(createJLabel("WON", Color.WHITE), 0, 0, Align.RIGHT);
            add(new JButton(new AbstractAction("Play again") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GameViewModel.get().startNewGame();
                }
            }), 5, 0, Align.BOTTOM_LEFT);
            add(new JButton(new AbstractAction("Quit") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
                }
            }), 5, 0, Align.BOTTOM_RIGHT);
        }

        public void setSide(Piece.Side side) {
            if (side == Piece.Side.RED) {
                winSideLabel.setText("RED");
                winSideLabel.setForeground(Color.RED);
            } else {
                winSideLabel.setText("BLUE");
                winSideLabel.setForeground(Color.BLUE);
            }
        }

        private JLabel createJLabel() {
            JLabel label = new JLabel();
            label.setFont(label.getFont().deriveFont(50f));
            return label;
        }

        private JLabel createJLabel(String text, Color color) {
            JLabel label = createJLabel();
            label.setForeground(color);
            label.setText(text);
            return label;
        }

        enum Align {
            LEFT, RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
        }

        public void add(Component component, int x, int y, Align align) {
            component.setBackground(Colors.TRANSPARENT);
            Dimension preferredD = component.getPreferredSize();
            int realX = x, realY = y;
            if (align == Align.RIGHT || align == Align.BOTTOM_RIGHT)
                realX = size.width - preferredD.width - x;
            if (align == Align.BOTTOM_LEFT || align == Align.BOTTOM_RIGHT)
                realY = size.height - preferredD.height;
            component.setBounds(realX, realY, preferredD.width, preferredD.height);
            add(component);
        }

        @Override
        public Dimension getPreferredSize() {
            return size;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return null;
    }
}
