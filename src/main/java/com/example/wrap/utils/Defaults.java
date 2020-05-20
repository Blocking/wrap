package com.example.wrap.utils;

import com.google.common.reflect.Invokable;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 工具类 给bean 中为null的字段设置默认值
 */
public final class Defaults {
    private Defaults() {}

    private static final Map<Class<?>, Object> DEFAULTS;

    static {
        // Only add to this map via put(Map, Class<T>, T)
        Map<Class<?>, Object> map = new HashMap<Class<?>, Object>();
        put(map, boolean.class, false);
        put(map, char.class, '\0');
        put(map, byte.class, (byte) 0);
        put(map, short.class, (short) 0);
        put(map, int.class, 0);
        put(map, long.class, 0L);
        put(map, float.class, 0f);
        put(map, double.class, 0d);

        put(map, Boolean.class, false);
        put(map, Character.class, '\0');
        put(map, Byte.class, (byte) 0);
        put(map, Short.class, (short) 0);
        put(map, Integer.class, 0);
        put(map, Long.class, 0L);
        put(map, Float.class, 0f);
        put(map, Double.class, 0d);
        put(map, Date.class, new Date());
        put(map, String.class, "");

        DEFAULTS = Collections.unmodifiableMap(map);
    }

    private static <T> void put(Map<Class<?>, Object> map, Class<T> type, T value) {
        map.put(type, value);
    }

    /**
     * Returns the default value of {@code type} as defined by JLS --- {@code 0} for numbers, {@code
     * false} for {@code boolean} and {@code '\0'} for {@code char}. For non-primitive types and
     * {@code void}, null is returned.
     */
    public static <T> T defaultValue(Class<T> type) {
        // Primitives.wrap(type).cast(...) would avoid the warning, but we can't use that from here
        @SuppressWarnings("unchecked") // the put method enforces this key-value relationship
                T t = (T) DEFAULTS.get(checkNotNull(type));
        return t;
    }
    public static void defaults(Object dto) {
        Class<?> dtoClass = dto.getClass();
        Map<String, ? extends Invokable<?, Object>> invokableMap = Arrays.stream(dtoClass.getDeclaredMethods())
                .map(method -> Invokable.from(method))
                .collect(Collectors.toMap(Invokable::getName, Function.identity()));
        Arrays.stream(dtoClass.getDeclaredFields()).forEach(field -> {
            Invokable invokable = getMethod("get", field.getName(), invokableMap);
            try {
                Object invoke = invokable.invoke(dto, new Object[]{});
                if (invoke == null) {
                    Invokable set_invokable = getMethod("set", field.getName(), invokableMap);
                    set_invokable.invoke(dto, Defaults.defaultValue(field.getType()));
                }
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    private static Invokable getMethod(String prefix, String name, Map<String, ? extends Invokable<?, Object>> invokableMap) {
        if (null != name && !"".equals(name.trim())) {
            char[] cs = name.toCharArray();
            cs[0] -= 32;
            String methodS = prefix + String.valueOf(cs);
            return invokableMap.get(methodS);
        } else {
            throw new IllegalArgumentException("属性值不能为空或者空字符串");
        }
    }
}
