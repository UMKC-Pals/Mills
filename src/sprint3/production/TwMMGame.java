package sprint3.production;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class TwMMGame extends Game{
    public TwMMGame(boolean playAgainstComputerPassed) {
        this.playAgainstComputer=playAgainstComputerPassed;
        TwMMGame.setPlayer1Count(12);
        TwMMGame.setPlayer2Count(12);
        dirAndFileSetup();
        Board twmmBoard=new Board(12);
    }

    @Override
    public boolean checkMillFormation(ArrayList<Integer> playersPieces, HashSet<TreeSet<Integer>> formedMills) {
        return false;
    }

    @Override
    public boolean checkGameOver() {
        return false;
    }

    @Override
    public void doComputerMoves() {
        return;
    }
}
