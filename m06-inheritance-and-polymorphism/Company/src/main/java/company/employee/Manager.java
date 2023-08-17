package company.employee;

import java.util.concurrent.ThreadLocalRandom;

public class Manager extends AbstractEmployee {

    private static final long MIN_MONTH_SALARY = 80_000;
    private static final long MAX_MONTH_SALARY = 90_000;
    private static final long MIN_PROFIT = 115_000;
    private static final long MAX_PROFIT = 140_000;
    private static final double BONUS_PERCENT = 5.0 / 100;
    private long profit;
    protected long bonus;

    public Manager(String name) {
        super(name);
    }

    public long getProfit() {
        return profit;
    }

    public long getBonus() {
        return bonus;
    }

    @Override
    public long calculateMonthSalary() {
        long fixedSalary = ThreadLocalRandom.current().nextLong(MIN_MONTH_SALARY, MAX_MONTH_SALARY);
        profit = ThreadLocalRandom.current().nextLong(MIN_PROFIT, MAX_PROFIT);
        bonus = (long) (profit * BONUS_PERCENT);

        return fixedSalary + bonus;
    }

    @Override
    public void fired() {
        id = 0;
        company = null;
        monthSalary = 0;
        profit = 0;
        bonus = 0;
    }

    @Override
    public String toString() {
        return String.format("ID=%-6d %-25s %-45s %,8d", id, "Менеджер по продажам", name, monthSalary);
    }
}
