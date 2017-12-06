package com.example.wrap.concurrent.executor;
/**
 * Created by E0441 on 2017/12/5.
 */

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author E0441
 *
线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 说明：Executors各个方法的弊端：
1）newFixedThreadPool和newSingleThreadExecutor:
  主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
2）newCachedThreadPool和newScheduledThreadPool:
  主要问题是线程数最大数是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM。

Positive example 1：
//org.apache.commons.lang3.concurrent.BasicThreadFactory
ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());



Positive example 2：
ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
.setNameFormat("demo-pool-%d").build();

//Common Thread Pool
ExecutorService pool = new ThreadPoolExecutor(5, 200,
0L, TimeUnit.MILLISECONDS,
new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

pool.execute(()-> System.out.println(Thread.currentThread().getName()));
pool.shutdown();//gracefully shutdown



Positive example 3：
<bean id="userThreadPool"
class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
<property name="corePoolSize" value="10" />
<property name="maxPoolSize" value="100" />
<property name="queueCapacity" value="2000" />

<property name="threadFactory" value= threadFactory />
<property name="rejectedExecutionHandler">
<ref local="rejectedExecutionHandler" />
</property>
</bean>
//in code
userThreadPool.execute(thread);
 **/
public class ExecutorsT {
//    static ExecutorService executorService = ExecutorsT.newCachedThreadPool(); 采用手动创建线程池 理由在上方
    ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
        new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

    public void sample1() throws ExecutionException, InterruptedException {
        final Callable<Boolean> task = ()->{
            System.out.println("123");
            return true;
        };
		Future<Boolean> is  = executorService.submit(task);
        System.out.println(is.get());
    }
    public void sample2(){
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

        //Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        pool.execute(()-> System.out.println(Thread.currentThread().getName()));
        pool.shutdown();//gracefully shutdown
    }

    /**
     * 使用CompletionService 这个类的poll和take方法会产生对应的Future实例，这些实例是按照任务完成的顺序排列的。
     * 在ExecutorCompletionService接口的平台实现中，
     * 为了实现该功能，每项任务都会包装在一个FutureTask中，FutureTask是Future的一个实现，它允许提供完成时的回调。
     * Future的回调行为是在ExecutorCompletionService中创建的，完成的任务会封装到一个队列中，供客户端询问时使用
     */
    @Test
    public void use1() throws InterruptedException, ExecutionException {
        CompletionService<Boolean> completionService =
                new ExecutorCompletionService<>(executorService);
        completionService.submit(()->{
            System.out.println("123");
            return true;
        } );
        Future<Boolean> fu = completionService.take();
        System.out.println(fu.get());
    }
    public void use2(){

    }
}
