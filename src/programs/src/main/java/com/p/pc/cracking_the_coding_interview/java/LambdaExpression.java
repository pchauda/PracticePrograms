package com.p.pc.cracking_the_coding_interview.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class LambdaExpression {

    static class Country {
        String name;
        String continent;
        int population;

        Country(String name, String continent, int population) {
            this.name = name;
            this.continent = continent;
            this.population = population;
        }
        public int getPopulation() {
            return population;
        }

        public String getContinent() {
            return continent;
        }
    }

    public static void main(String[] args) {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("India", "Asia", 10000));
        countries.add(new Country("China", "Asia", 15000));
        countries.add(new Country("Pakistan", "Asia", 25000));
        countries.add(new Country("Germany", "Europe", 18000));

        LambdaExpression obj = new LambdaExpression();
        System.out.println(obj.getPopulation(countries, "Asia"));
        List<Country> randomSubSet = obj.getRandomSubSet(countries);
        // Print countries
        System.out.println(
            Arrays.toString(
                randomSubSet.stream().map(t -> t.name).collect(Collectors.toList()).toArray()
            )
        );
    }

    int getPopulation(List<Country> countries, String continent) {
        return countries.stream().filter(c -> c.getContinent() == continent)
                .map(t -> t.getPopulation())
                .reduce(0, (a, b) -> a + b);
    }

    /**
     * Implement a method to return a random subset of arbitrary size .
     * Approach:
     *  Iterate over a list and for each entry flip a coin.
     * @param list
     * @return
     */
    List<Country> getRandomSubSet(List<Country> list) {
        Random random = new Random();
        return list.stream().filter(t -> random.nextBoolean()).collect(Collectors.toList());
    }
}
