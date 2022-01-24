package y2020.day_10;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int arr[] = input;
        Arrays.sort(arr);
        int three = 1;
        int one = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i+1] - arr[i] == 1) {
                one++;
            } else if (arr[i+1] - arr[i] == 3) {
                three++;
            }else {
                System.out.println(i);
            }
        }
        //System.out.printf("%d, %d, %d\n", one, three, one*three);

        System.out.println(ways(arr, 0));

    }

   static  long ways(int[] arr, int from) {
        if (from >= arr.length ) {
            return 0;
        }
        if (from == arr.length-1) return 1;
        long result = ways(arr, from+1);
        if (from < arr.length-2 && arr[from+2] - arr[from] <= 3) {
            result+=ways(arr, from+2);
        }
        if (from < arr.length-3 && arr[from+3] - arr[from] <= 3) {
            result+=ways(arr, from+3);
        }
        return result;
    }


    static int[] in1 = {
            0,
            16,
            10,
            15,
            5,
            1,
            11,
            7,
            19,
            6,
            12,
            4
    };







    static int[] in2 = {
            0,
            28,
            33,
            18,
            42,
            31,
            14,
            46,
            20,
            48,
            47,
            24,
            23,
            49,
            45,
            19,
            38,
            39,
            11,
            1,
            32,
            25,
            35,
            8,
            17,
            7,
            9,
            4,
            2,
            34,
            10,
            3
    };

    static int[] input = {
            0,
            144,
            10,
            75,
            3,
            36,
            80,
            143,
            59,
            111,
            133,
            1,
            112,
            23,
            62,
            101,
            137,
            41,
            24,
            8,
            121,
            35,
            105,
            161,
            69,
            52,
            21,
            55,
            29,
            135,
            142,
            38,
            108,
            141,
            115,
            68,
            7,
            98,
            82,
            9,
            72,
            118,
            27,
            153,
            140,
            61,
            90,
            158,
            102,
            28,
            134,
            91,
            2,
            17,
            81,
            31,
            15,
            120,
            20,
            34,
            56,
            4,
            44,
            74,
            14,
            147,
            11,
            49,
            128,
            16,
            99,
            66,
            47,
            125,
            155,
            130,
            37,
            67,
            54,
            60,
            48,
            136,
            89,
            119,
            154,
            122,
            129,
            163,
            73,
            100,
            85,
            95,
            30,
            76,
            162,
            22,
            79,
            88,
            150,
            53,
            63,
            92
    };

}
