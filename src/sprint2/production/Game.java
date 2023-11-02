package sprint2.production;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

public abstract class Game {
    Color player1Color, player2Color;
    final Color myBlack = new Color(0,0,0);
    final Color myWhite = new Color(221,186,126);

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

    public HashSet<Integer> player1Pieces=new HashSet<Integer>();
    public HashSet<Integer> player2Pieces=new HashSet<Integer>();

    int movePickIdx;

    enum gameStates{
        PLACE,
        MOVEPICK,
        MOVEDROP,
        FLYPICK,
        FLYDROP,
        REMOVE
    }

    gameStates player1GameState=gameStates.PLACE;
    gameStates player2GameState=gameStates.PLACE;

    public boolean placePiece(int idx, boolean player1IsWhite){
        if (Board.roundBtnArray[idx].currentBtnState == buttonStates.EMPTY) {

            if (Game.isPlayer1Turn() && Game.reducePlayer1count()) {
                Board.roundBtnArray[idx].currentBtnState = buttonStates.PLAYER1;
                Board.roundBtnArray[idx].setBackground(player1Color);

                Game.setPlayer1Turn(false);
                player1Pieces.add(idx);
                System.out.println(player1Pieces.toString());
                Board.roundBtnArray[idx].setBounds(285 + Board.x[idx] - (Board.dim2 / 2), Board.y[idx] - (Board.dim1 / 2), Board.dim2, Board.dim2);
                Board.roundBtnArray[idx].setBorder(BorderFactory.createEmptyBorder());
                GamePlayGUI.updatePlayerCountLabels(player1IsWhite);
//                if(checkMillFormation()){
//                    Game.setPlayer1Turn(true);
//                    player1GameState=gameStates.REMOVE;
//                }
                GamePlayGUI.updatePlayerTurnLabel();
                if(getPlayer1Count()==0){
                    player1GameState=gameStates.MOVEPICK;
                }
                return true;
            }
            else if (!Game.isPlayer1Turn() && Game.reducePlayer2count()) {
                Board.roundBtnArray[idx].currentBtnState = buttonStates.PLAYER2;
                Board.roundBtnArray[idx].setBackground(player2Color);

                Game.setPlayer1Turn(true);
                player2Pieces.add(idx);
                System.out.println(player2Pieces.toString());
                Board.roundBtnArray[idx].setBounds(285 + Board.x[idx] - (Board.dim2 / 2), Board.y[idx] - (Board.dim1 / 2), Board.dim2, Board.dim2);
                Board.roundBtnArray[idx].setBorder(BorderFactory.createEmptyBorder());
                GamePlayGUI.updatePlayerCountLabels(player1IsWhite);
                GamePlayGUI.updatePlayerTurnLabel();
                return true;
            }
        }
        return false;
    }// end of place function

    public boolean movePick(int idx) {
        movePickIdx=idx;
        return false;
    }
    public boolean moveDrop(int dropIdx){
//        if()
        return false;
    }
    public boolean flyPick(){
        return false;
    }
    public boolean flyDrop(){
        return false;
    }

    // create removePiece function here.

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

}


