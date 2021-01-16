package com.chess.jungle.database;

import com.chess.jungle.utils.LiveData;
import com.chess.jungle.utils.MutableLiveData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author 10926
 */
public class Database {

    protected final MutableLiveData<List<User>> userListLiveData = new MutableLiveData<>();

    private volatile static Database database;
    private Connection conn;
    private final Executor executor = Executors.newSingleThreadExecutor();

    protected Database() {
    }

    /**
     * @return database
     */
    public static Database getInstance() {
        if (database == null) {
            synchronized (Database.class) {
                if (database == null) {
                    database = new Database();
                    database.connectToDatabase();
                }
            }
        }
        return database;
    }

    private void connectToDatabase() {
        executor.execute(this::connectToDatabaseSync);
    }

    protected void connectToDatabaseSync() {
        try {
            conn = DriverManager.getConnection("jdbc:derby:Jungle;create=true");

            createLeaderBoardTable();

        } catch (Exception ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    private void createLeaderBoardTable() {
        try {
            DatabaseMetaData metas = conn.getMetaData();
            ResultSet tables = metas.getTables(conn.getCatalog(), null, "LEADERBOARD", null);
            if (!tables.next()) {
                conn.createStatement().execute("CREATE TABLE LEADERBOARD (PlayerName VARCHAR(15), WIN INT, LOSS INT)");
            }
            tables.close();
        } catch (Exception e) {
            System.err.println("SQLException from createPlayerTable: " + e.getMessage());
        }
    }

    public LiveData<List<User>> getUserList() {
        refreshList();
        return userListLiveData;
    }

    protected void refreshList() {
        executor.execute(this::refreshListSync);
    }

    protected void refreshListSync() {
        try {
            ArrayList<User> userList = new ArrayList<>();
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM LEADERBOARD ORDER BY WIN DESC");
            while (resultSet.next()) {
                String name = resultSet.getString("PlayerName");
                int win = resultSet.getInt("WIN");
                int loss = resultSet.getInt("LOSS");
                userList.add(new User(name, win, loss));
            }
            this.userListLiveData.setValue(userList);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayerRecord(String name, boolean won) {
        executor.execute(() -> {
            createPlayerRecordSync(name, won);
            refreshListSync();
        });
    }

    protected void createPlayerRecordSync(String name, boolean won) {
        try {
            // Create record with 0 wins and losses
            if (!checkPlayerRecordSync(name)) {
                if (won) {
                    conn.createStatement().execute("INSERT INTO LEADERBOARD VALUES ('" + name + "', 1, 0) ");
                } else {
                    conn.createStatement().execute("INSERT INTO LEADERBOARD VALUES ('" + name + "', 0, 1) ");
                }
            } else {
                updateRecordSync(name, won);
            }

        } catch (SQLException ex) {
            System.err.println("SQLException from createPlayerRecord: " + ex.getMessage());
        }
    }

    /**
     * @param name name
     * @return rs.next()
     */
    public boolean checkPlayerRecordSync(String name) {
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT PlayerName, WIN, LOSS FROM LEADERBOARD WHERE PlayerName ='" + name + "'");
            boolean next = rs.next();
            rs.close();
            return next;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete all records in database
     */
    public void deleteAllRecords() {
        executor.execute(() -> {
            deleteAllRecordsSync();
            refreshListSync();
        });
    }

    protected void deleteAllRecordsSync() {
        try {
            // Delete a certain player record
            conn.createStatement().execute("TRUNCATE TABLE LEADERBOARD");
        } catch (SQLException ex) {
            System.err.println("SQLException from deleteRecord: " + ex.getMessage());
        }
    }

    /**
     * @param name name
     * @param won  won
     */
    protected void updateRecordSync(String name, boolean won) {

        try {
            // First get the player record from the database
            User user = getPlayerRecordSync(name);
            // if player record exists
            if (user != null) {
                // If player just won a game, update record with +1 wins
                if (won) {
                    conn.createStatement().executeUpdate("UPDATE LEADERBOARD SET WIN = " + (user.getWin() + 1) + " WHERE PlayerName ='" + name + "'");

                } // Else if player just lost a game, update record with +1 losses
                else {
                    conn.createStatement().executeUpdate("UPDATE LEADERBOARD SET LOSS = " + (user.getLoss() + 1) + " WHERE PlayerName ='" + name + "'");
                }
            }
        } catch (SQLException ex) {
            System.err.println("SQLException from getPlayerRecord: " + ex.getMessage());
        }
    }

    /**
     * Gets a single record from a given name
     *
     * @param name player record to get
     * @return resultset of player record
     */
    protected User getPlayerRecordSync(String name) {

        User user = null;

        try {
            // Get player record
            ResultSet playerRecord = conn.createStatement().executeQuery("SELECT PlayerName, WIN, LOSS FROM LEADERBOARD WHERE PlayerName ='" + name + "'");
            if (playerRecord.next())
                user = new User(playerRecord.getString("PlayerName"), playerRecord.getInt("WIN"), playerRecord.getInt("LOSS"));
            playerRecord.close();
        } catch (SQLException ex) {
            System.err.println("SQLException from getPlayerRecord: " + ex.getMessage());
        }

        return user;
    }
}
