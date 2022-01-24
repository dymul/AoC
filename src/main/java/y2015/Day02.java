package y2015;

import lib.Day;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day02 implements Day {
    @Override
    public String solve(List<String> input) {
        int total = 0;
        for (String s : input) {
            total+= calcArea(s.split("x"));

        }
        return Integer.toString(total);
    }

    private int calcArea(String[] areas) {
        List<Integer> sides = Arrays.stream(areas).map(Integer::valueOf).sorted().collect(Collectors.toList());
        return 2*sides.get(0)*sides.get(1) +
                2*sides.get(0)*sides.get(2) +
                2*sides.get(1)*sides.get(2) +
                sides.get(0)*sides.get(1);
    }

    @Override
    public String solve2(List<String> input) {
        int total = 0;
        for (String s : input) {
            total+= calcRibbon(s.split("x"));

        }
        return Integer.toString(total);
    }

    private int calcRibbon(String[] areas) {
        List<Integer> sides = Arrays.stream(areas).map(Integer::valueOf).sorted().collect(Collectors.toList());
        return 2*sides.get(0) + 2* sides.get(1)+
                sides.get(0)*sides.get(1)* sides.get(2);
    }
}
