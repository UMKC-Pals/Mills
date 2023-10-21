package sprint2.test;

import sprint2.production.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameTests {

    private static Game game;
    @BeforeAll
    protected static void setUp() {//throws Exception{

        GameSetupGUI gameSetupGUI = new GameSetupGUI();
        game = new NMMGame(false, true);
    }

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


}
