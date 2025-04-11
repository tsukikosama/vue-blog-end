package com.weilai.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Boolean success;
    private String msg;
    private Object data;
    private Integer code;
    public static Result ok(){
        return new Result(true, null, null,200);
    }
    public static Result ok(Object data){
        return new Result(true, null, data,200);
    }
    public static Result ok(List<?> data, Long total){
        return new Result(true, null, data, 200);
    }
    public static Result fail(String errorMsg){
        return new Result(false, errorMsg, null, 500);
    }
    public static Result fail(Integer code,String errorMsg){
        return new Result(false, errorMsg,  null,code);
    }
}
