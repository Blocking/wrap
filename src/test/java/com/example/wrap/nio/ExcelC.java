package com.example.wrap.nio;

import com.google.common.io.Files;
import org.apache.commons.io.Charsets;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 对比俩个excel 行内容是否一致
 * 不一致的数据写到对应的txt文件中
 *  列内容要对齐
 */
public class ExcelC {

//    static Path path1 = Paths.get("D:\\tmp\\compare\\xufei\\1\\portal_channel_navigation.xlsx");
    static Path path1 = Paths.get("D:\\tmp\\compare\\xufei\\1\\Portal_ChannelNavigation.xlsx");
    static Path path2 = Paths.get("D:\\tmp\\compare\\xufei\\1\\Portal_ChannelNavigation.xlsx");

    // Create a DataFormatter to format and get each cell's value as String
    static DataFormatter dataFormatter = new DataFormatter();

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    public static void main(String[] args) throws IOException, InvalidFormatException {
        List<StringBuilder> rows = getRows(path1);
        List<StringBuilder> rows1 = getRows(path2);
        List<StringBuilder> errors = new ArrayList<>(1000);
        for (int i = 0; i < rows.size(); i++) {
            StringBuilder builder = rows.get(i);
            StringBuilder builder1 = rows1.get(i);
            if(!builder.toString().equals(builder1.toString())){
                errors.add(builder.append("================================》").append(builder1));
            }
        }
        String s = path1.getFileName() + "==compare==" + path2.getFileName();
        BufferedWriter bufferedWriter = Files.newWriter(new File("D:\\tmp\\compare\\xufei\\"+s + ".txt"), Charsets.UTF_8);
        for (StringBuilder error : errors) {
            bufferedWriter.write(error.toString());
            bufferedWriter.write("\n");
        }
        bufferedWriter.flush();
        bufferedWriter.close();

    }
    private static List<StringBuilder> getRows(Path path) throws IOException, InvalidFormatException {
        //创建 workbook (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(path.toFile());

        workbook.forEach(sheet -> {
            System.out.println("=> " + sheet.getSheetName());
        });
        Sheet sheet = workbook.getSheetAt(0);
        List<StringBuilder> rows = new ArrayList<>(10000);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            short lastCellNum = row.getLastCellNum();
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j <lastCellNum ; j++) {
                Cell cell = row.getCell(j);
                String cellValue = getCellValue(cell);
                builder.append(cellValue);
//                System.out.print(cellValue + "\t");
            }
            rows.add(builder);
//            System.out.println();
        }
        return rows;
    }

    private static String getCellValue(Cell cell) {
        String cellValue = "";
        if(cell == null){
            return "";
        }
        switch (cell.getCellTypeEnum()) {
//            case BOOLEAN:
//                System.out.print(cell.getBooleanCellValue());
//                break;
            case STRING:
                cellValue = dataFormatter.formatCellValue(cell);
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date dateCellValue = cell.getDateCellValue();
                    cellValue = sdf.format(dateCellValue);
                } else {
                    cellValue = dataFormatter.formatCellValue(cell);
                }
                break;
            /*case FORMULA:
                System.out.print(cell.getCellFormula());
                break;
            case BLANK:
                System.out.print("");
                break;*/
            default:
                System.out.print("");
        }

        return cellValue;
    }


}
