package com.nowcoder.community.utils;

import com.nowcoder.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author Tom Smile
 * @version 1.0
 * @description: Threadlocal存放用户信息，代替session
 * @date 2024/5/20 21:08
 */
@Component
public class HostHolder {

    private ThreadLocal<User> threadLocal = new ThreadLocal<>();

    /**
     * 存放用户信息到threadlocal
     * @param user
     */
    public void setUser(User user) {
        threadLocal.set(user);
    }

    /**
     * 获取threadlocal存放的用户信息
     * @return
     */
    public User getUser() {
       return threadLocal.get();
    }

    /**
     * 删掉threadlocal中存放的用户信息
     */
    public void clear() {
        threadLocal.remove();
    }
}
