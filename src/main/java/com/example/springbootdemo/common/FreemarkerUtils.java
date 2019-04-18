package com.example.springbootdemo.common;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.Map;

/**
 * FreemarkerUtil
 *
 * @author zhengzhongpeng
 * @date 2019/4/18 9:21
 */
public class FreemarkerUtils {

    /**
     * ftl 模板解析
     * @param model 模板参数
     * @param templateName  resources 路径下 的 模板名称
     * @return
     */
    public static String freeMarkerRender(Map<String,Object> model, String templateName){
        try {
            FreeMarkerConfigurer configurer = SpringUtils.getBean(FreeMarkerConfigurer.class);
            Template template = configurer.getConfiguration().getTemplate(templateName);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

}
