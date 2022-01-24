package y2020.day_18;

public enum Operator {
    ADD {
        @Override
        long eval(long a, long b) {
            return a+b;
        }
    }, EXTRACT {
        @Override
        long eval(long a, long b) {
            return a-b;
        }
    }, DIVIDE {
        @Override
        long eval(long a, long b) {
            return a/b;
        }
    }, MULTIPLY {
        @Override
        long eval(long a, long b) {
            return a*b;
        }
    };

    abstract long eval(long a, long b);

    static Operator fromString(String s) {
        switch (s) {
            case "+":
                return ADD;
            case "-":
                return EXTRACT;
            case "/" :
                return DIVIDE;
            case "*":
                return MULTIPLY;
            default:
                throw new RuntimeException("Invalid in: " + s);
        }
    }

}
