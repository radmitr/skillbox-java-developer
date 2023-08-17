import java.util.Scanner;

// 4.3.2
public class Main {

    public static void main(String[] args) {
        int numberOfBoxes;
        Scanner scanner = new Scanner(System.in);
        HumanitarianAid aid = new HumanitarianAid();

        System.out.println("Введите количество ящиков:");
        numberOfBoxes = scanner.nextInt();
        aid.setNumberOfBoxes(numberOfBoxes);

        aid.printNumberOfBoxes();
        System.out.println("Необходимо:");
        System.out.println("грузовиков - " + aid.getNumberOfTrucks() + " шт.");
        System.out.println("контейнеров - " + aid.getNumberOfContainers() + " шт.");
    }
}
