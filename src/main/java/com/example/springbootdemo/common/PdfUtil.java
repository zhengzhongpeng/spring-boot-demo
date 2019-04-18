package com.example.springbootdemo.common;

import com.example.springbootdemo.common.ResourceFileUtils;
import com.itextpdf.text.pdf.BaseFont;
import com.lowagie.text.DocumentException;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;

/**
 * PdfUtil
 *
 * @author zhengzhongpeng
 * @date 2019/4/17 15:22
 */
public class PdfUtil {

    private static final String FONT_PATH_DEFAULT = "ttc/simsun.ttc";//宋体

    /**
     * 生成byte型 PDF文件
     * @param htmlStr html字符串 格式的 PDF内容
     * @param fontRelatedPath 字体的相对路径 resources/
     * @return
     */
    public static byte[] createPDF(String htmlStr,String fontRelatedPath){
        ByteArrayOutputStream outputStream = null;
        byte[] result = null;
        try {
            outputStream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            ITextFontResolver fontResolver = renderer.getFontResolver();
            renderer.setDocumentFromString(htmlStr);
            fontResolver.addFont(ResourceFileUtils.getAbsolutePath(fontRelatedPath), BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
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

    /**
     * 生成byte[] PDF文件
     * @param htmlStr html字符串 格式的 PDF内容
     * @return
     */
    public static byte[] createPDF(String htmlStr){
        return createPDF(htmlStr,FONT_PATH_DEFAULT);
    }


}
