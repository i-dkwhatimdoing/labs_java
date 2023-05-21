package com.kursach.app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App extends JFrame {
    // файл с данными
    private static final String FILENAME = "data.bin";

    // --- Вспомогательные поля (UI) ---
    DB db;
    private JPanel mainPanel;
    private JPanel panel1;
    private JPanel Top;
    private JPanel Left;
    private JPanel Right;
    private JPanel firstPanel;
    private JPanel secondPanel;
    private JButton showRecordsButton;
    private JButton addRecordButton;
    private JPanel thirdPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel numberOfPlane;
    private JLabel aircraftBrand;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JLabel destination;
    private JLabel numberOfTicketsSold;
    private JLabel thePriceOfOneTicket;
    private JButton sendButton;
    private JPanel panelWithButton;
    private JButton backButton;
    private JButton exitButton;
    private JPanel otherPanel;
    private JTable table1;
    private JButton otherBackButton;
    private JButton otherExitButton;
    private JButton deleteButton;
    private JButton solve;
    private JLabel helpText;
    private JTextField BrigadeName;
    private JTextField solvePorodaTextField;
    private JTextField solveColorTextField;

    //используются для смены заголовков в таблице
    private static final String[] headersMain= {"Фамилия", "Год", "Бригада", "Специальность", "Оклад"};
    // Заголовки таблицы стандартные

    private static final String[] headersSecond = {"Фамилия", "Год рождения"};// Заголовки для решения задачи

    /**
     * создаём объекб базы данных и запускаем графику
     * @throws IOException
     */
    public App() throws IOException {
        db = new DB(FILENAME);
        this.getContentPane().add(mainPanel);
    }

    /**
     * Запуск приложения
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.createAndShowGUI();
        app.pack();
    }


    /**
     * запускает графический интерфейс и привязывает функции к кнопкам
     */
    public void createAndShowGUI() {
        this.setTitle("БД для «Разгуляй»");
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addRecordButton.addActionListener(this::addRecordButtonClick);
        showRecordsButton.addActionListener(this::showRecordsButtonClick);
        sendButton.addActionListener(this::sendButtonClick);
        backButton.addActionListener(this::backButtonClick);
        exitButton.addActionListener(this::exitButtonClick);
        otherBackButton.addActionListener(this::backButtonClick);
        otherExitButton.addActionListener(this::exitButtonClick);
        deleteButton.addActionListener(this::deleteSelectedRow);
        solve.addActionListener(this::solveProblem);
        //solve.hide();
    }

    /**
     * Решает практическую задачу
     * @param e
     */
    private void solveProblem(ActionEvent e) {

    //Считывание данных в переменные
        int Brigada;
        int young = -1;
        int old = 10000000;
        try {
            Brigada = Integer.parseInt(BrigadeName.getText().trim());
        } catch (final NumberFormatException error) {
            BrigadeName.setText("Ошибка");
            return;
        }


        // Получаем все записи и сортируем их
        List<INFO> records = db.getInfoList();
        List<INFO> sortedList = new ArrayList<INFO>();
        // Здесь происходит решение задачи
        String surname = "";
        String surname_1 = "";
        for(INFO looked : records)
        {
            if(Brigada == looked.getBrigadenum()) {
                if (young < looked.getYear()) {
                    young = looked.getYear();
                    surname = looked.getSurname();
                }
                if (old > looked.getYear()) {
                    old = looked.getYear();
                    surname_1 = looked.getSurname();
                }
            }
        }
        if(young == -1 || old == 10000000)
        {
            BrigadeName.setText("Ошибка");
            return;
        }
        // --- конец решения ---

        // Настраиваем заголовки и чистим таблицу
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setColumnIdentifiers(headersSecond);
        model.setRowCount(0);

        // Заполняем таблицу элементами из массива

            model.addRow(new Object[]
                    {
                            surname_1,
                            old
                    }
            );
            model.addRow(new Object[]
                    {
                        surname,
                        young
                    }
            );

        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // авторазмер для колонок

        firstPanel.setVisible(false);
        thirdPanel.setVisible(true);
        this.pack();
    }

    /**
     * Удаляет выбранную запись из бд
     * @param e
     */
    private void deleteSelectedRow(ActionEvent e) {
        if (table1.getSelectedRow() != -1) {
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            int recordId = table1.getSelectedRow();
            model.removeRow(recordId);
            db.removeRowByIndex(recordId);

        }
    }

    public void addRecordButtonClick(ActionEvent e) {
        firstPanel.setVisible(false);
        secondPanel.setVisible(true);
        otherPanel.setVisible(false);
        panelWithButton.setVisible(true);
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        this.pack();
    }

    /**
     * Выводит на экран все записи таблицы
     * @param e
     */
    public void showRecordsButtonClick(ActionEvent e) {
        firstPanel.setVisible(false);
        thirdPanel.setVisible(true);
        this.pack();

        // Получаем все записи и сортируем их
        List<INFO> sortedList = db.getInfoList();
        sortedList.sort(new INFOComparator());

        // Насраиваем заголовки и чистим таблицу
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setColumnIdentifiers(headersMain);
        model.setRowCount(0);

        // Заполняем таблицу каждым элементом из массива
        for (INFO record : sortedList) {
            model.addRow(new Object[]
                    {
                            record.getSurname(),
                            record.getYear(),
                            record.getBrigadenum(),
                            record.getSpeciality(),
                            record.getSalary()
                    }
            );
        }
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // авторазмер для колонок
    }

    /**
     * При нажатии кнопкисоздаётся новая запись в бд
     * @param e
     */
    public void sendButtonClick(ActionEvent e) {

        if (textField1.getText().isEmpty() || textField2.getText().isEmpty() || textField3.getText().isEmpty() || textField4.getText().isEmpty() || textField5.getText().isEmpty()) {
            System.err.println("Поля пустые");
        } else {
            panelWithButton.setVisible(false);
            otherPanel.setVisible(true);
            this.pack();

            // Использует второй конструктор для конвертации в нужные типы данных
            INFO record = new INFO(
                    textField1.getText().trim(),
                    textField2.getText().trim(),
                    textField3.getText().trim(),
                    textField4.getText().trim(),
                    textField5.getText().trim()
            );
            db.addNewRecord(record); // добавить
            db.saveAllData(); // сохранить

            panelWithButton.setVisible(false);
            otherPanel.setVisible(true);
            this.pack();
        }
    }

    /**
     * Выход и таблицы с сохранением в файл
     * @param e
     */
    public void backButtonClick(ActionEvent e) {
        firstPanel.setVisible(true);
        secondPanel.setVisible(false);
        thirdPanel.setVisible(false);
        db.saveAllData();
        this.pack();
    }

    /**
     * Выход из приложения с сохранением в файл
     * @param e
     */
    public void exitButtonClick(ActionEvent e) {
        db.saveAllData();
        System.exit(0);
    }

    /**
     * Вспомогательная функция для создания заголовков таблицы и задания размеров
     */
    private void createUIComponents() {
        DefaultTableModel model = new DefaultTableModel();
        table1 = new JTable(model) {
            @Override
            public Dimension getPreferredScrollableViewportSize() {
                return new Dimension(super.getPreferredSize().width,
                        getRowHeight() * getRowCount());
            }
        };
        model.setColumnIdentifiers(headersMain);
    }


}
