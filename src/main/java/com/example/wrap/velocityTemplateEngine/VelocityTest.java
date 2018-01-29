package com.example.wrap.velocityTemplateEngine;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by 12232 on 2018/1/27.
 */
public class VelocityTest {


    @Test
    public void test1() throws ClassNotFoundException {
        Class c = Class.forName("com.example.wrap.velocityTemplateEngine.User");
        Arrays.stream(c.getDeclaredFields()).forEach(field -> {
            System.out.println(field.getName());
        });
        String rootPath = VelocityTest.class.getClassLoader().getResource("").getFile() + "../../src/main";
        System.out.println(rootPath);

        System.out.println(VelocityTest.class.getResource(""));
        // class path根目录 (指定资源名，可以获取根目录下面的资源)
        System.out.println(VelocityTest.class.getResource("/"));
        // path是从ClassPath根下获取
        System.out.println(VelocityTest.class.getClassLoader().getResource(""));
        // path不能以'/'开头
        System.out.println(VelocityTest.class.getClassLoader().getResource("/"));
    }

}
