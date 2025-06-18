package com.weilai.blog.model.resp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "博客详情信息")
public class BlogResp {
    private Long id;


    /**
     * 标题
     */
    private String title;

    /**
     * 图片
     */
    private String picture;

    /**
     * 内容
     */
    private String content;

    /**
     * 流量数量
     */
    private Integer visit;

    /**
     * 简化标题
     */
    private String simpleTitle;

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 0保存 1发布
     */

    private Integer state;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    private Long updateUser;

    private List<Long> tagId;
}
