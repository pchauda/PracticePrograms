package com.p.pc.cracking_the_coding_interview.recursion_dp;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Implement an algorithm to print all valid (i.e., properly opened and closed) combinations
 * of n pairs of parentheses. <br/>
 * EXAMPLE <br/>
 * Input: 3 <br/>
 * Output: (( () ) ) , ( () () ) , ( () ) () , () ( () ) , () () ()
 * </p>
 * Approach: <br/>
 * Break condition: If no more left brackets left, or remaining right brackets are less than left brackets <br/>
 * Collect: If remaining left and right brackets are zero then collect the string <br/>
 * Recursive: <br/>
 * i. Pick left bracket, collect in the temp char array, recursively collect other brackets. <br/>
 * ii. Pick right bracket, collect in the temp char array, recursively collect other brackets. <br/>
 */
public class ParenthesesCombination {
    static ParenthesesCombination obj = new ParenthesesCombination();

    public static void main(String[] args) {
        int pairs = 10;
        List<String> parens = obj.generateParens(pairs);
        System.out.println("Total parentheses count: " + parens.size());
        System.out.println("All parentheses: " + parens);
    }

    private List<String> generateParens(int pairs) {
        List<String> result = new ArrayList<>();
        char[] temp = new char[pairs * 2];
        calculateCombinations(pairs, pairs, result, temp, 0);
        return result;
    }

    private void calculateCombinations(int left, int right, List<String> result, char[] temp, int index) {
        // break condition
        if (left < 0 || right < left) return; // invalid combination
        if (left == 0 && right == 0) {
            result.add(String.valueOf(temp)); // Add the string to the result
        } else {
            temp[index] = '('; // use the left bracket
            // recursively collect remaining brackets
            calculateCombinations(left - 1, right, result, temp, index + 1);
            temp[index] = ')'; // use the right bracket
            calculateCombinations(left, right - 1, result, temp, index + 1);
        }
    }


}
