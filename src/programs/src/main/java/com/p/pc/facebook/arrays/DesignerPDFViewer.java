package com.p.pc.facebook.arrays;

/**
 * Given an array representing the font height for each lowercase alphabet starting from a to z, find out the maximum
 * rectangle area that can fit a given word.
 *
 * Approach:
 *  Find out the maximum height of the font for the given word using the array and then calculate the area.
 */
public class DesignerPDFViewer {
    public static void main(String[] args) {
        int[] h = new int[] {1,3,1,3,1,4,1,3,2,5,5,5,5,1,1,5,5,1,5,2,5,5,5,5,5,5};
        String word = "prince";
        int result = designerPdfViewer(h, word);
        System.out.println(result); // Output = 30
    }

    static int designerPdfViewer(int[] h, String word) {
        int max = 0;
        for(char c: word.toCharArray()) {
            int i = c - 'a'; // get the index for a char, subtracting 'a' gives a based value of 0 for a and so on.
            if(h[i] > max)
                max = h[i];
        }
        return word.length() * max;
    }
}
