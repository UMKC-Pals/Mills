package sprint3.production;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.util.TimerTask;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



public class GamePlayGUI extends JFrame{

    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel centerPanel;
    private JLabel imageLabel;
    private static JLabel whitePlayerCountLabel;
    private static JLabel blackPlayerCountLabel;
    private static JLabel currentTurn;
    private JLabel mediaButtonLabel; // Label for buttons
    private JButton[] mediaButtons; // Array of buttons
    public static JCheckBox recordButton; // Button for recording
    private JButton replayButton; // Button for replaying
    private JButton newGameButton;





    private JRadioButton autoReplay;//olena
    private JRadioButton manualReplay;//olena
    private Timer timer;//olena

    private GameLog gameLog;

    private JSONArray array;//olena







    public JRadioButton getAutoReplay() {//olena
        return autoReplay;
    }//for testing purpose

    public static boolean record = false;//olena
    public URL imageUrl;
    private int menNum;//olena
    private String player1LastPlace, player2LastPlace;//olena
    private int currentMove = 0;//olena
    private int previousMove = 0;//olena



    NMMGame nmmGame;
    TMMGame tmmGame;
    SMMGame smmGame;
    TwMMGame twmmGame;

    Game currentGame;

    public static void updatePlayerCountLabels(boolean player1IsWhite){
        whitePlayerCountLabel.setText("Player 1 "+(player1IsWhite?"(White)":"(Black)")+" has : "+String.valueOf(Game.getPlayer1Count())+" men.");
        blackPlayerCountLabel.setText("Player 2 "+(player1IsWhite?"(Black)":"(White)")+" has : "+String.valueOf(Game.getPlayer2Count())+" men.");

    }

