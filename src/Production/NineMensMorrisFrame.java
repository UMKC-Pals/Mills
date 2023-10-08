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

    public void setB2(JButton b2) {
        this.b2 = b2;
    }

    public JButton getB2() {
        return b2;
    }

    JButton newGameBtn, replayBtn;
    JRadioButton whiteHumanRbtn, whiteComputerRbtn, blackHumanRbtn, blackComputerRbtn;
    JCheckBox recordCb;

    JLabel white_pieces, black_pieces;

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

    JComboBox millsVariation;
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

    JLabel turn;

    public JLabel getTurn() {
        return turn;
    }

    public void setTurn(JLabel turn) {
        this.turn = turn;
    }

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

        JLabel imageLabel = new JLabel(new ImageIcon("C:\\CS-5551-1\\board.png"));
        centerPanel.add(imageLabel);


        b2 = new RoundButton("");
        b2.setBounds(120,470,10,10);
        b2.setVisible(true);

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b2.setBounds(115,465,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    b2.setBackground(BLACK);
                    Integer numBlackPieces = getBlackPieces();
                    if (numBlackPieces < 9){
                        System.out.println("black < 9");
                        setBlackPieces(numBlackPieces + 1);
                        getBlack_pieces().setText(Integer.toString(getBlackPieces()));
                    }
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    b2.setBackground(WHITE);
                    Integer numWhitePieces = getWhitePieces();
                    if(numWhitePieces < 9){
                        System.out.println("white < 9: ");
                        setWhitePieces(numWhitePieces + 1);
                        System.out.println(getWhitePieces());
                        getWhite_pieces().setText(Integer.toString(getWhitePieces()));
                    }
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }



            }
        });

        a1 = new RoundButton("");// new JButton();
        a1.setBounds(5,585,10,10);

        a1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a1.setBounds(0,580,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    a1.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    a1.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        d1 = new RoundButton("");//new JButton();
        d1.setBounds(295,585,10,10);

        d1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d1.setBounds(290,580,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    d1.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    d1.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        g1 = new RoundButton("");//new JButton();
        g1.setBounds(585, 585,10,10);

        g1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g1.setBounds(580,580,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    g1.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    g1.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        d2 = new RoundButton("");//new JButton();
        d2.setBounds(295,470,10,10);

        d2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d2.setBounds(290,465,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    d2.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    d2.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        f2 = new RoundButton("");//new JButton();
        f2.setBounds(470,470,10,10);

        f2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f2.setBounds(465,465,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    f2.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    f2.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        d3 = new RoundButton("");//new JButton();
        d3.setBounds(295, 355, 10, 10);

        d3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d3.setBounds(290,350,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    d3.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    d3.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        e3 = new RoundButton("");//new JButton();
        e3.setBounds(355,355,10,10);

        e3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                e3.setBounds(350,350,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    e3.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    e3.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        c3 = new RoundButton("");//new JButton();
        c3.setBounds(235, 355,10,10);

        c3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c3.setBounds(230,350,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    c3.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    c3.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        a4 = new RoundButton("");//new JButton();
        a4.setBounds(5, 295, 10, 10);

        a4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a4.setBounds(0,290,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    a4.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    a4.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        b4 = new RoundButton("");//new JButton();
        b4.setBounds(120,295,10,10);

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b4.setBounds(115,290,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    b4.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    b4.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        c4 = new RoundButton("");//new JButton();
        c4.setBounds(235,295,10,10);

        c4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c4.setBounds(230,290,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    c4.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    c4.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        e4 = new RoundButton("");//new JButton();
        e4.setBounds(355, 295,10,10);

        e4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                e4.setBounds(350,290,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    e4.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    e4.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        f4 = new RoundButton("");//new JButton();
        f4.setBounds(470,295,10,10);

        f4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f4.setBounds(465,290,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    f4.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    f4.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        g4 = new RoundButton("");//new JButton();
        g4.setBounds(585,295,10,10);

        g4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g4.setBounds(580,290,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    g4.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    g4.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        a7 = new RoundButton("");//new JButton();
        a7.setBounds(5,5, 10,10);

        a7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a7.setBounds(0,0,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    a7.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    a7.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        b5 = new RoundButton("");//new JButton();
        b5.setBounds(120,120,10,10);

        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b5.setBounds(115,115,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    b5.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    b5.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        c5 = new RoundButton("");//new JButton();
        c5.setBounds(235,235,10,10);

        c5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c5.setBounds(230,230,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    c5.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    c5.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        d5 = new RoundButton("");//new JButton();
        d5.setBounds(295,235,10,10);

        d5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d5.setBounds(290,230,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    d5.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    d5.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        e5 = new RoundButton("");//new JButton();
        e5.setBounds(355,235,10,10);

        e5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                e5.setBounds(350,230,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    e5.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    e5.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        d6 = new RoundButton("");//new JButton();
        d6.setBounds(295,120,10,10);

        d6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d6.setBounds(290,115,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    d6.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    d6.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        f6 = new RoundButton("");//new JButton();
        f6.setBounds(470,120,10,10);

        f6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f6.setBounds(465,115,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    f6.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    f6.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        d7 = new RoundButton("");//new JButton();
        d7.setBounds(295,5,10,10);

        d7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d7.setBounds(290,0,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    d7.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    d7.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

        g7 = new RoundButton("");//new JButton();
        g7.setBounds(585,5,10,10);

        g7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g7.setBounds(580,0,20,20);
                String current_turn = getCurrentTurn();
                if (current_turn == "black"){
                    g7.setBackground(BLACK);
                    setCurrentTurn("white");
                    getTurn().setText(getCurrentTurn());
                }
                if (current_turn == "white"){
                    g7.setBackground(WHITE);
                    setCurrentTurn("black");
                    getTurn().setText(getCurrentTurn());
                }
            }
        });

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
/*
            key.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    key.setBackground(BLUE);
                }
            });*/
        }//end while


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




    public static void main(String[] args){
        NineMensMorrisFrame nineMensMorrisFrame = new NineMensMorrisFrame();
    }
}



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
