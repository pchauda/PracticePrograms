package com.p.pc.facebook.arrays;

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
            int i = c - 'a';
            if(h[i] > max)
                max = h[i];
        }
        return word.length() * max;
    }
}
