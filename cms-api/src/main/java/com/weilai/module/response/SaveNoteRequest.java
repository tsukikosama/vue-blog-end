package com.weilai.module.response;

import lombok.Data;

@Data
public class SaveNoteRequest {
    private Long id;
    private String cause;
    private Integer type;
    private String name;
}
