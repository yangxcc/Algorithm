package StructureAndTrick.dp;

/**
 * 题目：给定一个正数数组，其中数组的值表示的是硬币的面值，
 * 给定一个target，每个硬币可以无限使用，请问有几种方式能够凑出target来
 * <p>
 * 比如：数组为[2,3,4,5,6], target = 100，给了无数个2，无数个3...有几种方法能够凑出100来
 */
public class Coins {

    public static int way1(int[] arr, int target) {
        if (arr == null || arr.length == 0 || target <= 0) {
            return 0;
        }
        return process1(arr, 0, target);
    }

    // index表示 可以使用的硬币的范围是index之后的全部硬币
    public static int process1(int[] arr, int index, int target) {
        if (index == arr.length) {
            return target == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int num = 0; num * arr[index] <= target; num++) {
            ways += process1(arr, index + 1, target - arr[index] * num);
        }
        return ways;
    }


    // 严格表结构的DP
    public static int way2(int[] arr, int target) {
        if (arr == null || arr.length == 0 || target <= 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][target + 1];
        dp[arr.length][0] = 1;   // 对dp数组初始化
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int aim = 0; aim <= target; aim++) {
                int ways = 0;
                for (int num = 0; num * arr[index] <= aim; num++) {
                    ways += dp[index + 1][aim - num * arr[index]];
                }
                dp[index][aim] = ways;
            }
        }
        return dp[0][target];
    }

    /**
     * 枚举行为被临近位置代替
     * */
    public static int way3(int[] arr, int target) {
        if (arr == null || arr.length == 0 || target <= 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][target + 1];
        dp[arr.length][0] = 1;   // 对dp数组初始化
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int aim = 0; aim <= target; aim++) {
                dp[index][aim] = dp[index + 1][aim];
                if (aim - arr[index] >= 0) {
                    dp[index][aim] += dp[index][aim - arr[index]];
                }
            }
        }
        return dp[0][target];
    }


    public static int[] getRandomArray(int length, int maxValue) {
        int[] arr = new int[(int) (Math.random() * length) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] arr1 = {2, 3};
        int target1 = 10;
        System.out.println(way2(arr1, target1));
        int testTimes = 1000;
        int maxValue = 1000;
        int length = 10;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = getRandomArray(length, maxValue);
            int target = (int) (Math.random() * 3 * maxValue) + maxValue;
            if (way1(arr, target) != way2(arr, target) || way1(arr, target) != way3(arr, target)) {
                System.out.println("oooooops");
                break;
            }
        }
        System.out.println("success");
    }
}
