package com.example.wrap.nio;

import org.junit.Test;

import java.nio.ByteOrder;
import java.nio.CharBuffer;

/**
 * @author 12232
 */
public class BufferFillDrain {
    public static void main(String[] args) {
        CharBuffer charBuffer  = CharBuffer.allocate(100);
        while (fillBuffer(charBuffer)){
            charBuffer.flip();
            drainBuffer(charBuffer);
            charBuffer.clear();
        }
    }

    private static void drainBuffer(CharBuffer charBuffer) {
        while (charBuffer.hasRemaining()){
            System.out.print(charBuffer.get());
        }
        System.out.println();
    }

    private static boolean fillBuffer(CharBuffer charBuffer) {
        if(index < strings.length){
            String string = strings[index];
            for (int i = 0; i < string.length() ; i++) {
                charBuffer.put(string.charAt(i));
            }
            index++;
            return true;
        }else{
            return false;
        }
    }

    private static int index = 0;
    private static String[] strings = {
            "A random string value",
            "The product of an infinite number of monkeys",
            "Hey hey we're the Monkees",
            "Opening act for the Monkees: Jimi Hendrix",
            // Sorry Jimi ;-)
            "'Scuse me while I kiss this fly",
            "Help Me!  Help Me!",};

    @Test
    public void t(){
        System.out.println(ByteOrder.nativeOrder());
    }

}
