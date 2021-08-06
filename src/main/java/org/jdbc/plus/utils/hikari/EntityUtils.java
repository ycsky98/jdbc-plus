package org.jdbc.plus.utils.hikari;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

import org.jdbc.plus.annotation.Param;

/**
 * @author yangcong
 * 
 *         实体工具类
 */
public class EntityUtils {

    /**
     * 将Map转换成实体
     * 
     * @param <T>
     * @param map
     * @param entity
     * @param construCtorargs
     * @return
     */
    public static <T> T mapToT(Map<String, Object> map, Class<T> entity, Object... construCtorargs) throws Exception {
        Class<?>[] types = new Class<?>[construCtorargs.length];
        for (int i = 0; i < construCtorargs.length; i++) {
            types[i] = construCtorargs[i].getClass();
        }
        // 构建出对象
        T t = entity.getDeclaredConstructor(types).newInstance(construCtorargs);

        Field[] fields = t.getClass().getDeclaredFields();
        fields = Arrays.stream(fields).filter(field -> field.isAnnotationPresent(Param.class)).toArray(Field[]::new);

        Object val = null;
        for (int i = 0; i < fields.length; i++) {
            val = map.get(fields[i].getName());
            fields[i].setAccessible(true);
            // val 不为空值 并且 类型一致
            if (val != null) {
                fields[i].set(t, val);
            } else {
                fields[i].set(t, val);
            }
        }
        return t;
    }

}
