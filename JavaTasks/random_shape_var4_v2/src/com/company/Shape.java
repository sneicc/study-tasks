package com.company;

import java.awt.*;
import java.util.Random;

public abstract class Shape {
    public int x, y, width, height;
    public Color color= Color.BLUE;

    public int randint( int min, int max){
        Random rnd = new Random();
        return min + rnd.nextInt(max - min);
    }

    public void randomFill(){
        this.x = randint(40,560);
        this.y = randint(40,370);
        this.width = randint(40,80);
        this.height = randint(40,80);
        this.color = new Color(randint(0,255),randint(0,255),randint(0,255));
    }

    public abstract void draw(Graphics g);

}
