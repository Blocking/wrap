package com.example.wrap.nio2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Test behavior of memory mapped buffer types . Create a file , write
 * some data to it, then create three different types of mappings
 * to it. Observe the effects of changes through the buffer APIs
 * and updating the file directly . the data spans page boundaries
 * to illustrate the page-oriented nature of Copy-On-Write mappings.
 *
 * 例中代码诠释了写时拷贝是如何页导
 * 向（page-oriented）的。当在使用 MAP_MODE.PRIVATE 模式创建的 MappedByteBuffer 对象上
 * 调用 put( )方法而引发更改时，就会生成一个受影响页的拷贝。这份私有的拷贝不仅反映本地更
 * 改，而且使缓冲区免受来自外部对原来页更改的影响。然而，对于被映射文件其他区域的更改还是
 * 可以看到的
 *
 * @author 12232
 */
public class MapFile {
    public static void main(String[] args) throws IOException {
        // Create a temp file and get a channel to connected to it
        File file = File.createTempFile("mmaptest",null);
        RandomAccessFile raf = new RandomAccessFile(file,"rw");
        FileChannel channel = raf.getChannel();
        ByteBuffer temp = ByteBuffer.allocate(100);
        temp.put("This is the file content".getBytes());
        temp.flip();
        channel.write(temp,0);
        //Put something else in the file , starting at location at 8192.
        //8192 is 8 KB ,almost certainly a different memory/FS page.
        //This may cause a file hole , depending on the
        // filesystem page size.
        temp.clear();
        temp.put("This the more file content".getBytes());
        temp.flip();
        channel.write(temp,8192);
        //Create three types of mappings to the same file
        MappedByteBuffer ro = channel.map(FileChannel.MapMode.READ_ONLY,0,channel.size());
        MappedByteBuffer rw = channel.map(FileChannel.MapMode.READ_WRITE,0,channel.size());
        MappedByteBuffer cow = channel.map(FileChannel.MapMode.PRIVATE,0,channel.size());
        // the buffer states before any modifications
        System.out.println("Begin");
        showBuffers(ro,rw,cow);
        // modify the copy-on-write buffer
        cow.position(8);
        cow.put("COW".getBytes());
        System.out.println("Change to COW buffer");
        showBuffers(ro,rw,cow);
        // modify the read/write buffer
        rw.position(9);
        rw.put("RW".getBytes());
        rw.position(8194);
        rw.put("RW".getBytes());
        rw.force();
        System.out.println("Change to R/W buffer");
        showBuffers(ro,rw,cow);
        // Write to the file through the channel; hit both pages
        temp.clear();
        temp.put("Channel write".getBytes());
        temp.flip();
        channel.write(temp,0);
        temp.rewind();
        channel.write(temp,8202);
        System.out.println("Write on channel");
        showBuffers(ro,rw,cow);
        // modify the copy-on-write buffer again
        cow.position(8207);
        cow.put("COW2".getBytes());
        System.out.println("Second change to COW buffer");
        showBuffers(ro,rw,cow);
        // modify the read/write buffer
        rw.position(0);
        rw.put(" R/W2 ".getBytes());
        rw.position (8210);
        rw.put(" R/W2 ".getBytes());
        rw.force();
        System.out.println("Second change to R/W buffer");
        showBuffers(ro,rw,cow);
        // cleanup
        channel.close();
        raf.close();
        file.delete();
    }

    private static void showBuffers(MappedByteBuffer ro, MappedByteBuffer rw, MappedByteBuffer cow) {
        dumpBuffer("R/O",ro);
        dumpBuffer("R/W",rw);
        dumpBuffer("COW",cow);
        System.out.println("");
    }

    private static void dumpBuffer(String prefix, MappedByteBuffer buffer) {
        System.out.print(prefix+ ": '");
        int nulls =0;
        int limit = buffer.limit();
        for (int i = 0; i < limit; i++) {
            char c = (char) buffer.get(i);
            if(c == '\u0000'){
                nulls++;
                continue;
            }
            if (nulls != 0){
                System.out.print("| ["+nulls+" nulls] |");
                nulls = 0;
            }
            System.out.print(c);
        }
        System.out.println("'");
    }
}
