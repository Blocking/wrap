package com.example.wrap.DesignPatterns.two;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by 12232 on 2017/10/21.
 * zip压缩算法实现类
 */
public class ZipCompressionStrategy implements CompressionStrategy {
    @Override
    public OutputStream compression(OutputStream outputStream) throws IOException {
        return new ZipOutputStream(outputStream);
    }
}
