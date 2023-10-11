package sprint1.production;

public class SMMGame extends Game{
    public SMMGame(boolean playAgainstComputer) {

        SMMGame.player1Count=6;
        SMMGame.player2Count=6;

        dirAndFileSetup();
        Board smmBoard=new Board(6);
    }
}
