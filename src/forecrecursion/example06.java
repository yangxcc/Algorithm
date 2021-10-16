package forecrecursion;

/**
 * 规定1和A对应，2和B对应，3和C对应...
 * 假如有一个字符串“111”就可以转化成 AAA, KA, AK
 * 给定一个只有数字的字符串str，返回多少种转化结果
 * */
public class example06 {

    public static int transformNumToString(String nums) {
        if (nums == null) {
            return -1;
        }
        char[] chars = nums.toCharArray();
        return process(chars,0);
    }

    public static int process(char[] chs, int index) {
        if (index == chs.length) {
            return 1;
        }
        // 假设字符串已经遍历到 index 了，index之前的字母已经确定好了
        // 如果char[index] == 0
        if (chs[index] == '0') {
            return 0;
        }
        // 如果char[index] == 1
        if (chs[index] == '1') {
            int result = process(chs, index + 1);   // index自己
            if (index + 1 < chs.length) {
                result += process(chs, index + 2);  // 让(i,i+1)是一个整体
            }
            return result;
        }
        // 如果char[index] == 1
        if (chs[index] == '2') {
            int result = process(chs, index + 1);
            if (index + 1 < chs.length && chs[index + 1] <= 6) {
                result += process(chs, index + 1);
            }
            return result;
        }
        return process(chs, index + 1);
    }

    public static void main(String[] args) {
        String nums = "111";
        int count = transformNumToString(nums);
        System.out.println(count);
    }
}
