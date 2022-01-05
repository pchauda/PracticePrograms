package com.p.pc.cracking_the_coding_interview.java;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <p>In the classic problem FizzBuzz, you are told to print the numbers from 1 to n. However,
 * when the number is divisible by 3, print "Fizz''. When it is divisible by 5, print "Buzz''. When it is
 * divisible by 3 and 5, print"FizzBuzz''. In this problem, you are asked to do this in a multithreaded way.
 * Implement a multithreaded version of FizzBuzz with four threads. One thread checks for divisibility
 * of 3 and prints"Fizz''. Another thread is responsible for divisibility of 5 and prints"Buzz''. A third thread
 * is responsible for divisibility of 3 and 5 and prints "FizzBuzz''. A fourth thread does the numbers.
 *</p>
 * Approach: <br/>
 *  Apply java 8 lambda functional approach to define the predicate and printer function and pass it dynamically to different threads.
 *  Since all threads will run in parallel and there is no control on which one will succeed, a synchronization is required within the run method itself using a static lock/atomic integer.
 */
public class FizzBuzzProblem {

    public static void main(String[] args) {
        int n = 15;
        FizzBuzzThread[] threads = new FizzBuzzThread[] {
                new FizzBuzzThread(i -> i % 3 == 0 && i % 5 != 0, i -> "Fizz", n),
                new FizzBuzzThread(i -> i % 5 == 0 && i % 3 != 0, i -> "Buzz", n),
                new FizzBuzzThread(i -> i % 3 == 0 && i % 5 == 0, i -> "FizzBuzz", n),
                new FizzBuzzThread(i -> i % 3 != 0 && i % 5 != 0, i -> Integer.toString(i), n)
        };
        Arrays.stream(threads).forEach(t -> t.start());
    }

    static class FizzBuzzThread extends Thread {
        // shared counter across all thread objects
        private static AtomicInteger counter = new AtomicInteger(1);
        final int max;
        final Predicate<Integer> predicate;
        final Function<Integer, String> printer;

        public FizzBuzzThread(Predicate<Integer> predicate, Function<Integer, String> printer, int max) {
            this.predicate = predicate;
            this.printer = printer;
            this.max = max;
        }

        public void run() {
            while(true) {
                int i = counter.intValue();
                if(i > max) break;
                if(predicate.test(i)) {
                    System.out.println(printer.apply(i));
                    counter.incrementAndGet();
                }
            }
        }
    }
}
