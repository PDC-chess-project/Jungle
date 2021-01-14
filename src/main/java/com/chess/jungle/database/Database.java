package com.chess.jungle.database;

import com.chess.jungle.utils.MutableLiveData;

import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author 10926
 */
public class Database {

    private volatile static Database database;
    private Statement statement;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    private Database() {

    }

    /**
     *
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

            Connection conn = DriverManager.getConnection("jdbc:derby:Jungle;create=true");
            statement = conn.createStatement();

            DatabaseMetaData metas = conn.getMetaData();
            ResultSet tables = metas.getTables(conn.getCatalog(), null, "LeaderBoard", null);
            if (!tables.next()) {
                createRankingListTable();
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    /**
     * set value
     * @param mutableLiveData mutableLiveData
     */
    public void getLeaderBoard(MutableLiveData mutableLiveData) {
        executorService.submit(() -> {
            System.out.println("正在执行");
            try {
                mutableLiveData.setValue(database.getAllData());
            } catch (Exception e) {
            }
            System.out.println("执行完成");
        });
    }

    /**
     *  create table
     */
    private void createRankingListTable() {
        try {
            statement.execute("CREATE TABLE LeaderBoard (PlayerName VARCHAR(15), WIN INT, LOSS INT)");
        } catch (Exception e) {
            System.err.println("SQLException from createPlayerTable: " + e.getMessage());
        }
    }

    /**
     * Get all data from LeaderBoard
     * @return ResultSet rs
     */
    private ResultSet getAllData() {
        ResultSet rs = null;
        try {

            rs = statement.executeQuery("SELECT * FROM LeaderBoard");

        } catch (Exception e) {

            System.err.println("SQLException from getAllData: " + e.getMessage());
        }

        return (rs);
    }

    /**
     * Check if the current player name already exists
     * @param name name
     * @return RankingHsRecord
     */
    public boolean checkPlayerRecord(String name) {

        boolean RankingHasRecord = false;

        ResultSet allData = getAllData();

        try {
            while (allData.next()) {

                String recordName = allData.getString("PlayerName");
                if (recordName.equals(name)) {

                    RankingHasRecord = true;
                }
            }
        } catch (SQLException ex) {
            System.err.println("SQLException from checkPlayerRecord: " + ex.getMessage());
        }
        return RankingHasRecord;
    }

    /**
     * Insert player information
     * @param name name
     */
    public void createPlayerRecord(String name) {

        try {
            // Create record with 0 wins and losses
            statement.execute("INSERT INTO LeaderBoard VALUES ('" + name + "', 0, 0)");
        } catch (SQLException ex) {
            System.err.println("SQLException from createPlayerRecord: " + ex.getMessage());
        }
    }

    /**
     * get player information
     * @param name name
     * @return playerRecord
     */
    public ResultSet getPlayerRecord(String name) {

        ResultSet playerRecord = null;

        try {
            // Get player record     
            playerRecord = statement.executeQuery("SELECT NAME, WIN, LOSS FROM LeaderBoard WHERE NAME ='" + name + "'");

        } catch (SQLException ex) {
            System.err.println("SQLException from getPlayerRecord: " + ex.getMessage());
        }

        return playerRecord;
    }

    /**
     * Update the information of existing players
     * @param name name
     * @param won won
     */
    public void updateRecord(String name, boolean won) {

        try {
            // First get the player record from the database
            ResultSet playerRecord = getPlayerRecord(name);
            // if player record exists
            if (playerRecord.next()) {
                // If player just won a game, update record with +1 wins
                if (won) {
                    int wins = playerRecord.getInt("WIN");
                    statement.executeUpdate("UPDATE LeaderBoard SET WIN = " + (++wins) + " WHERE NAME ='" + name + "'");

                } // Else if player just lost a game, update record with +1 losses
                else {
                    int losses = playerRecord.getInt("LOSS");
                    statement.executeUpdate("UPDATE LeaderBoard SET LOSS = " + (++losses) + " WHERE NAME ='" + name + "'");

                }
            }
        } catch (SQLException ex) {
            System.err.println("SQLException from getPlayerRecord: " + ex.getMessage());
        }
    }

    /**
     * Delete a certain player record
     * @param name name
     */
    public void deleteRecord(String name) {

        try {
            statement.execute("DELETE FROM LeaderBoard WHERE PlayerName = '" + name + "'");
        } catch (SQLException ex) {
            System.err.println("SQLException from deleteRecord: " + ex.getMessage());
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


}
