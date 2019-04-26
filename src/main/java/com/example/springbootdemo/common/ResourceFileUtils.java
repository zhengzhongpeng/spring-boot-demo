package com.example.springbootdemo.common;

import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * FileUtils
 *
 * @author zhengzhongpeng
 * @date 2019/4/17 17:25
 */
public class ResourceFileUtils {

    /**
     * 获取资源文件
     * @param relativePath
     * @return
     * @throws FileNotFoundException
     */
    public static File getFile(String relativePath) throws FileNotFoundException {
        if (StringUtils.isEmpty(relativePath)){
            return null;
        }
        if (relativePath.startsWith("/")){
            relativePath = relativePath.substring(1);
        }
        return ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX+relativePath);
    }

    /**
     * 获取绝对路径
     * @param relativePath
     * @return
     * @throws FileNotFoundException
     */
    public static String getAbsolutePath(String relativePath) throws FileNotFoundException {
        return getFile(relativePath).getAbsolutePath();
    }

    /**
     * 获取资源父级目录
     * @param relativePath
     * @return
     */
    public static String getParent(String relativePath) throws FileNotFoundException {
        return getFile(relativePath).getParent();
    }

    /**
     * 获取资源文件名
     * @param relativePath
     * @return
     * @throws FileNotFoundException
     */
    public static String getFileName(String relativePath) throws FileNotFoundException {
        return getFile(relativePath).getName();
    }

}
