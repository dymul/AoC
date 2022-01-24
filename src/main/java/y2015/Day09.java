package y2015;

import lib.Day;
import lib.Permutations;
import lib.Split;

import java.util.*;
import java.util.stream.Stream;

public class Day09 implements Day {
    @Override
    public String solve(List<String> input) throws Exception {
        Integer shortest = allPossibleDistances(input).min(Integer::compareTo).orElse(0);
        return "Shortest: " + shortest;
    }

    @Override
    public String solve2(List<String> input) throws Exception {
        int longest = allPossibleDistances(input).max(Integer::compareTo).orElse(0);
        return "Longest: " + longest;
    }

    private Stream<Integer> allPossibleDistances(List<String> input) {
        Map<String, Integer> distances = new HashMap<>();
        HashSet<String> all_cities = new HashSet<>();
        for (String s : input) {
            Split split = new Split(s, " = ");
            int dist = Integer.parseInt(split.get(1));
            Split cities = new Split(split.get(0), " to ");
            distances.put(cities.get(0) + ":" + cities.get(1), dist);
            distances.put(cities.get(1) + ":" + cities.get(0), dist);
            all_cities.add(cities.get(0));
            all_cities.add(cities.get(1));
        }
        ArrayList<String> cities_list = new ArrayList<>(all_cities);

        return Permutations.asLists(cities_list).map(citiesInOrder -> {
            int distance = 0;
            for (int i = 0; i < citiesInOrder.size() - 1; i++) {
                distance += distances.get(citiesInOrder.get(i) + ":" + citiesInOrder.get(i + 1));
            }
            return distance;
        });
    }
}
