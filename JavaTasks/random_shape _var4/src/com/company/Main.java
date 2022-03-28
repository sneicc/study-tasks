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
        frame.setSize(670,530);
        frame.setVisible(true);
        drawPanel = new DrawPanel();
        drawPanel.setSize(640,480);

        Container mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        mainContainer.add(drawPanel,BorderLayout.CENTER);

                drawPanel.addCircle();
                drawPanel.addRectangle();
                drawPanel.addOctagon();

        drawPanel.repaint();
    }
}
