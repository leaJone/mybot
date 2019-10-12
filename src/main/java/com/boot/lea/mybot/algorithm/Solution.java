package com.boot.lea.mybot.algorithm;

/**
 * @Title: Solution.java
 * @Package com.boot.lea.mybot.algorithm
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/10/12 16:57
 * @version v.3.0
 */

import lombok.NoArgsConstructor;

import java.util.Random;

/**
 * 给定一个正整数数组 w ，其中 w[i] 代表位置 i 的权重，请写一个函数 pickIndex ，它可以随机地获取位置 i，选取位置 i 的概率与 w[i] 成正比。
 * 说明:
 * 1 <= w.length <= 10000
 * 1 <= w[i] <= 10^5
 * pickIndex 将被调用不超过 10000 次
 * 示例1:
 * 输入:
 * [“Solution”,“pickIndex”]
 * [[[1]],[]]
 * 输出: [null,0]
 * 示例2:
 * 输入:
 * [“Solution”,“pickIndex”,“pickIndex”,“pickIndex”,“pickIndex”,“pickIndex”]
 * [[[1,3]],[],[],[],[],[]]
 * 输出: [null,0,1,1,1,0]
 * 输入语法说明：
 * 输入是两个列表：调用成员函数名和调用的参数。Solution 的构造函数有一个参数，即数组 w。pickIndex 没有参数。输入参数是一个列表，即使参数为空，也会输入一个 [] 空列表。
 * <p>
 * <p>
 * 2. 题目分析
 * 根据权重选择来获取下标，其实这题跟我之前拼多多一轮面试的题目，根据权重负载均衡到某一台魂村服务器上的意思是一样的，只是不要求平滑的均衡分布。
 * <p>
 * 3. 解题思路
 * 可以将概率转换成几何问题，即将权重用线段长度表示，或者说用一个区间表示一个权重，然后在整个集合范围内随机生成一个数，查看这个数在哪个区间，那么就返回这个区间对应的数字即可。
 * 如，权重：[1,2,3,4]，则
 * 可以用区间（左开右闭区间）：	[1,2), [2, 4), [4,7), [7, 11)
 * 那么我们可以在[1-10]之间生成随机数，然后看随机数在哪个区间，比如说，随机数为6，则在区间 [4,7)，那么返回区间所在的下标3。
 * 那么问题来了，我们怎么找到随机数所在的区间呢？顺序遍历肯定是OK的，时间复杂度为O(n)，但有没有更高效率的算法呢？我们发现区建设hi有序的，有序的查找值，最常见的算法就是二分查找了，yes，就是优化后的思路，就是通过二分法来查找所在的区间。
 * ————————————————
 * 版权声明：本文为CSDN博主「凌凌小博客」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_35923749/article/details/89214611
 */
@NoArgsConstructor
class Solution {

    private int[] sum;//前n为权重的和
    private Random random;//随机生成器

    public Solution(int[] w) {
        random = new Random();
        sum = new int[w.length];

        sum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            sum[i] = sum[i - 1] + w[i];
        }

    }

    //利用二分法找到当前随机数所在的区间,左闭右开
    //按权重随机选择的实现方法就是，根据权重划分区域，然后生成随机数，找到随机数才在的区间
    public int pickIndex() {
        int value = random.nextInt(sum[sum.length - 1]) + 1;//从0-sum[sum.length-1]+1随机生成一个随机数
        int left = 0;
        int right = sum.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (sum[mid] == value) {
                return mid;
            } else if (sum[mid] > value) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    public static void main(String[] args) {

        int[] w={2,3,4,6};
        final Solution solution = new Solution(w);
        final int index = solution.pickIndex();
        System.out.println(index);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */

