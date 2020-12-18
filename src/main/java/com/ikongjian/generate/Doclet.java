package com.ikongjian.generate;

import com.google.common.collect.Lists;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.RootDoc;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangxiaoyu
 * @date 2020/12/1
 */
@Slf4j
public class Doclet {


    private static RootDoc rootDoc;
    private String javaBeanFilePath;

    public static boolean start(RootDoc root) {
        rootDoc = root;
        return true;
    }

    public Doclet(String javaBeanFilePath) {
        this.javaBeanFilePath = javaBeanFilePath;
    }

    public ModelClassDocVO exec() {
        ModelClassDocVO modelClassDocVO = new ModelClassDocVO();
        com.sun.tools.javadoc.Main.execute(new String[]{"-doclet", Doclet.class.getName(), "-docletpath",
                Doclet.class.getResource("/").getPath(), "-encoding", "utf-8", javaBeanFilePath});
        ClassDoc[] classes = rootDoc.classes();

        if (classes == null || classes.length == 0) {
            log.warn(javaBeanFilePath + " 无ClassDoc信息");
            return modelClassDocVO;
        }


        ClassDoc classDoc = classes[0];
        // 获取类的名称
        modelClassDocVO.setModelClassName(classDoc.name());
        // 获取类的注释
        String classComment = classDoc.getRawCommentText();
        String spitStr = "\n";
        for (String msg : classComment.split(spitStr)) {
            if (!msg.trim().startsWith("@") && msg.trim().length() > 0) {
                modelClassDocVO.setModelCommentText(msg);
                break;
            }
        }

        List<FieldEntry> fieldEntryList = Lists.newArrayList();

        // 获取属性名称和注释
        FieldDoc[] fields = classDoc.fields(false);
        final List<FieldDoc> fieldDocs = Arrays.stream(fields).filter(fieldDoc -> !"serialVersionUID".equals(fieldDoc.name())).collect(Collectors.toList());
        for (FieldDoc field : fieldDocs) {
            fieldEntryList.add(new FieldEntry(field.name(), field.type().typeName(), field.commentText()));
        }

        modelClassDocVO.setFields(fieldEntryList);
        return modelClassDocVO;
    }

    // 测试一下
    public static void main(String[] args) {
        Doclet doclet = new Doclet(
                "/Users/zhangxiaoyu/IdeaProjects/wrap/src/main/java/com/example/wrap/velocityTemplateEngine/ArtificialAuxiliaryMaterialRelation.java");
        ModelClassDocVO modelClassDocVO = doclet.exec();
        log.info("类注释：" + modelClassDocVO.getModelCommentText());
        log.info("属性字段注释如下：");
        modelClassDocVO.getFields().forEach(System.out::println);
    }
}
