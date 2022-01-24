package y2020.day_16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ticket {

    List<Integer> list;

    Ticket(String s) {
        list = new ArrayList<>(Arrays.stream(s.split(",")).map(Integer::parseInt).collect(Collectors.toList()));
    }
}
