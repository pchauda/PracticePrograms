package com.p.pc.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class StreamTest {

    public static void main(String args[]) {
        List<Integer> list = new ArrayList<>(); list.add(1); list.add(2); list.add(1); list.add(2); list.add(3);

        Map<Integer, Integer> listMap = list.stream().collect(groupingBy(t -> t, Collectors.reducing(0, e -> 1, Integer::sum)));
        listMap.entrySet().forEach(t -> System.out.println("key=" + t.getKey() + ", value="+ t.getValue()));

        Stream.of("Prince", "Chauda", " Riya", "Malik").map(t -> {
            System.out.println("name: " + t); return t.toUpperCase();}).forEach(System.out::println);

        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));
        System.out.println(streamSupplier.get().anyMatch(s -> s.startsWith("a")));   // true
        System.out.println(streamSupplier.get().noneMatch(s -> s.startsWith("a")));   // false

    }

}