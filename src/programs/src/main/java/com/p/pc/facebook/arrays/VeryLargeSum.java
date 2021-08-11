package com.p.pc.facebook.arrays;

import java.util.Arrays;
import java.util.Scanner;

public class VeryLargeSum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numbers = in.nextInt();
        long[] arr = new long[numbers];
        for(int i=0; i<numbers; i++) {
            arr[i] = in.nextLong();
        }
        System.out.println(sum(arr));
    }

    static long sum(long[] arr) {
        return Arrays.stream(arr).reduce(0L, (a, b) -> a + b);
    }
}
