package Production;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreeSquares extends JPanel {

   // protected static JButton b2;

    public ThreeSquares() {
        //setLayout(new FlowLayout());
        setPreferredSize(new Dimension(600, 600));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int outerSquareSize = 500;
        int middleSquareSize = 300;
        int innerSquareSize = 100;

        int outerSquareX = centerX - outerSquareSize / 2;
        int outerSquareY = centerY - outerSquareSize / 2;

        int middleSquareX = centerX - middleSquareSize / 2;
        int middleSquareY = centerY - middleSquareSize / 2;

        int innerSquareX = centerX - innerSquareSize / 2;
        int innerSquareY = centerY - innerSquareSize / 2;

        g.drawRect(outerSquareX, outerSquareY, outerSquareSize, outerSquareSize);
        g.drawRect(middleSquareX, middleSquareY, middleSquareSize, middleSquareSize);
        g.drawRect(innerSquareX, innerSquareY, innerSquareSize, innerSquareSize);

        g.drawLine(50, 550, 250, 350);
        g.drawLine(50, 50, 250, 250);
        g.drawLine(350,250, 550, 50);
        g.drawLine(350,350,550,550);
        g.drawLine(300,250,300,50);
        g.drawLine(300, 350, 300, 550);
        g.drawLine(250,300,50,300);
        g.drawLine(350,300, 550,300);

        g.fillRect(297,544,6,6);
        g.fillRect(49,544, 6,6);
        g.fillRect(547,544,6,6);


    /*    b2 = new JButton();
        b2.setBounds(140,440,20,20);
        b2.setVisible(true);

        add(b2);

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b2.setVisible(false);
            }
        });

     */
    }



 /*   public static void main(String[] args) {
        JFrame frame = new JFrame("Three Squares");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ThreeSquares());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    */

}

