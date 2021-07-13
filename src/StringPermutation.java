import java.util.ArrayList;
import java.util.List;

public class StringPermutation {
    public static void main(String[] args) {
        var input = "ABCD";

        List<String> allPermutations = new ArrayList<>();
        printPermutations(input, "", allPermutations);
        System.out.println(allPermutations);
        System.out.println(allPermutations.size());
    }

    /**
     * For each character in the string, remove the character and collect it as prefix and invoke
     * permutation method recursively using the remaining string and prev prefix + character removed
     */
    static void printPermutations(String s, String prefix, List<String> allPermutations) {
        if(s.length() == 0) {
            allPermutations.add(prefix);
        }
        for(int i=0; i < s.length(); i++) {
            String suffix = s.substring(0, i) + s.substring(i + 1);
            printPermutations(suffix, prefix + s.charAt(i), allPermutations);
        }
    }
}
