package com.p.pc.programs;

public class StringQuestions {
    public static void main(String[] args) {
        System.out.println("Reversed string for \"Prince\" is " + reverseString("Prince"));
        System.out.println("Reversed string for \"Namrita\" is " + reverseString("Namrita"));
    }

    static String reverseString(String str) {
        char[] s = str.toCharArray();
        int i=0, j=s.length - 1;

        while(i < s.length/2) {
            char tmp = s[i];
            s[i++] = s[j];
            s[j--] = tmp;
        }
        return new String(s);
    }

}
