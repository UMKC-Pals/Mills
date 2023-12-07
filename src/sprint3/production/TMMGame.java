package sprint3.production;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TMMGame extends Game{
    Board tmmBoard;
    int [] tmmIndices = {6,7,8,11,24,12,15,16,17};

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

        for(int i=0;i<=24;i++){
            tmmBoard.roundBtnArray[i].currentBtnState =buttonStates.INVALID;
            tmmBoard.roundBtnArray[i].setVisible(false);
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



        for(int itr=0;itr<tmmIndices.length;itr++) {
            int i=tmmIndices[itr];
            int j=i;
            tmmBoard.roundBtnArray[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    boolean pl1Place=isPlayer1Turn() && player1GameState==gameStates.PLACE;
                    boolean pl2Place=!isPlayer1Turn() && player2GameState==gameStates.PLACE;

                    boolean pl1MovePick=isPlayer1Turn() && player1GameState==gameStates.MOVEPICK;
                    boolean pl2MovePick=!isPlayer1Turn() && player2GameState==gameStates.MOVEPICK;

                    boolean pl1MoveDrop=isPlayer1Turn() && player1GameState==gameStates.MOVEDROP;
                    boolean pl2MoveDrop=!isPlayer1Turn() && player2GameState==gameStates.MOVEDROP;


                    if(pl1Place || pl2Place){
//                      call place function player1
                        placePiece(j,player1IsWhite);
                        //if (GamePlayGUI.record)
                        //gameLog.writeGameLogFile(gameLogFile.getAbsolutePath());
                    }
                    else if(pl1MovePick || pl2MovePick){
//                      call movePick function
                        movePick(j);
                    }
                    else if(pl1MoveDrop || pl2MoveDrop){
//                      call moveDrop function
                        moveDrop(j);

                    }
                }// end of actionPerformed

            });
        }// end of for loop



    }//end of constructor


    //a game continuous until the first player to align his three pieces in a line
    //in other words until the first mill


    @Override
    public boolean checkMillFormation(ArrayList<Integer> playersPieces, HashSet<TreeSet<Integer>> formedMills) {
        String gameResult = "";
        for (TreeSet<Integer> mill : tmmBoard.getTmmMills()) {
            int match = 0;
            for (Integer piece : playersPieces) {
                if (mill.contains(piece)) {
                    match += 1;
                }
            }
            if (match == 3) {
                formedMills.add(mill);
                if (isPlayer1Turn()) {
                    setPlayer1Mills(formedMills);
                    gameResult = "Player 1 won!";
                } else {
                    setPlayer2Mills(formedMills);
                    gameResult = "Player 2 won!";
                }

                JOptionPane.showMessageDialog(null, gameResult);
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean checkGameOver() {
        return false;
    }

    @Override
    public void doComputerMoves() {

        if(playAgainstComputer) {
            //place
            if(player2GameState==gameStates.PLACE && !isPlayer1Turn()) {

                //

                    ArrayList<Integer> emptyPieces=new ArrayList<>();


                    for(int itr=0;itr<tmmIndices.length;itr++){
                        int i=tmmIndices[itr];
                        emptyPieces.add(i);
                    }
                    emptyPieces.removeAll(player1Pieces);
                    emptyPieces.removeAll(player2Pieces);


                    for(TreeSet<Integer> mill: tmmBoard.tmmMills) {
                        ArrayList<Integer> thisMill = new ArrayList<>(mill);

                        int c = 0, emptyPc = -1;
                        //priority 1 - complete computer player mill
                        for (Integer pc : thisMill) {
                            if (player2Pieces.contains(pc)) {
                                c++;
                            }
                            if (!player2Pieces.contains(pc) && !player1Pieces.contains(pc)) {
                                emptyPc = pc;
                            }

                        }
                        if (c == 2 && emptyPc != -1) {
                            tmmBoard.roundBtnArray[emptyPc].doClick();
                            return;
                        }
                    }
                    for(TreeSet<Integer> mill: tmmBoard.tmmMills){
                        ArrayList<Integer> thisMill=new ArrayList<>(mill);

                        int c=0,emptyPc=-1;

                        //priority 2 - block opponent player mill
                        for(Integer pc:thisMill){
                            if(player1Pieces.contains(pc)) {
                                c++;
                            }
                            if(!player1Pieces.contains(pc) && !player2Pieces.contains(pc)){
                                emptyPc=pc;
                            }

                        }
                        if(c==2 && emptyPc!=-1){
                            tmmBoard.roundBtnArray[emptyPc].doClick();
                            return;
                        }

                    }
                    //priority 3 - randomize the next place
                    // creating object
                    int rndmElem = emptyPieces.get(rndm.nextInt(emptyPieces.size()));
                    tmmBoard.roundBtnArray[rndmElem].doClick();
                    return;


                //end of place state


                //
            }



            //move pick and drop
            if(player2GameState==gameStates.MOVEPICK && !isPlayer1Turn()) {

            }



        }
    }
}
