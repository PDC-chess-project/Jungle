package com.chess.jungle.ui;

import com.chess.jungle.ui.layout.CustomLayout;
import com.chess.jungle.utils.Colors;
import com.chess.jungle.utils.LiveData;
import com.chess.jungle.utils.MutableLiveData;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * An panel represent a leaderBoard.
 *
 * @author Chengjie Luo
 */
public class LeaderboardPanel extends JPanel {

    public LeaderboardPanel() {
        setLayout(new CustomLayout(CustomLayout.Orientation.VERTICAL));
        setBackground(Colors.TRANSPARENT);
        MutableLiveData<List<String>> test = new MutableLiveData<>(new ArrayList<>());
        add(new LeaderboardTablePanel(test));
        add(new JButton("Clear record"));
    }

    @Override
    public Dimension getPreferredSize() {
        return null;
    }

    static class LeaderboardTablePanel extends JScrollPane {

        LeaderboardTablePanel(LiveData<List<String>> liveData) {
            super(new JTable(new TableModel(liveData)));
            JTable table = (JTable) getViewport().getView();
            table.setFillsViewportHeight(true);
        }

        static class TableModel extends AbstractTableModel {

            private final static String[] columns = new String[]{"Name", "Win", "Lose"};
            private final LiveData<List<String>> liveData;

            TableModel(LiveData<List<String>> liveData) {
                this.liveData = liveData;
                liveData.observe(v -> fireTableDataChanged());
            }

            @Override
            public int getRowCount() {
                return liveData.get().size();
            }

            @Override
            public int getColumnCount() {
                return columns.length;
            }

            @Override
            public String getColumnName(int index) {
                return columns[index];
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return liveData.get().get(rowIndex);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return null;
        }
    }
}
