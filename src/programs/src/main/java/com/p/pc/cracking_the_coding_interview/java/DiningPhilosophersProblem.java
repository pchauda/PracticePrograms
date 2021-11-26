package com.p.pc.cracking_the_coding_interview.java;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The dining philosophers problem states that there are 5 philosophers sharing a circular table, they eat and think
 * alternatively. There is a bowl of rice for each of the philosophers and 5 chopsticks.
 * A philosopher needs both their right and a left chopstick to eat. A hungry philosopher may only eat if there are both
 * chopsticks available. Otherwise, a philosopher puts down their chopstick and begin thinking again.
 *
 * Approach:
 *  Prioritized chopstick approach will be followed here i.e. the last philosopher will pick up the chopsticks in reverse order.
 */
public class DiningPhilosophersProblem {
    private static final int count = 5;
    public static void main(String[] args) {
        // initialize chopsticks
        Chopstick[] chopsticks = new Chopstick[count];
        for(int i=0; i<count; i++) {
            chopsticks[i] = new Chopstick(i);
        }
        // initialize philosophers
        Philosopher[] philosophers = new Philosopher[count];
        for(int i=0; i<count; i++) {
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % count]);
            philosophers[i].start();
        }
    }

    static class Chopstick {
        private final Lock lock;
        private final int id;
        public Chopstick(int id) {
            this.id = id;
            lock = new ReentrantLock();
        }
        public int getId() {
            return id;
        }
        public void pickUp() {
            lock.lock();
        }
        public void putDown() {
            lock.unlock();
        }
    }

    static class Philosopher extends Thread {
        private final int id;
        private final Chopstick lower;
        private final Chopstick higher;

        public Philosopher(int id, Chopstick left, Chopstick right) {
            this.id = id;
            // By using lower and higher approach, we can ensure that the last philosopher picks up the chopsticks in opposite order and thus breaks the cycle and deadlock
            this.lower = left.getId() < right.getId() ? left : right;
            this.higher = left.getId() < right.getId() ? right : left;
        }

        public void run() {
            // 5 bites
            for(int i=0; i<5; i++) {
                eat();
            }
        }
        private void eat() {
            pickUp();
            chew();
            putDown();
        }
        private void pickUp() {
            System.out.println("Philosopher: " + id + " is waiting for lower chopstick");
            lower.pickUp();
            System.out.println("Philosopher: " + id + " is waiting for higher chopstick");
            higher.pickUp();
            System.out.println("Philosopher: " + id + " is ready to chew" );
        }
        private void chew() {
            System.out.println("Philosopher: " + id + " is chewing food using chopstick: " + lower.getId() + " and " + higher.getId() + " chopsticks");
        }
        private void putDown(){
            System.out.println("Philosopher: " + id + " is trying to put down higher chopstick");
            higher.putDown();
            System.out.println("Philosopher: " + id + " is trying to put down lower chopstick");
            lower.putDown();
            System.out.println("Philosopher: " + id + " put down both chopsticks");
        }
    }
}
