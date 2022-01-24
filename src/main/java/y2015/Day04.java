package y2015;

import lib.Day;

import java.security.MessageDigest;
import java.util.List;

public class Day04 implements Day {
    @Override
    public String solve(List<String> input) throws Exception {
        String in = input.get(0);
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        int i =1;
        while(true) {
            String s = in + i;
            byte[] digest = md5.digest(s.getBytes());
            if (firstFiveHexZero(digest)) {
                return Integer.toString(i);
            } else {
                i++;
            }
        }
    }

    private boolean firstFiveHexZero(byte[] bytes) {
        return bytes[0]==0&&bytes[1]==0&&bytes[2]>=0;
    }

    private boolean firstSixHexZero(byte[] bytes) {
        return bytes[0]==0&&bytes[1]==0&&bytes[2]==0;
    }

    @Override
    public String solve2(List<String> input) throws Exception {
        String in = input.get(0);
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        int i =1;
        while(true) {
            String s = in + i;
            byte[] digest = md5.digest(s.getBytes());
            if (firstSixHexZero(digest)) {
                return Integer.toString(i);
            } else {
                i++;
            }
        }
    }
}
