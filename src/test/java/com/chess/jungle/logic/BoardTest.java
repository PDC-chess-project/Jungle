package com.chess.jungle.logic;

import org.junit.*;

import static org.junit.Assert.*;

/**
 *
 * @author Liangwei Chen
 */
public class BoardTest {
    
    public BoardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getGrid method, of class Board.
     */
    @Test
    public void testGetGrid() {

    }

    /**
     * Test of getWidth method, of class Board.
     */
    @Test
    public void testGetWidth() {

    }

    /**
     * Test of getHeight method, of class Board.
     */
    @Test
    public void testGetHeight() {

    }

    /**
     * Test of isInBoard method, of class Board.
     */
    @Test
    public void testIsInBoard_Coordinate() {

    }

    /**
     * Test of isInBoard method, of class Board.
     */
    @Test
    public void testIsInBoard_int_int() {
        Board board = new Board();
        Assert.assertFalse(board.isInBoard(-1,-1));
        Assert.assertFalse(board.isInBoard(-1,0));
        Assert.assertFalse(board.isInBoard(0,-1));
        Assert.assertFalse(board.isInBoard(7,9));
        Assert.assertFalse(board.isInBoard(6,9));
        Assert.assertFalse(board.isInBoard(7,8));
        Assert.assertTrue(board.isInBoard(0,0));
        Assert.assertTrue(board.isInBoard(6,8));
    }

    /**
     * Test of isRiver method, of class Board.
     */
    @Test
    public void testIsRiver() {

    }

    /**
     * Test of isTrap method, of class Board.
     */
    @Test
    public void testIsTrap() {

    }

    /**
     * Test of isDen method, of class Board.
     */
    @Test
    public void testIsDen() {

    }

    /**
     * Test of getOppositeShore method, of class Board.
     */
    @Test
    public void testGetOppositeShore() {
        System.out.println("getOppositeShore");
        Coordinate c = new Coordinate(1,2);
        Board instance = new Board();
        Coordinate result = instance.getOppositeShore(c, Direction.DOWN);
        Assert.assertEquals(new Coordinate(1,6), result);
        c = new Coordinate(0,3);
        result = instance.getOppositeShore(c,Direction.RIGHT);
        Assert.assertEquals(new Coordinate(3,3),result);
        c = new Coordinate(1,6);
        result = instance.getOppositeShore(c,Direction.UP);
        Assert.assertEquals(new Coordinate(1,2),result);
        c = new Coordinate(0,0);
        result = instance.getOppositeShore(c,Direction.RIGHT);
        Assert.assertEquals(new Coordinate(1,0),result);
    }
    
}
