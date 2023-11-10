package sprint3.production;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class TMMGame extends Game{
    public TMMGame(boolean playAgainstComputer) {
        TMMGame.setPlayer1Count(3);
        TMMGame.setPlayer2Count(3);
        dirAndFileSetup();
        Board tmmBoard=new Board(3);
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
