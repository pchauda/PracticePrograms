import java.util.*;

public class Test {
    public static void main(String arg[]) {
        System.out.println("Calling f()");

        Test t = new Test();
        t.f();

        LinkedHashSet<Character> chars = new LinkedHashSet<>();

        String s;
        Math.max(10, 9);

        String name = "Prince Chauda";
        System.out.println(name.indexOf("CHauda"));

    }

    void f() {
        try {
            throw new RuntimeException("Exception 1");
        } catch (Exception ex) {
        } finally {
            System.out.println("In finally");
        }
    }

    public int lengthOfLongestSubstring(String s) {
        if(s == null) return 0;
        int len = s.length();
        if(len <= 1) return len;
        int i=0, j=0, result = 0;
        Set<Character> chars = new HashSet<>();
        while(i < len && j < len) {
            if(!chars.contains(s.charAt(j))) {
                chars.add(s.charAt(j++));
                result = Math.max(result, j - i);
            } else {
                chars.remove(s.charAt(i++));
            }
        }
        return result;
    }

    public int lengthOfLongestSubstringOptimized(String s) {
        if(s == null) return 0;
        int len = s.length();

        if(len <= 1) return len;

        int result = 0;
        // Retain the index of the character and move i to the Max(index + 1, i) when there is duplicate character encountered.
        Map<Character, Integer> charsMap = new HashMap<>();
        for(int i = 0, j = 0; j < s.length(); j++) {
            if(charsMap.containsKey(s.charAt(j))) {
                i = Math.max(charsMap.get(s.charAt(j)) + 1, i); // Max is required so that i does not move in backward direction
            }
            result = Math.max(result, j - i + 1);
            charsMap.put(s.charAt(j), j);
        }
        return result;
    }
}
