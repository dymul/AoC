package y2015;

import lib.Day;

import java.util.Arrays;
import java.util.List;

public class Day18 implements Day {
    @Override
    public String solve(List<String> input) throws Exception {
        boolean[][] origin = parse(input);
        boolean[][] copy = new boolean[origin.length][origin[0].length];

        for (int rounds = 0; rounds < 100; rounds++) {
            //print(rounds, origin);
            playRound(origin, copy);
            origin = copy;
            copy = new boolean[origin.length][origin[0].length];
        }
        //print(4, origin);

        int result = calculateOn(origin);

        return "" + result;
    }

    void print(int iter, boolean[][] arr) {
        System.out.println("After " + iter + " rounds");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] ? "#" : ".");
            }
            System.out.println();
        }


        System.out.println("===========================\n");
    }

    int calculateOn(boolean[][] origin) {
        int rows = origin.length;
        int cols = origin[0].length;
        int result = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (origin[i][j]) {
                    result++;
                }
            }
        }
        return result;
    }

    private void playRound(boolean[][] origin, boolean[][] copy) {
        int rows = origin.length;
        int cols = origin[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean alive = origin[i][j];
                int neighs = calculateAliveNeighs(origin, i, j);
                if (alive && (neighs == 2 || neighs == 3)) {
                    copy[i][j]  = true;
                } else if (!alive && neighs == 3) {
                    copy[i][j]  = true;
                } else {
                    copy[i][j]  = false;
                }
            }
        }
    }

    private int calculateAliveNeighs(boolean[][] origin, int i, int j) {
        int result = 0;
        int rows = origin.length;
        int cols = origin[0].length;
        for (int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j+1 ; l++) {
                if (k >=0 && k < rows && l >=0 && l < cols && origin[k][l]) {
                    if (k == i && l == j) {
                        continue;
                    }
                    result++;
                }
            }

        }
        return result;
    }

    @Override
    public String solve2(List<String> input) throws Exception {
        boolean[][] origin = parse(input);
        boolean[][] copy = new boolean[origin.length][origin[0].length];
        turnOnCorners(origin);
        for (int rounds = 0; rounds < 100; rounds++) {
            //print(rounds, origin);
            playRound(origin, copy);
            origin = copy;
            turnOnCorners(origin);
            copy = new boolean[origin.length][origin[0].length];
        }
        //print(5, origin);

        int result = calculateOn(origin);

        return "" + result;
    }

    void turnOnCorners(boolean[][] origin) {
        int rows = origin.length;
        int cols = origin[0].length;
        origin[0][0] = true;
        origin[0][cols-1] = true;
        origin[rows-1][0] = true;
        origin[rows-1][cols-1] = true;
    }

    boolean[][] parse(List<String> in) {
        int rows = in.size();
        int cols = in.get(0).length();

        boolean[][] result = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] cells = in.get(i).split("");
            for (int j = 0; j < cols; j++) {
                result[i][j] = cells[j].equals("#");
            }
        }
        return result;
    }

}
