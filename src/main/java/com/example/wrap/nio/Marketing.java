package com.example.wrap.nio;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

/**
 * Demonstrate gathering write using many buffers
 *
 * @author 12232
 */
public class Marketing {

    private static final String DEMOGRAPHIC = "blahblah.txt";


    public static void main(String[] args) throws Exception {
        /*int reps = 10;
        if (args.length > 0) {
            reps = Integer.parseInt(args[0]);
        }
        FileOutputStream fos = new FileOutputStream(DEMOGRAPHIC);
        GatheringByteChannel gatherChannel = fos.getChannel();
        ByteBuffer[] bs = utterBS(reps);
        //Deliver the message to the waiting market
        while (gatherChannel.write(bs) > 0) {
            //empty body
            // loop until write() returns zero
        }
        System.out.println("mindshare  paradigms synergized to " + DEMOGRAPHIC);
        fos.close();*/
        Path path = Paths.get("D:\\tmp");
        BiPredicate<Path, BasicFileAttributes> matcher = (path1, basicFileAttributes) ->  {
            File file = path1.toFile();
            return file.getName().equals("Test1.java") && !basicFileAttributes.isDirectory();
        };
        Stream<Path> pathStream = Files.find(path, Integer.MAX_VALUE, matcher);
        pathStream.forEach(path1 -> {
            System.out.println(path1);
        });
    }

    /**
     * these are just representative; add you own
     */
    private static String[] col1 = {
            "Aggregate", "Enable", "Leverage",
            "Facilitate", "Synergize", "Repurpose",
            "Strategize", "Reinvent", "Harness"
    };

    private static String[] col2 = {
            "cross-platform", "best-of-breed", "frictionless",
            "ubiquitous", "extensible", "compelling",
            "mission-critical", "collaborative", "integrated"
    };
    private static String[] col3 = {
            "methodologies", "infomediaries", "platforms",
            "schemas", "mindshare", "paradigms",
            "functionalities", "web services", "infrastructures"
    };

    private static String newline = System.getProperty("line.separator");

    private static ByteBuffer[] utterBS(int howMany) throws Exception{

        List list = new LinkedList();
        for (int i = 0; i < howMany; i++) {
            list.add(pickRandom(col1," "));
            list.add(pickRandom(col2," "));
            list.add(pickRandom(col3,newline));
        }
        ByteBuffer[] buffs = new ByteBuffer[list.size()];
        list.toArray(buffs);
        return buffs;
    }

    private static Random rand = new Random();

    private static Object pickRandom(String[] strings, String suffix) throws UnsupportedEncodingException {
        String string = strings[rand.nextInt(strings.length)];
        int total = string.length() + suffix.length();
        ByteBuffer buffer = ByteBuffer.allocate(total);
        buffer.put(string.getBytes("US-ASCII"));
        buffer.put(suffix.getBytes("US-ASCII"));
        buffer.flip();
        return buffer;

    }

}
