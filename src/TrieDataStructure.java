import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sample code to represent Trie data-structure using a simple {@link TrieNode} class.
 */
public class TrieDataStructure {

    static List<String> findAllWordsForPrefix(String prefix, TrieNode root) {
        List<String> words = new ArrayList<>();
        TrieNode current = root;
        for(Character c: prefix.toCharArray()) {
            TrieNode nextNode = current.children.get(c);
            if(nextNode == null) return words;
            current = nextNode;
        }
        if(!current.children.isEmpty()) {
            findAllWordsStartingFromTheNode(prefix, current, words);
        } else {
            if(current.isWord) words.add(prefix);
        }
        return words;
    }

    private static void findAllWordsStartingFromTheNode(String prefix, TrieNode node, List<String> words) {
        if(node.isWord) words.add(prefix);
        if(node.children.isEmpty()) {
            return;
        }
        for(Character c: node.children.keySet()) {
            findAllWordsStartingFromTheNode(prefix + c, node.children.get(c), words);
        }
    }

    static boolean findWord(String word, TrieNode root) {
        TrieNode current = root;
        for(Character c: word.toCharArray()) {
            TrieNode child =  current.children.get(c);
            if(child == null) return false;
            current = child;
        }
        return current.isWord;
    }

    static void addWord(String word, TrieNode root) {
        TrieNode current = root;
        for(Character c: word.toCharArray()) {
            current = current.children.computeIfAbsent(c, k -> new TrieNode());
        }
        current.isWord = true;
    }

    static boolean deleteWord(String word, TrieNode current, int index) {
        if (index == word.length()) {
            if (!current.isWord) {
                return false;
            }
            current.isWord = false;
            return current.children.isEmpty();
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) return false;
        boolean shouldDeleteCurrentNode = deleteWord(word, node, index + 1) && !node.isWord;

        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            return current.children.isEmpty();
        }
        return false;
    }

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord = false;
    }

    public static void main(String[] args) {
        TrieNode root = new TrieNode();
        addWord("Prince", root); addWord("Prince", root); addWord("Priya", root); addWord("Pri", root);
        addWord("Pinki", root);  addWord("Chauda", root);
        addWord("Riya", root); addWord("Rahul", root); addWord("Malik", root);
        addWord("Print", root); addWord("Printf", root);
        System.out.println(findWord("Prince", root)); // should print true
        System.out.println(findAllWordsForPrefix("Pri", root)); // should print [Pri, Priya, Prince, Print, Printf]
        deleteWord("Prince", root, 0);
        System.out.println(findAllWordsForPrefix("Pri", root)); // should print [Pri, Priya, Print, Printf]
    }
}
