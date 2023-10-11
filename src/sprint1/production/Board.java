package sprint1.production;

import java.util.HashSet;
import java.util.TreeSet;

public class Board {
    enum cellValue{
        WHITE,
        BLACK,
        INVALID,
        EMPTY
    }

//    in the board, there are 25 positions maximum,
//    these 25 are represented as 0 to 24 in an array.
//    with 0 to 23 linearly arranged from top-left to bottom right row wise.
//    24 is the center vertex used in 3 mens morris.
//

    boolean[][] edgeExists;
    public Board(int size) {
            edgeExists = new boolean[25][25];
    }
    TreeSet<Integer> line=new TreeSet<Integer>();

    HashSet<TreeSet<Integer>> innerSquareMills = new HashSet<TreeSet<Integer>>();
    HashSet<TreeSet<Integer>> middleSquareMills = new HashSet<TreeSet<Integer>>();
    HashSet<TreeSet<Integer>> outerSquareMills = new HashSet<TreeSet<Integer>>();

    HashSet<TreeSet<Integer>> nmmMills = new HashSet<TreeSet<Integer>>();
    HashSet<TreeSet<Integer>> smmMills = new HashSet<TreeSet<Integer>>();
    HashSet<TreeSet<Integer>> twmmMills = new HashSet<TreeSet<Integer>>();
    HashSet<TreeSet<Integer>> tmmMills = new HashSet<TreeSet<Integer>>();

