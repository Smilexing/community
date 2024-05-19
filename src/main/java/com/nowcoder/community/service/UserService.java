package com.nowcoder.community.service;

import com.nowcoder.community.constant.ActivationCode;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.utils.CommunityUtil;
import com.nowcoder.community.utils.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailClient mailClient;
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;


    public User findUserById(int id) {

        return userMapper.selectById(id);
    }

    /**
     * 注册用户-map作通用返回类-返回错误信息
     * @param user 账号、密码、邮箱
     * @return
     */
    public Map<String,Object> userRegister(User user) {
        Map<String, Object> map = new HashMap<>();

    //     空值处理
        // 空值处理
        if (user == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        if (StringUtils.isBlank(user.getUsername())) {
            map.put("usernameMsg", "账号不能为空!");
            return map;
        }
        if (StringUtils.isBlank(user.getPassword())) {
            map.put("passwordMsg", "密码不能为空!");
            return map;
        }
        if (StringUtils.isBlank(user.getEmail())) {
            map.put("emailMsg", "邮箱不能为空!");
            return map;
        }

    //     验证账号是否存在
        User u = userMapper.selectByName(user.getUsername());
        if (u != null) {
            map.put("usernameMsg", "账号已存在");
            return map;
        }

    //     注册用户-在传入user对象之前设置其默认值
    //     确定盐值-由uuid随机生成的字符串的前5位
        user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
        user.setPassword(CommunityUtil.md5(user.getPassword() + user.getSalt()));
        user.setType(0);
        user.setStatus(0);
    //      随机生成激活码-后续校验一致代表激活
        user.setActivationCode(CommunityUtil.generateUUID());
    //      设置默认头像图片-调用牛客官网图像库
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        user.setCreateTime(new Date());
        userMapper.insertUser(user);

    //     激活邮件-发送html模板邮件点击进行注册
    //     这里以html模板需要使用thymeleaf下的context进行加载
        Context context = new Context();
        context.setVariable("email", user.getEmail());

    //    拼接url(访问域名） http://localhost:8080/community/activation/{userid}/{activationcode}
        String url = domain + contextPath+ "/activation/" + user.getId() + "/" + user.getActivationCode();
        context.setVariable("url", url);

        String content = templateEngine.process("/mail/activation", context);
        mailClient.SendMessage(user.getEmail(), "激活账号", content);
        return map;
    }

    /**
     * 账号激活
     * @param userid
     * @param code
     * @return
     */

    // 匹配随机生成的activationcode是否一致
    public int acvtivation(int userid, String code) {
        User user = userMapper.selectById(userid);
        if (user.getStatus() == 1) {
            return ActivationCode.ACTIVATION_REPEAT;
        } else if (user.getActivationCode().equals(code)) {
            return ActivationCode.ACTIVATION_SUCCESS;
        }
        return ActivationCode.ACTIVATION_FAILURE;
    }

}
