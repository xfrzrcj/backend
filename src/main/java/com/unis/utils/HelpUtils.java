package com.unis.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * FileName: HelpUtils
 * Author:   Mr.wjg
 * Date:     2018/9/26 9:55
 * Description: 杂乱的方法
 * History:
 * <author>          <time>          <version>          <desc>
 * 修改名称：          修改时间：        版本号：              描述
 */
public class HelpUtils {

    public static String getVerificationCode() {
        String sources = "0123456789";
        // 加上一些字母，就可以生成pc站的验证码了
        Random rand = new Random();
        StringBuilder flag = new StringBuilder();
        for (int j = 0; j < 6; j++) {
            flag.append(sources.charAt(rand.nextInt(9)) + "");
        }
//        System.out.println(flag.toString());
        return flag.toString();
    }

    public static String getCookieValByKey(String key, HttpServletRequest request) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    value = cookie.getValue();
                }
            }
        }
        return value;
    }

    private static final ThreadLocal<DateFormat> YYYY_MM_DD_HH_MM_SS_Thread = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //严格日期
            sdf.setLenient(false);
            return sdf;
        }
    };

    private static final ThreadLocal<DateFormat> YYYY_MM_DD_Thread = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //严格日期
            sdf.setLenient(false);
            return sdf ;
        }
    };

    public static String toYMD(Date date){
        return YYYY_MM_DD_Thread.get().format(date);
    }

    public static String toYMDHMS(Date date){
        return YYYY_MM_DD_HH_MM_SS_Thread.get().format(date);
    }

    public static String formatDouble(Double num){
        String result = "0";
        if (num != null && num != 0){
            result = String.valueOf(num);
        }
        return result;
    }
}
