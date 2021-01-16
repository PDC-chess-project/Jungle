package com.chess.jungle.database;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/** 
* Database Tester. 
* 
* @author Chenfan Wang
* @since <pre>1, 14, 2021</pre>
* @version 1.0 
*/ 
public class DatabaseTest {
private Database database;
@Before
public void setUp() throws Exception {
    database = Database.getInstance();
    database.createPlayerRecord("testName");
} 

@After
public void tearDown() throws Exception {
    database.deleteAllRecords();
    database = null;
} 

/**
* 
* Method: getInstance() 
* 
*/ 
@Test
public void testGetInstance() throws Exception { 
//TODO: Test goes here...
    database = Database.getInstance();
} 

/** 
* 
* Method: getLeaderBoard(MutableLiveData mutableLiveData) 
* 
*/ 
@Test
public void testGetLeaderBoard() throws Exception { 
//TODO: Test goes here...

} 

/** 
* 
* Method: checkPlayerRecord(String name) 
* 
*/ 
@Test
public void testCheckPlayerRecord() throws Exception { 
//TODO: Test goes here...
    String name = "testName";
    database.checkPlayerRecord(name);

} 

/** 
* 
* Method: createPlayerRecord(String name) 
* 
*/ 
@Test
public void testCreatePlayerRecord() throws Exception { 
//TODO: Test goes here...
    String name = "createName";
    database.createPlayerRecord(name);
} 

/** 
* 
* Method: getPlayerRecord(String name) 
* 
*/ 
@Test
public void testGetPlayerRecord() throws Exception { 
//TODO: Test goes here...
    ResultSet resultSet = database.getPlayerRecord("testName");
    try {
        while(resultSet.next()){
            String recordName = "" + resultSet.getString("PlayerName") + "";
            assertEquals(recordName,"testName");
        }
    }catch (SQLException e){
        fail("Fail at testGetPlayerRecord Test");
    }
} 

/** 
* 
* Method: updateRecord(String name, boolean won) 
* 
*/ 
@Test
public void testUpdateRecordWin() throws Exception {
//TODO: Test goes here...
    database.updateRecord("testName",true);
    ResultSet resultSet = database.getPlayerRecord("testName");

    try {
        while(resultSet.next()){
            int wins = resultSet.getInt("WIN");
            assertEquals(wins,1);
        }
    }catch (SQLException e){
            fail("Fail at testUpdateRecordWin Test");
    }
}
    public void testUpdateRecordLoss() throws Exception {
//TODO: Test goes here...
        database.updateRecord("testName",false);
        ResultSet resultSet = database.getPlayerRecord("testName");

        try {
            while(resultSet.next()){
                int losses = resultSet.getInt("LOSS");
                assertEquals(losses,-1);
            }
        }catch (SQLException e){
            fail("Fail at testUpdateRecordWin Test");
        }
    }
/** 
* 
* Method: deleteRecord(String name) 
* 
*/ 
@Test
public void testDeleteRecord() throws Exception {
//TODO: Test goes here...
    database.deleteRecord("testName");
    database.checkPlayerRecord("testName");
} 

/** 
* 
* Method: deleteAllRecords() 
* 
*/ 
@Test
public void testDeleteAllRecords() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: connectToDatabase() 
* 
*/ 
@Test
public void testConnectToDatabase() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = Database.getClass().getMethod("connectToDatabase"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: createRankingListTable() 
* 
*/ 
@Test
public void testCreateRankingListTable() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = Database.getClass().getMethod("createRankingListTable"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getAllData() 
* 
*/ 
@Test
public void testGetAllData() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = Database.getClass().getMethod("getAllData"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
