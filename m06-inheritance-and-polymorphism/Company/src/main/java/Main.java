import company.Company;
import company.employee.Employee;
import company.employee.Manager;
import company.employee.Operator;
import company.employee.TopManager;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

// Task 6.4
public class Main {

    public static void main(String[] args) {
        long seed = 12345;
        Faker faker = new Faker(new Locale("ru"), new Random(seed));

        System.out.println("***************************************************************************************");
        System.out.println("Создаём фирму...");
        System.out.println("***************************************************************************************\n");
        String creatorName = generateFullName(faker);
        String companyName = generateCompanyName(faker);
        Company company = new Company(companyName, creatorName);
        System.out.println(company);


        System.out.println("***************************************************************************************");
        System.out.println("Нанимаем 270 работников...");
        System.out.println("***************************************************************************************\n");
        for (int i = 0; i < 10; i++) {
            company.hire(new TopManager(generateFullName(faker)));
        }
        for (int i = 0; i < 80; i++) {
            company.hire(new Manager(generateFullName(faker)));
        }
        for (int i = 0; i < 180; i++) {
            company.hire(new Operator(generateFullName(faker)));
        }
        System.out.println(company);

        showTopSalaryStaff(company, 15);
        showLowestSalaryStaff(company, 30);

        System.out.println("***************************************************************************************");
        System.out.println("Увольняем 100 сотрудников...");
        System.out.println("***************************************************************************************\n");
        int startCount = company.getAllEmployees().size();
        int endCount = startCount - 100;

        for (int j = startCount; j > endCount; j--) {
            company.fire(company.getAllEmployees()
                    .get(ThreadLocalRandom.current().nextInt(0, j)));
        }
        System.out.println(company);

        showTopSalaryStaff(company, 15);
        showLowestSalaryStaff(company, 30);

        System.out.println("***************************************************************************************");
        System.out.println("Увольняем всех оставшихся сотрудников и меняем название фирмы...");
        System.out.println("***************************************************************************************\n");
        company.fireAll();
        company.setCompanyName(generateCompanyName(faker));
        System.out.println(company);

        System.out.println("***************************************************************************************");
        System.out.println("Нанимаем 100 работников, из них 80 менеджеров...");
        System.out.println("***************************************************************************************\n");
        List<Employee> newEmployeeList = new ArrayList<>(100);

        for (int i = 0; i < 10; i++) {
            newEmployeeList.add(new TopManager(generateFullName(faker)));
        }
        for (int i = 0; i < 80; i++) {
            newEmployeeList.add(new Manager(generateFullName(faker)));
        }
        for (int i = 0; i < 10; i++) {
            newEmployeeList.add(new Operator(generateFullName(faker)));
        }
        company.hireAll(newEmployeeList);
        System.out.println(company);

        System.out.println("***************************************************************************************");
        System.out.println("Только тогда доход фирмы выше 10 млн рублей, когда число менеджеров 79 и выше. \n" +
                "Уволим 5 топ-менеджеров и 2-х операторов, доход не поменяется...");
        System.out.println("***************************************************************************************\n");
        company.firePart(98); // уволим 2-х операторо
        company.firePart(2, 5); // уволили 4-х топ-менеджеров
        company.fire(1); // уволили 1-го топ-менеджера

        System.out.println(company);

        System.out.println("***************************************************************************************");
        System.out.println("Если уволим 2-х менеджеров, доход фирмы станет меньше 10 млн рублей...");
        System.out.println("***************************************************************************************\n");
        company.fire(50); // уволили 1-го менеджера
        company.fire(25); // уволили 1-го менеджера

        System.out.println(company);
    }

    //==============================================================================

    private static String generateFullName(Faker faker) {
        StringBuilder builder = new StringBuilder();
        String name = faker.name().nameWithMiddle();

        String[] nameItems = name.split("\s");

        if (nameItems.length == 3) {
            builder.append(nameItems[2]).append(" ").append(nameItems[0]).append(" ").append(nameItems[1]);
            return builder.toString();
        } else {
            return name;
        }
    }

    private static String generateCompanyName(Faker faker) {
        return faker.company().name();
    }

    private static void showTopSalaryStaff(Company company, int number) {
        List<Employee> topSalaryStaff;

        System.out.printf("Топ %d самых высокооплачиваемых работников:\n", number);
        topSalaryStaff = company.getTopSalaryStaff(number);
        for (int i = 0; i < topSalaryStaff.size(); i++) {
            System.out.printf("%3d   %s руб.\n", (i+1), topSalaryStaff.get(i));
        }
        System.out.println();
    }

    private static void showLowestSalaryStaff(Company company, int number) {
        List<Employee> lowestSalaryStaff;

        System.out.printf("Топ %d самых низкооплачиваемых работников:\n", number);
        lowestSalaryStaff = company.getLowestSalaryStaff(number);
        for (int i = 0; i < lowestSalaryStaff.size(); i++) {
            System.out.printf("%3d   %s руб.\n", (i+1), lowestSalaryStaff.get(i));
        }
        System.out.println();
    }
}
