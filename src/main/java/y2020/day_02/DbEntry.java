package y2020.day_02;

public class DbEntry {

    final int low;
    final int high;
    final String letter;
    final String password;

    DbEntry(String entry) {
        // "3-11 z: zzzzzdzzzzlzz"
        String entries[] = entry.split(" ");
        String[] low_high = entries[0].split("-");
        low = Integer.parseInt(low_high[0]);
        high = Integer.parseInt(low_high[1]);
        letter = entries[1].substring(0,1);
        password = entries[2];
        System.out.println("Input: " + entry + ", " + this.toString() + " Valid:" + isValid());
    }

    boolean isValid() {
        long count = password.chars().filter(l -> l == letter.charAt(0)).count();
        return count >=low && count <=high;
    }

    boolean isValid2() {
        return (password.charAt(low-1)==letter.charAt(0))^(password.charAt(high-1)==letter.charAt(0));

    }

    @Override
    public String toString() {
        return "DbEntry{" +
                "low=" + low +
                ", high=" + high +
                ", letter='" + letter + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
