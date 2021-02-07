package com.reactor.fluxmono;

import com.reactor.fluxmono.model.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KEYWORDS
 * <p>
 * Anonymous Class:
 * <p>
 * Lambda Function/Expressions:
 * <p>
 * Method References:
 * <p>
 * Functional Interface:
 * <p>
 * Streams:
 * <p>
 * Collections:
 * <p>
 * Sequential Stream:
 * Parallel Stream:
 */
public class Java8StreamAPI {

    private static Employee[] arrayOfEmployees = {
            new Employee("1", "Jeff Bezos", 100000.00),
            new Employee("2", "Bill Gates", 200000.0),
            new Employee("3", "Mark Zuckerberg", 300000.0)
    };


    private static List<Employee> empList = Arrays.asList(arrayOfEmployees);

    public static void main(String[] args) {

        // Stream.of
        Stream.of(arrayOfEmployees).forEach(employee -> {
            System.out.println(employee.employee_name);
        });

        // Arrays.asList().stream()
        empList.stream().forEach(employee -> {
            System.out.println(employee.employee_salary);
        });

        // With List of items in Stream
        // Stream.of(arr[0], arr[1], arr[2])
        Stream.of(arrayOfEmployees[0], arrayOfEmployees[1]).forEach(employee -> {
            System.out.println(employee.id);
        });


        // Using Stream Builder

        Stream.Builder<Employee> employeeStreamBuilder = Stream.builder();
        employeeStreamBuilder.accept(arrayOfEmployees[2]);
        employeeStreamBuilder.accept(arrayOfEmployees[0]);

        Stream<Employee> employeeStream = employeeStreamBuilder.build();

        employeeStream.forEachOrdered(employee -> {
            System.out.println(employee.employee_name);
        });

        // Stream map function --> Produces a new function at the end after applying to each element

        Integer[] employeeIds = {1, 2, 3};

        // Creating a list of Integers
        List<Integer> list = Arrays.asList(3, 6, 9, 12, 15);

        // Using Stream map(Function mapper) and
        // displaying the corresponding new stream
        list.stream().map(number -> number * 3).forEach(System.out::println);


        // Get Employee having salary less than 200K using Predicate
        System.out.println("Get Employee having salary less than 200K using Predicate");
        empList.stream().filter(new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.employee_salary < 200000.0;
            }
        }).forEach(employee -> {
            System.out.println(employee.id);
            System.out.println(employee.employee_name);
        });

        // Above predicate can be replaced using lambda
        System.out.println("Get Employee having salary less than 200K using Predicate with Lambda function");
        empList.stream().filter(employee -> employee.employee_salary < 200000.0).forEach(employee -> {

            System.out.println(employee.id);
            System.out.println(employee.employee_name);
        });


        // Get all employees where name is not Jeff
        System.out.println("Get all employees where name is not Jeff");
        empList.stream().filter(employee -> Java8StreamAPI.nameIsNotJeff(employee)).forEach(employee -> {
            System.out.println(employee.id);
            System.out.println(employee.employee_name);
        });


        // Convert employee list to map and print with entrySet & iterator
        System.out.println("Convert employee list to map and print with entrySet & iterator");
        empList.stream()
                .collect(Collectors.toMap(Employee::getId, Employee::getEmployee_name))
                .entrySet()
                .iterator()
                .forEachRemaining(stringStringEntry -> {
                    System.out.println(stringStringEntry.getKey() + " " + stringStringEntry.getValue());
                });

        // Convert employee list to map and get all values
        System.out.println("Convert employee list to map and get all values");
        empList.stream()
                .collect(Collectors.toMap(Employee::getId, Employee::getEmployee_name))
                .values()
                .iterator()
                .forEachRemaining(System.out::println);


        // Convert employee list to map and get all ids
        System.out.println("Convert employee list to map and get all ids");
        empList.stream()
                .collect(Collectors.toMap(Employee::getId, Employee::getEmployee_name))
                .keySet()
                .iterator()
                .forEachRemaining(System.out::println);


        // Convert employee list to map and print all values using forEach
        System.out.println("Convert employee list to map and print all values using forEach");
        empList.stream()
                .collect(Collectors.toMap(Employee::getId, Employee::getEmployee_name))
                .forEach((s, s2) -> {
                    System.out.println(s + " " + s2);
                });


        // Convert employee list to map and get all hashcodes
        System.out.println("Convert employee list to map and get all hashcodes");
        empList.stream()
                .collect(Collectors.toMap(Employee::getId, Employee::getEmployee_name))
                .forEach((s, s2) -> {
                    System.out.println(s.hashCode() + " " + s2.hashCode());
                });


        // Count all employees Salary using map reduce
        System.out.println("Count all employees salary using map reduce");
        Double allEmployeeSalary = empList.stream().map(Employee::getEmployee_salary).reduce(0.0, Double::sum);
        System.out.println("Total Salaries " + allEmployeeSalary);


        // Count all employees Salary using map reduce USING PARALLEL STREAMS
        // using parallel Streams, expect to get non deterministic results, when you run multiple times
        System.out.println("Count all employees salary using map reduce USING PARALLEL STREAMS");
        Double allEmployeeSalary1 = empList.parallelStream()
                .map(Employee::getEmployee_salary).reduce(0.0, Double::sum);
        System.out.println("Total Salaries " + allEmployeeSalary1);


        // Get the max salary of an employee
        System.out.println("Get the max salary of an employee");
        empList.stream()
                .max((o1, o2) -> o1.getEmployee_salary() > o2.getEmployee_salary() ? 0 : -1)
                .ifPresent(employee -> System.out.println(employee.getEmployee_name()));


        // Get the min salary of an employee
        System.out.println("Get the min salary of an employee");
        empList.stream()
                .min((o1, o2) -> o1.getEmployee_salary() > o2.getEmployee_salary() ? 0 : -1)
                .ifPresent(employee -> System.out.println(employee.getEmployee_name()));


        // Get comma, separated values of all employee names
        System.out.println("\nGet comma, separated values of all employee names");
        String s1 = empList.stream().map(Employee::getEmployee_name)
                .reduce("", (s, s2) -> {
                    if ("".equals(s)) {
                        return s2;
                    } else {
                        return s + ", " + s2;
                    }
                });
        System.out.println(s1);


        // Sort employees by name
        System.out.println("\nSort employees by name");
        empList.stream()
                .sorted(Comparator.comparing(Employee::getEmployee_name))
                .forEachOrdered(employee -> System.out.println(employee.getEmployee_name()));


        // Adding duplicates to List and then convert that to map
        System.out.println("\nAdding duplicates to List and then convert that to map");
        List<Employee> newArrayList = new ArrayList<>(empList);
        newArrayList.add(new Employee("3", "Rakesh Chouhan", 9999999.0));

        newArrayList.stream().collect(Collectors.toMap(Employee::getId, Employee::getEmployee_name, (oldValue, newValue) -> newValue))
                .forEach((s, s2) -> {
                    System.out.println(s + " " + s2);
                });


    }

    public static boolean nameIsNotJeff(Employee employee) {
        return !employee.employee_name.equals("Jeff Bezos");
    }

}
