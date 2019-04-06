package com.example.springbootdemo.biz.mail;

/**
 * 邮件处理类
 */
public interface EmailHandler {

    /**
     * 公共发送邮件接口
     * @param mailParam
     */
    void sendMail(MailParam mailParam) throws Exception;

}
