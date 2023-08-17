package org.example;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@ToString
public class Employee {

    @Getter
    @Setter
    @NonNull
    private String name;

    @Getter
    @Setter
    private Integer salary;

    @Getter
    @Setter
    @NonNull
    private Date workStart;

    public Employee(String name, Integer salary, Date workStart) {
        this.name = name;
        this.salary = salary;
        this.workStart = workStart;
    }

//    public String getName() {
//        return name;
//    }

    // 1.1
//    public void setName(String name) {
//        if (name == null) {
//            throw new NullPointerException();
//        }
//        this.name = name;
//    }
    // 1.2 - with parameter @NonNull
//    public void setName(@NonNull String name) {
//        this.name = name;
//    }

//    public Integer getSalary() {
//        return salary;
//    }

//    public void setSalary(int salary) {
//        this.salary = salary;
//    }

//    public Date getWorkStart() {
//        return workStart;
//    }

//    public void setWorkStart(Date workStart) {
//        this.workStart = workStart;
//    }

//    public String toString() {
//        return name + " - " + salary + " - " +
//            (new SimpleDateFormat("dd.MM.yyyy")).format(workStart);
//    }
}
