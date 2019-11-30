package com.example.wrap.excel;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 12232
 */
public class ThemeScreenExcel {

    private static final String SOURCE_XLSX_FILE_PATH = "D:\\temp\\excel\\全国艺联加盟影院影厅信息.xls";
    private static final String DES_XLSX_FILE_PATH = "D:\\temp\\excel\\dest.xls";

    private static String[] columns = {"序号", "省份", "所在区/县", "影院编码", "影院名称", "影厅编号", "座位数", "影厅业务类型"};

    @Test
    public void wirteData() throws IOException, InvalidFormatException {
        List<List<String>> lists = readData();

        List<Screen> screens = convert(lists);

        // Create a Workbook
        Workbook workbook = new HSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat,
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Employee");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);
        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }


        for (int i = 1; i <= screens.size(); i++) {
            // Create a Row
            Row row = sheet.createRow(i);
            Screen screen = screens.get(i-1);
            Cell cell = row.createCell(0);
            cell.setCellValue(i);

            Cell cell1 = row.createCell(1);
            cell1.setCellValue(screen.getProvince());

            Cell cell2 = row.createCell(2);
            cell2.setCellValue(screen.getCountry());

            Cell cell3 = row.createCell(3);
            cell3.setCellValue(screen.getCinemaCode());

            Cell cell4 = row.createCell(4);
            cell4.setCellValue(screen.getCinemaName());

            Cell cell5 = row.createCell(5);
            cell5.setCellValue(screen.getScreenCode());

            Cell cell6 = row.createCell(6);
            cell6.setCellValue(screen.getSeatCounts());

            Cell cell7 = row.createCell(7);
            cell7.setCellValue(screen.getBusinessType());
        }

        // Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(DES_XLSX_FILE_PATH);
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();

    }

    private static final String NULL = "(空)";

    private List<Screen> convert(List<List<String>> lists) {
        List<Screen> screens = Lists.newArrayList();
        lists.stream().forEach(strings -> {
            Screen screen  = new Screen(strings.get(0),strings.get(1),strings.get(2),strings.get(3),strings.get(4),strings.get(5),"艺术影厅");
            screens.add(screen);
            String screenCode = strings.get(6);
            if (!NULL.equals(screenCode)){
                Screen screen1 = new Screen();
                BeanUtils.copyProperties(screen,screen1);
                screen1.setScreenCode(screenCode);
                screen1.setSeatCounts(strings.get(7));
                screens.add(screen1);
            }
            String nextScreenCode = strings.get(8);
            if (!NULL.equals(nextScreenCode)){
                Screen screen1 = new Screen();
                BeanUtils.copyProperties(screen,screen1);
                screen1.setScreenCode(nextScreenCode);
                screen1.setSeatCounts(strings.get(9));
                screens.add(screen1);
            }
        });
        return screens;
    }


    public List<List<String>> readData() throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(new File(SOURCE_XLSX_FILE_PATH));
        List<List<String>> alllist = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        System.out.println("lastRowNum:"+lastRowNum);
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            final List<String> list = new ArrayList<>();
            short lastCellNum = row.getLastCellNum();
            System.out.print("row:"+i+"||lastCellNum:"+lastCellNum+"     ");
            for (int j = 1; j < lastCellNum; j++) {
                String stringCellValue = row.getCell(j).getStringCellValue();
                System.out.print(stringCellValue+"||");
                list.add(stringCellValue);
            }
            System.out.println("");
            alllist.add(list);
        }
        return alllist;
    }
}
@Data
@NoArgsConstructor
@AllArgsConstructor
class Screen{
    private String province;
    private String country;
    private String cinemaCode;
    private String cinemaName;
    private String screenCode;
    private String seatCounts;
    private String businessType;
}
