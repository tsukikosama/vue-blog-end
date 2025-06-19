package com.weilai.system.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Query {
    Type type() default Type.EQ;
    String column() default ""; // 可选：数据库字段名，默认驼峰转下划线

    enum Type {
        EQ, LIKE, GT, GE, LT, LE, NE, IN, BETWEEN
    }
}
