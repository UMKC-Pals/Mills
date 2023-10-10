package sprint1.production;

public class Board {
    enum Cell{
        WHITE,
        BLACK,
        INVALID,
        EMPTY
    }
    boolean[][] edgeExists;
    public Board(int size) {
        if(size==9 || size==12) { //nine mens

            edgeExists = new boolean[7][7];
        }
        if(size==6){

        }
        if(size==3){

        }
    }
    public void SetupNineBoard(){


    }
}
