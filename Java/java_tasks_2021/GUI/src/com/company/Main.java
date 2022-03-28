package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    public Main(){
        setTitle("First swing");

        JPanel jp1= (JPanel)getContentPane();
        jp1.setLayout(new GridLayout(2,2));
        JButton btn1 = new JButton("Hello : ");
        JLabel lbl1 = new JLabel();
        jp1.add(btn1);
        JTextField txt1 = new JTextField();
        jp1.add(txt1);
        jp1.add(lbl1);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbl1.setText("Hello "+txt1.getText());
            }
        });
        setSize(400,200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
	// write your code here
    }
}
