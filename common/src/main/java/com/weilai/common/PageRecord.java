package com.weilai.common;

import lombok.Data;

import java.util.List;

@Data
public class PageRecord {
    private List<Object> records;
    private int current;
    private int maxSize;
    private int total;
}
