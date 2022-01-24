package lib;

public enum IN_TYPE {
    TEST("_t.txt"), CHALLENGE("_c.txt");

    final String fileSuffix;

    IN_TYPE(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }
}
