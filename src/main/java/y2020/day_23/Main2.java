package y2020.day_23;

import java.util.*;

public class Main2 {
    static int rounds = 10_000_000;
    //static int rounds = 100;
    static int max = 1_000_000;
    //static int max = 9;


    public static void main(String[] args) {

        int[] input = solve;
        List<Node> nodes = new ArrayList<>();
        for(Integer i : input) {
            nodes.add(new Node(i));
        }
        for (int i = 10; i <= max ; i++) {
            nodes.add(new Node(i));
        }
        for (int i = 0; i < max -1 ; i++) {
            nodes.get(i).next = nodes.get(i+1);
        }
        nodes.get(max -1).next = nodes.get(0);

        Map<Integer, Node> map = new HashMap<>();
        nodes.forEach(node -> map.put(node.value, node));
        map.values().forEach( node -> {
            if (node.value == 1) {
                node.minusOne = map.get(max);
            } else {
                node.minusOne = map.get(node.value -1);
            }
        });

        Node current = nodes.get(0);

        for (int i = 0; i < rounds; i++) {
            Node first = current.next;
            Node second = first.next;
            Node third = second.next;
            Node minusOne = current.minusOne;
            while (minusOne==first || minusOne==second || minusOne==third) {
                minusOne = minusOne.minusOne;
            }
            Node minusOneNext = minusOne.next;

            current.next = third.next;
            minusOne.next = first;
            third.next = minusOneNext;
            current = current.next;
        }

        Node node1 = map.get(1);
        Node next = node1.next;
        Node next2 = next.next;
        System.out.println("v1: " + next.value + ", v2: " + next2.value + " result: "+ ((long) next.value * next2.value));


    }









    static int[] test = {
            3,8,9,1,2,5,4,6,7
    };

    static int[] solve = {
            7,8,9,4,6,5,1,2,3
    };



}