    public GamePlayGUI(int numberOfMen, boolean playAgainstComputer, boolean player1IsWhite) {

        this.gameLog=new GameLog();//olena
        this.menNum=numberOfMen;//olena


        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(1200,800);



        URL imageUrl = null;
        ImageIcon imageIcon = null;

        if(numberOfMen==9){
            this.setTitle("Mills - 9 Men's Morris Game - by Pals");
            imageUrl=GamePlayGUI.class.getResource("/nineBoardNoBg.png");
            nmmGame = new NMMGame(playAgainstComputer, player1IsWhite);
            currentGame=nmmGame;

            for(int i=0;i<25;i++){
                this.add(nmmGame.nmmBoard.roundBtnArray[i]);
            }
        }
        if(numberOfMen==3){
            this.setTitle("Mills - 3 Men's Morris Game - by Pals");
            imageUrl=GamePlayGUI.class.getResource("/threeBoardNoBg.png");
            tmmGame = new TMMGame(playAgainstComputer,player1IsWhite);
            currentGame=tmmGame;
            for(int i=6;i<=8;i++){
                this.add(tmmGame.tmmBoard.roundBtnArray[i]);
            }// 6 - 8
            for(int i=11;i<=12;i++){
                this.add(tmmGame.tmmBoard.roundBtnArray[i]);
            }// 11, 12
            for(int i=15;i<=17;i++){
                this.add(tmmGame.tmmBoard.roundBtnArray[i]);
            }// 15 - 17
            for(int i=24;i<=24;i++){
                this.add(tmmGame.tmmBoard.roundBtnArray[i]);
            }// 24


        }
        if(numberOfMen==6){
            this.setTitle("Mills - 6 Men's Morris Game - by Pals");
            imageUrl=GamePlayGUI.class.getResource("/sixBoardNoBg.png");
            smmGame = new SMMGame(playAgainstComputer);
            currentGame=smmGame;

        }
        if(numberOfMen==12){
            this.setTitle("Mills - 12 Men's Morris Game - by Pals");
            imageUrl=GamePlayGUI.class.getResource("/twelveBoardNoBg.png");
            twmmGame = new TwMMGame(playAgainstComputer);
            currentGame=twmmGame;

        }

        this.setResizable(false);
        this.setLocationRelativeTo(null); // to display the JFrame centered to the screen

        leftPanel = new JPanel();
        centerPanel = new JPanel();
        rightPanel = new JPanel();

//        leftPanel.setBackground(GRAY);
        leftPanel.setSize(285,800);
        leftPanel.setBounds(0,0,285,800);
        leftPanel.setVisible(true);

//        centerPanel.setBackground(BLUE);
        centerPanel.setSize(630,800);
        centerPanel.setBounds(285,0,630,800);
        centerPanel.setVisible(true);

//        rightPanel.setBackground(RED);
        rightPanel.setSize(285,800);
        rightPanel.setBounds(915,0,285,800);
        rightPanel.setVisible(true);

//        Initializing counts for the players
        whitePlayerCountLabel = new JLabel("Player 1 "+(player1IsWhite?"(White)":"(Black)")+" has : "+String.valueOf(Game.getPlayer1Count())+" men.");
        blackPlayerCountLabel = new JLabel("Player 2 "+(player1IsWhite?"(Black)":"(White)")+" has : "+String.valueOf(Game.getPlayer2Count())+" men.");

        Font labelFont = new Font("SansSerif", Font.PLAIN, 12);
        whitePlayerCountLabel.setFont(labelFont);
        blackPlayerCountLabel.setFont(labelFont);

// Set bounds for white player count label
        whitePlayerCountLabel.setBounds(10, 320, 200, 30);

        // Set bounds for black player count label
        blackPlayerCountLabel.setBounds(10, 350, 200, 30);

        this.add(whitePlayerCountLabel);
        this.add(blackPlayerCountLabel);

// Initializing current turn label
        currentTurn = new JLabel("Current Turn : "+(Game.isPlayer1Turn() ?"Player 1":"Player 2"));
        currentTurn.setBounds(940,325,200,30);

        this.add(currentTurn);
// buttons


        // Create and initialize an array of buttons
        mediaButtonLabel = new JLabel();
        mediaButtons = new JButton[5];

        //int [] c = {0x23ee,0x23ea,0x23f5,0x23e9,0x23ed};
        int[] c = {0x23EE, 0x23EA, 0x23F5, 0x23E9, 0x23ED};

        for (int i = 0; i < mediaButtons.length; i++) {
            mediaButtons[i] = new JButton(Character.toString((char)c[i]));
            //mediaButtons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            mediaButtons[i].setPreferredSize(new Dimension(50, 50)); // Adjust the size as needed

            mediaButtonLabel.add(mediaButtons[i]); // Add each button to the label
            mediaButtons[i].setEnabled(false);//olena
        }
        mediaButtonLabel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

        mediaButtonLabel.setBounds(300, 680, 600, 50);

        this.add(mediaButtonLabel);

// Record and replay buttons
        recordButton = new JCheckBox("Record");
        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                record = true;
                if (currentGame.gameLogFile.exists()) {
                    currentGame.gameLogFile.delete();
                    currentGame.dirAndFileSetup();
                }
            }//actionPerformed end
        });

        replayButton = new JButton("Replay");

        autoReplay = new JRadioButton("Auto Replay");
        manualReplay = new JRadioButton("Manual Replay");
        autoReplay.setEnabled(false);
        manualReplay.setEnabled(false);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(autoReplay);
        buttonGroup.add(manualReplay);

        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autoReplay.setEnabled(true);
                manualReplay.setEnabled(true);
                recordButton.setEnabled(false);
                //flagAutoReplay = true;

                for(int i = 0; i < Board.roundBtnArray.length; i++){
                    if (Board.roundBtnArray[i].currentBtnState != buttonStates.INVALID) {
                        Board.roundBtnArray[i].currentBtnState = buttonStates.EMPTY;
                        Board.roundBtnArray[i].setBackground(Color.WHITE);
                        Board.roundBtnArray[i].setVisible(true);
                        Board.roundBtnArray[i].setBounds(285+Board.x[i]-(Board.dim1/2),Board.y[i],Board.dim1,Board.dim1);
                        Board.roundBtnArray[i].setBorder(BorderFactory.createEmptyBorder());
                    }
                }
            }
        });

        //recordButton.setBounds(10,660,80,40);
        //replayButton.setBounds(10,700,80,40);
        recordButton.setBounds(10, 600, 80, 40);
        replayButton.setBounds(10, 640, 80, 40);

        this.add(recordButton);
        this.add(replayButton);

        autoReplay.setBounds(10, 690, 120, 30);
        manualReplay.setBounds(10, 715, 120, 30);

        String homeDir = System.getProperty("user.home");
        String fileName = homeDir + "/MillsData/gameLogFile.json";

        gameLog.parseLogFile(fileName);
        array = gameLog.getParseMoveArray();//returns array of all moves saved in a JSON file

        GamePlayGUI thisGameplayGUI=this;




