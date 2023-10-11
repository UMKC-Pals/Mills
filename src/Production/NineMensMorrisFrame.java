package Production;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import static java.awt.Color.*;

public class NineMensMorrisFrame extends JFrame {


    private JPanel centerPanel, leftPanel, rightPanel;
    private JPanel frame;

    private enum validSpots{EMPTY, BLACK, WHITE};
    private Map<JButton, validSpots> buttonsMap;

    private JButton b2, a1, d1, g1, d2, f2, d3, e3, c3, a4, b4, c4,
            e4, f4, g4, a7, b5, c5, d5, e5, d6, f6, d7, g7;

    public void setB2(JButton b2) {
        this.b2 = b2;
    }

    public JButton getB2() {
        return b2;
    }

    private JButton newGameBtn, replayBtn;
    private JRadioButton whiteHumanRbtn, whiteComputerRbtn, blackHumanRbtn, blackComputerRbtn;
    private JCheckBox recordCb;

    private JLabel white_pieces, black_pieces;

    public JLabel getWhite_pieces() {
        return white_pieces;
    }

    public void setWhite_pieces(JLabel white_pieces) {
        this.white_pieces = white_pieces;
    }

    public JLabel getBlack_pieces() {
        return black_pieces;
    }

    public void setBlack_pieces(JLabel black_pieces) {
        this.black_pieces = black_pieces;
    }

    private JComboBox millsVariation;
    private Integer whitePieces, blackPieces;

    public Integer getWhitePieces() {
        return whitePieces;
    }

    public void setWhitePieces(Integer whitePieces) {
        this.whitePieces = whitePieces;
    }

    public Integer getBlackPieces() {
        return blackPieces;
    }

    public void setBlackPieces(Integer blackPieces) {
        this.blackPieces = blackPieces;
    }

    private String currentTurn = "FDFFD7";

    public String getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(String currentTurn) {
        this.currentTurn = currentTurn;
    }

    private JLabel turn;

    public JLabel getTurn() {
        return turn;
    }

    public void setTurn(JLabel turn) {
        this.turn = turn;
    }

    private JLabel imageLabel;

    private Integer blackPiecesOnBoard, whitePiecesOnBoard;



