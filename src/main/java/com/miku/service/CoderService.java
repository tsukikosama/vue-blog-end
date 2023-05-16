package com.miku.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.miku.entity.Code;

public interface CoderService extends IService<Code> {
    public void delCode();
}