// New game
        newGameButton= new JButton("New Game");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameSetupGUI.setMyGameSetupGUI(new GameSetupGUI());
                setVisible(false);//hiding gameplayGUI
                dispose();// disposing gameplayGUI

            }
        });
        newGameButton.setBounds(20,20,100,30);
        this.add(newGameButton);





        //RadioButton autoReplay. Select this radio button to start auto replay.
//Media buttons remain disabled in this mode.
        autoReplay.addActionListener(new ActionListener() {
            @Override
            public synchronized void actionPerformed(ActionEvent e) {
                for (int i = 0; i < mediaButtons.length; i++) {
                    mediaButtons[i].setEnabled(false);
                }
                System.out.println("AUTO replay");
                java.util.Timer timer = new java.util.Timer();
                int delay = 1000;
                int interval = 1000;//2 sec
                for (int i = 0; i < array.size(); i++) {
                    int finalI = i;
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            JSONObject moveObject = (org.json.simple.JSONObject) array.get(finalI);
                            int position = ((Number) moveObject.get("position")).intValue();
                            System.out.println("position" + position);
                            int x = ((Number) moveObject.get("x")).intValue();
                            int y = ((Number) moveObject.get("y")).intValue();
                            int w = ((Number) moveObject.get("w")).intValue();
                            int h = ((Number) moveObject.get("h")).intValue();
                            int piecesLeft = ((Number) moveObject.get("piecesLeft")).intValue();
                            String color = (String) moveObject.get("color");
                            String state = (String) moveObject.get("state");
                            String player = "";
                            if (state.equals("PLAYER1")) {
                                player = "Player 1";
                            } else if (state.equals("PLAYER2")) {
                                player = "Player 2";
                            }

                            Scanner sc = new Scanner(color);
                            sc.useDelimiter("\\D+");
                            Color color1 = new Color(sc.nextInt(), sc.nextInt(), sc.nextInt());

                            String turn = (String) moveObject.get("turn");

                            nmmGame.nmmBoard.roundBtnArray[position].setBounds(x, y, w, h);
                            nmmGame.nmmBoard.roundBtnArray[position].setBackground(color1);
                            if (finalI == 16) {
                                player1LastPlace = player;
                            }
                            if (finalI == 17) {
                                player2LastPlace = player;
                            }
                            if (finalI < 18) {
                                if (!Objects.equals(color1.toString(), Color.BLACK.toString())) {
                                    whitePlayerCountLabel.setText(player + " (White) has : " + piecesLeft + " men.");
                                } else if (Objects.equals(color1.toString(), Color.BLACK.toString())) {
                                    blackPlayerCountLabel.setText(player + " (Black) has : " + piecesLeft + " men.");
                                }
                            } else {
                                whitePlayerCountLabel.setText(player1LastPlace + " (White) has : " + piecesLeft + " men.");
                                blackPlayerCountLabel.setText(player2LastPlace + " (Black) has : " + piecesLeft + " men.");
                            }

                            currentTurn.setText("Current turn: " + turn);

                            if (finalI == array.size() - 1){
                                String result = turn + " won!";
                                JOptionPane.showMessageDialog(null, result);
                            }


                        }
                    }, delay + (i * interval));
                }
            }
        });//end auto replay

        manualReplay.addActionListener(new ActionListener() {
            @Override
            public synchronized void actionPerformed(ActionEvent e) {
                for (int i = 0; i < mediaButtons.length; i++) {
                    mediaButtons[i].setEnabled(true);
                }
            }
        });//end manual replay

        mediaButtons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JSONObject moveObject = (org.json.simple.JSONObject)array.get(currentMove);

                int position = ((Number) moveObject.get("position")).intValue();
                System.out.println("position" + position);
                int x = ((Number) moveObject.get("x")).intValue();
                int y = ((Number) moveObject.get("y")).intValue();
                int w = ((Number) moveObject.get("w")).intValue();
                int h = ((Number) moveObject.get("h")).intValue();
                int piecesLeft = ((Number) moveObject.get("piecesLeft")).intValue();
                String color = (String) moveObject.get("color");
                String state = (String) moveObject.get("state");
                String player = "";
                if (state.equals("PLAYER1")) {
                    player = "Player 1";
                } else if (state.equals("PLAYER2")) {
                    player = "Player 2";
                }

                Scanner sc = new Scanner(color);
                sc.useDelimiter("\\D+");
                Color color1 = new Color(sc.nextInt(), sc.nextInt(), sc.nextInt());

                String turn = (String) moveObject.get("turn");

                nmmGame.nmmBoard.roundBtnArray[position].setBounds(x, y, w, h);
                nmmGame.nmmBoard.roundBtnArray[position].setBackground(color1);

                if (!Objects.equals(color1.toString(), Color.BLACK.toString())) {
                    whitePlayerCountLabel.setText(player + " (White) has : " + piecesLeft + " men.");
                } else if (Objects.equals(color1.toString(), Color.BLACK.toString())) {
                    blackPlayerCountLabel.setText(player + " (Black) has : " + piecesLeft + " men.");
                }

                currentTurn.setText("Current turn: " + turn);
                mediaButtons[2].setEnabled(false);

                if (currentMove == array.size() - 1){
                    String result = turn + " won!";
                    JOptionPane.showMessageDialog(null, result);
                }

                currentMove += 1;
                previousMove = currentMove - 1;
            }
        });

