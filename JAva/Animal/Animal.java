package com.Animal;

public class Animal {
    public String name;
    public int age;

    public Animal(String name, int age){
        this.name = name;
        this.age = age;
    }
    public String makeSound(){
        return "Издаёт звук";
    }

    public String info(){
        return "Имя: " + name + " Возраст: " + age;
    }


}
