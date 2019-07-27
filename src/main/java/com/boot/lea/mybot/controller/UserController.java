package com.boot.lea.mybot.controller;

/**
 * @Title: BaseController.java
 * @Package com.xxmy.boot.neteasy_one.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/4/25 15:16
 * @version v.3.0
 */

import com.boot.lea.mybot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        return "启动用户模块成功~~~~~~~~";
    }

}
