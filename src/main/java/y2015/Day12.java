package y2015;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lib.Day;

import java.util.Iterator;
import java.util.List;

public class Day12 implements Day {
    @Override
    public String solve(List<String> input) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        long numericValuesSum = 0;
        for (String in : input) {
            JsonNode jsonNode = objectMapper.readTree(in);
            numericValuesSum = getNumericValues(jsonNode);
            System.out.println("<-- " + numericValuesSum);

        }

        return "" + numericValuesSum;
    }

    @Override
    public String solve2(List<String> input) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        long numericValuesSum = 0;
        for (String in : input) {
            JsonNode jsonNode = objectMapper.readTree(in);
            numericValuesSum = getNumericValuesIgnoreRed(jsonNode);
            System.out.println("<-- " + numericValuesSum);

        }

        return "" + numericValuesSum;
    }

    private long getNumericValuesIgnoreRed(JsonNode node) {
        if (node.isNumber()) {
            System.out.print(node.asLong() + " ");
            return node.asLong();
        } else if (node.isObject()) {
            Iterator<JsonNode> elements = node.elements();
            boolean hasRed = false;
            while (elements.hasNext()) {
                JsonNode current = elements.next();
                if (current.isTextual() && current.asText().equals("red")) {
                    hasRed = true;
                }
            }
            if (!hasRed) {
                long result = 0;
                elements = node.elements();
                while (elements.hasNext()) {
                    result+=getNumericValuesIgnoreRed(elements.next());
                }
                return result;
            } else {
                return 0;
            }
        } else {
            long result = 0;
            Iterator<JsonNode> children = node.elements();
            while (children.hasNext()) {
                result+=getNumericValuesIgnoreRed(children.next());
            }
            return result;
        }
    }


    private long getNumericValues(JsonNode node) {
        if (node.isNumber()) {
            System.out.print(node.asLong() + " ");
            return node.asLong();
        } else {
            long result = 0;
            Iterator<JsonNode> children = node.elements();
            while (children.hasNext()) {
                result+=getNumericValues(children.next());
            }
            return result;
        }
    }

}
