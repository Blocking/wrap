package com.example.wrap.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ILock;
import com.hazelcast.core.IMap;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Create by zhangxy on 2018/10/30 16:42
 */
@Slf4j
public class HazelcastSample {

    static Lock lock = new ReentrantLock();
    static Map<String, Value> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
//        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
//        IMap<String, Value> map = hz.getMap( "map" );
        //hz.getMap( "map" );
        String key = "1";
        map.put( key, new Value(1) );
        map.put( "2", new Value(2) );
        /*log.info("lock start ==============");


        log.info("get [{}]",map.get(key));
        lock.tryLock();
        log.info("get [{}]",map.get(key));*/
        tet();
        tet1();
    }
    @ToString
    static class Value implements Serializable {
        public Value(int amount) {
            this.amount = amount;
        }

        public int amount;
    }
    public static void tet() throws InterruptedException {
        lock.lock();
        TimeUnit.SECONDS.sleep(5L);
        log.info("lock end ==============");
        lock.unlock();
    }
    public static void tet1(){
        lock.lock();
        log.info("lock end ==============");
    }
}
