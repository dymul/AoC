package y2020.day_12;

public class Ship2 {
                        //N, E, S, W
    static int[] dir_x = {0, 1, 0, -1};
    static int[] dir_y = {-1, 0, 1, 0};

    int curr_dir = 1;

    int w_x_pos = 10;
    int w_y_pos = -1;

    int s_x_pos = 0;
    int s_y_pos = 0;

    void move(String[] route) {
        for (String s : route) {
            handle(s);
            System.out.println(position() + ", waypoint:" + w_position());
        }
    }

    String position() {
        return "X:" + s_x_pos + ", Y:" + s_y_pos;
    }

    String w_position() {
        return "X:" + w_x_pos + ", Y:" + w_y_pos;
    }

    int distance() {
        return s_x_pos*Integer.signum(s_x_pos) + s_y_pos*Integer.signum(s_y_pos);
    }

    private void handle(String s) {
        String direction = s.substring(0,1);
        int val = Integer.parseInt(s.substring(1));
        switch (direction) {
            case "N":
            case "S":
            case "W":
            case "E":
                move_waypoint(direction, val);
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
        s_x_pos = s_x_pos + val*w_x_pos;
        s_y_pos = s_y_pos + val*w_y_pos;
    }

    private void rotate(String direction, int val) {
        int rot = val/90;
        while (rot>3) rot-=4;
        if (rot ==2) {
            w_x_pos*=-1;
            w_y_pos*=-1;
        } else if ((rot == 1 && direction.equals("R")) ||
                    rot == 3 && direction.equals("L")) {
            int tmp_w_x_pos = w_y_pos*-1;
            w_y_pos = w_x_pos;
            w_x_pos=tmp_w_x_pos;

        } else if ((rot == 1 && direction.equals("L")) ||
                rot == 3 && direction.equals("R")) {
            int tmp_w_y_pos = w_x_pos*-1;
            w_x_pos = w_y_pos;
            w_y_pos = tmp_w_y_pos;
        }
    }

    private void move_waypoint(String direction, int val) {
        w_x_pos = w_x_pos + val*dir_x[vector(direction)];
        w_y_pos = w_y_pos + val*dir_y[vector(direction)];
    }

    private int vector(String direction) {
        if (direction.equals("N")) return 0;
        if (direction.equals("E")) return 1;
        if (direction.equals("S")) return 2;
        if (direction.equals("W")) return 3;
        return -1;
    }
}
