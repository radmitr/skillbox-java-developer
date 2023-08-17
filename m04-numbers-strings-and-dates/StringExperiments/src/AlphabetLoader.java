// 4.4.1
public class AlphabetLoader {

    private final static String EN_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final static String RU_ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private final static String NUMBERS = "01234567890";

    public static void main(String[] args) {

        for (int i = 0; i < EN_ALPHABET.length(); i++) {
            char charSymbol = EN_ALPHABET.charAt(i);
            int charCode = EN_ALPHABET.charAt(i);
            System.out.println("Символ \"" + charSymbol + "\" - " + charCode);
        }
        System.out.println("-----------------------------------------------------");

        for (int i = 0; i < RU_ALPHABET.length(); i++) {
            char charSymbol = RU_ALPHABET.charAt(i);
            int charCode = RU_ALPHABET.charAt(i);
            System.out.println("Символ \"" + charSymbol + "\" - " + charCode);
        }
        System.out.println("-----------------------------------------------------");

        for (int i = 0; i < NUMBERS.length(); i++) {
            char charSymbol = NUMBERS.charAt(i);
            int charCode = NUMBERS.charAt(i);
            System.out.println("Символ \"" + charSymbol + "\" - " + charCode);
        }
    }
}
