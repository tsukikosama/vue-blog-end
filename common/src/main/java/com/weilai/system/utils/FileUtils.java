package com.weilai.system.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;

import java.io.File;

public class FileUtils {
    /**
     * 生成文件名
     *
     * @param file
     * @return
     */
    public static String createFileName(File file) {
        String suffix = FileUtil.getSuffix(file);
        String fileName = IdUtil.randomUUID() + "." + suffix;
        return fileName;
    }

    /**
     * 生成文件名
     *
     * @param file
     * @return
     */
    public static String createFileName(String file) {
        String suffix = FileUtil.getSuffix(file);
        String fileName = IdUtil.randomUUID() + "." + suffix;
        return fileName;
    }

    /**
     * 获取本地保存路径
     *
     * @param localPath
     * @return
     */
    public static String createFilePath(String localPath) {
        return localPath + File.separator + DateUtil.today() + File.separator;
    }

}
