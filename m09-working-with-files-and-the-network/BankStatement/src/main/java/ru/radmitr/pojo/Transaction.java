package ru.radmitr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@ToString
public class Transaction {

    private BankAccount bankAccount;  // Банковский счет
    private LocalDate date;           // Дата операции
    private String bankReference;     // Референс проводки
    private String description;       // Описание операции
    private String recipient;         // Получатель
    private Double income;            // Приход
    private Double expense;           // Расход
}
