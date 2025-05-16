package com.weilai.module.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.weilai.common.Result;
import com.weilai.module.entity.CodeEntity;
import com.weilai.module.entity.DictEntity;
import com.weilai.module.service.ICodeService;
import com.weilai.module.service.IDictService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miku
 * @since 2025-05-15
 */
@RestController
@RequestMapping("/dict")
@AllArgsConstructor
public class DictController {
    private final IDictService iDictService;

    private final ICodeService iCodeService;
//    @GetMapping("/list")
//    public Result list(){
//        List<DictEntity> list = iDictService.list();
//        list.stream().forEach(item -> {
//            iCodeService.list(Wrappers.<CodeEntity>lambdaQuery().eq(CodeEntity::getCode));
//
//        });
//    }
}