//move to the very first move, clear the rest of the board
        mediaButtons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JSONObject moveObject = (org.json.simple.JSONObject) array.get(0);
                currentMove = 1;
                previousMove = 0;
                int position = ((Number) moveObject.get("position")).intValue();
                System.out.println("position" + position);
                int x = ((Number) moveObject.get("x")).intValue();
                int y = ((Number) moveObject.get("y")).intValue();
                int w = ((Number) moveObject.get("w")).intValue();
                int h = ((Number) moveObject.get("h")).intValue();
                int piecesLeft = ((Number) moveObject.get("piecesLeft")).intValue();
                String color = (String) moveObject.get("color");
                String state = (String) moveObject.get("state");
                String player = "";
                if (state.equals("PLAYER1")) {
                    player = "Player 1";
                } else if (state.equals("PLAYER2")) {
                    player = "Player 2";
                }


                Scanner sc = new Scanner(color);
                sc.useDelimiter("\\D+");
                Color color1 = new Color(sc.nextInt(), sc.nextInt(), sc.nextInt());

                String turn = (String) moveObject.get("turn");

                nmmGame.nmmBoard.roundBtnArray[position].setBounds(x, y, w, h);
                nmmGame.nmmBoard.roundBtnArray[position].setBackground(color1);

                if (!Objects.equals(color1.toString(), Color.BLACK.toString())) {
                    whitePlayerCountLabel.setText(player + " (White) has : " + piecesLeft + " men.");
                    if (Objects.equals(player, "Player 1")){
                        blackPlayerCountLabel.setText("Player 2 (Black) has : " + 9 + " men.");
                    }

                } else if (Objects.equals(color1.toString(), Color.BLACK.toString())) {
                    blackPlayerCountLabel.setText(player + " (Black) has : " + piecesLeft + " men.");
                    if (Objects.equals(player, "Player 2")){
                        whitePlayerCountLabel.setText("Player 2 (White) has : " + 9 + " men.");
                    }
                }

                currentTurn.setText("Current turn: " + turn);

                for(int i = 0; i < Board.roundBtnArray.length; i++){
                    if (Board.roundBtnArray[i].currentBtnState != buttonStates.INVALID && i != position) {
                        Board.roundBtnArray[i].currentBtnState = buttonStates.EMPTY;
                        Board.roundBtnArray[i].setBackground(Color.WHITE);
                        Board.roundBtnArray[i].setVisible(true);
                        Board.roundBtnArray[i].setBounds(285+Board.x[i]-(Board.dim1/2),Board.y[i],Board.dim1,Board.dim1);
                        Board.roundBtnArray[i].setBorder(BorderFactory.createEmptyBorder());
                    }
                }

                mediaButtons[0].setEnabled(false);
                mediaButtons[2].setEnabled(true);
            }
        });

