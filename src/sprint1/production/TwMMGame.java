package sprint1.production;

public class TwMMGame extends Game{
    public TwMMGame(boolean playAgainstComputer) {

        TwMMGame.player1Count=12;
        TwMMGame.player2Count=12;

        dirAndFileSetup();
        Board twmmBoard=new Board(12);
    }
}
