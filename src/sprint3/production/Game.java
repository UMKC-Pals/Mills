package sprint3.production;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import static sprint3.production.Board.*;

public abstract class Game {
    Color player1Color, player2Color;
    final Color myBlack = new Color(0,0,0);
    final Color myWhite = new Color(221,186,126);

    private boolean isDropSelectedButtonEmpty;// for testing purpose
    private boolean isAdjacentPosition;// for testing purpose
    //-----------------------------------------------------
    private HashSet<TreeSet<Integer>> player1Mills = new HashSet<>();
    private HashSet<TreeSet<Integer>> player2Mills = new HashSet<>();


    public HashSet<TreeSet<Integer>> getPlayer1Mills() {
        return player1Mills;
    }

    public HashSet<TreeSet<Integer>> getPlayer2Mills() {
        return player2Mills;
    }


    public void setPlayer1Mills(HashSet<TreeSet<Integer>> player1Mills) {
        this.player1Mills = player1Mills;
    }


    public void setPlayer2Mills(HashSet<TreeSet<Integer>> player2Mills) {
        this.player2Mills = player2Mills;
    }

    //--------------------end -----------------------------------------
    public boolean getIsDropSelectedButtonEmpty() {// for testing purpose
        return isDropSelectedButtonEmpty;
    }

    public void setIsDropSelectedButtonEmpty(boolean isDropSelectedButtonEmpty){// for testing purpose
        this.isDropSelectedButtonEmpty = isDropSelectedButtonEmpty;
    }

    public boolean getIsAdjacentPosition() {// for testing purpose
        return isAdjacentPosition;
    }

    public void setAdjacentPosition(boolean adjacentPosition) {// for testing purpose
        isAdjacentPosition = adjacentPosition;
    }

    private static int player1Count=0;// the number of new un-used pieces player1
    private static int player2Count=0;// the number of new un-used pieces player2

    public static void setPlayer1Count(int player1Count) {
        Game.player1Count = player1Count;
    }

    public static void setPlayer2Count(int player2Count) {
        Game.player2Count = player2Count;
    }

    public static int getPlayer1Count() {
        return player1Count;
    }

    public static int getPlayer2Count() {
        return player2Count;
    }

    public static boolean reducePlayer1count(){
        if(player1Count>=1){
            player1Count=player1Count-1;
        }
        else{
            return false;
        }
        return true;
    }

    public static boolean reducePlayer2count(){
        if(player2Count>=1){
            player2Count=player2Count-1;
        }
        else{
            return false;
        }
        return true;
    }

    private static boolean isPlayer1Turn=true;
    public static void setPlayer1Turn(boolean player1Turn) {
        Game.isPlayer1Turn = player1Turn;
    }
    public static boolean isPlayer1Turn() {
        return isPlayer1Turn;
    }


    public ArrayList<Integer> player1Pieces = new ArrayList<>();
    public ArrayList<Integer> player2Pieces = new ArrayList<>();

    private int movePickIdx=-1,flyPickIdx=-1;// for testing purpose add private

    public int getMovePickIdx() {// for testing purpose
        return movePickIdx;
    }

    public void setMovePickIdx(int movePickIdx) {// for testing purpose
        this.movePickIdx = movePickIdx;
    }

    public int getFlyPickIdx() {// for testing purpose
        return flyPickIdx;
    }

    public void setFlyPickIdx(int flyPickIdx) {// for testing purpose
        this.flyPickIdx = flyPickIdx;
    }

    public gameStates player1GameState=gameStates.PLACE;// add public for testing purpose

    public gameStates player2GameState=gameStates.PLACE;// add public for testing purpose

