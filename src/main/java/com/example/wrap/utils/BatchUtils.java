package com.example.wrap.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * 批次执行工具类
 *
 * @author zhangxiaoyu
 * @date 2020/12/18
 */

@Slf4j
public class BatchUtils {

    /**
     * 批次执行的默认大小
     */
    public static final int DEFAULT_BATCH_LIMIT = 200;

    public static void main(String[] args) {
        final LongStream longStream = LongStream.rangeClosed(1, 201);
        final List<Long> longList = longStream.boxed().collect(Collectors.toList());
        batch((Consumer<List<Long>>) System.out::println, longList);
    }

    /**
     * 分批次消费 没有返回值
     *
     * @param consumer 消费行为
     * @param list     要消费的集合
     * @param <E>      消费集合内部元素的类型
     */
    public static <E> void batch(Consumer<List<E>> consumer, List<E> list) {
        batch(consumer, list, DEFAULT_BATCH_LIMIT);
    }

    /**
     * 分批次消费 没有返回值
     *
     * @param consumer 消费行为
     * @param list     要消费的集合
     * @param <E>      消费集合内部元素的类型
     * @param limit    批次大小
     */
    public static <E> void batch(Consumer<List<E>> consumer, List<E> list, int limit) {
        int size = list.size();
        final int cap = size / limit;
        log.info("count:{},limit:{},cap:{}", list.size(), limit, cap);
        if (size < limit) {
            log.info("size小于limit，执行一次！");
            consumer.accept(list);
        } else {
            for (int i = 0; i <= cap; i++) {
                log.info("执行次数:{}", i + 1);
                if (i == cap) {
                    consumer.accept(list.subList(i * limit, size));
                } else {
                    consumer.accept(list.subList(i * limit, (i + 1) * limit));
                }
            }
        }
    }

    public static <E, R> Collection<R> batch(Function<List<E>, List<R>> function, List<E> list) {
        return batch(function, list, ArrayList::new);
    }

    public static <E, R> Collection<R> batch(Function<List<E>, List<R>> function, List<E> list, Supplier<Collection<R>> collectionSupplier) {
        return batch(function, list, collectionSupplier, DEFAULT_BATCH_LIMIT);
    }

    /**
     * 分批次执行
     *
     * @param function 行为
     * @param list     参数集合
     * @param limit    拆分大小
     * @param <E>      参数集合元素类型
     * @param <R>      结果集合元素类型
     * @return 结果合集
     */
    public static <E, R> Collection<R> batch(Function<List<E>, List<R>> function, List<E> list, Supplier<Collection<R>> collectionSupplier, int limit) {
        int size = list.size();
        final int cap = size / limit;
        log.info("count:{},limit:{},cap:{}", list.size(), limit, cap);
        if (size < limit) {
            log.info("size小于limit，执行一次！");
            final List<R> result = function.apply(list);
            log.info("执行次数:1，获取数量：{}", result.size());
            return result;
        } else {
            Collection<R> collection = collectionSupplier.get();
            for (int i = 0; i <= cap; i++) {
                final Collection<R> result;
                if (i == cap) {
                    result = function.apply(list.subList(i * limit, size));
                } else {
                    result = function.apply(list.subList(i * limit, (i + 1) * limit));
                }
                log.info("执行次数:{}，获取数量：{}", i, result.size());
                collection.addAll(result);
                log.info("执行次数:{}，结果数量：{}", i, collection.size());
            }
            return collection;
        }
    }


}
