package sprint3.production;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class SMMGame extends Game{
    public SMMGame(boolean playAgainstComputer) {
        SMMGame.setPlayer1Count(6);
        SMMGame.setPlayer2Count(6);
        dirAndFileSetup();
        Board smmBoard=new Board(6);
    }

    @Override
    public boolean checkMillFormation(ArrayList<Integer> playersList, HashSet<TreeSet<Integer>> formedMills) {
        return false;
    }

    @Override
    public boolean checkGameOver() {
        return false;
    }
}
