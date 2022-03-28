package com.company;

import java.awt.*;

public class Octagon extends Shape{

    @Override
    public void draw(Graphics g) {

        int X[]={x, (int)(x-width/2.3),x-width/2,(int)(x-width/2.3), x,         (int)(x+width/2.3),x+width/2,(int)(x+width/2.3)};
        int Y[]={y+height/2,y+height/4,y        ,y-height/4,y-height/2,y-height/4,y,y+height/4};
        g.drawPolygon(X,Y,8);
        g.setColor(color);
        g.fillPolygon(X,Y,8);

    }
}
