package sprint1.production;

public class Board {
    enum cellValue{
        WHITE,
        BLACK,
        INVALID,
        EMPTY
    }
    boolean[][] edgeExists;
    public Board(int size) {
        if(size==9 || size==12) { //nine mens
            edgeExists = new boolean[24][24];
        }
        if(size==6){
            edgeExists = new boolean[16][16];
        }
        if(size==3){
            edgeExists = new boolean[9][9];
        }
    }
    public void setupNineBoard(){

        edgeExists[0][1]=true;
        edgeExists[1][0]=true;

        edgeExists[0][9]=true;
        edgeExists[9][0]=true;

        edgeExists[1][2]=true;
        edgeExists[2][1]=true;

        edgeExists[1][4]=true;
        edgeExists[4][1]=true;

        edgeExists[2][14]=true;
        edgeExists[14][2]=true;

        edgeExists[3][4]=true;
        edgeExists[4][3]=true;

        edgeExists[3][10]=true;
        edgeExists[10][3]=true;

        edgeExists[4][5]=true;
        edgeExists[5][4]=true;

        edgeExists[4][7]=true;
        edgeExists[7][4]=true;

        edgeExists[5][13]=true;
        edgeExists[13][5]=true;

        edgeExists[6][7]=true;
        edgeExists[7][6]=true;

        edgeExists[6][11]=true;
        edgeExists[11][6]=true;

        edgeExists[7][8]=true;
        edgeExists[8][7]=true;

        edgeExists[8][12]=true;
        edgeExists[12][8]=true;


        edgeExists[9][10]=true;
        edgeExists[10][9]=true;

        edgeExists[9][21]=true;
        edgeExists[21][9]=true;


        edgeExists[10][11]=true;
        edgeExists[11][10]=true;

        edgeExists[11][15]=true;
        edgeExists[15][11]=true;

        edgeExists[10][18]=true;
        edgeExists[18][10]=true;

        edgeExists[12][13]=true;
        edgeExists[13][12]=true;

        edgeExists[12][17]=true;
        edgeExists[17][12]=true;

        edgeExists[13][14]=true;
        edgeExists[14][13]=true;

        edgeExists[13][20]=true;
        edgeExists[20][13]=true;

        edgeExists[14][23]=true;
        edgeExists[23][14]=true;

        edgeExists[15][16]=true;
        edgeExists[16][15]=true;

        edgeExists[16][19]=true;
        edgeExists[19][16]=true;

        edgeExists[16][17]=true;
        edgeExists[17][16]=true;

        edgeExists[18][19]=true;
        edgeExists[19][18]=true;

        edgeExists[19][20]=true;
        edgeExists[20][19]=true;

        edgeExists[19][22]=true;
        edgeExists[22][19]=true;

        edgeExists[21][22]=true;
        edgeExists[22][21]=true;

        edgeExists[22][23]=true;
        edgeExists[23][22]=true;

    }
}
