package com.p.pc.hackerrank;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Sock merchant problem:
 * Given a merchant maintains a socks in a pair represented by number:
 * 1 1 2 2 2 2 3 4 4 5 6 7 8
 *
 * Identify how many complete pairs of socks the merchant has.
 */
public class SockMerchantProblem {

    public static void main(String[] args) throws IOException {
        int[] socks = new int[] {1, 1, 2, 2, 2, 2, 3, 4, 4, 5, 6, 7, 8}; // 4 complete pairs
        // count of socks for each sock number
        Map<Integer, Long> sockIdentityToCountMap = Arrays.stream(socks).boxed().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        // Count the sockIdentity where count is even number
        System.out.println(sockIdentityToCountMap.values().stream().reduce(0L, (sum, next) -> sum + next / 2).intValue());

        sockMerchant(socks);
    }

    // Complete the sockMerchant function below.
    static int sockMerchant(int[] ar) {

        Map<Integer, Integer> socksCounts = new HashMap<>();
        Map<Integer, Integer> socksCounts2 = new HashMap<>();
        for(int i : ar) {
            if(!socksCounts.containsKey(i)) {
                socksCounts.put(i, 1);
            }
            else {
                socksCounts.put(i, socksCounts.get(i) + 1);
            }
        }
        int total = 0;
        for(Integer value : socksCounts.values()) {
            total += value / 2;
        }
        return total;
    }
}
