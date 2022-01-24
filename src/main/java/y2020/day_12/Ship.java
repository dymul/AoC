package y2020.day_12;

public class Ship {
                        //N, E, S, W
    static int[] dir_x = {0, 1, 0, -1};
    static int[] dir_y = {-1, 0, 1, 0};

    int curr_dir = 1;

    int x_pos = 0;
    int y_pos = 0;

    void move(String[] route) {
        for (String s : route) {
            handle(s);
            System.out.println(position());
        }
    }

    String position() {
        return "X:" + x_pos + ", Y:" + y_pos;
    }

    int distance() {
        return x_pos*Integer.signum(x_pos) + y_pos*Integer.signum(y_pos);
    }

    private void handle(String s) {
        String direction = s.substring(0,1);
        int val = Integer.parseInt(s.substring(1));
        switch (direction) {
            case "N":
            case "S":
            case "W":
            case "E":
                move_ship(direction, val);
                break;
            case "L":
            case "R":
                rotate(direction, val);
                break;
            case "F":
                moveForward(val);
                break;

        }
    }

    private void moveForward(int val) {
        x_pos = x_pos + val*dir_x[curr_dir];
        y_pos = y_pos + val*dir_y[curr_dir];
    }

    private void rotate(String direction, int val) {
        int tmp = curr_dir;
        if (direction.equals("L")) {
            tmp-=(val/90);
        } else {
            tmp+=(val/90);
        }
        while(tmp<0) tmp+=4;
        while(tmp>3) tmp-=4;
        curr_dir = tmp;
    }

    private void move_ship(String direction, int val) {
        x_pos = x_pos + val*dir_x[vector(direction)];
        y_pos = y_pos + val*dir_y[vector(direction)];
    }

    private int vector(String direction) {
        if (direction.equals("N")) return 0;
        if (direction.equals("E")) return 1;
        if (direction.equals("S")) return 2;
        if (direction.equals("W")) return 3;
        return -1;
    }
}
