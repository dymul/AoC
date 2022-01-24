package y2020.day_07a;

import java.util.LinkedList;
import java.util.List;;
import java.util.stream.Collectors;

public class Entry {
    String color;
    List<Child> children = new LinkedList<>();
    List<Entry> parents = new LinkedList<>();

    public Entry(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "color='" + color + '\'' +
                ", children=" + children +
                ", parents = " + parents.stream().map(p -> p.color).collect(Collectors.toList()) +
                '}';
    }

}

