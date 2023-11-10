package sprint2.test;

import sprint2.production.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameTests {

    private static Game game;
    private static GamePlayGUI gamePlayGUI;
    @BeforeAll
    protected static void setUp() {//throws Exception{

        GameSetupGUI gameSetupGUI = new GameSetupGUI();
        game = new NMMGame(false, true);
        gamePlayGUI = new GamePlayGUI(9,false,true);
    }
    //======================UNIT TESTS=====================================
    @Test
    public void testPlayer1Count(){
        Game.setPlayer1Count(5);
        boolean expected = true;
        var actual = Game.reducePlayer1count();
        assertEquals(expected,actual);
    }

    @Test
    public void testPlayer1CountNeg(){
        Game.setPlayer1Count(0);
        boolean expected = false;
        var actual = Game.reducePlayer1count();
        assertEquals(expected, actual);
    }

    @Test
    public void testPlayer2Count(){
        Game.setPlayer2Count(5);
        boolean expected = true;
        var actual = Game.reducePlayer2count();
        assertEquals(expected,actual);
    }

    @Test
    public void testPlayer2CountNeg(){
        Game.setPlayer2Count(0);
        boolean expected = false;
        var actual = Game.reducePlayer2count();
        assertEquals(expected, actual);
    }

    @Test
    public void testDirAndFileSetup(){
        var actual = game.dirAndFileSetup();
        var expected = true;
        assertEquals(expected,actual);
    }
    //=============================END UNIT TESTS===================================

    //=======================ACCEPTANCE TESTS===========================================
    //-------------------------PLACE PIECE TESTS------------------------------------
    @Test
    //placing a piece on a valid (empty) spot, current turn - Player1
    public void testPlacePiecePlayer1Valid(){//acceptance test
        Game.setPlayer1Turn(true);
        Board.roundBtnArray[19].currentBtnState = buttonStates.EMPTY;
        var actual = game.placePiece(19, true);
        assertTrue(actual);
    }

    @Test
    //placing a piece on a valid (empty) spot, current turn - Player2
    public void testPlacePiecePlayer2Valid(){//acceptance test
        Game.setPlayer1Turn(false);
        Board.roundBtnArray[19].currentBtnState = buttonStates.EMPTY;
        var actual = game.placePiece(19, false);
        assertFalse(actual);
    }

    @Test
    //placing a piece on an invalid (nonempty) spot, current turn - Player1
    public void testPlacePieceNonEmptySpot(){//acceptance test
        Game.setPlayer1Turn(true);
        Board.roundBtnArray[19].currentBtnState = buttonStates.PLAYER1;
        var actual = game.placePiece(19, true);
        assertFalse(actual);
    }
    //-----------------------------END PLACE PIECE TESTS--------------------------------
    //-------------------------MOVE PICK TESTS-------------------------------------
    @Test
    //acceptance test. Testing movePick(int). current turn - Player1, player1 tries to pick his piece.
    //expected return true
    public void testMovePickValidPlayer1Turn(){
        Game.setPlayer1Turn(true);
        Board.roundBtnArray[19].currentBtnState = buttonStates.PLAYER1;
        game.player1Pieces.add(19);
        game.player1GameState = gameStates.MOVEPICK;
        game.movePick(19);
        assertTrue(true);
    }

    @Test
    //acceptance test. Testing movePick(int). current turn - Player2, player2 tries to pick his piece.
    //expected return true
    public void testMovePickValidPlayer2Turn(){
        Game.setPlayer1Turn(false);
        Board.roundBtnArray[19].currentBtnState = buttonStates.PLAYER2;
        game.player2Pieces.add(19);
        game.player2GameState = gameStates.MOVEPICK;
        game.movePick(19);
        assertTrue(true);
    }

    @Test
    //acceptance test. Testing movePick(int). current turn - Player1, player1 tries to pick player2's piece.
    // expected return false
    public void testMovePickInvalidPieceForPlayer1(){
        Game.setPlayer1Turn(true);
        Board.roundBtnArray[21].currentBtnState = buttonStates.PLAYER2;
        game.player2Pieces.add(21);
        game.player1GameState = gameStates.MOVEPICK;
        var actual = game.movePick(21);
        assertFalse(actual);
    }

    @Test
    //acceptance test. Testing movePick(int). current turn - Player1, player1 tries to pick player2's piece.
    // expected return false
    public void testMovePickInvalidPieceForPlayer2(){
        Game.setPlayer1Turn(false);
        Board.roundBtnArray[22].currentBtnState = buttonStates.PLAYER1;
        game.player1Pieces.add(22);
        game.player2GameState = gameStates.MOVEPICK;
        var actual = game.movePick(22);
        assertFalse(actual);
    }
    //------------------------------END MOVE PICK TESTS---------------------------------------
    //------------------------------MOVE DROP TESTS-------------------------------------------
    @Test
    //acceptance test. Testing moveDrop(int)
    //test updating movePick variable for Player1
    public void testMoveDropUpdateVarPlayer1(){
        Game.setPlayer1Turn(true);
        Board.roundBtnArray[2].currentBtnState = buttonStates.PLAYER1;
        game.player1Pieces.add(2);
        game.player1GameState = gameStates.MOVEPICK;
        assertTrue(game.moveDrop(2));
    }

    @Test
    //acceptance test. Testing moveDrop(int)
    //test updating movePick variable for Player2
    public void testMoveDropUpdateVarPlayer2(){
        Game.setPlayer1Turn(false);
        Board.roundBtnArray[3].currentBtnState = buttonStates.PLAYER2;
        game.player2Pieces.add(3);
        game.player2GameState = gameStates.MOVEPICK;
        assertTrue(game.moveDrop(3));
    }

    @Test
    //acceptance test. Testing moveDrop(int)
    //player1 drops the picked piece to the adjacent empty spot from 18 to 19
    public void testMoveDropValidPlayer1(){
        Game.setPlayer1Turn(true);
        int from = 18;
        Board.roundBtnArray[from].currentBtnState = buttonStates.PLAYER1;
        game.player1GameState = gameStates.MOVEPICK;
        game.player1Pieces.add(from);
        int to = 19;
        Board.roundBtnArray[to].currentBtnState = buttonStates.EMPTY;
        boolean isAdjacent = Board.getEdgeExists(from, to);
        game.setIsDropSelectedButtonEmpty(true);
        game.setAdjacentPosition(isAdjacent);
        game.setMovePickIdx(from);
        assertTrue(game.moveDrop(to));
    }

    //acceptance test. Testing moveDrop(int)
    //player2 drops the picked piece to the adjacent empty spot from 10 to 11
    @Test
    public void testMoveDropValidPlayer2(){
        Game.setPlayer1Turn(false);
        int from = 10;
        Board.roundBtnArray[from].currentBtnState = buttonStates.PLAYER2;
        game.player2GameState = gameStates.MOVEPICK;
        game.player2Pieces.add(from);
        int to = 11;
        Board.roundBtnArray[to].currentBtnState = buttonStates.EMPTY;
        boolean isAdjacent = Board.getEdgeExists(from, to);
        game.setIsDropSelectedButtonEmpty(true);
        game.setAdjacentPosition(isAdjacent);
        game.setMovePickIdx(from);
        assertTrue(game.moveDrop(to));
    }

    @Test
    //acceptance test. Testing movePick(int)
    //current turn: player1, selected position for drop piece is not adjacent, but empty
    public void testMoveDropNotAdjacent(){
        Game.setPlayer1Turn(true);
        int from = 6;
        int to = 8;
        Board.roundBtnArray[from].currentBtnState = buttonStates.PLAYER1;
        game.player1GameState = gameStates.MOVEPICK;
        Board.roundBtnArray[to].currentBtnState = buttonStates.EMPTY;
        game.setAdjacentPosition(false);
        game.setIsDropSelectedButtonEmpty(true);
        assertFalse(game.moveDrop(to));
    }

    @Test
    //acceptance test. Testing movePick(int)
    //current turn: player1, selected position for drop piece is adjacent, but not empty
    public void testMoveDropNotEmpty(){
        Game.setPlayer1Turn(true);
        int from = 0;
        int to = 9;
        Board.roundBtnArray[from].currentBtnState = buttonStates.PLAYER1;
        game.player1GameState = gameStates.MOVEPICK;
        Board.roundBtnArray[to].currentBtnState = buttonStates.PLAYER2;
        game.setAdjacentPosition(true);
        game.setIsDropSelectedButtonEmpty(false);
        assertFalse(game.moveDrop(to));
    }

    //------------------------------END MOVE DROP TESTS---------------------------------------

    //-----------------------------FLY PICK TESTS---------------------------------------------
    //acceptance tests for the game phase "Flying"
    @Test
    //testing flyPick(int)
    //current turn: Player1, Player1 picks up his piece
    public void testFlyPickValidPlayer1(){
        Game.setPlayer1Turn(true);
        Board.roundBtnArray[14].currentBtnState = buttonStates.PLAYER1;
        game.player1GameState = gameStates.FLYPICK;
        game.player1Pieces.add(14);
        assertTrue(game.flyPick(14));
    }
    @Test
    //testing flyPick(int)
    //current turn: Player2, Player2 picks up his piece
    public void testFlyPickValidPlayer2(){
        Game.setPlayer1Turn(false);
        Board.roundBtnArray[13].currentBtnState = buttonStates.PLAYER2;
        game.player2GameState = gameStates.FLYPICK;
        game.player2Pieces.add(13);
        assertTrue(game.flyPick(13));
    }
    @Test
    //testing flyPick(int)
    //current turn: Player1, Player1 attempts to pick up Player2's piece [13]
    public void testFlyPiecePlayer1InvalidPick(){
        Game.setPlayer1Turn(true);
        game.player1GameState = gameStates.FLYPICK;
        assertFalse(game.flyPick(13));
    }
    @Test
    //testing flyPick(int)
    //current turn: Player2, Player2 attempts to pick up Player1's piece [18]
    public void testFlyPiecePlayer2InvalidPick(){
        Game.setPlayer1Turn(false);
        game.player2GameState = gameStates.FLYPICK;
        assertFalse(game.flyPick(18));
    }
    //-----------------------------END FLY PICK TESTS-----------------------------------------
    //-----------------------------FLY DROP TESTS---------------------------------------------
    @Test
    //acceptance test. Testing flyDrop(int)
    //test updating flyPick variable for Player1
    public void testFlyDropUpdateVarPlayer1(){
        Game.setPlayer1Turn(true);
        Board.roundBtnArray[1].currentBtnState = buttonStates.PLAYER1;
        game.player1Pieces.add(1);
        game.player1GameState = gameStates.FLYPICK;
        assertTrue(game.flyDrop(1));
    }

    @Test
    //acceptance test. Testing flyDrop(int)
    //test updating flyPick variable for Player2
    public void testFlyDropUpdateVarPlayer2(){
        Game.setPlayer1Turn(false);
        Board.roundBtnArray[21].currentBtnState = buttonStates.PLAYER2;
        game.player2Pieces.add(21);
        game.player2GameState = gameStates.FLYPICK;
        assertTrue(game.flyDrop(21));
    }

    @Test
    //acceptance test. Testing flyDrop(int)
    //player1 drops the picked piece to the empty spot from 1 to 9
    public void testFlyDropValidPlayer1(){
        Game.setPlayer1Turn(true);
        int from = 1;
        Board.roundBtnArray[from].currentBtnState = buttonStates.PLAYER1;
        game.player1GameState = gameStates.FLYPICK;
        game.player1Pieces.add(from);
        int to = 4;
        Board.roundBtnArray[to].currentBtnState = buttonStates.EMPTY;
        game.setIsDropSelectedButtonEmpty(true);
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            assertTrue(game.flyDrop(to));
        });
    }

    //acceptance test. Testing flyDrop(int)
    //player2 drops the picked piece to the empty spot from 10 to 19
    @Test
    public void testFlyDropValidPlayer2(){
        Game.setPlayer1Turn(false);
        int from = 10;
        Board.roundBtnArray[from].currentBtnState = buttonStates.PLAYER2;
        game.player2GameState = gameStates.FLYPICK;
        game.player2Pieces.add(from);
        int to = 19;
        Board.roundBtnArray[to].currentBtnState = buttonStates.EMPTY;
        game.setIsDropSelectedButtonEmpty(true);
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            assertTrue(game.flyDrop(to));
        });
    }

    @Test
    //acceptance test. Testing flyPick(int)
    //current turn: player1, selected position for drop piece is not empty
    public void testFlyDropNotEmpty(){
        Game.setPlayer1Turn(true);
        int from = 20;
        int to = 3;
        Board.roundBtnArray[from].currentBtnState = buttonStates.PLAYER1;
        game.player1GameState = gameStates.MOVEPICK;
        Board.roundBtnArray[to].currentBtnState = buttonStates.PLAYER2;
        game.setIsDropSelectedButtonEmpty(false);
        assertFalse(game.moveDrop(to));
    }
    //-----------------------------END FLY DROP TESTS-----------------------------------------
}
