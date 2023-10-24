package sprint2.production;

public class SMMGame extends Game{
    public SMMGame(boolean playAgainstComputer) {
        SMMGame.setPlayer1Count(6);
        SMMGame.setPlayer2Count(6);
        dirAndFileSetup();
        Board smmBoard=new Board(6);
    }
}
