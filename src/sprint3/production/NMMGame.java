package sprint3.production;

import com.sun.source.tree.Tree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.*;

public class NMMGame extends Game{
    Board nmmBoard;

    public NMMGame(boolean playAgainstComputerPassed, boolean player1IsWhite) {

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
                        flyPick(j);
                    }
                    else if(pl1FlyDrop || pl2FlyDrop){
//                      call flyDrop function
                        flyDrop(j);
                    }
                    else if(pl1Remove || pl2Remove){
                        System.out.println("calling remove by player: "+(isPlayer1Turn()?"player1":"player2"));
                        System.out.println("remove: "+j);

                        removePiece(isPlayer1Turn() ,j);
                    }
                }// end of actionPerformed

            });
        }// end of for loop

    }// end of constructor
    //===============================================================================
    //the idea of this method is that the latest move defines a new mill.
    //check the first (n-1) elements of the playersPieces
    //if there are two matches with the elements of the known mill (e.g. [15, 16, 17] - [15, 17])
    //then check the latest move of the player arrayPlayer.size() - 1.
    //if it forms a mill (e.g. in this case the latest move should be [16]),
    //then true, otherwise - false
    //go through the DS that contains all possible mills: first two "for" loops
    //the most inner loop "for" is for the elements of playersPieces
    //and copy the mill to the corresponding DS that stores mills for each of the players
    //once the mill is formed return true, otherwise - return false
    @Override
    public boolean checkMillFormation(ArrayList<Integer> playersPieces, HashSet<TreeSet<Integer>> formedMills) {
        //first update formedMills to check if all mills are valid
        HashSet<TreeSet<Integer>> TempFormedMills=new HashSet<>(formedMills);
        for(TreeSet<Integer> tempMill: TempFormedMills){
            if(!playersPieces.containsAll(tempMill)){
                formedMills.remove(tempMill);
            }
        }
        TempFormedMills=null;

        //check for new mills
        Integer lastAddedElement = playersPieces.get(playersPieces.size()-1);
        System.out.println("checkMill called");
        for (TreeSet<Integer> mill : nmmBoard.getNmmMills()) {//each mill in set of all possible known mills
            if (mill.contains(lastAddedElement) && playersPieces.containsAll(mill) && !formedMills.contains(mill)){
                System.out.println("found mill new.");
                formedMills.add(mill);
                if(isPlayer1Turn()){
                    setPlayer1Mills(formedMills);
                    System.out.println("found new mill. player1");
                    System.out.println("new mill is : "+mill);
                    System.out.println("player 1 mills: "+getPlayer1Mills());
                    System.out.println("player 2 mills: "+getPlayer2Mills());


                    return true;
                }
                else{
                    setPlayer2Mills(formedMills);
                    System.out.println("found new mill. player2");
                    System.out.println("new mill is : "+mill);

                    System.out.println("player 1 mills: "+getPlayer1Mills());
                    System.out.println("player 2 mills: "+getPlayer2Mills());

                    return true;
                }
            }
        }
        System.out.println("player 1 mills: "+getPlayer1Mills());
        System.out.println("player 2 mills: "+getPlayer2Mills());
        return false;
    }

    //here we check each element of the player's pieces and their adjacent spots
    //if at least one adjacent spot is empty increase the counter variable by 1.
    //example: player1 is at [1], then check positions [0], [2], and [4]. if at least one of them is empty, count++
    //parameter is player1Pieces, player2Pieces.
    //returns the least number of possible moves
    public int getPossibleMoves(ArrayList<Integer> playerPieces){
        int countPossibleMoves = 0;
        for (Integer piece : playerPieces){
            switch (piece){
                case 0:
                    if(Board.roundBtnArray[1].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[9].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 1:
                    if(Board.roundBtnArray[0].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[2].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[4].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 2:
                    if(Board.roundBtnArray[1].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[14].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 3:
                    if(Board.roundBtnArray[4].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[10].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 4:
                    if(Board.roundBtnArray[1].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[3].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[5].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[7].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 5:
                    if(Board.roundBtnArray[4].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[13].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 6:
                    if(Board.roundBtnArray[7].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[11].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 7:
                    if(Board.roundBtnArray[4].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[6].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[8].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 8:
                    if(Board.roundBtnArray[7].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[12].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 9:
                    if(Board.roundBtnArray[0].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[10].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[21].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 10:
                    if(Board.roundBtnArray[3].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[9].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[11].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[18].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 11:
                    if(Board.roundBtnArray[6].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[10].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[15].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 12:
                    if(Board.roundBtnArray[8].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[13].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[17].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 13:
                    if(Board.roundBtnArray[5].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[12].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[14].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[20].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 14:
                    if(Board.roundBtnArray[2].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[13].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[23].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 15:
                    if(Board.roundBtnArray[11].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[16].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 16:
                    if(Board.roundBtnArray[15].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[17].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[19].currentBtnState == buttonStates.EMPTY) {
                        countPossibleMoves += 1;
                    }
                case 17:
                    if(Board.roundBtnArray[12].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[16].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 18:
                    if(Board.roundBtnArray[10].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[19].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 19:
                    if(Board.roundBtnArray[16].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[18].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[20].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[22].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 20:
                    if(Board.roundBtnArray[13].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[19].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 21:
                    if(Board.roundBtnArray[9].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[22].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
                case 22:
                    if(Board.roundBtnArray[19].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[21].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[23].currentBtnState == buttonStates.EMPTY) {
                        countPossibleMoves += 1;
                    }
                case 23:
                    if(Board.roundBtnArray[14].currentBtnState == buttonStates.EMPTY ||
                            Board.roundBtnArray[22].currentBtnState == buttonStates.EMPTY){
                        countPossibleMoves += 1;
                    }
            }
        }
        return countPossibleMoves;
    }

    //this method calls the previous one.
    //parameter is player1Pieces, player2Pieces
    //check if the number of possible moves is greater than 0
    public boolean isPlayerBlocked(ArrayList<Integer> playerPieces){
        int numPlayerMoves = getPossibleMoves(playerPieces);
        if (numPlayerMoves > 0)
            return false;
        return true;
    }

    //game over:
    //- if every piece of the player is blocked and the game is in its second phase (MOVEPICK, MOVEDROP)
    //- if the game phase is "Flying" and the number of player's pieces is less than 3
    //draw: ???
    @Override
    public boolean checkGameOver() {
        if (player1Pieces.size() > 3 && isPlayerBlocked(player1Pieces) && (player1GameState == gameStates.MOVEPICK ||
                player1GameState == gameStates.MOVEDROP )){
            System.out.println("Player 2 won!");
            JOptionPane.showMessageDialog(null, "player 2 won");


            return true;
        }
        else if (player2Pieces.size() > 3 && isPlayerBlocked(player2Pieces) && (player2GameState == gameStates.MOVEPICK ||
                player2GameState == gameStates.MOVEDROP)){
            System.out.println("Player 1 won!");
            JOptionPane.showMessageDialog(null, "player 1 won");

            return true;
        }
        else if ((player1GameState == gameStates.FLYPICK || player1GameState == gameStates.FLYDROP) &&
                player1Pieces.size() < 3){
            System.out.println("Player 2 won!");
            JOptionPane.showMessageDialog(null, "player 2 won");

            return true;
        }
        else if ((player2GameState == gameStates.FLYPICK || player2GameState == gameStates.FLYDROP) &&
                player2Pieces.size() < 3){
            System.out.println("Player 1 won!");
            JOptionPane.showMessageDialog(null, "player 1 won");

            return true;
        }
        //else if () what is the condition for draw? ChatGPT suggests counting a total number of moves and check if
        //it is > 50, then declare draw and end the game.
        return false;
    }


//computer moves for NMM
    public void doComputerMoves(){


        if(playAgainstComputer){
            //place state - player 2
            if(player2GameState==gameStates.PLACE && !isPlayer1Turn()){
                ArrayList<Integer> emptyPieces=new ArrayList<>();
                for(int i=0;i<24;i++){
                    emptyPieces.add(i);
                }
                emptyPieces.removeAll(player1Pieces);
                emptyPieces.removeAll(player2Pieces);


                for(TreeSet<Integer> mill: nmmBoard.nmmMills) {
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
                        nmmBoard.roundBtnArray[emptyPc].doClick();
                        return;
                    }
                }
                for(TreeSet<Integer> mill: nmmBoard.nmmMills){
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
                        nmmBoard.roundBtnArray[emptyPc].doClick();
                        return;
                    }

                }
                //priority 3 - randomize the next place
                // creating object
                int rndmElem = emptyPieces.get(rndm.nextInt(emptyPieces.size()));
                nmmBoard.roundBtnArray[rndmElem].doClick();
                return;

            }
            //end of place state

            //movepick & movedrop
            if(player2GameState==gameStates.MOVEPICK && !isPlayer1Turn()){
                //1 - make our mill

                //2 - block opponent mill

                //3 - random move by looping on all pieces
            }//end of movepick & movedrop

            //flypick & flydrop
            if(player2GameState==gameStates.FLYPICK && !isPlayer1Turn()){
                //1 - make our mill

                //2 - block opponent mill

                //3 - random fly
            }//end of flypick and flydrop

            //remove
            if(player2GameState==gameStates.REMOVE && !isPlayer1Turn()) {

                ArrayList<Integer> emptyPieces = new ArrayList<>();
                for (int i = 0; i < 24; i++) {
                    emptyPieces.add(i);
                }
                emptyPieces.removeAll(player1Pieces);
                emptyPieces.removeAll(player2Pieces);



                for (TreeSet<Integer> mill : nmmBoard.nmmMills) {
                    ArrayList<Integer> thisMill = new ArrayList<>(mill);

                    int c = 0;
                    //remove case 1- which has 2 pieces in mill of player1 and 3rd piece is empty
                    for (Integer pc : mill) {
                        if (player1Pieces.contains(pc)) {
                            c++;
                        } else if (!player1Pieces.contains(pc) && !player2Pieces.contains(pc)) {
                            thisMill.remove(pc);
                        }
                    }
                    if (c == 2 && thisMill.size()==2) {
                        ArrayList<Integer> player1millItems = new ArrayList<>();
                        for (TreeSet<Integer> ts : getPlayer1Mills()) {
                            player1millItems.addAll(ts);
                        }
                        thisMill.removeAll(player1millItems);
                        if (thisMill.size() > 0) {
                            System.out.println("remove called case1: ");

                            int rmvPc = thisMill.get(rndm.nextInt(thisMill.size()));
                            System.out.println("removing piece: " + rmvPc);
                            nmmBoard.roundBtnArray[rmvPc].doClick();
                            return;
                        }

                    }
                }
                for(TreeSet<Integer> mill : nmmBoard.nmmMills) {
                    ArrayList<Integer> thisMill = new ArrayList<>(mill);

                    int c = 0;
                    //remove where 2 pieces of computer & 3rd piece is player1

                    int flag=-1;
                    for (Integer pc : mill) {
                        if (player2Pieces.contains(pc)) {
                            c++;
                        }
                        else if(player1Pieces.contains(pc)){
                            flag=pc;
                        }

                    }
                    if (c == 2 && flag!=-1) {
                        ArrayList<Integer> player1millItems = new ArrayList<>();
                        for (TreeSet<Integer> ts : getPlayer1Mills()) {
                            player1millItems.addAll(ts);
                        }

                        if(!player1millItems.contains(flag)){
                            System.out.println("remove called case2");
                            nmmBoard.roundBtnArray[flag].doClick();
                            return;
                        }

                    }

                }


                ArrayList<Integer> player1millItems = new ArrayList<>();
                for (TreeSet<Integer> ts : getPlayer1Mills()) {
                    player1millItems.addAll(ts);
                }


                //remove - randomize piece which is not in mill
                ArrayList<Integer> tempPlayer1Items=new ArrayList<>(player1Pieces);
                tempPlayer1Items.removeAll(player1millItems);
                if(tempPlayer1Items.size()>0){
                    int rndmElem = tempPlayer1Items.get(rndm.nextInt(tempPlayer1Items.size()));
                    System.out.println("removing by random from NOT in mill: "+rndmElem);
                    nmmBoard.roundBtnArray[rndmElem].doClick();
                    return;
                }


                //remove - random piece from any mill
                if(tempPlayer1Items.size()==0){
                    int rndmElem = player1millItems.get(rndm.nextInt(player1millItems.size()));
                    System.out.println("removing by random from in mill: "+rndmElem);
                    nmmBoard.roundBtnArray[rndmElem].doClick();
                    return;
                }

            }//end of remove
            

        }//end of if playAgainstComputer

    }//end of method doComputerMoves


//====================================end======================================
}// end of class
