package com.example.wrap.company.excel;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 12232
 */
@Slf4j
public class ThemeScreenExcelReader {

    public static void main(String[] args) throws Exception {

//        final Map<String, CinemaInfo> cinemaInfoMap =  getCinemaInfo();
//        final Map<String, Integer> screenInfoMap =  getScreenfo();

        final Workbook workbook = WorkbookFactory.create(new File("D:\\tmp\\人民院线影厅导入结果校验 (4).xls"));
        List<ThemeScreen> list = new ArrayList<>();
        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
        final Sheet sheet = workbook.getSheet("模板");
        int index = 1;
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            final Row row = sheet.getRow(i);
            final String errorString = dataFormatter.formatCellValue(row.getCell(9));
            if(StringUtils.isBlank(errorString)){
                continue;
            }
            final String provinceName = dataFormatter.formatCellValue(row.getCell(1));
            final String cityName = dataFormatter.formatCellValue(row.getCell(2));
            final String cinemaCode = dataFormatter.formatCellValue(row.getCell(3));
            final String cienmaName = dataFormatter.formatCellValue(row.getCell(4));
             String scrennCode = dataFormatter.formatCellValue(row.getCell(5));
             scrennCode = StringUtils.leftPad(scrennCode,16,'0');

            ThemeScreen screen = new ThemeScreen(cinemaCode, cienmaName, scrennCode);
            screen.setCountry(cityName);
            screen.setProvince(provinceName);
            screen.setNum(index++ +"");
            screen.setErrorString(errorString);
            list.add(screen);
           /* String scrennCode1 = dataFormatter.formatCellValue(row.getCell(7));
            if (StringUtils.isNotBlank(scrennCode1)) {
                scrennCode1 = org.springframework.util.StringUtils.trimLeadingCharacter(scrennCode1, '0');
                final ThemeScreen screen1 = (ThemeScreen) screen.clone();
                screen1.setScreenCode(scrennCode1);
                list.add(screen1);
            }

            String scrennCode2 = dataFormatter.formatCellValue(row.getCell(8));
            if (StringUtils.isNotBlank(scrennCode2)) {
                scrennCode2 =  org.springframework.util.StringUtils.trimLeadingCharacter(scrennCode2, '0');
                final ThemeScreen screen2 = (ThemeScreen) screen.clone();
                screen2.setScreenCode(scrennCode2);
                list.add(screen2);
            }
*/
        }

        write(list);
    }

    private static void write(List<ThemeScreen> list) throws IOException {
        String path = "D:\\tmp\\screen.xls";
        InputStream inputStream = new FileInputStream(path);
        Workbook workbook = new HSSFWorkbook(inputStream); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat,
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */

        // Create a Sheet
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 0; i < list.size(); i++) {
            final Row row = sheet.createRow(i + 2);
            final ThemeScreen screen = list.get(i);
            row.createCell(0).setCellValue(screen.getNum());
            row.createCell(1).setCellValue(screen.getProvince());
            row.createCell(2).setCellValue(screen.getCountry());
            row.createCell(3).setCellValue(screen.getCinemaCode());
            row.createCell(4).setCellValue(screen.getCinemaName());
            row.createCell(5).setCellValue(screen.getScreenCode());
            row.createCell(7).setCellValue("艺术影厅");
            row.createCell(9).setCellValue(screen.getErrorString());
        }
        OutputStream outputStream = new FileOutputStream("D:\\tmp\\screen1.xls");
        workbook.write(outputStream);
        workbook.close();
    }

    private static Map<String, Integer> getScreenfo() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        final Connection connection;
        Statement stmt;
        Map<String, Integer> screenInfoHashMap = new HashMap<>();
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bits_db?useSSL=false&serverTimezone=UTC", "root", "root");
            stmt = connection.createStatement();
            final ResultSet rs = stmt.executeQuery(
                    "SELECT c.`code`,cs.`code` scode,cs.seats FROM `cinema_screen` cs LEFT JOIN cinema c on c.id = cs.cinema_id");
            while (rs.next()) {
                final String code = rs.getString("code");
                final String screenCode = rs.getString("scode");
                final Integer seats = rs.getInt("seats");

                screenInfoHashMap.put(code + screenCode, seats);
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenInfoHashMap;
    }

    private static Map<String, CinemaInfo> getCinemaInfo() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        final Connection connection;
        Statement stmt;
        Map<String, CinemaInfo> cinemaInfoMap = new HashMap<>();
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bits_db?useSSL=false&serverTimezone=UTC", "root", "root");
            stmt = connection.createStatement();
            final ResultSet rs = stmt.executeQuery(
                    "SELECT c.code,c.short_name,p.`name` pname,co.`name` cname FROM `cinema` c LEFT join province p on c.province_id=p.id LEFT JOIN county co on co.id = c.county_id");
            while (rs.next()) {
                final String code = rs.getString("code");
                final String provinceName = rs.getString("pname");
                final String countryName = rs.getString("cname");
                CinemaInfo info = new CinemaInfo();
                info.setCountry(countryName);
                info.setProvince(provinceName);
                cinemaInfoMap.put(code, info);
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cinemaInfoMap;
    }

}

@Data
@Accessors(chain = true)
class ThemeScreen implements Cloneable {

    public ThemeScreen(String cinemaCode, String cinemaName, String screenCode) {
        this.cinemaCode = cinemaCode;
        this.cinemaName = cinemaName;
        this.screenCode = screenCode;
    }

    private String num;

    private String province;
    private String country;
    private String cinemaCode;
    private String cinemaName;
    private String screenCode;
    private int seatCounts;
    private String businessType;

    private String errorString;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

@Data
class CinemaInfo {
    private String province;
    private String country;
    private String cinemaCode;
}

