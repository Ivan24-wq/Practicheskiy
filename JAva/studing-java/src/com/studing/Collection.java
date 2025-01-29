/* Напишите программу на Java, которая:

Считывает произвольное количество чисел (целых или с плавающей точкой) от пользователя до тех пор, пока не будет введено ключевое слово "stop".
Сохраняет эти числа в коллекцию ArrayList.
После завершения ввода программа должна:
Найти и вывести максимальное и минимальное число из коллекции.
Найти и вывести сумму всех чисел.
Найти и вывести среднее значение всех чисел с точностью до двух знаков после запятой.*/

package com.studing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        ArrayList<Double> nums = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите числа (введите 'stop' для завершения):");

        while(true) {
            String input = scan.nextLine();
            if (input.equalsIgnoreCase("stop")) {
                break;
            }
            try {
                double num = Double.parseDouble(input);
                nums.add(num);
            } catch (NumberFormatException e) {
                System.out.print("Введите коррректое число!");
            }
        }
        if(nums.isEmpty()){
            System.out.print("Вы не ввели не одного числа!");
        } else{
            double sum = 0;
            for(double num : nums){
                sum += num;
            }


            double res = sum / nums.size();
            res = Math.round(res * 100) / 100.0;
            double max = Collections.max(nums);
            double min = Collections.min(nums);
            System.out.println("Максимальное: " + max);
            System.out.println("Минимальное: " + min);
            System.out.println("Сумма: " + sum);
            System.out.println("Среднее: " + res);
        }

    }
}