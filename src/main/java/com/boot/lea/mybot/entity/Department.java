package com.boot.lea.mybot.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Department
 * @Description: 部门表
 * @author LiJing
 * @date 2019/8/1 15:38 
 *
 */
@Data
public class Department implements Serializable {

    private static final long serialVersionUID = 2820028198409418108L;

    private Integer id;
    private String departmentName;
}
