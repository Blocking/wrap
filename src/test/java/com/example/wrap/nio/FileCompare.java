package com.example.wrap.nio;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileCompare {


    @Test
    public void t() throws IOException {
        Path path1 = Paths.get("D:\\tmp\\compare\\test1\\影院信息查询_201912.xls");
        Path path2 = Paths.get("D:\\tmp\\compare\\test2\\影院信息查询_201912_new.xls");

        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(path1.toFile()));
        HSSFSheet sheetAt = workbook.getSheetAt(0);

        byte[] bytes1 = Files.readAllBytes(path1);
        byte[] bytes2 = Files.readAllBytes(path2);
        for (int i = 0; i < bytes1.length; i++) {
            if(bytes1[i] != bytes2[i]){
                System.out.println(i+"==="+bytes1[i]+"|||"+bytes2[i]);
            }
        }
        System.out.println(bytes1.length);
        System.out.println(bytes2.length);

    }


    public static void main(String[] args) throws IOException {
        Path path1 = Paths.get("D:\\tmp\\compare\\test1");
        Path path2 = Paths.get("D:\\tmp\\compare\\test2");
        Map<String,String> map = new HashMap<>();
        byte[] bytes1 = {};
        byte[] bytes2 = null;
        Files.list(path1).forEach(path -> {
            File file = path.toFile();
            try {
//                HashCode md5Hash = com.google.common.io.Files.hash(file, Hashing.md5());
                byte[] bytes = Files.readAllBytes(path);
//                map.put(path.getFileName().toString(),md5Hash.toString());
                System.out.println(path.getFileName().toString()+"::"+DigestUtils.md5Hex(bytes)+"::"+bytes.length);
                if(path.getFileName().toString().equalsIgnoreCase("影院信息查询_201911.xls")){
//                    bytes1 = bytes;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("=================================");
       Files.list(path2).forEach(path -> {
            File file = path.toFile();
            try {
//                HashCode md5Hash = com.google.common.io.Files.hash(file, Hashing.md5());
                String fileName = path.getFileName().toString();
//                String hashCode = map.get(fileName);

                byte[] bytes = Files.readAllBytes(path);
                System.out.println(path.getFileName().toString()+"::"+DigestUtils.md5Hex(bytes)+"::"+bytes.length);
                if(path.getFileName().toString().equalsIgnoreCase("影院信息查询_201911_new.xls")){
                    System.out.println(Arrays.toString(bytes));
                }
//                System.out.println(path.getFileName().toString()+"::"+md5Hash.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
