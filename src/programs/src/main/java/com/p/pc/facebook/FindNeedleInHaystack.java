package com.p.pc.facebook;

public class FindNeedleInHaystack {
    public static void main(String[] args) {
        String hayStack = "Finding a needle in a haystack is not easy but still worth a shot";
        String needle = "worth";

        System.out.println(String.format("First index of needle in haystack (iterative) = %d", findNeedleInHayStackIterative(hayStack, needle)));
        // Output = 38
    }
    // Naive solution, Complexity = O(m * n)
    private static int findNeedleInHayStackIterative(String hayStack, String needle) {
        if(hayStack == null || needle.length() > hayStack.length()) return -1;
        if(needle == null || needle.length() == 0) return 0;
        // For each character in the haystack try to find the needle only till (haystack length - needle length) as
        // needle couldn't be found after that.
        for(int i=0; i <= hayStack.length() - needle.length(); i++) {
            int j=0;
            while(j < needle.length() && i + j < hayStack.length() &&
                    hayStack.charAt(i + j) == needle.charAt(j++));
            if(j == needle.length()) return i; // needle found, return the index
        }
        return -1;
    }
}
