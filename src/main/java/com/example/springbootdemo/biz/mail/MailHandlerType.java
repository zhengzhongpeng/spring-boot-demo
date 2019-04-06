package com.example.springbootdemo.biz.mail;

/**
 * 邮件发送类型
 */
public enum MailHandlerType {

    NON(""),
    TEST_EMAIL_HANDLER("邮件测试处理类"),
    ;


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private MailHandlerType(String name){}


}
