package com.example.wrap.nio;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelCompare {

    public static void main(String[] args) throws Exception {
        try {
            // 同时支持Excel 2003、2007
            File excelFile = new File("D:\\tmp\\compare\\xufei\\1\\portal_channel_navigation.xlsx");
//            FileInputStream in = new FileInputStream(excelFile);
            Workbook workbook = WorkbookFactory.create(excelFile);
            /**
             * 设置当前excel中sheet的下标：0开始
             */
            List<String> first_stringsList = new ArrayList<>();
            Sheet sheet = workbook.getSheetAt(0);
            StringBuilder stringBuilder = new StringBuilder();
            for (Row row : sheet) {
                for (int i = 1; i < row.getLastCellNum(); i++) {
                    stringBuilder.append(row.getCell(i));
                }
                first_stringsList.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }
            List<String> second_stringsList = getData();
            System.out.println("first: " + first_stringsList.size());
            System.out.println("second: " + second_stringsList.size());
            if (second_stringsList.size() > 0) {
                for (String str : first_stringsList) {
                    boolean flag = second_stringsList.contains(str);
                    if (!flag) {
                        System.out.println(str);
                        System.out.println();
                    }
                }
            }
            System.out.println("对比完成！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getData(){
        try {
            // 同时支持Excel 2003、2007
            File excelFile = new File("D:\\tmp\\compare\\xufei\\1\\Portal_ChannelNavigation.xlsx");
//            FileInputStream in = new FileInputStream(excelFile);
//            Workbook workbook = new HSSFWorkbook(in);
            Workbook workbook = WorkbookFactory.create(excelFile);
            /**
             * 设置当前excel中sheet的下标：0开始
             */
            List<String> first_stringsList = new ArrayList<>();
            Sheet sheet = workbook.getSheetAt(0);
            StringBuilder stringBuilder = new StringBuilder();
            for (Row row : sheet) {
                for (int i = 1; i < row.getLastCellNum(); i++) {
                    stringBuilder.append(row.getCell(i));
                }
                first_stringsList.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }
            return first_stringsList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
