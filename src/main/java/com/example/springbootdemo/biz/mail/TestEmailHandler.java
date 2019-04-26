package com.example.springbootdemo.biz.mail;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


/**
 * 测试邮件处理类
 */
@Service("testEmailHandler")
public class TestEmailHandler extends EmailHandlerAdapter {

    @Override
    void fireSendmail(MailParam mailParam) throws Exception{
        MailInfo mailInfo = new MailInfo();
        mailInfo.setTo(new String[]{mailParam.getTo()});
        mailInfo.setSubject("JAVA邮件模板测试");
        mailInfo.setContent("Jave Mail Hello word !");
        Map<String,Object> model = new HashMap<>();
        model.put("nowDate", LocalDateTime.now().toString());
        mailEngine.sendMailFreeMarker(mailInfo,"testMailTemplate.ftl",model);
    }
}
