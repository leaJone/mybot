package com.boot.lea.mybot.zk;

/**
 * @Title: ZookeeperLock.java
 * @Package com.boot.lea.mybot.zk
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/10/21 15:22
 * @version v.3.0
 * @ClassName: ZookeeperLock
 * @Description: 使用Zookeeper原生API实现分布式锁
 * @author LiJing
 * @date 2019/10/21 15:22
 */


import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * @author LiJing
 * @ClassName: ZookeeperLock
 * @Description: 使用Zookeeper原生API实现分布式锁
 * @date 2019/10/21 15:22
 */
public class ZookeeperLock implements Watcher {

    // 声明zk对象
    private ZooKeeper zk = null;
    // 此demo使用的集群，所以有多个ip和端口
    private static String CONNECT_SERVER = "192.168.242.130:2181,192.168.242.130:2182,192.168.242.130:2183";
    // session过期时间
    private static int SESSION_TIMEOUT = 3000;
    // 根节点
    private String root = "/locks";
    // 当前等待的节点
    private String waitNode;
    // 等待的时间
    private int waitTime;
    // 锁节点
    private String myzkNode;
    // 计数器
    private CountDownLatch latch;

    /**
     * 构造函数 初始化
     */
    public ZookeeperLock() {
        try {
            zk = new ZooKeeper(CONNECT_SERVER, SESSION_TIMEOUT, this);
            // 判断是否存在根节点，不需要监听根节点
            Stat stat = zk.exists(root, false);
            // 为空，说明不存在
            if (stat == null) {
                // 添加一个永久节点，即添加一个根节点
                zk.create(root, new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 尝试获取锁
     *
     * @return
     */
    private boolean tryLock() {
        String splitStr = "lock_";  // 格式 lock_000000001
        try {
            // 创建一个临时有序节点，并赋值给 myzkNode
            myzkNode = zk.create(root + "/" + splitStr, new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            // 获取根节点下的所有子节点
            List<String> children = zk.getChildren(root, false);
            // 对子节点 排序
            Collections.sort(children);
            // 如果刚刚创建的节点 等于  获取最小的一个子节点，则说明 获取到锁
            if (myzkNode.equals(root + "/" + children.get(0))) {
                return true;
            }
            // 如果刚刚创建的节点 不等于 获取到的最小的一个子节点，则 监控比自己小的一个节点
            // 获取刚刚建立的子节点（不包含根节点的子节点）
            String childNode = myzkNode.substring(myzkNode.lastIndexOf("/") + 1);
            // 获取比自己小的节点
            waitNode = children.get(Collections.binarySearch(children, childNode) - 1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 等待锁释放
     *
     * @param waitNode
     * @param waidTime
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    private boolean waitLock(String waitNode, int waidTime) throws KeeperException, InterruptedException {
        // 判断比自己小的节点是否存在，并添加监听
        Stat stat = zk.exists(root + "/" + waitNode, true);
        // 如果存在 比自己小的节点
        if (stat != null) {
            this.latch = new CountDownLatch(1);
            this.latch.await(waidTime, TimeUnit.MILLISECONDS);
            this.latch = null;
        }
        return true;
    }

    /**
     * 获取锁
     */
    public void lock() {
        // 如果尝试获取锁成功
        if (tryLock()) {
            // 获取 成功
            System.out.println("Thread" + Thread.currentThread().getName() + " -- hold lock!");
            return;
        }
        // 等待并获取锁
        try {
            waitLock(waitNode, waitTime);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解锁
     */
    public void unLock() {
        try {
            zk.delete(myzkNode, -1);
            zk.close();
            System.out.println("Thread" + Thread.currentThread().getName() + " unlock success! 锁节点：" + myzkNode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除的时候 触发事件
     */
    @Override
    public void process(WatchedEvent event) {
        // 如果计数器不为空的话，释放计数器锁
        if (this.latch != null) {
            this.latch.countDown();
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
        // 只能10个线程同时运行，即模拟并发数为10
        final Semaphore semaphore = new Semaphore(10);
        // 10个客户端连接
        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        ZookeeperLock zkLock = new ZookeeperLock();
                        zkLock.lock();
                        // 业务操作代码
                        Thread.sleep(3000);
                        zkLock.unLock();
                        semaphore.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }
        service.shutdown();
    }
}
