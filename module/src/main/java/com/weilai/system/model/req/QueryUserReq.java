package com.weilai.system.model.req;

import com.weilai.system.common.CommonPage;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class QueryUserReq extends CommonPage {
    private String username;
    private String nickname;
//    private List<LocalTime> createTime;
    private Integer type;
    private Long deptId;
}
