package com.example.wrap.excel;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class ExcelReader {
    public static final String SAMPLE_XLSX_FILE_PATH = "D:\\temp\\excel\\environmental2018_12_27白主任.xlsx";
    public static final String MODIFY_XLSX_FILE_PATH = "D:\\temp\\excel\\modify.xlsx";
    public static final String SOURCE_XLSX_FILE_PATH = "D:\\temp\\excel\\模板（区域小组分组以及导入-导出模板）2018.12.26-鲁.xlsx";

    public static Set<String> sheetName = new HashSet<>(3);

    static {
        sheetName.add("加油站任务导入&导出模板");
        sheetName.add("黑加油站任务导入&导出模板");
        sheetName.add("内部油库任务导入&导出模板");
        sheetName.add("加油站基础信息导入&导出模板");
        sheetName.add("黑加油站基础信息导入&导出模板");
        sheetName.add("内部油库基础信息导入&导出模板");
    }


    public static void main(String[] args) throws IOException, InvalidFormatException {
//        Map<String,Coordinate> map = getData();
        Map<String, String> map1 = getNatureData();
//        modifyExistingWorkbook(map);
        modifyNatureWorkbook(map1);
        System.out.println(map1.keySet().size());

    }

    private static void modifyNatureWorkbook(Map<String, String> map) throws IOException, InvalidFormatException {
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(MODIFY_XLSX_FILE_PATH));

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
        workbook.forEach(sheet -> {

            String sheetName = sheet.getSheetName();
            log.info("sheetName:{}", sheetName);
            if (!"加油站任务导入&导出模板".equals(sheet.getSheetName())) {
                return;
            }

            System.out.println("=> " + sheet.getSheetName() + "::" + sheet.getPhysicalNumberOfRows());

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                //加油站名称
                Cell cell = row.getCell(3);
                String name = dataFormatter.formatCellValue(cell);

                Cell codeCell = row.getCell(4);
                String code = dataFormatter.formatCellValue(codeCell);
                //地址
                Cell cell4 = row.getCell(5);
                String address = dataFormatter.formatCellValue(cell4);


                String key = name.concat(code).concat(address);
                if (map.containsKey(key)) {
                    String nature = map.get(key);
                    Cell natureCell = row.getCell(6);
                    if(natureCell == null){
                        natureCell = row.createCell(6);
                    }
                    natureCell.setCellValue(nature);
                }

            }

        });

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\temp\\excel\\existing-spreadsheet2.xlsx");
        workbook.write(fileOutputStream);
        // Closing the workbook
        workbook.close();
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private static Map<String, String> getNatureData() throws IOException, InvalidFormatException {
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(SOURCE_XLSX_FILE_PATH));

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        Map<String, String> map = new HashMap<>();
        workbook.forEach(sheet -> {
            if (!"加油站基础信息导入&导出模板".equals(sheet.getSheetName())) {
                return;
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                //加油站名称
                Cell nameCell = row.getCell(3);
                String name = dataFormatter.formatCellValue(nameCell);
                //地址
                Cell codeCell = row.getCell(4);
                String code = dataFormatter.formatCellValue(codeCell);
                //地址
                Cell adressCell = row.getCell(5);
                String address = dataFormatter.formatCellValue(adressCell);

                //性质
                Cell natureCell = row.getCell(6);
                String nature = dataFormatter.formatCellValue(natureCell);
                map.put(name.concat(code).concat(address), nature);
            }

        });
        // Closing the workbook
        workbook.close();
        return map;
    }

    private static void modifyExistingWorkbook(Map<String, Coordinate> map) throws IOException, InvalidFormatException {
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(MODIFY_XLSX_FILE_PATH));

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
        workbook.forEach(sheet -> {

            String sheetName = sheet.getSheetName();
            log.info("sheetName:{}", sheetName);
          /*  if (!ExcelReader.sheetName.contains(sheet.getSheetName())) {
                return;
            }*/

            System.out.println("=> " + sheet.getSheetName() + "::" + sheet.getPhysicalNumberOfRows());

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                //加油站名称
                Cell cell = row.getCell(3);
                String name = dataFormatter.formatCellValue(cell);
                String address = "";
               /* if ("内部油库基础信息导入&导出模板".equals(sheetName)) {
                    //地址
                    Cell cell4 = row.getCell(6);
                    address = dataFormatter.formatCellValue(cell4);
                } else {*/
                    //地址
                    Cell cell4 = row.getCell(5);
                    address = dataFormatter.formatCellValue(cell4);
//                }


                String key = name.concat(address);
                if (map.containsKey(key)) {
                    Coordinate coordinate = map.get(key);
                   /* if ("加油站基础信息导入&导出模板".equals(sheetName)) {
                        row.getCell(9).setCellValue(coordinate.getLongitude());
                        row.getCell(10).setCellValue(coordinate.getLatitude());
                    } else if ("黑加油站基础信息导入&导出模板".equals(sheetName)) {
                        row.getCell(8).setCellValue(coordinate.getLongitude());
                        row.getCell(9).setCellValue(coordinate.getLatitude());
                    } else if ("内部油库基础信息导入&导出模板".equals(sheetName)) {
                        row.createCell(7).setCellValue(coordinate.getLongitude());
                        row.createCell(8).setCellValue(coordinate.getLatitude());
                    } else if ("加油站任务导入&导出模板".equals(sheetName)) {
                        row.createCell(7).setCellValue(coordinate.getLongitude());
                        row.createCell(8).setCellValue(coordinate.getLatitude());
                    } else if ("黑加油站任务导入&导出模板".equals(sheetName)) {
                        row.createCell(7).setCellValue(coordinate.getLongitude());
                        row.createCell(8).setCellValue(coordinate.getLatitude());
                    } else */if ("内部油库任务导入&导出模板".equals(sheetName)) {
                        row.getCell(6).setCellValue(coordinate.getLongitude());
                        row.getCell(7).setCellValue(coordinate.getLatitude());
                    } else {
                        row.getCell(7).setCellValue(coordinate.getLongitude());
                        row.getCell(8).setCellValue(coordinate.getLatitude());
//                        log.info("未对比到的数据:{} ", key);
                    }
                }

            }

        });

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\temp\\excel\\existing-spreadsheet2.xlsx");
        workbook.write(fileOutputStream);
        // Closing the workbook
        workbook.close();
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private static Map<String, Coordinate> getData() throws IOException, InvalidFormatException {
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
        // 3. Or you can use a Java 8 forEach with lambda
        System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
        Map<String, Coordinate> coordinateMap = new HashMap<>();
        workbook.forEach(sheet -> {
            System.out.println("=> " + sheet.getSheetName() + "::" + sheet.getPhysicalNumberOfRows());
            System.out.println("=> " + sheet.getSheetName() + ":last:" + sheet.getLastRowNum());

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                //加油站名称
                Cell cell = row.getCell(3);
                String name = dataFormatter.formatCellValue(cell);
                //地址
                Cell cell4 = row.getCell(4);
                String address = dataFormatter.formatCellValue(cell4);

                //经度
                Cell cell1 = row.getCell(5);
                String longitude = dataFormatter.formatCellValue(cell1);

                //纬度
                Cell cell2 = row.getCell(6);
                String latitude = dataFormatter.formatCellValue(cell2);
                if (coordinateMap.containsKey(name)) {
                    log.info("加油站名称:{} ===经度：{},纬度:{}", name, longitude, latitude);
                }
                coordinateMap.put(name.concat(address), Coordinate.builder().latitude(latitude).longitude(longitude).build());
            }

        });

        // Closing the workbook
        workbook.close();
        return coordinateMap;
    }


}

@Data
@Builder
class Coordinate {
    //经度
    private String longitude;
    //纬度
    private String latitude;
}
