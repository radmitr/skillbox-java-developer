import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

// Task 5.4
public class Main {

    /** Паттерн для проверки вводимых команд */
    private static final Pattern COMMAND_PATTERN = Pattern.compile("^(?i)list|save|load|help|exit$");

    /** Паттерн для проверки ФИО */
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Zа-яА-ЯёЁ -]{2,}$");

    /**
     * Паттерн для проверки мобильных и городских номеров России и СНГ
     * ----------------------
     * Страны:
     *
     * Армения [+374],
     * Азербайджан [+994]
     * Грузия [+995]
     * Беларусь [+375]
     * Россия/Казахстан [+7]
     * Украина [+380, +38]
     * Киргизия [+996]
     * Узбекистан [+998]
     * Туркмения [+993]
     * Узбекистан [+998]
     * ----------------------
     * Проходят номера:
     *
     * +7(999)000-00-00
     * 8 495 753 00 00
     * +7 844 57-123-45
     * +79781234567
     * 9781234567
     * 89781234567
     * +380 926 123 45 67
     */
    private static final Pattern MOBILE_PHONE_PATTERN = Pattern.compile(
            "^(?<country>8|\\+374|\\+994|\\+995|\\+375|\\+?7|\\+380|\\+38|\\+996|\\+998|\\+993[\\- ]?)?"
                    + "(?<operator>\\(?\\d{3}\\)?)[\\- ]?"
                    + "(?<number>(?:\\d[\\- ]?){6}\\d)$");

    /**
     * Карта телефонной книги
     *
     * ФИО : Номер телефона
     */
    private static Map<String, String> nameToPhoneMap = new HashMap<>();

    /**
     * Зеркальная карта телефонной книги
     *
     * Номер телефона : ФИО
     */
    private static Map<String, String> phoneToNameMap = new HashMap<>();

    /** Имя файля для сохранения и загрузки адресной киги */
    private static final String FILE_NAME = "phone_book.txt";

    /** Строка приветсвия */
    private static final String GREETING = "\\".repeat(15)
            + " Добро пожаловать в приложение \"PhoneBook\" "
            + "/".repeat(15);

    /** Строка прощания */
    private static final String FAREWELL = "/".repeat(20)
            + " Выход из приложения \"PhoneBook\" "
            + "\\".repeat(20);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(GREETING);
        while (true) {
            System.out.println("\nВведите ФИО, или номер телефона, или команду:");
            String input = "";
            try {
                input = scanner.nextLine().trim();
            } catch (NoSuchElementException e) {
                // after Ctrl+D
                System.out.println(FAREWELL);
                System.exit(0);
            }

            if (isCommand(input)) {
                switch (input.toLowerCase()) {
                    case ("list") -> list();
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
            } else if (isName(input)) {
                // проверяем ФИО на наличие
                String name = input;
                if (nameToPhoneMap.containsKey(name)) {
                    System.out.println("Контакт существует");
                    System.out.println("------------------------------------------------------");
                    System.out.println(name + " : " + nameToPhoneMap.get(name));
                    System.out.println("------------------------------------------------------");
                    continue;
                }

                // вводим номер телефона
                System.out.println("Введите номер телефона:");
                String phone = "";
                try {
                    phone = scanner.nextLine().trim();
                    phone = normalizePhone(phone);
                } catch (NoSuchElementException e) {
                    // after Ctrl+D
                    System.out.println(FAREWELL);
                    System.exit(0);
                }

                // проверяем номер и вносим контакт
                if (isPhone(phone)) {
                    // проверяем номер телефона на наличие
                    if (phoneToNameMap.containsKey(phone)) {
                        System.out.println("Контакт существует");
                        System.out.println("------------------------------------------------------");
                        System.out.println(phoneToNameMap.get(phone) + " : " + phone);
                        System.out.println("------------------------------------------------------");
                        continue;
                    }

                    // вносим контакт в телефонную книгу
                    nameToPhoneMap.put(name, phone);
                    phoneToNameMap.put(phone, name);
                    System.out.println("Контакт занесён в телефонную книгу");
                    System.out.println("------------------------------------------------------");
                    System.out.println(name + " : " + nameToPhoneMap.get(name));
                    System.out.println("------------------------------------------------------");
                } else {
                    System.out.println("Введён некорректный номер телефона");
                }
            } else if (isPhone(normalizePhone(input))) {
                // проверяем номер телефона на наличие
                String phone = normalizePhone(input);
                if (phoneToNameMap.containsKey(phone)) {
                    System.out.println("Контакт существует");
                    System.out.println("------------------------------------------------------");
                    System.out.println(phoneToNameMap.get(phone) + " : " + phone);
                    System.out.println("------------------------------------------------------");
                    continue;
                }

                // Вводим ФИО
                System.out.println("Введите ФИО:");
                String name = "";
                try {
                    name = scanner.nextLine().trim();
                } catch (NoSuchElementException ex) {
                    // after Ctrl+D
                    System.out.println(FAREWELL);
                    System.exit(0);
                }

                // проверяем ФИО и вносим контакт
                if (isName(name)) {
                    // проверяем ФИО на наличие
                    if (nameToPhoneMap.containsKey(name)) {
                        System.out.println("Контакт существует");
                        System.out.println("------------------------------------------------------");
                        System.out.println(name + " : " + nameToPhoneMap.get(name));
                        System.out.println("------------------------------------------------------");
                        continue;
                    }

                    // вносим контакт в телефонную книгу
                    nameToPhoneMap.put(name, phone);
                    phoneToNameMap.put(phone, name);
                    System.out.println("Контакт внесён в телефонную книгу");
                    System.out.println("------------------------------------------------------");
                    System.out.println(name + " : " + nameToPhoneMap.get(name));
                    System.out.println("------------------------------------------------------");
                } else {
                    System.out.println("Введён некорректный ФИО");
                }
            } else {
                System.out.println("Введены некорректные данные");
            }
        }
    }

    /**
     * list()
     */
    private static void list() {
        if (!nameToPhoneMap.isEmpty()) {
            System.out.println("------------------------------------------------------");
            nameToPhoneMap.entrySet().stream()
                    .sorted(HashMap.Entry.comparingByKey())
                    .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));
            System.out.println("------------------------------------------------------");
        } else {
            System.out.println("Список контактов пустой");
        }
    }

    /**
     * save()
     */
    private static void save() {
        if (!nameToPhoneMap.isEmpty()) {
            try (FileWriter fw = new FileWriter(FILE_NAME, false)) {
                nameToPhoneMap.entrySet().stream()
                        .sorted(HashMap.Entry.comparingByKey())
                        .forEach(e -> {
                            try {
                                fw.write(e.getKey() + "=" + e.getValue() + "\r\n");
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        });
                fw.flush();
                System.out.printf("Список контактов сохранён в файл '%s'\n", FILE_NAME);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Нет информации для сохранения");
        }
    }

    /**
     * load()
     */
    private static void load() {
        File file = new File(FILE_NAME);
        if (file.exists() && file.isFile() && file.length() != 0) {
            try (FileReader fr = new FileReader(file);
                 Scanner scanner = new Scanner(fr)) {
                nameToPhoneMap.clear();
                phoneToNameMap.clear();
                while (scanner.hasNextLine()) {
                    String[] readLine = scanner.nextLine().split("=");
                    nameToPhoneMap.put(readLine[0], readLine[1]);
                    phoneToNameMap.put(readLine[1], readLine[0]);
                }
                System.out.printf("Список контактов загружен из файла '%s'\n", FILE_NAME);
                list();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Нет информации для загрузки");
        }
    }

    /**
     * help()
     */
    private static void help() {
        System.out.println(String.format("%67s", "").replace(' ', '='));
        System.out.printf("%-14s%s\n", "[ФИО]", "Фамилия Имя отчество");
        System.out.printf("%-14s%s\n", "[номер_тлф]", "Номер телефона");
        System.out.printf("%-14s%s\n", "list", "Показать телефонную книгу");
        System.out.printf("%-14s%s '%s'\n", "save", "Сохранить телефонную книгу в файл", FILE_NAME);
        System.out.printf("%-14s%s '%s'\n", "load", "Загрузить телефонную книгу из файла", FILE_NAME);
        System.out.printf("%-14s%s\n", "help", "Вывести список всех команд");
        System.out.printf("%-14s%s\n", "exit", "Завершить работу приложения");
        System.out.println(String.format("%67s", "").replace(' ', '-'));
        System.out.println("Примеры:");
        System.out.println("  Радионов Дмитрий Александрович");
        System.out.println("  +7 (978) 123-45-67");
        System.out.println("  +79781234567");
        System.out.println("  +7-978-123-45-67");
        System.out.println("  8(978)123-45-67");
        System.out.println("  list");
        System.out.println("  save");
        System.out.println("  load");
        System.out.println("  help");
        System.out.println("  exit");
        System.out.println(String.format("%67s", "").replace(' ', '='));
    }

    /**
     * Проверка строки на наличие команды
     */
    private static boolean isCommand(String command) {
        return COMMAND_PATTERN.matcher(command).find();
    }

    /**
     * Проверка строки на наличие ФИО
     */
    private static boolean isName(String name) {
        return NAME_PATTERN.matcher(name).find();
    }

    /**
     * Проверка строки на наличие номера мобильного телефона
     */
    private static boolean isPhone(String phone) {
        return MOBILE_PHONE_PATTERN.matcher(phone).find();
    }

    /**
     * Нормализуем номер мобильного телефона к формату +7(999)123-45-67.
     * Если цифр меньше 10, возращаеем пустую строку.
     * Восстанавливаем код страны +7 для РФ, если код не (+7) или отсутствует.
     */
    private static String normalizePhone(String phone) {
        StringBuilder sb = new StringBuilder();

        // убираем всё, кроме знака '+' и цифр
        phone = phone.replaceAll("[()\s-]+", "");
        if (phone.length() < 10) {
            return "";
        }

        // восстанавливаем код страны +7 для РФ, если код не (+7) или отсутствует, иначе оставляем как есть
        if (phone.startsWith("8")) {
            sb.append("+7").append(phone.substring(1));
        } else if (phone.startsWith("7")) {
            sb.append("+7").append(phone.substring(1));
        } else if (phone.length() == 10) {
            sb.append("+7").append(phone);
        } else {
            sb.append(phone);
        }

        // делаем номер красивым с конца
        sb.insert(sb.length() - 2, "-");
        sb.insert(sb.length() - 5, "-");
        sb.insert(sb.length() - 9, ")");
        sb.insert(sb.length() - 13, "(");

        return sb.toString();
    }
}
