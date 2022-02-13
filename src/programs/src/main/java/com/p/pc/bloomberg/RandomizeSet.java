package com.p.pc.bloomberg;

import java.util.*;

/**
 * Implement the RandomizedSet class:
 * <p>
 * RandomizedSet() Initializes the RandomizedSet object.
 * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element
 * exists when this method is called). Each element must have the same probability of being returned.
 * <p>
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 * <p>
 * Approach: <br/>
 * HashMap is good for inserting and removing in O(1) however since HashMap is not index based, getting a random
 * element from the list will not be O(1). ArrayList is good for inserting and getting a random element and both operation
 * can be completed in O(1) but removal operation is not O(1) as first the item needs to be located and then removed.
 * Hence, a combination of both should be used to achieve the task in hand.
 */
public class RandomizeSet {
    Map<Integer, Integer> dict; // Map containing <value, index>
    List<Integer> list;
    Random random = new Random();

    RandomizeSet() {
        this.dict = new HashMap<>();
        this.list = new ArrayList<>();
    }

    boolean insert(int val) {
        if(dict.containsKey(val)) return false;
        // insert into both dictionary and the list
        dict.put(val, list.size());
        list.add(list.size(), val); // add at a particular index
        return true;
    }

    boolean remove(int val) {
        if(!dict.containsKey(val)) return false;
        // move the last element to the required index and then delete the last element
        int idx = dict.get(val);
        int lastItem = list.get(list.size() - 1);
        list.set(idx, lastItem);
        dict.put(lastItem, idx);
        // delete the last element
        list.remove(list.size() - 1);
        dict.remove(val);
        return true;
    }

    int getRandom() {
        return list.get(random.nextInt(list.size()));
    }

    void print() {
        System.out.println(list);
    }

    public static void main(String[] args) {
        RandomizeSet obj = new RandomizeSet();
        obj.insert(10);
        obj.insert(5);
        obj.insert(2);
        obj.insert(40);
        obj.insert(13);
        obj.print();
        System.out.println("Random value: " + obj.getRandom());
        System.out.println("Random value: " + obj.getRandom());
        System.out.println("Random value: " + obj.getRandom());
        obj.remove(40);
        obj.print();
    }
}
