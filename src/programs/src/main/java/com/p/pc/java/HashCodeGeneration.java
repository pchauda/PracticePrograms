package com.p.pc.java;

public class HashCodeGeneration {
    public static void main(String[] args) {
        HashCodeGeneration obj = new HashCodeGeneration();
        System.out.println("string".hashCode() % 16);

        System.out.println(-100 % 16);
        System.out.println(-15 ^ -15 >>> 16);
        System.out.println(15 & (-15 ^ -15 >>> 16));
        System.out.println(15 & -8);
        System.out.println(Integer.toBinaryString(-3));
        System.out.println(~13 + 1); // way to represent a negative number => -13
        // In java negative numbers are stored as 2's compliment. -13 is stored like above in binary form
    }

}
