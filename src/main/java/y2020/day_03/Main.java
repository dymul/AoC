package y2020.day_03;

public class Main {
    //test env setup
    public static void main(String[] args) {
        int xsize = input[0].length();
        int ysize = input.length;
        String[][] in = new String[ysize][xsize];
        for (int i = 0; i < ysize; i++) {
            in[i] = input[i].split("");
        }

        //traverse
        int y_step = 1;
        int x_step = 3;
        int y_start = 0;
        int x_start = 0;

        int counter = traverse(in, xsize, ysize, x_step, y_step, x_start, y_start);
        System.out.println("Counter: " + counter);

        /*
        Right 1, down 1.
        Right 3, down 1. (This is the slope you already checked.)
        Right 5, down 1.
        Right 7, down 1.
        Right 1, down 2
         */
        long multiplier = 1;
        multiplier*=traverse(in, xsize, ysize, 1, 1, x_start, y_start);
        multiplier*=traverse(in, xsize, ysize, 3, 1, x_start, y_start);
        multiplier*=traverse(in, xsize, ysize, 5, 1, x_start, y_start);
        multiplier*=traverse(in, xsize, ysize, 7, 1, x_start, y_start);
        multiplier*=traverse(in, xsize, ysize, 1, 2, x_start, y_start);
        System.out.println("Mulitplier: " + multiplier);


    }

    private static int traverse(String[][] in, int xsize, int ysize, int x_step, int y_step, int x_start, int y_start) {
        int counter=0;
        for (int y_pos = y_start, x_pos = x_start; y_pos<ysize ; y_pos=y_pos+y_step,x_pos=(x_pos+x_step)%xsize) {
            if(in[y_pos][x_pos].equals("#")) {
                counter++;
            }
        }
        System.out.println("C:" + counter);
        return counter;
    }


