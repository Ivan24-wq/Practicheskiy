package com.Animal;

public class Main{
    public static void main(String[] ars){
        Dog Bobik = new Dog("Бобик", 3, "Доберман");
        System.out.println(Bobik.info());
        System.out.println(Bobik.makeSound());
        System.out.println(Bobik.fetch());


        Cat my = new Cat("Мурка", 2, "Рыжий");
        System.out.println(my.info());
        System.out.println(my.makeSound());
        System.out.println(my.scratch());

        Bird br = new Bird("Попугай", 1, true);
        System.out.println(br.info());
        System.out.println(br.makeSound());
        System.out.println(br.fly());


    }

}