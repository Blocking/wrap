package com.example.wrap.course.concurrent;

import com.google.common.collect.Lists;
import org.junit.Test;

/**
 * @author 12232
 */
public class Class1 {
    private static long count =0;
    private void add10K(){
        int idx = 0;
        while (idx++ < 10_000 ){
            count += 1 ;
        }
    }
    public static long calc() throws InterruptedException {
        Class1 class1 = new Class1();
        Thread t1 = new Thread(() -> class1.add10K());
        Thread t2 = new Thread(() -> class1.add10K());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        return count;
    }

    @Test
    public void testCalc() throws InterruptedException {
        System.out.println(calc());
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(calc());
    }

}
