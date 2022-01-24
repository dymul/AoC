package y2015;

import lib.Day;
import lib.Split;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day14 implements Day {

    private static int TIME = 2503;

    @Override
    public String solve(List<String> input) throws Exception {
        int distance = input.stream()
                .map(this::fromLine)
                .map(this::toDistance)
                .mapToInt(a -> a)
                .max().orElseThrow();
        return "" + distance;
    }

    @Override
    public String solve2(List<String> input) throws Exception {
        List<Raindeer> raindeerList = input.stream()
                .map(this::fromLine).collect(Collectors.toList());
        Map<String, Integer> points = new HashMap<>();
        raindeerList.forEach(r -> points.put(r.name, 0));

        for (int i = 1; i < TIME; i++) {
            int currTime = i;
            Map<String, Integer> roundResult = new HashMap<>();
            raindeerList.forEach(r -> {
                int distance = toDistance(r, currTime);
                roundResult.put(r.name, distance);
            });
            int max = roundResult.values().stream().mapToInt(a -> a).max().orElseThrow();
            roundResult.forEach((name, result) ->  {
                if (result == max) {
                    points.put(name, points.get(name) + 1);
                }
            });
        }

        int winner = points.values().stream().mapToInt(a -> a).max().orElseThrow();

        return "" + winner;
    }

    private Raindeer fromLine(String s) {
        Split split = new Split(s, " ");
        return new Raindeer(split.get(0), Integer.parseInt(split.get(3)), Integer.parseInt(split.get(6)), Integer.parseInt(split.get(13)));
    }

    private int toDistance(Raindeer r) {
        return toDistance(r, TIME);
    }

    private int toDistance(Raindeer r, int time) {
        int total = 0;

        int cycles = time / (r.time + r.rest);
        total += cycles*r.speed*r.time;
        int remainingTime = time % (r.time + r.rest);
        int runningTime = remainingTime > r.time ? r.time : remainingTime;
        total += runningTime*r.speed;

        return total;
    }
}

class Raindeer {
    String name;
    int speed;
    int time;
    int rest;

    Raindeer(String name, int speed, int time, int rest) {
        this.name = name;
        this.speed = speed;
        this.time = time;
        this.rest = rest;
    }
}
