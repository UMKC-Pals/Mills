package sprint1.production;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class Board {
//    in the board, there are 25 positions maximum,
//    these 25 are represented as 0 to 24 in an array.
//    with 0 to 23 linearly arranged from top-left to bottom right row wise.
//    24 is the center vertex used in 3 mens morris.
//
    boolean[][] edgeExists;
    RoundButton [] roundBtnArray;
    HashSet<TreeSet<Integer>> innerSquareMills, middleSquareMills, outerSquareMills, nmmMills, smmMills, twmmMills, tmmMills;
    TreeSet<Integer> line;
    public Board(int size) {
            edgeExists = new boolean[25][25];
            roundBtnArray = new RoundButton[25];

            int[] y = {15,15,15,115,115,115,215,215,215,315,315,315,315,315,315,415,415,415,515,515,515,615,615,615, 315};
            int[] x = {15,315,615,115,315,515,215,315,415,15,115,215,415,515,615,215,315,415,115,315,515,15,315,615, 315};

            int dim1=10,dim2=20;
//            Pair c1=new Pair<>(x1,y1);


            for(int i=0;i<25;i++){
                roundBtnArray[i] = new RoundButton("");
                roundBtnArray[i].currentState=buttonStates.INVALID;
                roundBtnArray[i].setSize(10,10);

                roundBtnArray[i].setBounds(285+x[i]-(dim1/2),y[i],dim1,dim1);

            }



            innerSquareMills = new HashSet<TreeSet<Integer>>();
            middleSquareMills = new HashSet<TreeSet<Integer>>();
            outerSquareMills = new HashSet<TreeSet<Integer>>();
            nmmMills = new HashSet<TreeSet<Integer>>();
            smmMills = new HashSet<TreeSet<Integer>>();
            twmmMills = new HashSet<TreeSet<Integer>>();
            tmmMills = new HashSet<TreeSet<Integer>>();
            line=new TreeSet<Integer>();

            if(size==3){
            setupThreeBoard();
            }
            else if(size==6){
            setupSixBoard();
            }
            else if(size==9){
            setupNineBoard();
            }
            else if(size==12){
            setupTwelveBoard();
            }
            else{
                System.exit(1);
            }
    }

    protected void setEdgeExists(int a, int b){
        if (a >= 0 && a <= 24 && b>=0 && b<=24) {
            edgeExists[a][b] = true;
            edgeExists[b][a] = true;
        }
    }
    protected void setUpInnerSquare() {
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

        setEdgeExists(6,7);
        setEdgeExists(6,11);
        setEdgeExists(8,7);
        setEdgeExists(12,8);
        setEdgeExists(12,17);
        setEdgeExists(11,15);
        setEdgeExists(15,16);
        setEdgeExists(16,17);

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

        setEdgeExists(3,4);
        setEdgeExists(3,10);
        setEdgeExists(5,4);
        setEdgeExists(13,5);
        setEdgeExists(10,18);
        setEdgeExists(18,19);
        setEdgeExists(13,20);
        setEdgeExists(19,20);

    }
    protected void setUpOuterSquare() {
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

        setEdgeExists(1,0);
        setEdgeExists(9,0);
        setEdgeExists(1,2);
        setEdgeExists(14,2);
        setEdgeExists(14,23);
        setEdgeExists(21,9);
        setEdgeExists(21,22);
        setEdgeExists(23,22);

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

        setEdgeExists(1,4);
        setEdgeExists(10,9);
        setEdgeExists(13,14);
        setEdgeExists(19,22);

    }
    protected void setupSixBoard() {
        setUpInnerSquare();
        setUpMiddleSquare();

        smmMills.addAll(middleSquareMills);
        smmMills.addAll(innerSquareMills);

        setEdgeExists(7,4);
        setEdgeExists(10,11);
        setEdgeExists(12,13);
        setEdgeExists(16,19);

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

        setEdgeExists(0,3);
        setEdgeExists(3,6);
        setEdgeExists(5,2);
        setEdgeExists(8,5);
        setEdgeExists(18,15);
        setEdgeExists(18,21);
        setEdgeExists(20,17);
        setEdgeExists(20,23);
    }
    protected void setupThreeBoard(){

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

        setEdgeExists(24,6);
        setEdgeExists(7,24);
        setEdgeExists(8,24);
        setEdgeExists(11,24);
        setEdgeExists(12,24);
        setEdgeExists(15,24);
        setEdgeExists(16,24);
        setEdgeExists(17,24);

    }

}
