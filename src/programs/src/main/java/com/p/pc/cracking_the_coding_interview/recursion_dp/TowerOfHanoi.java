package com.p.pc.cracking_the_coding_interview.recursion_dp;

import java.util.Stack;

/**
 * In the classic problem of the Towers of Hanoi, you have 3 towers and N disks of
 * different sizes which can slide onto any tower. The puzzle starts with disks sorted in ascending order
 * of size from top to bottom (i.e., each disk sits on top of an even larger one). You have the following
 * constraints:
 * (1) Only one disk can be moved at a time.
 * (2) A disk is slid off the top of one tower onto another tower.
 * (3) A disk cannot be placed on top of a smaller disk.
 * Write a program to move the disks from the first tower to the last using Stacks.
 * <p>
 * Approach:
 * Use an extra tower as buffer. Algo: Move n-1 disks from source to buffer using destination tower as buffer.
 * Then move nth disk from source to destination.
 * Then move n-1 disks from buffer to destination using source as buffer.
 * <p>
 * Time Complexity: Exponential (2^n)
 */
public class TowerOfHanoi {
    public static void main(String[] args) {
        TowerOfHanoi towerOfHanoi = new TowerOfHanoi();
        int totalDisks = 25;
        Tower original = new Tower();
        Tower buffer = new Tower();
        Tower destination = new Tower();
        for (int n = totalDisks; n > 0; n--) {
            original.addDisk(n);
        }
        System.out.println("Original Tower   : " + original.stack);
        towerOfHanoi.moveDisks(totalDisks, original, destination, buffer);
        System.out.println("Destination Tower: " + destination.stack);
    }

    private void moveDisks(int disks, Tower original, Tower destination, Tower buffer) {
        if (disks > 0) {
            // Move n-1 disks from original to buffer using destination as buffer
            moveDisks(disks - 1, original, buffer, destination);
            // Move nth disk from original to destination
            moveTop(original, destination);
            // Move n-1 disks from buffer to destination using original as buffer
            moveDisks(disks - 1, buffer, destination, original);
        }
    }

    private void moveTop(Tower original, Tower destination) {
        destination.addDisk(original.stack.pop());
    }

    static class Tower {
        private Stack<Integer> stack = new Stack<>();

        public int addDisk(int i) {
            return stack.push(i);
        }

        public int removeDisk() {
            return stack.pop();
        }
    }
}
