package com.example.wrap.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author 12232
 * test copying be
 */
public class ChannelCopy {

    public static void main(String[] args) throws IOException {
        ReadableByteChannel source = Channels.newChannel(System.in);
        WritableByteChannel dest = Channels.newChannel(System.out);

//        channelCopy1(source, dest);

        channelCopy2(source, dest);

        source.close();
        dest.close();

    }

    private static void channelCopy2(ReadableByteChannel source, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(16 * 1024);
        while (source.read(buffer) != -1) {
            buffer.flip();
            while(buffer.hasRemaining()){
                dest.write(buffer);
            }
            buffer.clear();

        }
    }

    private static void channelCopy1(ReadableByteChannel source, WritableByteChannel dest) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(16 * 1024);
        while (source.read(buffer) != -1) {
            System.out.println(buffer.toString());
            // Prepare the buffer to be drained
            buffer.flip();
            System.out.println(buffer.toString());
            // Write to the channel; may block
            dest.write(buffer);
            System.out.println(buffer.toString());
            // If partial transfer, shift remainder down
            // If buffer is empty, same as doing clear( )
            buffer.compact();
            System.out.println(buffer.toString());
        }
        // EOF will leave buffer in fill state
        buffer.flip();
        // Make sure that the buffer is fully drained
        while (buffer.hasRemaining()) {
            dest.write(buffer);
        }
    }

}
