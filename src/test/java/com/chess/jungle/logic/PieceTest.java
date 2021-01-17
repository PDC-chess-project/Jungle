package com.chess.jungle.logic;

import org.junit.*;

import static org.junit.Assert.*;

/**
 *
 * @author Liangwei Chen
 */
public class PieceTest {
    
    public PieceTest() {
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
     * Test of getType method, of class Piece.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Piece instance = null;
        Piece.Type expResult = null;
        Piece.Type result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getX method, of class Piece.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Piece instance = null;
        int expResult = 0;
        int result = instance.getX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getY method, of class Piece.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Piece instance = null;
        int expResult = 0;
        int result = instance.getY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSide method, of class Piece.
     */
    @Test
    public void testGetSide() {
        System.out.println("getSide");
        Piece instance = null;
        Piece.Side expResult = null;
        Piece.Side result = instance.getSide();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCoordinate method, of class Piece.
     */
    @Test
    public void testSetCoordinate() {
        System.out.println("setCoordinate");
        Coordinate coordinate = null;
        Piece instance = null;
        instance.setCoordinate(coordinate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoordinate method, of class Piece.
     */
    @Test
    public void testGetCoordinate() {
        System.out.println("getCoordinate");
        Piece instance = null;
        Coordinate expResult = null;
        Coordinate result = instance.getCoordinate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isBiggerThan method, of class Piece.
     */
    @Test
    public void testIsBiggerThan() {
        Piece mouse = new Piece(Piece.Type.MOUSE, Piece.Side.BLUE,0,0);
        Piece cat = new Piece(Piece.Type.CAT, Piece.Side.BLUE,0,0);
        Piece elephant = new Piece(Piece.Type.ELEPHANT, Piece.Side.BLUE,0,0);
        Piece mouse2 = new Piece(Piece.Type.MOUSE, Piece.Side.BLUE,0,0);
        Assert.assertTrue(mouse.isBiggerThan(elephant));
        Assert.assertTrue(elephant.isBiggerThan(cat));
        Assert.assertTrue(cat.isBiggerThan(mouse));
        Assert.assertFalse(elephant.isBiggerThan(mouse));
        Assert.assertTrue(mouse.isBiggerThan(mouse2));
    }

    @Test
    public void testEquals(){
        Piece a = new Piece(Piece.Type.MOUSE, Piece.Side.BLUE,0,0);
        Piece b = new Piece(Piece.Type.MOUSE, Piece.Side.BLUE,0,0);
        Piece c = new Piece(Piece.Type.MOUSE, Piece.Side.BLUE,0,1);
        Assert.assertEquals(a,b);
        Assert.assertNotEquals(a,c);
    }
    
}
