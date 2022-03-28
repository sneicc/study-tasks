package com.company;

public  class Main {
    public static String[][] maze = {
            {" ", " ", " ", "*", " ", " ", " ",},
            {"*", "*", " ", "*", " ", "*", " ",},
            {" ", " ", " ", " ", " ", " ", " ",},
            {" ", "*", "*", "*", "*", "*", " ",},
            {" ", " ", " ", " ", " ", " ", "e"}};

    public static void main(String[] args) {
	// write your code here
        findPath(0,0,0);
    }

    public static boolean isInRange(int row, int col) {
        boolean rowInRange = row >= 0 && row < maze.length;
        boolean colInRange = col >= 0 && col < maze[0].length;
        return rowInRange && colInRange;
    }

    public static void findPath(int row, int col, int step){
        if(! isInRange(row, col))return;
        if(maze[row][col] == "e"){
            printSolution();
            return;
        }
        if(maze[row][col] != " ")return;

        maze[row][col]= "" + step;

        findPath(row-1,col,step+1);
        findPath(row+1,col,step+1);
        findPath(row,col-1,step+1);
        findPath(row,col+1,step+1);

        maze[row][col]=" ";

    }

    public static void printSolution(){
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
