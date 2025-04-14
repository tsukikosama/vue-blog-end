package com.weilai.common;

import lombok.Data;

@Data
public class Page {
    private int current;
    private int maxSize;
    private int total;
}
