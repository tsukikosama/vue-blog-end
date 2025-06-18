package com.weilai.blog.model.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BlogReq {
    private Long id;
    /**
     * 标题
     */
    @NotNull(message = "标题不能为空")
    private String title;

    /**
     * 图片
     */
    @NotNull(message = "图片不能为空")
    private String picture;

    /**
     * 内容
     */
    @NotNull(message = "内容不能为空")
    private String content;

    /**
     * 简化标题
     */
    @NotNull(message = "小标题不能为空")
    private String simpleTitle;


    /**
     * 0保存 1发布
     */
    @NotNull(message = "状态不能为空")
    private Integer state;


    @NotNull(message = "标签不能为空")
    private List<Long> tagId;


}
