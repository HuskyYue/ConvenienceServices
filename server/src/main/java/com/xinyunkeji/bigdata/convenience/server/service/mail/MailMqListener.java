package com.xinyunkeji.bigdata.convenience.server.service.mail;

import com.xinyunkeji.bigdata.convenience.server.controller.AbstractController;
import com.xinyunkeji.bigdata.convenience.server.request.MailRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 邮件监听器-消费者
 *
 * @author Yuezejian
 * @date 2020年 08月24日 20:23:50
 */
@Component
public class MailMqListener extends AbstractController {

    @Autowired
    private MailService mailService;

    //监听邮件发送的消息
    @RabbitListener(queues = {"${mq.email.queue}"},containerFactory = "multiListenerContainer")
    public void consumMsg(MailRequest request) {
        try {
            //TODO:
            log.info("监听消费邮件发送的消息--监听到消息；{}",request);
            if (request != null && StringUtils.isNotBlank(request.getUserMails())){
                mailService.sendSimpleEmail(request.getSubject(),request.getContent(),
                        StringUtils.split(request.getUserMails(),","));//用逗号隔开实现邮件的群发
            }
        } catch (Exception e) {
            log.error("监听消费邮件发送的消息--监听异常",e);
        }
    }
}