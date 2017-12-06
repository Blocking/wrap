package com.example.wrap.DesignPatterns.two;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by 12232 on 2017/10/21.
 */
public interface CompressionStrategy {

    public OutputStream compression(OutputStream outputStream) throws IOException;

}
