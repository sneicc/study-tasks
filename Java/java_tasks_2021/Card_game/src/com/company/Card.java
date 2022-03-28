package com.company;

public class Card {
    public int value;
    public int suit; //1-пики, 2-черви, 3-буби, 4-крести
    public String pick;

    public Card(){
        this.value = 0;
        this.suit = 0;
        this.pick = "";
    }
    public Card(int value, int suit, String pick){
        this.value = value;
        this.suit = suit;
        this.pick = pick;
    }


    public int getValue(){
        return value;
    }

    public int getSuit(){
        return suit;
    }

    public String getPick(){
        return pick;
    }

    public void setValue(int a){
        value = a;
    }

    public void setSuit(int a){
        suit = a;
    }

    public void setPick(String a){
        pick = a;
    }




}
