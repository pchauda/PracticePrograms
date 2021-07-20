package com.p.pc.java;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapTest {
    HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        HashMapTest obj = new HashMapTest();
        Thread t1 = new Thread(new MyRunnable(obj.map));
        Thread t2 = new Thread(new MyRunnable2(obj.map));

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(obj.map.keySet().size());
    }
    static class MyRunnable implements Runnable {
        HashMap<Integer, Integer> map = new HashMap<>();

        MyRunnable(HashMap map) {
            this.map = map;
        }

        @Override
        public void run() {
            for(int i = 0 ; i < 100 ; i++) {
                map.put(i, i);
            }
        }
    }

    static class MyRunnable2 implements Runnable {
        HashMap<Integer, Integer> map = new HashMap<>();

        MyRunnable2(HashMap map) {
            this.map = map;
        }

        @Override
        public void run() {
            Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }
}
