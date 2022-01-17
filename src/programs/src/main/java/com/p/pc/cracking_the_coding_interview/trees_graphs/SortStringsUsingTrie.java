package com.p.pc.cracking_the_coding_interview.trees_graphs;

/**
 * Given an array of strings (all lowercase letters), print them in a sorted manner using Trie data structure.
 * Example:
 *  String[] arr = new String[]{"foobar", "bar", "foo"}
 *
 *  Output:
 *      bar foo foobar
 *
 *  Approach:
 *      Instead of holding the characters in a HashMap, use an array as data structure within Tie.
 * Store the index of the element at the leaf node and then traverse the trie using pre-order i.e. checking the values
 * from index 0 of the array of the Trie and recursively checking for all children. The index will be used to read
 * the string from the array directly and hence no need to construct the index on the fly using Trie.
 */
public class SortStringsUsingTrie {
    private static SortStringsUsingTrie obj = new SortStringsUsingTrie();
    private static int MAX_CHAR = 26;

    public static void main(String[] args) {
        String[] arr = new String[] {"foobar", "bar", "foo"};
        obj.printSortedArray(arr);
    }

    /**
     * Create a Trie structure and insert words into it. Then iterate the Trie structure for all array elements at each
     * level in a approach similar to pre-order traversal to print the words in sorted order.
     */
    private void printSortedArray(String[] arr) {
        TrieNode root = new TrieNode();
        for(int i=0; i<arr.length; i++) {
            addWord(root, arr[i], i);
        }
        printArrayUsingTrie(root, arr);
    }

    private void printArrayUsingTrie(TrieNode root, String[] arr) {
        if(root == null) return;
        for(int i=0; i<MAX_CHAR; i++) {
            if(root.children[i] != null) {
                if(root.children[i].index != -1) {
                    System.out.print(arr[root.children[i].index] + " ");
                }
                printArrayUsingTrie(root.children[i], arr);
            }
        }
    }

    private void addWord(TrieNode root, String word, int index) {
        TrieNode node = root;
        for(int i=0; i<word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if(node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.index = index;
    }

    static class TrieNode {
        int index = -1;
        TrieNode[] children = new TrieNode[26];
        TrieNode() {
            for(int i=0; i<MAX_CHAR; i++) {
                children[i] = null; // clear the garbage if any
            }
        }
    }
}
