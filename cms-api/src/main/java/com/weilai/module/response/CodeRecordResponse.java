package com.weilai.module.response;

import com.weilai.module.entity.CodeEntity;
import com.weilai.module.entity.DictEntity;
import lombok.Data;

import java.util.List;

@Data
public class CodeRecordResponse {
    private Long id;
    private String name;
    private List<DictEntity> children;
}
