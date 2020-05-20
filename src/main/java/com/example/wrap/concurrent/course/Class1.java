package com.example.wrap.concurrent.course;

import lombok.SneakyThrows;
import org.junit.Test;

/**
 * @author 12232
 */
public class Class1 {
    private static long count =0;
    private void add10K(){
        int idx = 0;
        while (idx++ < 1_000_000_000 ){
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

    @SneakyThrows
    @Test
    public void testCalc(){
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(calc());
    }

}
