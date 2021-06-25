import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieDataStructure {

    public static void main(String[] args) {
        TrieNode root = new TrieNode();
        addWord("Prince", root); addWord("Prince", root); addWord("Priya", root); addWord("Pri", root);
        addWord("Pinki", root);  addWord("Chauda", root);
        addWord("Riya", root); addWord("Rahul", root); addWord("Malik", root);
        addWord("Print", root); addWord("Printf", root);

        System.out.println(root);
        System.out.println(findWord("Prince", root)); // should print true
        List<String> allWordsMatchingPrefix = findAllWordsForPrefix("Pri", root);

        System.out.println(allWordsMatchingPrefix); // should print [Pri, Priya, Prince, Print, Printf]
    }

    static List<String> findAllWordsForPrefix(String prefix, TrieNode root) {
        List<String> words = new ArrayList<>();
        TrieNode current = root;
        for(Character c: prefix.toCharArray()) {
            TrieNode nextNode = current.children.get(c);
            if(nextNode == null) return words;
            current = nextNode;
        }
        if(!current.children.isEmpty()) {
            findAllWordsForPrefixRecursively(prefix, current, words);
        } else {
            if(current.isWord) words.add(prefix);
        }
        return words;
    }

    static void findAllWordsForPrefixRecursively(String prefix, TrieNode node, List<String> words) {
        if(node.isWord) words.add(prefix);
        if(node.children.isEmpty()) {
            return;
        }
        for(Character c: node.children.keySet()) {
            findAllWordsForPrefixRecursively(prefix + c, node.children.get(c), words);
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

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord = false;
    }
}
