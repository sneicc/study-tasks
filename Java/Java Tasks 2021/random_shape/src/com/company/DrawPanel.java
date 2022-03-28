package com.company;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    Shape[] shapes = new Shape[80];
    static int cnt=0;

    public void paint(Graphics g){
        super.paint(g);
        setDoubleBuffered(true);
        for (Shape shape: shapes){
            if (shape != null)shape.draw(g);
        }
    }

    public void addRectangle(){
        Rect rect=new Rect();
        rect.randomFill();
        shapes[cnt++]=rect;
    }
    public void addCircle(){
        Circle circle=new Circle();
        circle.randomFill();
        shapes[cnt++]=circle;
    }
    public void addTriangle(){
        Triangle triangle=new Triangle();
        triangle.randomFill();
        shapes[cnt++]=triangle;
    }
}

