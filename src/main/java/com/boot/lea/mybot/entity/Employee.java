package com.boot.lea.mybot.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LiJing
 * @ClassName: Employee
 * @Description: 员工表
 * @date 2019/8/1 15:37
 */
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = -9097897857581455509L;

    private Integer id;
    private String lastName;
    private Integer gender;
    private String email;
    private Integer dId;

}
