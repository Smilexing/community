package com.nowcoder.community.controller.interceptor;

import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.utils.CookieUtil;
import com.nowcoder.community.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Tom Smile
 * @version 1.0
 * @description: 根据登录凭证实现白名单页面
 * @date 2024/5/20 19:57
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle is called");
        System.out.println(request.getMethod());
        System.out.println(request.getContextPath());
        System.out.println(request.getRequestURI());
        // 从cookie中获取登录凭证
        String ticket = CookieUtil.getCookie(request, "ticket");
        // 空值判定
        if (ticket != null) {
        //     查询Cookie登录凭证
            LoginTicket loginTicket = userService.findLoginTicket(ticket);
        //     判断cookie登录凭证是否有效(状态、过期）
            if (loginTicket != null && loginTicket.getStatus() == 0 && loginTicket.getExpired().after(new Date())) {
            //     根据凭证查询用户信息
                User user = userService.findUserById(loginTicket.getUserId());
            //     将用户信息存放到threadlocal
                hostHolder.setUser(user);
            }

        }

        return true;
    }

    /**
     * 拦截之后传给模板引擎model封装
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    //     获取用户信息从threadlocal
        User user = hostHolder.getUser();
        // 登录凭证有效，进行封装model
        if (user != null && modelAndView != null) {
            // 模板文件通过loginUser拿到user对象
            modelAndView.addObject("loginUser",user);
        }
    }

    /**
     * 拦截之后清理threadlocal对象
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();

    }
}
