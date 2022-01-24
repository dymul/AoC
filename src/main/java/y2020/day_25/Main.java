package y2020.day_25;

public class Main {

    static long card_pub_key = 1965712;
    static long card_pub_key_t = 5764801;
    static long door_pub_key = 19072108;
    static long door_pub_key_t = 17807724;
    static int subject_number = 7;

    public static void main(String[] args) {
        int cardLoopSize = findLoopSize(card_pub_key);
        int doorLoopSize = findLoopSize(door_pub_key);

        System.out.println(cardLoopSize);
        System.out.println(doorLoopSize);

        long enc1 = transform(card_pub_key, doorLoopSize);
        long enc2 = transform(door_pub_key, cardLoopSize);

        System.out.println(enc1);
        System.out.println(enc2);


    }

    static long transform(long key, int loopSzize) {
        long result = 1;
        while (loopSzize-->0) {
            result*=key;
            result%=20201227;
        }
        return result;
    }

    static int findLoopSize(long exp) {
        long result = 1;
        int loop_size = 0;
        while (result!=exp) {
            loop_size++;
            result*=7;
            result%=20201227;
        }
        return loop_size;
    }




}
