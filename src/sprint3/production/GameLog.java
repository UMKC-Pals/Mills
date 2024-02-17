package sprint3.production;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameLog {
    private int moveNumber;
    private int position;
    private int x;
    private int y;
    private int w;
    private int h;
    private String color;
    private String buttonState;
    private String turn;
    private int piecesLeft;

    private JSONObject movesObject;

    private org.json.simple.JSONArray parseMoveArray = new org.json.simple.JSONArray();

    public GameLog(){}

    //writing to file at a placing phase
    public void writeGameLogFile(String fileName){
        try (FileWriter fileWriter = new FileWriter(fileName)){
            fileWriter.write(movesObject.toString());
            fileWriter.flush();
        } catch (IOException ex){
            JOptionPane.showMessageDialog(null, "Error accessing the log file!");
            throw new RuntimeException(ex);
        }
    }

    public void writeMovesToFile(String fileName){
        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject jsonObject;

        try{
            jsonObject = (org.json.simple.JSONObject) parser.parse(new FileReader(fileName));
            org.json.simple.JSONArray moves = (org.json.simple.JSONArray) jsonObject.get("moves");
            org.json.simple.JSONObject move = new org.json.simple.JSONObject();

            move.put("move", moveNumber);
            move.put("position", position);
            move.put("x", x);
            move.put("y", y);
            move.put("w", w);
            move.put("h", h);
            move.put("color", color);
            move.put("state", buttonState);
            move.put("turn", turn);
            move.put("piecesLeft", 0);

            moves.add(move);
            jsonObject.put("moves", moves);

            String modifiedMoves = JSONValue.toJSONString(jsonObject);
            try(FileWriter fileWriter = new FileWriter(fileName)){
                fileWriter.write(modifiedMoves);
                fileWriter.close();
            } catch (IOException ex){
                JOptionPane.showMessageDialog(null, "Error accessing the log file!");
                throw new RuntimeException(ex);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error accessing the log file!");
            throw new RuntimeException();
        }
    }

    public void parseLogFile(String fileName) {
        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject movesObj;
        try {
            movesObj = (org.json.simple.JSONObject) parser.parse(new FileReader(fileName));
            this.parseMoveArray = (org.json.simple.JSONArray) movesObj.get("moves");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error accessing the log file!");
            throw new RuntimeException(ex);
        }
    }

    public org.json.simple.JSONArray getParseMoveArray() {
        return parseMoveArray;
    }
    public void setTurn(String turn) {
        this.turn = turn;
    }
    public void setPiecesLeft(int piecesLeft) {
        this.piecesLeft = piecesLeft;
    }
    public void setMoveNumber(int moveNumber) {
        this.moveNumber = moveNumber;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setW(int w) {
        this.w = w;
    }
    public void setH(int h) {
        this.h = h;
    }
    public void setButtonState(String buttonState) {
        this.buttonState = buttonState;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setMovesObject(JSONObject movesObject) {
        this.movesObject = movesObject;
    }




}

