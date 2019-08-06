package com.boot.lea.mybot.service.impl;


import com.boot.lea.mybot.dto.UserDTO;
import com.boot.lea.mybot.entity.User;
import com.boot.lea.mybot.mapper.UserMapper;
import com.boot.lea.mybot.service.SendService;
import com.boot.lea.mybot.service.UserService;
import com.boot.lea.mybot.utils.BeanCopyUtils;
import com.boot.lea.mybot.vo.UserVO;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.concurrent.TimeUnit;
/**
 *如下方式会使@Async失效
 * 一、异步方法使用static修饰
 * 二、异步类没有使用@Component注解（或其他注解）导致spring无法扫描到异步类
 * 三、异步方法不能与异步方法在同一个类中
 * 四、类中需要使用@Autowired或@Resource等注解自动注入，不能自己手动new对象
 * 五、如果使用SpringBoot框架必须在启动类中增加@EnableAsync注解
 * 六、在Async 方法上标注@Transactional是没用的。 在Async 方法调用的方法上标注@Transactional 有效。
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    SendService sendService;

    @Override@Transactional(propagation = Propagation.REQUIRED)
    public int save(UserDTO userDTO) {
        User user = new User();
        BeanCopyUtils.copy(userDTO, user);
        int insert = userMapper.insert(user);
        System.out.println("User 保存用户成功:" + user);
        UserService currentProxy = UserService.class.cast(AopContext.currentProxy());
        currentProxy.senMsg(user);
//        currentProxy.senEmail(user);
//        int i = 1 / 0;
        return insert;
    }
    @Async  @Override  @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void senMsg(User user) {
        user.setUsername(Thread.currentThread().getName()+"发短信测试事务...."+ new Random().nextInt());
        int insert = userMapper.insert(user);
//        int i = 1 / 0;
        UserService currentProxy = UserService.class.cast(AopContext.currentProxy());
        currentProxy.senEmail(user);
        System.out.println(Thread.currentThread().getName() + "给用户id:" + user.getId() + ",手机号:" + user.getMobile() + "发送短信成功");
    }
    @Async @Override @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void senEmail(User user) {
        user.setUsername("发邮件测试事务...."+ new Random().nextInt());
//        int insert = userMapper.insert(user);

        System.out.println(Thread.currentThread().getName() + "给用户id:" + user.getId() + ",邮箱:" + user.getEmail() + "发送邮件成功");
    }

    @Override
    public User selectById(Long userId) {
        return userMapper.selectById(userId);
    }

    @Override
    @Transactional
    public int updateById(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getUserId());
        user.setSex(userDTO.getSex());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return userMapper.updateById(user);
    }


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
            System.out.println("获取FollowCount===睡眠:" + 10 + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userMapper.countFollowCountByUserId(userId);
    }

    @Override
    public long countRedBagCountByUserId(Long userId) {
        System.out.println("UserService获取RedBagCount的线程  " + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(4);
            System.out.println("获取RedBagCount===睡眠:" + 4 + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 99;
    }

    @Override
    public long countCouponCountByUserId(Long userId) {
        System.out.println("UserService获取CouponCount的线程  " + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(8);
            System.out.println("获取CouponCount===睡眠:" + 8 + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 66;
    }

    @Override
    public int save(UserVO userVO) {
        System.out.println("userVO 保存用户成功:" + userVO);
        return 1;
    }


}
