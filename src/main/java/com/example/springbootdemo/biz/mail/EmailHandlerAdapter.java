package com.example.springbootdemo.biz.mail;

import javax.annotation.Resource;

/**
 * 邮件处理适配器
 */
public abstract class EmailHandlerAdapter implements EmailHandler {

    @Resource
    protected MailEngine mailEngine ;

    @Override
    public void sendMail(MailParam mailParam) throws Exception {
        fireSendmail(mailParam);
    }


    abstract void fireSendmail(MailParam mailParam) throws Exception;

}
