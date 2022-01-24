package y2020.day_08;

public class Instruction {
    String op;
    int arg;
    boolean exec = false;

    public Instruction(String op, int arg) {
        this.op = op;
        this.arg = arg;
    }

    Instruction(Instruction i) {
        this.op = i.op;
        this.arg = i.arg;
    }
}
