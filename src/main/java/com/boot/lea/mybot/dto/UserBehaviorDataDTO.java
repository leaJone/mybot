package com.boot.lea.mybot.dto;

import lombok.*;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserBehaviorDataDTO implements Serializable {

    /**粉丝数量*/
    private Long fansCount;

    /**消息数量*/
    private Long msgCount;

    /**收藏数量*/
    private Long collectCount;

    /**关注数量*/
    private Long followCount;

    /**红包数量*/
    private Long redBagCount;

    /**卡券数量*/
    private Long couponCount;
}
