//Создайте массив, после чего найдите наибольший элемент массива
package com.studing;

import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner num = new Scanner(System.in);

        System.out.println("a-наименьшее, b-наибольшее");
        System.out.print("Выберите: ");
        char choise = num.next().charAt(0);
        if(choise != 'a' && choise != 'b'){
            System.out.print("выберите указанные буквы!");
            return;
        }


        System.out.print("Введите количество элементов массива: ");
        int size = num.nextInt();

        if(size <=0){
            System.out.print("Массив долже быть положительным!");
            return;
        }
        System.out.println("Введети элементы: ");
        int [] arr = new int[size];
        for(int i = 0; i < arr.length; i++){

            arr[i] = num.nextInt();
        }

        int res = arr[0];
        if(choise == 'a'){
            for(int i = 0; i < arr.length; i++){
                if(arr[i] > res){
                    res = arr[i];
                }
            }
            System.out.print("Наиболльшее: " + res);
        } else {
            for(int i = 0; i< arr.length; i++){
                if(arr[i] < res){
                    res = arr[i];
                }
            }
            System.out.println("Наименьшее: " + res);
        }
    }
}