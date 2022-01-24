package y2020.day_07;


public class Neigbour {
    Entry entry;
    int number;

    public Neigbour(Entry entry, int number) {
        this.entry = entry;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Child{" +
                "entry=" + entry +
                ", number=" + number +
                '}';
    }
}
