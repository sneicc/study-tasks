package com.company;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	// write your code here
        char [][]arr=
                {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                 {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                 {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                 {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                 {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                 {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                 {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                 {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                 {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        //testdisp(get_row(arr,3));
        //testdisp(get_col(arr,2));
        //testdisp(get_block(arr,2,3));
        //testdisp(find_empty_pos(arr));
        //testdisp(find_poss_val(arr,find_empty_pos(arr)));
        //char []arra={'2','0'};
        //testdisp(find_poss_val(arr,arra));
        find_solution(arr);


    }

    static char[] get_row(char [][] arr, int pos){
        char[] ans = new char[9];
        for (int i = 0; i < 9; i++) {
            ans[i] = arr[pos][i];
        }
        return ans;
    }

    static char[] get_col(char[][] arr, int pos){
        char[] ans = new char[9];
        for (int i = 0; i < 9; i++) {
            ans[i]= arr[i][pos];
        }
        return ans;
    }

    static char[] get_block(char[][] arr, int row, int col){
        char[] ans = new char[9];
        int x = (row/3)*3;
        int y = (col/3)*3;
        int k=0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ans[k++]=arr[x+i][y+j];
            }
        }
        return ans;
    }

    static char[] find_empty_pos(char[][] arr){
        char[] ans= {'N','N'};
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(arr[i][j] == '.') {
                    ans[0]= (char)(i+'0');
                    ans[1]= (char)(j+'0');
                    return ans;
                }
            }
        }
        return ans;
    }

    static int[] find_poss_val(char[][] arr, char[] pos){
        if(pos[0]!='N') {
            int x = pos[0] - '0';
            int y = pos[1] - '0';
            Deque ans = new ArrayDeque();
            Map<Integer, Integer> posvalues = new HashMap<>();
            char[] row = get_row(arr, x);

            for (int i = 1; i <= 9; i++) {
                boolean flag = true;
                for (int j = 0; j < 9; j++) {
                    if (row[j] - '0' == i) {
                        flag = false;
                        break;
                    }
                }
                if (flag == true) {
                    if (posvalues.containsKey(i)) {
                        posvalues.put(i, posvalues.get(i) + 1);
                    } else {
                        posvalues.put(i, 1);
                    }
                }
            }
            char[] col = get_col(arr, y);
            for (int i = 1; i <= 9; i++) {
                boolean flag = true;
                for (int j = 0; j < 9; j++) {
                    if (col[j] - '0' == i) {
                        flag = false;
                        break;
                    }
                }
                if (flag == true) {
                    if (posvalues.containsKey(i)) {
                        posvalues.put(i, posvalues.get(i) + 1);
                    } else {
                        posvalues.put(i, 1);
                    }
                }
            }
            char[] block = get_block(arr, x, y);
            for (int i = 1; i <= 9; i++) {
                boolean flag = true;
                for (int j = 0; j < 9; j++) {
                    if (block[j] - '0' == i) {
                        flag = false;
                        break;
                    }
                }
                if (flag == true) {
                    if (posvalues.containsKey(i)) {
                        posvalues.put(i, posvalues.get(i) + 1);
                    } else {
                        posvalues.put(i, 1);
                    }
                }
            }

            int count = 0;
            for (int a : posvalues.keySet()) {
                if (posvalues.get(a) == 3) count++;
            }
            if(count ==0){
                int[] c={0};
                return c;
            }
            int[] buf = new int[count];
            count = 0;
            for (int a : posvalues.keySet()) {
                if (posvalues.get(a) == 3) {
                    buf[count++] = a;
                }
            }

            return buf;
        }
        int[] C = {-1};
        return C;
    }

    static void find_solution(char[][] arr){
        char[] positions = find_empty_pos(arr);
        int x = positions[0]-'0';
        int y = positions[1]-'0';
        int[] values = find_poss_val(arr, positions);
        if(values[0] == 0){
            return;
        }else if(values[0] == -1){
            print(arr);
            return;
        }
        for (int i = 0; i < values.length; i++) {
            arr[x][y] = (char)(values[i]+'0');
            find_solution(arr);
        }
        arr[x][y] = '.';

    }


    static void testdisp(char []arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] +" ");
        }
        System.out.println();
    }

    static void testdisp(int []arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] +" ");
        }
        System.out.println();
    }

    static void print(char[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length ; j++) {
                System.out.print(arr[i][j] +" ");
            }
            System.out.println();
        }

    }
}
