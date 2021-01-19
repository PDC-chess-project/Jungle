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

    }

    /**
     * Test of getBoard method, of class JungleGame.
     */
    @Test
    public void testGetBoard() {

    }

    /**
     * Test of getWinner method, of class JungleGame.
     */
    @Test
    public void testGetWinner() {

    }

    /**
     * Test of resetPieces method, of class JungleGame.
     */
    @Test
    public void testResetPieces() {

    }

    /**
     * Test of getPossibleMove method, of class JungleGame.
     */
    @Test
    public void testGetPossibleMove() {

    }

    /**
     * Test of movePiece method, of class JungleGame.
     */
    @Test
    public void testMovePiece() {
        JungleGame game = new JungleGame();
        Piece piece = game.findPieceByPos(new Coordinate(0,0));
        //valid movement
        game.movePiece(piece,new Coordinate(0,1));
        Assert.assertEquals(new Coordinate(0,1),piece.getCoordinate());
        piece = game.findPieceByPos(new Coordinate(2,2));
        //invalid movement
        game.movePiece(piece,new Coordinate(2,3));
        Assert.assertNotEquals(new Coordinate(2,3),piece.getCoordinate());
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
        JungleGame game = new JungleGame();
        Assert.assertTrue(game.isDenAccessible(new Coordinate(3,0), Piece.Side.BLUE));
        Assert.assertFalse(game.isDenAccessible(new Coordinate(3,0), Piece.Side.RED));
        Assert.assertTrue(game.isDenAccessible(new Coordinate(3,8), Piece.Side.RED));
        Assert.assertFalse(game.isDenAccessible(new Coordinate(3,7), Piece.Side.RED));
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
        JungleGame game = new JungleGame();
        //in the same side
        Piece p1 = new Piece(Piece.Type.MOUSE, Piece.Side.BLUE,0,1);
        Piece p2 = new Piece(Piece.Type.MOUSE, Piece.Side.BLUE,0,0);
        Assert.assertFalse(game.animalAbleToFight(p1,p2));
        //one is in river and another is on land
        p1 = new Piece(Piece.Type.MOUSE, Piece.Side.BLUE,1,3);
        p2 = new Piece(Piece.Type.ELEPHANT, Piece.Side.RED,0,3);
        Assert.assertFalse(game.animalAbleToFight(p1,p2));
        //attack is not bigger than defender
        p1 = new Piece(Piece.Type.ELEPHANT, Piece.Side.BLUE,0,3);
        p2 = new Piece(Piece.Type.MOUSE, Piece.Side.RED,0,3);
        Assert.assertFalse(game.animalAbleToFight(p1,p2));
        p1 = new Piece(Piece.Type.MOUSE, Piece.Side.BLUE,0,3);
        p2 = new Piece(Piece.Type.ELEPHANT, Piece.Side.RED,0,3);
        Assert.assertTrue(game.animalAbleToFight(p1,p2));

    }
    
}
