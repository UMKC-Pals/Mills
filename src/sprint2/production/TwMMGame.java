package sprint2.production;

public class TwMMGame extends Game{
    public TwMMGame(boolean playAgainstComputer) {
        TwMMGame.setPlayer1Count(12);
        TwMMGame.setPlayer2Count(12);
        dirAndFileSetup();
        Board twmmBoard=new Board(12);
    }
}
