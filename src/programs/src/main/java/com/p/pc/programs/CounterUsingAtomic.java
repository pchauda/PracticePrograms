package com.p.pc.programs;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Volatile adds a significant cost to read a variable. Synchronized is 50% worse than the volatile keyword. Volatile itself
 * does not help with counter.
 *
 * Atomic offers a neat solution with nearly same time as volatile and offers synchronization benefits.
 */
public class CounterUsingAtomic {
    volatile int counter;
    int counter2;
    AtomicInteger counter3 = new AtomicInteger(0);

    public void incrementCounter() {
        counter++;
        counter2++;
        counter3.getAndIncrement();
    }

    public int getCounter() {
        return counter;
    }
    public int getCounter2() {
        return counter2;
    }
    private int getCounter3() {
        return counter3.get();
    }

    public static void main(String[] args) throws InterruptedException {
        CounterUsingAtomic obj = new CounterUsingAtomic();
        Thread t1 = new Thread(new MyRunnable(obj));
        Thread t2 = new Thread(new MyRunnable(obj));

        long startTime = System.nanoTime();
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        long endTime = System.nanoTime();
        System.out.println(obj.getCounter());
        System.out.println(obj.getCounter2());
        System.out.println(obj.getCounter3());
        System.out.println("Time taken: " + (endTime - startTime)/1000.0 + " micros");
    }

    static class MyRunnable implements Runnable {
        CounterUsingAtomic obj;

        public MyRunnable(CounterUsingAtomic obj) {
            this.obj = obj;
        }

        @Override
        public void run() {
            for(int i=1; i <= 100000; i++) {
                obj.incrementCounter();
            }
        }
    }
}
