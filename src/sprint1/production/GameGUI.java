package sprint1.production;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {
    public static void main(String[] args) {
        Game game=new Game();

        game.dirAndFileSetup();

        GameGUIFrame frame = new GameGUIFrame();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(400,500);
        frame.setVisible(true);




    }

}
class GameGUIFrame extends JFrame {

    public GameGUIFrame() {

        JLabel title=new JLabel("Welcome to Mills!");
        JLabel subtitle=new JLabel("Choose 3/6/9/12 Men Morris:");

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("3 Men's Morris");
        comboBox.addItem("6 Men's Morris");
        comboBox.addItem("9 Men's Morris");
        comboBox.addItem("12 Men's Morris");

        JButton chooseMenMorrisBtn = new JButton("Choose!");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);

        title.setVerticalAlignment(SwingConstants.CENTER);
        subtitle.setVerticalAlignment(SwingConstants.CENTER);


        title.setBounds(10,50,380,60);
        subtitle.setBounds(10,140,380,60);

        comboBox.setBounds(50,240,300,20);

        chooseMenMorrisBtn.setBounds(150,300,100,30);
        chooseMenMorrisBtn.setSize(100,50);

        chooseMenMorrisBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox.getSelectedItem().equals("9 Men's Morris")){
                    NMMGameGUI nmmGameGUI=new NMMGameGUI();
                }
            }
        });

        this.add(title);
        this.add(subtitle);
        this.add(comboBox);
        this.add(chooseMenMorrisBtn);

        this.setLayout(null);

    }

}