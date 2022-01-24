package y2020.day_04;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Passport {

    Passport(String data) {
        String[] keyValues = data.split(" ");
        for(String keyValue: keyValues) {
            String key= keyValue.substring(0,3);
            switch (key) {
                case "byr":
                    byr = Integer.valueOf(keyValue.substring(4));
                    break;
                case "iyr":
                    iyr = Integer.valueOf(keyValue.substring(4));
                    break;
                case "eyr":
                    eyr = Integer.valueOf(keyValue.substring(4));
                    break;
                case "hgt":
                    hgt = keyValue.substring(4);
                    break;
                case "hcl":
                    hcl = keyValue.substring(4);
                    break;
                case "ecl":
                    ecl = keyValue.substring(4);
                    break;
                case "pid":
                    pid = keyValue.substring(4);
                    break;
                case "cid":
                    cid = keyValue.substring(4);
                    break;
            }
        }
        System.out.println(toString() + "isValid: " + isValid2());
    }

    Integer byr;
    Integer iyr;
    Integer eyr;
    String hgt;
    String hcl;
    String ecl;
    String pid;
    String cid;

    public boolean isValid() {
        boolean valid = byr != null && iyr != null && eyr != null && hgt != null && hcl != null && ecl != null && pid != null;
        return valid;
    }

    public boolean isValid2() {
        return validateByr() && validateIyr() && validateEyr() && validateHgt() && validateHcl() && validateEcl() && validatePid();
    }

    private boolean validateByr() {
        boolean valid = byr != null && byr >= 1920 && byr <= 2002;
        return valid;
    }

    private boolean validateIyr() {
        boolean valid = iyr != null && iyr >= 2010 && iyr <= 2020;
        return valid;
    }

    private boolean validateEyr() {
        boolean valid = eyr != null && eyr >= 2020 && eyr <= 2030;
        return valid;
    }

    private boolean validateHgt() {
        if (hgt == null) {
            return false;
        }
        if (hgt.endsWith("cm")) {
            Integer cm = Integer.valueOf(hgt.replace("cm", ""));
            boolean valid = cm >= 150 && cm <= 193;
            return valid;
        } else if (hgt.endsWith("in")) {
            Integer in = Integer.valueOf(hgt.replace("in", ""));
            boolean valid = in >= 59 && in <= 76;
            return valid;
        }
        return false;
    }

    private boolean validateHcl() {
        boolean valid = hcl != null && hcl.matches("#([0-9a-f)]{6})");
        return valid;
    }

    Set<String> validEcl = Stream.of("amb","blu","brn","gry","grn","hzl","oth").collect(Collectors.toSet());

    private boolean validateEcl() {
        boolean valid = ecl != null && validEcl.contains(ecl);
        return valid;
    }

    private boolean validatePid() {
        boolean valid = pid != null && pid.matches("([0-9]){9}");
        return valid;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "byr=" + byr +
                ", iyr=" + iyr +
                ", eyr=" + eyr +
                ", hgt=" + hgt +
                ", hcl='" + hcl + '\'' +
                ", ecl='" + ecl + '\'' +
                ", pid='" + pid + '\'' +
                ", cid='" + cid + '\'' +
                '}';
    }
}
