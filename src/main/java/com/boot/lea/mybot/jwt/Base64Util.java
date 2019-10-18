package com.boot.lea.mybot.jwt;

/**
 * @Title: Base64Util.java
 * @Package com.boot.lea.mybot.jwt
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/10/18 11:05
 * @version v.3.0
 */


import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;


/**
 * @ClassName: Base64Util
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiJing
 * @date 2019/10/18 11:05 
 *
 */
public class Base64Util {
    private static final Logger logger = LoggerFactory.getLogger(Base64Util.class);

    private static final String charset = "utf-8";

    /**
     * 解密
     * @param data
     * @return
     */
    public static String decode(String data) {
        try {
            if (null == data) {
                return null;
            }

            return new String(Base64.decodeBase64(data.getBytes(charset)), charset);
        } catch (UnsupportedEncodingException e) {
            logger.error(String.format("字符串：%s，解密异常", data), e);
        }

        return null;
    }

    /**
     * 加密
     * @param data
     * @return
     */
    public static String encode(String data) {
        try {
            if (null == data) {
                return null;
            }
            return new String(Base64.encodeBase64(data.getBytes(charset)), charset);
        } catch (UnsupportedEncodingException e) {
            logger.error(String.format("字符串：%s，加密异常", data), e);
        }

        return null;
    }
}
