package com.company;

import java.awt.*;

public class Octagon extends Shape{

    @Override
    public void draw(Graphics g) {

        int X[]={475,400,350,400,475,550,600,550};
        int Y[]={110,140,170,210,230,210,170,140};
        g.drawPolygon(X,Y,8);
        g.setColor(color);
        g.fillPolygon(X,Y,8);


        g.setColor(Color.BLACK);
        g.drawLine(0,240,640,240);
        g.drawLine(320,0,320,480);
    }
}
