package com.p.pc.cracking_the_coding_interview.code_library;

import java.util.List;

public class Trie {
    private TrieNode root;
    // Add a list of words to the Trie
    public Trie(List<String> words) {
        root = new TrieNode();
        for(String word : words) {
            root.addWord(word);
        }
    }
    // Add an array of words to the Trie
    public Trie(String[] words) {
        root = new TrieNode();
        for(String word : words) {
            root.addWord(word);
        }
    }
    // Returns true if the prefix is contained exactly (end of word) or not
    public boolean contains(String prefix, boolean exact) {
        TrieNode tmp = root;
        if(prefix == null || prefix.isEmpty()) return false;
        for(int i=0; i < prefix.length(); i++) {
            tmp = tmp.getChild(prefix.charAt(i));
            if(tmp == null) return false;
        }
        return !exact || tmp.terminates();
    }

    public boolean contains(String prefix) {
        return contains(prefix, false);
    }

    public TrieNode getRoot() {
        return root;
    }
}
