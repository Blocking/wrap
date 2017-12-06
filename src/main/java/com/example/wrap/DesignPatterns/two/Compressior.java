package com.example.wrap.DesignPatterns.two;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by 12232 on 2017/10/21.
 * 压缩操作类 使用各种策略
 */
public class Compressior {

    private CompressionStrategy strategy;

    public Compressior(CompressionStrategy strategy) {
        this.strategy = strategy;
    }

    public void compress(Path path, File outFile) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(outFile)) {
            Files.copy(path,this.strategy.compression(outputStream));
        }
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("");
        File tempFile = new File("");
        //使用Gzip算法进行压缩
        Compressior compressior = new Compressior(new GzipCompressionStrategy());
        compressior.compress(path,tempFile);
        //使用zip算法进行压缩
        Compressior compressior1 = new Compressior(new ZipCompressionStrategy());
        compressior1.compress(path,tempFile);

        //java8 可以这么写 只是单纯的写法变了
        Compressior compressior2 = new Compressior(GZIPOutputStream::new);

        Compressior compressior3 = new Compressior(ZipOutputStream::new);

    }

}
