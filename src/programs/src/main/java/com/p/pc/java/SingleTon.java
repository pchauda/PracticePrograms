package com.p.pc.java;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class SingleTon {

    public static void main(String[] args) {
        MySingleTon singleTon = MySingleTon.getInstance();
    }

    // Enums are Thread Safe by design and also serializable, hence defining singleton using Enum is better than creating
    // singleton using double check. It is also safe from reflection as JVM makes sure only one instance of the enum exists
    // However, singleton using Enum is not lazy loaded.
    enum MySingleTon {
        INSTANCE;

        private final FileReader fr;

        MySingleTon() {
            try {
                this.fr = new FileReader("");
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Problem while initialization.");
            }

        }

        public FileReader getFr() {
            return fr;
        }

        public static MySingleTon getInstance() {
            return INSTANCE;
        }
    }

    class MySingleTonClass {

        private volatile MySingleTonClass instance; // Volatile guarantees the happens before relationship starting Java 6.

        MySingleTonClass getInstance() {
            if(instance == null) {
                synchronized (MySingleTon.class) {
                    if(instance == null)
                        instance = new MySingleTonClass();
                }
            }
            return instance;
        }
    }

}
