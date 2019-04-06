package com.example.springbootdemo.biz.mail;

import com.example.springbootdemo.common.SpringUtils;
import org.springframework.util.Assert;

/**
 * 邮件处理类工厂
 */
public class MailHandlerFactory {

    private MailHandlerFactory(){}

    public static EmailHandler createMailHandler(MailHandlerType handlerType){
        EmailHandler emailHandler = null;
        switch (handlerType){
            case TEST_EMAIL_HANDLER:
                emailHandler = (EmailHandler)SpringUtils.getBean("testEmailHandler");
                break;
            default:
                Assert.isTrue(false,"没有匹配的邮件处理类");
        }
        return emailHandler;
    }

}
