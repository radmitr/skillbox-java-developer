package rabin_karp;

// 18.9 Алгоритм поиска подстроки Рабина-Карпа
public class RabinKarp {

    private RabinKarp() {
    }

    // R is the number of characters in the input alphabet
    private static final int R = 256;

    public static int search(String pattern, String text, int primeNumber) {

        int index = -1; // -1 here represents not found
        int patternLength = pattern.length();
        int textLength = text.length();
        int hashForPattern = 0;
        int hashForText = 0;
        int rm = 1; // R^(patternLength - 1) % primeNumber

        // The value of rm would be "pow(R, patternLength - 1) % primeNumber"
        for (int i = 0; i < patternLength - 1; i++) {
            rm = (rm * R) % primeNumber;
        }

        // Calculate the hash value of pattern and first
        // window of text
        for (int i = 0; i < patternLength; i++) {
            hashForPattern = (R * hashForPattern + pattern.charAt(i)) % primeNumber;
            hashForText = (R * hashForText + text.charAt(i)) % primeNumber;
        }

        // Slide the pattern over text one by one
        for (int i = 0; i <= textLength - patternLength; i++) {
            /* Check the hash values of current window of text
               and pattern. If the hash values match then only
               check for characters one by one*/

            int j = 0;
            if (hashForPattern == hashForText) {
                /* Check for characters one by one */
                for (j = 0; j < patternLength; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) break;
                }

                // if hashForPattern == hashForText and pattern[0...patternLength-1] = text[i, i+1, ...i+patternLength-1]
                if (j == patternLength) {
                    index = i;
                    return index;
                }
            }

            // Calculate hash value for next window of text: Remove leading digit, add trailing digit
            if (i < textLength - patternLength) {
                hashForText = (R * (hashForText - text.charAt(i) * rm) + text.charAt(i + patternLength)) % primeNumber;

                // handling negative hashForText
                if (hashForText < 0) hashForText = (hashForText + primeNumber);
            }
        }
        return index; // return -1 if pattern does not found
    }
}
