package com.company;

import javax.swing.*;
import java.awt.*;

public class Table extends JFrame  {

    public Table(String[][] data, String[] names){
        JTable table = new JTable(data, names);
        JScrollPane jsp = new JScrollPane(table);

        this.getContentPane().add(jsp,BorderLayout.CENTER);
        this.setSize(640,480);
        this.setVisible(true);

    }
}
