package com.example.wrap.company.weChart.test;

import com.example.wrap.utils.Downloader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created by 12232 on 2018/1/2.
 */
@Slf4j
public class TestGson {

    private static final String tagetRootPath = "/tmp/dim";



    @Test
    public void t() throws IOException {
        Resource resource = new ClassPathResource("/jsonData/configVersion.json");
        Reader reader = new FileReader(resource.getFile());
        Gson gson = new Gson();
        List<FileJson> fileJsons =  gson.fromJson(reader, new TypeToken<List<FileJson>>(){}.getType());
        final Set<String> set = fileJsons.stream()
                .map(fileJson -> fileJson.getName().substring(fileJson.getName().lastIndexOf(".")))
                .collect(Collectors.toSet());

        System.out.println(set);
    }


    @Test
    public void parse() throws IOException {
        Resource resource = new ClassPathResource("/jsonData/configVersion.json");
        Reader reader = new FileReader(resource.getFile());
        Gson gson = new Gson();
        List<FileJson> fileJsons =  gson.fromJson(reader, new TypeToken<List<FileJson>>(){}.getType());
        Downloader downloader = new Downloader();
        log.info("批量下载开始=========================================:::{}",fileJsons.size());
        final List<CompletableFuture<Void>> futureList = fileJsons.stream().map(fileJson -> {
            try {
                URL rightUrl = new URL(fileJson.getUrl());
                final CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    downloader.download(rightUrl, new File(tagetRootPath + fileJson.getPathWithName()));
                });
                return future;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        // 等待所有文件上传完毕
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()])).join();
        log.info("批量下载结束=========================================");
    }

    @Test
    public void t1() throws MalformedURLException {
        Downloader downloader = new Downloader();
        downloader.download(new URL("https://ikj-storage-front-test.oss-cn-beijing.aliyuncs.com/DimService/6cee8756/36e2d827/91c91fb2/86f13e3b/5pa95bel6aG5.ndm2"),
                new File("/tmp/dim/施工项.ndm2"));
    }


    @Setter
    @Getter
    @AllArgsConstructor
    private static class FileJson implements Serializable {
        private String md5;
        private String name;
        private String  path;
        private String pathWithName;
        private Long  size;
        private String url;

    }

}
