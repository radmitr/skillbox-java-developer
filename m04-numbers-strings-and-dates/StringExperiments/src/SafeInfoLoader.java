// 4.4.4
// 4.5.5
public class SafeInfoLoader {

    public static void main(String[] args) {

        // 4.4.4 (дополнительное задание)
        String safeNumber = searchAndReplaceDiamonds("Номер кредитной карты <4008 1234 5678> 8912", "***");
        System.out.println(safeNumber);
        System.out.println("-----------------------------------------------------");

        // 4.5.5
        String safeNumber2 = searchAndReplaceDiamondsWithRegex("Номер кредитной карты <4008 1234 5678> 8912", "***");
        System.out.println(safeNumber2);
    }

    private static String searchAndReplaceDiamonds(String text, String placeholder) {
        String newText;
        int index1, index2;

        index1 = text.indexOf('<');
        index2 = text.indexOf('>');
        if (index2 > index1) {
            newText = text.substring(0, index1) + placeholder + text.substring(index2 + 1);
        } else {
            newText = "";
        }
        return newText;
    }

    private static String searchAndReplaceDiamondsWithRegex(String text, String placeholder) {
        return text.replaceAll("<\\d{4}\\s\\d{4}\\s\\d{4}>", placeholder);
    }
}
