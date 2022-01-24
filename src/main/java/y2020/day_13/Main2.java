package y2020.day_13;

public class Main2 {

    public static void main(String[] args) {
//        String[] in = input;
//        String[] line =in[1].split(",");
//
//        Map<Long, Long> map = new HashMap<>();
//        for (int i = 0; i < line.length; i++) {
//            if(!line[i].equals("x")) {
//                map.put(Long.parseLong(line[i]), (long)i);
//            }
//        }
//
//        Map.Entry<Long, Long> entry = map.entrySet().stream().sorted(Comparator.comparingLong(Map.Entry::getValue))
//                .reduce((a, b) ->  {
//                        boolean found = false;
//                        long timestamp = a.getValue();
//                        while (!found) {
//                            if ((timestamp+b.getValue())%b.getKey()==0) {
//                                found = true;
//                            } else {
//                                timestamp+=a.getKey();
//                            }
//                        }
//                        return Map.entry(a.getKey()*b.getKey(), timestamp);
//
//
//                }).get();
//        System.out.println(entry);

    }

    static String[] input = {
            "1000677",
            "29,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,661,x,x,x,x,x,x,x,x,x,x,x,x,13,17,x,x,x,x,x,x,x,x,23,x,x,x,x,x,x,x,521,x,x,x,x,x,37,x,x,x,x,x,x,x,x,x,x,x,x,19"
    };

    static String[] in2 = {
            "939",
            "7,13,x,x,59,x,31,19"
    };



}
