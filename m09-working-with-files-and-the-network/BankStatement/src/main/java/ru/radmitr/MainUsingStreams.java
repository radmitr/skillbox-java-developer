package ru.radmitr;

import ru.radmitr.pojo.BankAccount;
import ru.radmitr.pojo.Transaction;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static ru.radmitr.Utility.extractRecipientName;

public class MainUsingStreams {

    private static final String CSV_FILE_PATH = "src/main/resources/movementList.csv";
    private static final DateTimeFormatter CSV_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy");

    public static void main(String[] args) {
        ArrayList<Transaction> transactions = loadTransactionsFromFile(CSV_FILE_PATH);

        System.out.println("Сумма доходов: " + transactions.stream()
                .mapToDouble(Transaction::getIncome).sum());
        System.out.println("Сумма расходов: " + transactions.stream()
                .mapToDouble(Transaction::getExpense).sum());
        System.out.println("--------------------------------------------------------");

        TreeSet<String> recipients = transactions.stream()
                .map(Transaction::getRecipient)
                .collect(Collectors.toCollection(TreeSet::new));

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
     * Load transactions from CSV file
     */
    private static ArrayList<Transaction> loadTransactionsFromFile(String path) {
        ArrayList<Transaction> tranList = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.remove(0);
            for (String line : lines) {
                // in numbers, replace the comma with a dot and divide the string into fragments by comma
                String[] columnArr = line.replaceAll("\"([\\d&&[^\"]]*),([\\d&&[^\"]]+)\"", "$1.$2")
                        .split(",");
                if (columnArr.length != 8) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                tranList.add(new Transaction(
                                new BankAccount(columnArr[1], columnArr[0], columnArr[2]),
                                LocalDate.parse(columnArr[3], CSV_DATE_FORMATTER),
                                columnArr[4],
                                columnArr[5],
                                extractRecipientName(columnArr[5]),
                                Double.parseDouble(columnArr[6]),
                                Double.parseDouble(columnArr[7])
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tranList;
    }
}
