package ru.radmitr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class BankAccount {

    private String id;       // Номер счета
    private String type;     // Тип счёта
    private String currency; // Валюта
}