    static String[] input = {
            ".........#.#.#.........#.#.....",
            "...#......#...#.....#.....#....",
            "#.....#.....#.....#.#........#.",
            "......#..#......#.......#......",
            ".#..........#.............#...#",
            "............#..##.......##...##",
            "....#.....#..#....#............",
            ".#..#.........#....#.#....#....",
            "#.#...#...##..##.#..##..#....#.",
            ".#.......#.#...#..........#....",
            "...#...#........##...#..#.....#",
            "..................#..........#.",
            ".....#.##..............#.......",
            "........#....##..##....#.......",
            "...#.....#.##..........#...##..",
            ".......#.#....#............#...",
            "..............#......#......#..",
            "#.......#...........#........##",
            ".......#.......##......#.......",
            "................#....##...#.#.#",
            "#.......#....................#.",
            ".##.#..##..#..#.#.....#.....#..",
            "#...#............#......##....#",
            ".#....##.#......#.#......#..#..",
            "..........#........#.#.#.......",
            "...#...#..........#..#....#....",
            "..#.#...#...#...##...##......#.",
            "......#...#........#.......###.",
            "....#...............#.###...#.#",
            "..................#.....#..#.#.",
            ".#...#..#..........#........#..",
            "#..........##................##",
            "...#.....#...#......#.#......#.",
            "......#..........#.#......#..#.",
            "..#......#.....................",
            "............#.........##.......",
            "......#.......#........#.......",
            "#.#...#...........#.......#....",
            ".#.#........#.#.#....#........#",
            "#.....##........#.#.....#.#....",
            ".#...#..........##...#.....#..#",
            ".........#....###............#.",
            "..#...#..............#.#.#.....",
            ".....#.#.#..#.#.#.###......#.#.",
            ".#.##...#.......###..#.........",
            ".....##....#.##..#.##..#.......",
            "..#...........##......#..#.....",
            "................##...#.........",
            "##.....................#..#.###",
            "...#..#....#...........#.......",
            ".#.............##.#.....#.#....",
            ".......#.#.#....##..#....#...#.",
            "...##..#..........#....#.......",
            "....##......#.........#........",
            ".##....#...........#.#.......#.",
            "...#...#.##.......#.#..........",
            "..#.........#.##...........#...",
            "....#.##........#.......#...##.",
            "...................#..#..#...##",
            "#...#......###..##.#..###......",
            "#.............#.#........#...#.",
            "...#...#..#..#..............#..",
            "#.....#..#...#...#......#.#..#.",
            "...##.............#........##.#",
            "......#.#.........#.#....#...#.",
            "........##............#...#....",
            "............#.#...#......#.....",
            "...#...........#...#...........",
            ".........#.#......#............",
            "....#.............#..#.....#..#",
            "#.....#...........#.....#.#.#.#",
            ".............#.....##......#...",
            "...................###.#......#",
            "#.##.....#...#..#..#...#....#..",
            "............#.....#....#.#.....",
            "#....#..#..........#....#..#...",
            "..........##..................#",
            "....#.......###..#......###....",
            ".......#...#.##.##..#....##....",
            "...##...............#.....#...#",
            "#...........#...#......#..#..#.",
            "..##....#.......#...#.....#....",
            ".......##..............#.##..#.",
            ".#......#..........#.......#...",
            "....##...................#.#..#",
            "......#....###................#",
            ".#........#...........#........",
            ".#.....##....#..##...........#.",
            "##..............##...#.......#.",
            "......#..........#..........##.",
            "..#.....#.#.........####....#..",
            ".............#......#......#...",
            "..#.............#...........##.",
            "#.#...#........#..........##...",
            "...#....#.....#.....#.....##...",
            "......#........................",
            "......#..#...#......#.....#....",
            "......#....##.....#....#.......",
            "#.#......#.##..#...............",
            "..........#.#.##..##..#........",
            "......#.#..#....###.#..........",
            "........................#....#.",
            "#.#.............#....#.....##.#",
            ".#.#..........#.......#..#....#",
            "..#...#......#..#..#...#.#...#.",
            "...#.##...###..................",
            "........#.#...........#...#....",
            "........#..........#....#......",
            "#....#........#.......##.....#.",
            "......###...##...#......#......",
            "............#.......#.....##..#",
            "....#..#.......##......#.......",
            "#............##................",
            "...............#..#......#...#.",
            ".#....##...#......#............",
            "#...#................#.........",
            "..#....#..#........##......#...",
            "....#....###......##.#.......#.",
            "......#.#..#.#..#....#..#......",
            "....#..........#..#..#.........",
            ".#..#.......#......#........#..",
            ".......#..#..#............#....",
            ".............#...#....#..#.....",
            "..............#.#.#.........#..",
            "#.....##.......#....#..........",
            "...#.....#......#..............",
            "...##.#..#.#........#..##....#.",
            ".......#.#.....##..#...........",
            ".....#.#....#..................",
            ".#......#.###..............##..",
            "..#....#...#..#...##...##....#.",
            "..........##..#...#..#.........",
            "..#............#........#.#...#",
            ".........................#.#.#.",
            "......#........#.#..#......##.#",
            "#.#......#..#.........#........",
            ".....#........#......#...#.#...",
            "........##....##....#.#........",
            "....#....#.#...#...##....#..#..",
            "#.............#.....#..........",
            "#............##..#............#",
            "..#.#......#........#..........",
            ".#......#......#.#.##.##.......",
            "..#.....#..........#......##...",
            "...#......#...#.##....#.....##.",
            "......#......#...........#.#...",
            "....#........#..#..#........#.#",
            "....#.........#.....#...#.#.#..",
            "....#.....###........#.........",
            ".............##........#.#.....",
            "...#............#........#.#.#.",
            "......#....#.......#.#.........",
            ".....#................#........",
            ".#....#..#.#.............#...#.",
            "#..##...#............#......#..",
            "...#..#........................",
            ".#.#...........#.......#.......",
            "#....###............##.........",
            "...##....#.#............##.....",
            ".........####......#........#..",
            ".....#.......#.#...............",
            ".......#...#.###..#....#...#..#",
            "...#.....##..#....#..#.#...###.",
            ".............#........#.#.#..#.",
            "................#..........##..",
            ".......####..##..##........##.#",
            "..#......#..#..#.#.##..........",
            "#....#........#....#...###.#.#.",
            "........##........##.....#.....",
            "...........#.#...........#....#",
            "#.............#...........#....",
            "...#.........#...#....#.....#..",
            "..##......#...#...............#",
            ".............#.........#....#..",
            "..#...........#..#........#.##.",
            ".#.#......#.............##...#.",
            ".#......#.....##.#..#..#.......",
            "....##......#..................",
            ".#.#..##............#....#....#",
            "........#...##.............#..#",
            "........#....##.....#......###.",
            ".........#....#.#..............",
            "#.....#........................",
            ".#..#....#.....#......#.###..#.",
            "..........#...#....##....#..#..",
            "...#.#.#...##..#..#......#..#.#",
            "#............#.....#....#......",
            "#.###...#.#......###..#....#..#",
            "...#.###........#......#....#..",
            "..#.##...#.........#.#.........",
            "............##.................",
            "....#..........#.#.#.#.#....#..",
            "...##.#...#.......#.......##..#",
            "....##.#........#....#...#.....",
            ".............#.#....#...#.....#",
            "...#......................##...",
            "..#...#.....#.....#........#..#",
            "..#..#.......#....#..##.....#..",
            "..#..#.#.......................",
            ".......##..#....#....#..#......",
            "....#......##....#............#",
            ".#...#..#..#.##...#.#...#......",
            ".....#......#....#.........#...",
            ".##......##.........#....#.....",
            "#...........#...##.....#......#",
            ".....#.#.......#.........#.....",
            ".........#..........#..####.##.",
            "............#..#......#.#......",
            ".#.............#........#.#....",
            "......#......#...#..#....#####.",
            ".........##.#..##...###..#....#",
            "....#.#....#.#..#.........#....",
            "..#.............#...##...##....",
            "........#..........#.##..#....#",
            ".....#...#..##........#.#..#...",
            "##..#.#.....#............#.....",
            ".............#........##...##..",
            "#......####.....##.............",
            "..##.....##....###..#.#....#...",
            "......##.##.#...#..#.#..##.....",
            "......#.................#......",
            "#.....#.#...#......#.#....#....",
            "....#.#........#..............#",
            "##........#.......##.#...##...#",
            "..#..................#.#....#..",
            "...........#..........#.#.....#",
            "........##.#.....#......#..#..#",
            ".....#....#..#.....#.........##",
            "#.#..#..#...#......#..........#",
            "#...##.....#..#.#.......#.##...",
            "..#....##...............#......",
            "#..........#.#.........#.#....#",
            "..............#......#....#....",
            ".....#...........#...#...#...#.",
            "...#......#....#....#..........",
            ".#..........#.#....##..##....#.",
            "..............#.........#.#....",
            ".......#.....#.....#...##....#.",
            "##.#.........#....#.....#.#....",
            "....#..#......#................",
            "......##.....#.......##........",
            ".....##...#........#...#...#...",
            "..#...#...#..#..#.#......#..#..",
            "....#...#.......#..............",
            "....#..#.........###........#..",
            "....#.............##..#........",
            "..........##.#.......##..##....",
            "#.##..................#.....#..",
            "#........#........#.....#......",
            ".#...#......#..................",
            "#....##.##......#...#.........#",
            "......#.##..##................#",
            "............#.........##.......",
            "..........####.#........#.....#",
            ".##...#...#....#..#............",
            ".#.##...#..#...#......#......##",
            ".....#.#....#..###......#.#.#..",
            "...#.......................##..",
            "......................#.......#",
            "..#....#.........#..#.#.....#..",
            ".#....#..#....#...#............",
            "..........#...##.....#.#..#....",
            "........#..#..#....#...#...#...",
            ".....#......#.#................",
            ".....#...........#...#.........",
            ".....#...##..#.#....#..#.....#.",
            "#.......#.............##.......",
            "................#....#.#..#....",
            ".#..##...#.#........#......#.#.",
            ".#.##..........#...............",
            "....##......#....#........#....",
            "....#..#....#.##.#.............",
            ".......#..#......##.#.....#....",
            ".......#.....#.............#...",
            ".....#....#.......#............",
            "........#.#...##..##..##.......",
            "#.........##....##...##........",
            "........#..#.#..........###.#..",
            "..........................#.#..",
            "#.....#.......#..#........#....",
            "...##.....#.......#......#.....",
            ".#.#..#...........#...........#",
            ".....##..#........#...####.....",
            ".#.#...##.#.#..#..#.#..#.......",
            "..#.##.#...#.#.#...#..#........",
            "............#..........#..#....",
            "...............#..##.#.........",
            ".............#.....#....#......",
            "...##..##......##..........#...",
            "..#.......#....#..........#...#",
            ".##................#.#.#.......",
            ".....##.....#..#.....#.........",
            "......#.#.......#......#..#....",
            ".....#.....#........#.......##.",
            "......#.......##......#...#...#",
            "....#...........###.........#..",
            "...#.....#.........##........#.",
            "..#.....#..............#.......",
            "....#.......#...#....#....#..##",
            "......#...........#...........#",
            ".##......#......#.#.....#.##...",
            "....#..##......#...#..#.#.###..",
            ".......#.#....#......#..#......",
            "..........#........#...........",
            "#.##.........#.#.#...#...#.#...",
            ".#......###.....#....#.#....#..",
            "...................##..#.......",
            "....#..#..............#.#.....#",
            "#..................#.....#.....",
            "...........##.##.......#..#.#..",
            "........#.#......#...........#.",
            "#..#.......#...#...........#.#.",
            "......##...........#...........",
            ".........#.#........#........#.",
            "#......#....#.#.....#..#.......",
            "............#..#.....##...#....",
            ".#......#..#......#.........#..",
            ".......#...#.........#.##.....#",
            "........................#..#...",
            ".###..............#.#..#.......",
            ".....#.........#.......#......#",
            "..##..##....#.....#.......#.#..",
            "...###.#..#.##............#...."
    };
}