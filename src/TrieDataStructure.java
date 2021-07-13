import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sample code to represent Trie data-structure using a simple {@link TrieNode} class.
 * The leaf element of a branch will always have an empty children map and isWord = True to represent the ending of word.
 */
public class TrieDataStructure {
    /**
     * To find all words for a given prefix, first find the last node for the given prefix
     * (i.e. TrieNode at {prefix + 1 extra level down}), then perform a DFS to find all words starting from that node.
     */
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

    /**
     * Perform the recursive DFS search to find all words starting from a given node
     * @param prefix prefix collected so far for a given word
     * @param node current node being checked
     * @param words words collected so far
     */
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

    /**
     * Deleting a word will also require to delete
     * @param word
     * @param node
     * @param index
     * @return
     */
    static boolean deleteWord(String word, TrieNode node, int index) {
        if(word.length() == index) {
            if(!node.isWord)
                return false;
            node.isWord = false;
            return node.children.isEmpty();
        }

        char c = word.charAt(index);
        TrieNode child = node.children.get(c);
        if(child == null) return false;

        boolean isEmptyChild = deleteWord( word, child, index + 1);
        if(isEmptyChild) {
            node.children.remove(c);
            if(node.isWord)
                return false;
            else
                return node.children.isEmpty();
        }
        return false;
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

    public static void main(String[] args) {
        TrieNode root = new TrieNode();
        addWord("Prince", root); addWord("Prince", root); addWord("Priya", root); addWord("Pri", root);
        addWord("Pinki", root);  addWord("Chauda", root);
        addWord("Riya", root); addWord("Rahul", root); addWord("Malik", root);
        addWord("Print", root); addWord("Printf", root);
        System.out.println(findAllWordsForPrefix("", root)); // should print [Pri, Priya, Prince, Print, Printf]
        System.out.println(findWord("Prince", root)); // should print true
        System.out.println(findAllWordsForPrefix("Pri", root)); // should print [Pri, Priya, Prince, Print, Printf]
        deleteWord("Prince", root, 0);
        System.out.println(findAllWordsForPrefix("Pri", root)); // should print [Pri, Priya, Print, Printf]
    }
}
