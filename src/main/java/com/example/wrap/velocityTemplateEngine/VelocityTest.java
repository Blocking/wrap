package com.example.wrap.velocityTemplateEngine;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    Stream<String> templates = Stream.of(
            "velocityTemplate/add.vm","velocityTemplate/edit.vm",
            "velocityTemplate/list.vm","velocityTemplate/read.vm"
            );


    @Test
    public void test2() throws Exception {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        ve.init();


        VelocityContext ctx = new VelocityContext();

        Class c = Class.forName("com.example.wrap.velocityTemplateEngine.User");
        List<String> fields = Arrays.stream(c.getDeclaredFields()).map(field -> field.getName()).collect(Collectors.toList());
        ctx.put("fields", fields);
        ctx.put("fieldSize",fields.size());
        ctx.put("model","User");
        String rootPath = "D:\\test\\";

        templates.forEach(ts->{
            Template template = null;
            try {
                template = ve.getTemplate(ts);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String fileName = ts.substring(ts.lastIndexOf("/")+1,ts.lastIndexOf("."));
            merge(template,ctx,rootPath+fileName+".html");
        });

    }

    @Test
    public void test3() throws Exception {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        ve.init();


        VelocityContext ctx = new VelocityContext();

        Class c = Class.forName("com.example.wrap.velocityTemplateEngine.User");
        List<String> fields = Arrays.stream(c.getDeclaredFields()).map(field -> field.getName()).collect(Collectors.toList());
        ctx.put("fields", fields);
        ctx.put("fieldSize",fields.size());
        ctx.put("model","User");
        String rootPath = "D:\\test\\";
        Template template = ve.getTemplate("velocityTemplate/controller.vm");
        merge(template,ctx,rootPath+"controller.js");

    }

    private static void merge(Template template, VelocityContext ctx, String path) {

        try(PrintWriter writer =new PrintWriter(path)){
            template.merge(ctx, writer);
            writer.flush();
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        } catch (MethodInvocationException e) {
            e.printStackTrace();
        } catch (ParseErrorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
