package company.employee;

import java.util.concurrent.ThreadLocalRandom;

public class TopManager extends AbstractEmployee {

    private static final long MIN_MONTH_SALARY = 100_000;
    private static final long MAX_MONTH_SALARY = 130_000;
    private static final long INCOME_THRESHOLD = 10_000_000;
    private static final double BONUS_PERCENT = 150.0 / 100;
    private long bonus;

    public TopManager(String name) {
        super(name);
    }

    public long getBonus() {
        return bonus;
    }

    @Override
    public long calculateMonthSalary() {
        long fixedSalary = ThreadLocalRandom.current().nextLong(MIN_MONTH_SALARY, MAX_MONTH_SALARY);
        if (company.getIncome() > INCOME_THRESHOLD) {
            bonus = (long) (fixedSalary * BONUS_PERCENT);
            return fixedSalary + bonus;
        } else {
            return fixedSalary;
        }
    }

    @Override
    public void fired() {
        id = 0;
        company = null;
        monthSalary = 0;
        bonus = 0;
    }

    @Override
    public String toString() {
        return String.format("ID=%-6d %-25s %-45s %,8d", id, "Топ-менеджер", name, monthSalary);
    }
}
