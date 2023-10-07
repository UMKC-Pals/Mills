package Production;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static java.awt.Color.*;

public class NineMensMorrisFrame extends JFrame {
    JPanel centerPanel, leftPanel, rightPanel;
    JPanel frame;

    private enum validSpots{EMPTY, BLACK, WHITE};
    Map<JButton, validSpots> buttonsMap;

    JButton b2, a1, d1, g1, d2, f2, d3, e3, c3, a4, b4, c4,
            e4, f4, g4, a7, b5, c5, d5, e5, d6, f6, d7, g7;

    JButton newGameBtn, replayBtn;
    JRadioButton whiteHumanRbtn, whiteComputerRbtn, blackHumanRbtn, blackComputerRbtn;
    JCheckBox recordCb;


    public NineMensMorrisFrame(){
        setTitle("Nine Men's Morris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame = new JPanel();
        frame.setLayout(new BoxLayout(frame, BoxLayout.LINE_AXIS));
        add(frame);
        setSize(new Dimension(1200, 800));

        leftPanel = new JPanel();

        centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(500, 800));
        rightPanel = new JPanel();

        leftPanel.setBackground(GRAY);
        rightPanel.setBackground(GRAY);

        JLabel imageLabel = new JLabel(new ImageIcon("C:\\CS-5551-1\\board.png"));
        centerPanel.add(imageLabel);


        b2 = new JButton();
        b2.setBounds(120,470,10,10);
        b2.setVisible(true);


        a1 = new JButton();
        a1.setBounds(5,585,10,10);
        setVisible(true);

        d1 = new JButton();
        d1.setBounds(295,585,10,10);

        g1 = new JButton();
        g1.setBounds(585, 585,10,10);

        d2 = new JButton();
        d2.setBounds(295,470,10,10);

        f2 = new JButton();
        f2.setBounds(470,470,10,10);

        d3 = new JButton();
        d3.setBounds(295, 355, 10, 10);

        e3 = new JButton();
        e3.setBounds(355,355,10,10);

        c3 = new JButton();
        c3.setBounds(235, 355,10,10);

        a4 = new JButton();
        a4.setBounds(5, 295, 10, 10);

        b4 = new JButton();
        b4.setBounds(120,295,10,10);

        c4 = new JButton();
        c4.setBounds(235,295,10,10);

        e4 = new JButton();
        e4.setBounds(355, 295,10,10);

        f4 = new JButton();
        f4.setBounds(470,295,10,10);

        g4 = new JButton();
        g4.setBounds(585,295,10,10);

        a7 = new JButton();
        a7.setBounds(5,5, 10,10);

        b5 = new JButton();
        b5.setBounds(120,120,10,10);

        c5 = new JButton();
        c5.setBounds(235,235,10,10);

        d5 = new JButton();
        d5.setBounds(295,235,10,10);

        e5 = new JButton();
        e5.setBounds(355,235,10,10);

        d6 = new JButton();
        d6.setBounds(295,120,10,10);

        f6 = new JButton();
        f6.setBounds(470,120,10,10);

        d7 = new JButton();
        d7.setBounds(295,5,10,10);

        g7 = new JButton();
        g7.setBounds(585,5,10,10);

        buttonsMap = new HashMap<JButton, validSpots>();

        buttonsMap.put(a1, validSpots.EMPTY);
        buttonsMap.put(a4, validSpots.EMPTY);
        buttonsMap.put(a7, validSpots.EMPTY);
        buttonsMap.put(b2, validSpots.EMPTY);
        buttonsMap.put(b4, validSpots.EMPTY);
        buttonsMap.put(b5, validSpots.EMPTY);
        buttonsMap.put(c3, validSpots.EMPTY);
        buttonsMap.put(c4, validSpots.EMPTY);
        buttonsMap.put(c5, validSpots.EMPTY);
        buttonsMap.put(e3, validSpots.EMPTY);
        buttonsMap.put(e4, validSpots.EMPTY);
        buttonsMap.put(e5, validSpots.EMPTY);
        buttonsMap.put(f2, validSpots.EMPTY);
        buttonsMap.put(f4, validSpots.EMPTY);
        buttonsMap.put(f6, validSpots.EMPTY);
        buttonsMap.put(g1, validSpots.EMPTY);
        buttonsMap.put(g4, validSpots.EMPTY);
        buttonsMap.put(g7, validSpots.EMPTY);
        buttonsMap.put(d5, validSpots.EMPTY);
        buttonsMap.put(d6, validSpots.EMPTY);
        buttonsMap.put(d7, validSpots.EMPTY);
        buttonsMap.put(d3, validSpots.EMPTY);
        buttonsMap.put(d2, validSpots.EMPTY);
        buttonsMap.put(d1, validSpots.EMPTY);

        Set<Map.Entry<JButton, validSpots>> entrySet = buttonsMap.entrySet();
        Iterator<Map.Entry<JButton, validSpots>> iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Map.Entry<JButton,validSpots> entry = iterator.next();
            JButton key = entry.getKey();
            validSpots value = entry.getValue();

            imageLabel.add(key);

            key.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    key.setBackground(BLUE);
                }
            });
        }//end while
/*
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b2.setBackground(BLUE);
            }
        });

        imageLabel.add(b2);
        imageLabel.add(a1);
        imageLabel.add(d1);
        imageLabel.add(g1);
        imageLabel.add(d2);
        imageLabel.add(f2);
        imageLabel.add(d3);
        imageLabel.add(e3);
        imageLabel.add(c3);
        imageLabel.add(a4);
        imageLabel.add(b4);
        imageLabel.add(c4);
        imageLabel.add(e4);
        imageLabel.add(f4);
        imageLabel.add(g4);
        imageLabel.add(a7);
        imageLabel.add(b5);
        imageLabel.add(c5);
        imageLabel.add(d5);
        imageLabel.add(e5);
        imageLabel.add(d6);
        imageLabel.add(f6);
        imageLabel.add(d7);
        imageLabel.add(g7);

 */

        //centerPanel.add(board);

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        newGameBtn = new JButton("New Game");
        newGameBtn.setSize(15, 30);

        leftPanel.add(newGameBtn);
        leftPanel.add(Box.createVerticalStrut(150));

        JLabel whiteLbl = new JLabel("White");
        leftPanel.add(whiteLbl);
        leftPanel.add(Box.createVerticalStrut(30));

        whiteHumanRbtn = new JRadioButton("Human");
        leftPanel.add(whiteHumanRbtn);
        leftPanel.add(Box.createVerticalStrut(20));

        whiteComputerRbtn = new JRadioButton("Computer");
        leftPanel.add(whiteComputerRbtn);
        leftPanel.add(Box.createVerticalStrut(200));

        recordCb = new JCheckBox("Record");
        leftPanel.add(recordCb);
        leftPanel.add(Box.createVerticalStrut(50));

        replayBtn = new JButton("Replay");
        replayBtn.setSize(15,30);
        leftPanel.add(replayBtn);
        leftPanel.add(Box.createVerticalStrut(30));



        frame.add(leftPanel);
        frame.add(centerPanel);
        frame.add(rightPanel);

        setVisible(true);
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel(JPanel centerPanel) {
        this.centerPanel = centerPanel;
    }




    public static void main(String[] args){
        NineMensMorrisFrame nineMensMorrisFrame = new NineMensMorrisFrame();
    }
}




