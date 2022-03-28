package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int []arr=new int[3];
        comb(arr,0,0,2);
    }

    public static void comb(int[] arr, int index, int min, int max){
        if(index >= 3){
            System.out.println("{"+arr[0]+", "+arr[1]+", "+arr[2]+"}");
            return;
        }

        for(int i = min; i <= max;i++){
            arr[index]=i;
            comb(arr,index+1,min,max);
        }
    }
}
