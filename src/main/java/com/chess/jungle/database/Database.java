package com.chess.jungle.database;

import com.chess.jungle.utils.LiveData;
import com.chess.jungle.utils.MutableLiveData;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author 10926
 */
public class Database {
    private volatile static Database database = new Database();
    Connection conn = null;
    Statement statement;
    DatabaseMetaData metas;
    ResultSet tables;
    ExecutorService executorService = Executors.newCachedThreadPool();
    private Database(){
        
    }
    public static Database getInstance(){
        if(database == null){
            synchronized(Database.class){
                if(database == null){
                    database.connectToDatabase();
                }
            }
        }
        return database;
    }
    public boolean connectToDatabase() {

        try {

            conn = DriverManager.getConnection("jdbc:derby:Jungle;create=true");
            statement = conn.createStatement();

            metas = conn.getMetaData();
            tables = metas.getTables(conn.getCatalog(), null, "RANKINGLIST", null);
            if (!tables.next()) {

                createRANKINGLISTTable();
            }
            return true;
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            return false;
        }
    }
    public void getRankingList(MutableLiveData mutableLiveData){
        executorService.submit(()->{
            System.out.println("正在执行");
            try {
                mutableLiveData.setValue(database.getAllData());
            } catch (Exception e) {
            }
            System.out.println("执行完成");
        });
    }
    public void createRANKINGLISTTable() {
        try {
            statement.execute("CREATE TABLE RANKINGLIST (PLAYERNAME VARCHAR(15), WIN INT, LOSS INT)");
        } catch (Exception e) {
            System.err.println("SQLException from createPlayerTable: " + e.getMessage());
        }
    }
    public ResultSet getAllData() {
        ResultSet rs = null;
        try {

            rs = statement.executeQuery("SELECT * FROM RANKINGLIST");

        } catch (Exception e) {

            System.err.println("SQLException from getAllData: " + e.getMessage());
        }

        return (rs);
    }
    public boolean checkPlayerRecord(String name) {

        boolean RankingHasRecord = false;

        ResultSet allData = getAllData();

        try {
            while (allData.next()) {

                String recordName = allData.getString("PLAYERNAME");
                if (recordName.equals(name)) {

                    RankingHasRecord = true;
                }
            }
        } catch (SQLException ex) {
            System.err.println("SQLException from checkPlayerRecord: " + ex.getMessage());
        }
        return RankingHasRecord;
    }
    public void createPlayerRecord(String name) {

        try {
            // Create record with 0 wins and losses
            statement.execute("INSERT INTO RANKINKList VALUES ('" + name + "', 0, 0)");
        } catch (SQLException ex) {
            System.err.println("SQLException from createPlayerRecord: " + ex.getMessage());
        }
    }
    public ResultSet getPlayerRecord(String name) {

        ResultSet playerRecord = null;

        try {
            // Get player record     
            playerRecord = statement.executeQuery("SELECT NAME, WIN, LOSS FROM RANKINKLIST WHERE NAME ='" + name + "'");

        } catch (SQLException ex) {
            System.err.println("SQLException from getPlayerRecord: " + ex.getMessage());
        }

        return playerRecord;
    }
    public void updateRecord(String name, boolean won) {

        try {
            // First get the player record from the database
            ResultSet playerRecord = getPlayerRecord(name);
            // if player record exists
            if (playerRecord.next()) {
                // If player just won a game, update record with +1 wins
                if (won) {
                    int wins = playerRecord.getInt("WIN");
                    statement.executeUpdate("UPDATE RANKINKLIST SET WIN = " + (++wins) + " WHERE NAME ='" + name + "'");

                } // Else if player just lost a game, update record with +1 losses
                else {
                    int losses = playerRecord.getInt("LOSS");
                    statement.executeUpdate("UPDATE PLAYER_INFO SET LOSS = " + (++losses) + " WHERE NAME ='" + name + "'");

                }
            }
        } catch (SQLException ex) {
            System.err.println("SQLException from getPlayerRecord: " + ex.getMessage());
        }
    }
    public void deleteRecord(String name) {

        try {
            // Delete a certain player record
            statement.execute("DELETE FROM RANKINGLIST WHERE NAME = '" + name + "'");
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
            statement.execute("TRUNCATE TABLE RANKINGLIST");
        } catch (SQLException ex) {
            System.err.println("SQLException from deleteRecord: " + ex.getMessage());
        }
    }
    public static void main(String[] args) throws Exception{
        Database database1 = Database.getInstance();
        ResultSet resultSet = database1.getAllData();
        System.out.println(resultSet);
    }
}
