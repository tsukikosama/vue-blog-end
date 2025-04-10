package com.weilai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.entity.Img;
import com.weilai.mapper.ImgMapper;
import com.weilai.service.ImgService;
import org.springframework.stereotype.Service;

@Service
public class ImgServiceImpl extends ServiceImpl<ImgMapper, Img> implements ImgService {
}
