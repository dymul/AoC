package y2015;

import lib.Day;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day06 implements Day {

    @Override
    public String solve(List<String> input) throws Exception {
        boolean[][] lights = new boolean[1000][1000];
        input.stream().map(Command::parse).forEach(c -> applyCommand(c, lights));

        int counter = 0;
        for (boolean[] arr : lights) {
            for (boolean b : arr) {
                if (b) counter++;
            }
        }

        return Integer.toString(counter);
    }

    void applyCommand(Command c, boolean[][] lights) {
        for (int i = c.top_x; i <=c.bottom_x ; i++) {
            for (int j = c.top_y; j <= c.bottom_y ; j++) {
                if (c.type == CommandType.ON) {
                    lights[i][j] = true;
                } else if (c.type == CommandType.OFF) {
                    lights[i][j] = false;
                } else {
                    lights[i][j] = !lights[i][j];
                }

            }

        }
    }

    @Override
    public String solve2(List<String> input) throws Exception {
        int[][] lights = new int[1000][1000];
        input.stream().map(Command::parse).forEach(c -> applyCommand(c, lights));

        int counter = 0;
        for (int[] arr : lights) {
            for (int b : arr) {
                counter+=b;
            }
        }

        return Integer.toString(counter);
    }

    void applyCommand(Command c, int[][] lights) {
        for (int i = c.top_x; i <=c.bottom_x ; i++) {
            for (int j = c.top_y; j <= c.bottom_y ; j++) {
                if (c.type == CommandType.ON) {
                    lights[i][j]++;
                } else if (c.type == CommandType.OFF) {
                    lights[i][j] = lights[i][j] == 0 ? 0 : lights[i][j] -1;
                } else {
                    lights[i][j] +=2;
                }

            }

        }
    }
}

class Command {
    CommandType type;
    int top_x, top_y, bottom_x, bottom_y;

    static Command parse(String line) {
        Command c = new Command();

        if (line.startsWith("turn on")) {
            c.type = CommandType.ON;
        } else if (line.startsWith("turn off")) {
            c.type = CommandType.OFF;
        } else {
            c.type = CommandType.TOGGLE;
        }
        String toParse = line
                .replace("turn on ", "")
                .replace("turn off ", "")
                .replace("toggle ", "")
                .replace(" through ", " ")
                .replace("," , " ");
        List<Integer> ints = Arrays.stream(toParse.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        c.top_x = ints.get(0);
        c.top_y = ints.get(1);
        c.bottom_x = ints.get(2);
        c.bottom_y = ints.get(3);
        return c;
    }
}

enum CommandType {
    ON,OFF,TOGGLE;
}
