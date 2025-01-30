package com.studing;

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        BankAccount account = new BankAccount("11245", 500f);
        Scanner scan = new Scanner(System.in);
        System.out.println("a - Пополнить баланс, b - Снять средства, c - Постомтреть баланс");
        System.out.println("Выберите: ");
        char choise = scan.next().charAt(0);
        if(choise != 'a' && choise != 'b' && choise != 'c'){
            System.out.println("Выберите указанные буквы!");
            return;
        }
        if(choise == 'a'){
            System.out.println("Введите сумму пополнения: ");
            double amount = scan.nextDouble();
            account.deposit(amount);
        } else if(choise == 'b'){
            System.out.println("Введите сумму снятия наличных: ");
            double amount = scan.nextDouble();
            account.withdraw(amount);
        } else if(choise == 'c'){
            account.displayInfo();
        }
    }


}