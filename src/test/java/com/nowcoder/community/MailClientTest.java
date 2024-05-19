package com.nowcoder.community;

import com.nowcoder.community.CommunityApplication;
import com.nowcoder.community.utils.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommunityApplication.class)
public class MailClientTest {

    @Autowired
    private MailClient mailClient;

    // 以html模板形式发送邮件
    @Autowired
    private TemplateEngine templateEngine;
    @Test
    public void sendMail() {
        mailClient.SendMessage("chineselanguages@163.com", "Test", "welcome");
    }

    @Test
    public void sendHtmlMail() {
        Context context = new Context();
        // 设置动态绑定的属性值
        context.setVariable("username", "Smilexin");
        // 设置返回的html页面
        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);
        mailClient.SendMessage("chineselanguages@163.com", "HTML", content);
    }
}