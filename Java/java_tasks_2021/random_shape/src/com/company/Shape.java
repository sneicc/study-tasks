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

    public Color randomColor(){
        Color[] colors = new Color[]{new Color(0,255,255), new Color(0,255,0), new Color(0,169,245),
                new Color(0,169,0), new Color(255,169,255), new Color(255,255,255), new Color(255,89,80)};
        return colors[randint(0, colors.length)];
    }

    public void randomFill(){
        this.x = randint(20,380);
        this.y = randint(20,180);
        this.width = randint(10,40);
        this.height = randint(10,40);
        this.color = randomColor();
    }

    public abstract void draw(Graphics g);

}
