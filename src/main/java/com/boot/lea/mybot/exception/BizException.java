package com.boot.lea.mybot.exception;

import lombok.Data;

/**
 * @author LiJing
 * @ClassName: BizException
 * @Description: 自定义业务异常
 * @date 2019/7/30 13:57
 */

@Data
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String msg;
    private Integer code;

    public BizException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BizException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BizException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public BizException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public BizException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }


}
