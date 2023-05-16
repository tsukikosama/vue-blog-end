package com.miku.utils;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.miku.entity.Code;
import com.miku.service.impl.CodeServiceImpl;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.Date;

/**
 * 邮件业务类
 * @author qzz
 */
@Service
@Slf4j
public class MailService {

    /**
     * 注入邮件工具类
     */
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private CodeServiceImpl codeService;

    @Value("${spring.mail.username}")
    private String sendMailer;

    /**
     * 检测邮件信息类
     * @param to
     * @param subject
     * @param text
     */
    private void checkMail(String to,String subject,String text){
        if(StrUtil.isEmpty(to)){
            throw new RuntimeException("邮件收信人不能为空");
        }
        if(StrUtil.isEmpty(subject)){
            throw new RuntimeException("邮件主题不能为空");
        }
        if(StrUtil.isEmpty(text)){
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    /**
     * 发送纯文本邮件
     * @param to
     */

    //表示这个方法是一个异步任务
    @Async
    @Transactional
    public void sendTextMailMessage(String to){
        String subject = "验证码";
        String text = RandomUtil.randomNumbers(6);
        try {
            //true 代表支持复杂的类型
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(),true);
            //邮件发信人
            mimeMessageHelper.setFrom(sendMailer);
            //邮件收信人  1或多个
            mimeMessageHelper.setTo(to.split(","));
            //邮件主题
            mimeMessageHelper.setSubject(subject);
            //邮件内容
            mimeMessageHelper.setText("您的验证码为"+text+"有效期为5分钟");
            //邮件发送时间
            mimeMessageHelper.setSentDate(new Date());

            //发送邮件
            javaMailSender.send(mimeMessageHelper.getMimeMessage());

//            System.out.println("发送邮件成功："+sendMailer+"->"+to);
            Code code = new Code(to,text);
            //把验证码存入数据库中
            codeService.save(code);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("发送邮件失败："+e.getMessage());
        }
    }
}

