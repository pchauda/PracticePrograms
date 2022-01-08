package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Oh, no! You have accidentally removed all spaces, punctuation, and capitalization in a
 * lengthy document. A sentence like "I reset the computer. It still didn't boot!" became
 * "iresetthecomputeritstilldidntboot". You'll deal with the punctuation and capitalization
 * later; right now you need to re-insert the spaces. Most of the words are in a dictionary but
 * a few are not. Given a dictionary (a list of strings) and the document (a string), design an algorithm
 * to un-concatenate the document in a way that minimizes the number of unrecognized characters.</p>
 * <p>
 * EXAMPLE
 * Input: jesslookedjustliketimherbrother
 * Output: jess looked just like tim her brother (7 unrecognized characters)
 *</p>
 * Approach: Recursion with memoization.
 *  Iterate through the input string, pick a character, check if it has minimum valid chars and recurse.
 *  Add the picked char to the partial string, check if partial string exists in the dictionary, if yes then 0 invalid
 *  chars so far else length of partial becomes total invalid characters.
 */
public class ReSpaceString {
    private static ReSpaceString obj = new ReSpaceString();

    public static void main(String[] args) {
        // Original: We love my computer and alex as well. There is nothing that can beat the human instinct.
        String input = "welovemycomputerandalexaaswellthereisnothingthatcanbeatthehumaninstinct";
        Set<String> dictionary = new HashSet<>(Arrays.asList(
                new String[] {"a", "and", "love", "my", "computer", "rand", "as", "we", "well", "we", "alexa", "there",
                        "are", "no", "nothing", "that", "can", "beat", "the", "human", "instinct"})
        );

        Result result = obj.parseString(input, dictionary, 0, new Result[input.length()]);
        System.out.println("Parsed string: " + result.parsedString + ", invalid count: " + result.invalidCount);
    }

    private Result parseString(String input, Set<String> dictionary, int start, Result[] memo) {
        if(start >= input.length()) {
            return new Result(0, ""); // Zero invalid chars left and parsed string is empty
        }
        if(memo[start] != null) {
            return memo[start];
        }

        int bestInvalid = Integer.MAX_VALUE;
        String bestParsedString = null;

        // iterate through the input string, pick a character, check if it has minimum valid chars and recurse
        String partial = "";
        int index = start;
        while(index < input.length()) {
            char c = input.charAt(index);
            partial += c; // add current char to the partial string
            // check if partial string exists in the dictionary, if yes then 0 invalid chars so far else length of partial
            // becomes total invalid characters
            int invalidCount = dictionary.contains(partial) ? 0 : partial.length();
            if(invalidCount < bestInvalid) { // short circuit
                // recursively check for remaining string
                Result result = parseString(input, dictionary, index + 1, memo);
                if(invalidCount + result.invalidCount < bestInvalid) {
                    bestInvalid = invalidCount + result.invalidCount;
                    bestParsedString = partial + " " + result.parsedString;
                    // short circuit, if best invalid is equal to zero then break as it can't be improved further
                    if(bestInvalid == 0) break;
                }
            }
            index++;
        }
        memo[start] = new Result(bestInvalid, bestParsedString);
        return memo[start];
    }

    static class Result {
        int invalidCount;
        String parsedString;

        Result(int invalidCount, String parsedString) {
            this.invalidCount = invalidCount;
            this.parsedString = parsedString;
        }
    }
}