    public boolean placePiece(int idx, boolean player1IsWhite){
        if (Board.roundBtnArray[idx].currentBtnState == buttonStates.EMPTY) {

            if (Game.isPlayer1Turn() && Game.reducePlayer1count()) {
                Board.roundBtnArray[idx].currentBtnState = buttonStates.PLAYER1;
                Board.roundBtnArray[idx].setBackground(player1Color);

                player1Pieces.add(idx);
                System.out.println("player 1 pieces: "+player1Pieces);
                Board.roundBtnArray[idx].setBounds(285 + x[idx] - (Board.dim2 / 2), y[idx] - (dim1 / 2), Board.dim2, Board.dim2);
                GamePlayGUI.updatePlayerCountLabels(player1IsWhite);

                if(getPlayer1Count()==0){
                    player1GameState=gameStates.MOVEPICK;
                }

                if(checkMillFormation(player1Pieces, player1Mills)){

                    Game.setPlayer1Turn(true);
                    GamePlayGUI.updatePlayerTurnLabel();

                    player1GameState=gameStates.REMOVE;

                    return true;
                }
                Game.setPlayer1Turn(false);
                GamePlayGUI.updatePlayerTurnLabel();


                checkGameOver();

                return true;
            }

            else if (!Game.isPlayer1Turn() && Game.reducePlayer2count()) {
                Board.roundBtnArray[idx].currentBtnState = buttonStates.PLAYER2;
                Board.roundBtnArray[idx].setBackground(player2Color);

                player2Pieces.add(idx);
                System.out.println("player 2 pieces: "+player2Pieces);
                Board.roundBtnArray[idx].setBounds(285 + x[idx] - (Board.dim2 / 2), y[idx] - (dim1 / 2), Board.dim2, Board.dim2);
                GamePlayGUI.updatePlayerCountLabels(player1IsWhite);

                if(getPlayer2Count()==0){
                    player2GameState=gameStates.MOVEPICK;
                }

                if(checkMillFormation(player2Pieces, player2Mills)){

                    Game.setPlayer1Turn(false);
                    GamePlayGUI.updatePlayerTurnLabel();

                    player2GameState=gameStates.REMOVE;

                    return true;
                }

                Game.setPlayer1Turn(true);
                GamePlayGUI.updatePlayerTurnLabel();

                checkGameOver();//

                return true;
            }
        }
        return false;
    }// end of place function

