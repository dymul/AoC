package y2015;

import lib.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day17 implements Day {
    private static int LITERS = 150;

    @Override
    public String solve(List<String> input) throws Exception {
        List<Integer> capacities = capacities(input);
        int result = ways(capacities, LITERS);

        return "" + result;
    }

    @Override
    public String solve2(List<String> input) throws Exception {
        List<Integer> capacities = capacities(input);
        Map<Integer, Integer> result = new HashMap<>();
        ways2(capacities, LITERS, new ArrayList<>(), result);

        return "" + result.get(result.keySet().stream().mapToInt(i -> i).min().orElse(0));
    }

    int ways(List<Integer> capacitiesLeft, int litersToFill) {
        if (capacitiesLeft.isEmpty() && litersToFill > 0) {
            return 0;
        }
        if (litersToFill < 0) {
            return 0;
        }
        List<Integer> remaining = new ArrayList<>(capacitiesLeft);
        int removed = remaining.remove(0);
        if (removed == litersToFill) {
            return 1 + ways(remaining, litersToFill);
        }
        return ways(remaining, litersToFill) + ways(remaining, litersToFill - removed);
    }

    void ways2(List<Integer> capacitiesLeft, int litersToFill, List<Integer> capacitiesUsed, Map<Integer, Integer> results) {
        if (capacitiesLeft.isEmpty() && litersToFill > 0) {
            return;
        }
        if (litersToFill < 0) {
            return;
        }
        List<Integer> remaining = new ArrayList<>(capacitiesLeft);
        List<Integer> newCapacitiesUsed = new ArrayList<>(capacitiesUsed);
        int removed = remaining.remove(0);
        newCapacitiesUsed.add(removed);
        if (removed == litersToFill) {
            results.compute(newCapacitiesUsed.size(), (k,v) -> v == null? 1 : v + 1);
            ways2(remaining, litersToFill, new ArrayList<>(capacitiesUsed), results);
            return;
        }

        ways2(remaining, litersToFill, new ArrayList<>(capacitiesUsed), results);
        ways2(remaining, litersToFill - removed, newCapacitiesUsed, results);
    }


    List<Integer> capacities(List<String> in) {
        return in.stream().map(Integer::parseInt).collect(Collectors.toList());
    }
}
