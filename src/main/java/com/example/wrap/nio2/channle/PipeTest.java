package com.example.wrap.nio2.channle;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Random;

/**
 * @author 12232
 */
public class PipeTest {

    public static void main(String[] args) throws IOException {
        //Wrap a channel around stout
        WritableByteChannel out = Channels.newChannel(System.out);
        // start worker and get read end of channel
        ReadableByteChannel wokerChannel = startWorker(10);
        ByteBuffer buffer = ByteBuffer.allocate(100);
        while (wokerChannel.read(buffer) >= 0) {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
        }
    }

    /**
     * This method could return a socketChannel or
     * FileChannel instance just as easily
     * @param reps
     * @return
     */
    private static ReadableByteChannel startWorker(int reps) throws IOException {
        Pipe pipe = Pipe.open();
        Worker worker = new Worker(pipe.sink(),reps);
        worker.start();
        return pipe.source();

    }

    private static class Worker extends Thread{

        WritableByteChannel channel;
        private int reps;

        public Worker(Pipe.SinkChannel sink, int reps) {
            this.channel = sink;
            this.reps = reps;
        }

        /**
         * thread execution begins here
         */
        @Override
        public void run() {

            ByteBuffer buffer = ByteBuffer.allocate(100);
            try {
                for (int i = 0; i < reps; i++) {
                    doSomeWork(buffer);
                    //channel may not take it all at once
                    while(channel.write(buffer)>0){
                        // empty
                    }
                }
                this.channel.close();
            }catch (IOException e){
                //easy way out ; this is a demo
                e.printStackTrace();
            }
        }

        private String [] products = {
                "No good deed goes unpunished",
                "To be, or what?",
                "No matter where you go, there you are",
                "Just say \"Yo\"",
                "My karma ran over my dogma"
        };

        private Random rand = new Random();

        private void doSomeWork(ByteBuffer buffer) {
            int product = rand.nextInt(products.length);
            buffer.clear();
            buffer.put(products[product].getBytes());
            buffer.put ("\r\n".getBytes( ));
            buffer.flip( );
        }
    }
}
