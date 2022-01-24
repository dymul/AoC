package y2020.day_17;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Point2 {
    final int x;
    final int y;
    final int z;
    final int w;

    public Point2(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    Set<Point2> neighs() {
        Set<Point2> result = new HashSet<>();
        for (int i = -1; i <=1 ; i++) {
            for (int j = -1; j <=1 ; j++) {
                for (int k = -1; k <=1 ; k++) {
                    for (int l = -1; l <=1 ; l++) {
                        if (i==0&&k==0&&j==0&&l==0) continue;
                        result.add(new Point2(x+i, y+j, z+k, w+l));
                    }
                }
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2 point2 = (Point2) o;
        return x == point2.x &&
                y == point2.y &&
                z == point2.z &&
                w == point2.w;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }

    @Override
    public String toString() {
        return "Point2{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }
}