    public NineMensMorrisFrame(){
        setResizable(false);
        setTitle("Nine Men's Morris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        whitePieces = 0;
        blackPieces = 0;

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

        imageLabel = new JLabel(new ImageIcon("src\\Production\\board.png"));//("C:\\CS-5551-1\\board.png"));
        centerPanel.add(imageLabel);

        //=====================GAME BOARD SETUP===================================
        gameBoardSetup();

        //==================LEFT PANEL======================================

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JPanel innerPanel = new JPanel(new FlowLayout());
        innerPanel.setPreferredSize(new Dimension(60, 40));
        innerPanel.setBackground(GRAY);
        leftPanel.add(innerPanel);

        Integer[] numOfMen = {9,6,12};
        millsVariation = new JComboBox<Integer>(numOfMen);
        innerPanel.add(millsVariation);

        JLabel gameName = new JLabel("Men's Morris");
        innerPanel.add(gameName);

        newGameBtn = new JButton("New Game");
        newGameBtn.setSize(15, 30);

        leftPanel.add(newGameBtn);
        leftPanel.add(Box.createVerticalStrut(150));

        //JLabel whiteLbl = new JLabel("White");
        //leftPanel.add(whiteLbl);
        //leftPanel.add(Box.createVerticalStrut(30));

        JButton chooseWhite = new JButton("White");
        chooseWhite.setSize(15,30);
        chooseWhite.setBackground(WHITE);
        leftPanel.add(chooseWhite);
        leftPanel.add(Box.createVerticalStrut(30));



        whiteHumanRbtn = new JRadioButton("Human");
        leftPanel.add(whiteHumanRbtn);
        leftPanel.add(Box.createVerticalStrut(20));

        whiteComputerRbtn = new JRadioButton("Computer");
        leftPanel.add(whiteComputerRbtn);
        leftPanel.add(Box.createVerticalStrut(30));

        JLabel whiteCount = new JLabel("Pieces:");// + Integer.toString(whitePieces));
        leftPanel.add(whiteCount);
        leftPanel.add(Box.createVerticalStrut(20));

        white_pieces = new JLabel(Integer.toString(getWhitePieces()));
        leftPanel.add(white_pieces);
        leftPanel.add(Box.createVerticalStrut(150));

        recordCb = new JCheckBox("Record");
        leftPanel.add(recordCb);
        leftPanel.add(Box.createVerticalStrut(50));

        replayBtn = new JButton("Replay");
        replayBtn.setSize(15,30);
        leftPanel.add(replayBtn);
        leftPanel.add(Box.createVerticalStrut(30));

        //==================END LEFT PANEL=======================================

        //==================RIGHT PANEL==========================================

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(Box.createVerticalStrut(300));

        //JLabel blackLbl = new JLabel("Black");
        //rightPanel.add(blackLbl);
        //rightPanel.add(Box.createVerticalStrut(30));

        JButton chooseBlack = new JButton("Black");
        chooseBlack.setSize(15,30);
        chooseBlack.setBackground(BLACK);
        rightPanel.add(chooseBlack);
        rightPanel.add(Box.createVerticalStrut(30));



        blackHumanRbtn = new JRadioButton("Human");
        //blackHumanRbtn.setBackground(GRAY);
        blackHumanRbtn.setFont(new Font("Arial", Font.BOLD, 14));
        rightPanel.add(blackHumanRbtn);
        rightPanel.add(Box.createVerticalStrut(20));

        blackComputerRbtn = new JRadioButton("Computer");
        rightPanel.add(blackComputerRbtn);
        rightPanel.add(Box.createVerticalStrut(30));

        JLabel blackCount = new JLabel("Pieces:");//  Integer.toString(blackPieces));
        rightPanel.add(blackCount);
        rightPanel.add(Box.createVerticalStrut(20));

        black_pieces = new JLabel(Integer.toString(getBlackPieces()));
        rightPanel.add(black_pieces);
        rightPanel.add(Box.createVerticalStrut(90));

        JPanel intervalPanel = new JPanel(new FlowLayout());
        rightPanel.add(intervalPanel);
        intervalPanel.setPreferredSize(new Dimension(60,40));
        intervalPanel.setBackground(GRAY);

        JLabel intervalLbl = new JLabel("Interval:");
        intervalPanel.add(intervalLbl);

        Integer[] intervalSec = {1,2,3};
        JComboBox<Integer> comboSeconds = new JComboBox<Integer>(intervalSec);
        intervalPanel.add(comboSeconds);

        JLabel seconds = new JLabel("s");
        intervalPanel.add(seconds);
        rightPanel.add(Box.createVerticalStrut(20));

        JLabel turnLbl = new JLabel("Current turn:");
        rightPanel.add(turnLbl);
        rightPanel.add(Box.createVerticalStrut(15));

        turn = new JLabel(currentTurn);
        rightPanel.add(turn);
        rightPanel.add(Box.createVerticalStrut(30));

        //=================END RIGHT PANEL=====================================

        chooseWhite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentTurn("white");
                System.out.println(currentTurn);
                getTurn().setText(getCurrentTurn());
            }
        });

        chooseBlack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentTurn("black");
                getTurn().setText(getCurrentTurn());
            }
        });

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

    public void gameBoardSetup(){
        System.out.println("Setup");

        buttonsMap = new HashMap<JButton, validSpots>();

        b2 = new RoundButton("");
        b2.setBounds(120,470,10,10);
        buttonsMap.put(b2, validSpots.EMPTY);

        a1 = new RoundButton("");// new JButton();
        a1.setBounds(5,585,10,10);
        buttonsMap.put(a1, validSpots.EMPTY);

        d1 = new RoundButton("");//new JButton();
        d1.setBounds(295,585,10,10);
        buttonsMap.put(d1, validSpots.EMPTY);

        g1 = new RoundButton("");//new JButton();
        g1.setBounds(585, 585,10,10);
        buttonsMap.put(g1, validSpots.EMPTY);

        d2 = new RoundButton("");//new JButton();
        d2.setBounds(295,470,10,10);
        buttonsMap.put(d2, validSpots.EMPTY);

        f2 = new RoundButton("");//new JButton();
        f2.setBounds(470,470,10,10);
        buttonsMap.put(f2, validSpots.EMPTY);

        d3 = new RoundButton("");//new JButton();
        d3.setBounds(295, 355, 10, 10);
        buttonsMap.put(d3, validSpots.EMPTY);

        e3 = new RoundButton("");//new JButton();
        e3.setBounds(355,355,10,10);
        buttonsMap.put(e3, validSpots.EMPTY);

        c3 = new RoundButton("");//new JButton();
        c3.setBounds(235, 355,10,10);
        buttonsMap.put(c3, validSpots.EMPTY);

        a4 = new RoundButton("");//new JButton();
        a4.setBounds(5, 295, 10, 10);
        buttonsMap.put(a4, validSpots.EMPTY);

        b4 = new RoundButton("");//new JButton();
        b4.setBounds(120,295,10,10);
        buttonsMap.put(b4, validSpots.EMPTY);

        c4 = new RoundButton("");//new JButton();
        c4.setBounds(235,295,10,10);
        buttonsMap.put(c4, validSpots.EMPTY);

        e4 = new RoundButton("");//new JButton();
        e4.setBounds(355, 295,10,10);
        buttonsMap.put(e4, validSpots.EMPTY);

        f4 = new RoundButton("");//new JButton();
        f4.setBounds(470,295,10,10);
        buttonsMap.put(f4, validSpots.EMPTY);

        g4 = new RoundButton("");//new JButton();
        g4.setBounds(585,295,10,10);
        buttonsMap.put(g4, validSpots.EMPTY);

        a7 = new RoundButton("");//new JButton();
        a7.setBounds(5,5, 10,10);
        buttonsMap.put(a7, validSpots.EMPTY);

        b5 = new RoundButton("");//new JButton();
        b5.setBounds(120,120,10,10);
        buttonsMap.put(b5, validSpots.EMPTY);

        c5 = new RoundButton("");//new JButton();
        c5.setBounds(235,235,10,10);
        buttonsMap.put(c5, validSpots.EMPTY);

        d5 = new RoundButton("");//new JButton();
        d5.setBounds(295,235,10,10);
        buttonsMap.put(d5, validSpots.EMPTY);

        e5 = new RoundButton("");//new JButton();
        e5.setBounds(355,235,10,10);
        buttonsMap.put(e5, validSpots.EMPTY);

        d6 = new RoundButton("");//new JButton();
        d6.setBounds(295,120,10,10);
        buttonsMap.put(d6, validSpots.EMPTY);

        f6 = new RoundButton("");//new JButton();
        f6.setBounds(470,120,10,10);
        buttonsMap.put(f6, validSpots.EMPTY);

        d7 = new RoundButton("");//new JButton();
        d7.setBounds(295,5,10,10);
        buttonsMap.put(d7, validSpots.EMPTY);

        g7 = new RoundButton("");//new JButton();
        g7.setBounds(585,5,10,10);
        buttonsMap.put(g7, validSpots.EMPTY);

        for (Map.Entry<JButton, validSpots> entry : buttonsMap.entrySet()) {
            entry.getKey().setBackground(GRAY);
        }



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
                    if (blackPieces < 9 || whitePieces < 9) {
                        if (key.equals(g7)) {
                            key.setBounds(580, 0, 20, 20);
                        }
                        if (key.equals(d7)) {
                            key.setBounds(290, 0, 20, 20);
                        }
                        if (key.equals(f6)) {
                            key.setBounds(465, 115, 20, 20);
                        }
                        if (key.equals(d6)) {
                            key.setBounds(290, 115, 20, 20);
                        }
                        if (key.equals(e5)) {
                            key.setBounds(350, 230, 20, 20);
                        }
                        if (key.equals(d5)) {
                            key.setBounds(290, 230, 20, 20);
                        }
                        if (key.equals(c5)) {
                            key.setBounds(230, 230, 20, 20);
                        }
                        if (key.equals(b5)) {
                            key.setBounds(115, 115, 20, 20);
                        }
                        if (key.equals(a7)) {
                            key.setBounds(0, 0, 20, 20);
                        }
                        if (key.equals(g4)) {
                            key.setBounds(580, 290, 20, 20);
                        }
                        if (key.equals(f4)) {
                            key.setBounds(465, 290, 20, 20);
                        }
                        if (key.equals(e4)) {
                            key.setBounds(350, 290, 20, 20);
                        }
                        if (key.equals(c4)) {
                            key.setBounds(230, 290, 20, 20);
                        }
                        if (key.equals(b4)) {
                            key.setBounds(115, 290, 20, 20);
                        }
                        if (key.equals(a4)) {
                            key.setBounds(0, 290, 20, 20);
                        }
                        if (key.equals(c3)) {
                            key.setBounds(230, 350, 20, 20);
                        }
                        if (key.equals(e3)) {
                            key.setBounds(350, 350, 20, 20);
                        }
                        if (key.equals(d3)) {
                            key.setBounds(290, 350, 20, 20);
                        }
                        if (key.equals(f2)) {
                            key.setBounds(465, 465, 20, 20);
                        }
                        if (key.equals(d2)) {
                            key.setBounds(290, 465, 20, 20);
                        }
                        if (key.equals(g1)) {
                            key.setBounds(580, 580, 20, 20);
                        }
                        if (key.equals(d1)) {
                            key.setBounds(290, 580, 20, 20);
                        }
                        if (key.equals(a1)) {
                            key.setBounds(0, 580, 20, 20);
                        }
                        if (key.equals(b2)) {
                            key.setBounds(115, 465, 20, 20);
                        }
                    }
                    //while (blackPieces == 9 && whitePieces == 9) {
                        String current_turn = getCurrentTurn();
                        if (Objects.equals(current_turn, "black") && value.equals(validSpots.EMPTY)) {
                            buttonsMap.replace(key, validSpots.EMPTY, validSpots.BLACK);
                            Integer numBlackPieces = getBlackPieces();

                            if (key.getBackground() == GRAY && numBlackPieces < 9) {
                                key.setBackground(BLACK);
                                setCurrentTurn("white");
                                getTurn().setText(getCurrentTurn());
                                setBlackPieces(numBlackPieces + 1);
                                getBlack_pieces().setText(Integer.toString(getBlackPieces()));
                            }
                        } else if (Objects.equals(current_turn, "white") && value.equals(validSpots.EMPTY)) {
                            Color color = key.getBackground();

                            buttonsMap.replace(key, validSpots.EMPTY, validSpots.WHITE);
                            Integer numWhitePieces = getWhitePieces();

                            if (color == GRAY && numWhitePieces < 9) {
                                key.setBackground(WHITE);
                                setCurrentTurn("black");
                                getTurn().setText(getCurrentTurn());
                                setWhitePieces(numWhitePieces + 1);
                                System.out.println(getWhitePieces());
                                getWhite_pieces().setText(Integer.toString(getWhitePieces()));
                            }
                        }
                   // }
                    System.out.println(buttonsMap.size());
                    for (Map.Entry<JButton, validSpots> entry : buttonsMap.entrySet()) {
                        System.out.println(entry.getKey().toString() + ": " + entry.getValue().toString());
                    }


                }
            });
        }//end while
    }



    public static void main(String[] args){
        NineMensMorrisFrame nineMensMorrisFrame = new NineMensMorrisFrame();
    }
}