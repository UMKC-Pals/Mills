package sprint2.test;

import org.junit.jupiter.api.*;
import sprint2.production.*;
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
    void setEdgeExists() {
    }

    @Test
    void setUpInnerSquare() {
    }

    @Test
    void setUpMiddleSquare() {
    }

    @Test
    void setUpOuterSquare() {
    }

    @Test
    void setupNineBoard() {
    }

    @Test
    void setupSixBoard() {
    }

    @Test
    void setupTwelveBoard() {
    }

    @Test
    void setupThreeBoard() {
    }

    @AfterAll
    static void tearDown() {
        game=null;
        board=null;
        gameSetupGUI=null;
        gamePlayGUI=null;
    }
}
