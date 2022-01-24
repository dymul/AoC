package y2015;

import lib.Day;

import java.util.List;
import java.util.stream.Collectors;

public class Day01 implements Day {
    @Override
    public String solve(List<String> input) {
        return Integer.toString(
                String.join("", input).chars()
                        .map(c -> c == (int) '(' ? 1 : -1)
                        .sum()
        );
    }

    @Override
    public String solve2(List<String> input) {
        List<Integer> ints = String.join("", input).chars()
                .map(c -> c == (int) '(' ? 1 : -1).boxed().collect(Collectors.toList());
        int acc = 0;
        int counter = 0;
        for (Integer anInt : ints) {
            counter++;
            acc+=anInt;
            if (acc == -1) return Integer.toString(counter);
        }
        return "FAIL";
    }
}
