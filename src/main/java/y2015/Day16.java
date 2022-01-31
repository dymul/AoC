package y2015;

import lib.Day;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day16 implements Day {
    @Override
    public String solve(List<String> input) throws Exception {
        Params sample = new Params(input.stream().filter(s -> !s.startsWith("Sue")).collect(Collectors.toList()));
        List<Params> aunts = getAunts(input.stream().filter(s -> s.startsWith("Sue")).collect(Collectors.toList()));
        List<Integer> result = aunts.stream().filter(a -> match(sample, a)).map(a -> a.number).collect(Collectors.toList());
        return "" + result;
    }

    private List<Params> getAunts(List<String> sues) {
        return sues.stream()
                .map(s -> s.replaceFirst("Sue ", ""))
                .map(s -> {
                    int i = s.indexOf(":");
                    int number = Integer.parseInt(s.substring(0, i));
                    List<String> auntParams = Arrays.asList(s.substring(i + 2).split(", "));
                    return new Params(auntParams, number);
                })
                .collect(Collectors.toList());
    }

    private boolean match(Params sample, Params aunt) {
        return (aunt.children == null || aunt.children.equals(sample.children)) &&
                (aunt.cats == null || aunt.cats.equals(sample.cats)) &&
                (aunt.samoyeds == null || aunt.samoyeds.equals(sample.samoyeds)) &&
                (aunt.pomeranians == null || aunt.pomeranians.equals(sample.pomeranians)) &&
                (aunt.akitas == null || aunt.akitas.equals(sample.akitas)) &&
                (aunt.vizslas == null || aunt.vizslas.equals(sample.vizslas)) &&
                (aunt.goldfish == null || aunt.goldfish.equals(sample.goldfish)) &&
                (aunt.trees == null || aunt.trees.equals(sample.trees)) &&
                (aunt.cars == null || aunt.cars.equals(sample.cars)) &&
                (aunt.perfumes == null || aunt.perfumes.equals(sample.perfumes));
    }

    private boolean match2(Params sample, Params aunt) {
        return (aunt.children == null || aunt.children.equals(sample.children)) &&
                (aunt.cats == null || aunt.cats > sample.cats) &&
                (aunt.samoyeds == null || aunt.samoyeds.equals(sample.samoyeds)) &&
                (aunt.pomeranians == null || aunt.pomeranians < sample.pomeranians) &&
                (aunt.akitas == null || aunt.akitas.equals(sample.akitas)) &&
                (aunt.vizslas == null || aunt.vizslas.equals(sample.vizslas)) &&
                (aunt.goldfish == null || aunt.goldfish < sample.goldfish) &&
                (aunt.trees == null || aunt.trees.equals(sample.trees)) &&
                (aunt.cars == null || aunt.cars > sample.cars) &&
                (aunt.perfumes == null || aunt.perfumes.equals(sample.perfumes));
    }

    @Override
    public String solve2(List<String> input) throws Exception {
        Params sample = new Params(input.stream().filter(s -> !s.startsWith("Sue")).collect(Collectors.toList()));
        List<Params> aunts = getAunts(input.stream().filter(s -> s.startsWith("Sue")).collect(Collectors.toList()));
        List<Integer> result = aunts.stream().filter(a -> match2(sample, a)).map(a -> a.number).collect(Collectors.toList());
        return "" + result;
    }
}

class Params {

    Params(List<String> in, int number) {
        this(in);
        this.number = number;
    }

    Params(List<String> in) {
        for (String s : in) {
            if (s.startsWith("children")) {
                children = Integer.valueOf(s.split(" ")[1]);
            }
            if (s.startsWith("cats")) {
                cats = Integer.valueOf(s.split(" ")[1]);
            }
            if (s.startsWith("samoyeds")) {
                samoyeds = Integer.valueOf(s.split(" ")[1]);
            }
            if (s.startsWith("pomeranians")) {
                pomeranians = Integer.valueOf(s.split(" ")[1]);
            }
            if (s.startsWith("akitas")) {
                akitas = Integer.valueOf(s.split(" ")[1]);
            }
            if (s.startsWith("vizslas")) {
                vizslas = Integer.valueOf(s.split(" ")[1]);
            }
            if (s.startsWith("goldfish")) {
                goldfish = Integer.valueOf(s.split(" ")[1]);
            }
            if (s.startsWith("trees")) {
                trees = Integer.valueOf(s.split(" ")[1]);
            }
            if (s.startsWith("cars")) {
                cars = Integer.valueOf(s.split(" ")[1]);
            }
            if (s.startsWith("perfumes")) {
                perfumes = Integer.valueOf(s.split(" ")[1]);
            }

        }
    }

    Integer children;
    Integer cats;
    Integer samoyeds;
    Integer pomeranians;
    Integer akitas;
    Integer vizslas;
    Integer goldfish;
    Integer trees;
    Integer cars;
    Integer perfumes;
    Integer number;
}