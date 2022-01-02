package com.p.pc.facebook;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>You are given a dictionary of words and a large input string. You have to find out whether the input string can be
 * completely segmented into the words of a given dictionary.</p>
 */
public class StringSegmentation {
    public static void main(String[] args) {
        Set<String> dictionary = new HashSet<>();
        dictionary.add("apple"); dictionary.add("pear"); dictionary.add("pier"); dictionary.add("applep");
        dictionary.add("ie");

        System.out.println("Word can be segmented: " + canSegmented("applepiepear", dictionary)); // Output: true
    }

    static boolean canSegmented(String s, Set<String> dictionary) {
        for(int i=1; i <= s.length(); i++) {
            String sub = s.substring(0, i);
            if(dictionary.contains(sub)) {
                String rem = s.substring(i);
                if(rem == null || rem.length() == 0) return true;
                if(dictionary.contains(rem)) return true;
                if(canSegmented(rem, dictionary)) return true;
            }
        }
        return false;
    }
}
