package sprint1.production;

public class NMMGame extends Game{

    Board nmmBoard;
    public NMMGame(boolean playAgainstComputer) {

        this.player1Count=9;
        this.player2Count=9;


        dirAndFileSetup();
        nmmBoard=new Board(9);

        for(int i=0;i<=23;i++){
            nmmBoard.roundBtnArray[i].currentState=buttonStates.EMPTY;
            nmmBoard.roundBtnArray[i].setVisible(true);
//            nmmBoard.roundBtnArray[i].paint();
//            nmmBoard.roundBtnArray[i].paintBorder();
        }




    }
}
