package y2020.day_23;

import java.util.LinkedList;
import java.util.List;

public class Main {
    static int rounds = 100;

    public static void main(String[] args) {

        int[] input = solve;
        List<Integer> labels = new LinkedList<>();
        for(Integer i : input) {
            labels.add(i);
        }
        String out = simulate(labels);
        System.out.println(out);

    }

    private static Integer playRound(List<Integer> labels, Integer current) {
        int curr_idx = labels.indexOf(current);
        Integer curr_value = labels.get(curr_idx);
        List<Integer> removed = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            int idxToRemove = curr_idx + 1 < labels.size() ? curr_idx + 1 : 0;
            removed.add(labels.remove(idxToRemove));
        }
        int prev_idx = findPrevIdx(labels, curr_value);
        labels.addAll(prev_idx+1, removed);
        curr_idx = labels.indexOf(current);
        int newCurrIdx = curr_idx + 1 < labels.size() ? curr_idx + 1 : 0;
        return labels.get(newCurrIdx);
    }

    private static int findPrevIdx(List<Integer> labels, Integer curr_value) {
        Integer searched = curr_value;
        while (true) {
            searched = searched == 1 ? searched = 9 : searched - 1;
            if (labels.contains(searched)) {
                return labels.indexOf(searched);
            }
        }
    }

    private static String simulate(List<Integer> labels) {
        int current = labels.get(0);
        for (int i = 0; i < rounds; i++) {
            current = playRound(labels, current);
        }
        String result = "";
        int index = labels.indexOf(1)+1;
        for (int i = 0; i < labels.size()-1; i++) {
            result += labels.get((i+index)%labels.size()).toString();

        }
        return result;
    }



    static int[] test = {
            3,8,9,1,2,5,4,6,7
    };

    static int[] solve = {
            7,8,9,4,6,5,1,2,3
    };



}
