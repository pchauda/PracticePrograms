package com.p.pc.java;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class StreamTest {

    public static void main(String args[]) {
        List<Integer> list = Stream.of(1, 2, 1, 2, 3).collect(Collectors.toList());

        Map<Integer, Integer> listMap = list.stream().collect(groupingBy(t -> t, Collectors.reducing(0, e -> 1, Integer::sum)));

        Stream.of("Bob", "John", " Alex", "Kathy").map(t -> {
            System.out.println("name: " + t);
            return t.toUpperCase();
        }).forEach(System.out::println);

        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));
        System.out.println(streamSupplier.get().anyMatch(s -> s.startsWith("a")));   // true
        System.out.println(streamSupplier.get().noneMatch(s -> s.startsWith("a")));   // false

    }

}