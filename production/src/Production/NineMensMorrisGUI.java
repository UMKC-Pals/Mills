package Production;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NineMensMorrisGUI extends JFrame {
    private static Integer[] numOfMen = {9,6,12};
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel text_field = new JLabel();
    JButton newGameButton = new JButton();

    JButton b2 = new JButton();

    private static final int BOARD_SIZE = 7;
    private static final int CELL_SIZE = 80;
    private static final Color BOARD_COLOR = new Color(214, 181, 134);
    private static final Color CELL_COLOR = new Color(243, 229, 171);
    private int[][] board;

    private static JPanel inBoardPanel = new JPanel(new BorderLayout());

    public JButton getNewGameButton() {
        return newGameButton;
    }

    public void setNewGameButton(JButton newGameButton) {
        this.newGameButton = newGameButton;
    }

    JComboBox<Integer> numberOfMorris;
    JPanel topPanel = new JPanel();
    JPanel westPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    Integer[] intervals = {1,2,3};
    JPanel eastPanel = new JPanel();

    JPanel boardPanel;

    NineMensMorrisGUI(){
        this.setTitle("Nine Men's Morris");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(795,715);

        this.setBackground(Color.GRAY);
        this.setLayout(new BorderLayout());

        String name;





        //newGameButton.setBackground(Color.BLUE);
        newGameButton.setText("New Game");
        newGameButton.setSize(30,15);
        newGameButton.setVisible(true);
        newGameButton.setEnabled(true);

        topPanel.setLayout(new BorderLayout());


        topPanel.add(newGameButton, BorderLayout.WEST);

        numberOfMorris = new JComboBox<Integer>(numOfMen);
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Men's Morris");

        //topPanel.add(Box.createRigidArea(new Dimension(300,15)));
        comboPanel.add(numberOfMorris);
        comboPanel.add(label);
        topPanel.add(comboPanel, BorderLayout.CENTER);


        JLabel whiteLabel = new JLabel("White");


        westPanel.setLayout(new GridLayout(5,1));
        westPanel.add(whiteLabel);

        JPanel whiteHumanCompPanel = new JPanel(new BorderLayout());
        westPanel.add(whiteHumanCompPanel);

        JRadioButton whiteHumanBtn = new JRadioButton("Human");
        JRadioButton whiteComputerBtn = new JRadioButton("Computer");

        whiteHumanCompPanel.add(whiteHumanBtn, BorderLayout.NORTH);
        whiteHumanCompPanel.add(whiteComputerBtn, BorderLayout.CENTER);

        JPanel whitePiecesPanel = new JPanel(new FlowLayout());
        JLabel piecesLbl = new JLabel("Pieces: ");
        JLabel piecesNum = new JLabel();
        whitePiecesPanel.add(piecesLbl);
        whitePiecesPanel.add(piecesNum);
        westPanel.add(whitePiecesPanel);

        JCheckBox recordCB = new JCheckBox("Record");
        westPanel.add(recordCB);

        JButton replayBth = new JButton("Replay");
        JPanel replayPanel = new JPanel(new FlowLayout());
        replayPanel.add(replayBth);
        replayBth.setSize(30,15);
        westPanel.add(replayPanel);

        bottomPanel.setLayout(new FlowLayout());
        JPanel center = new JPanel(new FlowLayout());
        JPanel eastBottom = new JPanel(new FlowLayout());

        JLabel turnLbl = new JLabel("Current turn: ");
        JLabel turnValue = new JLabel("value");
        center.add(turnLbl);
        center.add(turnValue);

        JLabel intervalLabel = new JLabel("Interval: ");
        JComboBox<Integer> intervalCB = new JComboBox<Integer>(intervals);
        JLabel second = new JLabel("s");
        eastBottom.add(intervalLabel);
        eastBottom.add(intervalCB);
        eastBottom.add(second);


        bottomPanel.add(center);
        bottomPanel.add(eastBottom);

        eastPanel.setLayout(new GridLayout(5,1));

        JLabel blackLabel = new JLabel("Black");
        eastPanel.add(blackLabel);

        JRadioButton blackHumanCB = new JRadioButton("Human");
        eastPanel.add(blackHumanCB);

        JRadioButton blackCompCB = new JRadioButton("Computer");
        eastPanel.add(blackCompCB);

        JPanel blPiecesPanel = new JPanel(new FlowLayout());
        JLabel blPiecesLb = new JLabel("Pieces: ");
        JLabel blPiecesValue = new JLabel("value");

        blPiecesPanel.add(blPiecesLb);
        blPiecesPanel.add(blPiecesValue);

        eastPanel.add(blPiecesPanel);
/*
        boardPanel = new JPanel(new GridLayout(7,7));
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 7; j++){
                JButton button = new JButton();
                button.setText(Integer.toString(i) + "," + Integer.toString(j));
                button.setPreferredSize(new Dimension(10, 30));
                button.setFont(new Font("Arial", Font.BOLD, 12));
                button.setFocusPainted(false);
                button.setContentAreaFilled(true);
                button.setOpaque(true);

                button.setBorder(new RoundedBorder(10));
                //button.setSize(5,5);
                //button.setVisible(true);

                boardPanel.add(button);
            }
        }
*/
        boardPanel = new JPanel();
        //boardPanel.setPreferredSize(new Dimension(BOARD_SIZE * CELL_SIZE, BOARD_SIZE * CELL_SIZE));
        boardPanel.setBackground(BOARD_COLOR);
        ThreeSquares grid = new ThreeSquares();
        //board = new int[BOARD_SIZE][BOARD_SIZE];

     //   b2.setBounds(50,572,20,20);
     //   grid.add(b2);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = e.getY() / CELL_SIZE;
                int col = e.getX() / CELL_SIZE;
                System.out.println("Clicked: " + row + ", " + col);
            }
        });




        this.add(topPanel,BorderLayout.NORTH);
        this.add(westPanel, BorderLayout.WEST);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(eastPanel, BorderLayout.EAST);

        this.add(grid, BorderLayout.CENTER);

        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent actionEvent){

    }

/*
    @Override
    public void paint(Graphics g) {
        boardPanel.add(inBoardPanel, BorderLayout.CENTER);
        inBoardPanel.paint(g);

        // Draw the board
        g.setColor(CELL_COLOR);
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                g.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }
*/

}
