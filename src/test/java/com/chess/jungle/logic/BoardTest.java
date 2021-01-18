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
        System.out.println("getGrid");
        Board instance = new Board();
        Board.SquareType[][] expResult = null;
        Board.SquareType[][] result = instance.getGrid();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWidth method, of class Board.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        Board instance = new Board();
        int expResult = 0;
        int result = instance.getWidth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHeight method, of class Board.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        Board instance = new Board();
        int expResult = 0;
        int result = instance.getHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isInBoard method, of class Board.
     */
    @Test
    public void testIsInBoard_Coordinate() {
        System.out.println("isInBoard");
        Coordinate coordinate = null;
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.isInBoard(coordinate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isInBoard method, of class Board.
     */
    @Test
    public void testIsInBoard_int_int() {
        System.out.println("isInBoard");
        int x = 0;
        int y = 0;
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.isInBoard(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRiver method, of class Board.
     */
    @Test
    public void testIsRiver() {
        System.out.println("isRiver");
        Coordinate c = null;
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.isRiver(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isTrap method, of class Board.
     */
    @Test
    public void testIsTrap() {
        System.out.println("isTrap");
        Coordinate c = null;
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.isTrap(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDen method, of class Board.
     */
    @Test
    public void testIsDen() {
        System.out.println("isDen");
        Coordinate c = null;
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.isDen(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
