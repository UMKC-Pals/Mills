package sprint2.production;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

public class Board {


    /*  In the board, there are 25 positions maximum,
        these 25 are represented as 0 to 24 in an array.
        with 0 to 23 linearly arranged from top-left to bottom-right row wise.
        24 is the center vertex which is used only in 3 mens morris.
    */
    static boolean[][] edgeExists;
    static RoundButton [] roundBtnArray;
    HashSet<TreeSet<Integer>> innerSquareMills, middleSquareMills, outerSquareMills, nmmMills, smmMills, twmmMills, tmmMills;
//    TreeSet<Integer> line;
    static int[] y = {15,15,15,115,115,115,215,215,215,315,315,315,315,315,315,415,415,415,515,515,515,615,615,615, 315};
    static int[] x = {15,315,615,115,315,515,215,315,415,15,115,215,415,515,615,215,315,415,115,315,515,15,315,615, 315};

    static int dim1=10;
    static int dim2=20;

    public Board(int size) {

            edgeExists = new boolean[25][25];
            roundBtnArray = new RoundButton[25];

            for(int i=0;i<25;i++){
                roundBtnArray[i] = new RoundButton("");
                roundBtnArray[i].currentBtnState =buttonStates.INVALID;
                roundBtnArray[i].setSize(10,10);

                roundBtnArray[i].setBounds(285+x[i]-(dim1/2),y[i],dim1,dim1);
                roundBtnArray[i].setBorder(BorderFactory.createEmptyBorder());
            }

            innerSquareMills = new HashSet<TreeSet<Integer>>();
            middleSquareMills = new HashSet<TreeSet<Integer>>();
            outerSquareMills = new HashSet<TreeSet<Integer>>();
            nmmMills = new HashSet<TreeSet<Integer>>();
            smmMills = new HashSet<TreeSet<Integer>>();
            twmmMills = new HashSet<TreeSet<Integer>>();
            tmmMills = new HashSet<TreeSet<Integer>>();
//            line=new TreeSet<Integer>();

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
        if (a >= 0 && a <= 24 && b >= 0 && b <= 24) {
            edgeExists[a][b] = true;
            edgeExists[b][a] = true;
        }
    }
    public static boolean getEdgeExists(int a, int b) {
        return edgeExists[a][b];
    }
    protected void setUpInnerSquare() {

        innerSquareMills.add(new TreeSet<Integer>(Arrays.asList(6,7,8)));

        innerSquareMills.add(new TreeSet<Integer>(Arrays.asList(6,11,15)));

        innerSquareMills.add(new TreeSet<Integer>(Arrays.asList(8,12,17)));

        innerSquareMills.add(new TreeSet<Integer>(Arrays.asList(15,16,17)));

        setEdgeExists(6,7);
        setEdgeExists(7,8);
        setEdgeExists(8,12);
        setEdgeExists(12,17);
        setEdgeExists(6,11);
        setEdgeExists(11,15);
        setEdgeExists(15,16);
        setEdgeExists(16,17);

    }
    protected void setUpMiddleSquare() {

        middleSquareMills.add(new TreeSet<Integer>(Arrays.asList(3,4,5)));

        middleSquareMills.add(new TreeSet<Integer>(Arrays.asList(3,10,18)));

        middleSquareMills.add(new TreeSet<Integer>(Arrays.asList(5,13,20)));

        middleSquareMills.add(new TreeSet<Integer>(Arrays.asList(18,19,20)));

        setEdgeExists(3,4);
        setEdgeExists(4,5);
        setEdgeExists(5,13);
        setEdgeExists(13,20);
        setEdgeExists(3,10);
        setEdgeExists(10,18);
        setEdgeExists(18,19);
        setEdgeExists(19,20);

    }
    protected void setUpOuterSquare() {
        outerSquareMills.add(new TreeSet<Integer>(Arrays.asList(0,1,2)));

        outerSquareMills.add(new TreeSet<Integer>(Arrays.asList(0,9,21)));

        outerSquareMills.add(new TreeSet<Integer>(Arrays.asList(2,14,23)));

        outerSquareMills.add(new TreeSet<Integer>(Arrays.asList(21,22,23)));

        setEdgeExists(0,1);
        setEdgeExists(1,2);
        setEdgeExists(2,14);
        setEdgeExists(14,23);
        setEdgeExists(0,9);
        setEdgeExists(9,21);
        setEdgeExists(21,22);
        setEdgeExists(22,23);

    }
    protected void setupThreeBoard(){

        setUpInnerSquare();

        tmmMills.addAll(innerSquareMills);

        tmmMills.add(new TreeSet<Integer>(Arrays.asList(6,17,24)));

        tmmMills.add(new TreeSet<Integer>(Arrays.asList(8,15,24)));

        tmmMills.add(new TreeSet<Integer>(Arrays.asList(7,16,24)));

        tmmMills.add(new TreeSet<Integer>(Arrays.asList(11,12,24)));

        setEdgeExists(24,6);
        setEdgeExists(7,24);
        setEdgeExists(8,24);
        setEdgeExists(11,24);
        setEdgeExists(12,24);
        setEdgeExists(15,24);
        setEdgeExists(16,24);
        setEdgeExists(17,24);

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
    protected void setupNineBoard(){

        setupSixBoard();
        setUpOuterSquare();

        nmmMills.addAll(smmMills);
        nmmMills.addAll(outerSquareMills);

        nmmMills.add(new TreeSet<Integer>(Arrays.asList(1, 4, 7)));

        nmmMills.add(new TreeSet<Integer>(Arrays.asList(9, 10, 11)));

        nmmMills.add(new TreeSet<Integer>(Arrays.asList(12, 13, 14)));

        nmmMills.add(new TreeSet<Integer>(Arrays.asList(16, 19, 22)));

        setEdgeExists(1,4);
        setEdgeExists(10,9);
        setEdgeExists(13,14);
        setEdgeExists(19,22);

    }

    protected void setupTwelveBoard(){

        setupNineBoard();

        twmmMills.addAll(nmmMills);

        twmmMills.add(new TreeSet<Integer>(Arrays.asList(0,3,6)));

        twmmMills.add(new TreeSet<Integer>(Arrays.asList(2,5,8)));

        twmmMills.add(new TreeSet<Integer>(Arrays.asList(15,18,21)));

        twmmMills.add(new TreeSet<Integer>(Arrays.asList(17,20,23)));

        setEdgeExists(0,3);
        setEdgeExists(3,6);
        setEdgeExists(5,2);
        setEdgeExists(8,5);
        setEdgeExists(18,15);
        setEdgeExists(18,21);
        setEdgeExists(20,17);
        setEdgeExists(20,23);
    }

}
