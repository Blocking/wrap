package com.example.wrap.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author 12232
 */
public class ChannelTestFirst {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.connect(new InetSocketAddress("localhost",8080));

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));

        DatagramChannel datagramChannel = DatagramChannel.open();

        RandomAccessFile raf = new RandomAccessFile("somefile","r");

        FileChannel fileChannel = raf.getChannel();



    }
}
