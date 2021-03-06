package com.example.wrap.nio;


import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;


/**
 * @author 12232
 */
public class BufferCharView {
    public static void main(String[] args) {
        //创建大端排序的 字节缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(7).order(ByteOrder.BIG_ENDIAN);

        //在字节缓冲区的基础上 创建 字符视图
        CharBuffer charBuffer = byteBuffer.asCharBuffer();

        // Load the ByteBuffer with some bytes
        byteBuffer.put(0, (byte) 0);
        byteBuffer.put(1, (byte) 'H');
        byteBuffer.put(2, (byte) 0);
        byteBuffer.put(3, (byte) 'i');
        byteBuffer.put(4, (byte) 0);
        byteBuffer.put(5, (byte) '!');
        byteBuffer.put(6, (byte) 0);

        println(byteBuffer);
        println(charBuffer);

    }

    private static void println(Buffer buffer) {
        System.out.println("pos=" + buffer.position()
                + ", limit=" + buffer.limit()
                + ", capacity=" + buffer.capacity()
                + ": '" + buffer.toString() + "'");
    }
}
