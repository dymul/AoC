package lib;

public class Split {

    private String[] split;

    public Split(String string, String pattern) {
        split = string.split(pattern);
    }

    public String get(int i) {
        return split[i];
    }

    public Integer getAsInt(int i) {
        return Integer.parseInt(split[i]);
    }
}
