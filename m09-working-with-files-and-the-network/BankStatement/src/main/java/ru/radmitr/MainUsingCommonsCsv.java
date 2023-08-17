package ru.radmitr;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.radmitr.pojo.BankAccount;
import ru.radmitr.pojo.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static ru.radmitr.Utility.*;

public class MainUsingCommonsCsv {

    private static final String CSV_FILE_PATH = "src/main/resources/movementList.csv";
    private static final DateTimeFormatter CSV_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy");

    public static void main(String[] args) throws IOException {
        List<Transaction> transactions = loadTransactionsFromFile(CSV_FILE_PATH);

        System.out.println("Сумма доходов: " + transactions.stream()
                .mapToDouble(Transaction::getIncome).sum());
        System.out.println("Сумма расходов: " + transactions.stream()
                .mapToDouble(Transaction::getExpense).sum());
        System.out.println("--------------------------------------------------------");

        System.out.println("Сумма доходов по организациям: ");
        transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getRecipient,
                        TreeMap::new, Collectors.toSet()))
                .entrySet().stream()
                .forEach(group -> {
                    double sum = group.getValue().stream().mapToDouble(Transaction::getIncome).sum();
                    if (sum == 0) {
                        return;
                    }
                    System.out.format("\t %-30s %.2f руб.\n", group.getKey(), sum);
                });

        System.out.println("\nСумма расходов по организациям: ");
        transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getRecipient,
                        TreeMap::new, Collectors.toSet()))
                .entrySet().stream()
                .forEach(group -> {
                    double sum = group.getValue().stream().mapToDouble(Transaction::getExpense).sum();
                    if (sum == 0) {
                        return;
                    }
                    System.out.format("\t %-30s %.2f руб.\n", group.getKey(), sum);
                });
    }

    /**
     * Load transactions from CSV file using commons CSV
     */
    private static List<Transaction> loadTransactionsFromFile(String path) throws IOException {
        List<Transaction> tranList = new ArrayList<>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path), 1000);
            Iterable<CSVRecord> csvRecords = CSVFormat.DEFAULT.builder()
                    .setHeader().setSkipHeaderRecord(true).build().parse(br);

            for (CSVRecord record : csvRecords) {
                tranList.add(new Transaction(
                                new BankAccount(record.get("Номер счета"), record.get("Тип счёта"), record.get("Валюта")),
                                LocalDate.parse(record.get("Дата операции"), CSV_DATE_FORMATTER),
                                record.get("Референс проводки"),
                                record.get("Описание операции"),
                                extractRecipientName(record.get("Описание операции")),
                                getDouble(record.get("Приход")),
                                getDouble(record.get("Расход"))
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            br.close();
        }

        return tranList;
    }
}
