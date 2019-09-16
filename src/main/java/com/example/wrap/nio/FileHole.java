package com.example.wrap.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * create file with holes in it
 * @author 12232
 */
public class FileHole {

    public static void main(String[] args) throws IOException {
        File temp  = File.createTempFile("holy",null);
        RandomAccessFile file = new RandomAccessFile(temp,"rw");
        FileChannel channel = file.getChannel();
        //creating a working buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        putData(0,byteBuffer,channel);
        putData(500_0000,byteBuffer,channel);
        putData(50000,byteBuffer,channel);
        //size will report the largest position written,
        // but there are two holes in this file. This file will
        // not consume 5MB on disk (unless the fileSystem is extremely brain-damaged)
        System.out.println("Wrote temp file '"+temp.getPath()+"',size="+channel.size());
        channel.close();
        file.close();
    }

    private static void putData(int position, ByteBuffer buffer, FileChannel channel) throws IOException {
        String string = "*<-- location "+position;
        buffer.clear();
        buffer.put(string.getBytes("US-ASCII"));
        buffer.flip();
        channel.position(position);
        channel.write(buffer);
    }
}
