package com.miku.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.entity.Img;
import com.miku.mapper.ImgMapper;
import com.miku.service.ImgService;
import org.springframework.stereotype.Service;

@Service
public class ImgServiceImpl extends ServiceImpl<ImgMapper, Img> implements ImgService {
}
