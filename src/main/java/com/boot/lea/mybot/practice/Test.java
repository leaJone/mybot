package com.boot.lea.mybot.practice;

/**
 * @Title: Test.java
 * @Package com.boot.lea.mybot.practice
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/9/12 10:33
 * @version v.3.0
 */


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author LiJing
 * @ClassName: Test
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/9/12 10:33
 */
public class Test {

    /**
     * 在编写多线程代码时，我们时常会用到线程池，以提高效率。那么线程池用完需不需要关闭呢？
     *
     * 如果是局部变量（即一次性使用），用完是要关闭的，否者大量的线程资源占用，会导致内存泄漏。
     *
     * 如果不想手动关闭，可以把线程池里的线程设为守护线程：
     *
     * thread.setDaemon(true);
     *
     * 在这里之前还遇到过一个坑，我在项目测试时发现一个功能请求一次后，下一次请求必然会超时。
     * 后来想到是线程池的问题。因为我的线程池是全局的，其实不用关闭的，但是我在使用完后用了shutdown。
     * 但是线程池里的线程又设为了守护线程，也就是说要等到jvm关闭，线程池才会关闭掉，所以后面调用时就一直超时。
     * */
    private final static Executor executor1 = Executors.newCachedThreadPool();

    private static ExecutorService executor2 = new ThreadPoolExecutor(15, 70,
            0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1),
            new ThreadFactoryBuilder().setNameFormat("User_Async_FutureTask-%d").setDaemon(true).build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    private static ExecutorService executor3 = new ThreadPoolExecutor(15, 70,
            0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1),
            new ThreadFactoryBuilder().setNameFormat("User_Async_FutureTask-%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    private static ExecutorService executor4 = new ThreadPoolExecutor(100, 120,
            20L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(20),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws Exception {
        ExecutorService executor4 = new ThreadPoolExecutor(100, 120,
                20L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(20),
                new ThreadFactoryBuilder().setNameFormat("User_Async_FutureTask-%d").setDaemon(true).build(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        System.out.println("开启main方法");
        for (int i = 1; i <= 10; i++) {
            int x = i;
            executor4.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "===:executor:" + x);
            });
            System.out.println("任务结束==========:" + i);
        }
    }
}
