/**
 * @Title: BaseEntity.java
 * @Package com.cn.alasga.common.core.base
 * @Description: 基础实体
 * @author wanghj
 * @date 2018年3月22日 下午4:23:39
 * @version V1.0
 */
package com.boot.lea.mybot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wanghj
 * @ClassName: BaseEntity
 * @Description: 基础实体
 * @date 2018年3月22日 下午4:23:39
 */

@Setter
@Getter
@ToString(callSuper = true)
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("dataID,数据id,数据唯一凭证id")
    protected Long id;

    /**
     * 创建日期
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间,操作时间,格式化:yyyy-MM-dd HH:mm:ss,timezone =GMT+8")
    protected Date createDate;

    /**
     * 更新日期
     */
    @ApiModelProperty("更新时间,操作时间,格式化:yyyy-MM-dd HH:mm:ss,timezone =GMT+8")
    protected Date updateDate;


}
