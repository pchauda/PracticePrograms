package com.p.pc.leetcode;

import java.util.*;

/**
 * Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0]
 * is a name, and the rest of the elements are emails representing emails of the account.
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common
 * email to both accounts. Note that even if two accounts have the same name, they may belong to different people as
 * people could have the same name. A person can have any number of accounts initially, but all of their accounts
 * certainly have the same name.
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name,
 * and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 * Example:
 * Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],
 * ["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 */
public class AccountsMerge {

    public static void main(String[] args) {
        String[][] accounts = new String[][]{
                {"John","johnsmith@mail.com","john_newyork@mail.com"},
                {"John","johnsmith@mail.com","john00@mail.com"},
                {"Mary","mary@mail.com"},
                {"John","johnnybravo@mail.com"}
        };

        List<List<String>> mergedAccounts = mergeAccounts(accounts);
        System.out.println(mergedAccounts);
    }
    static Set<String> visited = new HashSet<>();
    static Map<String, List<String>> adjacencyList = new HashMap<>();
    //
    private static List<List<String>> mergeAccounts(String[][] accounts) {
        // Create an adjacency list for all the emails
        for(String[] account : accounts) {
            int size = account.length;
            // first entry is the name and entries going forward are the emails
            String firstEmail = account[1];
            for(int i=2; i<size; i++) {
                String email = account[i];
                if(!adjacencyList.containsKey(firstEmail)) {
                    adjacencyList.put(firstEmail, new ArrayList<>());
                }
                adjacencyList.get(firstEmail).add(email);
                if(!adjacencyList.containsKey(email)) {
                    adjacencyList.put(email, new ArrayList<>());
                }
                adjacencyList.get(email).add(firstEmail);
            }
        }
        // Iterate over the accounts and collect the merged accounts using adjacency list
        List<List<String>> mergedAccounts = new ArrayList<>();
        for(String[] account : accounts) {
            String name = account[0];
            String email = account[1];
            if(!visited.contains(email)) {
                List<String> mergedAccount = new ArrayList<>();
                mergedAccount.add(name);
                DFS(mergedAccount, email);
                Collections.sort(mergedAccount.subList(1, mergedAccount.size())); // sort the merged account only for emails
                mergedAccounts.add(mergedAccount);
            }
        }
        return mergedAccounts;
    }

    private static void DFS(List<String> mergedAccount, String email) {
        mergedAccount.add(email);
        visited.add(email); // add to the visited
        if(!adjacencyList.containsKey(email)) return;
        for(String connectedEmail : adjacencyList.get(email)) {
            if(!visited.contains(connectedEmail))
                DFS(mergedAccount, connectedEmail);
        }
    }
}
