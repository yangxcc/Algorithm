package 剑指offer;

/**
 * 左旋转字符串
 * */
public class offer58_2 {
    public String reverseLeftWords(String s, int n) {
        String res = s + s;
        return res.substring(n, n + s.length());
    }
}
