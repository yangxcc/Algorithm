package 错题集;


import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString {
    // 这种方法只能够解决一层嵌套
    public static String decodeString(String s) {
        // 使用两个栈，一个是数字栈，一个是字母栈（包含了这个[）
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<String> strStack = new ArrayDeque<>();

        // 策略是：遇到数字进入数字栈，遇到左括号和字母进入字母栈
        // 当遇到]的时候，数字栈出栈一个数，字母栈一直出栈到[，而且还要把这个数组倒过来
        StringBuilder res = new StringBuilder();
        int cur = 0;
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; ) {
            if (Character.isDigit(chs[i])) {
                // 是数字，后面可能还有
                int num = 0;
                while (Character.isDigit(chs[i])) {
                    num = num * 10 + chs[i] - '0';
                    i++;
                }
                numStack.push(num);
                System.out.println(numStack.peek());
            } else if (Character.isLetter(chs[i]) || chs[i] == '[') {
                StringBuilder temp = new StringBuilder();
                while (Character.isLetter(chs[i]) || chs[i] == '[') {
                    temp.append(chs[i]);
                    i++;
                }
                strStack.push(temp.toString());
                System.out.println(strStack.peek());
            } else {
                 i++;  // 先把这个]越过去
                // 数字栈出战一次
                int repeat = numStack.poll();
                // 字符栈也出战一次就行了，因为上面对他进行循环了
                String str = strStack.poll().substring(1);  // 最开头一定是一个[
                StringBuilder temp = new StringBuilder();
                while (repeat > 0) {
                    temp.append(str);
                    repeat--;
                }
                res.append(temp);
//                System.out.println(res);
            }
        }

        return res.toString();
    }

    // 策略如下：
    //     当c为数字的时候，继续向下遍历，因为他有可能是多位数字，记作num
    //     当c为字母的时候，继续向下遍历，把这个字符串连起来，记作str
    //     当c为[的时候，把上面的num和str都入栈，入栈之后一定要把num置为0，str置为空!!!!!!
    //     当c为]的时候，拼接字符串 res = last_res + num * res，其中:
    //            last_res是上一个[到当前[的字符串
    //            num是当前[和]之间的字符串的重复次数
    public static String decodeString2(String s) {
        StringBuilder res = new StringBuilder();
        int num = 0;
        Deque<Integer> numStack = new ArrayDeque<>();
        // 这一个的字母栈中不包括[，和上面这种解法不同了
        Deque<String> strStack = new ArrayDeque<>();

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                // ch是数字
                num = num * 10 + ch - '0';
            } else if (Character.isLetter(ch)) {
                // 是字母
//                StringBuilder str = new StringBuilder();  // 这样写局部变量，在下面没法加入到栈中
                res.append(ch);
            } else if (ch == '[') {
                // 是左括号，把num和str入栈，然后置空
                numStack.addLast(num);
                num = 0;
                strStack.addLast(res.toString());   // 可以看出，res是两个相邻左括号之间的字符串
                res = new StringBuilder();

            } else {
                // 是右括号
                int repeat = numStack.removeLast();
                StringBuilder temp = new StringBuilder();

                while (repeat > 0) {
                    temp.append(res);
                    repeat--;
                }
                res = new StringBuilder(strStack.removeLast() + temp);  // 这里也是res，把连接后的值给了res
                // 所以res在这里始终也能代表括号里面的数
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "3[a2[bc]b]";
        System.out.println(decodeString2(s));
    }
}
