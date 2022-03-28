package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    public Main(){
        setTitle("Football");

        JPanel jp1 = (JPanel)getContentPane();
        JButton btn1 = new JButton("AC Milan");

        JButton btn2 = new JButton("Real Madrid");

        jp1.add(btn1, BorderLayout.WEST);
        jp1.add(btn2, BorderLayout.EAST);

        JPanel jp2 = new JPanel();
        jp2.setLayout(new GridLayout(1,3));
        JLabel lbl1 = new JLabel("Result: 0 X 0");
        JLabel lbl2 = new JLabel("Last Scorer: N/A");
        JLabel lbl3 = new JLabel("Winner: N/A");

        jp2.add(lbl1);
        jp2.add(lbl2);
        jp2.add(lbl3);
        jp1.add(jp2);

        btn1.addActionListener((e)->{
                char ch=lbl1.getText().charAt(8);
                int k = ch -'0';
                k++;
                lbl1.setText(lbl1.getText().substring(0,8)+k+lbl1.getText().substring(9 ));
                lbl2.setText("Last Scorer: Milan");
                if(lbl1.getText().charAt(12)-'0' < k){
                    lbl3.setText("Winner: Milan");
                }else if(lbl1.getText().charAt(12)-'0' == k) lbl3.setText("Winner: N/A");
        });
        btn2.addActionListener((e)->{
            char ch=lbl1.getText().charAt(12);
            int i = ch -'0';
            i++;
            lbl1.setText(lbl1.getText().substring(0,12)+i);
            lbl2.setText("Last Scorer: Real");
            if(lbl1.getText().charAt(8)-'0' < i){
                lbl3.setText("Winner: Real");
            }else if(lbl1.getText().charAt(12)-'0' == i) lbl3.setText("Winner: N/A");
        });



        setSize(800,200);
        setVisible(true);
    }

    public static void main(String[] args) {

        new Main();

    }
}
