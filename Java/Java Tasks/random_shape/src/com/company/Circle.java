package com.company;

import java.awt.*;

public class Circle extends Shape{
    public void randomFill(){
        super.randomFill();
        this.width = this.height;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x,y,width,height);

    }
}
