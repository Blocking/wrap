package com.example.wrap;


import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Convertor {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> cla = Class.forName("com.example.wrap.model.User");
        genToDTO(cla.getDeclaredFields());
        genToModel(cla.getDeclaredFields());
    }

    private static void genToDTO(Field[] declaredFields) {
        StringBuilder sb = new StringBuilder();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            String captureName = captureName(name);
            sb.append("dto.set"+captureName+"("+"model.get"+captureName+"());\n");
        }
        System.out.println(sb);
    }
    private static void genToModel(Field[] declaredFields) {
        StringBuilder sb = new StringBuilder();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            String captureName = captureName(name);
            sb.append("model.set"+captureName+"("+"dto.get"+captureName+"());\n");
        }
        System.out.println(sb);
    }

    /**
     * 将字符串的首字母转大写
     * @param str 需要转换的字符串
     * @return
     */
    private static String captureName(String str) {
        // 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
        char[] cs=str.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }

    @Test
    public void t(){
        String jsonString = "[\n" +
                "    {\n" +
                "        \"md5\": \"2c91e8bf9da42bcc5984cbf2850c1e6a\",\n" +
                "        \"name\": \"Aliyun.OSS.dll\",\n" +
                "        \"path\": \"\",\n" +
                "        \"pathWithName\": \"/Aliyun.OSS.dll\",\n" +
                "        \"size\": 258048,\n" +
                "        \"url\": \"http://ikj-x-test.oss-cn-beijing.aliyuncs.com/hades/client/BIM/LAUNCH/1.0.5/Aliyun.OSS.dll\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"md5\": \"913cdd59e4fea514a4011e48699c197f\",\n" +
                "        \"name\": \"BIMLauncher.exe\",\n" +
                "        \"path\": \"\",\n" +
                "        \"pathWithName\": \"/BIMLauncher.exe\",\n" +
                "        \"size\": 559616,\n" +
                "        \"url\": \"http://ikj-x-test.oss-cn-beijing.aliyuncs.com/hades/client/BIM/LAUNCH/1.0.5/BIMLauncher.exe\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"md5\": \"e98f4223a78e90e09c368a37ff3c19c6\",\n" +
                "        \"name\": \"Launch.ini\",\n" +
                "        \"path\": \"\",\n" +
                "        \"pathWithName\": \"/Launch.ini\",\n" +
                "        \"size\": 67,\n" +
                "        \"url\": \"http://ikj-x-test.oss-cn-beijing.aliyuncs.com/hades/client/BIM/LAUNCH/1.0.5/Launch.ini\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"md5\": \"5afda7c7d4f7085e744c2e7599279db3\",\n" +
                "        \"name\": \"Newtonsoft.Json.dll\",\n" +
                "        \"path\": \"\",\n" +
                "        \"pathWithName\": \"/Newtonsoft.Json.dll\",\n" +
                "        \"size\": 662528,\n" +
                "        \"url\": \"http://ikj-x-test.oss-cn-beijing.aliyuncs.com/hades/client/BIM/LAUNCH/1.0.5/Newtonsoft.Json.dll\"\n" +
                "    }\n" +
                "]";
        InputStream jsonStream = new ByteArrayInputStream(jsonString.getBytes());
        String jsonKey = "config_version" + ".json";
        final Path path = Paths.get("/Users/zhangxiaoyu/tmp/"+jsonKey);
        try {
            final File file = path.toFile();
            FileUtils.writeByteArrayToFile(file,jsonString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
