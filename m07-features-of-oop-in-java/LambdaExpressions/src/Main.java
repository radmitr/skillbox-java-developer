import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {
        ArrayList<Employee> staff = loadStaffFromFile();

        System.out.println("Not sorted");
        for (Employee employee : staff) {
            System.out.println(employee);
        }

        // Functional class, anonymous class
        System.out.println("\nSorted by salary");
        Collections.sort(staff, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getSalary().compareTo(o2.getSalary());
            }
        });
        for (Employee employee : staff) {
            System.out.println(employee);
        }
        System.out.println("--------------------------------------------------");

        /////////////////////////////// Lambda ///////////////////////////////
        System.out.println("Lambda\n");

//        Collections.sort(staff, (o1, o2) -> o1.getSalary().compareTo(o2.getSalary()));
//        Collections.sort(staff, Comparator.comparing(Employee::getSalary));

        // Homework 7.1
        Collections.sort(staff, (o1, o2) -> {
            int ret;
            ret = o1.getSalary().compareTo(o2.getSalary());
            if (ret == 0) {
                ret = o1.getName().compareTo(o2.getName());
            }
            return ret;
        });

        // for each, forEach()
        System.out.println("Old salaries");
//        for (Employee employee : staff) {
//            System.out.println(employee);
//        }
//        staff.forEach(employee -> System.out.println(employee));
        staff.forEach(System.out::println);

        System.out.println("\nNew salaries");
//        staff.forEach(employee -> {
//            int salary = employee.getSalary();
//            employee.setSalary(salary + 10000);
//        });
        int salaryIncrease = 10000;
        staff.forEach(e -> e.setSalary(e.getSalary() + salaryIncrease));
        staff.forEach(System.out::println);
        System.out.println("--------------------------------------------------");

        /////////////////////////////// Stream ///////////////////////////////
        System.out.println("Stream\n");

        // filter()
        System.out.println("Filter >= 100000");
//        Stream<Employee> stream = staff.stream();
//        stream.filter(employee -> employee.getSalary() >= 100000).forEach(System.out::println);
        staff.stream()
                .filter(e -> e.getSalary() >= 100000)
                .forEach(System.out::println);

        System.out.println("\nEven numbers");
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers.filter(number -> number % 2 == 0)
                .forEach(System.out::println);

        System.out.println("\nOdd numbers");
        Integer[] newNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Arrays.stream(newNumbers)
                .filter(number -> number % 2 != 0)
                .forEach(System.out::println);

        // iterate(), generate()
        System.out.println("\nEndless stream");
//        Stream.iterate(1, n -> n + 1).forEach(System.out::println);
//        Stream.generate(() -> "aaa").forEach(System.out::println);

        // chars(), filter()
        System.out.println("\nChars < 5 ");
        "0123456789".chars()
                .filter(ch -> ch < '5')
                .forEach(System.out::println);

        // sorted()
        System.out.println("\nStream sorted");
        staff.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .forEach(System.out::println);

        // max(), get(), Optional, ifPresent()
        System.out.println("\nMax");
//        Optional<Employee> optional = staff.stream()
//                .max(Comparator.comparing(Employee::getSalary));
//        Employee employee = optional.get();
//        System.out.println(employee);
        staff.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent(System.out::println);

        // map(), reduce()
        System.out.println("\nSum salary >= 100000");
        staff.stream()
                .map(e -> e.getSalary())
                .filter(s -> s >= 100000)
                .reduce((s1, s2) -> s1 + s2)
                .ifPresent(System.out::println);

        // Homework 7.2.1
        System.out.println("\nMax salary 2017");
        staff.stream()
                .filter(e -> e.getWorkStart().getYear() == (2017 - 1900))
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent(System.out::println);
        System.out.println("--------------------------------------------------");

        /////////////////////////////// Generics ///////////////////////////////
        System.out.println("Generics\n");

        System.out.println("LRUCash");
        LRUCash<Employee> cash = new LRUCash(5);
        for (Employee employee : staff) {
            cash.addElement(employee);
        }
        cash.getAllElements().forEach(System.out::println);
        System.out.println(cash.getElement(0).getSalary());

        System.out.println("\nCalculator");
        Calculator calculator = new Calculator();
        Integer[] integerArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> integerList = new ArrayList<>(Arrays.asList(integerArray));
        System.out.println(integerList);
        System.out.println(Calculator.sum(integerList));
        System.out.println("--------------------------------------------------");

        /////////////////////////////// Maven ///////////////////////////////

        System.out.println("Maven");
        try {
            String data = getDataFromFile("data/staff.json");
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(data);
            for (Object item : array) {
                JSONObject jsonObject = (JSONObject) item;
                System.out.println(jsonObject.get("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Employee> loadStaffFromFile() {
        ArrayList<Employee> staff = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for (String line : lines) {
                String[] fragments = line.split("\t");
                if (fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staff;
    }

    private static String getDataFromFile(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                builder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
