package sprint1.production;

public class TMMGame extends Game{
    public TMMGame(boolean playAgainstComputer) {
        dirAndFileSetup();
        Board tmmBoard=new Board(3);
    }
}
