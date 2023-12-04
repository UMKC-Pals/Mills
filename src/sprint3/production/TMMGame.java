package sprint3.production;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class TMMGame extends Game{
    Board tmmBoard;
    public TMMGame(boolean playAgainstComputerPassed, boolean player1IsWhite) {
        this.playAgainstComputer=playAgainstComputerPassed;



        setPlayer1Turn(true);
        //reset variable after every new constructor call; works for new game functionality

        if(player1IsWhite){
            player1Color=myWhite;
            player2Color=myBlack;

        }
        else{
            player1Color=myBlack;
            player2Color=myWhite;
        }

        Game.setPlayer1Count(3);
        Game.setPlayer2Count(3);

        this.dirAndFileSetup();

        tmmBoard=new Board(3);

        for(int i=0;i<=23;i++){
            tmmBoard.roundBtnArray[i].currentBtnState =buttonStates.EMPTY;
            tmmBoard.roundBtnArray[i].setVisible(true);
        }

        for(int i=6;i<=8;i++){
            tmmBoard.roundBtnArray[i].currentBtnState =buttonStates.EMPTY;
            tmmBoard.roundBtnArray[i].setVisible(true);
        }// 6 - 8
        for(int i=11;i<=12;i++){
            tmmBoard.roundBtnArray[i].currentBtnState =buttonStates.EMPTY;
            tmmBoard.roundBtnArray[i].setVisible(true);
        }// 11, 12
        for(int i=15;i<=17;i++){
            tmmBoard.roundBtnArray[i].currentBtnState =buttonStates.EMPTY;
            tmmBoard.roundBtnArray[i].setVisible(true);
        }// 15 - 17
        for(int i=24;i<=24;i++){
            tmmBoard.roundBtnArray[i].currentBtnState =buttonStates.EMPTY;
            tmmBoard.roundBtnArray[i].setVisible(true);
        }// 24





    }

    @Override
    public boolean checkMillFormation(ArrayList<Integer> playersList, HashSet<TreeSet<Integer>> formedMills) {
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