    public void setUpInnerSquare() {
        line.clear();

        line.add(6);
        line.add(7);
        line.add(8);
        innerSquareMills.add(line);
        line.clear();

        line.add(6);
        line.add(11);
        line.add(15);
        innerSquareMills.add(line);
        line.clear();

        line.add(8);
        line.add(12);
        line.add(17);
        innerSquareMills.add(line);
        line.clear();

        line.add(15);
        line.add(16);
        line.add(17);
        innerSquareMills.add(line);
        line.clear();

        edgeExists[6][7] = true;
        edgeExists[7][6] = true;

        edgeExists[6][11] = true;
        edgeExists[11][6] = true;

        edgeExists[7][8] = true;
        edgeExists[8][7] = true;

        edgeExists[8][12] = true;
        edgeExists[12][8] = true;

        edgeExists[12][17] = true;
        edgeExists[17][12] = true;

        edgeExists[11][15] = true;
        edgeExists[15][11] = true;

        edgeExists[15][16] = true;
        edgeExists[16][15] = true;

        edgeExists[16][17] = true;
        edgeExists[17][16] = true;

    }
    protected void setUpMiddleSquare() {
        line.clear();

        line.add(3);
        line.add(4);
        line.add(5);
        middleSquareMills.add(line);
        line.clear();

        line.add(3);
        line.add(10);
        line.add(18);
        middleSquareMills.add(line);
        line.clear();

        line.add(5);
        line.add(13);
        line.add(20);
        middleSquareMills.add(line);
        line.clear();

        line.add(18);
        line.add(19);
        line.add(20);
        middleSquareMills.add(line);
        line.clear();


        edgeExists[3][4]=true;
        edgeExists[4][3]=true;

        edgeExists[3][10]=true;
        edgeExists[10][3]=true;

        edgeExists[4][5]=true;
        edgeExists[5][4]=true;

        edgeExists[5][13]=true;
        edgeExists[13][5]=true;

        edgeExists[10][18]=true;
        edgeExists[18][10]=true;

        edgeExists[18][19]=true;
        edgeExists[19][18]=true;

        edgeExists[13][20]=true;
        edgeExists[20][13]=true;

        edgeExists[19][20]=true;
        edgeExists[20][19]=true;


    }
    private void setUpOuterSquare() {
        line.clear();

        line.add(0);
        line.add(1);
        line.add(2);
        outerSquareMills.add(line);
        line.clear();

        line.add(0);
        line.add(9);
        line.add(21);
        outerSquareMills.add(line);
        line.clear();

        line.add(2);
        line.add(14);
        line.add(23);
        outerSquareMills.add(line);
        line.clear();

        line.add(21);
        line.add(22);
        line.add(23);
        outerSquareMills.add(line);
        line.clear();



        edgeExists[0][1]=true;
        edgeExists[1][0]=true;

        edgeExists[1][2]=true;
        edgeExists[2][1]=true;

        edgeExists[2][14]=true;
        edgeExists[14][2]=true;

        edgeExists[14][23]=true;
        edgeExists[23][14]=true;

        edgeExists[0][9]=true;
        edgeExists[9][0]=true;

        edgeExists[9][21]=true;
        edgeExists[21][9]=true;

        edgeExists[21][22]=true;
        edgeExists[22][21]=true;

        edgeExists[22][23]=true;
        edgeExists[23][22]=true;

    }
    protected void setupNineBoard(){

        setupSixBoard();
        setUpOuterSquare();

        nmmMills.addAll(smmMills);
        nmmMills.addAll(outerSquareMills);

        line.clear();

        line.add(1);
        line.add(4);
        line.add(7);
        nmmMills.add(line);
        line.clear();

        line.add(9);
        line.add(10);
        line.add(11);
        nmmMills.add(line);
        line.clear();

        line.add(12);
        line.add(13);
        line.add(14);
        nmmMills.add(line);
        line.clear();

        line.add(16);
        line.add(19);
        line.add(22);
        nmmMills.add(line);
        line.clear();



        edgeExists[1][4]=true;
        edgeExists[4][1]=true;

        edgeExists[9][10]=true;
        edgeExists[10][9]=true;

        edgeExists[13][14]=true;
        edgeExists[14][13]=true;

        edgeExists[19][22]=true;
        edgeExists[22][19]=true;



    }
    public void setupSixBoard() {
        setUpInnerSquare();
        setUpMiddleSquare();

        smmMills.addAll(middleSquareMills);
        smmMills.addAll(innerSquareMills);

        edgeExists[4][7]=true;
        edgeExists[7][4]=true;

        edgeExists[10][11]=true;
        edgeExists[11][10]=true;

        edgeExists[12][13]=true;
        edgeExists[13][12]=true;

        edgeExists[16][19]=true;
        edgeExists[19][16]=true;

    }
    protected void setupTwelveBoard(){

        setupNineBoard();

        twmmMills.addAll(nmmMills);

        line.clear();

        line.add(0);
        line.add(3);
        line.add(6);
        twmmMills.add(line);
        line.clear();

        line.add(2);
        line.add(5);
        line.add(8);
        twmmMills.add(line);
        line.clear();

        line.add(15);
        line.add(18);
        line.add(21);
        twmmMills.add(line);
        line.clear();

        line.add(17);
        line.add(20);
        line.add(23);
        twmmMills.add(line);
        line.clear();


        edgeExists[0][3]=true;
        edgeExists[3][0]=true;

        edgeExists[3][6]=true;
        edgeExists[6][3]=true;

        edgeExists[2][5]=true;
        edgeExists[5][2]=true;

        edgeExists[5][8]=true;
        edgeExists[8][5]=true;

        edgeExists[15][18]=true;
        edgeExists[18][15]=true;

        edgeExists[18][21]=true;
        edgeExists[21][18]=true;

        edgeExists[17][20]=true;
        edgeExists[20][17]=true;

        edgeExists[20][23]=true;
        edgeExists[23][20]=true;



    }
    public void setupThreeBoard(){

        setUpInnerSquare();

        tmmMills.addAll(innerSquareMills);

        line.clear();

        line.add(6);
        line.add(17);
        line.add(24);
        tmmMills.add(line);
        line.clear();

        line.add(8);
        line.add(15);
        line.add(24);
        tmmMills.add(line);
        line.clear();

        line.add(11);
        line.add(12);
        line.add(24);
        tmmMills.add(line);
        line.clear();

        line.add(7);
        line.add(16);
        line.add(24);
        tmmMills.add(line);
        line.clear();


        edgeExists[24][6]=true;
        edgeExists[6][24]=true;

        edgeExists[24][7]=true;
        edgeExists[7][24]=true;

        edgeExists[24][8]=true;
        edgeExists[8][24]=true;

        edgeExists[24][11]=true;
        edgeExists[11][24]=true;

        edgeExists[24][12]=true;
        edgeExists[12][24]=true;

        edgeExists[24][15]=true;
        edgeExists[15][24]=true;

        edgeExists[24][16]=true;
        edgeExists[16][24]=true;

        edgeExists[24][17]=true;
        edgeExists[17][24]=true;

    }

}
