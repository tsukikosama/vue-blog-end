package com.weilai.cms.blog;

import cn.hutool.core.bean.BeanUtil;
import com.weilai.blog.model.entity.BlogTagEntity;
import com.weilai.blog.model.entity.TagEntity;
import com.weilai.blog.model.query.TagQueryReq;
import com.weilai.blog.model.req.TagReq;
import com.weilai.blog.model.resp.TagResp;
import com.weilai.blog.service.ITagService;
import com.weilai.blog.service.impl.BlogTagServiceImpl;
import com.weilai.system.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miku
 * @since 2025-06-12
 */
@RestController
@RequestMapping("/admin/blogTag")
@Tag(name = "标签接口", description = "标签接口")
@RequiredArgsConstructor
public class BlogTagController {

}
