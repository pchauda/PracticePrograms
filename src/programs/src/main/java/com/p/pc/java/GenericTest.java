package com.p.pc.java;

import java.io.IOException;
import java.io.UTFDataFormatException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class GenericTest {
    public static void main(String[] args) throws IOException, BrokenBarrierException, InterruptedException {
        System.out.println("PrincePrince".substring(0, 2)); // Output: Pr

        System.out.println(-7 >> 1); // Output: -4
        System.out.println(7 >> 1); // Output: 3

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(2);

        Double d = Math.min(-Double.POSITIVE_INFINITY, -Double.MAX_VALUE);
        System.out.println(d);

        char[] chars = new char[] {'\u0097'};
        String str = new String(chars);
        byte[] bytes = str.getBytes();
        System.out.println(str);
        System.out.println(Arrays.toString(bytes));

        try {
            System.out.println("I am in try block");
            // System.exit(1);
        } finally {
            System.out.println("I am in finally block"); // This will not be executed if System.exit is commented out
        }

        GenericTest obj = new GenericTest();
        Dog dog = obj.new Dog();
        Mammal animal = obj.new Mammal();
        dog.getType(animal);
        CyclicBarrier c = new CyclicBarrier(4);
        try {
            c.await(1, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        Thread t1 = new Thread();
        t1.join();
        Lock lock = new ReentrantLock();

        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);




        Integer aa = Integer.valueOf(10);
        Integer ab = 12;
        aa.compareTo(ab);
        Double dasd = (0.1 * 3);
        System.out.println(dasd.doubleValue() == 0.3); // false
    }

    class Animal {
        Animal getType() throws IOException {
            System.out.println("Animal");
            return new Animal();
        }
    }
    class Mammal extends Animal {
        Mammal getType() {
            System.out.println("Mammal");
            return new Mammal();
        }
    }
    class Dog extends Animal{

        int getType(Animal a) throws IOException {
            System.out.println("Animal");
            return 4;
        }
        int getType(Mammal a) throws IOException {
            System.out.println("Mammal");
            return 4;
        }
    }
}
