import java.util.Scanner;

// 4.4.3
public class FioSplitterLoader {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String firstName = "";
        String middleName = "";
        String lastName = "";

        System.out.println("Введите ФИО:");
        String fio = in.nextLine().trim();

        if (fio.length() > 0) {
            while (true) {
                int i = fio.indexOf(' ');
                if (lastName.length() == 0) {
                    if (i == -1) {
                        lastName = fio;
                        break;
                    } else {
                        lastName = fio.substring(0, i);
                    }
                } else if (firstName.length() == 0) {
                    if (i == -1) {
                        firstName = fio;
                        break;
                    } else {
                        firstName = fio.substring(0, i);
                    }
                } else {
                    middleName = fio;
                    break;
                }
                fio = fio.substring(i + 1);
            }
        }

        System.out.println("Фамилия: " + lastName);
        System.out.println("Имя: " + firstName);
        System.out.println("Отчество: " + middleName);
    }
}
