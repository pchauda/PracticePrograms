package com.p.pc.coinbase;

import java.util.*;

/**
 * Give a list of string list, the inner list represents the related strings, and the question was to find the most relevant
 * string for each string. The most relevant string refers to the most common occurrences of other strings and this string.
 *
 * Input:
 * [['Casper', 'Purple', 'Wayfair'],['Purple', 'Wayfair', 'Tradesy'],['Wayfair', 'Tradesy', 'Peloton']]
 *
 * Output:
 * {
 *    'Casper': ['Purple', 'Wayfair'],
 *    'Purple': ['Wayfair'],
 *    'Wayfair': ['Purple', 'Tradesy'],
 *    'Tradesy': ['Wayfair'],
 *    'Peloton': ['Wayfair', 'Tradesy']
 * }
 */
public class MostRelevantStrings {
    public static void main(String[] args) {
        String[][] input = new String[][] {
                {"Casper", "Purple", "Wayfair"},
                {"Purple", "Wayfair", "Tradesy"},
                {"Wayfair", "Tradesy", "Peloton"}
        };
        Map<String, List<String>> mostRelevantStrings = findMostRelevantStrings(input);
        System.out.println(mostRelevantStrings);
    }

    private static Map<String, List<String>> findMostRelevantStrings(String[][] input) {
        Map<String, List<String>> resultMap = new HashMap<>();
        Map<String, Map<String, Integer>> countMap = new HashMap<>();

        for (String[] currList : input) {
            for (int i =0; i < currList.length; i++) {
                String curr = currList[i];
                Map<String, Integer> temp = countMap.getOrDefault(curr,
                        new HashMap<String, Integer>());
                countMap.put(curr, temp);
                for (int j =0; j < currList.length; j++ ) {
                    if (i==j) {
                        continue;
                    }
                    String related = currList[j];
                    temp.put(related, temp.getOrDefault(related, 0)+1);
                }
            }
        }

        for (String keyWord : countMap.keySet()) {
            Map<String, Integer> temp = countMap.get(keyWord);
            OptionalInt max = temp.keySet().stream().mapToInt(e->temp.get(e)).max();
            List<String> resList =  new ArrayList<>();
            resultMap.put(keyWord, resList);
            for (String word : temp.keySet()) {
                if (temp.get(word) == max.getAsInt()) {
                    //result
                    resList.add(word);
                }
            }
        }

        return resultMap;
    }
}
