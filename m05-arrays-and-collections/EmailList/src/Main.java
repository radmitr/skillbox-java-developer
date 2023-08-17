import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Task 5.3
public class Main {

    /**
     * Паттерн для проверки введённой команды. Корректность email
     * адреса не проверяется. Проверяется только наличие символов после
     * команды и пробела
     */
    private static final Pattern INPUT_PATTERN = Pattern.compile("^(?i)"
            + "(?<command>list|add|save|load|help|exit)"
            + "(?:\\s+(?<email>.+))?$");

    /**
     * Паттерн для проверки адреса электронной почты (email)
     * -------------------------------------------------------------------------
     * Максимальная длина всего адреса электронной почты
     * (вместе с символом '@') равна 256 символов.
     * -------------------------------------------------------------------------
     * В локальной части адреса электронной почты (local-part)
     * накладываются следующие ограничения:
     * - Для локальной части разрешено не более 64 символов.
     * - Допускаются как прописные, так и строчные буквы от A до Z.
     * - Допускаются числовые значения от 0 до 9.
     * - Допускаются подчеркивание «_», дефис «-» и точка «.».
     * - Точка «.» не допускается в начале и в конце локальной части.
     * - Подчеркивание «_» и дефис «-» не допускаются в начале локальной части.
     * - Последовательные точки не допускаются.
     * ----------------------------------------------------------------------------
     * В доменной части адреса электронной почты (domain)
     * накладываются следующие ограничения:
     * - Для доменной части разрешено не более 253 символов.
     * - Допускаются как прописные, так и строчные буквы от A до Z.
     * - Допускаются числовые значения от 0 до 9.
     * - Подчеркивание «_», дефис «-» и точка «.» не допускаются в начале и в
     *   конце доменной части.
     * - Последовательные точки не допускаются.
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(?=.{6,256}$)(?=.{1,64}@)"
            + "(?<localpart>[A-Za-z\\d][A-Za-z\\d_-]*(?:\\.[A-Za-z\\d_-]+)*)"
            + "@(?=.{4,253}$)"
            + "(?<domain>[A-Za-z\\d][A-Za-z\\d-]+(?:\\.[A-Za-z\\d-]+)*(?:\\.[A-Za-z]{2,}))$");

    /** Множество email адресов */
    private static TreeSet<String> emailSet = new TreeSet<>();

    /** Имя файля для сохранения и загрузки email адресов */
    private static final String FILE_NAME = "email_list.txt";

    /** Строка приветсвия */
    private static final String GREETING = "/".repeat(15)
            + " Добро пожаловать в приложение \"EmailList\" "
            + "\\".repeat(15);

    /** Строка прощания */
    private static final String FAREWELL = "\\".repeat(20)
            + " Выход из приложения \"EmailList\" "
            + "/".repeat(20);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(GREETING);
        System.out.println("Введите команду:");
        while (true) {
            String input = "";
            try {
                input = scanner.nextLine().trim();
            } catch (NoSuchElementException e) {
                // after Ctrl+D
                System.out.println(FAREWELL);
                System.exit(0);
            }

            Matcher matcher = INPUT_PATTERN.matcher(input);
            if (matcher.find()) {
                String command = matcher.group("command").toLowerCase();
                String email = matcher.group("email");
                switch (command) {
                    case ("list") -> list();
                    case ("add") -> add(email);
                    case ("save") -> save();
                    case ("load") -> load();
                    case ("help") -> help();
                    case ("exit") -> {
                        System.out.println(FAREWELL);
                        System.exit(0);
                    }
                    default -> System.out.println("Введена недопустимая команда."
                            + " Введите 'help' для просмотра списка всех возможных команд:");
                }
            } else {
                System.out.println("Введена недопустимая команда."
                        + " Введите 'help' для просмотра списка всех возможных команд:");
            }
        }
    }

    private static void list() {
        if (emailSet.size() > 0) {
            System.out.println("------------------------------------------------------");
            for (String email : emailSet) {
                System.out.println(email);
            }
            System.out.println("------------------------------------------------------");
        } else {
            System.out.println("Список email адресов пустой."
                    + " Чтобы добавить email, введите 'add [email_адрес]':");
        }
    }

    private static void add(String email) {
        if (email != null && !email.isBlank()) {
            if (emailSet.contains(email.trim())) {
                System.out.println("Этот email адрес уже вводился ранее. Добавьте новый email:");
            } else {
                if (EMAIL_PATTERN.matcher(email).find()) {
                    emailSet.add(email.toLowerCase());
                    System.out.println("Email aдрес добавлен");
                } else {
                    System.out.println("Введён некорректный email адрес. Добавьте новый email:");
                }
            }
        } else if (email == null) {
            System.out.println("В комманде отсутсвует email адрес."
                    + "Введите команду 'add [email_адрес]':");
        }
    }

    private static void save() {
        if (emailSet.size() > 0) {
            try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
                for (String s : emailSet) {
                    writer.write(s + "\r\n");
                }
                System.out.printf("Список email адресов сохранён в файл '%s'\n", FILE_NAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Нет информации для сохранения");
        }
    }

    private static void load() {
        File file = new File(FILE_NAME);
        if (file.exists() && file.isFile() && file.length() != 0) {
            try (FileReader reader = new FileReader(file);
                 Scanner scanner = new Scanner(reader)) {
                emailSet.clear();
                while (scanner.hasNextLine()) {
                    emailSet.add(scanner.nextLine());
                }
                System.out.printf("Список email адресов загружен из файла '%s'\n", FILE_NAME);
                list();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Нет информации для загрузки");
        }
    }

    private static void help() {
        System.out.println(String.format("%69s", "").replace(' ', '='));
        System.out.printf("%-20s%s\n", "list", "Вывести список всех email адресов");
        System.out.printf("%-20s%s\n", "add [email_адрес]", "Добавить email адрес");
        System.out.printf("%-20s%s '%s'\n", "save", "Сохранить email адреса в файл", FILE_NAME);
        System.out.printf("%-20s%s '%s'\n", "load", "Загрузить email адреса из файла", FILE_NAME);
        System.out.printf("%-20s%s\n", "help", "Вывести список всех команд");
        System.out.printf("%-20s%s\n", "exit", "Завершить работу приложения");
        System.out.println(String.format("%69s", "").replace(' ', '-'));
        System.out.println("Примеры:");
        System.out.println("  list");
        System.out.println("  add hello@skillbox.ru");
        System.out.println("  save");
        System.out.println("  load");
        System.out.println("  help");
        System.out.println("  exit");
        System.out.println(String.format("%69s", "").replace(' ', '='));
        System.out.println("Введите команду:");
    }
}
