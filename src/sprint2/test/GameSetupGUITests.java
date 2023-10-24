package sprint2.test;

import sprint2.production.*;

import org.junit.jupiter.api.*;
import sprint2.production.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameSetupGUITests { //extends TestCase {
    private GamePlayGUI gamePlayGUI;
    @BeforeAll
    protected static void setUp() {//throws Exception{
        GameSetupGUI gameSetupGUI = new GameSetupGUI();
    }

    @Test
    public void testNMMFrame(){
        gamePlayGUI = new GamePlayGUI(9, false, true);
        assertNotNull(gamePlayGUI);
    }

    @Test
    public void testSMMFrame(){
        gamePlayGUI = new GamePlayGUI(6, false, true);
        assertNotNull(gamePlayGUI);
    }

    @Test
    public void testTMMFrame(){
        gamePlayGUI = new GamePlayGUI(3, false, true);
        assertNotNull(gamePlayGUI);
    }

    @Test
    public void testTwMMFrame(){
        gamePlayGUI = new GamePlayGUI(12, false, true);
        assertNotNull(gamePlayGUI);
    }

}
