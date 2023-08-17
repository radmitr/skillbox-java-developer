package company;

import company.employee.Employee;
import company.employee.Manager;
import company.employee.TopManager;

import java.util.*;

public class Company {

    private String companyName;
    private String creatorName;
    private List<Employee> employees = new ArrayList<>();
    private long income;

    public Company(String companyName, String creatorName) {
        this.companyName = companyName;
        this.creatorName = creatorName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void addProfit(long profit) {
        if (profit > 0) {
            income += profit;
        }
    }

    public void minusProfit(long profit) {
        if (profit > 0) {
            income -= profit;
        }
    }

    public long getIncome() {
        return income;
    }

    public void hire(Employee employee) {
        employees.add(employee);
        employee.setCompany(this);
        employee.setMonthSalary();

        if (employee instanceof Manager) {
            income += ((Manager) employee).getProfit(); // добавим профит для компании

            for (Employee emp : employees) {
                if (emp instanceof TopManager) {
                    emp.setMonthSalary(); // пересчитаем зарплату топ-менеджеров
                }
            }
        }
    }

    public void hireAll(List<Employee> employees) {
        this.employees.addAll(employees);

        for (Employee employee : employees) {
            employee.setCompany(this);
            employee.setMonthSalary();

            if (employee instanceof Manager) {
                income += ((Manager) employee).getProfit(); // добавим профит для компании

                for (Employee emp : employees) {
                    if (emp instanceof TopManager) {
                        emp.setMonthSalary(); // пересчитаем зарплату топ-менеджеров
                    }
                }
            }
        }
    }

    public void fire(int index) {
        Employee employee = employees.get(index);

        if (employee instanceof Manager) {
            income -= ((Manager) employee).getProfit(); // удалим профит для компании

            for (Employee emp : employees) {
                if (emp instanceof TopManager) {
                    emp.setMonthSalary(); // пересчитаем зарплату топ-менеджеров
                }
            }
        }
        employees.remove(index);
        employee.fired();
    }

    public void fire(Employee employee) {
        if (employee instanceof Manager) {
            income -= ((Manager) employee).getProfit(); // удалим профит для компании

            for (Employee emp : employees) {
                if (emp instanceof TopManager) {
                    emp.setMonthSalary(); // пересчитаем зарплату топ-менеджеров
                }
            }
        }
        employees.remove(employee);
        employee.fired();
    }

    public void firePart(int beginIndex) {
        for (int i = employees.size() - 1; i >= beginIndex; i--) {
            fire(employees.get(i));
        }
    }

    public void firePart(int beginIndex, int endIndex) {
        for (int i = endIndex; i >= beginIndex; i--) {
            fire(employees.get(i));
        }
    }

    public void fireAll() {
        for (int i = employees.size() - 1; i >= 0; i--) {
            fire(employees.get(i));
        }
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        if (count <= 0) {
            return Collections.emptyList();
        }
        if (count > employees.size()) {
            count = employees.size();
        }

        List<Employee> list = new ArrayList<>(employees);

        Collections.sort(list, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.getMonthSalary() > o2.getMonthSalary()) {
                    return -1;
                } else if (o1.getMonthSalary() < o2.getMonthSalary()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        return list.subList(0, count);
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        if (count <= 0) {
            return Collections.emptyList();
        }
        if (count > employees.size()) {
            count = employees.size();
        }

        List<Employee> list = new ArrayList<>(employees);

        Collections.sort(list, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.getMonthSalary() > o2.getMonthSalary()) {
                    return 1;
                } else if (o1.getMonthSalary() < o2.getMonthSalary()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        return list.subList(0, count);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Фирма: " + this.getCompanyName() + '\n');
        builder.append("Основатель: " + this.getCreatorName() + '\n');
        builder.append("Доход: " + String.format("%,d", this.getIncome()) + " руб.\n");
        builder.append("Сотрудники (" + String.format("%,d", employees.size()) + " человек):\n");

        for (int i = 0; i < employees.size(); i++) {
            builder.append(String.format("%3d   %s руб.\n", (i+1), employees.get(i)));
        }
        return builder.toString();
    }
}
