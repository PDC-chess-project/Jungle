package com.chess.jungle.ui;

import com.chess.jungle.database.User;
import com.chess.jungle.ui.layout.CustomLayout;
import com.chess.jungle.utils.Colors;
import com.chess.jungle.utils.LiveData;
import com.chess.jungle.viewModel.LeaderBoardViewModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * An panel represent a leaderBoard.
 *
 * @author Chengjie Luo
 */
public class LeaderboardPanel extends JPanel {

    private final LeaderBoardViewModel viewModel = LeaderBoardViewModel.get();

    public LeaderboardPanel() {
        setLayout(new CustomLayout(CustomLayout.Orientation.VERTICAL));
        setBackground(Colors.TRANSPARENT);
        add(new LeaderboardTablePanel(viewModel.getLeaderboard()));
        add(new JButton(new AbstractAction("Clear all record") {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.clearLeaderboard();
            }
        }));
    }

    @Override
    public Dimension getPreferredSize() {
        return null;
    }

    static class LeaderboardTablePanel extends JScrollPane {

        LeaderboardTablePanel(LiveData<List<User>> liveData) {
            super(new JTable(new TableModel(liveData)));
            JTable table = (JTable) getViewport().getView();
            table.setFillsViewportHeight(true);
        }

        static class TableModel extends AbstractTableModel {

            private final static String[] columns = new String[]{"Name", "Win", "Lost"};
            private final LiveData<List<User>> liveData;

            TableModel(LiveData<List<User>> liveData) {
                this.liveData = liveData;
                liveData.observe(v -> fireTableDataChanged());
            }

            @Override
            public int getRowCount() {
                return liveData.get() == null ? 0 : liveData.get().size();
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
                if (liveData.get() == null)
                    return null;
                User user = liveData.get().get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return user.getName();
                    case 1:
                        return user.getWin();
                    case 2:
                        return user.getLoss();
                    default:
                        return null;
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return null;
        }
    }
}
