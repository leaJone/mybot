package com.boot.lea.mybot.test;

import org.springframework.beans.factory.annotation.Value;

import java.util.StringJoiner;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {

    private static int SIZE;

    @Value("ai.threshold") //这里ai.threshold=10读取配置
    public void setSize(int threshold) {
        Test.SIZE = threshold;
    }


    private static LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(SIZE);

    public static void main(String[] args) {
        //AOP生产者
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                try {
                    queue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //AOP生产者
        Thread thread3 = new Thread(() -> {
            for (int i = 200; i <= 300; i++) {
                try {
                    queue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //消费者
        Thread thread2 = new Thread(() -> {
            StringJoiner joiner;
            while (true) {
                if (queue.size() >= SIZE) {
                    joiner = new StringJoiner(":");
                    for (Integer x : queue) {
                        joiner.add(x.toString());
                    }
                    System.out.println(joiner);
                    queue.clear();
                }
            }
        });
        thread1.start();
//        thread3.start();
        thread2.start();
    }


}
