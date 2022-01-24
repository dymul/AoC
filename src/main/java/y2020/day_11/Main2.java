package y2020.day_11;


import java.util.LinkedList;
import java.util.List;

public class Main2 {

    public static void main(String[] args) {
        State[][] board = parseInput(in2);
        State[][] copy = copy(board);
        boolean changed = true;
        int cnt =1;
        while (changed) {
            print(board);
            System.out.println("transfer " + cnt++);
            changed = transform(board, copy);
            board = copy;
            copy = copy(board);
        }

        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == State.OCC) {
                    count++;
                }
            }
        }
        System.out.println(count);

        System.out.println("hello");
    }

    static void print(State[][] board) {
        System.out.println("----------------");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == State.FREE) {
                    System.out.print("L");
                } else if (board[i][j] == State.OCC) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println("----------------");
    }

    static boolean transform(State[][] input, State[][] copy) {
        boolean changed = false;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j].equals(State.FREE) &&
                    noOCC(input, i, j)) {
                    copy[i][j] = State.OCC;
                    changed = true;
                }
                if (input[i][j].equals(State.OCC) &&
                numberOCC(input, i, j) >=5) {
                    copy[i][j] = State.FREE;
                    changed = true;
                }

            }
        }
        return changed;
    }

    private static long numberOCC(State[][] input, int i, int j) {
        return neighs(input, i, j).stream().filter(s-> s.equals(State.OCC)).count();
    }

    private static boolean noOCC(State[][] input, int i, int j) {
        return neighs(input, i, j).stream().noneMatch(s->s.equals(State.OCC));
    }

    private static List<State> neighs(State[][] input, int i, int j) {
        List<State> result = new LinkedList<>();
        //top
        int i1 = i-1;
        int j1 = j;
        while (i1 >=0) {
            if (input[i1][j1] != State.FLOOR) {
                result.add(input[i1][j1]);
                break;
            }
            i1--;
        }
        //topleft
        i1=i-1;j1=j-1;
        while (i1 >=0 && j1>=0) {
            if (input[i1][j1] != State.FLOOR) {
                result.add(input[i1][j1]);
                break;
            }
            i1--;j1--;
        }
        //left
        i1=i;j1=j-1;
        while (j1>=0) {
            if (input[i1][j1] != State.FLOOR) {
                result.add(input[i1][j1]);
                break;
            }
            j1--;
        }
        //downleft
        i1=i+1;j1=j-1;
        while (i1 <input.length && j1>=0) {
            if (input[i1][j1] != State.FLOOR) {
                result.add(input[i1][j1]);
                break;
            }
            i1++;j1--;
        }
        //down
        i1 = i+1;
        j1 = j;
        while (i1 <input.length) {
            if (input[i1][j1] != State.FLOOR) {
                result.add(input[i1][j1]);
                break;
            }
            i1++;
        }
        //downright
        i1=i+1;j1=j+1;
        while (i1 <input.length && j1<input[i].length) {
            if (input[i1][j1] != State.FLOOR) {
                result.add(input[i1][j1]);
                break;
            }
            i1++;j1++;
        }
        //right
        i1=i;j1=j+1;
        while (j1<input[i].length) {
            if (input[i1][j1] != State.FLOOR) {
                result.add(input[i1][j1]);
                break;
            }
            j1++;
        }
        //topright
        i1=i-1;j1=j+1;
        while (i1 >=0 && j1<input[i].length) {
            if (input[i1][j1] != State.FLOOR) {
                result.add(input[i1][j1]);
                break;
            }
            i1--;j1++;
        }
        return result;
    }


    static State[][] copy (State[][] state) {
        State[][] copy = new State[state.length][];
        for (int i = 0; i < state.length; i++) {
            copy[i] = new State[state[i].length];
            for (int j = 0; j < state[i].length; j++) {
                copy[i][j] = state[i][j];
            }
        }
        return copy;
    }


    static State[][] parseInput(String[] input) {
        State[][] out = new State[input.length][];
        for (int i = 0; i < input.length; i++) {
            out[i] = parse(input[i]);
        }
        return out;
    }

    static private State[] parse(String input) {
        State[] out = new State[input.length()];
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'L') {
                out[i] = State.OCC;
            } else {
                out[i] = State.FLOOR;
            }
        }
        return out;
    }


    static String[] input = {
            "L.LL.LL.LL",
            "LLLLLLL.LL",
            "L.L.L..L..",
            "LLLL.LL.LL",
            "L.LL.LL.LL",
            "L.LLLLL.LL",
            "..L.L.....",
            "LLLLLLLLLL",
            "L.LLLLLL.L",
            "L.LLLLL.LL"
    };

    static String[] in2 = {
            "LLLLLLLLLL.LLLL.LLLLLLL.LLLLL.LLLLLLLLLLLLLLLLLLLLLL.LL.LLLLLLLLLLL.LLLLLL.LL.LLLL.LLLLLL.LLLLLLL",
            "LL.LLLLLLL.LLLLLLLLLLLL.LLLLL.LLLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLLL.LL.LLLL.LLLLLL.LLLLLLL",
            "LLLLLL.LLLLLLLLLLLLLLLL.LLL.L.LLLLLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLL.LLLL.L.LLLLLLL",
            "LLLLLLLLLL.LLLL.LLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL",
            "LLLLLLLLLLLLLLL.LLLLLLL.LLLLL.LLLLLL.L.LLLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLL",
            "LLLLLLLLLL.LLLLLLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLL.LLLLLLL.LLLLLL.LLLLLLL",
            "LLLLLLLLLL.LLLL.LLLLLLL.LLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLLLLLLLL.LLLLLL.LLLLLLL",
            "LLLLLLLLLL.LLLL.LLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL",
            "L.L....L...L..LLLLLL.L.LLL..L...L.....L.L...L..L.LL.....L..L..LL...L........L..LLLLLLL.L......L..",
            "LLLLLLLLLL.LLLLLLLL.LLL.LLLLLLLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL",
            "LLLLLLLLLLLLLLLLLLLLLLL.LLLL..LLLLLLLL.LL.LLLLLLL.LLLLLLLLLLLLLL.LL.LLLLLLLLLLLLLL.LLLLLLLLLLLLLL",
            "LLL.LLLLLL.LLLL.LLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLLL.L.LLLLLLL.LLLLLLLLLLLLLL",
            "LLLLLLLLLLLLLLL..L.LLLL.LLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLL",
            "LLLLLLL.LLLLLLL.LLLLLLL.LLLLL.LLL.LLLLLLLLLLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLL",
            "LLLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLLLLLLL",
            "LLLLLLLLLL.LLLL.LLLLLLLLLL.LL.LLLLLLLL.LLLLLLLL...LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL",
            "LLLLLLLLLLLLL.LLLLLLLLL.LLLLL.LLLLLLL..LLLLLL.LLLLLLLLLLL.LLLLLL.LLLLLLLLL.LL.LLLLLLLLLLLLLLLLLLL",
            "..L...L..LL.L....LL.L...LLLL......L...L....L...L.L...L..L..L...L.....L..L...................LLL.L",
            "LLLLLLLLLLLL.LLLLLLL.LL.LLLLL.LLLLLLLL.LLLLLLLLL.LLLLL..LLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL",
            "LLLLLLLLLL.LLLL.LLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLL.LL.LLLL.LLLLLL.LLLLLLL",
            "LLLLLLLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLL.L.LLLLLLLLLLLL.LLLLLLL",
            "LLLLLLLLLLLLLLL.LLLLLLL.LLLLL.LLLLLLLLLLLLLLLLLL.LLLLLL.L.LLLLLL.LLLLLLLLLLL.LLLL..LLLLLL.LLLLLLL",
            "LLLLLLLLLL.LLLL.LLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLLL.LL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLL",
            "LL..LL.....LLL......LL.........L...L..L......L.....LL........LLLL.....L..................LL....L.",
            "LLLLLLLLLL.LLLL.LLLLLLL.LLLLL.LLLLLLLL.LLLLLLLLL.L.LLLLLLLLLLLLL.LLLLL.LLLLLLLLLL.LLLLLLL.LLLLLLL",
            "LLLLLLLLLLLLLLLLLLL.LLL.LLL.L.LLLLLLLLLLLLLLLLLL.LL.L.L.LLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLL",
            "LL.LLLLLLL.LLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLLL.LLLLLLL.LLL.LL.LLLLLL.",
            "LLLLLLLLLL.LLLL.LLLLLLL.LLLLL.LLLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLLL.LLLLLLL",
            ".LLLLLLLLLLLL.L.LLLLLLL.LLLLL.LLLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLLLLLLLL.LLLLLLLLL.LLLLLLL",
            "...L..L...L.....L....LL..L.....L.L..L...LLLL..L....LL.L....L.L.L......LL...L...L..LLLL.L..L......",
            "LLLLLLLLLL.LLLLLLLLLLLL.LLLLL.LLLLLLLL.LLLL.LLLLLLLLLLL.LLLLLL...LLL.LLLLL.LLL.LLL.LLLLL..LLLLLLL",
            "LLLLLLLLLL.LLLL.LLLLLLL.LLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLL.LLLLLLLLLL.LLLLLLL",
            "LLLLLLLLLLLLLLLLLLLLLLL.LLLLL.LLLLLLLLLLLLLLLLLLLLLLL.L.LLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL",
            "LLLLLLLLLL.LLLL.LLLLLLLLLLLLL.LLLLLLLL.LL.LL.LLL.LLLLL..LLLLLLLL.LLLLLLLLL.LLLL.LLLLLLLLLLLLLLLLL",
            "L.LLLLLLLLLLL.L..LLLLLL.LLLLL.LLLLLLLL..LLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL",
            ".L....LL....L......LLL...L..L..L.L.L.LLL.L.L........L....L..LL......L......L.L..LLLL.L.L..L......",
            "LLLLLLLLLL.LLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLLL..LLLLLLL..LL.LLLLLLLLLL",
            "LLLLLLLLLLLLLLLLLLLLLLL.LLLLL.LLLLLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLL",
            "LLLLLLLLLL..LLL.LLLLL.L.LL.LL.LLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLLLLLLL",
            "LLLLL.LLLLLLLLL.LLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLL.L.LLLLLLLLL.LLLLL.L.LLLLLLLLLLLLLL",
            "LLLLLL.....L......L.......L..L.LLL...L.L...LLL....L.......L.L..L...LL....LL.L...L...LLL.L...L..LL",
            "LLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLL",
            "LLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL.L.LLLLL",
            "LLLLLLLLLLLLLLL.LL.LLLL.LLLLL.LLLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLL",
            "LLLLLLLLLLLLLLLLLLLLLLL.LLLLL.LLLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLL.LL.LLLLLLLLLLLLLLLLLLLLLL",
            "....L..L.L.L...L.....L...LLL.....L.......L..LLL.LL...L.....L..L.L...L.L.L...LL...LL.L....LL.....L",
            "LLLLLLLLLL.LLLL.LLLLLLL.LLLLL.LLLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLLLLLLL.LLLLLLLLLL.LLL.LLL",
            "LLL.LLLLLL.LLLL.LLLLLLL.LLLLLLLLLLLLLLLLLLL.LLLLLLLL.LL.LLLLLLLLLLLLLLL.LLLLLLLLLL.LLLLLL.LLLLLLL",
            "LLLLLLLLLL.LLLL.LLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLL.L.LLLLLLLLLLL.LLLLLL.LLLLLL..LLLLLLLLLLLLLL",
            "LL.LLLLLLLLLLLL.LLLLLLL.LLLLL.LLLLLLLLLLLLLLLLLL.LLLLLL.LL.LLLL..LLLLLLLLL.LLLLLLL.LLLLLL.LLLLLLL",
            "LLLLLLLLLL.LLLL.LLLL.LL.LLLLLLLLLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLLL.LLLLLLL.LLLLLL.L.LLLLL",
            "LLL.LLLLLL.LLLL.LLLLLLL.LLLLL.LLLLLLLL.LLLLLLLLL.LL.LLLLLL..LLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLL.LL.L",
            "LLLLLLLLLL.LLLL.LLLLLLL.LLLLL.LLLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLL",
            "......L......LLL......L.LL...L..L.L.L..L.L.......L.L....L..LL.......L......L.....L.LLLLLL...L...L",
            "LLLLLLLLLLLLLLL.LLLLLLL.LLLLL.LLLLLLLL.LLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLLLL..L.L.LLLLL",
            "LLLLLLLLLLLLLLL.LLLLLLL.LLLL.LLLLLLLLL.LLLLLLLLLLLLLLLL.LLLLL.LLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLL",
            "LLLLLLLLLL.LLLL.LLLLL.L.LLL.L.LLLLLLLL.LLLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL",
            "LLLLLLLLLL.LLLL.LL.LLLL.LLLLL.LLLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLLL.LLLLLLL..LLLLL.LLLLLLL",
            "LL.LLLLLLLLLLLL.LLLLLLL.LLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLLLLLLL",
            ".LLLLL.LLL.LLLLLLLLLLLL.LLLLL.LLLLLLLL.LLLLLL.LL.LLLLLLLLLLLL.LL.LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL",
            "LLLLLLLLLL.LLLL.LLLLLLLLLLLLL.LLLLLLLL.LLL.LL.L.LLLL.L.LLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLL",
            "LLLLLLLLLL.LLLLLLLLLLLL.LLLL..LLL.LLLL.LLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL",
            "LLLLLLLLLLLLLLLLLLLL.LL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.L.L.LLLLLLL.L.LLLLL.LLLLLL.LLLLLLL",
            ".L....L...L..LL.....L.LL...L.L..LLL...L..L..L..L..L.LLLLL....LLLL.........L..L..L..L..L...LL....L",
            "LLLLLLLLLL.LLLL.LLLLLLL.LLLL..LLLLLLLLLLLLLLLLL..L.LLLL.LLLLLLLL.LLLLLLLLL.LLLLLLL.LLLLLL.LLLLLLL",
            "LLLLLLLLLLLLLLL.LLLLLLL.LLLLL.LLLLLLLL.LLLLLLL.L.LLLLLLLLLLLLLLLLL.LLLLLLL.LLLLLLL.LLLLLL.LLLLLLL",
            "LLLLL.LLLL.LLLL.LLLLLLL.LLLLL.LLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL..LLLL",
            "LLLLLLLLLL.LLLL.LLLLLLL.LLLLL.LLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLL",
            "LL.LLLLLLLLLLLL.LLLLLLL.LLLLL.LLLL.L.L.LLLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLLL.LLLLLLL",
            "LLLLL.LLLL.LLLL.LLLLLLL.LLLLL.LLLLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLLL.LLLLLL.LLLLLLL",
            "..L.L...L.L........L.......L......LLLLL......L.LLL.LL.........L.L....L..L.L.L...L..LL.L......LL..",
            "LLLLLLLLLL.LL.L.LLLLLLL.LLLLL.LLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLLL.LLLLLLL",
            "LLLLLLLLLL.LLLL.LLLLLLLLL.LLL.LLLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLLLLLLLLLL.LL.LLLLLLL.LLLLLL.LLLLLLL",
            "LLL.LLLLLLLLL.L.LLLLLLL.LLLLLLLLLLLL.L.LLLLLLLL..LLLLLLLLLLLLLLL.LLLL.LLLLLLLLLL.L.LLLLLLLLLLLLL.",
            "LLLLLLLLLLLLLLL.LLLLLLL.LLLLL.LLLLLLLLLLLLLLL.LL.LLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL",
            "LLLLLLLLLL.LLLL.LLLLLLL.LLLLL.LLLLLLLL.LLLLLLLLL.LLLLLL.L.LLLLLL.LL.LLLLLL.LLLLLLL.LL.LLL.LLLLLLL",
            "LLLLLLLLLLLLLLLLLLLLLLL.LL.LL.LLLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLL",
            "LLLLLLLLLL.LLLLLLLLLL.L.LLLLL.LLLLLLLL.LLLLLLLLL.LLL.LL.LLLLLLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLLLLLLL",
            "LLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LLLLLL.LL.LLLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLLLLLLL",
            "LLLLLLLL.L.LLLL.LLLLLLL.LLLL..LLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLLL.LLLLLL.LLLL.LL",
            ".....LL.L...L..L.L..LL.L.......L...L...LLL.L.L..L.LL.L...LL..L.LL.LLL......LL.L..L.LL......L.....",
            "LLL.LLLLLL.LLLLLLLLLLLL.LLLLLL..LLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLLL.LLLLLLLLLL.LLLLLLLL..L",
            "LLLLLLLLLL.LLLL.LLLLL.L.LLLLL.LLLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLLL.L..LLLLLL.LLLLLLL.LLLLL.LLLLLLLL",
            "LLLLLLLLLL.LLL..LLLLLLL.LLLLL.LLLLLLLL.LLLLLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL",
            "LLLLLLLLLL.LLLLLLLLLLLL.LLLLL.LLLLLLLL.LLLLLLLLLLLLLLL..LLLLLL.LLLLLLLLL.L.LLLLL.LLLLLLLL.LLLLLLL",
            "LLLLLLLLLL.LLLL.LLLLLLL.LLLLL.LLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLLL.LLLLLL.LLLLLLL",
            "LLLLLLLLLLLLLLL.LLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLL.LLLLLLLLLLLL.LL.LLL.LLLLLLLLL.LLLLLLLLLL.LLLLLLL",
            "LLLLLLLLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL",
            "LL.LLLLLLLLLLLL.LLLLLLL.LLLL..LLLLLLL..LLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLL",
            "LLLLLLLL.L.LLLLLLLLLLLL.LLLLLLLLLLLLL..LLLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLLL.LLLLLLL",
            "LLLLLLLL.LLLLLLLLLLLLLL.LLLLL.LLLLLLLLLL.LLLLLLL.LLLLLL.LLLLLL.L.LLLLLLLLL.LLLLLLL.LL.LLL.LLLLLLL",
            "LLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL",
            "LLLLLLLLLLLLLLL.LLLLLLL.LLLLL.LLLLL.LLLLL.LLLLLL.L.LLLL.LLLLLLLL.LLLLLLLLLLLLLLLLL.LLL.LL.LLLLLL."
    };



}
