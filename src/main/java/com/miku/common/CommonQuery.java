package com.miku.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonQuery {
    /**
     * 页数
     */
    private Integer pageNum;
    /**
     * 每页数据
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

    /**
     * 作者
     */
    private String author;




}
