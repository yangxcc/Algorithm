package forecrecursion;

/**
 * 给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表
 * i号物品的重量和价值，给定一个正数表示bag的载重，所装物品不能超过袋子的载重，
 * 返回能够装下的最大价值
 */
public class example07 {

    public static int returnMaxValue(int bag, int[] weights, int[] values) {
        if (bag <= 0) {
            return -1;
        }
        return process2(bag, weights, values, 0, 0,0);
    }

//    public static int process(int bag, int[] weights, int[] values, int i, int alreadyWeight) {
//        if (i == values.length) {
//            return 0;
//        }
//        if (alreadyWeight > bag) {
//            return 0;
//        }
//        // 对于第i号货物我有两种选择，一个是我选，一个是我不选
//        return Math.max(
//                process(bag, weights, values, i + 1, alreadyWeight),
//                values[i] + process(bag, weights, values, i + 1, alreadyWeight + weights[i])
//        );
//        // return alreadyWeight + weights[i] <= bag ? alreadyValue + values[i] : alreadyValue;
//    }

    public static int process2(int bag, int[] weights, int[] values, int i, int alreadyWeight, int alreadyValue) {
        if (i == values.length) {
            return 0;
        }
        if (alreadyWeight > bag) {
            return alreadyValue;
        }
        // 对于第i号货物我有两种选择，一个是我选，一个是我不选
        return Math.max(
                process2(bag, weights, values, i + 1, alreadyWeight,alreadyValue),
                values[i] + process2(bag, weights, values, i + 1, alreadyWeight + weights[i],alreadyValue + values[i])
        );
        // return alreadyWeight + weights[i] <= bag ? alreadyValue + values[i] : alreadyValue;
    }

    public static void main(String[] args) {
        int[] w = {1, 2, 3, 4};
        int[] v = {1, 3, 4, 2};
        int bag = 6;
        System.out.println(returnMaxValue(bag, w, v));
    }
}
