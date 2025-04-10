package com.weilai.config;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.dfa.WordTree;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ForbiddenWordsLoader {

    @Value("${forbidden.words.filepath}")
    private String forbiddenWordsFilePath;

    private String forbiddenWords;

    private WordTree wordTree;

    public void init() {
        // 通过 ResourceUtil 获取资源
        String content = ResourceUtil.readUtf8Str(forbiddenWordsFilePath);
        if (content != null) {
            forbiddenWords = content.trim(); // 去除首尾空白字符
            System.out.println(forbiddenWords);
        } else {
            // 文件内容为空或无法读取文件
            forbiddenWords = "";
            System.out.println("空");
        }
    }


    public String getForbiddenWords() {
        return forbiddenWords;
    }

    public WordTree newWordTree(){
        init();
        WordTree wt = new WordTree();
        String forbiddenWords1 = getForbiddenWords();
        String[] split = forbiddenWords1.split(",");
        for (String s : split) {
            wt.addWord(s);
        }
        return wt;
    }

    public Boolean checkWord(String text){
        System.out.println(text);
        if (wordTree == null){
            wordTree = newWordTree();
            log.info("初始化成功");
        }
        log.info("开始检查");
        return wordTree.isMatch(text);
    }
}
