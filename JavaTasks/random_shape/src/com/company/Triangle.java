package com.company;

import java.awt.*;

public class Triangle extends Shape{

    @Override
    public void draw(Graphics g) {
        int X[]={x,y-width/2,x+width/2};
        int Y[]={y,y-height/2, y-height/2};
        g.drawPolygon(X,Y,3);
        g.setColor(color);
        g.fillPolygon(X,Y,3);
    }
}
