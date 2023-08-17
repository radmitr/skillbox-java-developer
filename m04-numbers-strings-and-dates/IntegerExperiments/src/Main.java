// Task 4.1.1
// Task 4.1.2
public class Main {

    public static void main(String[] args) {
        Container container = new Container();
        container.count += 7843;

        System.out.println(container.count);
        System.out.println(sumDigits(container.count));
        System.out.println("--------------------------------");

        // 4.1.1
        System.out.println(sumDigits(12345));
        System.out.println(sumDigits(10));
        System.out.println(sumDigits(5059191));
        System.out.println("--------------------------------");

        // 4.1.2
        System.out.println(sumDigitsWithCharacter(12345));
        System.out.println(sumDigitsWithCharacter(10));
        System.out.println(sumDigitsWithCharacter(5059191));
    }

    public static int sumDigits(Integer number) {
        String numberStr = number.toString();
        int sum = 0;

        for(int i = 0; i < numberStr.length(); i++) {
            sum += Integer.parseInt(String.valueOf(numberStr.charAt(i)));
        }
        return sum;
    }
    
    public static int sumDigitsWithCharacter(Integer number) {
        String numberStr = number.toString();
        Container container = new Container();

        for(int i = 0; i < numberStr.length(); i++) {
            container.count += Character.getNumericValue(number.toString().charAt(i));
        }
        return container.count;
    }
}
