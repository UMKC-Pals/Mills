package sprint2.production;

public class TMMGame extends Game{
    public TMMGame(boolean playAgainstComputer) {
        TMMGame.setPlayer1Count(3);
        TMMGame.setPlayer2Count(3);
        dirAndFileSetup();
        Board tmmBoard=new Board(3);
    }
}
