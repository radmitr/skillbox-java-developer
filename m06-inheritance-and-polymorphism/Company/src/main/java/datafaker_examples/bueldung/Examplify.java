package datafaker_examples.bueldung;

import net.datafaker.Faker;

public class Examplify {

    private static Faker faker = new Faker();

    public static void main(String[] args) {
        System.out.println("Expression: " + getExpression());
        System.out.println("Number expression: " + getNumberExpression());
    }

    static String getExpression() {
        return faker.expression("#{examplify 'Cat in the Hat'}");
    }

    static String getNumberExpression() {
        return faker.expression("#{examplify '123-123-123'}");
    }
}
