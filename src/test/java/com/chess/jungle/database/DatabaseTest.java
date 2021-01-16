package com.chess.jungle.database;

import com.chess.jungle.utils.MutableLiveData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.sql.ResultSet;

/** 
* Database Tester. 
* 
* @author <Authors name> 
* @since <pre>1 15, 2021</pre>
* @version 1.0 
*/ 
public class DatabaseTest { 
private Database database;
@Before
public void before() {
    database = Database.getInstance();
    database.createPlayerRecord("testName",true);
} 

@After
public void after() {
    database.deleteAllRecords();
    database = null;
} 

/** 
* 
* Method: getInstance() 
* 
*/ 
@Test
public void testGetInstance() {
    database = Database.getInstance();
    Assert.assertTrue(database.checkPlayerRecord("testName"));
} 

/** 
* 
* Method: getLeaderBoard(MutableLiveData<List<User>> mutableLiveData) 
* 
*/ 
@Test
public void testGetLeaderBoard() {
    database.getLeaderBoard(new MutableLiveData<>());
} 

/** 
* 
* Method: createPlayerRecord(String name, boolean won) 
* 
*/ 
@Test
public void testCreatePlayerRecord() {
    database.createPlayerRecord("createName",true);
    Assert.assertTrue(database.checkPlayerRecord("createName"));
} 

/** 
* 
* Method: deleteAllRecords() 
* 
*/ 
@Test
public void testDeleteAllRecords() {
    database.deleteAllRecords();
    Assert.assertFalse(database.checkPlayerRecord("testName"));
}

/**
 *
 * Method: checkPlayerRecord()
 *
 */
@Test
public void checkPlayerRecord() {
    boolean isTrue = database.checkPlayerRecord("testName");
    Assert.assertTrue(isTrue);
}

/**
 *
 * Method: getPlayerRecord()
 *
 */
@Test
public void getPlayerRecord() throws Exception {
    ResultSet rs = database.getPlayerRecord("testName");
    Assert.assertTrue(rs.next());
}


} 
