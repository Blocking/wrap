package com.example.wrap.opencsv;

import com.beust.jcommander.internal.Lists;
import com.beust.jcommander.internal.Sets;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.junit.Test;

import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *http://opencsv.sourceforge.net/
 * @author 12232
 * @date 2017/12/23
 */
public class OpenCSVReader {
    private static final String SAMPLE_CSV_FILE_PATH = "D:\\test\\csv\\users.csv";
    private static final String TEMP_SAMPLE_CSV_FILE_PATH = "D:\\test\\csv\\dsapi.xuban.v1-data.csv";
    private static final String TEMP_SAMPLE_CSV_FILE_PATH_OUT = "D:\\test\\csv\\dsapi.xuban.v1-data-out.csv";
    private static final String STRING_ARRAY_SAMPLE = "./string-array-sample.csv";


    @Test
    public  void sample2() throws Exception {
        Reader reader = Files.newBufferedReader(Paths.get(TEMP_SAMPLE_CSV_FILE_PATH));
        CSVReader csvReader = new CSVReader(reader);
        String[] nextRecords;
        Set<Temp> temps = Sets.newLinkedHashSet();
        while((nextRecords = csvReader.readNext()) != null){
            String nextRecord = nextRecords[9];
            String[] split = nextRecord.split("&");
            Temp temp = new Temp();
            Arrays.stream(split).forEach(str->{
                int idx = str.indexOf("=");
                if(idx == -1){
                    return;
                }
                try {
                    String key = URLDecoder.decode(str.substring(0, idx), "UTF-8");
                    String value = URLDecoder.decode(str.substring(idx + 1), "UTF-8");
                    if("studentCode".equals(key)){
                        temp.setStudentCode(value);
                    }
                    if("schoolId".equals(key)){
                        temp.setSchoolId(value);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            });
            temps.add(temp);
            System.out.println("args : " + nextRecord+"::::"+temp);

        }


        try (
                Writer writer = Files.newBufferedWriter(Paths.get(TEMP_SAMPLE_CSV_FILE_PATH_OUT));
        ) {
            StatefulBeanToCsv<Temp> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            beanToCsv.write(Lists.newArrayList(temps));
        }

    }


    @Test
    public  void sample() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
        CSVReader csvReader = new CSVReader(reader);
        String[] nextRecords;
        while((nextRecords = csvReader.readNext()) != null){
            System.out.println("Name : " + nextRecords[0]);
            System.out.println("Email : " + nextRecords[1]);
            System.out.println("Phone : " + nextRecords[2]);
            System.out.println("Country : " + nextRecords[3]);
            System.out.println("==========================");
        }

    }
    @Test
    public void sample1() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(STRING_ARRAY_SAMPLE));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> records = csvReader.readAll();
        for (String[] record : records) {
            System.out.println("Name : " + record[0]);
            System.out.println("Email : " + record[1]);
            System.out.println("Phone : " + record[2]);
            System.out.println("Country : " + record[3]);
            System.out.println("---------------------------");
        }
    }


    @Test
    public void t() throws IOException {
        //version 4.2 or up
        Map<String, String> values = new CSVReaderHeaderAware(new FileReader(STRING_ARRAY_SAMPLE)).readMap();
        System.out.println(values);
    }
    @Test
    public void t1() throws IOException {
        new CSVReaderHeaderAware(new FileReader(STRING_ARRAY_SAMPLE)).forEach(strings -> {
            System.out.println(Arrays.toString(strings));
        });
    }
}
