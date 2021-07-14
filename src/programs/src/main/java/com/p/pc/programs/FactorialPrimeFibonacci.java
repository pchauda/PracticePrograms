package com.p.pc.programs;

import java.util.HashMap;
import java.util.Map;

public class FactorialPrimeFibonacci {
    public static void main(String[] args) {
        int n = 20;
        System.out.println("Factorial Recursive: " + factorialRecursive(n)); // 720
        System.out.println("Factorial Iterative: " + factorialIterative(n)); // 720
        System.out.println("Is 2789987 prime? Result: " + isPrime(2789987));
        System.out.println("Fibonacci of 10: " + fibonacci(10));
        System.out.println("Fibonacci Iterative of 10: " + fibonacciIterative(10));
        System.out.println("Fibonacci RecursiveMemo of 10: " + fibonacciRecursiveWithMemo(10, new int[10 + 1]));

        Map<String, String> map = new HashMap<>();
    }

    static long factorialRecursive(int n) {
        if(n < 0) throw new IllegalArgumentException("Please provide a positive integer");
        if(n == 0 ) return 1;
        return n * factorialRecursive(n - 1);
    }

    static long factorialIterative(int n) {
        if(n < 0) throw new IllegalArgumentException("Please provide a positive integer");
        long fact = 1;
        for(int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    static boolean isPrime(int num) {
        for(int i=2; i * i <= num; i++) {
            if(num % i == 0) {
                System.out.println("Divisible by: " + i);
                return false;
            }
        }
        return true;
    }

    /**
     * TimeComplexity: power(2, n) due to the multiple recursive calls
     */
    static int fibonacci(int n) {
        if(n <= 0 ) return 0;
        else if(n == 1) return 1;
        return fibonacci(n-1) + fibonacci(n-2);
    }

    /**
     *  TimeComplexity: O(n)
     */
    static int fibonacciIterative(int n) {
        if(n <= 0 ) return 0;
        else if(n == 1) return 1;
        int prev2 = 0;
        int prev1 = 1;
        for(int i=2; i <= n; i++) {
            int tmp = prev1 + prev2;
            prev2 = prev1;
            prev1 = tmp;
        }
        return prev1;
    }

    /**
     *  TimeComplexity: O(n)
     */
    static int fibonacciRecursiveWithMemo(int n, int[] memo) {
        if(n <= 0 ) return 0;
        if(n == 1) return 1;
        if(memo[n] > 0) return memo[n];

        memo[n] = fibonacciRecursiveWithMemo(n-1, memo) + fibonacciRecursiveWithMemo(n-2, memo);
        return memo[n];
    }
}
