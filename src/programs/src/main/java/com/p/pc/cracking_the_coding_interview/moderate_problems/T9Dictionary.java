package com.p.pc.cracking_the_coding_interview.moderate_problems;

import com.p.pc.cracking_the_coding_interview.code_library.Trie;
import com.p.pc.cracking_the_coding_interview.code_library.TrieNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>On old cell phones, users typed on a numeric keypad and the phone would provide a list of words
 * that matched these numbers. Each digit mapped to a set of O - 4 letters. Implement an algorithm
 * to return a list of matching words, given a sequence of digits. You are provided a list of valid words
 * (provided in whatever data structure you'd like). The mapping is shown in the diagram below: <br/>
 * EXAMPLE <br/>
 * 1    2   3  <br/>
 * abc def<br/>
 * 4    5   6  <br/>
 * ghi  jkl mno<br/>
 * 7    8   9  <br/>
 * pqrs tuv wxyz<br/>
 * 0      <br/>
 * <br/>
 * Input:   8733<br/>
 * Output:  tree, used
 * </p>
 * Approach:<br/>
 * Follow a Trie based approach to solve this problem optimally.
 *
 * Time Complexity: First a Trie needs to be created from the given dictionary and then search is happening and hence
 * total time complexity will be O(w * l + n * l)
 * where
 *  w = total words in dictionary
 *  n = length of the given num string
 *  l = avg length of the word in dictionary
 *
 *  There are different flavors of this problem such that:
 *  1. Find all words represented by the given num string
 *  2. Find all words starting from the given num string
 *  3. Find all words contained within the given num string
 */
public class T9Dictionary {

    public static void main(String[] args) {
        String[] validWords = new String[]{"use", "used", "tree", "true", "bat", "cat", "mat", "trees"};
        Trie trie = new Trie(validWords);
        // Find all words represented by the given num string
        System.out.println(findAllWordsRepresentingNumString("873", trie)); // Output: [use]
        // Find all words starting from the given num string
        System.out.println(findAllWordsStartingFromNumString("873", trie)); // Output: [tree, trees, use, used]
        // Find all words contained within the given num string
        // TODO
    }

    // 1. This method returns all words represented by the given num string
    static List<String> findAllWordsRepresentingNumString(String numString, Trie trie) {
        List<String> words = new ArrayList<>();
        findAllWordsRepresentingNumStringRecursive(numString, 0, "", trie.getRoot(), words);
        return words;
    }

    private static void findAllWordsRepresentingNumStringRecursive(String numString, int index, String prefix, TrieNode node, List<String> result) {
        if (index == numString.length()) {
            if(node.terminates())
                result.add(prefix);
            return;
        }
        char n = numString.charAt(index); // pick the number character at given index
        char[] letters = getT9Chars(n); // get all linked english characters
        if (letters != null) {
            for (char c : letters) {
                TrieNode child = node.getChild(c);
                if (child != null) {
                    findAllWordsRepresentingNumStringRecursive(numString, index + 1, prefix + c, child, result);
                }
            }
        }
    }

    // 2. This method returns all words starting from the given num string
    static List<String> findAllWordsStartingFromNumString(String numString, Trie trie) {
        List<String> words = new ArrayList<>();
        findAllWordsStartingFromNumStringRecursive(numString, 0, "", trie.getRoot(), words);
        return words;
    }

    private static void findAllWordsStartingFromNumStringRecursive(String numString, int index, String prefix, TrieNode node, List<String> result) {
        if (index == numString.length()) {
            findAllWordsStartingFromTheNode(prefix, node, result);
            return;
        }
        char n = numString.charAt(index); // pick the number character at given index
        char[] letters = getT9Chars(n); // get all linked english characters
        if (letters != null) {
            for (char c : letters) {
                TrieNode child = node.getChild(c);
                if (child != null) {
                    findAllWordsStartingFromNumStringRecursive(numString, index + 1, prefix + c, child, result);
                }
            }
        }
    }

    private static void findAllWordsStartingFromTheNode(String prefix, TrieNode node, List<String> result) {
        if(node.terminates()) result.add(prefix);
        if(node.getChildren().isEmpty()) {
            return;
        }
        for(Character c: node.getChildren().keySet()) {
            findAllWordsStartingFromTheNode(prefix + c, node.getChildren().get(c), result);
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
            {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}
    };
}
