package com.example.wrap.nio2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Test channel transfer. This ia a very simplistic concatenation
 * program. It takes a list of file names as arguments , opens each
 * in turn and transfers(copies) their content to the given
 * WritableByteChannel (in the case,stdout)
 *
 * @author 12232
 */
public class ChannelTransfer {
    public static void main(String[] args) throws IOException {
        if (args.length == 0){
            System.err.println("Usage: filename ...");
        }
        catfiles(Channels.newChannel(System.out),args);
    }

    /**
     * Concatenate the content of each of the named files to
     * the given channel . A very dump version of 'cat'.
     * @param target
     * @param files
     */
    private static void catfiles(WritableByteChannel target, String[] files) throws IOException {
        for (int i = 0; i < files.length; i++) {
            FileInputStream fis = new FileInputStream(files[0]);
            FileChannel channel = fis.getChannel();
            channel.transferTo(0,channel.size(),target);
            channel.close();
            fis.close();
        }
    }
}
