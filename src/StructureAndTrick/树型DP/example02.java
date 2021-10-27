package StructureAndTrick.树型DP;

import binaryTree.Node;

import java.util.List;

/**
 * party的最大快乐值
 *
 * 公司中每个员工的描述都符合Employee，整个公司的人员结构可以看成一棵标准的，没有环的多叉树，树的头节点是公司的唯一老板
 * 除了老板以外的每个员工都有唯一的上级，叶节点是没有任何下属的基层员工，即subordinates列表为空，每个员工都有一个或者多个下级
 *
 * 现在公司准备开party，老板决定哪些员工可以来，哪些不来，但是要遵守以下规则：
 *    - 如果某个员工来了，那么这个员工的直接下属就不能来了
 *    - party的整体快乐值是来的员工的累加和
 *    - 目标是让party的快乐值最大
 *
 *
 * 解决方法
 *   对于一个员工来说，他可以来，也可以不来，于是就分成了两种大情况
 *     - 员工x来，它的下属肯定是不能来了
 *        此时的maxHappy是 员工x的happy值 + 下属1不来的maxHappy值（因为下属1的下属可以来） + ...  + 下属n不来的maxHappy值
 *     - 员工x不来，那么它的下属可以选择来也可以选择不来
 *         此时的maxHappy是 0 + Math.max{下属1来时的maxHappy，下属1不来时的maxHappy} + ... + Math.max{下属n来时的maxHappy，下属n不来时的maxHappy}
 *  所以， Info需要包含两个信息，来时的maxHappy，不来时的maxHappy
 *
 * */
public class example02 {

    public static class Info {
        int laiMaxHappy;
        int buMaxHappy;
        public Info(int lai, int bu) {
            this.laiMaxHappy = lai;
            this.buMaxHappy = bu;
        }
    }

    public static Info process(Employee x) {
        if (x.subordinates.isEmpty()) {   // x是基层员工
            return new Info(x.happy, 0);
        }
        int laiMaxHappy = x.happy;
        int buMaxHappy = 0;
        for (Employee employee : x.subordinates) {
            Info info = process(employee);
            laiMaxHappy += info.buMaxHappy;
            buMaxHappy += Math.max(info.laiMaxHappy, info.buMaxHappy);
        }
        return new Info(laiMaxHappy, buMaxHappy);
    }

    public static int maxHappy(Employee boss) {
        if (boss == null) {
            return -1;
        }
        Info info = process(boss);
        return Math.max(info.laiMaxHappy, info.buMaxHappy);
    }
}

class Employee {
    int happy;  // 快乐值
    List<Employee> subordinates; // 下级
}
