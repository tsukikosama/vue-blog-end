package com.miku.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.entity.Code;
import com.miku.mapper.CodeMapper;
import com.miku.service.CoderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CodeServiceImpl extends ServiceImpl<CodeMapper, Code> implements CoderService {

    /**
     * redis代替验证码 弃用
     */
    @Override
    public void delCode() {
        //通过手机查找到对应的验证码然后删除
        LambdaQueryWrapper<Code> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Code::getEmail,"*");
        boolean remove = remove(wrapper);
        System.out.println(remove);
        if (remove){
            log.info("验证码清除成功");
        }
    }
}
