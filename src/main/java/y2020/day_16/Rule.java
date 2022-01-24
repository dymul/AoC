package y2020.day_16;

public class Rule {
    String name;
    int r1min;
    int r1max;
    int r2min;
    int r2max;

    Rule(String line) {
        // "class: 1-3 or 5-7"
        String[] split = line.split(": ");
        name = split[0];
        String[] ranges = split[1].split(" or ");
        r1min = Integer.parseInt(ranges[0].split("-")[0]);
        r1max = Integer.parseInt(ranges[0].split("-")[1]);
        r2min = Integer.parseInt(ranges[1].split("-")[0]);
        r2max = Integer.parseInt(ranges[1].split("-")[1]);
    }

    boolean isValid(int v) {
        return (v>=r1min && v<=r1max) || (v>=r2min && v<=r2max);
    }
}
