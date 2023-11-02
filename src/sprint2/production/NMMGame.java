package sprint2.production;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NMMGame extends Game{
    Board nmmBoard;
    public NMMGame(boolean playAgainstComputer, boolean player1IsWhite) {

        if(player1IsWhite){
             player1Color=myWhite;
             player2Color=myBlack;
        }
        else{
             player1Color=myBlack;
             player2Color=myWhite;
        }

        Game.setPlayer1Count(9);
        Game.setPlayer2Count(9);

        this.dirAndFileSetup();

        nmmBoard=new Board(9);

        for(int i=0;i<=23;i++){
            nmmBoard.roundBtnArray[i].currentBtnState =buttonStates.EMPTY;
            nmmBoard.roundBtnArray[i].setVisible(true);
        }

        for(int i=0;i<=23;i++) {
            int j=i;
            nmmBoard.roundBtnArray[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean pl1Place=isPlayer1Turn() && player1GameState==gameStates.PLACE;
                    boolean pl2Place=!isPlayer1Turn() && player2GameState==gameStates.PLACE;

                    boolean pl1MovePick=isPlayer1Turn() && player1GameState==gameStates.MOVEPICK;
                    boolean pl2MovePick=!isPlayer1Turn() && player2GameState==gameStates.MOVEPICK;

                    boolean pl1MoveDrop=isPlayer1Turn() && player1GameState==gameStates.MOVEDROP;
                    boolean pl2MoveDrop=!isPlayer1Turn() && player2GameState==gameStates.MOVEDROP;

                    boolean pl1FlyPick=isPlayer1Turn() && player1GameState==gameStates.FLYPICK;
                    boolean pl2FlyPick=!isPlayer1Turn() && player2GameState==gameStates.FLYPICK;

                    boolean pl1FlyDrop=isPlayer1Turn() && player1GameState==gameStates.FLYDROP;
                    boolean pl2FlyDrop=!isPlayer1Turn() && player2GameState==gameStates.FLYDROP;

                    boolean pl1Remove=isPlayer1Turn() && player1GameState==gameStates.REMOVE;
                    boolean pl2Remove=!isPlayer1Turn() && player2GameState==gameStates.REMOVE;



                    if(pl1Place || pl2Place){
//                      call place function player1
                        placePiece(j,player1IsWhite);
                    }
                    else if(pl1MovePick || pl2MovePick){
//                      call movePick function
                        movePick(j);
                    }
                    else if(pl1MoveDrop || pl2MoveDrop){
//                      call moveDrop function
                        moveDrop(j);
                    }
                    else if(pl1FlyPick || pl2FlyPick){
//                      call flyPick function
                        flyPick();
                    }
                    else if(pl1FlyDrop || pl2FlyDrop){
//                      call flyDrop function
                        flyDrop();
                    }
                    else if(pl1Remove || pl2Remove){
//                      call removePiece function from Game.java
//                        removePiece();
                    }




//                    if (nmmBoard.roundBtnArray[j].currentBtnState == buttonStates.EMPTY) {
//
//                        if (Game.isPlayer1Turn() && Game.reducePlayer1count()) {
//
//                            nmmBoard.roundBtnArray[j].currentBtnState = buttonStates.PLAYER1;
//                            nmmBoard.roundBtnArray[j].setBackground(player1Color);
//
//                            Game.setPlayer1Turn(false);
//                            nmmBoard.roundBtnArray[j].setBounds(285 + nmmBoard.x[j] - (nmmBoard.dim2 / 2), nmmBoard.y[j] - (nmmBoard.dim1 / 2), nmmBoard.dim2, nmmBoard.dim2);
//                            nmmBoard.roundBtnArray[j].setBorder(BorderFactory.createEmptyBorder());
//                            GamePlayGUI.updatePlayerCountLabels(player1IsWhite);
//                            GamePlayGUI.updatePlayerTurnLabel();
//
//                        } else if (!NMMGame.isPlayer1Turn() && Game.reducePlayer2count()) {
//
//                            nmmBoard.roundBtnArray[j].currentBtnState = buttonStates.PLAYER2;
//                            nmmBoard.roundBtnArray[j].setBackground(player2Color);
//                            Game.setPlayer1Turn(true);
//                            nmmBoard.roundBtnArray[j].setBounds(285 + nmmBoard.x[j] - (nmmBoard.dim2 / 2), nmmBoard.y[j] - (nmmBoard.dim1 / 2), nmmBoard.dim2, nmmBoard.dim2);
//                            nmmBoard.roundBtnArray[j].setBorder(BorderFactory.createEmptyBorder());
//                            GamePlayGUI.updatePlayerCountLabels(player1IsWhite);
//                            GamePlayGUI.updatePlayerTurnLabel();
//
//                        }
//
//                    }








                }// end of actionPerformed

            });
        }// end of for loop

    }// end of constructor
}// end of class
