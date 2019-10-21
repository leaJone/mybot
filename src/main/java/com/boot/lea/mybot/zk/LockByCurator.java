package com.boot.lea.mybot.zk;

/**
 * @Title: LockByCurator.java
 * @Package com.boot.lea.mybot.zk
 * @Description: 使用Curator来实现分布式锁
 * @author LiJing
 * @date 2019/10/21 15:20
 * @version v.3.0
 */


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

/**
 * @author LiJing
 * @ClassName: LockByCurator
 * @Description: 使用Curator来实现分布式锁
 * @date 2019/10/21 15:20
 */
public class LockByCurator {

    // 此demo使用的集群，所以有多个ip和端口
    private static String CONNECT_SERVER = "192.168.242.129:2181,192.168.242.129:2182,192.168.242.129:2183";
    // session过期时间
    private static int SESSION_TIMEOUT = 3000;
    // 连接超时时间
    private static int CONNECTION_TIMEOUT = 3000;

    // 锁节点
    private static final String CURATOR_LOCK = "/curatorLock";

    /**
     * 获取锁操作
     *
     * @param cf
     */
    public static void doLock(CuratorFramework cf) {
        System.out.println(Thread.currentThread().getName() + " 尝试获取锁！");
        // 实例化 zk分布式锁
        InterProcessMutex mutex = new InterProcessMutex(cf, CURATOR_LOCK);
        try {
            // 判断是否获取到了zk分布式锁
            if (mutex.acquire(5, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " 获取到了锁！-------");
                // 业务操作
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放锁
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        // 定义线程池
        ExecutorService service = Executors.newCachedThreadPool();
        // 定义信号灯，只能允许10个线程并发操作
        final Semaphore semaphore = new Semaphore(10);
        // 模拟10个客户端
        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        // 连接 ZooKeeper
                        CuratorFramework framework = CuratorFrameworkFactory.
                                newClient(CONNECT_SERVER, SESSION_TIMEOUT, CONNECTION_TIMEOUT, new RetryNTimes(10, 5000));
                        // 启动
                        framework.start();
                        doLock(framework);

                        semaphore.release();
                    } catch (Exception e) {

                    }
                }
            };
            service.execute(runnable);
        }
        service.shutdown();
    }
}
