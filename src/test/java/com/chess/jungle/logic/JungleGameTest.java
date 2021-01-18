package com.chess.jungle.logic;

import java.util.List;

import org.junit.*;

import static org.junit.Assert.*;

/**
 *
 * @author chan_we
 */
public class JungleGameTest {
    
    public JungleGameTest() {
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
     * Test of getPieceList method, of class JungleGame.
     */
    @Test
    public void testGetPieceList() {
        System.out.println("getPieceList");
        JungleGame instance = new JungleGame();
        List<Piece> expResult = null;
        List<Piece> result = instance.getPieceList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBoard method, of class JungleGame.
     */
    @Test
    public void testGetBoard() {
        System.out.println("getBoard");
        JungleGame instance = new JungleGame();
        Board expResult = null;
        Board result = instance.getBoard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWinner method, of class JungleGame.
     */
    @Test
    public void testGetWinner() {
        System.out.println("getWinner");
        JungleGame instance = new JungleGame();
        Piece.Side expResult = null;
        Piece.Side result = instance.getWinner();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetPieces method, of class JungleGame.
     */
    @Test
    public void testResetPieces() {
        System.out.println("resetPieces");
        JungleGame instance = new JungleGame();
        instance.resetPieces();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPossibleMove method, of class JungleGame.
     */
    @Test
    public void testGetPossibleMove() {
        System.out.println("getPossibleMove");
        Piece piece = null;
        JungleGame instance = new JungleGame();
        Coordinate[] expResult = null;
        Coordinate[] result = instance.getPossibleMove(piece);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of movePiece method, of class JungleGame.
     */
    @Test
    public void testMovePiece() {
        System.out.println("movePiece");
        Piece piece = null;
        Coordinate coordinate = null;
        JungleGame instance = new JungleGame();
        instance.movePiece(piece, coordinate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findPieceByPos method, of class JungleGame.
     */
    @Test
    public void testFindPieceByPos() {
        JungleGame game = new JungleGame();
        Piece piece = game.findPieceByPos(new Coordinate(0,0));
        Assert.assertEquals(new Piece(Piece.Type.LION, Piece.Side.RED,0,0),piece);
        piece = game.findPieceByPos(new Coordinate(0,1));
        Assert.assertEquals(null,piece);
    }

    /**
     * Test of findPiecesByType method, of class JungleGame.
     */
    @Test
    public void testFindPiecesByType() {
        System.out.println("findPiecesByType");
        Piece.Type type = null;
        JungleGame instance = new JungleGame();
        Piece[] expResult = null;
        Piece[] result = instance.findPiecesByType(type);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasBlockInRiver method, of class JungleGame.
     */
    @Test
    public void testHasBlockInRiver() {
        JungleGame game = new JungleGame();
        Piece mouse = game.findPieceByPos(new Coordinate(0,2));
        mouse.setCoordinate(new Coordinate(1,3));
        Assert.assertTrue(game.hasBlockInRiver(new Coordinate(1,2),Direction.DOWN));
        Assert.assertTrue(game.hasBlockInRiver(new Coordinate(1,6),Direction.UP));
        Assert.assertTrue(game.hasBlockInRiver(new Coordinate(0,3),Direction.RIGHT));
        Assert.assertTrue(game.hasBlockInRiver(new Coordinate(3,3),Direction.LEFT));
        Assert.assertFalse(game.hasBlockInRiver(new Coordinate(2,2),Direction.DOWN));
    }

    /**
     * Test of isDenAccessible method, of class JungleGame.
     */
    @Test
    public void testIsDenAccessible() {
        System.out.println("isDenAccessible");
        Coordinate c = null;
        Piece.Side side = null;
        JungleGame instance = new JungleGame();
        boolean expResult = false;
        boolean result = instance.isDenAccessible(c, side);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPieceTrapped method, of class JungleGame.
     */
    @Test
    public void testIsPieceTrapped() {
        JungleGame game = new JungleGame();
        Piece piece = new Piece(Piece.Type.LION, Piece.Side.BLUE,2,0);
        Assert.assertTrue(game.isPieceTrapped(piece));
        piece = new Piece(Piece.Type.LION, Piece.Side.BLUE,2,8);
        Assert.assertFalse(game.isPieceTrapped(piece));
    }

    /**
     * Test of animalAbleToFight method, of class JungleGame.
     */
    @Test
    public void testAnimalAbleToFight() {
        System.out.println("animalAbleToFight");
        Piece attacker = null;
        Piece defender = null;
        JungleGame instance = new JungleGame();
        boolean expResult = false;
        boolean result = instance.animalAbleToFight(attacker, defender);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
