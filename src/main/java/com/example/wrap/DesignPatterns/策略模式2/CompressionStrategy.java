package com.example.wrap.DesignPatterns.策略模式2;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by 12232 on 2017/10/21.
 */
public interface CompressionStrategy {

    public OutputStream compression(OutputStream outputStream) throws IOException;

}
