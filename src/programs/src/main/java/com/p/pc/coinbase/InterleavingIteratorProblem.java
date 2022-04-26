package com.p.pc.coinbase;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * First given a 2D array, output the results in interleaving fashion.
 * Ex: [[1,2,3],[4,5],[6],[],[7,8,9]] ---> [1,4,6,7,2,5,8,3,9]
 *
 * Approach:
 *  Use a queue to keep track of next to-be-visited index for each array.
 */
public class InterleavingIteratorProblem {

    public static void main(String[] args) {
        int[][] input = new int[][] { {1, 4,}, {2, 5}, {}, {3, 6}};
        printInterleavingArray(input, false);
    }

    private static void printInterleavingArray(int[][] input, boolean cycle) {
        InterleavingIterator<Integer> iterator = new InterleavingIterator(input, cycle);
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    static class InterleavingIterator<T> implements Iterator<T> {
        // A queue containing array of two elements representing the array number in the index in the array.
        private Queue<int[]> iteratorQueue;

        private int[][] array;
        private boolean cycle;

        InterleavingIterator(int[][] input, boolean cycle) {
            this.array = input;
            this.cycle = cycle;
            iteratorQueue = new ArrayDeque<>();
            initializeQueue(input);
        }

        private void initializeQueue(int[][] input) {
            for(int i=0; i< input.length; i++) {
                if(input[i].length > 0) {
                    iteratorQueue.add(new int[]{i, 0}); // add the first index for each array in the queue
                }
            }
        }

        // Returns true if cycle = true or queue has remaining elements
        public boolean hasNext() {
            return cycle || iteratorQueue.size() > 0;
        }

        // Iterate over the queue and return the element and push the index for the next element on the queue.
        // next() method will only be called after hasNext()
        public T next() {
            if(iteratorQueue.size() == 0) {
                if(cycle) this.initializeQueue(array);
                else throw new NoSuchElementException();
            }

            int[] item = iteratorQueue.poll();
            int i = item[0], j = item[1];
            // check for next item and push it to the queue
            if(j < array[i].length - 1) {
                iteratorQueue.add(new int[]{i, j+1});
            }
            return (T) Integer.valueOf(array[i][j]);
        }
    }
}
