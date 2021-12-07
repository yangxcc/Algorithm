package 括号题目;

/**
 * 使括号有效的最少添加
 * <p>
 * 不能简单的判断左括号和右括号的个数，让他们相减，
 * 对于这个测试用例就不行了   "()))(("
 * <p>
 * 应该恰是需要添加4个括号，如果按照上面思路返回的是0
 */
public class minimumAddToMakeParenthesesValid {

    public int minAddToMakeValid(String s) {
        if (s.equals("") || s.length() == 0) {
            return 0;
        }
        int need = 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                need++;
            } else {
                need--;
                if (need == -1) {
                    res++;  // 填上一个左括号
                    need = 0;
                }
            }
        }
        return res + need;  // need表示多出来的左括号，即需要的右括号数目
    }

    /**
     * 如果一个(需要两个)来匹配，那么算法如下
     */
    public int minInsertions(String s) {
        if (s.equals("") || s.length() == 0) {
            return 0;
        }
        // 需要右括号的个数
        int need = 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                need += 2;  // 需要两个右括号
                if (need % 2 == 1) {  // 当碰到左括号的时候，如果需要的右括号为奇数，那么
                    // 肯定要在加上一个右括号，因为需要有括号的个数一定要是偶数
                    res++;    // 加上一个右括号
                    need--;   // 需要的右括号个数减1
                }
            } else {
                need--;
                if (need == -1) {
                    res++;       // 加上一个左括号
                    need = 1;   // 说明此时还需要一个）
                }
            }
        }
        return res + need;
    }
}
