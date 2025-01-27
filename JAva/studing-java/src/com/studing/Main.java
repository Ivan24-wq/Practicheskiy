package com.studing;
import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите первое число: ");
        int num1 = scan.nextInt();

        System.out.println("Введите второе число: ");
        int num2 = scan.nextInt();

        System.out.println("Действие: ");
        String num = scan.next();
        int res;
        // Калькулятор
        switch(num){
            case "+":
                res = num1 + num2;
                System.out.println("Ответ: " + res);
                break;
            case "-":
                res = num1 - num2;
                System.out.println("Ответ: " + res);
                break;
            case "*":
                res = num1 * num2;
                System.out.println("Ответ: " + res);
                break;
            case "/":
                if(num2 == 0){
                    System.out.println("ОШИБКА! На 0 делить нельзя!");
                }  else{
                    double result = (double) num1 / num2;
                    System.out.println("Ответ: " + result);
                    break;
                }
            default:
                System.out.println("Повторите ввод!");
        }
    }
}