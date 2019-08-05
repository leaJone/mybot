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
import com.boot.lea.mybot.dto.assist.Update;
import com.boot.lea.mybot.entity.User;
import com.boot.lea.mybot.exception.BizException;
import com.boot.lea.mybot.futrue.MyFutureTask;
import com.boot.lea.mybot.service.UserService;
import com.boot.lea.mybot.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author LiJing
 * @ClassName: UserController
 * @Description: 用户控制器
 * @date 2019/4/25 15:16
 */
@RestController
@RequestMapping("user/")
//@Validated
public class UserController extends AbstractController {


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

    @GetMapping("/get")
    public RspDTO getUser(Long userId) {
        User user = userService.selectById(userId);
        if (user == null) {
            return new RspDTO<User>().nonAbsent("用户不存在");
        }
        return new RspDTO<User>().success(user);
    }


    //  http://localhost:8080/api/user/save/serial

    /**
     * 走串行校验的疼痛路线
     *
     * @param userVO
     * @return
     */
    @PostMapping("/save/serial")
    public Object save(@RequestBody UserVO userVO) {
        String mobile = userVO.getMobile();

        //手动逐个 参数校验~ 写法
        if (StringUtils.isBlank(mobile)) {
            return RspDTO.paramFail("mobile:手机号码不能为空");
        } else if (!Pattern.matches("^[1][3,4,5,6,7,8,9][0-9]{9}$", mobile)) {
            return RspDTO.paramFail("mobile:手机号码格式不对");
        }

        //抛出自定义异常等~写法
        if (StringUtils.isBlank(userVO.getUsername())) {
            throw new BizException(Constant.PARAM_FAIL_CODE, "用户名不能为空");
        }

        // 比如写一个map返回 ~ 写法
        if (StringUtils.isBlank(userVO.getSex())) {
            Map<String, Object> result = new HashMap<>(5);
            result.put("code", Constant.PARAM_FAIL_CODE);
            result.put("msg", "性别异常");
            return result;
        }

        //.........各种写法 ... 还有业务代码..

        userService.save(userVO);
        return RspDTO.success();
    }

    /**
     * 走参数校验注解的性感路线
     *
     * @param username
     * @return
     */
    @PostMapping("/save/rest")
    public RspDTO save(@NotBlank(message = "username不能为空") String username, @NotBlank(message = "sex不能为空") String sex) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setSex(sex);
        userService.save(userDTO);
        return RspDTO.success();
    }


    //  http://localhost:8080/api/user/save/valid

    /**
     * 走参数校验注解的性感路线
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/save/valid")
    public RspDTO save(@RequestBody @Validated UserDTO userDTO) {
        userService.save(userDTO);
        return RspDTO.success();
    }

    /**
     * 走参数校验注解的 groups 组合校验
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/update/groups")
    public RspDTO update(@RequestBody @Validated(Update.class) UserDTO userDTO) {
        int i = userService.updateById(userDTO);
        System.out.println("影响行数:"+i);
        if (i > 0) {
            return RspDTO.success();
        }
        return RspDTO.failed();
    }


}
