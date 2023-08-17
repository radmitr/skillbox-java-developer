package org.example;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        System.out.println("------------------------------------------");

        //////////////////////////// Employee ////////////////////////////

        Employee employee = new Employee("Василий Петров", 75_000, new Date());

        // annotation: @ToString
        System.out.println(employee);
        System.out.println();

        // annotation: @Getter
        System.out.println(employee.getName());
        System.out.println(employee.getSalary());
        System.out.println(employee.getWorkStart());
        System.out.println();

        // annotation: @Setter
        employee.setName("Григорий Сидоров");
        employee.setSalary(150_000);

        Date newDate = new Date();
        newDate.setTime(1_999_777_555_333L);
        employee.setWorkStart(newDate);

        System.out.println(employee.getName());
        System.out.println(employee.getSalary());
        System.out.println(employee.getWorkStart());

        // annotation: @NonNull
//        employee.setName(null); // java.lang.NullPointerException
//        System.out.println(employee.getName());

        System.out.println("------------------------------------------");

        //////////////////////////// Worker ////////////////////////////

        // annotations: @Data, @AllArgsConstructor
        Worker worker = new Worker("Пётр Мамонов", 1_000_000, new Date());

        // annotation: @ToString(exclude = "workStart")
        System.out.println(worker);
        System.out.println();

        // annotation: @Getter
        System.out.println(worker.getName());
        System.out.println(worker.getSalary());
        System.out.println(worker.getWorkStart());
        System.out.println();

        // annotation: @Setter
        worker.setName("Андрей Шпиц");
        worker.setSalary(250_000);

        Date newNewDate = new Date();
        newNewDate.setTime(1_333_333_333_333L);
        worker.setWorkStart(newNewDate);

        System.out.println(worker.getName());
        System.out.println(worker.getSalary());
        System.out.println(worker.getWorkStart());

        System.out.println("------------------------------------------");
    }
}
