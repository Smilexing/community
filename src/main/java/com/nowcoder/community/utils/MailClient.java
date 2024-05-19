package com.nowcoder.community.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.standard.expression.MessageExpression;
import org.thymeleaf.standard.expression.Token;

import javax.mail.internet.MimeMessage;


/**
 * @author Tom Smile
 * @version 1.0
 * @description: TODO
 * @date 2024/5/19 9:30
 */
@Component
public class MailClient {
    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

    @Autowired
    private JavaMailSender sender;

    @Value("2664626327@qq.com")
    private String from;

    public void SendMessage(String to, String subject, String content) {
        // 创建message-message接受上面的三个参数
        MimeMessage message = sender.createMimeMessage();
        // 使用代理对象来设置message
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            // 设置发送人
            helper.setFrom(from);
            sender.send(helper.getMimeMessage());
        } catch (Exception e) {
            logger.error("发送邮件失败" + e.getMessage());
        }
    }
}
