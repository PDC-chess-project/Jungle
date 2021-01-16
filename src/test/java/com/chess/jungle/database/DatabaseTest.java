package com.chess.jungle.database;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * Database Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>1�� 16, 2021</pre>
 */
public class DatabaseTest {
    private Database database;

    @Before
    public void before() {
        database = new Database();
        database.connectToDatabaseSync();
        database.deleteAllRecordsSync();
        database.createPlayerRecordSync("testName", true);
    }

    @After
    public void after() {
        database.deleteAllRecordsSync();
        database = null;
    }

    @Test
    public void testRefreshList() {
        database.refreshListSync();
        Assert.assertEquals(1, database.userListLiveData.get().size());
    }

    /**
     * Method: createPlayerRecordSync(String name, boolean won)
     */
    @Test
    public void testCreatePlayerRecordSyncTrue() {
        database.createPlayerRecordSync("createName", true);
        Assert.assertTrue(database.checkPlayerRecordSync("createName"));
    }

    /**
     * Method: createPlayerRecordSync(String name, boolean won)
     */
    @Test
    public void testCreatePlayerRecordSyncFalse() {
        database.createPlayerRecordSync("createName", false);
        Assert.assertTrue(database.checkPlayerRecordSync("createName"));
    }

    /**
     * Method: updateRecordSync(String name, boolean won)
     */
    @Test
    public void testUpdateRecordSyncWin() {
        database.updateRecordSync("testName", true);
        User user = database.getPlayerRecordSync("testName");
        Assert.assertEquals(user.getWin(), 2);
    }

    /**
     * Method: updateRecordSync(String name, boolean won)
     */
    @Test
    public void testUpdateRecordSyncLoss() {
        database.updateRecordSync("testName", false);
        User user = database.getPlayerRecordSync("testName");
        Assert.assertEquals(user.getLoss(), 1);
    }

    /**
     * Method: deleteAllRecordsSync()
     */
    @Test
    public void testDeleteAllRecordsSync() {
        database.deleteAllRecordsSync();
        boolean isFalse = database.checkPlayerRecordSync("testName");
        Assert.assertFalse(isFalse);
    }
} 
