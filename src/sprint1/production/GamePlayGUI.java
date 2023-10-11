package sprint1.production;

import javax.swing.*;
import java.net.URL;

public class GamePlayGUI extends JFrame{

    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel centerPanel;
    private JLabel imageLabel;

    public GamePlayGUI(int numberOfMen, boolean playAgainstComputer) {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,800);
        URL imageUrl = null;
        ImageIcon imageIcon = null;
        if(numberOfMen==9){
            this.setTitle("Mills - 9 Men's Morris Game - by Pals");
            imageUrl=GamePlayGUI.class.getResource("/nineBoardNoBg.png");
            NMMGame nmmGame = new NMMGame(playAgainstComputer);
        }
        if(numberOfMen==3){
            this.setTitle("Mills - 3 Men's Morris Game - by Pals");
            imageUrl=GamePlayGUI.class.getResource("/threeBoardNoBg.png");
            TMMGame tmmGame = new TMMGame(playAgainstComputer);

        }
        if(numberOfMen==6){
            this.setTitle("Mills - 6 Men's Morris Game - by Pals");
            imageUrl=GamePlayGUI.class.getResource("/sixBoardNoBg.png");
            SMMGame smmGame = new SMMGame(playAgainstComputer);

        }
        if(numberOfMen==12){
            this.setTitle("Mills - 12 Men's Morris Game - by Pals");
            imageUrl=GamePlayGUI.class.getResource("/twelveBoardNoBg.png");
            TwMMGame twmmGame = new TwMMGame(playAgainstComputer);

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
