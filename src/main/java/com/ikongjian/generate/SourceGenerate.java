package com.ikongjian.generate;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static com.example.wrap.velocityTemplateEngine.VelocityTest.merge;

/**
 * 代码生成工具
 * @author zhangxiaoyu
 * @date 2020/12/1
 */
@Slf4j
public class SourceGenerate {

    VelocityContext ctx ;

     VelocityEngine ve;

    final String writePath = "/Users/zhangxiaoyu/tmp/";

    final String date = "Date";

    final String bigDecimal = "BigDecimal";

    @Before
    public void init(){
        ctx = new VelocityContext();
        ve = initVe();
    }

    @Test
    public void generate() throws ClassNotFoundException {
        final String modelClassName = "com.ikongjian.dim.model.ClientUpgrade";
        Class c = Class.forName(modelClassName);
        //base
        final String packageName = c.getPackage().getName();
        final String parentPackageName = packageName.substring(0, packageName.lastIndexOf("."));
        ctx.put("date", LocalDate.now().toString());
        ctx.put("author", System.getProperty("user.name"));
        ctx.put("package", parentPackageName);
        final String simpleName = c.getSimpleName();
        ctx.put("className", simpleName);
        // 首字母小写
        final String initials = simpleName.substring(0, 1);
        final String other = simpleName.substring(1);
        ctx.put("lowerClassName", StringUtils.lowerCase(initials).concat(other));
        ctx.put("oldProject", false);

        final ModelClassDocVO classDocVO = getFields(c);
        final List<FieldEntry> fields = classDocVO.getFields();

        ctx.put("classComments", classDocVO.getModelCommentText().trim());

        fields.forEach(field -> {
            if(date.equals(field.getFType())){
                ctx.put("hasDate", true);
            }
            if(bigDecimal.equals(field.getFType())){
                ctx.put("hasBigDecimal", true);
            }
        });
        ctx.put("fields", fields);
        generateDTO();
        generateQuery();
        generateConverter();
        generateManager();
        generateService();
        generateController();
    }

    private void generateController() {
        Template template = ve.getTemplate("velocityTemplate/java/controller.vm");
        merge(template,ctx, writePath +ctx.get("className")+"Controller.java");
    }

    private void generateService() {
        Template template = ve.getTemplate("velocityTemplate/java/service.vm");
        merge(template,ctx, writePath +ctx.get("className")+"Service.java");

        Template template1 = ve.getTemplate("velocityTemplate/java/serviceImpl.vm");
        merge(template1,ctx, writePath +ctx.get("className")+"ServiceImpl.java");
    }

    private void generateConverter() {
        Template template = ve.getTemplate("velocityTemplate/java/converter.vm");
        merge(template,ctx, writePath +ctx.get("className")+"Converter.java");
    }
    private void generateManager() {
        Template template = ve.getTemplate("velocityTemplate/java/manager.vm");
        merge(template,ctx, writePath +ctx.get("className")+"Manager.java");
        Template template1 = ve.getTemplate("velocityTemplate/java/managerImpl.vm");
        merge(template1,ctx, writePath +ctx.get("className")+"ManagerImpl.java");
    }

    private void generateQuery() {
        Template template = ve.getTemplate("velocityTemplate/java/edit_query.vm");
        merge(template,ctx, writePath +ctx.get("className")+"EditQuery.java");

        Template template1 = ve.getTemplate("velocityTemplate/java/add_query.vm");
        merge(template1,ctx, writePath +ctx.get("className")+"AddQuery.java");

        Template template2 = ve.getTemplate("velocityTemplate/java/page_query.vm");
        merge(template2,ctx, writePath +ctx.get("className")+"PageQuery.java");
    }

    private void generateDTO() {
        Template template = ve.getTemplate("velocityTemplate/java/dto.vm");
        merge(template,ctx, writePath +ctx.get("className")+"DTO.java");
    }

    private ModelClassDocVO getFields(Class c) {
        //设定为当前文件夹
        File directory = new File("");
        final String javaBeanFilePath = directory.getAbsolutePath() + "/src/main/java/" + c.getCanonicalName().replace(".", "/") + ".java";
        Doclet doclet = new Doclet(javaBeanFilePath);
        ModelClassDocVO modelClassDocVO = doclet.exec();
        log.info("类注释：" + modelClassDocVO.getModelCommentText());
        return modelClassDocVO;
    }

    public VelocityEngine initVe() {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        ve.init();
        return ve;
    }
}


