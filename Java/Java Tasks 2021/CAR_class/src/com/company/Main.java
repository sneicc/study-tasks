package com.company;
import javax.sound.midi.SysexMessage;
import java.util.Arrays;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
	    Car[] arr = new Car[5];


        arr[0] = new Car("А223БВ777", 2020, 500,  5500000);
        arr[1] = new Car("П623БВ747", 2001, 3500, 1000000);
        arr[2] = new Car("А126БВ277", 1995, 5200, 3600000);
        arr[3] = new Car("Б666АН666", 2008, 100, 6300000);
        arr[4] = new Car("А173БВ177", 2019, 400, 410000);

        Scanner sc = new Scanner(System.in);
        boolean stop = false;
        while(!stop) {
            System.out.println("---------Выбор режим работы---------");
            System.out.println("1 - Найти самый дешёвый автомобиль ");
            System.out.println("2 - Отсортировать автомобили по году выпуска ");
            System.out.println("3 - Поиск автомобиля по номеру ");
            System.out.println("4 - Внесение изменений в информацию об автомобиле");
            System.out.println("0 - Выход");
            int mode = sc.nextInt();
            if(mode == 1){
                cheap(arr);
                System.out.println();
            } else if(mode == 2) {
                Arrays.sort(arr);
                for (Car e : arr) {
                    System.out.println(e);
                }
                System.out.println();
            } else if(mode == 3){
                System.out.println();
                findCar(arr);
            }else if(mode == 4){
                System.out.println();
                change(arr);
            }else if(mode == 0){
                stop = true;
            }else{
                System.out.println("Введите корректный номер режима работы");
            }
        }
    }
    public static void cheap(Car[] arr){
        int a=0;
        for(int i =0; i < arr.length; i++){
            if(arr[i].getPrice() < arr[a].getPrice()) a = i;
        }
        System.out.println("Самый дешевый автомобиль - ");
        System.out.println(arr[a]);
    }
    public static void findCar(Car[] arr){
        System.out.println("Введите номер автомобиля");
        Scanner Ssc = new Scanner(System.in);
        String regnum = Ssc.nextLine();
        boolean check = false;
        while(!check) {
            if (regnum.length() != 9) {
                System.out.println("Введите корректный номер автомобиля");
                regnum = Ssc.nextLine();
            } else check = true;
        }
        for(Car e: arr){
            if(e.getReg().equals(regnum)) {
                System.out.println(e);
                return;
            }
        }
        System.out.println("Номер не найден");
    }
    public static void change(Car[] arr){
        int i =1;
        for (Car e : arr) {
            System.out.print(i+" ");
            System.out.println(e);
            i++;
        }
        System.out.println("Выбор автомобиля");
        Scanner sc = new Scanner(System.in);
        Scanner Ssc = new Scanner(System.in);
        int num = sc.nextInt() -1;
        System.out.println();
        System.out.println(arr[num]);
        System.out.println("---------Выбор поля для изменения---------");
        System.out.println("1 - номер");
        System.out.println("2 - год производства");
        System.out.println("3 - пробег");
        System.out.println("4 - стоимость");
        int num2 = sc.nextInt();
        if(num2 == 1){
            System.out.println("Введите новый номер");
            String reg = Ssc.nextLine();
            boolean check = false;
            while(!check) {
                if (reg.length() != 9) {
                    System.out.println("Введите корректный номер автомобиля");
                    reg = sc.nextLine();
                } else check = true;
            }
            arr[num].setReg(reg);
        }else if(num2 == 2){
            System.out.println("Введите год производства");
            int year =sc.nextInt();
            arr[num].setYear(year);
        }else if(num2 == 3){
            System.out.println("Введите пробег");
            int mil =sc.nextInt();
            arr[num].setMil(mil);
        }else if(num2 == 4){
            System.out.println("Введите стоимость");
            int price =sc.nextInt();
            arr[num].setPrice(price);
        }
        System.out.println(arr[num]);
    }
}
