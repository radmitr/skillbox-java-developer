package datafaker_examples;

import net.datafaker.Faker;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        // 1 - полное имя
        System.out.println("--- 1 --------------------------------------");
        Faker faker = new Faker();
        System.out.println(faker.name().fullName());

        // 2 - фиксированное начальное число (seed)
        System.out.println("--- 2 --------------------------------------");
        long seed = 1;
        Faker faker2 = new Faker(new Locale("nl"), new Random(seed));
        System.out.println(faker2.name().fullName());

        // 3 - фиктивные коллекции
        System.out.println("--- 3 --------------------------------------");
        Faker faker3 = new Faker(new Locale("ru"));
        List<String> names = faker3.<String>collection()
                .suppliers(
                        () -> faker3.name().firstName(),
                        () -> faker3.name().lastName())
                .minLen(5)
                .maxLen(10)
                .build().get();
        names.forEach(System.out::println);

        // 4 - список объектов
        System.out.println("--- 4 --------------------------------------");
        List<Object> data = faker.collection()
                .suppliers(
                        () -> faker.date().future(10, TimeUnit.DAYS),
                        () -> faker.medical().hospitalName(),
                        () -> faker.number().numberBetween(10, 50))
                .minLen(8)
                .maxLen(10)
                .build().get();

        System.out.println(data);

        // 5 - использование пользовательского класс Faker
        System.out.println("--- 5 --------------------------------------");
        MyCustomFaker faker4 = new MyCustomFaker();
        System.out.println(faker4.turtle().name());
    }
}