//1 step forward.
        mediaButtons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JSONObject moveObject = (org.json.simple.JSONObject) array.get(currentMove);


                int position = ((Number) moveObject.get("position")).intValue();
                System.out.println("position" + position);
                int x = ((Number) moveObject.get("x")).intValue();
                int y = ((Number) moveObject.get("y")).intValue();
                int w = ((Number) moveObject.get("w")).intValue();
                int h = ((Number) moveObject.get("h")).intValue();
                int piecesLeft = ((Number) moveObject.get("piecesLeft")).intValue();
                String color = (String) moveObject.get("color");
                String state = (String) moveObject.get("state");
                String player = "";
                if (state.equals("PLAYER1")) {
                    player = "Player 1";
                } else if (state.equals("PLAYER2")) {
                    player = "Player 2";
                }


                Scanner sc = new Scanner(color);
                sc.useDelimiter("\\D+");
                Color color1 = new Color(sc.nextInt(), sc.nextInt(), sc.nextInt());

                String turn = (String) moveObject.get("turn");

                nmmGame.nmmBoard.roundBtnArray[position].setBounds(x, y, w, h);
                nmmGame.nmmBoard.roundBtnArray[position].setBackground(color1);

                if (currentMove == 16) {
                    player1LastPlace = player;
                }
                if (currentMove == 17) {
                    player2LastPlace = player;
                }
                if (currentMove < 18) {
                    if (!Objects.equals(color1.toString(), Color.BLACK.toString())) {
                        whitePlayerCountLabel.setText(player + " (White) has : " + piecesLeft + " men.");
                    } else if (Objects.equals(color1.toString(), Color.BLACK.toString())) {
                        blackPlayerCountLabel.setText(player + " (Black) has : " + piecesLeft + " men.");
                    }
                } else {
                    whitePlayerCountLabel.setText(player1LastPlace + " (White) has : " + piecesLeft + " men.");
                    blackPlayerCountLabel.setText(player2LastPlace + " (Black) has : " + piecesLeft + " men.");
                }

                currentTurn.setText("Current turn: " + turn);

                if (currentMove == array.size() - 1){
                    String result = turn + " won!";
                    JOptionPane.showMessageDialog(null, turn);
                    mediaButtons[3].setEnabled(false);
                }

                currentMove += 1;
                previousMove = currentMove - 1;

                //if (currentMove == array.size() - 1){
                //    mediaButtons[3].setEnabled(false);
                //}
            }
        });

//move to the last move
        mediaButtons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = currentMove; i < array.size(); i++) {
                    JSONObject moveObject = (org.json.simple.JSONObject) array.get(i);
                    int position = ((Number) moveObject.get("position")).intValue();
                    System.out.println("position" + position);
                    int x = ((Number) moveObject.get("x")).intValue();
                    int y = ((Number) moveObject.get("y")).intValue();
                    int w = ((Number) moveObject.get("w")).intValue();
                    int h = ((Number) moveObject.get("h")).intValue();
                    int piecesLeft = ((Number) moveObject.get("piecesLeft")).intValue();
                    String color = (String) moveObject.get("color");
                    String state = (String) moveObject.get("state");
                    String player = "";
                    if (state.equals("PLAYER1")) {
                        player = "Player 1";
                    } else if (state.equals("PLAYER2")) {
                        player = "Player 2";
                    }


                    Scanner sc = new Scanner(color);
                    sc.useDelimiter("\\D+");
                    Color color1 = new Color(sc.nextInt(), sc.nextInt(), sc.nextInt());

                    String turn = (String) moveObject.get("turn");

                    nmmGame.nmmBoard.roundBtnArray[position].setBounds(x, y, w, h);
                    nmmGame.nmmBoard.roundBtnArray[position].setBackground(color1);
                    if (i == 16) {
                        player1LastPlace = player;
                    }
                    if (i == 17) {
                        player2LastPlace = player;
                    }
                    if (i < 18) {
                        if (!Objects.equals(color1.toString(), Color.BLACK.toString())) {
                            whitePlayerCountLabel.setText(player + " (White) has : " + piecesLeft + " men.");
                        } else if (Objects.equals(color1.toString(), Color.BLACK.toString())) {
                            blackPlayerCountLabel.setText(player + " (Black) has : " + piecesLeft + " men.");
                        }
                    } else {
                        whitePlayerCountLabel.setText(player1LastPlace + " (White) has : " + piecesLeft + " men.");
                        blackPlayerCountLabel.setText(player2LastPlace + " (Black) has : " + piecesLeft + " men.");
                    }

                    currentTurn.setText("Current turn: " + turn);

                    if (i == array.size() - 1){
                        String result = turn + " won!";
                        JOptionPane.showMessageDialog(null, result);
                    }
                }

            }
        });


