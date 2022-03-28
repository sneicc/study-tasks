package com.company;

import javax.swing.*;
import java.awt.*;
public class Main {
    private JFrame frame;
    private DrawPanel drawPanel;

    public static void main(String[] args) {
	// write your code here
        new Main();
    }

    public Main(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(640,480);
        frame.setVisible(true);
        drawPanel = new DrawPanel();
        drawPanel.setSize(640,480);

        //Container mainContainer = frame.getContentPane();
        //mainContainer.setLayout(new BorderLayout());

        //mainContainer.add(drawPanel,BorderLayout.CENTER);
        frame.getContentPane();
        frame.add(drawPanel,BorderLayout.CENTER);
        for (int i=0; i<80;i++){
            int k = (int)(Math.random()*3);
            switch(k){
                case 0:drawPanel.addCircle();
                    break;
                case 1:drawPanel.addRectangle();
                    break;
                case 2:drawPanel.addOctagon();
                    break;
            }
        }
        drawPanel.repaint();
    }
}
