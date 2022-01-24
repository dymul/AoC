package y2020.day_22;

import java.util.*;
import java.util.stream.Collectors;

public class Main2 {

    public static void main(String[] args) {

        int[][] input = in2;

        List<Integer> p1 = parse(input[0]);
        List<Integer> p2 = parse(input[1]);
        play(p1,p2);
        long score  = calculateScore(p1.size() > 0 ? p1 : p2);
        System.out.println(score);

    }

    private static boolean play(List<Integer> p1, List<Integer> p2) {
        Set<List<Integer>> prevRound = new HashSet<>();
        while (p1.size() > 0 && p2.size() > 0) {
            if (seen(prevRound, p1, p2)) {
                return true;
            }
            playRound(p1,p2);
        }
        return p1.size() > 0;
    }

    private static boolean seen(Set<List<Integer>> prevRound, List<Integer> p1, List<Integer> p2) {
        List<Integer> entry = new LinkedList<>();
        entry.addAll(p1);
        entry.add(-1);
        entry.addAll(p2);
        if (prevRound.contains(entry)) {
            return true;
        } else {
            prevRound.add(entry);
        }
        return false;
    }

    private static void playRound(List<Integer> p1, List<Integer> p2) {
        Integer p1Card = p1.remove(0);
        Integer p2Card = p2.remove(0);
        boolean p1wins;
        if (p1.size()>=p1Card && p2.size()>=p2Card) {
            List<Integer> newP1 = p1.stream().limit(p1Card).collect(Collectors.toList());
            List<Integer> newP2 = p2.stream().limit(p2Card).collect(Collectors.toList());
            p1wins = play(newP1, newP2);

        } else {
            p1wins = p1Card > p2Card;
        }

        if (p1wins) {
            p1.add(p1Card);
            p1.add(p2Card);
        } else {
            p2.add(p2Card);
            p2.add(p1Card);
        }
    }

    private static long calculateScore(List<Integer> integers) {
        long score = 0;
        int size = integers.size();
        while (size > 0) {
            Integer top = integers.remove(0);
            score+=top*size;
            size--;
        }
        return score;
    }

    private static List<Integer> parse(int[] ints) {
        List<Integer> result = new LinkedList<>();
        for(Integer i : ints) {
            result.add(i);
        }
        return result;
    }

    static int[][] in = {
            {
                    9,
                    2,
                    6,
                    3,
                    1
            },
            {
                    5,
                    8,
                    4,
                    7,
                    10
            }
    };

    static int[][] in3 = {
            {
                    43,19
            },
            {
                    2,29,14
            }
    };

    static int[][] in2 = {
            {
                    44,24,36,6,27,46,33,45,47,41,15,23,40,38,43,42,25,5,30,35,34,13,29,1,50
            },
            {
                    32,28,4,12,9,21,48,18,31,39,20,16,3,37,49,7,17,22,8,26,2,14,11,19,10
            }

    };



}
