package sprint2.test;

import org.junit.jupiter.api.*;
import sprint2.production.*;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTests { //extends TestCase {
    private static Game game;
    private static Board board;

    @BeforeAll
    protected static void setUp(){ // throws Exception{
//        super.setUp();
        GameSetupGUI gameSetupGUI = new GameSetupGUI();
        GamePlayGUI gamePlayGUI = new GamePlayGUI(9, false, true);

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



    @AfterAll
    static void tearDown() {
        game=null;
        board=null;
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
}
