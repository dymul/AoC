package y2020.day_17;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Point {
    final int x;
    final int y;
    final int z;

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    Set<Point> neighs() {
        Set<Point> result = new HashSet<>();
        for (int i = -1; i <=1 ; i++) {
            for (int j = -1; j <=1 ; j++) {
                for (int k = -1; k <=1 ; k++) {
                    if (i==0&&k==0&&j==0) continue;
                    result.add(new Point(x+i, y+j, z+k));
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y &&
                z == point.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
