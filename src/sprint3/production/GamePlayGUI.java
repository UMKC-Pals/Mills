package sprint3.production;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.*;

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
    private JCheckBox recordButton; // Button for recording
    private JButton replayButton; // Button for replaying
    private JButton newGameButton;


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

        int [] c = {0x23ee,0x23ea,0x23f5,0x23e9,0x23ed};

        for (int i = 0; i < mediaButtons.length; i++) {
            mediaButtons[i] = new JButton(Character.toString((char)c[i]));
            mediaButtons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            mediaButtons[i].setPreferredSize(new Dimension(50, 50)); // Adjust the size as needed

            mediaButtonLabel.add(mediaButtons[i]); // Add each button to the label
        }
        mediaButtonLabel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

        mediaButtonLabel.setBounds(300, 680, 600, 50);

        this.add(mediaButtonLabel);

// Record and replay buttons
        recordButton = new JCheckBox("Record");
        replayButton = new JButton("Replay");

        recordButton.setBounds(10,660,80,40);
        replayButton.setBounds(10,700,80,40);

        this.add(recordButton);
        this.add(replayButton);

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
