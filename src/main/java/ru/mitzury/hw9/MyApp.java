package ru.mitzury.hw9;

import java.util.ArrayList;
import java.util.List;

public class MyApp {

    public static void main(String[] args) {
        // 1 пункт
        int min = 1;
        int max = 15;
        RangeProcessor RangeProcessor = new RangeProcessor();
        ArrayList<Integer> range = RangeProcessor.generateRange(min, max);
        System.out.println(range);

        // 2 пункт
        int[] numbers = {2, 5, 7, 10, 3, 6};
        SumProcessor SumProcessor = new SumProcessor();
        int sum = SumProcessor.sumGreaterThanFive(numbers);
        System.out.println("Сумма элементов > 5: " + sum);

        // 3 пункт
        ArrayList<Integer> number = new ArrayList<>();
        number.add(1);
        number.add(2);
        number.add(3);
        int newValue = 7;
        ListProcessor processor = new ListProcessor();
        processor.fillWithNumber(newValue, number);
        System.out.println(number);

        // 4 пункт
        ArrayList<Integer> numberseval = new ArrayList<>();
        numberseval.add(1);
        numberseval.add(2);
        numberseval.add(3);
        int increment = 5;
        ListProcessor2 ListProcessor = new ListProcessor2();
        ListProcessor.incrementAll(increment, numberseval);
        System.out.println(numberseval);

        // 5 пункт. Сотрудник.

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Иван", 25));
        employees.add(new Employee("Мария", 30));
        employees.add(new Employee("Сергей", 22));

        EmployeeProcessor EmployeeProcessor = new EmployeeProcessor();

        // Список имён
        List<String> names = EmployeeProcessor.getNames(employees);
        System.out.println("Имена сотрудников: " + names);

        // Сотрудники старше или равны 25
        List<Employee> adults = EmployeeProcessor.filterByMinAge(employees, 25);
        System.out.println("Сотрудники с возрастом >= 25: ");
        for (Employee e : adults) {
            System.out.println(e.getName() + " " + e.getAge());
        }

        // Средний возраст > 24
        boolean isAverageHigh = EmployeeProcessor.isAverageAgeGreaterThan(employees, 24);
        System.out.println("Средний возраст > 24? " + isAverageHigh);

        // Самый молодой сотрудник
        Employee youngest = EmployeeProcessor.getYoungestEmployee(employees);
        System.out.println("Самый молодой: " + youngest.getName() + " " + youngest.getAge());

    }
}