package com.nowcoder.community.controller.interceptor;

import com.nowcoder.community.annotation.LoginRequired;
import com.nowcoder.community.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Tom Smile
 * @version 1.0
 * @description: TODO
 * @date 2024/5/22 16:19
 */

@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {
    @Autowired
    private HostHolder hostHolder;


    /**
     * 拦截方法-自定义注解
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断处理器是否拦截到方法-是则应该为HandlerMethod类型
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 通过反射拿到method对象
            Method method = handlerMethod.getMethod();
            //尝试去取LoginRequired注解
            LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
            // 空值判定-loginRequired是否存在，不存在-未登录
            if (loginRequired != null && hostHolder.getUser() == null) {
                // 未登录-重定向到localhost:8080/community/login
                response.sendRedirect(request.getContextPath()+"/login");
                return false;
            }
        }
        return true;


    }
}
