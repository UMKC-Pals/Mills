package sprint1.production;

public class TwMMGame extends Game{
    public TwMMGame(boolean playAgainstComputer) {
        dirAndFileSetup();
        Board twmmBoard=new Board(12);
    }
}
