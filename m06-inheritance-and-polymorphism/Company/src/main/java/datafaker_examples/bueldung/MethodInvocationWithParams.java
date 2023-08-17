package datafaker_examples.bueldung;

import net.datafaker.Faker;

import java.time.Duration;

public class MethodInvocationWithParams {

    public static int MIN = 1;
    public static int MAX = 10;
    public static String UNIT = "SECONDS";
    private static Faker faker = new Faker();

    public static void main(String[] args) {
        System.out.println("Duration from the method :" + getDurationFromMethod());
        System.out.println("Duration from the expression: " + getDurationFromExpression());
    }
    static Duration getDurationFromMethod() {
        return faker.date().duration(MIN, MAX, UNIT);
    }

    static String getDurationFromExpression() {
        return faker.expression("#{date.duration '1', '10', 'SECONDS'}");
    }
}
