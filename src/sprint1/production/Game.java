package sprint1.production;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Game {

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
