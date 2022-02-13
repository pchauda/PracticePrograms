package com.p.pc.hackerrank;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Sock merchant problem:
 * Given a merchant maintains a socks in a pair represented by number:
 * 1 1 2 2 2 2 3 4 4 5 6 7 8
 *
 * Identify how many complete pairs of socks the merchant has.
 */
public class SockMerchantProblem {

    public static void main(String[] args) {
        int[] socks = new int[] {1, 1, 2, 2, 2, 2, 3, 4, 4, 5, 6, 7, 8}; // 4 complete pairs
        // count of socks for each sock number
        Map<Integer, Long> sockIdentityToCountMap = Arrays.stream(socks).boxed().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        // Count the sockIdentity where count is even number
        System.out.println("Total Pairs using streams:" + sockIdentityToCountMap.values().stream().reduce(0L, (sum, next) -> sum + next / 2).intValue());
        System.out.println("Total Pairs: " + sockMerchant(socks));
    }

    // Complete the sockMerchant function below.
    static int sockMerchant(int[] ar) {
        Map<Integer, Integer> socksCounts = new HashMap<>();
        for(int i : ar) {
            socksCounts.compute(i, (k, v) -> v == null? 1: v+1 );
        }

        return socksCounts.values().stream().map(t -> t/2).reduce(0, Integer::sum);
    }
}
