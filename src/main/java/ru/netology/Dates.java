package ru.netology;

import java.time.format.DateTimeFormatter;

public class Dates {

    public String generateDate(int addDays, String pattern) {
        //Находим текущую дату, после чего добавляем необходимое количество дней, задаем формат даты, получаем переменную с минимальновозможной датой доставки
        java.time.LocalDate generatingDate = java.time.LocalDate.now().plusDays(addDays);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String foramatedDate = generatingDate.format(formatter);

        return foramatedDate;
    }

    //Если понадобится текущая дата
    public String getNow(String pattern) {
        java.time.LocalDate deliveryDateCard = java.time.LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String foramatedDate = deliveryDateCard.format(formatter);

        return foramatedDate;
    }
}
