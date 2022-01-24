package y2020.day_17;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main2 {

    static Map<Point2, String> state = new HashMap<>();
    public static void main(String[] args) {
        String[] in = input;
        parse(in);
        for (int i = 0; i < 6; i++) {
            long count = state.entrySet().stream().filter(e -> e.getValue().equals("#")).count();
            System.out.println("state:" + i + ": " + count);
            transform();
        }
        long count = state.entrySet().stream().filter(e -> e.getValue().equals("#")).count();
        System.out.println(count);

    }

    private static void transform() {
        Set<Point2> extended = new HashSet<>();
        for (Map.Entry<Point2, String> entry : state.entrySet()) {
            extended.addAll(entry.getKey().neighs());
        }
        for (Point2 point : extended) {
            state.putIfAbsent(point, ".");
        }
        Map<Point2, String> newState = new HashMap<>();
        for (Map.Entry<Point2, String> entry : state.entrySet()) {
            long count = countActiveNeigh(entry.getKey());
            if (entry.getValue().equals("#") && count >=2 && count <=3) {
                newState.put(entry.getKey(), "#");
            } else if (entry.getValue().equals("#")) {
                newState.put(entry.getKey(), ".");
            } else if (entry.getValue().equals(".") && count ==3) {
                newState.put(entry.getKey(), "#");
            } else {
                newState.put(entry.getKey(), ".");
            }
        }
        state = newState;
    }

    private static long countActiveNeigh(Point2 key) {
        return key.neighs().stream()
                .filter(k -> state.containsKey(k)).map(k -> state.get(k)).filter(s -> s.equals("#")).count();
    }

    static void parse(String [] arr) {
        for (int i = 0; i < arr.length; i++) {
            String[] split = arr[i].split("");
            for (int j = 0; j < split.length; j++) {
                state.put(new Point2(i,j,0,0), split[j]);
            }
        }
    }

    static String[] input = {
        "...#..#.",
        ".....##.",
        "##..##.#",
        "#.#.##..",
        "#..#.###",
        "...##.#.",
        "#..##..#",
        ".#.#..#."
    };

    static String[] input2 = {
            ".#.",
            "..#",
            "###"
    };



}