    public boolean movePick(int idx) {
        if(isPlayer1Turn() && player1Pieces.contains(idx)){
            System.out.println("Player1 state: "+player1GameState);

            movePickIdx=idx;
            player1GameState=gameStates.MOVEDROP;
            System.out.println("movePick player1"+idx);
            return true;
        }
        else if(!isPlayer1Turn() && player2Pieces.contains(idx)) {
            System.out.println("Player2 state: "+player2GameState);

            movePickIdx = idx;
            player2GameState=gameStates.MOVEDROP;
            System.out.println("movePick player2"+idx);
            return true;
        }
        return false;
    }
    public boolean moveDrop(int dropIdx){
        //updating movePick variable
        if(isPlayer1Turn() && player1Pieces.contains(dropIdx)){
            movePickIdx=dropIdx;
            return true;
        }
        else if(!isPlayer1Turn() && player2Pieces.contains(dropIdx)){
            movePickIdx=dropIdx;
            return true;
        }
        //moveDrop
        isDropSelectedButtonEmpty=Board.roundBtnArray[dropIdx].currentBtnState==buttonStates.EMPTY;// class field
        isAdjacentPosition = Board.getEdgeExists(movePickIdx,dropIdx);// class field

        if(isPlayer1Turn() && isAdjacentPosition && isDropSelectedButtonEmpty){
            //updating list variable
            player1Pieces.remove(Integer.valueOf(movePickIdx));
            player1Pieces.add(dropIdx);

            //updating gui buttons-change movePick btn-pl1
            Board.roundBtnArray[movePickIdx].currentBtnState = buttonStates.EMPTY;
            Board.roundBtnArray[movePickIdx].setBackground(Color.WHITE);
            Board.roundBtnArray[movePickIdx].setBounds(285+x[movePickIdx]-(dim1/2),y[movePickIdx],dim1,dim1);

            //updating gui buttons-change moveDrop btn-pl1
            Board.roundBtnArray[dropIdx].currentBtnState = buttonStates.PLAYER1;
            Board.roundBtnArray[dropIdx].setBackground(player1Color);
            Board.roundBtnArray[dropIdx].setBounds(285 + x[dropIdx] - (Board.dim2 / 2), y[dropIdx] - (dim1 / 2), Board.dim2, Board.dim2);

            //check for game end first
            checkGameOver();


            //check for mill formation,  remove if formed
            if(checkMillFormation(player1Pieces, player1Mills)){
                player1GameState=gameStates.REMOVE;
                return true;
            }



            Game.setPlayer1Turn(false);
            GamePlayGUI.updatePlayerTurnLabel();
            movePickIdx=-1;
            System.out.println("Player 1 state: "+player1GameState);

            return true;
        }
        if(!isPlayer1Turn() && isAdjacentPosition && isDropSelectedButtonEmpty){
            //updating list variable
            player2Pieces.remove(Integer.valueOf(movePickIdx));
            player2Pieces.add(dropIdx);

            //updating gui buttons-change movePick btn-pl2
            Board.roundBtnArray[movePickIdx].currentBtnState = buttonStates.EMPTY;
            Board.roundBtnArray[movePickIdx].setBackground(Color.WHITE);
            Board.roundBtnArray[movePickIdx].setBounds(285+x[movePickIdx]-(dim1/2),y[movePickIdx],dim1,dim1);

            //updating gui buttons-change moveDrop btn-pl2
            Board.roundBtnArray[dropIdx].currentBtnState = buttonStates.PLAYER2;
            Board.roundBtnArray[dropIdx].setBackground(player2Color);
            Board.roundBtnArray[dropIdx].setBounds(285 + x[dropIdx] - (Board.dim2 / 2), y[dropIdx] - (dim1 / 2), Board.dim2, Board.dim2);


            //check for game end first
            checkGameOver();



            //check for mill formation,  remove if formed
            if(checkMillFormation(player2Pieces, player2Mills)){
                player2GameState=gameStates.REMOVE;
                return true;
            }



            Game.setPlayer1Turn(true);
            GamePlayGUI.updatePlayerTurnLabel();
            movePickIdx=-1;
            System.out.println("Player 2 state: "+player2GameState);
            return true;
        }
        return false;
    }
    public boolean flyPick(int idx){
        if(isPlayer1Turn() && player1Pieces.contains(idx)){
            flyPickIdx=idx;
            player1GameState=gameStates.FLYDROP;
            System.out.println("flyPick player1"+idx);
            return true;
        }
        else if(!isPlayer1Turn() && player2Pieces.contains(idx)) {
            flyPickIdx = idx;
            player2GameState=gameStates.FLYDROP;
            System.out.println("flyPick player2"+idx);
            return true;
        }
        return false;
    }
    public boolean flyDrop(int dropIdx){
        //updating flyPick variable
        if(isPlayer1Turn() && player1Pieces.contains(dropIdx)){
            flyPickIdx=dropIdx;
            return true;
        }
        else if(!isPlayer1Turn() && player2Pieces.contains(dropIdx)){
            flyPickIdx=dropIdx;
            return true;
        }
        //flyDrop
        boolean isDropSelectedButtonEmpty=Board.roundBtnArray[dropIdx].currentBtnState==buttonStates.EMPTY;

        if(isPlayer1Turn()  && isDropSelectedButtonEmpty){
            //updating list variable
            player1Pieces.remove(Integer.valueOf(flyPickIdx));
            player1Pieces.add(dropIdx);

            //updating gui buttons-change flyPick btn-pl1
            Board.roundBtnArray[flyPickIdx].currentBtnState = buttonStates.EMPTY;
            Board.roundBtnArray[flyPickIdx].setBackground(Color.WHITE);
            Board.roundBtnArray[flyPickIdx].setBounds(285+x[flyPickIdx]-(dim1/2),y[flyPickIdx],dim1,dim1);

            //updating gui buttons-change flyDrop btn-pl1
            Board.roundBtnArray[dropIdx].currentBtnState = buttonStates.PLAYER1;
            Board.roundBtnArray[dropIdx].setBackground(player1Color);
            Board.roundBtnArray[dropIdx].setBounds(285 + x[dropIdx] - (Board.dim2 / 2), y[dropIdx] - (dim1 / 2), Board.dim2, Board.dim2);

            //check for game end first
            checkGameOver();

            //check for mill formation,  remove if formed
            if(checkMillFormation(player1Pieces, player1Mills)){
                player1GameState=gameStates.REMOVE;
                return true;
            }


            Game.setPlayer1Turn(false);
            GamePlayGUI.updatePlayerTurnLabel();
            flyPickIdx=-1;
            player1GameState=gameStates.FLYPICK;
            return true;
        }
        if(!isPlayer1Turn() && isDropSelectedButtonEmpty){
            //updating list variable
            player2Pieces.remove(Integer.valueOf(flyPickIdx));
            player2Pieces.add(dropIdx);

            //updating gui buttons-change flyPick btn-pl2
            Board.roundBtnArray[flyPickIdx].currentBtnState = buttonStates.EMPTY;
            Board.roundBtnArray[flyPickIdx].setBackground(Color.WHITE);
            Board.roundBtnArray[flyPickIdx].setBounds(285+x[flyPickIdx]-(dim1/2),y[flyPickIdx],dim1,dim1);

            //updating gui buttons-change flyDrop btn-pl2
            Board.roundBtnArray[dropIdx].currentBtnState = buttonStates.PLAYER2;
            Board.roundBtnArray[dropIdx].setBackground(player2Color);
            Board.roundBtnArray[dropIdx].setBounds(285 + x[dropIdx] - (Board.dim2 / 2), y[dropIdx] - (dim1 / 2), Board.dim2, Board.dim2);

            //check for game end first
            checkGameOver();

            //check for mill formation,  remove if formed
            if(checkMillFormation(player2Pieces, player2Mills)){
                player2GameState=gameStates.REMOVE;
                return true;
            }

            Game.setPlayer1Turn(true);
            GamePlayGUI.updatePlayerTurnLabel();
            flyPickIdx=-1;
            player2GameState=gameStates.FLYPICK;
            return true;
        }
        return false;
    }

