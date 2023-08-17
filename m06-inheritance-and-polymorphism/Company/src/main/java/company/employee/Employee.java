package company.employee;

import company.Company;

public interface Employee extends Comparable<Employee> {

    int getId();

    Company getCompany();

    void setCompany(Company company);

    void setMonthSalary();

    long getMonthSalary();

    void fired();

    @Override
    int compareTo(Employee o);
}
