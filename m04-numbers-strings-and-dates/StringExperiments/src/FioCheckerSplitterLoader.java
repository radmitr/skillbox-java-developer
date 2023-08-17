import java.util.Scanner;

// 4.5.3
public class FioCheckerSplitterLoader {

    private static final String[] FIO_DETAILS = {"Фамилия", "Имя", "Отчество"};

    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите фамилию имя и отчество человека в формате <Фамилия Имя Отчество>:");
            String in = new Scanner(System.in).nextLine();
            if (!checkString(in)) {
                System.out.println("Строка не соответствует предложенному формату\n");
                continue;
            }
            String[] fioArr = in.split("\\s+");
            for (int i = 0; i < fioArr.length; i++) {
                System.out.println(FIO_DETAILS[i] + ": " + fioArr[i]);
            }
            System.out.println();
        }
    }

    private static boolean checkString(String fio) {
        String mask = "^[А-Я&&[^ЬЪ]][а-я&&[^ъ]]+(ов|ова|ёв|ёва|ев|ева|ин|ина|ын|ына|их|ых|ский|ская|цкий|цкая|ь)\\s" +
                "[А-Я&&[^ЬЪ]][а-я&&[^ъ]]+\\s[А-Я&&[^ЬЪ]][а-я&&[^ъ]]+(ович|евич|ич|овна|евна|ична|инична)$";
        return fio.matches(mask);
    }
}
