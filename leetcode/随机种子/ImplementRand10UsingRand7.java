package 随机种子;

/**
 * 使用rand7实现rand10
 * <p>
 * 这种随机种子的问题能够总结出一套公式来
 */
public class ImplementRand10UsingRand7 {
    public int rand10() {
        int res = Integer.MAX_VALUE;
        while (res > 40) {
            res = (rand7() - 1) * 7 + rand7();
        }
        return res % 10 + 1;
    }

    // 假装是随机生成1-7的数，为了不让idea报错
    public int rand7() {
        return 0;
    }
}

/**
 * 分析，rand7能够等概率地随机生成1-7之间的数
 * 如果rand7() - 1，那么将等概率地生成0-6之间的数
 * 这时候如果(rand7()-1) * 7，他就会等概率地生成0，7，14，21，28，35，42，49
 * 令(rand7()-1) * 7 + rand7()则会等概率的生成 1，2，3...49
 * 事实上，这时候，我们就可以while(x > 8) reRand，但是这样舍弃的数太多了，所以我们要提高门槛
 * while (x > 40) reRand
 * 最后返回 res % 10 + 1
 * <p>
 * 这个40是怎么确定的呢？  40是最接近49，且是10的倍数
 */

class ImplementRand5UsingRand7 {
    // 同理，使用rand5生成rand7
    public int rand7() {
        int res = Integer.MAX_VALUE;
        while (res > 21) {
            res = (rand5() - 1) * 5 + rand5();
        }
        return res % 7 + 1;
    }

    // 假装是rand5
    public int rand5(){return 0;}
}

/**
 *
 * 推广一下，如果给了两个随机函数 randA和randB，分别生成1-a，1-b之间的随机数，a、b不相等，如果相等的话，就是同一个rand函数了
 * 现在让你使用randA生成randB
 *   - 如果 a 大于 b，也就是让rand12生成rand5，那么直接就可以写while (x > 5) reRand,但是为了不放弃这么多数据，我们可以找到一个
 *     数据，他是最接近12，而且是5的倍数的数，即10  --->  while(x > 10) reRand
 *
 *   - 如果 a 小于 b，也就是通过rand5实现rand12，这时候我们可以直接带入一个公式 (randA - 1) * A + randA，表示生成1-A^2之间的随机数
 *      然后这时候再找一个最接近A^2，而且是B的倍数的数。最后返回 x % B + 1   ---> while(x > 24) reRand   return x % 12 + 1
 *
 * 继续推广一下，通过上面的思路，我们能够得出，如果给了两个随机函数 randA和randB，分别生成1-a，1-b之间的随机数，那么也就能够
 * 得到一个随机函数randAB，等概率随机生成1-a*b之间的数
 * 公式为 randAB = b * (randA - 1) + randB 或者 randAB = a * (randB - 1) + randA
 *
 *
 * 最后在拓展一下  给你一个随机生成a到b的函数randAB， 用它去实现一个随机生成c到d的函数randCD。
 * 默认a < b,c < d
 * 这里直接给出公式  randCD = (randAB() - a) / (b - a) * (d - c) + c
 *
 * (randAB() - a) / (b - a)的目的是变成[0,1]的数
 *
 * 参考链接
 *  https://blog.csdn.net/u010025211/article/details/49668017
 *  https://www.zhihu.com/question/411084958
 * */