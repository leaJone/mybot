package com.boot.lea.mybot;

import com.boot.lea.mybot.dto.UserDTO;
import com.boot.lea.mybot.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MybotUserTest extends MybotApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void updateMatches() {
        UserDTO user = new UserDTO();
        user.setUserId(88L);
        user.setUsername("程序员DD");
        user.setSex("男");
        int i = userService.updateById(user);
        System.out.println("影响行数:" + i);
    }

}
