package com.kursach.app;

import java.io.Serializable;

public class INFO implements Serializable {

    private String surname;
    private int year;
    private int brigadenum;
    private String speciality;
    private double salary;

    public INFO(String surname, int year, int brigadenum, String speciality, double salary) {
        this.surname = surname;
        this.year = year;
        this.brigadenum = brigadenum;
        this.speciality = speciality;
        this.salary = salary;
    }

    /**
     * используется для конвертации полностью текстовых данных в объект класса
     */
    public String getSurname() {
        return surname;
    }

    public int getYear() {
        return year;
    }

    public int getBrigadenum() {
        return brigadenum;
    }

    public String getSpeciality() {
        return speciality;
    }

    public double getSalary() {
        return salary;
    }

    public INFO(String surname, String year, String brigadenum, String speciality, String salary) {
        this.surname = surname;
        this.year = Integer.parseInt(year);
        this.brigadenum = Integer.parseInt(brigadenum);
        this.speciality = speciality;
        this.salary = Double.parseDouble(salary);
    }
}