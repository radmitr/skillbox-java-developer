package company.employee;

import company.Company;

import java.util.Objects;

public abstract class AbstractEmployee implements Employee {

    private static int countId = 99;
    protected int id;
    protected String name;
    protected Company company;
    protected long monthSalary;

    public AbstractEmployee(String name) {
        id = ++countId;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Company getCompany() {
        return company;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }

    protected abstract long calculateMonthSalary();

    @Override
    public void setMonthSalary() {
        monthSalary = calculateMonthSalary();
    }

    @Override
    public long getMonthSalary() {
        return monthSalary;
    }

    @Override
    public void fired() {
        id = 0;
        company = null;
        monthSalary = 0;
    }

    @Override
    public int compareTo(Employee o) {
        if (monthSalary > o.getMonthSalary()) {
            return -1;
        } else if (monthSalary < o.getMonthSalary()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEmployee that = (AbstractEmployee) o;
        return id == that.id && monthSalary == that.monthSalary
                && Objects.equals(name, that.name)
                && Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, company, monthSalary);
    }
}
