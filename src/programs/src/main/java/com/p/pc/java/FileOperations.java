package com.p.pc.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class FileOperations {
    public static void main(String args[]) throws InterruptedException {
        FileOperations ops = new FileOperations();

        try (BufferedReader bf = new BufferedReader(new FileReader(ops.getClass().getClassLoader().getResource("com.p.pc.java/SomeFile.txt").getFile()))) {
            Stream<String> lines = bf.lines(); // read all lines using stream in a lazy fashion
            lines.forEach(System.out::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
