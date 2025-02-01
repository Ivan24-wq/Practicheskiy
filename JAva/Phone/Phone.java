package com.Phone;

public class Phone {
    public String number;
    public String model;
    public double weight;


    public Phone(String number, String  model, double weight){
        this.number = number;
        this.model = model;
        this.weight = weight;

    }

    public void phoneInfo(){
        System.out.println(" Номер телефона: " + number);
        System.out.println(" Модель:  " + model);
        System.out.println(" Вес " + weight + " г");
    }

    public void receiveCall(String name){
        System.out.println("Звонит: " + name);
    }
    public void receiveCall(String name, String number){
        System.out.print("Звонит: " + name + " Номер:" + number);
    }


}
