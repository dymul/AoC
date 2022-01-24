#!/usr/bin/env bash

create_input_files() {
touch "./input/y$1/Day$2_c.txt"
touch "./input/y$1/Day$2_t.txt"
}

createDay() {
    echo "package y$1;

import lib.Day;

import java.util.List;

public class Day$2 implements Day {
    @Override
    public String solve(List<String> input) throws Exception {
        return null;
    }

    @Override
    public String solve2(List<String> input) throws Exception {
        return null;
    }
}" >> ./src/main/java/y$1/Day$2.java

}
year=$1
day=$2

create_input_files $year $day
createDay $year $day

