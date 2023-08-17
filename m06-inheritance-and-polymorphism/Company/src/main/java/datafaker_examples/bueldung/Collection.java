package datafaker_examples.bueldung;

import net.datafaker.Faker;

import java.util.List;

public class Collection {

    public static int MIN = 1;
    public static int MAX = 100;
    private static Faker faker = new Faker();

    public static void main(String[] args) {
        System.out.println(getFictionalCharacters());
    }

    static List<String> getFictionalCharacters() {
        return faker.collection(
                        () -> faker.starWars().character(),
                        () -> faker.starTrek().character())
                .len(MIN, MAX)
                .generate();
    }
}
