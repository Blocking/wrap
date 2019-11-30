package com.example.wrap.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.ByteBuffer;

@Slf4j
public class NioTest1 {
    @Test
    public void demo1() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 'M').put((byte) 'e');
        buffer.mark();
        log.info(buffer.toString());
        buffer.put((byte) 'l').put((byte) 'l').put((byte) 'o').put((byte) 'w');
        log.info(buffer.toString());
        buffer.flip();
        log.info(buffer.toString());
        buffer.position(2);
        log.info(buffer.toString());

        buffer.compact();
        log.info(buffer.toString());

    }

    @Test
    public void tSql(){
        final String businessDate = "2019-07-26";
        final String importScdSqls = importScdSql + " where business_date = '" + businessDate + "'";
        System.out.println(importScdSqls);
    }


    private static String importScdSql = "insert overwrite table mtms_sdp "
            + "PARTITION(business_date) "
            + "select "
            + "key, "
            + "cinema_status, "
            + "cinema_code, "
            //+ "cinema_name, "
            //+ "cinema_chain_code, "
            //+ "cinema_chain_name, "
            + "screen_code, "
            + "screen_name, "
            + "film_code, "
            + "film_name, "
            + "session_code, "
            + "session_datetime, "
            + "local_sales_count, "
            + "local_refund_count, "
            + "local_refund,"
            + "local_sales,"
            + "online_sales_count,"
            + "online_refund_count,"
            + "online_refund,"
            + "online_sales,"
            + "past_sale_count,"
            + "past_sales,"
            + "region_code, "
            + "region_name, "
            + "province_code, "
            + "province_name, "
            + "city_code, "
            + "city_name, "
            + "area_code, "
            + "area_name, "
            + "seat_count, "
            + "vendor_code, "
            + "film_version, "
            + "film_type, "
            + "film_introduction, "
            + "local_service, "
            + "service, "
            + "report_time, "
            + "business_date "
            + "from mtms_scd";

}
