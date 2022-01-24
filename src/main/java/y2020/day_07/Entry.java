package y2020.day_07;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Entry {
    static Map<String, Entry> entries = new HashMap<>();
    String color;
    List<Neigbour> neighbours;
    List<Entry> parents = new LinkedList<>();

    public Entry(String color, List<Neigbour> neighbours) {
        this.color = color;
        this.neighbours = neighbours;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "color='" + color + '\'' +
                ", neighbours=" + neighbours +
                ", parents = " + parents.stream().map(p -> p.color).collect(Collectors.toList()) +
                '}';
    }

    static void parse(String input) {
        String[] split = input.split(" ");
        String name = split[0] + " " + split[1];
        List<Neigbour> neighs = new LinkedList<>();
        for (int i = 4; i  + 3 < split.length; i+=4) {
            int number = Integer.parseInt(split[i]);
            String neigh_color = split[i+1] + " " + split[i+2];
            Entry neigh;
            if (entries.containsKey(neigh_color)) {
                neigh = entries.get(neigh_color);
            } else {
                neigh = new Entry(neigh_color, new LinkedList<>());
                entries.put(neigh_color, neigh);
            }
            neighs.add(new Neigbour(neigh, number));
        }
        Entry newEntry;
        if (entries.containsKey(name)) {
            newEntry = entries.get(name);
            newEntry.neighbours = neighs;
        } else {
            newEntry = new Entry(name, neighs);
            entries.put(name, newEntry);
        }
        for(Neigbour n : newEntry.neighbours) {
            n.entry.parents.add(newEntry);
        }


    }
    public static void main(String[] args) {
        String[] input = {
                "light red bags contain 1 bright white bag, 2 muted yellow bags.",
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
                "bright white bags contain 1 shiny gold bag.",
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
                "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
                "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
                "faded blue bags contain no other bags.",
                "dotted black bags contain no other bag"
        };
        for (String s : input) {
            parse(s);
        }

        System.out.println(Entry.entries);
        System.out.println(Entry.entries.size());
        System.out.println(Entry.entries.get("shiny gold"));
    }


}



/*
0     1   2    3       4 5      6     7    8 9     10     11
light red bags contain 1 bright white bag, 2 muted yellow bags.
dark orange bags contain 3 bright white bags, 4 muted yellow bags.
0      1     2    3       4 5     6    7
bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
dark olive bags contain 3 faded blue bags, 4 dotted black bags.
vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
faded blue bags contain no other bags.
0      1     2    3       4  5     6
dotted black bags contain no other bags.
 */
