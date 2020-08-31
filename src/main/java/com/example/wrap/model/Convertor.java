package com.example.wrap.model;

import java.lang.reflect.Field;

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

}
