package com.boot.lea.mybot.controller;

/**
 * @Title: BaseController.java
 * @Package com.xxmy.boot.neteasy_one.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/4/25 15:16
 * @version v.3.0
 */

import com.boot.lea.mybot.dto.UserBehaviorDataDTO;
import com.boot.lea.mybot.futrue.MyFutureTask;
import com.boot.lea.mybot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiJing
 * @ClassName: BaseController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/4/25 15:16
 */
@RestController
@RequestMapping("user/")
public class UserController {


    @Autowired
    private UserService userService;


    @Autowired
    private MyFutureTask myFutureTask;


    @GetMapping("/index")
    @ResponseBody
    public String index() {
        return "启动用户模块成功~~~~~~~~";
    }

    //http://localhost:8080/api/user/get/data?userId=4

    @GetMapping("/get/data")
    @ResponseBody
    public UserBehaviorDataDTO getUserData(Long userId) {
        System.out.println("UserController的线程:" + Thread.currentThread());
        long begin = System.currentTimeMillis();
        UserBehaviorDataDTO userAggregatedResult = myFutureTask.getUserAggregatedResult(userId);
        long end = System.currentTimeMillis();
        System.out.println("===============总耗时:" + (end - begin) /1000.0000+ "秒");
        return userAggregatedResult;
    }


}
