package com.weilai.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageQuery {
    /**
     * 当前页数
     */
    private Integer current;
    /**
     * 每页大小
     */
    private Integer pageSize;
    /**
     * 关键词
     */
    private String key;
    /**
     * 起始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

}
