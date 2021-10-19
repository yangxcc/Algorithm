package hash;

public class BitMap {

    public static void main(String[] args) {
        int[] arr = new int[10];  // 32 bit * 10 = 320
        // arr[0]   能够表示 0 ~ 31 位的数据
        // arr[1]   能够表示 32~ 63 位的数据
        // ...
        int i = 178;  // 如果我想知道178位的状态
        int numIndex = 178 / 32;  // 在哪块上面
        int offset = 178 % 32;    // 块上的偏移量

        int s = (arr[numIndex] >> offset) & 1;  // 第178位的状态

        arr[numIndex] = arr[numIndex] | (1 << offset);      // 将第178位的状态改成1

        arr[numIndex] = arr[numIndex] & (~(1 << offset));   // 将第178位的状态改成0
    }
}
