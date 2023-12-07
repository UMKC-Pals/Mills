package sprint3.test;
import org.junit.jupiter.api.*;
import sprint3.production.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
public class GamePlayGUITests {
    private static GameSetupGUI gameSetupGUI;
    private static GamePlayGUI gamePlayGUI;
    private static Game game;
    private static Board board;
    @BeforeAll
    protected static void setUp() {//throws Exception{
        gameSetupGUI = new GameSetupGUI();
        game = new NMMGame(false, true);
        board = new Board(9);
        gamePlayGUI = new GamePlayGUI(9,false,true);
    }

    @AfterAll
    protected static void tearDown(){gameSetupGUI = null;}

    @Test
    public void testAutoReplay(){
        gamePlayGUI.getAutoReplay().setSelected(true);
//        assertNotEquals(0, gamePlayGUI.getArray().size());
    }

    //acceptance test 1.1
    @Test
    public void testPlayerChoseWhiteColor(){
        assertEquals(new Color(221,186,126), game.getPlayer1Color());
    }

    //acceptance test 2.1
    @Test
    public void testGivenGameFrame(){
        assertNotNull(board);
        assertNotNull(gamePlayGUI);

    }
}
