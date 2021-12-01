package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Design an LRU cache with below features:
 * 1. Max size and evict least recently used items if required
 * 2. Keys are integer and values are string
 * 3. Insert and retrieve a value using key
 *
 * Approach:
 *  Since it's a cache it should have O(1) complexity for search and insertion. This means a Map is required here as
 *  a data structure. However, to maintain the LRU feature, Map is not sufficient and hence another data structure like
 *  LinkedList is also required as well.
 */
public class LRUCacheProblem {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);

        cache.put(1, "Foo");
        cache.put(2, "Bar");
        cache.put(3, "Duck");
        cache.put(4, "Donald");
        cache.put(4, "Donald");
        cache.put(4, "Donald");
        cache.put(5, "Donald");
        cache.put(6, "Foo");
        cache.get(5); // access 5 to make it head
        cache.put(7, "Bar");
        cache.put(8, "Duck");

        System.out.println(cache.size());
        cache.getAllElements().stream().forEach(System.out::println);
        System.out.println("Cache head: " + cache.getHead());
        System.out.println("Cache tail: " + cache.getTail());
        System.out.println("Accessing key 7...");
        cache.get(7);
        System.out.println("Cache head: " + cache.getHead());
        System.out.println("Cache tail: " + cache.getTail());
    }

    public static class LRUCache {
        private int maxElements;
        private Map<Integer, LinkedListNode> cache = new HashMap<>();
        private LinkedListNode head = null;
        private LinkedListNode tail = null;

        // implement LRU approach during get i.e. mark the visited node as head
        public String get(int key) {
            LinkedListNode node = cache.get(key);
            if(node == null) return null;
            // if node is not the head then move it to head
            if(node != head) {
                removeFromList(node);
                insertAtHead(node);
            }
            return node.value;
        }
        // add a new element, check for max limit, if reached then remove tail from both map and list
        public void put(int key, String value) {
            // remove key if exists already
            remove(key);
            // remove the tail if max limit reached
            if(cache.size() >= maxElements && tail != null) {
                remove(tail.key); // Remove tail from both map and from the list
            }
            // add a new node
            LinkedListNode node = new LinkedListNode(key, value);
            insertAtHead(node);
            cache.put(key, node);
        }
        // remove the given key from both list and map
        public boolean remove(int key) {
            LinkedListNode node = cache.get(key);
            if(node == null) return false;
            // remove from linked list
            removeFromList(node);
            // remove from map
            cache.remove(key);
            return true;
        }
        // insert the node at head
        private void insertAtHead(LinkedListNode node) {
            if(head == null) {
                head = node;
                tail = node;
            } else {
                head.prev = node;
                node.next = head;
                head = node;
            }
        }
        // remove a given node
        private void removeFromList(LinkedListNode node) {
            if(node.prev != null) node.prev.next = node.next;
            if(node.next != null) node.next.prev = node.prev;
            if(node == tail) tail = node.prev;
            if(node == head) head = node.next;
        }

        private static class LinkedListNode {
            int key;
            String value;
            LinkedListNode next;
            LinkedListNode prev;

            LinkedListNode(int key, String value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public String toString() {
                return "LinkedListNode{" +
                        "key=" + key +
                        ", value='" + value + '\'' +
                        '}';
            }
        }

        public Collection<LinkedListNode> getAllElements() {
            return cache.values();
        }

        public LRUCache(int maxElements) {
            this.maxElements = maxElements;
        }

        public LinkedListNode getHead() {
            return head;
        }

        public LinkedListNode getTail() {
            return tail;
        }

        public int size() {
            return cache.size();
        }
    }

}