//1 step back
        mediaButtons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentMove > 0 ) {
                    JSONObject moveObject = (org.json.simple.JSONObject) array.get(currentMove - 1);
                    System.out.println("currentMove - 1 " + (currentMove - 1));
                    int position = ((Number) moveObject.get("position")).intValue();
                    System.out.println("position" + position);
                    int x = 285 + Board.x[position] - Board.dim1 / 2;
                    int y = Board.y[position];
                    int w = Board.dim1;
                    int h = Board.dim1;
                    int piecesLeft = ((Number) moveObject.get("piecesLeft")).intValue();
                    String state = (String) moveObject.get("state");
                    String player = "";
                    if (state.equals("PLAYER1")) {
                        player = "Player 1";
                    } else if (state.equals("PLAYER2")) {
                        player = "Player 2";
                    }

                    String color = (String) moveObject.get("color");
                    Scanner sc = new Scanner(color);
                    sc.useDelimiter("\\D+");
                    Color color1 = new Color(sc.nextInt(), sc.nextInt(), sc.nextInt());

                    nmmGame.nmmBoard.roundBtnArray[position].setBounds(x, y, w, h);
                    nmmGame.nmmBoard.roundBtnArray[position].setBackground(Color.WHITE);

                    if (currentMove - 1 == 16) {
                        player1LastPlace = player;
                    }
                    if (currentMove - 1 == 17) {
                        player2LastPlace = player;
                    }

                    String turn = (String) moveObject.get("turn");
                    currentTurn.setText("Current turn: " + (turn.equals("player 1") ? "player 2" : "player 1"));

                    if (currentMove < 18) {
                        if (!Objects.equals(color1.toString(), Color.BLACK.toString()) && state.equals("PLAYER1")) {
                            whitePlayerCountLabel.setText("Player 1 (White) has : " + (piecesLeft + 1) + " men.");
                        } else if (Objects.equals(color1.toString(), Color.BLACK.toString()) && state.equals("PLAYER2")) {
                            blackPlayerCountLabel.setText("Player 2 (Black) has : " + (piecesLeft + 1) + " men.");
                        } else if (Objects.equals(color1.toString(), Color.BLACK.toString()) && state.equals("PLAYER1")) {
                            System.out.println("Black player 1");
                            blackPlayerCountLabel.setText("Player 1 (Black) has : " + (piecesLeft + 1) + " men.");
                            whitePlayerCountLabel.setText("Player 2 (White) has : " + piecesLeft + " men.");
                        }
                    } else {
                        whitePlayerCountLabel.setText(player1LastPlace + " (White) has : " + piecesLeft + " men.");
                        blackPlayerCountLabel.setText(player2LastPlace + " (Black) has : " + piecesLeft + " men.");
                    }

                    if (currentMove > 0)
                        currentMove -= 1;

                }
                mediaButtons[2].setEnabled(true);
            }
        });

        this.add(autoReplay);
        this.add(manualReplay);








        this.add(leftPanel);
        this.add(rightPanel);
        this.add(centerPanel);

        try{
            imageIcon=new ImageIcon(imageUrl);
        }
        catch(Exception e){
            System.out.println("Image file not found!");
        }

        imageLabel = new JLabel(imageIcon);
        centerPanel.add(imageLabel);

        this.setVisible(true);
    }
    public static void updatePlayerTurnLabel() {
        currentTurn.setText("Current Turn : "+(Game.isPlayer1Turn() ?"Player 1":"Player 2"));
        
    }

}
