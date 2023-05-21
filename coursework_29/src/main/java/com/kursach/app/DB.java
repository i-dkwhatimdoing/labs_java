package com.kursach.app;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DB {

    private final File file;// Файл для сохранения

    private List<INFO> infoList;// Хранит в себе текущий список всех записей

    /**
     * Инициализация БД пустым файлом и пустым массивом
     * @param fileName
     * @throws IOException
     */
    public DB(String fileName) throws IOException {
        this.infoList = new ArrayList<>();
        File file =  new File(fileName);
        if (!file.exists()) file.createNewFile();
        this.file = file;
        this.readAllInObject();
    }

    /**
     * получить список всех записей
     */
    public  List<INFO> getInfoList(){ return this.infoList;}

    /**
     * Добавить одну запись
     * @param record - объект записи
     */
    public void addNewRecord(INFO record){
        this.infoList.add(record);
    }

    /**
     * Читает из файла все записи БД в объект массива
     */
    public void readAllInObject() {

        try (FileInputStream streamIn = new FileInputStream(this.file); ObjectInputStream objectinputstream = new ObjectInputStream(streamIn)) {
            this.infoList = (List<INFO>) objectinputstream.readObject();
        } catch (ClassNotFoundException | EOFException e ) {
            System.err.println("Error: nothing in DB");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сохраняет в файл все записи БД из объекта массива
     */
    public void saveAllData() {
        try (FileOutputStream streamIn = new FileOutputStream(this.file); ObjectOutputStream objectoutputstream = new ObjectOutputStream(streamIn)) {
            objectoutputstream.writeObject(this.infoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Удаления записи из бд
     * @param index - индекс записи в массиве
     */
    public void removeRowByIndex(int index) {infoList.remove(index);}

}
