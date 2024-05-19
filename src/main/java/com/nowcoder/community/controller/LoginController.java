package com.nowcoder.community.controller;

import com.nowcoder.community.constant.ActivationCode;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Tom Smile
 * @version 1.0
 * @description: TODO
 * @date 2024/5/19 11:15
 */
@RequestMapping
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 返回到注册页面
     *
     * @return
     */
    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getRegisterPage() {

        return "/site/register";
    }

    /**
     * 返回到登录页面
     *
     * @return
     */
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "/site/login";
    }

    /**
     * 注册用户
     *
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String userRegister(Model model, User user) {

        // map接受的错误信息，无异常map应该为null
        Map<String, Object> map = userService.userRegister(user);
        // 注册成功
        if (map == null || map.isEmpty()) {
            model.addAttribute("msg", "注册成功,我们已经向您的邮箱发送了一封激活邮件,请尽快激活!");
            model.addAttribute("target", "/index");
            // 跳转到激活页面
            return "/site/operate-result";
        } else {
            // 错误消息-绑定到模板文件，页面返回
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            model.addAttribute("emailMsg", map.get("emailMsg"));
            return "/site/register";
        }
    }

    // 以http://localhost:8080/community/activation/{userid}/{activationcode}访问
    @RequestMapping(path = "activation/{userId}/{code}", method = RequestMethod.GET)
    public String activation(Model model, @PathVariable("userId") int userId, @PathVariable("code") String code) {
        int result = userService.acvtivation(userId, code);
        if (result == ActivationCode.ACTIVATION_SUCCESS) {
            model.addAttribute("msg", "激活成功，您的账号可以正常使用！");
            model.addAttribute("target", "/login");
        } else if (result == ActivationCode.ACTIVATION_REPEAT) {
            model.addAttribute("msg", "无效操作,该账号已经激活过了!");
            model.addAttribute("target", "/index");
        } else {
            model.addAttribute("msg", "激活失败,您提供的激活码不正确!");
            model.addAttribute("target", "/index");
        }
        return "/site/operate-result";
    }

    // @RequestMapping(path = "/code", method = RequestMethod.GET)
    // public
    //
    // @RequestMapping(path = "/login", method = RequestMethod.POST)
    // public String userLoginPage(String username, String password, String code, boolean remberme
    //         , Model model, HttpSession session, HttpServletRequest request) {
    //
    //     // 判断优先级-验证码
    // }
}
