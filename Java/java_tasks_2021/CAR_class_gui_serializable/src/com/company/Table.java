package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class Table extends Main {
    //String [][] data;
    String [] names= new String[]{"Рег. номер","Год выпуска","Пробег","Цена"};
    Car[] arr;
    JPanel west = new JPanel();
    JPanel center = new JPanel();
    JPanel center2 = new JPanel();
    JPanel center2L = new JPanel();
    JPanel center2R = new JPanel();
    JButton btn0 = new JButton();
    JButton btn1 = new JButton();
    JButton btn2 = new JButton();
    JButton btn3 = new JButton();
    JButton btn4 = new JButton();
    //JButton btn5 = new JButton();
    JTable table;
    JScrollPane jsp;
    DefaultTableModel model;
    ListSelectionModel SelModel;
    JLabel lb1 = new JLabel();
    JLabel lb2 = new JLabel();
    JLabel lbR = new JLabel();
    JLabel lbY = new JLabel();
    JLabel lbM = new JLabel();
    JLabel lbP = new JLabel();

    JTextField txt= new JTextField(10);
    JTextField txtR= new JTextField(15);
    JTextField txtY= new JTextField(15);
    JTextField txtM= new JTextField(15);
    JTextField txtP= new JTextField(15);

    public Table(Car[]arr){
        this.arr=arr;
        model = new DefaultTableModel(names,arr.length);
        west.setLayout(new GridLayout(6,1));
        west.add(btn0);
        west.add(btn1);
        west.add(btn2);
        //west.add(btn3);
        //west.add(btn4);
        west.add(new JLabel(""));
        west.add(new JLabel(""));
        //west.add(btn5);

        table = new JTable(model);
        jsp = new JScrollPane(table);

        //center2.setLayout(new FlowLayout());
        lb2.setText("Введите номер автомобиля для поиска");
        lbR.setText("Рег. номер");
        lbY.setText("Год выпуска");
        lbM.setText("Пробег");
        lbP.setText("Цена");
        center2L.setLayout(new BoxLayout(center2L,BoxLayout.Y_AXIS));
        center2L.add(Box.createRigidArea(new Dimension(0,10)));
        center2L.add(Box.createVerticalGlue());
        center2L.add(lb2);
        center2L.add(txt);
        center2L.add(btn3);
        center2R.setLayout(new BoxLayout(center2R,BoxLayout.Y_AXIS));
        center2R.add(Box.createRigidArea(new Dimension(0,5)));
        center2R.add(lbR);
        center2R.add(txtR);
        center2R.add(lbY);
        center2R.add(txtY);
        center2R.add(lbM);
        center2R.add(txtM);
        center2R.add(lbP);
        center2R.add(txtP);
        center2R.add(btn4);
        center2.add(center2L, BorderLayout.WEST);
        center2.add(center2R, BorderLayout.EAST);

        center.setLayout(new BorderLayout());
        lb1.setText("Список всех автомобилей");
        center.add(lb1,BorderLayout.NORTH);
        center.add(jsp,BorderLayout.CENTER);
        center.add(center2,BorderLayout.SOUTH);
        this.getContentPane().add(west,BorderLayout.WEST);
        this.add(center,BorderLayout.CENTER);
        this.setSize(1050,700);
        SelModel = table.getSelectionModel();
        drawFrame(this.arr,0);
        chooseMode();
        rowSelect();
        this.setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    FileOutputStream fileOutputStream= new FileOutputStream("carData");
                    ObjectOutputStream objectOutputStream= new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(arr);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }



            }
        });
        //this.pack();
    }

    public void drawFrame(Car[]arr, int mode){
       if(mode==1) {
           lb1.setVisible(false);
       } else lb1.setVisible(true);
       rmAllrows();
       addRow(arr);
       //table.s
    }



    public void chooseMode(){
        btn0.setText("Список всех автомобилей");
        btn0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawFrame(arr,0);
                clearInfo();
            }
        });
        btn1.setText("Самый дешёвый автомобиль");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawFrame(cheap(arr),1);
                clearInfo();
            }

        });
        btn2.setText("Отсортировать автомобили по году выпуска");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Car[] cars = new Car[arr.length];
                for (int i = 0; i < arr.length; i++) {
                    cars[i]= new Car(arr[i].getReg(),arr[i].getYear(),arr[i].getMil(),arr[i].getPrice());

                }

                Arrays.sort(cars);
                drawFrame(cars,1);
                clearInfo();

            }
        });
        btn3.setText("Найти !");
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                drawFrame(findCar(arr),1);
                clearInfo();



            }
        });
        btn4.setText("Внесение изменений в информацию об автомобиле");
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = table.getSelectedRow();
                if(a<=-1)return;
                int check=0;
                if(txtR.getText().equals("")){
                    arr[a].setReg("-");
                    table.setValueAt("-",a,0);
                    check++;
                }
                if(txtY.getText().equals("")){
                    arr[a].setYear(0);
                    table.setValueAt("0",a,1);
                    check++;
                }
                if(txtM.getText().equals("")){
                    arr[a].setMil(0);
                    table.setValueAt("0",a,2);
                    check++;
                }
                if(txtP.getText().equals("")){
                    arr[a].setPrice(0);
                    table.setValueAt("0",a,3);
                    check++;
                }
                if(check != 0)return;

                table.setValueAt(txtR.getText(),a,0);
                table.setValueAt(txtY.getText(),a,1);
                table.setValueAt(txtM.getText(),a,2);
                table.setValueAt(txtP.getText(),a,3);

                arr[a].setReg((String) model.getValueAt(a,0));
                arr[a].setYear(Integer.parseInt((String)model.getValueAt(a,1)) );
                arr[a].setMil(Integer.parseInt((String)model.getValueAt(a,2)));
                arr[a].setPrice(Integer.parseInt((String)model.getValueAt(a,3)));

                //arr[a].setReg(txtR.getText());
                //arr[a].setYear(Integer.parseInt(txtY.getText()));
                //arr[a].setMil(Integer.parseInt(txtM.getText()));
                //arr[a].setPrice(Integer.parseInt(txtP.getText()));


                //drawFrame(arr,0);
            }
        });
        /*
        btn5.setText("Выход ");
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
        */

        //this.pack();

    }

    public void rowSelect(){
        SelModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int a = table.getSelectedRow();
                if(a<=-1)return;
                txtR.setText((String) model.getValueAt(a,0));
                txtY.setText((String) model.getValueAt(a,1));
                txtM.setText((String) model.getValueAt(a,2));
                txtP.setText((String) model.getValueAt(a,3));
            }
        });
    }

    public void rmRow(int i){
        model.removeRow(i);
    }
    public void rmAllrows(){
        int a = model.getRowCount();
        for (int i=0; i < a; i++) {
            rmRow(0);
        }
    }
    private  void addRow(Car[] arr){
        String[] strings = new String[4];
        for(int i=0;i < arr.length;i++){
            strings[0]=arr[i].getReg();
            strings[1]= String.valueOf(arr[i].getYear());
            strings[2]= String.valueOf(arr[i].getMil());
            strings[3]= String.valueOf(arr[i].getPrice());
            model.addRow(strings);
        }
    }
    public Car[] findCar(Car[] arr){
        String regnum= this.txt.getText();
        Car[] res = new Car[1];
        res[0]= new Car("Не найден",0,0,0);
        for(int i=0;i < arr.length;i++){
            if(arr[i].getReg().equals(regnum)) {
                res[0] = arr[i];
                return res;
            }
        }
        return res;
    }

    public void clearInfo(){
        txtR.setText("");
        txtY.setText("");
        txtM.setText("");
        txtP.setText("");
    }



}
