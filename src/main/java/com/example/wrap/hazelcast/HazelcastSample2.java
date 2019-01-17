package com.example.wrap.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Create by zhangxy on 2018/10/30 16:42
 */
@Slf4j
public class HazelcastSample2 {
    public static void main(String[] args) throws InterruptedException {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IMap<String, Value> map = hz.getMap( "map" );
        String key = "1";
        log.info("start=========");
        log.info("get key2 [{}]",map.get("2"));
        map.lock(key);
        log.info("get [{}]",map.get(key));
    }
    @ToString
    static class Value implements Serializable {
        public Value(int amount) {
            this.amount = amount;
        }
        public int amount;
    }
}
