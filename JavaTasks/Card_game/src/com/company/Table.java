package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Random;

public class Table extends JFrame {
    public Card [] arr = new Card[36];

    JLabel lb1;
    JButton btn1;
    JLabel lb2;
    JLabel countp1 = new JLabel();
    JLabel countp2 = new JLabel();
    ArrayDeque<Card> arr1 = new ArrayDeque<Card>();
    ArrayDeque<Card> arr2 = new ArrayDeque<Card>();
    ArrayDeque<Card> dispute = new ArrayDeque<Card>();

    public Table(){
        createDeck(arr);
        shuffleArray(arr);

        for (int i = 0; i < 18; i++) {
            arr1.addFirst(arr[i]);
            arr2.addFirst(arr[i+18]);
        }
        countp1.setText("Карт: "+arr1.size());
        countp2.setText("Карт: "+arr2.size());

        JPanel panelUp = new JPanel();
        JPanel panelDown = new JPanel();
        panelDown.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //JLabel lb1 = new JLabel(new ImageIcon(getClass().getResource("/picks/12.png")));
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/picks/what.png")); // load the image to a imageIcon
        lb1 = new JLabel();
        lb1.setIcon(resize(imageIcon));
        lb2= new JLabel(resize(imageIcon));
        btn1 = new JButton();
        ImageIcon imageIconBtn = new ImageIcon(getClass().getResource("/picks/jacket.png"));
        //btn1.setFocusPainted(true);
        btn1.setContentAreaFilled(false);
        //btn1.setMargin(new Insets(0, 0, 0, 0));
        btn1.setBorder(BorderFactory.createEmptyBorder());
        //btn1.setBackground(Color.white);
        btn1.setIcon(resize(imageIconBtn));

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.anchor= GridBagConstraints.LINE_START;
        //gbc.weightx=0;
        //gbc.insets=new Insets(0,0,0,0);
        panelDown.add(countp2,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.anchor= GridBagConstraints.CENTER;
        //gbc.weightx=0;
        gbc.insets=new Insets(0,4,0,0);
        panelDown.add(lb1,gbc);
        //gbc.insets=new Insets(0,0,0,0);
        //gbc.weightx=0;
        gbc.anchor= GridBagConstraints.LAST_LINE_END;
        gbc.gridx=3;
        gbc.gridy=0;
        panelDown.add(btn1,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.anchor= GridBagConstraints.CENTER;
        panelUp.add(countp1,gbc);
        gbc.anchor= GridBagConstraints.CENTER;
        gbc.gridx=2;
        gbc.gridy=0;
        panelUp.add(lb2,gbc);


        JPanel panelMain = new JPanel();
        panelMain.setLayout(new GridLayout(2,1));

        panelMain.add(panelUp);
        panelMain.add(panelDown);

        this.getContentPane().add(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setSize(900,900);
        this.setVisible(true);

        Buttons();


    }

    public void Buttons(){

        //this.add(btn1, BorderLayout.SOUTH);


        btn1.addActionListener(new ActionListener() {
            int count=0;
            boolean res = false;
            boolean disp = false;
            int cardCount=1;
            boolean stop=false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (arr1.peekFirst() == null) {
                    countp1.setText("Проиграл");
                    countp2.setText("Выйграл за "+count+" шагов");
                    btn1.setVisible(false);
                    stop = true;
                } else if (arr2.peekFirst() == null) {
                    countp2.setText("Проиграл");
                    countp1.setText("Выйграл за "+count+" шагов");
                    btn1.setVisible(false);
                    stop = true;
                }
                if (count == 106) {
                    countp1.setText("БОТВА!");
                    countp2.setText("БОТВА!");
                    btn1.setVisible(false);
                    stop = true;
                }
                if(!stop) {
                    ImageIcon imageIcon = new ImageIcon(getClass().getResource(arr2.peekLast().getPick()));
                    lb1.setIcon(resize(imageIcon));
                    imageIcon = new ImageIcon(getClass().getResource(arr1.peekLast().getPick()));
                    lb2.setIcon(resize(imageIcon));

                    count++;
                    if (arr1.peekLast().getValue() == arr2.peekLast().getValue()) {
                        dispute.addFirst(arr2.removeLast());
                        dispute.addFirst(arr1.removeLast());
                        countp1.setText("СПОР!  ");
                        countp2.setText("СПОР!  ");
                        disp = true;
                        cardCount = 0;
                    } else if (arr1.peekLast().getValue() > arr2.peekLast().getValue() || ((arr1.peekLast().getValue() == 0) && (arr2.peekLast().getValue() == 9))) {
                        arr1.addFirst(arr1.removeLast());
                        arr1.addFirst(arr2.removeLast());
                        countp1.setText("+" + cardCount + " Карт: " + arr1.size());
                        countp2.setText("-" + cardCount + " Карт: " + arr2.size());
                        if (disp == true) {
                            while (dispute.peekFirst() != null) {
                                arr1.addFirst(dispute.removeLast());
                                cardCount++;
                            }
                            disp = false;
                            countp1.setText("+" + cardCount + " Карт: " + arr1.size());
                            countp2.setText("-" + cardCount + " Карт: " + arr2.size());
                            cardCount = 1;
                        }

                    } else if (arr1.peekLast().getValue() < arr2.peekLast().getValue() || ((arr1.peekLast().getValue() == 9) && (arr2.peekLast().getValue() == 0))) {
                        arr2.addFirst(arr2.removeLast());
                        arr2.addFirst(arr1.removeLast());
                        countp1.setText("-" + cardCount + " Карт: " + arr1.size());
                        countp2.setText("+" + cardCount + " Карт: " + arr2.size());
                        if (disp == true) {
                            while (dispute.peekFirst() != null) {
                                arr2.addFirst(dispute.removeLast());
                                cardCount++;
                            }
                            disp = false;
                            countp1.setText("-" + cardCount + " Карт: " + arr1.size());
                            countp2.setText("+" + cardCount + " Карт: " + arr2.size());
                            cardCount = 1;
                        }

                    }

                }
            }
        });
    }

    public Card[] createDeck(Card[] arr){
        int count=0;
        for (int i = 0; i < 36; i++) {
            arr[i]= new Card();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 4; j++) {
                arr[count].setValue(i);
                arr[count].setSuit(j);
                arr[count].setPick("/picks/"+(i+1)+(j+1)+".png");
                count++;
            }
        }
        return arr;
    }

    public ImageIcon resize(ImageIcon imageIcon){
        Image image = imageIcon.getImage();
        Image imageScale = image.getScaledInstance(200,400,4);
        ImageIcon imgs = new ImageIcon(imageScale);
        return imgs;
    }

    static void shuffleArray(Card[] arr)
    {
        Random rnd = new Random();
        Card[]a=new Card[1];
        a[0]=new Card();
        for (int i = arr.length-1; i >= 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            a[0] = arr[index];
            arr[index] = arr[i];
            arr[i] = a[0];
        }
    }


}
