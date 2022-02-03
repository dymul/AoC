import lib.Day;
import lib.IN_TYPE;
import lib.InReader;
import y2015.*;

import java.util.function.Supplier;

public class Runner {

    public static Supplier<Day> dayProvider = Day18::new;

    public static void main(String[] args) throws Exception {

        InReader inReader = new InReader(dayProvider.get().getClass());
        String second_part = dayProvider.get().solve2(inReader.readInput(IN_TYPE.TEST));
        if (second_part != null) {
            System.out.println("*** SECOND PART ****");
            System.out.println("*** TEST ****");
            System.out.println("Result: " + second_part);
            String challenge_solution = dayProvider.get().solve2(inReader.readInput(IN_TYPE.CHALLENGE));
            System.out.println("*** CHALLENGE ****");
            System.out.println("Result: " + challenge_solution);
        } else {
            second_part = dayProvider.get().solve(inReader.readInput(IN_TYPE.TEST));
            System.out.println("*** FIRST PART ****");
            System.out.println("*** TEST ****");
            System.out.println("Result: " + second_part);
            String challenge_solution = dayProvider.get().solve(inReader.readInput(IN_TYPE.CHALLENGE));
            System.out.println("*** CHALLENGE ****");
            System.out.println("Result: " + challenge_solution);
        }
    }
}
