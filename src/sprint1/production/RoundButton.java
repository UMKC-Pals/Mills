package sprint1.production;

import javax.swing.*;
import java.awt.*;

enum buttonStates{
    PLAYER1,
    PLAYER2,
    EMPTY,
    INVALID;
}

public class RoundButton extends JButton {





    buttonStates currentState=buttonStates.INVALID;
    public RoundButton(String text) {
        super(text);
        setContentAreaFilled(false);

    }

    @Override
    protected void paintComponent(Graphics g) {

        if(this.currentState==buttonStates.INVALID){
            this.setVisible(false);
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            g2.setColor(Color.lightGray);
        } else {
            g2.setColor(getBackground());
        }

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {

        if(this.currentState==buttonStates.INVALID){
            this.setVisible(false);
        }
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2.dispose();
    }
}
