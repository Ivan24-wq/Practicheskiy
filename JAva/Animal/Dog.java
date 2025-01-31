package com.Animal;

public class Dog extends Animal{
    public String breed;

    public Dog(String name, int age, String breed){
        super(name, age);
        this.breed = breed;
    }

    public String makeSound(){
        return "Гав гав";
    }

    public String fetch(){
        return "Cобака приносит мяч";
    }
}

