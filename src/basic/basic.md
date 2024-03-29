### 时间复杂度
在算法中的时间复杂度一般将的是**最差情况下**的时间复杂度
### Master公式
数据状况不同会导致算法时间复杂度不同，比如插入排序，快速排序等
这时候估计算法的时间复杂度就需要使用Master公式了
`T(N)=a*T(N/b)+O(N^d)`

T(N)表示的是整个问题的规模

a*T(N/b)表示的是有a个相同规模的子问题，子问题的规模是N/b 
除了子问题的调用在外，其他过程的时间复杂度是O(N^d)

**Master公式能够估计递归算法的时间复杂度**

- log(b, a) > d ：时间复杂度为O(N^log(b,a))
- log(b, a) = d ：时间复杂度为O(N^d * logN)
- log(b, a) < d ：时间复杂度为O(N^d)


### 对数器的概念和使用
目前存在一个想要测试的方法a和一个复杂度不好但是容易实现的方法b，通过实现一个随机样本产生器，令方法a和方法b跑同样的一个样本集，看看得到的结果是否一致
，如果一个样本的结果不一致，那么就要进行人工干预，去修改方法a或者方法b，当样本数量很多比对测试依然正确的时候，可以确定方法a已经正确

**比如：我可以让冒牌排序和归并排序同时跑相同的50w个随机数组，如果两者结果一致，那么证明两个方法都对了，如果不一致，则证明至少错了一个**

### 比较器
告诉两个东西怎么比较大小，需要继承Comparator类
```java
import java.util.Comparator;
public class AgeAscendingComparator implements Comparator<Student> {
    /**
     * 自定义规则来比较数据大小
     * 返回负数时，第一个数排在前面
     * 返回正数时，第二个数排在前面
     * 返回0时，谁排在前面无所谓
     */

    @Override
    public int compare(Student o1, Student o2) {
        return o1.age - o2.age;
    }
}
```