package y2020.day_19;

import java.util.*;

public class Rule {

    int number;
    boolean solved = false;
    String raw = "";
    Set<String> matchers= new TreeSet<>();

    Rule(String s) {
        String[] split = s.split(": ");
        number = Integer.parseInt(split[0]);
        raw = split[1];
        if (raw.equals("a") || raw.equals("b")) {
            matchers.add(raw);
            solved = true;
        }
    }

    // 111: 29 74 | 106 110
    void solve(Map<Integer, Rule> others) {
        if (number == 0 || number == 8 || number == 11) {
            return;
        }
        if (!solved) {
            String[] split = raw.replace('|', 's').split(" s ");
            for(String s: split) {
                solveRule(others, s);
            }
            solved = true;
        }

    }
    //29 74
    private void solveRule(Map<Integer, Rule> others, String s) {
        String[] split = s.split(" ");
        if (split.length ==2) {
            Rule rule1 = others.get(Integer.parseInt(split[0]));
            Rule rule2 = others.get(Integer.parseInt(split[1]));
            if (!rule1.solved) rule1.solve(others);
            if (!rule2.solved) rule2.solve(others);
            for(String m1: rule1.matchers) {
                for(String m2 : rule2.matchers) {
                    matchers.add(m1+m2);
                }
            }
        } else {
            Rule rule1 = others.get(Integer.parseInt(split[0]));
            matchers.addAll(rule1.matchers);
        }
    }
}
