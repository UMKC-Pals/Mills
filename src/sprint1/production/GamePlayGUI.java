package sprint1.production;

import javax.swing.*;
import java.net.URL;
import java.awt.*;

public class GamePlayGUI extends JFrame{

    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel centerPanel;
    private JLabel imageLabel;
    private JLabel whitePlayerCountLabel;
    private JLabel blackPlayerCountLabel;

    NMMGame nmmGame;
    TMMGame tmmGame;
    SMMGame smmGame;
    TwMMGame twmmGame;

    Game currentGame;

    public GamePlayGUI(int numberOfMen, boolean playAgainstComputer) {



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,800);
        URL imageUrl = null;
        ImageIcon imageIcon = null;

        if(numberOfMen==9){
            this.setTitle("Mills - 9 Men's Morris Game - by Pals");
            imageUrl=GamePlayGUI.class.getResource("/nineBoardNoBg.png");
            nmmGame = new NMMGame(playAgainstComputer);
            currentGame=nmmGame;

            for(int i=0;i<25;i++){
                this.add(nmmGame.nmmBoard.roundBtnArray[i]);
            }
        }
        if(numberOfMen==3){
            this.setTitle("Mills - 3 Men's Morris Game - by Pals");
            imageUrl=GamePlayGUI.class.getResource("/threeBoardNoBg.png");
            tmmGame = new TMMGame(playAgainstComputer);
            currentGame=tmmGame;


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
        whitePlayerCountLabel = new JLabel("Player 1 (White) has : "+String.valueOf(currentGame.player1Count)+" men.");
        blackPlayerCountLabel = new JLabel("Player 2 (Black) has : "+String.valueOf(currentGame.player2Count)+" men.");

        Font labelFont = new Font("SansSerif", Font.PLAIN, 12);
        whitePlayerCountLabel.setFont(labelFont);
        blackPlayerCountLabel.setFont(labelFont);

// Set bounds for white player count label
        whitePlayerCountLabel.setBounds(10, 320, 200, 30);

        // Set bounds for black player count label
        blackPlayerCountLabel.setBounds(10, 350, 200, 30);



        this.add(whitePlayerCountLabel);
        this.add(blackPlayerCountLabel);







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



}
