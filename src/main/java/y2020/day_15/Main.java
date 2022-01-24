package y2020.day_15;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        long[] arr = new long[30000000];
        Map<Long, List<Integer>> map = new HashMap<>();
        long[] in = input;

        for (int i = 0; i < in.length; i++) {
            arr[i] = in[i];
            map.put(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }
        for (int i = in.length; i < arr.length; i++) {
            List<Integer> list = map.get(arr[i-1]);
            if (list.size() == 1) {
                arr[i] = 0;
            } else {
                arr[i] = list.get(list.size()-1) - list.get(list.size()-2);
            }
            if (map.containsKey(arr[i])) {
                map.get(arr[i]).add(i);
            } else {
                List<Integer> cuuList = new ArrayList<>();
                cuuList.add(i);
                map.put(arr[i], cuuList );
            }
        }
        System.out.println(arr[arr.length-1]);
    }

    static long[] input = {
            19,20,14,0,9,1
    };

    static long[] in1 = {
            0,3,6
    };

    static long[] in2 = {
            2,3,1
    };



}
