package com.p.pc.cracking_the_coding_interview.recursion_dp;

import java.util.StringJoiner;

import static com.p.pc.cracking_the_coding_interview.recursion_dp.PaintFillProblem.Color.*;

/**
 * Implement the "paint fill" function that one might see on many image editing programs.
 * That is, given a screen (represented by a two-dimensional array of colors), a point, and a new color,
 * fill in the surrounding area until the color changes from the original color.
 *
 * Approach:
 *  Follow depth first search pattern starting from the given pixel and continue up/down/left/right in a
 *  recursive manner as long as color or the next pixel matches the original color.
 */
public class PaintFillProblem {

    public static void main(String[] args) {
        PaintFillProblem obj = new PaintFillProblem();
        Color[][] screen = new Color[][] { // 4 x 6 matrix
                {R, R, R, R, B, R},
                {R, R, B, B, B, R},
                {R, R, B, R, R, R},
                {R, R, R, B, R, B}
        };
        System.out.println("Original screen:");
        printScreen(screen);
        Color newColor = Y;
        int x = 1, y = 2; // Given point
        paintFill(screen, x, y, newColor);
        System.out.println("New screen:");
        printScreen(screen);
    }

    private static void printScreen(Color[][] screen) {
        int x = screen.length, y=screen[0].length;
        for(int i=0; i<x; i++) {
            StringJoiner joiner = new StringJoiner("," , "{", "}");
            for(int j=0; j<y; j++) {
                joiner.add(screen[i][j].toString());
            }
            System.out.println(joiner);
        }
    }

    private static void paintFill(Color[][] screen, int x, int y, Color newColor) {
        Color originalColor = screen[x][y];
        if(originalColor != newColor) {
            paintFillRecursive(screen, x, y, newColor, originalColor);
        }
    }

    private static void paintFillRecursive(Color[][] screen, int x, int y, Color newColor, Color originalColor) {
        // edge cases
        if(x < 0 || x > screen.length - 1 || y < 0 || y > screen[0].length - 1) return;
        // If color of the new pixed matches with the original color, then re-color and check
        if(screen[x][y] == originalColor) {
            screen[x][y] = newColor;
            paintFillRecursive(screen, x-1, y, newColor, originalColor); // up
            paintFillRecursive(screen, x+1, y, newColor, originalColor); // down
            paintFillRecursive(screen, x, y-1, newColor, originalColor); // left
            paintFillRecursive(screen, x, y+1, newColor, originalColor); // right
        }
    }

    enum Color {
        R("Red"), B("Black"), G("Green"), Y("Green");
        String name;
        Color(String name) {
            this.name = name;
        }
    }
}
