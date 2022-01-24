package y2015;

import lib.Day;
import lib.Point;

import java.util.*;

public class Day03 implements Day {
    @Override
    public String solve(List<String> input) {
        Map<Point, Integer> points = new HashMap<>();
        List<String> split = Arrays.asList(input.get(0).split(""));
        Point start = new Point(0,0);
        points.put(start, 1);
        deliver(points, split, start);

        return Integer.toString(points.size());
    }

    private void deliver(Map<Point, Integer> points, List<String> split, Point start) {
        for (String s : split) {
            Point newPoint;
            switch (s) {
                case "<":
                    newPoint = new Point(start.getX() - 1, start.getY());
                    break;
                case ">":
                    newPoint = new Point(start.getX() + 1, start.getY());
                    break;
                case "^":
                    newPoint = new Point(start.getX(), start.getY() - 1);
                    break;
                default:
                    newPoint = new Point(start.getX(), start.getY() + 1);
                    break;
            }
            points.compute(newPoint,(k,v) -> v==null ? 1 : v+1);
            start = newPoint;
        }
    }

    @Override
    public String solve2(List<String> input) {
        Map<Point, Integer> points = new HashMap<>();
        List<String> split = Arrays.asList(input.get(0).split(""));
        Point start = new Point(0,0);
        points.put(start, 1);
        List<String> santa = new LinkedList<>();
        List<String> robosanta = new LinkedList<>();
        int i = 0;
        for (String s : split) {
            if (i%2==0) {
                santa.add(s);
            }else {
                robosanta.add(s);
            }
            i++;
        }

        deliver(points, santa, start);
        deliver(points, robosanta, start);

        return Integer.toString(points.size());
    }
}
