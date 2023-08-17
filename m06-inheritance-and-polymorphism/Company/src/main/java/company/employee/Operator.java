package company.employee;

import java.util.concurrent.ThreadLocalRandom;

public class Operator extends AbstractEmployee {

    private static final long MIN_MONTH_SALARY = 40_000;
    private static final long MAX_MONTH_SALARY = 60_000;

    public Operator(String name) {
        super(name);
    }

    @Override
    public long calculateMonthSalary() {
        return ThreadLocalRandom.current().nextLong(MIN_MONTH_SALARY, MAX_MONTH_SALARY);
    }

    @Override
    public String toString() {
        return String.format("ID=%-6d %-25s %-45s %,8d", id, "Оператор", name, monthSalary);
    }
}
