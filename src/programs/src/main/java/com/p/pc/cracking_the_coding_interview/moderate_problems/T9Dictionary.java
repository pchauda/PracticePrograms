package com.p.pc.cracking_the_coding_interview.moderate_problems;

import com.p.pc.cracking_the_coding_interview.code_library.Trie;
import com.p.pc.cracking_the_coding_interview.code_library.TrieNode;

import java.util.ArrayList;
import java.util.List;

/**
 * On old cell phones, users typed on a numeric keypad and the phone would provide a list of words
 * that matched these numbers. Each digit mapped to a set of O - 4 letters. Implement an algorithm
 * to return a list of matching words, given a sequence of digits. You are provided a list of valid words
 * (provided in whatever data structure you'd like). The mapping is shown in the diagram below:
 * EXAMPLE
 *      1    2   3
 *           abc def
 *      4    5   6
 *      ghi  jkl mno
 *      7    8   9
 *      pqrs tuv wxyz
 *           0
 *
 * Input:   8733
 * Output:  tree, used
 *
 * Approach:
 *  Follow a Trie based approach to solve this problem optimally.
 */
public class T9Dictionary {

    public static void main(String[] args) {
        String numString = "8733";
        String[] validWords = new String[] {"used", "tree", "true", "bat", "cat", "mat"};
        Trie trie = new Trie(validWords);
        List<String> validT9Words = getValidT9Words(numString, trie);
        System.out.println(validT9Words);
    }

    private static List<String> getValidT9Words(String numString, Trie trie) {
        List<String> words = new ArrayList<>();
        getValidWords(numString, 0, "", trie.getRoot(), words);
        return words;
    }

    private static void getValidWords(String numString, int index, String prefix, TrieNode node, List<String> result) {
        if(index == numString.length()) {
            if(node.terminates()) // if a valid word then add to the result
                result.add(prefix);
            return;
        }
        char n = numString.charAt(index); // pick the number character at given index
        char[] letters = getT9Chars(n); // get all linked english characters
        if(letters != null) {
            for(char c : letters) {
                TrieNode child = node.getChild(c);
                if(child != null) {
                    getValidWords(numString, index + 1, prefix + c, child, result);
                }
            }
        }
    }

    static char[] getT9Chars(char digit) {
        if (!Character.isDigit(digit)) {
            return null;
        }
        int dig = Character.getNumericValue(digit) - Character.getNumericValue('0'); // get index using ascii code
        return t9Letters[dig];
    }

    static char[][] t9Letters = {null, null, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},{'w', 'x', 'y', 'z'}
    };
}
