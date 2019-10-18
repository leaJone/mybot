package com.boot.lea.mybot.test;


/**
 * @ClassName: ConcurrentStreamTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiJing
 * @date 2019/10/17 14:04
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConcurrentStreamTest {

    private static int size = 1000_0000;

    public static void main(String[] args) {
        System.out.println("------------------set-------------------");
        testSet();
        System.out.println("------------------list-------------------");
        testList();
//        System.out.println("------------------set-------------------");
//        peekTest();
    }

    public static void testSet() {
        List<Integer> list = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        Set<Integer> temp = new HashSet<>(size*2);
        long startTime = System.currentTimeMillis();
        for (Integer num : list) {
            temp.add(num);
        }
        System.out.println("添加元素响应时间：" + (System.currentTimeMillis() - startTime));

        //同步
        long startTime1 = System.currentTimeMillis();
        list.stream().collect(Collectors.toSet());
        System.out.println("串行流响应时间:" + (System.currentTimeMillis() -
                startTime1));

        //并发 不一定快
        long startTime2 = System.currentTimeMillis();
        list.parallelStream().collect(Collectors.toSet());
        System.out.println("并行流响应时间:" + (System.currentTimeMillis() -
                startTime2));

    }

    public static void testList() {
        List<Integer> list = new ArrayList(size*2);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        List<Integer> temp = new ArrayList(size);
        long startTime = System.currentTimeMillis();
        for (Integer num : list) {
            temp.add(num);
        }
        System.out.println("添加元素响应时间：" + (System.currentTimeMillis() - startTime));
        //同步
        long startTime1 = System.currentTimeMillis();
        list.stream().collect(Collectors.toList());
        System.out.println("串行流响应时间:" + (System.currentTimeMillis() - startTime1));

        //并行 不一定快
        long startTime2 = System.currentTimeMillis();
        list.parallelStream().collect(Collectors.toList());
        System.out.println("并行流响应时间:" + (System.currentTimeMillis() - startTime2));
    }


    public static void mu(Function function) {

    }
}