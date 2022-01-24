package y2020.day_07a;


public class Child {
    Entry entry;
    int amount;

    public Child(Entry entry, int amount) {
        this.entry = entry;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Child{" +
                "entry=" + entry +
                ", amount=" + amount +
                '}';
    }
}
