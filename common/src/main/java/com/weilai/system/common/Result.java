package com.weilai.system.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Boolean success;
    private String msg;
    private T data;
    private Integer code;

    public static <T> Result<T> ok() {
        return new Result<>(true, null, null, 200);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(true, null, data, 200);
    }

    public static <T> Result<List<T>> ok(List<T> data, Long total) {
        return new Result<>(true, null, data, 200);
    }

    public static <T> Result<T> fail(String errorMsg) {
        return new Result<>(false, errorMsg, null, 500);
    }

    public static <T> Result<T> fail(Integer code, String errorMsg) {
        return new Result<>(false, errorMsg, null, code);
    }
}
