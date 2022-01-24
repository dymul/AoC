package y2020.day_21;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Line {

    Set<String> ingredients;
    List<String> allergens;

    Line(String line) {
        String[] split = line.split(" \\(contains ");
        ingredients = Arrays.stream(split[0].split(" ")).collect(Collectors.toSet());
        allergens = Arrays.stream((split[1].replace(")", "").split(", "))).collect(Collectors.toList());
    }
}
