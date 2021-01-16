package com.chess.jungle.database;

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

    private volatile static Database database;
    private Statement statement;
    private final Executor executorService = Executors.newSingleThreadExecutor();

    private Database() {

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

    /**
     * connect to Database
     */
    private void connectToDatabase() {

        try {
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby:Jungle;create=true");
            statement = conn.createStatement();

            createLeaderBoardTable(conn);

        } catch (Exception ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    /**
     * set value
     *
     * @param mutableLiveData mutableLiveData
     */
    public void getLeaderBoard(MutableLiveData<List<User>> mutableLiveData) {
        executorService.execute(() -> {
            ResultSet resultSet = database.getLeaderBoard();
            ArrayList<User> userList = null;
            try {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    String name = resultSet.getString("PlayerName");
                    int win = resultSet.getInt("WIN");
                    int loss = resultSet.getInt("LOSS");
                    userList.add(new User(name, win, loss));
                }
            } catch (SQLException e) {

            }
            mutableLiveData.setValue(userList);
        });
    }

    /**
     * create table
     */
    private void createLeaderBoardTable(Connection conn) {
        try {
            DatabaseMetaData metas = conn.getMetaData();
            ResultSet tables = metas.getTables(conn.getCatalog(), null, "LeaderBoard", null);
            if (!tables.next()) {
                statement.execute("CREATE TABLE LeaderBoard (PlayerName VARCHAR(15), WIN INT, LOSS INT)");
            }
        } catch (Exception e) {
            System.err.println("SQLException from createPlayerTable: " + e.getMessage());
        }
    }

    /**
     * Get all data from LeaderBoard
     *
     * @return ResultSet rs
     */
    private ResultSet getLeaderBoard() {
        ResultSet rs = null;
        try {

            rs = statement.executeQuery("SELECT * FROM LeaderBoard");

        } catch (Exception e) {

            System.err.println("SQLException from getLeaderBoard: " + e.getMessage());
        }

        return (rs);
    }

    /**
     * @param name name
     * @return rs.next()
     * @throws SQLException
     */
    protected boolean checkPlayerRecord(String name) throws SQLException {

        ResultSet rs = statement.executeQuery("SELECT PlayerName, WIN, LOSS FROM LeaderBoard WHERE PlayerName ='" + name + "'");
        if(!rs.next()){
            return false;
        }
        return true;
    }

    /**
     * Insert player information
     *
     * @param name name
     * @param won won
     */
    public void createPlayerRecord(String name, boolean won) {

        try {
            // Create record with 0 wins and losses
            if(!database.checkPlayerRecord(name)){
                if (won) {
                    statement.execute("INSERT INTO LeaderBoard VALUES ('" + name + "', 1, 0) ");
                } else {
                    statement.execute("INSERT INTO LeaderBoard VALUES ('" + name + "', 0, 1) ");
                }
            }else{
                updateRecord(name,won);
            }

        } catch (SQLException ex) {
            System.err.println("SQLException from createPlayerRecord: " + ex.getMessage());
        }
    }


    protected void updateRecord(String name, boolean won) {

        try {
            // First get the player record from the database
            ResultSet playerRecord = getPlayerRecord(name);
            // if player record exists
            if (playerRecord.next()) {
                // If player just won a game, update record with +1 wins
                if (won) {
                    int wins = playerRecord.getInt("WIN");
                    statement.executeUpdate("UPDATE LeaderBoard SET WIN = " + (++wins) + " WHERE PlayerName ='" + name + "'");

                } // Else if player just lost a game, update record with +1 losses
                else {
                    int losses = playerRecord.getInt("LOSS");
                    statement.executeUpdate("UPDATE LeaderBoard SET LOSS = " + (++losses) + " WHERE PlayerName ='" + name + "'");

                }
            }
        } catch (SQLException ex) {
            System.err.println("SQLException from getPlayerRecord: " + ex.getMessage());
        }
    }

    /**
     * Delete all records in database
     */
    public void deleteAllRecords() {

        try {
            // Delete a certain player record
            statement.execute("TRUNCATE TABLE LeaderBoard");
        } catch (SQLException ex) {
            System.err.println("SQLException from deleteRecord: " + ex.getMessage());
        }
    }

    /**
     * Gets a single record from a given name
     *
     * @param name player record to get
     * @return resultset of player record
     */
    protected ResultSet getPlayerRecord(String name) {

        ResultSet playerRecord = null;

        try {
            // Get player record
            playerRecord = statement.executeQuery("SELECT PlayerName, WIN, LOSS FROM LeaderBoard WHERE PlayerName ='" + name + "'");

        } catch (SQLException ex) {
            System.err.println("SQLException from getPlayerRecord: " + ex.getMessage());
        }

        return playerRecord;
    }

}
