package com.example.springbootdemo.mail;

import com.example.springbootdemo.biz.mail.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaMailTest {


    @Test
    public void testMailHandlerTest(){
        MailParam mailParam = new MailParam();
        mailParam.setTo("1010770504@qq.com");
        EmailHandler emailHandler = MailHandlerFactory.createMailHandler(MailHandlerType.TEST_EMAIL_HANDLER);
        try {
            emailHandler.sendMail(mailParam);
        }catch (Exception e){
            System.out.print(e);
        }
    }
}