    public boolean removePiece(boolean isPlayer1Turn, int idx){
        //once the player 1 placed/moved his piece and formed a mill the turn remains his

        //check if given idx is valid piece to remove
        //if yes, proceed
        //if no, return back, make NO change to state and turn

        //invalid piece, if all pieces are in mills, then skip

        if(isPlayer1Turn  && player2Pieces.contains(idx)){
            HashSet<Integer> player2MillPieces = new HashSet<Integer>();

            for( TreeSet<Integer> mill: player2Mills) {
                player2MillPieces.addAll(mill);
            }
            if(player2Mills.size()>0 && player2MillPieces.contains(idx)){
                if( player2MillPieces.size() != player2Pieces.size() ){
                    return false;
                }
            }
            System.out.println("inside remove, player 1 removing");

            player1GameState = gameStates.REMOVE;

            player2Pieces.remove(Integer.valueOf(idx));

            roundBtnArray[idx].currentBtnState = buttonStates.EMPTY;
            roundBtnArray[idx].setBackground(Color.WHITE);
            roundBtnArray[idx].setBounds(285 + x[idx] - (dim1/2), y[idx], dim1, dim1);

            checkMillFormation(player2Pieces,getPlayer2Mills());

            setPlayer1Turn(false);
            GamePlayGUI.updatePlayerTurnLabel();

            if(player1Count>0){
                player1GameState=gameStates.PLACE;
                System.out.println("Player 1 State:"+player1GameState);
            }
            else if (player1Count==0 && player1Pieces.size()>3) {
                player1GameState=gameStates.MOVEPICK;
                System.out.println("Player 1 State:"+player1GameState);

            }
            else if(player1Count==0 && player1Pieces.size()==3){
                player1GameState=gameStates.FLYPICK;
                System.out.println("Player 1 State:"+player1GameState);

            }
            if(player2Count>0){
                player2GameState=gameStates.PLACE;
                System.out.println("Player 2 State:"+player2GameState);

            }
            else if (player2Count==0 && player2Pieces.size()>3) {
                player2GameState=gameStates.MOVEPICK;
                System.out.println("Player 2 State:"+player2GameState);

            }
            else if(player2Count==0 && player2Pieces.size()==3){
                player2GameState=gameStates.FLYPICK;
                System.out.println("Player 2 State:"+player2GameState);

            }

            checkGameOver();
            return true;
        }
        else if(!isPlayer1Turn  && player1Pieces.contains(idx)){
            HashSet<Integer> player1MillPieces = new HashSet<Integer>();

            for( TreeSet<Integer> mill: player1Mills) {
                player1MillPieces.addAll(mill);
            }
            if(player1Mills.size()>0 && player1MillPieces.contains(idx)){
                if( player1MillPieces.size() != player1Pieces.size() ){
                    return false;
                }
            }
            System.out.println("inside remove, player 2 removing");

            player2GameState = gameStates.REMOVE;

            player1Pieces.remove(Integer.valueOf(idx));

            roundBtnArray[idx].currentBtnState = buttonStates.EMPTY;
            roundBtnArray[idx].setBackground(Color.WHITE);
            roundBtnArray[idx].setBounds(285 + x[idx] - (dim1/2), y[idx], dim1, dim1);

            checkMillFormation(player2Pieces,getPlayer2Mills());

            setPlayer1Turn(true);
            GamePlayGUI.updatePlayerTurnLabel();

            if(player1Count>0){
                player1GameState=gameStates.PLACE;
                System.out.println("Player 1 State:"+player1GameState);

            }
            else if (player1Count==0 && player1Pieces.size()>3) {
                player1GameState=gameStates.MOVEPICK;
                System.out.println("Player 1 State:"+player1GameState);

            }
            else if(player1Count==0 && player1Pieces.size()==3){
                player1GameState=gameStates.FLYPICK;
                System.out.println("Player 1 State:"+player1GameState);

            }
            if(player2Count>0){
                player2GameState=gameStates.PLACE;
                System.out.println("Player 2 State:"+player2GameState);

            }
            else if (player2Count==0 && player2Pieces.size()>3) {
                player2GameState=gameStates.MOVEPICK;
                System.out.println("Player 2 State:"+player2GameState);

            }
            else if(player2Count==0 && player2Pieces.size()==3){
                player2GameState=gameStates.FLYPICK;
                System.out.println("Player 2 State:"+player2GameState);

            }
            checkGameOver();
            return true;
        }
        checkGameOver();
        return false;
    }

