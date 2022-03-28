package com.company;

import javax.swing.*;
import java.awt.*;
public class Clock1 extends JFrame{
    Container cn;
    public Clock1() {

        cn = init();
    }
    public static void main(String[] args) {
        new Clock1();
    }
    public Container init() {
        Container cn = this.getContentPane();
//			this.setVisible(true);
        this.setSize(500, 500);
//			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.show();

        return cn;
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, 500, 400);
        setBackground(Color.white);
        g.setFont(new Font("UTM Nokia", 1, 20));
        g.setColor(Color.cyan);
        g.fillOval(107 , 105 , 285 , 285 );
        g.setColor(Color.white);
        g.fillOval(125 , 125 , 250 , 250 );
        g.setColor(Color.cyan);
        g.fillRect(185, 355, 140, 15);
        g.setColor(Color.white);
        g.fillRect(175, 368, 170, 30);

        g.setColor(Color.black);

        int x1,  x2;
        int y2=0;
        int y1=0;
        int a1=0;
        int a2=0;
        for (int i = 0; i <= 59; i++) {


            if (i>=25&&i<=35){
                x1 = (int) (140 * Math.sin(Math.toRadians(i * 6)));
                x2 = (int) (130 * Math.sin(Math.toRadians(i * 6)));
                a1 =y1;
                a2=y2;
                g.drawLine(x1 + 250, y1 + 255, x2 + 250, y2 + 250);

            }else{
                x1 = (int) (140 * Math.sin(Math.toRadians(i * 6)));
                y1 = (int) -(140 * Math.cos(Math.toRadians(i * 6)));
                x2 = (int) (130 * Math.sin(Math.toRadians(i * 6)));
                y2 = (int) -(130 * Math.cos(Math.toRadians(i * 6)));
                g.drawLine(x1 + 250, y1 + 250, x2 + 250, y2 + 250);
            }

        }
        int xx1, xx2;
        int yy1=0;
        int yy2=0;
        for (int i = 0; i <= 11; i++) {
            for (float j = (float) - 0.3; j <= 0.3; j += 0.1) {
                if (i>=5&&i<=7){
                    xx1 = (int) (105 * Math.sin(Math.toRadians(i * 30 + j)));
                    xx2 = (int) (120 * Math.sin(Math.toRadians(i * 30 + j)));
                    yy1=a1-20;
                    yy2=a2+13;
                }else{
                    xx1 = (int) (140 * Math.sin(Math.toRadians(i * 30 + j)));
                    yy1 = (int) -(140 * Math.cos(Math.toRadians(i * 30 + j)));
                    xx2 = (int) (120 * Math.sin(Math.toRadians(i * 30 + j)));
                    yy2 = (int) -(120 * Math.cos(Math.toRadians(i * 30 + j)));
                }

                g.drawLine(xx1 + 250, yy1 + 250, xx2 + 250, yy2 + 250);
            }
        }

        g.drawString("12", 240, 145);
        g.drawString("1", 300, 160);
        g.drawString("2", 340, 200);
        g.drawString("3", 357, 257);
        g.drawString("4", 340, 310);
        g.drawString("5", 295, 340);
        g.drawString("6", 245, 340);
        g.drawString("7", 194, 340);
        g.drawString("8", 150, 310);
        g.drawString("9", 135, 257);
        g.drawString("10", 150, 205);
        g.drawString("11", 187, 160);





    }
}
