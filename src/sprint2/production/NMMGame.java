package sprint2.production;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NMMGame extends Game{
    Board nmmBoard;
    Color player1Color, player2Color;
    public NMMGame(boolean playAgainstComputer, boolean player1IsWhite) {

        if(player1IsWhite){
             player1Color=new Color(221,186,126);
             player2Color=new Color(0,0,0);
        }
        else{
             player1Color=new Color(0,0,0);
             player2Color=new Color(221,186,126);
        }

        Game.setPlayer1Count(9);
        Game.setPlayer2Count(9);

        this.dirAndFileSetup();

        nmmBoard=new Board(9);

        for(int i=0;i<=23;i++){
            nmmBoard.roundBtnArray[i].currentState=buttonStates.EMPTY;
            nmmBoard.roundBtnArray[i].setVisible(true);
        }

        for(int i=0;i<=23;i++) {
            int j=i;
            nmmBoard.roundBtnArray[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                    if (nmmBoard.roundBtnArray[j].currentState == buttonStates.EMPTY) {

                        if (Game.player1turn && Game.reducePlayer1count()) {

                            nmmBoard.roundBtnArray[j].currentState = buttonStates.PLAYER1;
                            nmmBoard.roundBtnArray[j].setBackground(player1Color);


                            Game.player1turn = false;
                            nmmBoard.roundBtnArray[j].setBounds(285 + nmmBoard.x[j] - (nmmBoard.dim2 / 2), nmmBoard.y[j] - (nmmBoard.dim1 / 2), nmmBoard.dim2, nmmBoard.dim2);
                            nmmBoard.roundBtnArray[j].setBorder(BorderFactory.createEmptyBorder());
                            GamePlayGUI.updatePlayerCountLabels(player1IsWhite);
                            GamePlayGUI.updatePlayerTurnLabel();

                        } else if (!NMMGame.player1turn && Game.reducePlayer2count()) {

                            nmmBoard.roundBtnArray[j].currentState = buttonStates.PLAYER2;
                            nmmBoard.roundBtnArray[j].setBackground(player2Color);
                            Game.player1turn = true;
                            nmmBoard.roundBtnArray[j].setBounds(285 + nmmBoard.x[j] - (nmmBoard.dim2 / 2), nmmBoard.y[j] - (nmmBoard.dim1 / 2), nmmBoard.dim2, nmmBoard.dim2);
                            nmmBoard.roundBtnArray[j].setBorder(BorderFactory.createEmptyBorder());
                            GamePlayGUI.updatePlayerCountLabels(player1IsWhite);
                            GamePlayGUI.updatePlayerTurnLabel();

                        }

                    }

                }

            });
        }

    }
}
