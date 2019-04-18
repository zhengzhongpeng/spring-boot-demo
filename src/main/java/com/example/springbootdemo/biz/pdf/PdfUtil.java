package com.example.springbootdemo.biz.pdf;

import com.example.springbootdemo.common.ResourceFileUtils;
import com.example.springbootdemo.common.SpringUtils;
import com.itextpdf.text.pdf.BaseFont;
import com.lowagie.text.DocumentException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * PdfUtil
 *
 * @author zhengzhongpeng
 * @date 2019/4/17 15:22
 */
public class PdfUtil {

//    private static FreeMarkerConfigurer configurer;

    public static String freeMarkerRender(Map<String,Object> model,String templatePath){
        try {
            FreeMarkerConfigurer configurer = SpringUtils.getBean(FreeMarkerConfigurer.class);
            Template template = configurer.getConfiguration().getTemplate(templatePath);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] createPdf(String htmlStr){
        ByteArrayOutputStream outputStream = null;
        byte[] result = null;
        try {
            outputStream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            ITextFontResolver fontResolver = renderer.getFontResolver();
            renderer.setDocumentFromString(htmlStr);
            File file = ResourceFileUtils.getFile("ttc/simsun.ttc");
            fontResolver.addFont(ResourceFileUtils.getAbsolutePath("ttc/simsun.ttc"), BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
            renderer.layout();
            renderer.createPDF(outputStream);
            result = outputStream.toByteArray();
        } catch (DocumentException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


}
