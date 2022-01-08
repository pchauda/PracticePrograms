package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Given a list of people with their birth and death years, implement a method to
 * compute the year with the most number of people alive. You may assume that all people were born
 * between 1900 and 2000 (inclusive). If a person was alive during any portion of that year, they should
 * be included in that year's count. For example, Person (birth= 1908, death= 1909) is included in the
 * counts for both 1908 and 1909.</p>
 *
 * Approach: <br/>
 *  Create a Map<Year, Population> using the population array. For each birth year, increase the population by 1 and for each
 *  death year decrease the population by 1 for the next year.
 *  Then iterate over the min and max year and compute the totalPopulation so far and adjust the max population and year accordingly.
 */
public class LivingPeopleCount {
    public static void main(String[] args) {
        int minYear = 1900, maxYear = 2000;
        People[] population = new People[]{
                new People(1900, 1950), new People(1901, 1951), new People(1900, 1950),
                new People(1908, 1920), new People(1900, 2001), new People(1950), new People(1970),
                new People(1900, 1950), new People(1909, 1909), new People(1900, 1950), new People(1970, 1990),
                new People(1910), new People(1910, 1950), new People(1909), new People(1902, 1950)
        };
        System.out.println(Arrays.toString(yearWithMostNumberOfPeople(population, minYear, maxYear))); // Output: [1910, 11]
    }

    private static int[] yearWithMostNumberOfPeople(People[] population, int minYear, int maxYear) {
        Map<Integer, Integer> yearPopulationMap = calculatePopulationByYear(population);
        return computeYearWithMostPeople(yearPopulationMap, minYear, maxYear);
    }

    private static Map<Integer, Integer> calculatePopulationByYear(People[] population) {
        Map<Integer, Integer> yearPopulationMap = new HashMap<>();
        for(People p : population) {
            int birthYear = p.birthYear;
            int deathYear = p.deathYear;
            yearPopulationMap.compute(birthYear, (k ,v) -> v != null ? v + 1 : 1);
            // for death decrement the count for next year as the population still needs to be counted for the death year
            yearPopulationMap.compute(deathYear + 1, (k ,v) -> v != null ? v - 1 : -1);
        }
        return yearPopulationMap;
    }

    private static int[] computeYearWithMostPeople(Map<Integer, Integer> yearPopulationMap, int minYear, int maxYear) {
        int maxPopulation = 0, maxPopulationYear = minYear;
        int populationCount = 0;
        for(int i = minYear; i <= maxYear; i++) {
            populationCount += yearPopulationMap.getOrDefault(i, 0);
            if(populationCount > maxPopulation) {
                maxPopulation = populationCount;
                maxPopulationYear = i;
            }
        }
        return new int[]{maxPopulationYear, maxPopulation};
    }

    static class People {
        int birthYear;
        int deathYear;

        People(int b, int d) {
            this.birthYear = b;
            this.deathYear = d;
        }
        People(int b) {
            this(b, 9999);
        }
    }
}
