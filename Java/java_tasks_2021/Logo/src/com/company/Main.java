package com.company;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    Container cn;
    public Main() {

        cn = init();
    }
    public static void main(String[] args) {
        new Main();
    }
    public Container init (){
        Container cn = this.getContentPane();
        JPanel P = new JPanel();
        this.add(P,BorderLayout.CENTER);
        this.setSize(900,600);
        this.setVisible(true);

        return cn;
    }

    public void paint(Graphics g){
        g.setColor(new Color(192,192,192));
        g.fillOval(300,300,100,100);
        g.setColor(new Color(255,255,255));
        g.fillOval(300+5,300+5,90,90);

        g.setColor(new Color(192,192,192));
        g.fillOval(380,300,100,100);
        g.setColor(new Color(255,255,255));
        g.fillOval(380+5,300+5,90,90);

        g.setColor(new Color(192,192,192));
        g.fillOval(460,300,100,100);
        g.setColor(new Color(255,255,255));
        g.fillOval(460+5,300+5,90,90);

        g.setColor(new Color(192,192,192));
        g.fillOval(540,300,100,100);
        g.setColor(new Color(255,255,255));
        g.fillOval(540+5,300+5,90,90);
//---------------------------------------------------------------------
        g.setColor(new Color(192,192,192));
        g.fillOval(385,323,15,55);
        g.setColor(new Color(255,255,255));
        g.fillOval(385+1,323+4,9,45);

        g.setColor(new Color(192,192,192));
        g.fillOval(465,323,15,55);
        g.setColor(new Color(255,255,255));
        g.fillOval(465+1,323+4,9,45);

        g.setColor(new Color(192,192,192));
        g.fillOval(545,323,15,55);
        g.setColor(new Color(255,255,255));
        g.fillOval(545+1,323+4,9,45);

        g.setFont(new Font("Arial", 1, 80));
        g.setColor(Color.RED);

        g.drawString("A", 365, 470);
        g.drawString("u", 425, 470);
        g.drawString("d", 475, 470);
        g.drawString("i", 530, 470);


    }
}
