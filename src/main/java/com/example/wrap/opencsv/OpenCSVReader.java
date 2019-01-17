package com.example.wrap.opencsv;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderHeaderAware;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *http://opencsv.sourceforge.net/
 * @author 12232
 * @date 2017/12/23
 */
public class OpenCSVReader {
    private static final String SAMPLE_CSV_FILE_PATH = "D:\\test\\csv\\users.csv";
    private static final String STRING_ARRAY_SAMPLE = "./string-array-sample.csv";

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
