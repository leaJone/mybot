package com.boot.lea.mybot.utils;
/**
 * @Title: IpUtils.java
 * @Package com.boot.lea.mybot.utils
 * @Description: TODO(用一句话描述该文件做什么)
 * @author lijing
 * @date 2019-08-21 17:32
 * @version v.3.0
 */

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author lijing
 * @ClassName: IpUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-21 17:32
 */
@Slf4j
public class IpUtils {

    public static String getCliectIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ip.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ip = str;
                break;
            }
        }
        return ip;
    }


    /**
     * 获取ip v4地址
     *
     * @return
     */
    public static String getIpAddr() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            log.info("获取到的ip地址：{}", ip.getHostAddress());
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("获取ip地址失败，{}", e);
        }
        return "";
    }
}
