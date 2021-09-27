package basic;

/**
 * 用递归方式求解数组上的最大值
 * */
public class getMax {

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);    // 不直接写 R+L是因为当R和L很大时，R+L有可能会溢出
        int leftMax = process(arr,L,mid);
        int rightMax = process(arr,mid+1,R);
        return Math.max(leftMax,rightMax);
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        int max = process(arr,0,arr.length-1);
        System.out.println(max);
    }
}
