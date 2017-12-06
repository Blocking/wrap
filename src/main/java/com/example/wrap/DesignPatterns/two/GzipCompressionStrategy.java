package com.example.wrap.DesignPatterns.two;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by 12232 on 2017/10/21.
 * Gzip压缩算法实现
 */
public class GzipCompressionStrategy implements CompressionStrategy {
    @Override
    public OutputStream compression(OutputStream outputStream) throws IOException {
        return new GZIPOutputStream(outputStream);
    }
}
