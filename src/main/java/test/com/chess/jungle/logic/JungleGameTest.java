package test.com.chess.jungle.logic; 

import com.chess.jungle.logic.Coordinate;
import com.chess.jungle.logic.JungleGame;
import com.chess.jungle.logic.Piece;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* JungleGame Tester. 
* 
* @author <Authors name> 
* @since <pre>1�� 11, 2021</pre> 
* @version 1.0 
*/ 
public class JungleGameTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getPieceList() 
* 
*/ 
@Test
public void testGetPieceList() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getBoard() 
* 
*/ 
@Test
public void testGetBoard() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getPossibleMove(Piece piece) 
* 
*/ 
@Test
public void testGetPossibleMove() throws Exception { 
//TODO: Test goes here...
//    JungleGame game = new JungleGame();
//    Piece piece = game.getPieceByPos(2,2);
//    Coordinate[] coordinates = game.getPossibleMove(piece);
//    for(Coordinate coordinate : coordinates){
//        System.out.println(coordinate);
//    }

    Assert.assertEquals(1,1);
    Assert.assertTrue(true);
} 

/** 
* 
* Method: movePiece(Piece piece, int x, int y) 
* 
*/ 
@Test
public void testMovePiece() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getPieceByPos(int x, int y) 
* 
*/ 
@Test
public void testGetPieceByPos() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getPiecesByType(Piece.Type type) 
* 
*/ 
@Test
public void testGetPiecesByType() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: hasBlockInRiver(int x, int y, Direction direction) 
* 
*/ 
@Test
public void testHasBlockInRiver() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: isMovable(Piece piece, Direction direction) 
* 
*/ 
@Test
public void testIsMovable() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = JungleGame.getClass().getMethod("isMovable", Piece.class, Direction.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
