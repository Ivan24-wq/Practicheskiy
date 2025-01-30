package com.studing;

public class BankAccount {
    private String accountNumber;
    private double balance;


    // Инициализация счета
    public BankAccount(String accountNumber, double InitialBalance){
        this.accountNumber = accountNumber;
        this.balance = InitialBalance;
    }

    // Пополнение карты
    public void deposit(double amount){
        if(amount >0){
            balance += amount;
            System.out.print("Баланис пополнен на: " + balance);
        } else {
            System.out.print("Ошибка!");
        }
    }

    // Снфтие денег
    public void withdraw(double amount){
        if(amount > balance){
            System.out.print("Недостаночно средств!");
        } else if(amount > 0){
            balance -= amount;
            System.out.println("Снято с карты: " + balance);
        } else {
            System.out.print("Ошибка!");
        }
    }

    // Номер карты и текущий баланс
    public void displayInfo(){
        System.out.print("Номер карты: " + accountNumber);
        System.out.print("Текущий баланс: " + balance);
    }
}