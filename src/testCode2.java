import java.util.HashSet;
import java.util.Set;

public class testCode2 {

        static Set<String> res = new HashSet<>();

        public static String[] permutation(String s) {
            if (s.equals("") || s.length() == 0) {
                return new String[0];
            }
            int length = s.length();
            boolean[] isVisited = new boolean[length];
            StringBuilder temp = new StringBuilder();
            process(s, isVisited, temp);

            // 将set里面的元素还原成数组
            // String[] ans = new String[res.size()];
            // for (int i = 0; i < res.size(); i++) {
            //     ans[i] = res.get(i);
            // }
            // return ans;
            return res.toArray(new String[0]);
        }

        public static void process(String s, boolean[] isVisited, StringBuilder temp) {
            if (s.length() == temp.length()) {
                res.add(temp.toString());
                return;
            }
            for (int i = 0; i < s.length(); i++) {
                if (isVisited[i]) continue;
                isVisited[i] = true;
                process(s, isVisited, temp.append(s.charAt(i)));
                isVisited[i] = false;
            }
        }

    public static void main(String[] args) {
        String[] res = permutation("abc");
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

}
