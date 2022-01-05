package com.p.pc.cracking_the_coding_interview.java;

import java.util.concurrent.Semaphore;

/**
 * <p>Suppose we have the following code:
 * public class Foo {
 * public Foo() { ... }
 * public void first() { ... }
 * public void second() { ... }
 * public void third() { ... }
 * }
 * The same instance of Foo will be passed to three different threads. ThreadA will call first threadB
 * will call second, and threadC will call third method. Design a mechanism to ensure that business logic in first is
 * executed before second and second is executed before third.
 *</p>
 * Approach: <br/>
 *  With Locks, unlock method can only be invoked by the thread that acquired the lock, hence we can't use locks here to enforce call ordering.
 *  However, with Semaphores that is not the case hence we will use Semaphores here to achieve the call ordering.
 */
public class CallMethodsInSequenceProblem {
    static class Foo {
        // Two semaphores are created for first and second method
        final Semaphore s1, s2;
        public Foo() {
            s1 = new Semaphore(1);
            s2 = new Semaphore(1);
            System.out.println("In constructor of Foo");
            try {
                s1.acquire();
                s2.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void first() {
            System.out.println("In method first()");
            s1.release();
        }
        public void second() {
            try {
                s1.acquire(); // Wait for first to complete
                s1.release(); // Release the permit
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("In method second()");
            s2.release(); // Release the permit
        }
        public void third() {
            try {
                s2.acquire(); // Wait for second to complete
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("In method third()");
            s2.release(); // Release the permit
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo(); // Main thread will create the object and acquire the permits, permits will be released in other threads

        Thread t1 = new Thread(() -> foo.first());
        Thread t2 = new Thread(() -> foo.second());
        Thread t3 = new Thread(() -> foo.third());
        // Starting the threads in reverse order with a sleep in between to ensure threads have enough time to start
        // before the other threads. It is done, just to illustrate that the logic works as expected.
        t3.start();
        Thread.sleep(100);
        t2.start();
        Thread.sleep(100);
        t1.start();
        Thread.sleep(100);
    }
}
