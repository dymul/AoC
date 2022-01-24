package y2015;

import lib.Day;
import lib.Permutations;
import lib.Split;

import java.util.*;

public class Day13 implements Day {
    @Override
    public String solve(List<String> input) throws Exception {
        Map<String, Integer> hapMap = new HashMap<>();
        Set<String> names = new HashSet<>();

        for (String in : input) {
            in = in.replace(".", "");
            Split split = new Split(in, " ");
            String firstName = split.get(0);
            boolean gain = split.get(2).equals("gain");
            int hap = Integer.parseInt(split.get(3));
            if (!gain) hap*=-1;
            String secName = split.get(10);

            names.add(firstName);
            hapMap.put(firstName + ":" + secName, hap);
        }
        String someName = names.stream().findAny().get();
        names.remove(someName);
        List<List<String>> permutations = Permutations.asListOfLists(new ArrayList<>(names));
        for (List<String> permutation : permutations) {
            permutation.add(someName);
        }

        int max = permutations.stream().mapToInt(p -> getHappines(p, hapMap)).max().getAsInt();


        return "" + max;
    }

    private int getHappines(List<String> permutation, Map<String, Integer> hapMap) {
        int size = permutation.size();
        int happines = 0;
        for (int i = 0; i < size; i++) {
            happines += hapMap.get(permutation.get(i) + ":" + permutation.get(left(i, size)));
            happines += hapMap.get(permutation.get(i) + ":" + permutation.get(right(i, size)));
        }
        return  happines;
    }

    private int left(int i, int size) {
        return i ==0 ? size - 1 : i -1;
    }

    private int right(int i, int size) {
        return i == size - 1 ? 0 : i + 1;
    }


    @Override
    public String solve2(List<String> input) throws Exception {
        Map<String, Integer> hapMap = new HashMap<>();
        Set<String> names = new HashSet<>();

        for (String in : input) {
            in = in.replace(".", "");
            Split split = new Split(in, " ");
            String firstName = split.get(0);
            boolean gain = split.get(2).equals("gain");
            int hap = Integer.parseInt(split.get(3));
            if (!gain) hap*=-1;
            String secName = split.get(10);

            names.add(firstName);
            hapMap.put(firstName + ":" + secName, hap);
        }

        names.forEach(name -> {
            hapMap.put("me:" + name, 0);
            hapMap.put(name + ":me", 0);
        });
        names.add("me");
        String someName = names.stream().findAny().get();
        names.remove(someName);
        List<List<String>> permutations = Permutations.asListOfLists(new ArrayList<>(names));
        for (List<String> permutation : permutations) {
            permutation.add(someName);
        }

        int max = permutations.stream().mapToInt(p -> getHappines(p, hapMap)).max().getAsInt();


        return "" + max;
    }
}
