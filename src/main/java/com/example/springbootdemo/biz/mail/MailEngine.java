package com.example.springbootdemo.biz.mail;

import com.example.springbootdemo.common.FreemarkerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

/**
 * 邮件发送引擎
 */
@Service
public class MailEngine {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 默认发件人
     */
    @Value("${spring.mail.from}")
    private String from ;

    public void sendMailFreeMarker(MailInfo mailInfo) throws Exception{
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        messageHelper.setTo(mailInfo.getTo());//收件人
        if (mailInfo.getCc() != null){
            messageHelper.setCc(mailInfo.getCc());//抄送
        }
        messageHelper.setFrom(from);//发送人
        messageHelper.setSubject(mailInfo.getSubject());//主题
        messageHelper.setText(mailInfo.getContent());//内容
        if ( mailInfo.getAttachmentList() != null){
            for (Attachment attachment : mailInfo.getAttachmentList() ) {
                messageHelper.addAttachment(attachment.getAttachmentName(),new File(attachment.getAttachmentUrl()));
            }
        }
        mailSender.send(mimeMessage);
    }

    /**
     * 发送邮件
     * @param mailInfo
     * @param template
     * @param modelParam
     */
    public void sendMailFreeMarker(MailInfo mailInfo, String template, Map<String,Object> modelParam)throws Exception{
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        messageHelper.setTo(mailInfo.getTo());//收件人
        if (mailInfo.getCc() != null){
            messageHelper.setCc(mailInfo.getCc());//抄送
        }
        messageHelper.setFrom(from);//发送人
        messageHelper.setSubject(mailInfo.getSubject());//主题
        if ( mailInfo.getAttachmentList() != null){
            for (Attachment attachment : mailInfo.getAttachmentList() ) {
                messageHelper.addAttachment(attachment.getAttachmentName(),new File(attachment.getAttachmentUrl()));
            }
        }
        String content = FreemarkerUtils.freeMarkerRender(modelParam,template);
        messageHelper.setText(content,true);
        mailSender.send(mimeMessage);
    }

}
