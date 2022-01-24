package y2015;

import lib.Day;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day05 implements Day {

    private static final Set<String> vowels = new HashSet<>();
    private static final Set<String> banned = new HashSet<>();

    static {
        vowels.add("a");
        vowels.add("e");
        vowels.add("i");
        vowels.add("o");
        vowels.add("u");
        banned.add("ab");
        banned.add("cd");
        banned.add("pq");
        banned.add("xy");
    }

    @Override
    public String solve(List<String> input) {
        long nice = input.stream().filter(this::nice).count();
        return Long.toString(nice);
    }

    private boolean nice(String s) {
        List<String> letters = Arrays.asList(s.split(""));
        return hasThreeVowels(letters) && twiceInRow(letters) && notContainsBanned(s);
    }

    private boolean twiceInRow(List<String> letters) {
        for (int i = 0; i < letters.size() - 1; i++) {
            if (letters.get(i).equals(letters.get(i + 1))) {
                return true;
            }
        }
        return false;
    }

    private boolean notContainsBanned(String s) {
        for (String b : banned) {
            if (s.contains(b)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasThreeVowels(List<String> letters) {
        return letters.stream().filter(vowels::contains).limit(3).count() == 3;
    }

    @Override
    public String solve2(List<String> input) {
        long nice = input.stream().filter(this::nice2).count();
        return Long.toString(nice);
    }

    private boolean nice2(String s) {
        List<String> letters = Arrays.asList(s.split(""));
        return pairAppearsTwice(letters) && repeatsAfterOne(letters);
    }

    private boolean pairAppearsTwice(List<String> letters) {
        for (int i = 0; i < letters.size() - 1; i++) {
            for (int j = i+2; j < letters.size() - 1; j++) {
                if (letters.get(i).equals(letters.get(j)) &&
                        letters.get(i+1).equals(letters.get(j+1))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean repeatsAfterOne(List<String> letters) {
        for (int i = 0; i < letters.size() - 2; i++) {
            if (letters.get(i).equals(letters.get(i + 2))) {
                return true;
            }
        }
        return false;
    }
}