package sprint2.production;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSetupGUI extends JFrame {
    boolean playAgainstComputer = false;

    public GamePlayGUI getNmmGameGUI() {
        return nmmGameGUI;
    }

    public void setNmmGameGUI(GamePlayGUI nmmGameGUI) {
        this.nmmGameGUI = nmmGameGUI;
    }

    private GamePlayGUI nmmGameGUI;

    public GameSetupGUI() {



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,500);
        this.setLocationRelativeTo(null); // to display the JFrame centered to the screen
        this.setTitle("Mills Game - by Pals");

        JLabel title=new JLabel("Welcome to Mills!");
        JLabel subtitle=new JLabel("Choose 3/6/9/12 Men Morris:");

        JComboBox<String> numberOfMenComboBox = new JComboBox<>();

        ButtonGroup playVersusButtonGroup = new ButtonGroup();
        JRadioButton HvHradioBtn = new JRadioButton("Human vs. Human");
        JRadioButton HvCradioBtn = new JRadioButton("Human vs. Computer");

        playVersusButtonGroup.add(HvHradioBtn);
        playVersusButtonGroup.add(HvCradioBtn);
        HvHradioBtn.setSelected(true);

        ButtonGroup player1ColorButtonGroup = new ButtonGroup();
        JRadioButton player1WhiteRadioBtn = new JRadioButton("Player 1 uses White");
        JRadioButton player1BlackRadioBtn = new JRadioButton("Player 1 uses Black");

        player1ColorButtonGroup.add(player1WhiteRadioBtn);
        player1ColorButtonGroup.add(player1BlackRadioBtn);
        player1WhiteRadioBtn.setSelected(true);

        playAgainstComputer = false;
        
        numberOfMenComboBox.addItem("3 Men's Morris");
        numberOfMenComboBox.addItem("6 Men's Morris");
        numberOfMenComboBox.addItem("9 Men's Morris");
        numberOfMenComboBox.addItem("12 Men's Morris");

        numberOfMenComboBox.setSelectedItem("9 Men's Morris");


        JButton chooseMenMorrisBtn = new JButton("Choose!");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);

        title.setVerticalAlignment(SwingConstants.CENTER);
        subtitle.setVerticalAlignment(SwingConstants.CENTER);


        title.setBounds(10,50,380,60);
        subtitle.setBounds(10,140,380,60);

        numberOfMenComboBox.setBounds(50,240,300,20);

        HvHradioBtn.setBounds(10,300,180,20);
        HvCradioBtn.setBounds(10,320,180,20);

        player1WhiteRadioBtn.setBounds(200,300,180,20);
        player1BlackRadioBtn.setBounds(200,320,180,20);


        chooseMenMorrisBtn.setBounds(150,360,100,30);
        chooseMenMorrisBtn.setSize(100,50);


        chooseMenMorrisBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(HvHradioBtn.isSelected()){
                    playAgainstComputer=false;
                }
                else if(HvCradioBtn.isSelected()){
                    playAgainstComputer=true;
                }

                if(numberOfMenComboBox.getSelectedItem().equals("9 Men's Morris")){
                    nmmGameGUI=new GamePlayGUI(9,playAgainstComputer, player1WhiteRadioBtn.isSelected());
                }
                if(numberOfMenComboBox.getSelectedItem().equals("6 Men's Morris")){
                    GamePlayGUI smmGameGUI=new GamePlayGUI(6,playAgainstComputer, player1WhiteRadioBtn.isSelected());
                }
                if(numberOfMenComboBox.getSelectedItem().equals("12 Men's Morris")){
                    GamePlayGUI twmmGameGUI=new GamePlayGUI(12,playAgainstComputer, player1WhiteRadioBtn.isSelected());
                }
                if(numberOfMenComboBox.getSelectedItem().equals("3 Men's Morris")){
                    GamePlayGUI tmmGameGUI=new GamePlayGUI(3,playAgainstComputer, player1WhiteRadioBtn.isSelected());
                }
            }


        });

        this.add(title);
        this.add(subtitle);

        this.add(numberOfMenComboBox);
        this.add(chooseMenMorrisBtn);

        this.add(HvHradioBtn);
        this.add(HvCradioBtn);

        this.add(player1BlackRadioBtn);
        this.add(player1WhiteRadioBtn);

        this.setLayout(null);

        this.setVisible(true);
        this.setResizable(false);


    }

    public static GameSetupGUI getMyGameSetupGUI() {
        return myGameSetupGUI;
    }

    public static void setMyGameSetupGUI(GameSetupGUI newGameSetupGUI) {

        GameSetupGUI oldGameSetupGUI=GameSetupGUI.myGameSetupGUI;
        GameSetupGUI.myGameSetupGUI = newGameSetupGUI;
        oldGameSetupGUI.dispose();
    }

    static GameSetupGUI myGameSetupGUI;
    public static void main(String [] args){

        myGameSetupGUI=new GameSetupGUI();

    }

}