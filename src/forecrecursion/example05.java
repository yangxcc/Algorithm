package forecrecursion;

import java.util.Stack;

/**
 * 逆序实现栈，不使用额外空间
 *
 * 如果使用额外空间的话，直接在来一个栈就行，一个出栈，一个入栈
 * */
public class example05 {

    public static void reverse(Stack<Integer> stack) {
        // 递归，用一个数来一直保存栈最底端的那个数，然后再一层层地压进去
        if (stack.isEmpty()) {
            return;
        }
        int last = findLast(stack);
        reverse(stack);
        stack.push(last);
    }

    // 找到栈中最后的一个元素
    public static int findLast(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = findLast(stack);   // 递归了，所以每一层的result都不一样
            stack.push(result);
            return last;
        }
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 5; i++) {
            stack.add(i);
        }

        int length = stack.size();

//        for (int i = 0; i < length; i++) {
//            System.out.println(stack.pop());
//        }
//        int f = findLast(stack);
//        System.out.println(f);
        reverse(stack);


        for (int i = 0; i < length; i++) {
            System.out.println(stack.pop());
        }
    }
}
