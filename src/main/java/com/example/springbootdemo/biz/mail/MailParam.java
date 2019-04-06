package com.example.springbootdemo.biz.mail;

import java.util.List;
import java.util.function.Function;

public class MailParam {

    /**
     * 交换参数
     */
    private Function<Object,Object> swap;

    /**
     * 收件人
     */
    private String to;

    /**
     * 抄送
     */
    private String cc;

    /**
     * 主题
     */
    private String subject;

    /**
     * 别名
     */
    private String alias;

    /**
     * 内容
     */
    private String content;

    /**
     * 附件名称
     */
    private String attachmentName;

    /**
     * 附件地址
     */
    private String attachmentUrl;

    /**
     * 附件
     */
    private List<Attachment> attachmentList;

    public Function<Object, Object> getSwap() {
        return swap;
    }

    public void setSwap(Function<Object, Object> swap) {
        this.swap = swap;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }
}
