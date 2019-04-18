package com.example.springbootdemo.pdf;

import com.example.springbootdemo.Services.user.IUserService;
import com.example.springbootdemo.biz.pdf.PdfUtil;
import com.example.springbootdemo.dao.pojo.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * PdfTest
 *
 * @author zhengzhongpeng
 * @date 2019/4/17 16:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PdfTest {

    @Autowired
    private IUserService userService;

    @Test
    public void createUserListTable(){
        List<UserDO> userDOList = userService.queryAll();
        Map<String,Object> model = new ConcurrentHashMap<>();
        model.put("userList",userDOList);
        String htmlStr = PdfUtil.freeMarkerRender(model,"testPdfUserTableTemplate.ftl");
        byte[] pdfByte = PdfUtil.createPdf(htmlStr);
        if (pdfByte != null && pdfByte.length > 0){
            BufferedOutputStream bos = null;
            FileOutputStream fos = null;
            File file = null;
            try {
                File dir = new File("E:/demo/pdf");
                if (! dir.exists() ){
                    dir.mkdirs();
                }
                file = new File("E:/demo/pdf/testPdf.pdf");
                if (file.exists()){
                    file.delete();
                }
                fos = new FileOutputStream(file);
                bos = new BufferedOutputStream(fos);
                bos.write(pdfByte);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    bos.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
