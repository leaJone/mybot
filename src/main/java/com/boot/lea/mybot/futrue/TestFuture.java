package com.boot.lea.mybot.futrue;

/**
 * @ClassName: TestFuture
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiJing
 * @date 2019/8/5 15:16
 */

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("all")
public class TestFuture {
    static ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException {
        //两个线程的线程池
        //小红买酒任务，这里的future2代表的是小红未来发生的操作，返回小红买东西这个操作的结果
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("爸：小红你去买瓶酒！");
            try {
                System.out.println("小红出去买酒了，女孩子跑的比较慢，估计5s后才会回来...");
                Thread.sleep(5000);
                return "我买回来了！";
            } catch (InterruptedException e) {
                System.err.println("小红路上遭遇了不测");
                return "来世再见！";
            }
        }, executor);

        //小明买烟任务，这里的future1代表的是小明未来买东西会发生的事，返回值是小明买东西的结果
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("爸：小明你去买包烟！");
            try {
                System.out.println("小明出去买烟了，可能要3s后回来...");
                Thread.sleep(3000);

                throw new InterruptedException();
//                return "我买回来了!";
            } catch (InterruptedException e) {
                System.out.println("小明路上遭遇了不测！");
                return "这是我托人带来的口信，我已经不在了。";
            }
        }, executor);

        //获取小红买酒结果，从小红的操作中获取结果，把结果打印
        future2.thenAccept((e) -> {
            System.out.println("小红说：" + e);
        });
        //获取小明买烟的结果
        future1.thenAccept((e) -> {
            System.out.println("小明说：" + e);
        });

        System.out.println("爸：等啊等 西湖美景三月天嘞......");
        System.out.println("爸: 我觉得无聊甚至去了趟厕所。");
        Thread.currentThread().join(9 * 1000);
        System.out.println("爸：终于给老子买来了......huo 酒");
        //关闭线程池
        executor.shutdown();
    }
}
