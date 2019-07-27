package com.boot.lea.mybot.dto;

/**
 * @Title: RspDTO.java
 * @Package com.xxmy.boot.neteasy_one.dto
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/4/25 16:10
 * @version v.3.0
 */

import lombok.Data;

import java.io.Serializable;

/**
 * @author LiJing
 * @ClassName: RspDTO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/4/25 16:10
 */
@Data
public class RspDTO<T> implements Serializable {

    private T data;
    private Integer code;
    private String msg;

}
