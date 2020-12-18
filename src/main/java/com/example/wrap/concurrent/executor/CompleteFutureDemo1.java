package com.example.wrap.concurrent.executor;

import com.example.wrap.jdk8.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Created by 12232 on 2017/12/22.
 */
@Slf4j
public class CompleteFutureDemo1 {

    /////////////////将两个CompletableFutures组合在一起//////////////////////////

    CompletableFuture<User> getUserDetail(String password){
        return CompletableFuture.supplyAsync(() -> new User("tom",password));
    }
    CompletableFuture<String> getCreditRating(User user){
        return CompletableFuture.supplyAsync(() -> user.getPassword());
    }

    /**
     * 使用thenApply()方法造成的最终结果是一个嵌套的CompletableFuture
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void sample1() throws ExecutionException, InterruptedException {
        CompletableFuture<CompletableFuture<String>> result = getUserDetail("123").thenApply(user -> getCreditRating(user));
        System.out.println(result.get().get());
    }

    /**
     * 区别于上个例子  使用thenCompose方法 会返回一个顶级的CompletableFuture
     * thenCompose方法 是俩个未来相互依赖的关系 存在顺序执行关系
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void sample2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> result = getUserDetail("456").thenComposeAsync(user -> getCreditRating(user));
        log.info(result.get());
    }

    /**
     * 当thenCompose()两个期货在两个期货相互依赖的情况下thenCombine()被用来组合使用的时候，
     * 当你希望两个期货独立运行并且在两个期货完成之后做一些事情的时候
     * thenCombine()当两个期货都完成时，传入的回调函数将被调用
     * thenCombine() 结合俩个不依赖的未来  这俩个未来不存在依赖关系 不存在顺序执行关系
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void sample3() throws ExecutionException, InterruptedException {
        log.info("Retrieving weight.");
        CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
            try {
                log.info("weight");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 65.0;
        });

        log.info("Retrieving height.");
        CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
            try {
                log.info("height");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            final int i = 23 / 0;
            return 177.8;
        }).exceptionally(throwable -> {
            System.out.println(throwable.getCause()); // returns a throwable back
            return null;
        });

   /*     log.info("Calculating BMI.");
        CompletableFuture<Double> combinedFuture = weightInKgFuture
                .thenCombine(heightInCmFuture, (weightInKg, heightInCm) -> {
                    log.info("weight/height");
                    Double heightInMeter = heightInCm/100;
                    return weightInKg/(heightInMeter*heightInMeter);
                });

        log.info("Your BMI is - " + combinedFuture.get());*/

        final CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(weightInKgFuture, heightInCmFuture)
                .exceptionally(throwable -> {
                    System.out.println(throwable.getCause()); // returns a throwable back
                    return null;
                });

        voidCompletableFuture.join();

        System.out.println("weightInKgFuture:::"+weightInKgFuture.join());
        System.out.println("heightInCmFuture:::"+heightInCmFuture.join());

    }

    @Test
    public void sample4(){
        //TODO 1. CompletableFuture.allOf（）

        System.out.println(3/0);
    }



}
