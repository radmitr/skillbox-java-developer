import java.util.Scanner;

// 4.5.4
public class PhoneNumberCheckerLoader {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите номер телефона:");
        String phoneStr = in.nextLine().trim();
        if (phoneStr.startsWith("+8")) {
            System.out.println("Неверный ввод!");
            return;
        }
        phoneStr = phoneStr.replaceAll("[+\\-()\\s]", "");
        try {
            Long phone = Long.parseLong(phoneStr);
        } catch (NumberFormatException e) {
            System.err.println("Ошибка ввода!");
            return;
        }

        if (phoneStr.length() == 11) {
            if (phoneStr.charAt(0) == '8') {
                phoneStr = "7" + phoneStr.substring(1);
            } else if ((phoneStr.charAt(0) == '7')) {
                // nop
            } else {
                System.out.println("Неверный ввод!");
            }
        } else if (phoneStr.length() == 10) {
            phoneStr = "7" + phoneStr;
        } else {
            System.out.println("Неверный ввод!");
        }

        System.out.println(phoneStr);
    }
}
