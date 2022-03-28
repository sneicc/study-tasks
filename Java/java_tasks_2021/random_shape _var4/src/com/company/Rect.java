package com.company;

import java.awt.*;

public class Rect extends Shape{

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(150,300,100,50);
    }
}
