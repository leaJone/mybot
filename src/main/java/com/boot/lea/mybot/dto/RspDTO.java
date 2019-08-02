package com.boot.lea.mybot.dto;

/**
 * @Title: RspDTO.java
 * @Package com.xxmy.boot.neteasy_one.dto
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/4/25 16:10
 * @version v.3.0
 */

import com.boot.lea.mybot.constant.Constant;
import lombok.Data;
import org.apache.http.HttpStatus;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * @author LiJing
 * @ClassName: RspDTO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/7/29 16:10
 */
@Data
public class RspDTO<T> implements Serializable {

    private static int PARAM_FAIL_CODE = 1002;


    private T data;
    private Integer code;
    private String msg;

    private static final long serialVersionUID = 1L;

    public RspDTO() {
    }

    public RspDTO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RspDTO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static RspDTO error() {
        return new RspDTO(HttpStatus.SC_INTERNAL_SERVER_ERROR, "系统繁忙,请稍后再试");
    }


    public static RspDTO paramFail(String msg) {
        return new RspDTO(PARAM_FAIL_CODE, msg);
    }


    public static RspDTO success() {
        return new RspDTO(HttpStatus.SC_OK, "success");
    }

    public static RspDTO failed() {
        return new RspDTO(HttpStatus.SC_INTERNAL_SERVER_ERROR, "failed");
    }

    public RspDTO<T> success(T data) {
        return new RspDTO<>(HttpStatus.SC_OK, "success", data);
    }

    public RspDTO nonAbsent(String msg) {
        return new RspDTO<>(Constant.NON_ABSENT, msg);
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}[{1}]", this.code, this.msg);
    }
}
