package com.p.pc.java;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionTest {
    String name;

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Throwable error = null;

        CollectionTest testObj = new CollectionTest();
        System.out.println(testObj.name);

        System.out.println(Optional.ofNullable(error).map(Throwable::getMessage).orElse("Not found"));

        // Stream supplier can be used to construct a new stream and to perform operation on it.
        Supplier<Stream<String>> streamSupplier = () -> Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> s.startsWith("a"));
        System.out.println(streamSupplier.get().anyMatch(t -> t.contains("a")));
        System.out.println(streamSupplier.get().noneMatch(t -> t.contains("p")));

        List<Employee> employees = Stream.of(new Employee(1, "IT", 50), new Employee(1, "IT", 51), new Employee(1, "HR", 55), new Employee(1, "IT", 50),
                new Employee(1, "HR", 35), new Employee(1, "IT", 57), new Employee(1, "IT", 25)).collect(Collectors.toList());

        Map<String, Integer> collect = employees.stream().collect(Collectors.groupingBy(t -> t.department, Collectors.summingInt(t -> t.age)));
        System.out.println(collect);
    }

    static class Employee {
        int id;
        String department;
        int age;

        Employee(int id, String department, int age) {
            this.id = id;
            this.department = department;
            this.age = age;
        }
    }
}