    public boolean dirAndFileSetup(){

        String homeDir= System.getProperty("user.home");
        Path gameDirPath= Paths.get(homeDir,"MillsData");
        boolean gameDirExists=false;

        if(Files.exists(gameDirPath) == false){
            File gameDir = new File(String.valueOf(gameDirPath));
            try{
                gameDirExists=gameDir.mkdir();
            }
            catch (Throwable e){
                System.out.println("Exception in creating Game Directory: ");
                e.printStackTrace();
                return false;
            }

        }
        else{
            gameDirExists=true;
        }

        boolean gameFileExists = false;

        if(gameDirExists){
            Path gameLogFilePath = Paths.get(String.valueOf(gameDirPath),  "gameLogFile.txt");
            gameFileExists = Files.exists(gameLogFilePath);

            File gameLogFile= new File(String.valueOf(gameLogFilePath));

            if(gameFileExists == false){
//            gameLogFile = new File(String.valueOf(gameLogFilePath));
                try {
                    boolean createdFile =  gameLogFile.createNewFile();
                } catch (IOException e) {
                    System.out.println("Exception in creating Game File: ");
                    e.printStackTrace();
                    return false;
                }
                boolean writePermission = gameLogFile.setWritable(true);
                boolean readPermission = gameLogFile.setReadable(true);


                gameFileExists=true;
            }

            if(gameLogFile.canRead() && gameLogFile.canWrite()){
                try{
                    System.out.println("Game log file entered.");
                    FileWriter writerObj = new FileWriter(gameLogFile,true);
                    writerObj.write("123\n");
                    writerObj.close();
                }
                catch(IOException e){
                    System.out.println("File write error.");
                    e.printStackTrace();
                    return false;
                }

            }
        }
        return true;
    }
    public abstract boolean checkMillFormation(ArrayList<Integer> playersPieces, HashSet<TreeSet<Integer>> formedMills);//

    public abstract boolean checkGameOver();//

}