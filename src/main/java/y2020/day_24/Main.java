package y2020.day_24;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        String[] input = solve;

        List<List<String>> parsed = parse(input);
        System.out.println(parsed);
        Map<Point, String> points = new HashMap<>();

        processMoves(parsed, points);

        long b = points.values().stream().filter(s -> s.equals("B")).count();
        System.out.println(b);
        System.out.println("-------------");
        for (int i = 1; i <= 100; i++) {
            points = process(points);
            long black = points.values().stream().filter(s -> s.equals("B")).count();
            System.out.println(i + ": " + black);
        }

    }

    static Map<Point, String> process(Map<Point, String> points) {
        Map<Point, String> result = new HashMap<>();
        Map<Point, String> temp = new HashMap<>();

         temp.putAll(points);
         points.forEach( (p, c) ->{
             Set<Point> neighs = neighs(p);
             for(Point n : neighs) {
                 temp.compute(n , (k,v) -> v==null? "W" : v);
             }
         });

         temp.forEach((p, c) -> {
             int count = countBlack(temp, p);
             if (c.equals("B") && (count == 0 || count >2)) {
                 result.put(p, "W");
             } else if (c.equals("W") && count == 2) {
                 result.put(p,"B");
             } else {
                 result.put(p,c);
             }
         });


        return result;
    }

    static int countBlack(Map<Point, String> points ,Point p) {
        int counter = 0;
        Set<Point> neighs = neighs(p);
        for(Point n : neighs) {
            String s = points.get(n);
            if (s!= null && s.equals("B")) {
                counter++;
            }
        }
        return counter;
    }

    static Set<Point> neighs(Point p) {
        Set<Point> result = new HashSet<>();
        result.add(move(p, "e"));
        result.add(move(p, "w"));
        result.add(move(p, "sw"));
        result.add(move(p, "nw"));
        result.add(move(p, "ne"));
        result.add(move(p, "se"));
        return result;
    }

    private static void processMoves(List<List<String>> parsed, Map<Point, String> points) {
        for(List<String> line: parsed) {
            Point curr = new Point(0,0);
            for(String move : line) {
                curr = move(curr, move);
                System.out.println(curr);
            }
            String point = points.get(curr);
            if (point == null) {
                System.out.println("adding new: " + curr);
                points.put(curr, "B");
                System.out.println("adding new");
                System.out.println(points);
            }
            else {
                System.out.println("existing: " + curr);
                point = point.equals("B") ? "W" : "B";
                points.put(curr, point);
                System.out.println(points);
            }
        }
    }

    private static Point move(Point c, String move) {
        boolean pair = (c.y*c.y)%2==0;
        if (move.equals("e")) {
            return new Point(c.x+1, c.y);
        }else if (move.equals("w")) {
            return new Point(c.x-1, c.y);
        }else if (move.equals("se")) {
            int x_f = pair ? 1: 0;
            return new Point(c.x+x_f, c.y-1);
        }else if (move.equals("sw")) {
            int x_f = pair ? 0 : -1;
            return new Point(c.x + x_f, c.y-1);
        }else if (move.equals("ne")) {
            int x_f = pair ? 1: 0;
            return new Point(c.x+x_f, c.y+1);
        }else if (move.equals("nw")) {
            int x_f = pair ? 0 : -1;
            return new Point(c.x +x_f, c.y+1);
        } else {
            throw new RuntimeException("Err");
        }
    }

    private static List<List<String>> parse(String[] input) {
        List<List<String>> parsed = new LinkedList<>();

        for(String line : input) {
            List<String> lineList = new LinkedList<>();
            parsed.add(lineList);
            String[] splited = line.split("");
            int i = 0;
            while (i < splited.length) {
                if (splited[i].equals("e") || splited[i].equals("w")) {
                    lineList.add(splited[i]);
                    i++;
                } else  {
                    lineList.add(splited[i] + splited[i+1]);
                    i+=2;
                }
            }

        }
        return parsed;
    }

    static String[] test2 = {"nwwswee"};

    static String[] test = {
            "sesenwnenenewseeswwswswwnenewsewsw",
            "neeenesenwnwwswnenewnwwsewnenwseswesw",
            "seswneswswsenwwnwse",
            "nwnwneseeswswnenewneswwnewseswneseene",
            "swweswneswnenwsewnwneneseenw",
            "eesenwseswswnenwswnwnwsewwnwsene",
            "sewnenenenesenwsewnenwwwse",
            "wenwwweseeeweswwwnwwe",
            "wsweesenenewnwwnwsenewsenwwsesesenwne",
            "neeswseenwwswnwswswnw",
            "nenwswwsewswnenenewsenwsenwnesesenew",
            "enewnwewneswsewnwswenweswnenwsenwsw",
            "sweneswneswneneenwnewenewwneswswnese",
            "swwesenesewenwneswnwwneseswwne",
            "enesenwswwswneneswsenwnewswseenwsese",
            "wnwnesenesenenwwnenwsewesewsesesew",
            "nenewswnwewswnenesenwnesewesw",
            "eneswnwswnwsenenwnwnwwseeswneewsenese",
            "neswnwewnwnwseenwseesewsenwsweewe",
            "wseweeenwnesenwwwswnew"
    };

    static String[] solve = {
            "wwwnwwswweswwwswwwwswwswwww",
            "swnwwsesenesenenwwnewwwwnwwnwwnw",
            "nenewnesenwnenenenenenene",
            "neeeeewneneneneeeeneeeeeee",
            "swwneswswseseneweseswnwnwseneswswsesenew",
            "eneneneswneeneeeneneeneenenenenene",
            "sesesweseseseenwseseseswsenwseswswswse",
            "eeeeeweneeee",
            "neesewweneewswneeneneseeeeeeee",
            "swsenwswswswswseswseswswswsweswswswswsw",
            "neswewswwsewwwnwsw",
            "seswsenenwswswswseseneseswswswseseswseswse",
            "sewweswneneeneneeeseseeseeneesww",
            "seswsesesesenwnwseseneseseseeseewseee",
            "nwnwnwwewnwwwwnwwwwww",
            "wnwwswwwwwswsewwwweswne",
            "swsesenenwnenwswseseneswseswe",
            "nwwenwwnwwwwwwswwsenwnewnwnwwne",
            "swwwwneswswwseswwswwneswwwswwew",
            "nwneswnenwnenenwenwnwnenenwsenenwnenenw",
            "neneneneneneeneneneneneeneneneenenwnesw",
            "nesewnenwwenwnwwseseeseseneneswwsw",
            "nwnwnwenwwnwnwneswnenenwnenenwnenenwnwne",
            "wnewswwwwswswwswwseswwswswswswsw",
            "nwweswnwswwwswswwnwwsewswswswenesw",
            "neswneneneneneewnene",
            "nwnwswnenwwnwnwnwnwnwnwwswswnewnenwwnw",
            "nenenenewewneneenenewsenewneenesee",
            "nwwnenwnenenenenwnenenwenwnenenenenene",
            "nwsenenenwnwnenenwnesenwnwnwnwnwne",
            "nwneseswwwwswsewwseswswswswswswnewsw",
            "wwwwnwswwwenewnwwwwswwnwww",
            "sesesesesesenwesesesesesesewsweseneseese",
            "eeneneweeeneeseeee",
            "wweswnwwnwnewnwwwswseenwwnwwnwnw",
            "neenenwneneseewnenwnwnwnenenwnesweswsw",
            "seswnwsesewwswwseswneswsweneeseswwe",
            "nenewnwnwnenenenewnwswenwwnesenesenwne",
            "nwenwneswswneeswsesewseww",
            "neneeneneewswneenwneneeneswnenenenee",
            "swseswseseswswseseseseswnwswseswswsw",
            "swneneneswwwneswwsewseswswswswwww",
            "nwneseswnwnwneswnewnwnwnenwnweseenwe",
            "wwwseeenwwnwnwnewnwnwwwnwnwnww",
            "sesesesesesesesewseseseseseseseesese",
            "swswwswseneeswnwwesewswswweneswsw",
            "wseewesenweneenewseneenenwswsee",
            "enewseeseesesesenesesewsewsesesesesee",
            "sewwwswswswwwwswwswswwwswneswsw",
            "nwnwnenwnwwwnwnenwnwnwnwnwswnwwsenwnw",
            "nwnwnwnenwnwnwnwneenenenew",
            "neeneenenenwswwseeneeesenw",
            "eseeeeseseweewseeseeeseeeee",
            "nwsewswseseseseneseswseswseseswe",
            "wwswswwswswnwwenwwwswswwseneew",
            "wwwwewwwwwweewswwwwwww",
            "swswswswswwswnweswwswswsweseswneswswsw",
            "nwewwsenweseeeswneneseneeweewnw",
            "nenewwnwsenwwwwswwsewewwenw",
            "nwwnenwnenwnwnwnenwnenwnwswnwnwnenwnenese",
            "seseneswseswswseswseswseswswseswseseswse",
            "eeeeeeweeeeeeeeeeswenwe",
            "eneswneneswnenenweneneneee",
            "nwswseeneneneeeeeeeeeeenenwenee",
            "swsenewwwwnwwwnewneeswwwnwwswnww",
            "eeeeeeeeeeeeeeweeeee",
            "eseeseseseeeseweneseeseeeeseese",
            "wwwwwwswwnwwwwnwswwnwwneewe",
            "eneseseeseseseneswneenwsewsenewswwswe",
            "senweeeeeeneseneeeseeswnwenwwe",
            "sewsweeneseswneswseswswwseswwswesenw",
            "wwwwwwwwswnwwwwwswwweww",
            "sewnwwwwwsewwwwnwnwenwenwnwswne",
            "eeeeeenweeseeeeee",
            "swsenwsweseswseswswswseswswsesesesesesese",
            "sewswwnwseswswswnweeenwsesesenenesesw",
            "nwnwseseewnenenewewwenwwnwsesenwne",
            "sesesenweseseseneswseseewseeseseneew",
            "swwswsewswsweswswswswseseswneswswswswnw",
            "swseesenwseseweseeseseeseseseeenwe",
            "wwnenwneenwnenenesenenenenwneweseswnwne",
            "nwnwnwnwnwnwnwnwnwsewnwnwnwnwnwsenwnenw",
            "seswsenwsesweswseseseseseswseswswsesene",
            "seswwseseseseseseseseswswsenesesesesese",
            "nwswswswswswneswswswswswsweswseswswswswsw",
            "wwwswwwwwwnesewnwnwsewwwwnwe",
            "seneneneeenewneeeeeenenweneswne",
            "seneenwseseseswsenwwneswswsese",
            "wswwswwwwwswnwsweenwswswwwswesw",
            "eeseseseeeseeesenweeesewswese",
            "nesewswswswnwswswswswwswsweswswswsww",
            "eneswswneeeeenweeeewnwenenwseesw",
            "seneswnwswenwnenenwwnew",
            "seenweweesewewseseeeeeneeew",
            "swnwnwnwnwnwnwenwwnwswenwnwnwnwnwnwnwnw",
            "enwswswswwseseseswsenwseseeseeseswwsw",
            "eeseseseswseseseswsenwwnwseseese",
            "neeeeneswnenenenenenenenenenwnenenee",
            "wnewwwwswewwwwwnwwswseswww",
            "neneneneneneneswneneneneneneneswnenewnee",
            "seeeseeseseseeseseeseswsenwseesesese",
            "swwsewswswwwwswswswsenewseswswnenw",
            "swneseseseswswseswswswseswwseseswswswse",
            "wswweswwnewwnwwww",
            "swswswsesewswswneswswswwsweneswswswsw",
            "wnwwnwwwweswnenw",
            "swseswsweswseswswswswswseswnwswseswswsese",
            "neenesewseneeesewewneneneenenw",
            "seneswseswswnwnwsweswswwswswswseswsenese",
            "nwenwswswwnwwnwnwnwnwsewsenwwwnwnwenw",
            "nenwnenenwnwnwnwnwewnwnwwnwwnwenee",
            "wneseeswwwswsewwwswnww",
            "nwnwnenwnwnenwnwnwneswnwwnwnwnwenwsenw",
            "neeeeneneneneeesweeeenenwswneneee",
            "senweseweseseseeseseseneseseeeswsese",
            "wseseeeneseseneeeeesewseeeweese",
            "seseseseeseseseseeeesewsesesenwsese",
            "wseeseenwseseneeseseeeseseeseeeese",
            "nwwesesesesenwnewwnwnesene",
            "swwneneeeeseeeeeswseenweeeee",
            "nenwseneneneeneeeee",
            "nwwsewwnewwwwwwwww",
            "neneseseenewewwnewnwnwesesenewsesee",
            "neswswswneswnenwwewswneswwswneseswsw",
            "seswswswseseseswseswnewseseswseswsw",
            "wneswseseseenwnewseeswswswnwswsesenw",
            "eneeweneneneeeneesenenenenenenenewse",
            "neswswwwwwwwwwswsew",
            "wneseswnewswwswew",
            "swnwwneswnwewswwswewnwswwwsenwse",
            "wnwnwwwwwnwnwsenwnwnwnwnwnwnwwsew",
            "senwswnwnwnenwswsewneseesenwnwwneenwsw",
            "nesesenenenenwnwneneswneneseenenenwnewnwnw",
            "nenesenwneneneneneneneswneenenenenenenene",
            "eweneesesesesewseeeseeeeeesesee",
            "eeneeweneeneneneneeswnenwneeeswne",
            "wswseswswnwswwswsweeseneswswseseswnw",
            "swenwwnwwsenwnwnewnwwenewwwesw",
            "senwseswswnewswswseswesweswswneswswse",
            "neseneneneeswneneenenenenenenenewnewne",
            "nesewsewwwewwwwwnwwnwnw",
            "swneswwwnwswnewneswwwwwsewwswsesw",
            "nenwwsewnwnwneswsesw",
            "nwnwnwnweseswnenwenwnwnwnwnwswnwnenwnwnw",
            "eswneneeneneneswnenenenenenewseneene",
            "nenwswwwswwwswsweswwwsweswswsww",
            "newneeeeneneneneneene",
            "wnwswewneeewseeseenwswwesenenew",
            "swswswseswswswswswswswswswswwswsweswsw",
            "eeeeenweesweeeeeenweeesw",
            "swswswswswswswswswswneswswswswswsw",
            "eeeeswwwneseeneeeeenweeswee",
            "wwwwwwwwwwwnenwwwwswwww",
            "swwswwnwswwwsewsww",
            "swnweesenwnwsenwswseswwweswswnw",
            "nesenenwnenwnwnenwnwnenenwnenwseenwwnewne",
            "nwsewwnwwnwwwwwwwwwnwnwnwww",
            "eneenewenewneneeeneneeswswneneee",
            "nenwnwnwnwwwnwsenwnwnwnwnwnwnwnwnwnwnwsw",
            "nwnwnwnwnenwnwwnwnwnwnwwnwnwswnwnwnwsenenw",
            "weseeeewneneseseeeeeseswesese",
            "swseseseseeesesesesesesesesenw",
            "nenwnesenwnewnenwnwnenenwnwnwnwnwnwnwnenw",
            "nwnwnwnwswnenwnwnwnenenwnwnwnenwnwne",
            "nwnenweswnenenwneenenewnenenenwnwnwnene",
            "seseeeeseeseseswesenwseseenw",
            "wnwnwnwnwneweswnwwwnwnenwnwwnwnwsew",
            "seneeswwwswneseseswwswseswswswswswswnw",
            "senwwnenwnwnwnenwnwnwnenwnwnwnwnwnwnenw",
            "swnwnwsenwnenwnwnenwnewnwseswnwnenwswne",
            "swswswswneswseswswswsw",
            "seseseswseseseseesesesenwesesesenwnwsese",
            "swswseswseswswswsesesesenesesenwsesesw",
            "wwswwwswwwwswwsewwnewnewwsww",
            "neswswwswseeswwswnenesesesesewswswsw",
            "wwwwwwwswwswseswnewsewswnewwww",
            "swwswswswwswwswwswswswswneswwwswsw",
            "wenwseeeseeseeeseeeseseseneese",
            "neneswnewswwwenwwwnwswsenwwwse",
            "wneweeseeseeeeweenwwewseene",
            "wswnwnweeswesweseenenwswwnenwwnwsenw",
            "sesenesesewseenwsesweeseewesesese",
            "eswseswsenwesenesesesesesewwswsese",
            "nenwwswewwwweseenwsenwwwwnwesw",
            "nwswweswswswswswswneswswwwwnweswse",
            "nwseswseeneseeweesesewneeseeseesene",
            "neeeswewewneseneswneneeneeneenenw",
            "eneneneeeeeweeeneneneeeneee",
            "seswseseswseswswseswseeswsesesewswsese",
            "nwnwnwewwwnwwwwnwwwe",
            "nenenenenwneneneneneswnenenenenenenenenee",
            "wneswneswswswswwswwewswswwwswsesewnw",
            "nenenwnenenesenwnwnenenwnenwwnwnenenenwnw",
            "eneeseswewnwseeswseewweeneswew",
            "ewenweeeeeesesesenweeseeee",
            "nwsenwnwnenwwnwnwnwenwnwnwewnwnwnewne",
            "wsweswswwswwswswwswswswswwswswnwwenw",
            "wwsewwswnwwneeswwwwnwwwswwww",
            "swsewswswswswswswswswswswswswswseseswne",
            "swnwnwnwnwewswswweeenwwnewnwnwnew",
            "seswswswswswneswswseswswswseswseswswswse",
            "nwwwnenwwwnwsewwenwwwswwwwnwww",
            "senwnwnwswneenweneseswnwneeswnwnweswne",
            "wseeeeeeeeenweeeneeeeee",
            "enweeweeenwenwseeneswseneneeeee",
            "nenweeeneseneeeseeeeenesenewwne",
            "nwnenenenwnenwswneswnenenwneneseene",
            "nwnwsenwswnwnwnwnwnwnenwnwwnwnenwnwnwnwnw",
            "ewnwsenwenwswneeseneneswnenewnenenese",
            "wnwswwwwnwsewwnwnewwwwswwneesese",
            "newwnwseswsenenwnwnwnenenw",
            "nwsesesenwsenwseseseeswseswswseswsesese",
            "sesesenwswseseswseseseeseswse",
            "nwneneenwwnenwswnenwswsenwnenwswneneenw",
            "enwswneneneseeeneeneneeewneswneee",
            "wnenenwneneneneneneseswnese",
            "neweeeneeeeeeeeeeeeeee",
            "wswswseswswnwswswswwswswwneswseswswnene",
            "swswseswwswseswsesweswseswseneseswnwswse",
            "wnenweseeesewseseswseseseweswneene",
            "enwswseseswswnwenesesewse",
            "neseneneneneeneenenenenenewswnenenenenene",
            "nwswsweeswseseswseswswnwwswseseswswsene",
            "eeneseeseneeseseesenwseseeswsewsese",
            "sesesenwesenwsweseseweneewseneneese",
            "sesewnwnwwnwnwwwnenwwwewsweww",
            "nwswwwewswwwnwwnwnwwnesewwwnww",
            "enwswnwnwsesewwweneswewwwnwneew",
            "wswswswneseswswwnwswswswswswnenwseswswse",
            "nwnwnwnenwnwnenwnwnwnwnwswnwnwenwnwnwnwnw",
            "nenenwnwnenenenenenesewnenenenenene",
            "nenwnwsewswwnwseenwnesenenwnenenwse",
            "wwwwwwwwwwwwwwswwwwwe",
            "seswswswswswnwsweswenwswswsewswswsw",
            "swswnwwswswwwswwneewwwswsw",
            "ewsewwnwnewwwwnwnwsewwwnwwnwse",
            "eseeseeseeeeeseeeneeeeseswe",
            "sweswswswswswswswwswswnwswseswswseswe",
            "wnenwswwnwnwnwnww",
            "sweeeseneseseneseseewseesesew",
            "seneeswneneenenwwswwseenwneneewene",
            "swswswswswsesweswswswswseseeseswswwswnw",
            "seeseeeesenwsweeseeeseese",
            "nwnwnwnwnwnwenwnwenwnwwnwnwnewenww",
            "wswswwswswweswswswwswnewsewswsw",
            "nwnwnwwnwnwnwnwwwwnwwnwnweenwnwnwwsw",
            "eneeeeneenenweneneneesweewesw",
            "nwsenwswnwnwnwnwnenenwnwnenenewnwnwnwnee",
            "swweswswswswneneswswswsesesewswsesesese",
            "swswsenwswswswswswnwneswsweswsewsesesew",
            "eneeeeeeneeneeneneeeeeswee",
            "swwseseswseswswnenwnwseswenenesenwswswsee",
            "seseseseseneswneseseswswsesewsesesesenesese",
            "swwswwswswwwswewwwwswswneswswsw",
            "esewwswwwwwwswwsewswswwnwnwswswne",
            "nwnwwnenwewnwnwnwnwswnwnwwswnwnewnw",
            "newswwswwswswswwseswwswnewwnwswswse",
            "wwwsenwnwwnwnwsenewwwwnwnwsenwnwwne",
            "seswwnwwwnwswsweenwwwwwee",
            "swswwswswswswswswswswswesenewswswswsw",
            "wwswswswswswswswswswswswswswesw",
            "wnwnwwwsewwwwnwwnwwnwwsenenwsew",
            "sewnwnenewwnesenwneeenwnwnenwnw",
            "nenwsenewnenwnenenenwnenenenenenenenenwne",
            "swswswswswswswswswswseneswswseswswswswsw",
            "neewnenenwnenenewnwnenesenenenwnenene",
            "swneeeneneeeeweneesenw",
            "eseewneeneeeseeswnewneeeeeeee",
            "senewsewneewswseswneswswseseswseswsw",
            "nenwnwenenenesweweswneneneneseeenene",
            "sesesesewsesewseseweseneswnesesesesese",
            "nenwnwnenenwnwnwswsenwnwneneswnenwsene",
            "swswswwneswswswneswswswswswswswwswwswsw",
            "wwwswsewwwnewnwwwwwwwnwew",
            "nwwnwwnwwnwnwwwwnwwwswwnewwew",
            "eenwneenweeswseneneneswesweneenenwe",
            "nwwnwenwewnwwnwnwswnwwnwnenwsewwesw",
            "seswswwswswnwswswswswswswsenenwseenenesw",
            "eeneswenweeneeneeeneneeneeee",
            "wwswswswwswswswwswswswswwwswswneswne",
            "nenwnwnenenwnwnenenwnenenwsenwnenenwnwnw",
            "nenenenenenenenenenewneneneneseneenenene",
            "eswnwswswsweswswswswswswswswswnwswswsw",
            "ewwwnwwnwswwnwwwwwnwnwnwwwnwenw",
            "swswseswswswswswseseseswseneswswsewswsw",
            "nwnenenwnwnenwnesenwnenwnenenenwnwnw",
            "sewnwwwwsewwwswwswwswswnwneswwsw",
            "swswswswswseswsenwsw",
            "wseswewneseswseseswneeseeesesewne",
            "seeewsweswswwswsewnenwneewewwnw",
            "seswenesesweseeesesenwweewnesenw",
            "swswswnwenwswsesewneswswneswseseeswswnw",
            "nwnwnwnwnwnwnwnwnwnwnwnwsenwnw",
            "nwewneneswneneneneenwswnwwesesenesenene",
            "eeneneweneneeneneeswnewenenene",
            "swwswswnwwsesweswwsweswnewwswwnw",
            "enenewneweneneneneeenenenenenenene",
            "eseesweseenweeeenweeseeeeeesw",
            "nwneeeneseseseseseseswsewsewsesewse",
            "nenewnweeeeswseswsesenw",
            "wnenenenenwnwneneswnwseeswsesenwnwnenenee",
            "eeeweeneeneseeesweeeneeenee",
            "swwnewswnewwnewseswsweswwswswwwsw",
            "senwnwseswwneswseseseswnwseseseseseseseswse",
            "nwnwneswnwenwnwnwnwnwnwnwnwnwnwnenenenw",
            "wnewnewnwswnwnwwwnwnwwnwwnwnwwsew",
            "eeeeeeseeenwneeeewesesweese",
            "swswseswswswseswswsweseswswneswswwnwsw",
            "senwnwsenwnwswenenenwwenwenwnewnwnwnw",
            "eweseswewseseneneseswneewseenwee",
            "sesesesenweseseseseseseseesesesesewsesw",
            "nwsenwnwnwnenwnwenwnww",
            "newwweeswwnewnwseswseenewwnwswnew",
            "wwsewwwwwwwwwwwwwwwnww",
            "wwenwswsweeenweeeeeseeswnwe",
            "nwswsewwnwwsenwswnwwnwsenewwnwenw",
            "seewseeeseeeeneeeeeeeeese",
            "enwswwnwwwnewwwsewnwewsenewswww",
            "senwnwswsenwnwnwnwnenenwnwswnenwnwnwnwsw",
            "swswwwswwnewwwwwwwewswseww",
            "seswseswseesewseseswseseneseseseswsese",
            "eneesenenwwnwseseeeenwneeenwswese",
            "nenwnesenenwnenenwnwnewnwnwnwnwnenenene",
            "swseseseswnwseswswseswswseswswswseswswsw",
            "newnwswwwnwwwwswnwnwewwwnwww",
            "neswsweeeeeenenenwswnenw",
            "senwwnwnwnwnwwnwwnwnwnwnwnwnwnwenwnwne",
            "nwnwnwnwnwnwnwnwnwnwnwnwnwnwwnwnwnwenw",
            "seswwsewswwesweseseswneswswnwswnesw",
            "nenwnwswswneneswneneneneeneswneneenenene",
            "neeswnenenwneneneneneneneene",
            "nwnwnwnwnwnwnwnwnwwwwnwnwenwwnwnwsenw",
            "wwwwseswwwwenwnwswswwwswswswwsw",
            "wwwwwwwwwwwwewwwewww",
            "swswswswseswswswswswwswswswneswneswswswswse",
            "nwnwenwswnwsenenwnwnwnwwnwnwnwnwnwswnw",
            "nwseeneneneneneneeeeseeenewnenenenew",
            "swneswneseseewesweneseenenewewnenene",
            "enesenwnwseeeenwwweeseeswnwese",
            "swswswswswneswswswswswswswswswswswswswsw",
            "eeeeeswnweeeneneneeneeeeenene",
            "eeseeseseswwnweeeeseseeseewsee",
            "nwnwnwnwwnwnwnwnwnwnwnwnenwnwsenwnenwnwnw",
            "enwwseseeeseseeeneseeeseenewsenwse",
            "swneseseswsesesesesesesesesesesesesesese",
            "eswewsenenwseeenwseenenenweswswnwew",
            "eseswesesesesesweseseswwsenwnesesesene",
            "nwnwswnwnwneswnwswnwsenwnwnwnwnwneeswnwe",
            "swseswwneswneswswseswwswswswwneswese",
            "wwswswwsenewwsewsenewnenewnesew",
            "wseswnwswseeenwneeeeneeswsesenwesee",
            "swesesenesweseewseeneseneseeseese",
            "swnenesesenwseeswwswnwwnenwneneseswse",
            "senwseswnewseeseweseseeeewewse",
            "seeswseswwswneswswseneswseswseswswsww",
            "wswwswswwwwswwswwswswwesw",
            "seseeseneseseseeeneseseesewseswseese",
            "seneswnwewswsesenwsenwneswswnewwswse",
            "sweseeseseseseseseesesesenwseswnwsesesese",
            "swnwswnewswswseswnewwnwswnewswswee",
            "seenewwsenwwwwwwwnewsenewwsww",
            "neswneswnenenenwswwenenwnenenenwenenwne",
            "neneeeneneneswnwneneneweneeneswnenenene",
            "seseseeseseenwsesee",
            "nenenenenwnenwnwnwnenenewsenenenenenene",
            "wwwweneneswnwesweweswnwsenwswnew",
            "swnenwnwwenewnwnwnweneneenewneswnee",
            "senesesewenwsesesesesesesesesesesesee",
            "wswwnewewswswnwsweweseswwswswne",
            "esweenweneneswnenene",
            "seseseseswseseswesesesesesesesesesewse",
            "nwnwnwnwseenwnwnwnwnwnwwwnwnwnwnwnwse",
            "neneeeenweneeneneeeeesenenenee",
            "esesenenewwseseseeeseeseneseeeesw",
            "ewwnewwnwwsenwwnwnwswnwnwnwnwnwnw",
            "nwnwnwnwnwnwnwnwnwnwswnwnwnwwenwnwnw",
            "swnwnenwswewnwwnwnw",
            "neneenesenewnenenewnenenenenenenenenene",
            "swswsewsesweseseswseneswnw",
            "seneneneneneswneneneeeeenwnenenenene",
            "nwnewsenwnwenwnenwnwnwenwnwnenwnwnww",
            "seneswnwsesesweswsenwnwweseneseswswse",
            "nwwsenwwwenwwsenwsewswnweenwsenww",
            "sewsesweeseneswnewseseseseswesenenesw",
            "seswseswseseswswseseswswseseswseswsene",
            "wwwwwwwwwwnwwewewwwwww",
            "seswnwseseseeseese",
            "swnweeeeeeeewseneeenweesww",
            "seswsesenwsesenwsewswswswseeseswseeswsese",
            "nwnwnwwnwnwwenwwwwwnwwnwwwnww",
            "nenenewseneneneneneneswnenenenwnewsenenenw",
            "nwwnwwnwnwnenwnwnwnwnwnwnwsewwnwnwnww",
            "eneeeeseswesewnenwnwseswsewnwwnenwse",
            "wenwsenwnwnenwnwwsewnwnwnwnwwsenwnww",
            "nwseenwwsenewwenwsewewneenwnwnwsw",
            "seneeswnwnesenwnwnwwesweswswnwnesenwnw",
            "wwswswswswswwswwswwswswswneswsw",
            "nwneneswenwswneneneswwsenwnesenenwnwnwnwne",
            "wwsesenenenwnewnenenenenwneneseeene",
            "nwsewwnwwesenwnwwnewnwwnwnwwwnw",
            "seneneewneeneeneeneneneeenenenenenew",
            "neswswsweswswswswnewneswwswwnwswwsw",
            "seseseeseeswnweesewsesesenewnwse",
            "senwwwwnwwwwnwwnwnwwwwwnww",
            "swswswwswswswswswswswneswswswswswswswsw",
            "neswswwenwnesenwneswnenwnenesewnenwnenene",
            "enenenenenenenesweenenenenwnwenese",
            "nwwwwwnwsewwwwwwwwwwwnww",
            "nenenesenenwneneneenenenenee",
            "nwenwnewswsewseewwwnwwe",
            "nwnwnwnwnwnwewnwnwnwnwswnwsenwwnenwnwnw",
            "swswswwswswswswswwswswnwswswswswesweswsw",
            "seseeseseseseseseswsesesenwsesenw",
            "wwwwnwsenwwwwwsenewnwnwwwww",
            "eeeeeneeeeeenesweeeeneene",
            "nwnenwnwnwwnewsewnesenwnwwwswwwww",
            "wswnwwewseswswwnenewwseswswnewse",
            "swseswswswswswswswswswswseswswswnwswnese",
            "nenwswwnenwseeswnwnweswwnwnwnwne",
            "nenwnwnenwnwwneneswnesenwnenwnwnenenwnw",
            "eeeeeeeeeeneweee",
            "swseseseseswswseeseswswswsenwswsesesw",
            "eneeeeeweeeeneeneeeeesee",
            "wseenwswswwswwwswweswseswnwnwwne",
            "seseswswsesenwseseseseseswweswswswsese",
            "nwwnwswwwnwwnwwewnewwsenwswnwnw",
            "swwswswswsweesenwswswswwneswwswwswnwe",
            "ewnwwwwseneswesewwenwwswswesw",
            "seseesesenwseswseseesewsesesesesesese",
            "swswswswneswneswswwswseswseseseseswswsw",
            "wswswswnwseswswswswwswswsweswswwsw",
            "nwnwnwnwnenwnenwswenwwswenenwnwnenwnw",
            "swswswswswswwseswswseseswswneswswswsese",
            "swswswwneswsweswwsewswwnwswwswswsww",
            "nenewneneneswnwnwene",
            "enweeeeeeeneeeeeswneeeswne",
            "enenwneeeeeneeneeneneeeeeswsw",
            "seseseseseswseseseseseseseseseenwsesesese",
            "nwnwswwwnwwsenwwenww",
            "eseseeseneseseseseseeeseewsesenwse",
            "seseseseseswseswsenwswseswsenw",
            "nwswseseseeseeenweeeseseeeeseee",
            "nwwwwwwwseeswswewnwswsw",
            "eseseseeseeseneseseeeeeweeesee",
            "seenwsewseeeseswswseseseesenweesesee",
            "neseweneenenenenenenenenwnenenenenwsw",
            "swseewneswswswswswseseswswswseswsenwsw",
            "swwneswswswswswswswseswwswswswswswwsw",
            "wswswwwswweswsweswwnwswswswwwwww",
            "wwwwwwswwswwnwwseswwewwwwnew",
            "nwnwnwnwwnwwweswnwnwnwnwnwnwnwwnwnw",
            "nenenenwseneswesewenewenenenwseenw",
            "eenweeeesweeeeeeeeeenee",
            "newswwnwswnenwwnwe",
            "senwseswseneeseeseseenwwsenesesesenesew",
            "eeeeeweeeeeeenweeseeseswsese",
            "neneneneneewneneneneneeswnenwneseeese",
            "enwswnwwnwneenwwnwnwnwnwnenwnwswnwnese",
            "swnwseenwwwseeseesesese",
            "seswwnenwwwnweeswenewnwswswnesee",
            "seswsweswseswswseeswnesewenwwnwsesesw",
            "nwsenwwnwnenenwwnwenesenwne",
            "neeenwneseewneswnwnwsewseswneswwse",
            "eseswsenesesweseeenwneesweneeee",
            "wnenwseweneeswswneswsesenwseswneesesw",
            "nwnenwsenenwnwnwseneswenwneswnwwnewne",
            "eeneeseeewsweew",
            "swwswnwswwwwswswwsewwwsewnesww",
            "eeeeneweseeeeseneseseseeeweese",
            "swnenwnewwswseneseswe",
            "swnwswsewswnwweseswswseseseseswneneswwe",
            "eneeseseswesenwsweseeeseneseesesese",
            "neenenwnwneseneswwneenenesw",
            "wwswwwwswwswwswswwwseswswwnew",
            "seseseseeeeeeseseneeseseeeesewse",
            "swseseswsweswnwseseseswseswseswseswnwsesw",
            "swneswswnwswswswewwswswseswswnewswse",
            "newnwsenwnwwwswwsewwneewwwsew",
            "weneneeeeeeeneseeeneswwnenenene",
            "wswswwswwwsewneswswwswswwwswswww",
            "neneneneneneneneeneneneseneneneneswnewnene",
            "sesesewsenwseswseneweseseneswswsenwse",
            "swnwnenwswnwesenwnwwnwsenwnwwewenw",
            "nwsewnenwnwnwnwnwnwnwwnwnesenwnwnwwwnw",
            "nesweswwswseneswswnwseenwnwswsweseswsw",
            "swneswwnenewseseeneenenenweneneesw",
            "eseseseeseseeeeseseenweeeswsese",
            "wwswwswswwswnwwwswwwwswswswswesw",
            "ewwnwswnwwwwwwwwwsewwwww",
            "seswnwseeswseswswseswsewswswswswsesesese",
            "eesenwseeeseseseeseseseseeswneee",
            "enwwwswwseswwswswwwwnwswwwswwww",
            "nwnwwnwwwnwnwsewnwnwnwnwnwnwnwnwwnw",
            "eeesesweseeseeeswneeesewenene",
            "eeeeeseweseeeeseeseee",
            "seseseseseeseseseeseseenwsenwseswswse",
            "nwsenwnwnwsenwnwsenenwnwwnwnenwnwnwnwnw",
            "senenesweneneswnwenenenenwnenenewwnenene",
            "neweeweeseeneeneeseseeneeeeenw",
            "wsenwnwnenwneeneseenwswsenene",
            "seeeneneeenweneneeeenee",
            "seeneeewseswnwseeseenwneseeeee",
            "wweswwnwseewseswewswswnwnenw",
            "nwwnwnwnwwwnwwnesewnwnewswseswne",
            "swswswswwswsenwswsww",
            "swwwsewwwwwwwnweewwnwwwe",
            "wsewwwwwwwswwnwwwswwwwww",
            "eeneeenweewsesweeeeneeneneenee",
            "neneneneneneneseneeneswneswnewnenenenene",
            "nenewsenwnewsewwseesewseneneswesee",
            "nwnwnwnwnwnwnwnwnwnwnwsewnenwnwnwwnwnwnw",
            "swwsewnenesewsweeseseswnesesesenwnese",
            "nwwwswwwwswnewwwwwwwwwsene",
            "sweeswswswnenwswswswnwswswswswswswwswsw",
            "esesenenewwseneneseneenwswnewwewww",
            "eseseneeseeseseseweseneeswesesese",
            "swswswswnenwsesweseseseswswseswseseswseswse",
            "eeeneneswneswneneeswswewenw",
            "sesewnesewseseseseseseseneesesesesesese",
            "seswswnesesesewswswseswneswnesesewswsese",
            "neesewnwwswneswwswsewswwnww",
            "eseewwseneswnewseneseseenwewswse",
            "nwwnwwnwnwwswseenwwnwnwwwnwnwnwnw",
            "wnwswwswwwnwwwnweneenwwswsewwnw",
            "nwseseseseneeseseeseseseswsesesesesesese",
            "nenwseeswnwwnwsenweswswswseese",
            "nenenwnenwnenenwnwnenenenwswnenwnenwnene",
            "enesenwswenwsesesenwswsenwsesesesenw",
            "neneeeeeneseeenenwnenenenenenenee",
            "sesesenewwseseseseswe",
            "swseswnwswsewswswnwneeswswseewseseswswse",
            "nwwnwnwnwwnwnwnwwnwwwnenwsewsewnwse",
            "swnewneswwwsewswwnenenenwwewswwsw",
            "swnwwnwsweeneeneeneeseseeneeeene",
            "eeseewneneeseeeeeeeeseeweee",
            "swswswswneseneswswwswwswseswseseneswswsw",
            "senwnwnenwnwwnwnwnwnwswnwnwnwenwswnwnw",
            "eeseseewseswneseneeenewsesesesenwse",
            "swseswswswswseseswswswseseswswnesw",
            "wwsenwnwsenwnenwnene",
            "swswswsweswswswwswswswnwnewswseswswww",
            "neneewswnenesweneesenwenewsweneseenw",
            "swswseswsweneswnwwsenwswswse",
            "nwsewsewwseneswnwwwswswwwwnwwww",
            "nesesewseeseseseeseswseesesesesenwse",
            "enenesenwsewswswnwseneeeeeeeeene",
            "nenweneesesweeneeeneeeeeneene",
            "wwswwwwwswswswnewswewwwenwsw",
            "wwwwwswnwsewswwsewwwswwwnwsww",
            "nesenenenewneneneneswswnenenenenenenenene",
            "eeneeweesweeeeeeenesweew",
            "swswswswseswseseneseswswseseswswseswsese",
            "newnwswswswsweseswsww",
            "nwswwnwsweswneswnwsenwnwsenenwneneee",
            "wnwewswneswswswneweswwswwswswnweswsw",
            "neswswswswswswwswewswswwswwswswswsw",
            "swsewswswseswswswswswsweseswswswswswsw",
            "nwwnwnwsewnenweswewnwwnwwswnwnww",
            "swenenenenenenenwneneneneeswneneneenenw",
            "wweswwwwswwswww",
            "enweneenesweneneeeneeneeneneene",
            "sesenewsenwseseseseswswswseseswswsenenesese",
            "nenewneeswnesenwswnwnenwenwnwnwnw",
            "neneswswnenenwswenwneeneseneneneewnww",
            "neneeeneneenweeneeneneswneenenene",
            "wwwwnwwwwwwswwwnwnwnewwww",
            "swswswswwwnewwswwwwswwswwwww",
            "nesweweneeeeeeeneweeeeene",
            "nwnwnewnwnwnwsewnwwnwwnwwnwwswwww",
            "wnwnwnwwwwswnwwwwnwew",
            "senwnwnenwnwnwnwnwwnwnwnenwnwnwnwnwnenw",
            "nwsewnenwnwnwnwnwnwnwnwnwwwnwwnwnwnwse",
            "neswneseeswsweswneneenweeene",
            "nenenwnwnwnenwnenenwnenwnwnwnweswnwsenwne",
            "nwnwnewwnwnwnwwwswwnwnwnwwwwnwnw",
            "seswwswswwswwswswswwswswwswswnwswsw",
            "nwnwnwwnwsenwsenwnwsenwenwwnw",
            "enwnwnwswnwsenwnwnenwnwenwsw",
            "seneseswwswwesesenwewneenweseeewnw",
            "swseesewewneesesenwnewseseseswsenw",
            "nenwnwnenwnenwneenwsenenwnwnwnwnwnwnwwne",
            "nwnwnenwnwnwwwnwwnwswnwnwwnwnwwww",
            "seseseseseseseseseseswswsesesesesenesenwse",
            "wswnwseseweeswseeneseeeneesesee",
            "senewsweswseseseswsesesesesesese",
            "neneneeeneeeneneeeenenweeneesw",
            "nwnenenenenenenenenwswnenenenenenenenw",
            "seseseseseseswsesewseseswsesenesw",
            "eseeeeseseseeseseseseseesesewene",
            "nwnwnwwnwnwnwnwnwwnwenwnwnwwnwwnwnw",
            "seswseseenwswswwsesenwseeeeneesenesese",
            "neeswseeseeeeeseeeeeeeseee"
    };



}
