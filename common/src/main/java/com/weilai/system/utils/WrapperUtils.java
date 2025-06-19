package com.weilai.system.utils;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weilai.system.annotation.Query;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class WrapperUtils {
    public static <T> QueryWrapper<T> build(Object queryObj) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        if (queryObj == null) return wrapper;

        Field[] fields = queryObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Query query = field.getAnnotation(Query.class);
            if (query == null) continue;

            try {
                Object value = field.get(queryObj);
                if (value == null || (value instanceof String str && StrUtil.isBlank(str))) continue;

                String column = StrUtil.isNotBlank(query.column())
                        ? query.column()
                        : StrUtil.toUnderlineCase(field.getName());

                switch (query.type()) {
                    case EQ -> wrapper.eq(column, value);
                    case LIKE -> wrapper.like(column, value);
                    case GT -> wrapper.gt(column, value);
                    case GE -> wrapper.ge(column, value);
                    case LT -> wrapper.lt(column, value);
                    case LE -> wrapper.le(column, value);
                    case NE -> wrapper.ne(column, value);
                    case IN ->  {
                        if (value instanceof Collection<?> collection && !collection.isEmpty()) {
                            wrapper.in(column, collection);
                        }
                    }
                    case BETWEEN -> {
                        if (value instanceof LocalDateTime[] range && range.length == 2) {
                            wrapper.between(column, range[0], range[1]);
                        } else if (value instanceof List<?> rangeList && rangeList.size() == 2) {
                            wrapper.between(column, rangeList.get(0), rangeList.get(1));
                        } else {
                            throw new IllegalArgumentException("BETWEEN 查询条件必须是长度为2的数组或集合");
                        }
                    }
                    default -> {
                        // 可选：处理未覆盖的类型或抛异常
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return wrapper;
    }
}
