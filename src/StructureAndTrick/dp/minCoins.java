package StructureAndTrick.dp;

/**
 * 给定一个正数数组，和一个target值
 * 求数组中最少元素能够拼出 target的coin
 */
public class minCoins {
    public static int minCoinsAlgorithm(int[] arr, int target) {
        if (arr.length < 1 || arr == null || target < 0) {
            return -1;
        }
        return process(arr, target, 0);
    }

    // index表示当前硬币
    public static int process(int[] arr, int target, int index) {
        // base case
        if (target == 0) {
            return 0; // 需要0个硬币
        }
        if (index > arr.length - 1) {
            return -1;      // 数组都遍历完了，还没找到
        }
        if (target < 0) {
            return -1;
        }
        // 对于一个硬币来说，我可以选它，也可以不选他
        int p1 = process(arr, target, index + 1);  // 不选
        int p2 = process(arr, target - arr[index], index + 1); // 选他
        if (p1 < 0 && p2 < 0) {
            return -1;
        } else if (p1 < 0) {
            return p2 + 1;
        } else if (p2 < 0) {
            return p1;
        } else {
            return Math.min(p1, p2 + 1);
        }
    }


    public static int minCoinsAlgorithm02(int[] arr, int target) {
        if (arr.length < 1 || arr == null || target < 0) {
            return -1;
        }
        int[][] dp = new int[arr.length + 1][target + 1];
        for (int i = 0; i <= arr.length; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = -2;
            }
        }
        return process02(arr, target, 0, dp);
    }

    // index表示当前硬币
    public static int process02(int[] arr, int target, int index, int[][] dp) {
        // base case
        if (dp[index][target] != -2) {
            return dp[index][target];
        }
        if (index == arr.length - 1 && target == 0) {
            dp[index][target] = 0;
            // return 0; // 需要0个硬币
        }
        if (index > arr.length - 1) {
            dp[index][target] = -1;
            //return -1;      // 数组都遍历完了，还没找到
        }
        if (target < 0) {
            dp[index][target] = -1;
            //return -1;
        }
        // 对于一个硬币来说，我可以选它，也可以不选他
        int p1 = process(arr, target, index + 1);  // 不选
        int p2 = process(arr, target - arr[index], index + 1); // 选他
        if (p1 < 0 && p2 < 0) {
            dp[index][target] = -1;
//            return -1;
        } else if (p1 < 0) {
            dp[index][target] = p2 + 1;
            //return p2 + 1;
        } else if (p2 < 0) {
            dp[index][target] = p1;
//            return p1;
        } else {
            dp[index][target] = Math.min(p1, p2 + 1);
//            return Math.min(p1, p2 + 1);
        }
        return dp[index][target];
    }


    public static int minCoinsAlgorithm3(int[] arr, int target) {
        if (arr.length < 1 || arr == null || target < 0) {
            return -1;
        }
        int[][] dp = new int[arr.length + 1][target + 1];
        for (int i = 0; i <= arr.length; i++) {
            dp[i][0] = 0;
        }
        for (int j = 1; j <= target; j++) {
            dp[arr.length][j] = -1;
        }
        for (int index = arr.length - 1; index >= 0; index--) {   // row
            for (int aim = 1; aim <= target; aim++) {             // column

                int p1 = dp[index + 1][aim];
                int p2 = -1;
                if (aim - arr[index] >= 0) {
                    p2 = dp[index + 1][aim - arr[index]];
                }
//                // 对于一个硬币来说，我可以选它，也可以不选他
//                int p1 = process(arr, target, index + 1);  // 不选
//                int p2 = process(arr, target - arr[index], index + 1); // 选他
                if (p1 < 0 && p2 < 0) {
                    dp[index][aim] = -1;
//                    return -1;
                } else if (p1 < 0) {
                    dp[index][aim] = p2 + 1;
//                    return p2 + 1;
                } else if (p2 < 0) {
                    dp[index][aim] = p1;
//                    return p1;
                } else {
                    dp[index][aim] = Math.min(p1, p2 + 1);
//                    return Math.min(p1, p2 + 1);
                }
            }
        }
        return dp[0][target];
    }

    // index表示当前硬币
//    public static int process3(int[] arr, int target, int index, int[][] dp) {
//        if (target < 0) {
//            return -1;
//        }
//        // base case
//        if (dp[index][target] == -2) {
//            return dp[index][target];
//        }
//        if (target == 0) {
//            return 0; // 需要0个硬币
//        }
//        if (index > arr.length - 1) {
//            return -1;      // 数组都遍历完了，还没找到
//        }
//
//        // 对于一个硬币来说，我可以选它，也可以不选他
//        int p1 = process(arr, target, index + 1);  // 不选
//        int p2 = process(arr, target - arr[index], index + 1); // 选他
//        if (p1 < 0 && p2 < 0) {
//            return -1;
//        } else if (p1 < 0) {
//            return p2 + 1;
//        } else if (p2 < 0) {
//            return p1;
//        } else {
//            return Math.min(p1, p2 + 1);
//        }
//    }


    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 6, 7, 8, 9, 4};
        int target = 10;
        System.out.println("=====暴力递归=====");
        int res = minCoinsAlgorithm(arr, target);
        System.out.println(res);
        System.out.println("=====记忆性搜索遍历=====");
        res = minCoinsAlgorithm02(arr, target);
        System.out.println(res);
        System.out.println("=====静态表优化=====");
        res = minCoinsAlgorithm3(arr, target);
        System.out.println(res);
    }
}
