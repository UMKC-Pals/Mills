package sprint1.production;

public class TMMGame extends Game{
    public TMMGame(boolean playAgainstComputer) {

        TMMGame.player1Count=3;
        TMMGame.player2Count=3;

        dirAndFileSetup();
        Board tmmBoard=new Board(3);
    }
}
