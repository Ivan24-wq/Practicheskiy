package com.Phone;

public class Main{
    public static void main(String[] args){
        Phone phone1 = new Phone("+79781730205", "Redmi 10", 150);
        Phone phone2 = new Phone("+79781730204", "IPhone XS", 300);
        Phone phone3 = new Phone("+79781730206", "Tecno", 400);

        phone1.phoneInfo();
        phone2.phoneInfo();
        phone3.phoneInfo();

        System.out.println("Методы: ");

        phone1.receiveCall("Алексей");
        phone1.receiveCall("Алексей", "+79781730205");


        phone2.receiveCall("Иван");
        phone2.receiveCall("Иван", "+79781730204");


        phone3.receiveCall("Инна: ");
        phone3.receiveCall("Инна: ", "+79781730206");


    }
}