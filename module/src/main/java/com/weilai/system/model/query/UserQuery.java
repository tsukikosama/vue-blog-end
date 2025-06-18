package com.weilai.system.model.query;

import com.weilai.system.common.CommonPage;
import lombok.Data;

@Data
public class UserQuery extends CommonPage {
    private String username;
    private String nickname;
//    private List<LocalTime> createTime;
    private Integer type;
    private Long deptId;
}
