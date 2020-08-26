package com.xinyunkeji.bigdata.convenience.server.service.mail;

import com.xinyunkeji.bigdata.convenience.server.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
/**
 * 邮件service
 *
 * @author Yuezejian
 * @date 2020年 08月24日 20:23:04
 */
@Service
public class MailService extends AbstractController {
    @Autowired
    private Environment env;

    @Autowired
    private JavaMailSender mailSender;

    //TODO:发送简单的邮件消息
    @Async("threadPoolTaskExecutor")
    public void sendSimpleEmail(final String subject,final String content,final String ... tos){
        try {
            SimpleMailMessage message=new SimpleMailMessage();
            message.setSubject(subject);
            message.setText(content);
            message.setTo(tos);
            message.setFrom(env.getProperty("mail.send.from"));
            mailSender.send(message);

            log.info("----发送简单的邮件消息完毕--->");
        }catch (Exception e){
            log.error("--发送简单的邮件消息,发生异常：to={},subject={}",tos,subject,e.fillInStackTrace());
        }
    }
}