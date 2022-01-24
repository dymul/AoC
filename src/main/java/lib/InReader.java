package lib;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InReader {

    private final Class clazz;

    public InReader(Class clazz) {
        this.clazz = clazz;
    }

    public List<String> readInput (IN_TYPE in_type) {
        try {
            InputStream resourceAsStream = clazz.getResourceAsStream("/" + clazz.getName().replace('.', '/') + in_type.fileSuffix);
            BufferedReader rader = new BufferedReader(new InputStreamReader(resourceAsStream));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = rader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

