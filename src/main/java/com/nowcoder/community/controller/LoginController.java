package com.nowcoder.community.controller;

import com.google.code.kaptcha.Producer;
import com.nowcoder.community.constant.ActivationCode;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
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
    @Autowired
    private Producer kapatchaProducer;

    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

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

    /**
     * 获取图形验证码并存入session中返回给用户
     * @param response
     */

    @RequestMapping(path = "kaptcha", method = RequestMethod.GET)
    public void getKaptcha(HttpServletResponse response, HttpSession session) {
    //     生成验证码
        String text = kapatchaProducer.createText();
        BufferedImage image = kapatchaProducer.createImage(text);

    //     将验证码存入到session中
        session.setAttribute("kaptcha", text);

    //     将图片输出给游览器
        response.setContentType("image/png");
        try{
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, "png", os);
        } catch (IOException e) {
            logger.error("响应验证码失败:" + e.getMessage());
        }
    }


    // @RequestMapping(path = "/login", method = RequestMethod.POST)
    // public String userLoginPage(String username, String password, String code, boolean remberme
    //         , Model model, HttpSession session, HttpServletRequest request) {
    //
    //     // 判断优先级-验证码
    // }
}
