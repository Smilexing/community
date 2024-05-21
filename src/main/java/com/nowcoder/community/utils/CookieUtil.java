package com.nowcoder.community.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Tom Smile
 * @version 1.0
 * @description: cookie工具类
 * @date 2024/5/20 20:57
 */
public class CookieUtil {

    /**
     * 返回cookie的值
     * @param request
     * @param name
     * @return
     */
    public static String getCookie(HttpServletRequest request,String name) {
    //     空值判断
        if (request == null || name == null) {
            throw  new IllegalArgumentException("参数为空！");
        }
        // cookie的value是object类型
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            // 遍历每一个cookie，查找有无对应的cookie
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }

            }
        }
        return null;
    }
}
