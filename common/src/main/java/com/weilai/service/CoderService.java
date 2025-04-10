package com.weilai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.entity.Code;

public interface CoderService extends IService<Code> {
    public void delCode();
}
