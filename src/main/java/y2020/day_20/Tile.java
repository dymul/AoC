package y2020.day_20;

import java.util.*;
import java.util.stream.Collectors;

public class Tile {
    int id;
    List<String> points = new LinkedList<>();
    Set<String> edges = new HashSet<>();
    int mute = 0;

    Tile(List<String> points) {
        this.points.addAll(points);
        String id = this.points.remove(0).replaceAll("Tile ", "").replaceAll(":", "");
        this.id = Integer.valueOf(id);
        fillEdges();
    }

    private void fillEdges() {
        String top = points.get(0);
        edges.add(top);
        edges.add(new StringBuilder(top).reverse().toString());
        String bottom = points.get(points.size()-1);
        edges.add(bottom);
        edges.add(new StringBuilder(bottom).reverse().toString());
        String left = points.stream().map(s-> s.substring(0,1)).collect(Collectors.joining());
        edges.add(left);
        edges.add(new StringBuilder(left).reverse().toString());
        int size = points.size();
        String right = points.stream().map(s-> s.substring(size-1,size)).collect(Collectors.joining());
        edges.add(right);
        edges.add(new StringBuilder(right).reverse().toString());
    }

    long countHashes() {
        return points.stream().mapToLong(this::countHashes).sum();
    }

    long countHashes(String line) {
        return Arrays.stream(line.split("")).filter(s -> s.equals("#")).count();
    }

    void cleanBorders() {
        points.remove(0);
        points.remove(points.size()-1);
        points = points.stream().map(s -> s.substring(1, s.length()-1)).collect(Collectors.toList());
    }

    void flipTop() {
        Collections.reverse(points);
    }

    void flipLeft() {
        points = points.stream().map(s -> new StringBuilder(s).reverse().toString()).collect(Collectors.toList());
    }

    void rotate() {
        List<String> newPoints = new LinkedList<>();
        for (int i = points.size() -1 ; i >= 0; i--) {
            int idx = i;
            newPoints.add(points.stream().map(s-> s.substring(idx,idx+1)).collect(Collectors.joining()));
        }
        points = newPoints;
    }
    void mute() {
        if (mute%2==0) {
            flipTop();
        } else {
            flipTop();
            rotate();
        }
        mute++;
    }

    String top() {
        return points.get(0);
    }
    String bottom() {
        return points.get(points.size()-1);
    }
    String left() {
        return points.stream().map(s-> s.substring(0,1)).collect(Collectors.joining());
    }
    String right() {
        int size = points.size();
        return points.stream().map(s-> s.substring(size-1,size)).collect(Collectors.joining());
    }

    @Override
    public String toString() {
        return "Tile{" +
                "id=" + id +
                ", points=" + points +
                ", edges=" + edges +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return id == tile.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
