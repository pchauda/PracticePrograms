package com.p.pc.java;

import java.util.Optional;
import java.util.function.Supplier;
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
    }
}

