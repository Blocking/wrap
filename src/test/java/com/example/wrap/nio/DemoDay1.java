package com.example.wrap.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Arrays;

@Slf4j
public class DemoDay1 {

    @Test
    public void action1() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        log.info(buffer.toString());
        buffer.put((byte) 'H').put((byte) 'E').put((byte) 'L').put((byte) 'L').put((byte) 'O');
        log.info(buffer.toString());

        buffer.put(0, (byte) 'M').put((byte) 'W');
        log.info(buffer.toString());
        //缓冲区 翻转
        buffer.flip();
        log.info(buffer.toString());
        byte[] arr = new byte[10];
        for (int i = 0; buffer.hasRemaining(); i++) {
            arr[i] = buffer.get();
        }
        System.out.println(new String(arr));

        buffer.rewind();
        int remaining = buffer.remaining();
        for (int i = 0; i < remaining; i++) {
            arr[i] = buffer.get();
        }
        System.out.println(new String(arr));

        buffer.flip();

        byte[] bigArr = new byte[1000];
        int length = buffer.remaining();
        buffer.get(bigArr, 0, length);
        System.out.println(Arrays.toString(bigArr));

        //缓冲区的复制   复制一个缓冲区会创建一个新的buffer对象 但并不复制数据 原始缓冲区和副本都会操作同样的数据元素
        ByteBuffer duplicateBuffer = buffer.duplicate();

        //获得一个只读缓冲区
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        buffer.position(3).limit(5);

        //获得一个缓冲区 会从原始缓冲区的当前位置开始创建一个新缓冲区 并且其容量是原始缓冲区的剩余元素数量(limit - position).
        //这个新缓冲区共享一段数据元素于序列。分割出来的缓冲区也会继承只读和直接属性
        ByteBuffer slice = buffer.slice();


    }

    @Test
    public void demo2() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(23686454545L);
        buffer.position(1).limit(5);
        int anInt = buffer.getInt();
        System.out.println(anInt);
        buffer.getLong();
    }

}
