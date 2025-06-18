package com.weilai.system.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DictField {

    /**
     * 标签字段名
     *
     * @return 标签字段名
     */
    String labelKey() default "name";

    /**
     * 值字段名
     *
     * @return 值字段名
     */
    String valueKey() default "id";

    /**
     * 额外信息字段名
     *
     * @return 额外信息字段名
     */
    String[] extraKeys() default {};
}