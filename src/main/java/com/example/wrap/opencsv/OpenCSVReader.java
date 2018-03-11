package com.example.wrap.opencsv;

import com.opencsv.CSVReader;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author 12232
 * @date 2017/12/23
 */
public class OpenCSVReader {
    private static final String SAMPLE_CSV_FILE_PATH = "D:\\test\\csv\\users.csv";

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
        Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
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
}
