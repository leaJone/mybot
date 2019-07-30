package com.boot.lea.mybot.controller;

/**
 * @Title: BaseController.java
 * @Package com.xxmy.boot.neteasy_one.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/4/25 15:16
 * @version v.3.0
 */

import com.boot.lea.mybot.constant.Constant;
import com.boot.lea.mybot.dto.RspDTO;
import com.boot.lea.mybot.dto.UserBehaviorDataDTO;
import com.boot.lea.mybot.dto.UserDTO;
import com.boot.lea.mybot.exception.BizException;
import com.boot.lea.mybot.futrue.MyFutureTask;
import com.boot.lea.mybot.service.UserService;
import com.boot.lea.mybot.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public String index() {
        return "启动用户模块成功~~~~~~~~";
    }

    //http://localhost:8080/api/user/get/data?userId=4

    @GetMapping("/get/data")
    public UserBehaviorDataDTO getUserData(Long userId) {
        System.out.println("UserController的线程:" + Thread.currentThread());
        long begin = System.currentTimeMillis();
        UserBehaviorDataDTO userAggregatedResult = myFutureTask.getUserAggregatedResult(userId);
        long end = System.currentTimeMillis();
        System.out.println("===============总耗时:" + (end - begin) / 1000.0000 + "秒");
        return userAggregatedResult;
    }


    //  http://localhost:8080/api/user/save/userVO

    /**走串行校验
     * @param userVO
     * @return
     */
    @PostMapping("/save/userVO")
    public RspDTO save(@RequestBody UserVO userVO) {
        String mobile = userVO.getMobile();
        //参数校验为空返回参数信息
        if (StringUtils.isBlank(mobile)) {
            return RspDTO.paramFail("mobile:手机号码不能为空");
        }
        //各自抛异常
        if (StringUtils.isBlank(userVO.getUsername())) {
            throw new BizException(Constant.PARAM_FAIL_CODE, "用户名不能为空");
        }
        //.........
        userService.save(userVO);
        return RspDTO.success();
    }

    //  http://localhost:8080/api/user/save/userDTO

    /**
     * 走参数校验注解
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/save/userDTO")
    public RspDTO save(@RequestBody @Validated UserDTO userDTO) {
        userService.save(userDTO);
        return RspDTO.success();
    }


}
