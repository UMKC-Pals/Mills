package sprint3.test;

import org.junit.jupiter.api.*;
import sprint3.production.*;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {
    private static Game game;
    private static Board board;
    static GameSetupGUI gameSetupGUI;
    static GamePlayGUI gamePlayGUI;

    @BeforeAll
    protected static void setUp(){
        gameSetupGUI = new GameSetupGUI();
        gamePlayGUI = new GamePlayGUI(9, false, true);

    }

    @Test
    public void testNMMSetup(){
        game = new NMMGame(false, true);
        assertNotNull(game);
    }
    @Test
    public void testNMMBoardSetup(){
        board = new Board(9);
        assertNotNull(board);
    }




    @Test
    void testSetEdgeExists() {
        board.setEdgeExists(1,24);
        var actual = Board.getEdgeExists(1,24);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    void testSetEdgeExistsInvalidA(){
        int a = 25;
        board.setEdgeExists(a, 3);

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            Board.getEdgeExists(a,3);
        });
    }

    @Test
    void testSetEdgeExistsInvalidB(){
        int b = 25;
        board.setEdgeExists(3, b);

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            Board.getEdgeExists(3,b);
        });
    }

    @Test
    void testSetUpInnerSquare() {
        Board board1 = new Board(3);
        board1.setUpInnerSquare();
        int expectedNumberOfInnerSquareMills = 4;
        var actualNumberOfInnerSquareMills = board1.getInnerSquareMills().size();
        assertEquals(expectedNumberOfInnerSquareMills, actualNumberOfInnerSquareMills);
    }

    @Test
    void testSetUpMiddleSquare() {
        Board board2 = new Board(6);
        board2.setUpMiddleSquare();
        int expectedNumberOfMiddleSquareMills = 4;
        var actualNumberOfMiddleSquareMills = board2.getMiddleSquareMills().size();
        assertEquals(expectedNumberOfMiddleSquareMills, actualNumberOfMiddleSquareMills);
    }

    @Test
    void testSetUpOuterSquare() {
        board.setUpOuterSquare();
        int expectedNumberOfOuterSquareMills = 4;
        var actualNumberOfOuterSquareMills = board.getOuterSquareMills().size();
        assertEquals(expectedNumberOfOuterSquareMills, actualNumberOfOuterSquareMills);
    }

    @Test
    void setupNineBoard() {
        board.setupNineBoard();
        int expectedNumOfNMMMills = 16;
        var actualNumOfNMMMills = board.getNmmMills().size();
        assertEquals(expectedNumOfNMMMills, actualNumOfNMMMills);
    }

    @Test
    void setupSixBoard() {
        Board board4 = new Board(6);
        board4.setupSixBoard();
        int expectedNumOfSMMMills = 8;
        var actualNumOfSMMMills = board4.getSmmMills().size();
        assertEquals(expectedNumOfSMMMills, actualNumOfSMMMills);
    }

    @Test
    void setupTwelveBoard() {
        Board board5 = new Board(12);
        board5.setupTwelveBoard();
        int expectedNumOfTwMMMills = 20;
        var actualNumOfTwMMMills = board5.getTwmmMills().size();
        assertEquals(expectedNumOfTwMMMills, actualNumOfTwMMMills);
    }

    @Test
    void testSetupThreeBoard() {
        Board board3 = new Board(3);
        board3.setupThreeBoard();
        int expectedNumOfTMMMills = 8;
        var actualNumOfTMMMills = board3.getTmmMills().size();
        assertEquals(expectedNumOfTMMMills, actualNumOfTMMMills);
    }

    @AfterAll
    static void tearDown() {
        game=null;
        board=null;
        gameSetupGUI=null;
        gamePlayGUI=null;
    }
}
