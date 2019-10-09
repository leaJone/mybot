package com.boot.lea.mybot.utils;

/**
 * @Title: RegexUtils.java
 * @Package com.boot.lea.mybot.utils
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/10/8 17:41
 * @version v.3.0
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LiJing
 * @ClassName: RegexUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/10/8 17:41
 */
public class RegexUtils {

    public static boolean isNotContain(String srcString, String checkString) {
        Pattern pattern = Pattern.compile("^(?!.*?" + checkString + ").*$");
        return pattern.matcher(srcString).matches();
    }


    public static void main(String[] args) {
        //前瞻表达式  否定式前瞻 正则表达式中有前瞻（Lookahead）和后顾（Lookbehind）
        //正则表达式是NFA   DFA 算法  有限的自动机 NFA 算法 不包含字符串
        Pattern pattern = Pattern.compile("^(?!.*?\\/\\/).*$");
        final Matcher matcher1 = pattern.matcher("//");
        final Matcher matcher2 = pattern.matcher("acc///");
        final Matcher matcher3 = pattern.matcher("acc//www");
        final Matcher matcher4 = pattern.matcher("acc/ww/w");
        final Matcher matcher5 = pattern.matcher("accwww");

        System.out.println(matcher1.matches());
        System.out.println(matcher2.matches());
        System.out.println(matcher3.matches());
        System.out.println(matcher4.matches());
        System.out.println(matcher5.matches());

        System.out.println(isNotContain("acc///", "cc"));
    }
}
