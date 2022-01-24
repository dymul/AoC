package y2015;

import lib.Day;

import java.util.List;

public class Day08 implements Day {
    @Override
    public String solve(List<String> input) throws Exception {
        Integer allChars = input.stream().map(String::length).reduce(0, Integer::sum);
        Integer escaped = input.stream()
                .map(s -> s.substring(1, s.length() - 1))
                .map(s -> s.replaceAll("\\\\\"", "\""))
                .map(s -> s.replaceAll("\\\\\\\\", "p"))
                .map(s -> s.replaceAll("\\\\x..", "'"))
                .map(String::length).reduce(0, Integer::sum);
        return "" + (allChars-escaped);
    }

    @Override
    public String solve2(List<String> input) throws Exception {
        Integer allChars = input.stream().map(String::length).reduce(0, Integer::sum);
        Integer encoded = input.stream()
                .map(s -> s.substring(1, s.length() - 1))
                .map(s -> "###" + s + "###")
                .map(s -> s.replaceAll("\\\\\"", "@@@@"))
                .map(s -> s.replaceAll("\\\\\\\\", "&&&&"))
                .map(s -> s.replaceAll("\\\\x", "!!!"))
                .map(String::length)
                .reduce(0, Integer::sum);
        return "" + (encoded - allChars);
    }
}
