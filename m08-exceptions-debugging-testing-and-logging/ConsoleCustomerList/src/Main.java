import java.util.Scanner;

public class Main {

    private static String addCommand = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static String commandExamples = "\t" + addCommand + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static String hintCommandExamples = "Available command examples: \n" + commandExamples;
//    private static String commandError = "Wrong command! Available command examples: \n" +
//            commandExamples;
    private static String commandError = "Wrong command!" + hintCommandExamples;
    private static String helpText = "Command examples:\n" + commandExamples;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        for(;;) {
            try {
                // add Дмитрий Радионов diman-rt@mail.ru +7(978)018-27-56
                // add Василий Петров vasily.petrov@gmail.com +79215637722
                // add Мария Лазарева lmariyka@gmail.com +7 (919) 555 44 33
                String command = scanner.nextLine();
                String[] tokens = command.split("\\s+", 2);
                if (tokens[0].equals("add")) {
                    if (tokens.length == 1) {
                        throw new IllegalArgumentException("Missing parameters");
                    } else {
                        executor.addCustomer(tokens[1]);
                    }
                } else if (tokens[0].equals("list")) {
                    executor.listCustomers();
                } else if (tokens[0].equals("remove")) {
                    if (tokens.length == 1) {
                        throw new IllegalArgumentException("Missing name");
                    } else {
                        executor.removeCustomer(tokens[1]);
                    }
                } else if (tokens[0].equals("count")) {
                    System.out.println("There are " + executor.getCount() + " customers");
                } else if (tokens[0].equals("help")) {
                    System.out.println(helpText);
                } else if (tokens[0].equals("exit")) {
                    break;
                } else if (tokens[0].length() == 0) {
                    throw new IllegalArgumentException("Missing command");
                } else {
//                    System.out.println(commandError);
                    throw new IllegalArgumentException("Wrong command");
                }
            } catch (IllegalArgumentException e) {
                if (e.getMessage().equals("Non-existent name")) {
                    System.err.println(e.getMessage() + "!");
                } else {
                    System.err.println(e.getMessage() + "!\n" + hintCommandExamples);
                }
            }
        }
    }
}
