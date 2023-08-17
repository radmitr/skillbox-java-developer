import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Task 5.2
public class Main {

    /** Паттерн для проверки введённой команды */
    private static final Pattern INPUT_PATTERN = Pattern.compile("(?i)^"
            + "(?<command>list|add|edit|delete|save|load|help|exit)"
            + "(?:\\s+(?<index>-?\\d+))?"
            + "(?:\\s+(?<text>.+))?$");

    /** Список апланированных дел */
    private static List<String> toDoList = new ArrayList<>();

    /** Имя файля для сохранения и загрузки запланированных дел */
    private static final String FILE_NAME = "todo_list.txt";

    /** Строка приветсвия */
    private static final String GREETING = "/".repeat(15)
            + " Добро пожаловать в приложение \"ToDoList\" "
            + "\\".repeat(15);

    /** Строка прощания */
    private static final String FAREWELL = "\\".repeat(20)
            + " Выход из приложения \"ToDoList\" "
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
                String indexString = matcher.group("index");
                String text = matcher.group("text");
                switch (command) {
                    case ("list") -> list();
                    case ("add") -> add(indexString, text);
                    case ("edit") -> edit(indexString, text);
                    case ("delete") -> delete(indexString);
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
        if (toDoList.size() > 0) {
            System.out.println("------------------------------------------------------");
            for (int i = 0; i < toDoList.size(); i++) {
                System.out.println((i + 1) + ": " + toDoList.get(i));
            }
            System.out.println("------------------------------------------------------");
        } else {
            System.out.println("Нет запланированых дел."
                    + " Чтобы добавить задачу, введите 'add [описание_дела]':");
        }
    }

    private static void add(String indexString, String text) {
        if (text != null && !text.isBlank()) {
            int index = -1;
            if (indexString != null) {
                index = Integer.parseInt(indexString);
            }

            if (index > 0 && index <= toDoList.size()) {
                toDoList.add(index - 1, text);
            } else {
                toDoList.add(text);
            }
            System.out.println("Дело добавлено в список");
        } else if (text == null) {
            System.out.println("Отсутсвует описание запланированного дела."
                    + " Введите 'add [описание_дела]':");
        }
    }

    private static void edit(String indexString, String text) {
        if (indexString != null && text != null) {
            int index = Integer.parseInt(indexString);
            if (index > 0 && index <= toDoList.size()) {
                toDoList.set(index - 1, text);
                System.out.println("Вы успешно изменили задачу под номером " + index);
            } else {
                System.out.println("В списке дел отсутствует задача под номером " + index);
            }
        } else {
            System.out.println("Введена недопустимая команда."
                    + " Введите 'help' для просмотра списка всех возможных команд:");
        }
    }

    private static void delete(String indexString) {
        if (indexString != null) {
            int index = Integer.parseInt(indexString);
            if (index > 0 && index <= toDoList.size()) {
                toDoList.remove(index - 1);
                System.out.println("Задача под номером " + index + " успешно удалена");
            } else {
                System.out.println("В списке дел отсутствует задача под номером " + index);
            }
        } else {
            System.out.println("Введена недопустимая команда."
                    + " Введите 'help' для просмотра списка всех возможных команд:");
        }
    }

    private static void save() {
        if (toDoList.size() > 0) {
            try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
                for (String s : toDoList) {
                    writer.write(s + "\r\n");
                }
                System.out.printf("Список дел сохранён в файл '%s'\n", FILE_NAME);
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
                toDoList.clear();
                int i = 0;
                while (scanner.hasNextLine()) {
                    toDoList.add(i, scanner.nextLine());
                    i++;
                }
                System.out.printf("Список дел загружен из файла '%s'\n", FILE_NAME);
                list();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Нет информации для загрузки");
        }
    }

    private static void help() {
        System.out.println(String.format("%77s", "").replace(' ', '='));
        System.out.printf("%-37s%s\n", "list", "Вывести списка всех дел");
        System.out.printf("%-37s%s\n", "add [описание_дела]", "Добавить новое дело в конец списка");
        System.out.printf("%-37s%s\n", "add [номер_дела] [описание]", "Добавить новое дело с номером");
        System.out.printf("%-37s%s\n", "edit [номер_дела] [новое_описание]", "Редактировать дело");
        System.out.printf("%-37s%s\n", "delete [номер_дела]", "Удалить дело");
        System.out.printf("%-37s%s '%s'\n", "save", "Сохранить дела в файл", FILE_NAME);
        System.out.printf("%-37s%s '%s'\n", "load", "Загрузить дела из файла", FILE_NAME);
        System.out.printf("%-37s%s\n", "help", "Вывести список всех команд");
        System.out.printf("%-37s%s\n", "exit", "Завершить работу приложения");
        System.out.println(String.format("%77s", "").replace(' ', '-'));
        System.out.println("Примеры:");
        System.out.println("  list");
        System.out.println("  add Какое-то дело");
        System.out.println("  add 4 Какое-то дело на четвёртом месте");
        System.out.println("  edit 3 Новое название дела");
        System.out.println("  delete 7");
        System.out.println("  save");
        System.out.println("  load");
        System.out.println("  help");
        System.out.println("  exit");
        System.out.println(String.format("%77s", "").replace(' ', '='));
        System.out.println("Введите команду:");
    }
}
