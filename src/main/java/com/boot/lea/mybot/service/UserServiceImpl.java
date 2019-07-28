package com.boot.lea.mybot.service;


import com.boot.lea.mybot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public long countFansCountByUserId(Long userId) {
        try {
            Thread.sleep(10000);
            System.out.println("获取FansCount===睡眠:" + 10 + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("UserService获取FansCount的线程  " + Thread.currentThread().getName());
        return 520;
    }

    @Override
    public long countMsgCountByUserId(Long userId) {
        System.out.println("UserService获取MsgCount的线程  " + Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
            System.out.println("获取MsgCount===睡眠:" + 10 + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 618;
    }

    @Override
    public long countCollectCountByUserId(Long userId) {
        System.out.println("UserService获取CollectCount的线程  " + Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
            System.out.println("获取CollectCount==睡眠:" + 10 + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 6664;
    }

    @Override
    public long countFollowCountByUserId(Long userId) {
        System.out.println("UserService获取FollowCount的线程  " + Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
            System.out.println("获取FollowCount===睡眠:" + 10+ "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userMapper.countFollowCountByUserId(userId);
    }

    @Override
    public long countRedBagCountByUserId(Long userId) {
        System.out.println("UserService获取RedBagCount的线程  " + Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
            System.out.println("获取RedBagCount===睡眠:" + 10 + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 99;
    }

    @Override
    public long countCouponCountByUserId(Long userId) {
        System.out.println("UserService获取CouponCount的线程  " + Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
            System.out.println("获取CouponCount===睡眠:" + 10+ "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 66;
    }
}
