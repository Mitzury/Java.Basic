package ru.mitzury.hw9;

import java.util.ArrayList;
import java.util.List;

public class EmployeeProcessor {
    // Метод 1: возвращает список имён сотрудников
    public List<String> getNames(List<Employee> employees) {
        if (employees == null) {
            throw new IllegalArgumentException("Список сотрудников не может быть null");
        }
        List<String> names = new ArrayList<>();
        for (Employee e : employees) {
            names.add(e.getName());
        }
        return names;
    }

    // Метод 2: возвращает список сотрудников с возрастом >= minAge
    public List<Employee> filterByMinAge(List<Employee> employees, int minAge) {
        if (employees == null) {
            throw new IllegalArgumentException("Список сотрудников не может быть null");
        }
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getAge() >= minAge) {
                result.add(e);
            }
        }
        return result;
    }

    // Метод 3: проверяет, что средний возраст > minAverageAge
    public boolean isAverageAgeGreaterThan(List<Employee> employees, double minAverageAge) {
        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException("Список сотрудников не может быть null или пустым");
        }
        int sum = 0;
        for (Employee e : employees) {
            sum += e.getAge();
        }
        double average = (double) sum / employees.size();
        return average > minAverageAge;
    }

    // Метод 4: возвращает самого молодого сотрудника
    public Employee getYoungestEmployee(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException("Список сотрудников не может быть null или пустым");
        }
        Employee youngest = employees.get(0);
        for (Employee e : employees) {
            if (e.getAge() < youngest.getAge()) {
                youngest = e;
            }
        }
        return youngest;
    }
}
