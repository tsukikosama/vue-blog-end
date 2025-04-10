package com.weilai.pojo;

import com.weilai.entity.Type;
import lombok.Data;

import java.util.List;

@Data
public class BlogPo {
    private Integer id;
    private String title;
    private String createDate;
    private Integer visit;
    private Integer bid;
    private String content;
    //tagid
    private String tid;
    private List<Type> tagname;
}
