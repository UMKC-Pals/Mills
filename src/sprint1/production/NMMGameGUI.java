package sprint1.production;

import javax.swing.*;

class NMMGameGUIFrame extends JFrame{
    public NMMGameGUIFrame() {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(1200,800);
        this.setVisible(true);
    }

}

public class NMMGameGUI {
    public NMMGameGUI() {

        Board nineMenMorrisBoard=new Board(9);
        nineMenMorrisBoard.setupNineBoard();
        NMMGame nmmGame = new NMMGame();

        NMMGameGUIFrame nmmGameGUIFrame = new NMMGameGUIFrame();
    }




//        nmmGameGUIFrame.setDefaultCloseOperation(NMMGameGUIFrame.EXIT_ON_CLOSE);
//        nmmGameGUIFrame.setSize(400,500);
//        nmmGameGUIFrame.setVisible(true);



}
