package y2015;

import lib.Day;
import lib.Split;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day07 implements Day {
    @Override
    public String solve(List<String> input) throws Exception {
        Map<String, Gate> gates = new HashMap<>();
        gates.put("1", new SimpleGate(1));

        for (String gateDef : input) {
            Split split1 = new Split(gateDef, " -> ");
            String gate = split1.get(1);
            String def = split1.get(0);;
            Gate g;

            if (def.contains("AND")) {
                String[] split = def.split(" AND ");
                g = new AndGate(split[0], split[1]);
            } else if (def.contains("OR")) {
                String[] split = def.split(" OR ");
                g = new OrGate(split[0], split[1]);
            } else if (def.contains("LSHIFT")) {
                String[] split = def.split(" LSHIFT ");
                g = new LShiftGate(split[0], Integer.valueOf(split[1]));
            } else if (def.contains("RSHIFT")) {
                String[] split = def.split(" RSHIFT ");
                g = new RShiftGate(split[0], Integer.valueOf(split[1]));
            } else if (def.contains("NOT")) {
                g = new NotGate(def.replace("NOT ", ""));
            } else {
                try {
                    g = new SimpleGate(Integer.parseInt(def));
                } catch (NumberFormatException e) {
                    g = new PassGate(def);
                }
            }
            gates.put(gate, g);
        }

        return String.valueOf(gates.get("a") != null ? gates.get("a").getSignal(gates) : gates.get("d").getSignal(gates));
    }

    @Override
    public String solve2(List<String> input) throws Exception {
        Map<String, Gate> gates = new HashMap<>();
        gates.put("1", new SimpleGate(1));
        gates.put("b", new SimpleGate(956));

        for (String gateDef : input) {
            String gate = gateDef.split(" -> ")[1];
            String def = gateDef.split(" -> ")[0];
            Gate g;
            if (gate.equals("b")) continue;

            if (def.contains("AND")) {
                String[] split = def.split(" AND ");
                g = new AndGate(split[0], split[1]);
            } else if (def.contains("OR")) {
                String[] split = def.split(" OR ");
                g = new OrGate(split[0], split[1]);
            } else if (def.contains("LSHIFT")) {
                String[] split = def.split(" LSHIFT ");
                g = new LShiftGate(split[0], Integer.valueOf(split[1]));
            } else if (def.contains("RSHIFT")) {
                String[] split = def.split(" RSHIFT ");
                g = new RShiftGate(split[0], Integer.valueOf(split[1]));
            } else if (def.contains("NOT")) {
                g = new NotGate(def.replace("NOT ", ""));
            } else {
                try {
                    g = new SimpleGate(Integer.parseInt(def));
                } catch (NumberFormatException e) {
                    g = new PassGate(def);
                }
            }
            gates.put(gate, g);
        }
        return String.valueOf(gates.get("a") != null ? gates.get("a").getSignal(gates) : gates.get("d").getSignal(gates));
    }
}


interface Gate {

    int getSignal(Map<String, Gate> gates);
}

class PassGate implements Gate {

    String passed;
    Integer result;

    public PassGate(String passed) {
        this.passed  = passed;
    }

    @Override
    public int getSignal(Map<String, Gate> gates) {
        if (result == null) {
            result = gates.get(passed).getSignal(gates);
        }
        return result;
    }
}

class SimpleGate implements Gate {

    int val;

    public SimpleGate(int val) {
        this.val  = val;
    }

    @Override
    public int getSignal(Map<String, Gate> gates) {
        return val;
    }
}

class AndGate implements Gate {

    Integer result;
    String first,second;

    public AndGate(String first, String second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int getSignal(Map<String, Gate> gates) {
        if (result == null) {
            result = gates.get(first).getSignal(gates) & gates.get(second).getSignal(gates);
        }
        return result;
    }
}

class OrGate implements Gate {

    Integer result;
    String first,second;

    public OrGate(String first, String second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int getSignal(Map<String, Gate> gates) {
        if (result == null) {
            result = gates.get(first).getSignal(gates) | gates.get(second).getSignal(gates);
        }
        return result;
    }
}

class LShiftGate implements Gate {

    Integer result;
    String gate;
    int shift;

    public LShiftGate(String gate, int shift) {
        this.gate = gate;
        this.shift = shift;
    }

    @Override
    public int getSignal(Map<String, Gate> gates) {
        if (result == null) {
            result = (gates.get(gate).getSignal(gates) << shift) & 0x0000FFFF ;
        }
        return result;
    }
}

class RShiftGate implements Gate {

    Integer result;
    String gate;
    int shift;

    public RShiftGate(String gate, int shift) {
        this.gate = gate;
        this.shift = shift;
    }

    @Override
    public int getSignal(Map<String, Gate> gates) {
        if (result == null) {
            result = gates.get(gate).getSignal(gates) >>shift;
        }
        return result;
    }
}

class NotGate implements Gate {

    Integer result;
    String gate;

    public NotGate(String gate) {
        this.gate = gate;
    }

    @Override
    public int getSignal(Map<String, Gate> gates) {
        if (result == null) {
            result = (~gates.get(gate).getSignal(gates))  & 0x0000FFFF ;
        }
        return result;
    }
}

