package com.company;

public class Car implements Comparable{
    private String reg;
    private int year;
    private int mil;
    private int price;
    //--------------------------------
    public Car(String reg, int year, int mil, int price){
        this.reg = reg;
        this.year = year;
        this.mil = mil;
        this.price = price;
    }
    //--------------------------------
    public void setReg(String reg){
        this.reg = reg;
    }
    public void setYear(int year){
        this.year = year;
    }
    public void setMil(int mil){
        this.mil = mil;
    }
    public void setPrice(int price){
        this.price = price;
    }
    //--------------------------------
    public String getReg(){
        return reg;
    }
    public int getYear(){
        return year;
    }
    public int getMil(){
        return mil;
    }
    public int getPrice(){
        return price;
    }
    //--------------------------------
    public String toString(){
        return String.format("%9s \t %d \t %d \t %d", reg, year, mil, price);
    }
    //--------------------------------
    public int compareTo(Object obj){
        if(obj == null)return 1;
        Car e =(Car)obj;
        if(this.year >e.year) return -1;
        else if (this.year == e.year)return 0;
        return 1;
    }



    //public void cheap(Car[] arr){
    //    int a=0;
    //    for(int i =0; i < arr.length; i++){
    //       if(arr[i].price < arr[a].price) a = i;
    //    }
    //    System.out.println(arr[a]);
   //}
}
