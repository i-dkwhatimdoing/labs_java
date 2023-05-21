package com.kursach.app;


public class INFOComparator implements java.util.Comparator<INFO>{
    /**
     * Определяет способ сравнения двух объектов записей в бд
     * @param a - Первая запись
     * @param b - Вторая запись
     * @return
     */


    @Override
    public int compare(INFO a, INFO b) {
        // Реализация сравнения
        return b.getBrigadenum() - a.getBrigadenum();

    }
}

