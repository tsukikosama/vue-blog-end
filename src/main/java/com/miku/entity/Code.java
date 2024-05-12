package com.miku.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用redis代替了 邮箱 弃用
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Code {
    private String email;
    private String code;
}
