package com.istudy.common;

import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Description: TODO
 * @Author Baizhen
 * @Date 2020/5/13 20:20
 */
public class ReadFileUtil {

    static String getCriteriaFilePath(String fileName) {
        String path;
        try {
            path = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + fileName).getAbsoluteFile().getPath();
        } catch (FileNotFoundException e) {
            throw new NullPointerException(fileName + "文件不存在");
        }
        return path;
    }

    static String readFile(String fileName) {
        String ret;
        try {
            ret = readJarFile(fileName);
        } catch (IOException e) {
            throw new NullPointerException(fileName + "文件读取异常");
        }
        return ret;
    }

    /**
     *  读取jar包中的资源文件
     * @param fileName 文件名
     * @return 文件内容
     * @throws IOException 读取错误
     */
    static String readJarFile(String fileName) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(ReadFileUtil.class.getClassLoader().getResourceAsStream(fileName)));

        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        // 通过不同的OS来进行不同方式的读取
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            String filePath = getCriteriaFilePath("file/wechatcrert/1584445991/apiclient_cert.p12");
            System.out.println("文件路径：" + filePath);
        } else {
            String content = readFile("file/wechatcrert/1584445991/apiclient_cert.p12");
            System.out.println("内容为：" + content);
        }
    }

}
