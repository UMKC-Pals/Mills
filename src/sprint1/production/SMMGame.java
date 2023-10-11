package sprint1.production;

public class SMMGame extends Game{
    public SMMGame(boolean playAgainstComputer) {
        dirAndFileSetup();
        Board smmBoard=new Board(6);
    }
}
