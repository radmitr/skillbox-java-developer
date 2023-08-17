import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 4.4.2
// 4.5.1
public class Loader {

    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        Scanner in = new Scanner(System.in);

        // 4.4.2
        int income = 0;
        int index = 0;
        String tempText = text;
        while (index >= 0) {
            index = tempText.indexOf(' ');
            if (index == -1) {
                break;
            }
            String tempStr = tempText.substring(0, index);
            try {
                int item = Integer.parseInt(tempStr);
                income += item;
            } catch (NumberFormatException e) {
                // skip
            }
            tempText = tempText.substring(index + 1);
        }
        System.out.println("Сумма заработка всех друзей: " + income);
        System.out.println("-----------------------------------------------------");

        // 4.5.1
        int income2 = 0;
        String[] items = text.split("\\s");
        for (int i = 0; i < items.length; i++) {
            try {
                int item = Integer.parseInt(items[i]);
                income2 += item;
            } catch (NumberFormatException e) {
                // skip
            }
        }
        System.out.println("Сумма заработка всех друзей: " + income2);
        System.out.println("-----------------------------------------------------");

        // 4.5.1 (2)
        int income3 = 0;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            income3 += Integer.parseInt(text.substring(matcher.start(), matcher.end()));
        }
        System.out.println("Сумма заработка всех друзей: " + income3);
    }
}
