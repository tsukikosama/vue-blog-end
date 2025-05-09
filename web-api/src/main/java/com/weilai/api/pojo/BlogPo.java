package com.weilai.api.pojo;

import com.weilai.api.entity.Type;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
