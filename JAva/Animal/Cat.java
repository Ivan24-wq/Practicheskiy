package com.Animal;

public class Cat extends Animal{
    public String color;

    public Cat(String name, int age, String color){
        super(name, age);
        this.color = color;

    }
    public String makeSound(){
        return "Мяу";
    }
    public String scratch(){
        return "Царапает мебель!";
    }

}
