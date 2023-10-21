package sprint2.production;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class Game {

    public static void setPlayer1Count(int player1Count) {
        Game.player1Count = player1Count;
    }

    public static void setPlayer2Count(int player2Count) {
        Game.player2Count = player2Count;
    }

    public static void setPlayer1turn(boolean player1turn) {
        Game.player1turn = player1turn;
    }

    public static int getPlayer1Count() {
        return player1Count;
    }

    public static int getPlayer2Count() {
        return player2Count;
    }

    public static boolean isPlayer1turn() {
        return player1turn;
    }

    private static int player1Count=0;
    private static int player2Count=0;
    static boolean player1turn=true;
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
                 System.out.println("entered it");
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


