package com.p.pc.cracking_the_coding_interview.java;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The dining philosophers problem states that there are 5 philosophers sharing a circular table, they eat and think
 * alternatively. There is a bowl of rice for each of the philosophers and 5 chopsticks.
 * A philosopher needs both their right and a left chopstick to eat. A hungry philosopher may only eat if there are both
 * chopsticks available. Otherwise, a philosopher puts down their chopstick and begin thinking again.
 *
 * Approach 2:
 *  Thread coarsening approach will be used in this solution. If a philosopher is unable to pick both chopsticks
 *  then he will drop the other and try again.
 */
public class DiningPhilosophersProblem2 {
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

        public boolean pickUp() {
            return lock.tryLock();
        }
        public void putDown() {
            lock.unlock();
        }
    }

    static class Philosopher extends Thread {
        private final int id;
        private final Chopstick left;
        private final Chopstick right;

        public Philosopher(int id, Chopstick left, Chopstick right) {
            this.id = id;
            // By using lower and higher approach, we can ensure that the last philosopher picks up the chopsticks in opposite order and thus breaks the cycle and deadlock
            this.left = left;
            this.right = right;
        }

        public void run() {
            // 5 bites
            for(int i=0; i<5; i++) {
                boolean hasEaten = false;
                while(!hasEaten)
                    hasEaten = eat();
            }
        }
        private boolean eat() {
            // Chew and put down only pick successfully picked up both chopsticks
            if(pickUp()) {
                chew();
                putDown();
                return true;
            } else return false;
        }
        // pickup both chopsticks else drop the left as well
        private boolean pickUp() {
            if(!left.pickUp()) {
                return false;
            }
            if(!right.pickUp()) {
                left.putDown(); // put down left for others to use
                return false;
            }
            return true;
        }
        private void chew() {
            System.out.println("Philosopher: " + id + " is chewing food");
        }
        private void putDown(){
            right.putDown();
            left.putDown();
        }
    }
}
