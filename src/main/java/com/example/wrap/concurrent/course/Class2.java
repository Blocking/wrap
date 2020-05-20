package com.example.wrap.concurrent.course;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

// 以下代码来源于【参考1】
@Slf4j
class Class2 {
    static int x = 0;
    boolean v = false;
    public void writer() {
        log.info("writer========");
        x = 42;
        v = true;
        log.info("writer========finish");
    }
    public void reader() {
        log.info("reader========");
        if (v == true) {
            // 这里x会是多少呢？
            log.info("reader========b:X:[{}]",x);
        }
        log.info("reader========f:X:[{}]",x);
    }

    public static void main(String[] args) throws InterruptedException {
        Class2 class2  = new Class2();
        Thread t1 = new Thread(() -> class2.writer());
        Thread t2 = new Thread(() -> class2.reader());
        t1.start();
        t2.start();
        System.out.println(x);
        t2.join();
        t1.join();
        System.out.println(x);
    }
}
