package com.Animal;

public class Bird extends Animal{
    public boolean canFly;

    public Bird(String name, int age, boolean canFky){
        super(name, age);
        this.canFly = canFky;

    }

    public String makeSound(){
        return "Черик-черик";
    }
    public String fly(){
        if(canFly){
            return "Птица летит";
        } else{
            return "птица не летит";
        }
    }
}
