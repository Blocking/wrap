package com.example.wrap.concurrent;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author 12232
 * @date 2018/3/16
 *
 * BlockingQueue 的实现类
 * 1、ArrayBlockingQueue
 * 2、DelayQueue
 * 3、LinkedBlockingQueue
 * 4、PriorityBlockingQueue
 * 5、SynchronousQueue
 *
 */
public class BlockingQueueDemo {

    @Test
    public void demo1() throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue(1024);
        Runnable runnable = () -> {
            IntStream.rangeClosed(1,30).forEach(p -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    queue.put(p);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        };
        Runnable runnable1 = () -> {
            IntStream.rangeClosed(1,30).forEach(p -> {
                Object o = null;
                try {
                    while ((o=queue.take())!=null){
                        System.out.println(o);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        };
        new Thread(runnable).start();
        new Thread(runnable1).start();
        TimeUnit.SECONDS.sleep(40);
    }

}
