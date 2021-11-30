package com.p.pc.cracking_the_coding_interview.code_library;

import java.util.HashMap;

public class TrieNode {
    private char data; // character stored at this node as data
    private HashMap<Character, TrieNode> children;

    boolean terminates = false; // marks the end of a word

    public TrieNode() {
        children = new HashMap<>();
    }

    public TrieNode(char c) {
        this();
        this.data = c;
    }
    // Add the given word to the Trie and recursively create the child nodes.
    // Please note that the top level TrieNode does not hold any character data itself.
    public void addWord(String word) {
        if(word == null || word.isEmpty()) return;
        TrieNode current = this;
        for(Character c : word.toCharArray()) {
            current = current.children.computeIfAbsent(c, k -> new TrieNode(c));
        }
        current.terminates = true;
    }

    public TrieNode getChild(char c) {
        return children.get(c);
    }

    public void setTerminates(boolean terminates) {
        this.terminates = terminates;
    }

    public char getData() {
        return data;
    }

    public boolean terminates() {
        return terminates;
    }
}
