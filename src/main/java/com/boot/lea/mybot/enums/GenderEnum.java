package com.boot.lea.mybot.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LiJing
 * @ClassName: GenderEnum
 * @Description: 性别枚举
 * @date 2019/5/21 16:48
 */
@Getter
@AllArgsConstructor
public enum GenderEnum {

    /**
     * 预约人 性别:0 未知保密, 1 先生 2 女士
     */
    GENDER_SECRECY(0, "未知|保密"),
    GENDER_MALE(1, "先生,男性"),
    GENDER_FEMALE(2, "女士,女性"),;

    private Integer code;
    private String msg;
}
