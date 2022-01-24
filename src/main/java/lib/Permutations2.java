package lib;

import java.util.*;
import java.util.stream.Stream;

public class Permutations2 {

    public static <T> Stream<List<T>> asLists(final List<T> items) {
        return permutations(items).stream();
    }

    public static <T> List<List<T>> permutations(final List<T> items) {
        if (items.size() == 1) {
            ArrayList<List<T>> lists = new ArrayList<>();
            lists.add(new ArrayList<>(items));
            return lists;
        }

        List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            LinkedList<T> copy = new LinkedList<>(items);
            T removed = copy.remove(i);
            List<List<T>> permutations = permutations(copy);
            for (List<T> permutation : permutations) {
                permutation.add(0, removed);
            }
            result.addAll(permutations);
        }
        return result;
    }
}