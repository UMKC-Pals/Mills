package sprint1.production;

public class SMMGame extends Game{
    public SMMGame(boolean playAgainstComputer) {

        this.player1Count=6;
        this.player2Count=6;

        dirAndFileSetup();
        Board smmBoard=new Board(6);
    }
}
