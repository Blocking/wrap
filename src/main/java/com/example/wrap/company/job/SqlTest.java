package com.example.wrap.company.job;

import java.time.LocalDate;

/**
 * 统计报表-售票系统统计票房查询-院线影片分影厅查询
 * job sql
 */
public class SqlTest {

    public static void main(String[] args) {
        final String importScdSql = importSmdSql.replaceAll("%s", "2018-08-01");
        System.out.println(importScdSql);
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.plusDays(-1).toString());
        System.out.println(localDate.toString());
    }

<<<<<<< HEAD
    private static String importSql =
            "insert overwrite table mtms_yxypfyt                                                                "
                    + "PARTITION(business_date)                                                                 "
                    + "SELECT                                                                                   "
                    + "mtms_sdp.cinema_code,                                                                    "
                    + "mtms_sdp.screen_code,                                                                    "
                    + "mtms_cinema_chain.cinema_name,                                                           "
                    + "mtms_sdp.film_code,                                                                      "
                    + "mtms_sdp.film_name,                                                                      "
                    + "mtms_sdp.film_version,                                                                   "
                    + "mtms_sdp.province_code,                                                                  "
                    + "mtms_sdp.province_name,                                                                  "
                    + "mtms_film_parquet.producer_id,                                                           "
                    + "mtms_sdp.vendor_code,                                                                    "
                    + "mtms_cinema_chain.cinema_chain_id,                                                       "
                    + "count(*) AS 'total_session',                                                             "
                    + "SUM(                                                                                     "
                    + "    local_sales_count + online_sales_count                                               "
                    + "  ) AS 'total_audience',                                                                 "
                    + "SUM(local_sales + online_sales) AS 'total_boxoffice',                                    "
                    + "ROUND(                                                                                   "
                    + "    SUM(local_sales + online_sales) / 100 / SUM(                                         "
                    + "      local_sales_count + online_sales_count                                             "
                    + "    ),                                                                                   "
                    + "2                                                                                        "
                    + "  ) AS 'price',                                                                          "
                    + "SUM(nvl(mtms_sdp.local_service,0) + nvl(mtms_sdp.service,0) ) AS 'serviceSum',           "
                    + "business_date                                                                            "
                    + "FROM mtms_sdp                                                                            "
                    + "LEFT JOIN mtms_cinema_chain                                                              "
                    + "    ON mtms_sdp.cinema_code = mtms_cinema_chain.cinema_code                              "
                    + "  and business_date between mtms_cinema_chain.from_date and mtms_cinema_chain.to_date    "
                    + "LEFT JOIN mtms_film_parquet                                                              "
                    + "    ON mtms_sdp.film_code = mtms_film_parquet.film_code                                  "
                    + "WHERE business_date between '%s'                                                         "
                    + "  and '%s'                                                                               "
                    //                    + "  and local_sales + online_sales > 0                                                     "
                    + "GROUP BY                                                                                 "
                    + "  mtms_cinema_chain.cinema_chain_id,                                                     "
                    + "  mtms_cinema_chain.cinema_name,                                                         "
                    + "  mtms_sdp.cinema_code,                                                                  "
                    + "  film_name,                                                                             "
                    + "  province_code,                                                                         "
                    + "  province_name,                                                                         "
                    + "  film_version,                                                                          "
                    + "  mtms_sdp.film_code,                                                                    "
                    + "  screen_code,                                                                           "
                    + "  business_date,                                                                         "
                    + "  producer_id,                                                                           "
                    + "  vendor_code                                                                           ";



    private static String importRcsmdSql = "insert overwrite table mtms_rcsdp "
=======

    private static String importSmdSql = "insert overwrite table mtms_sdp "
>>>>>>> add
            + "PARTITION(business_date) "
            + "select "
            + "(case when a.key is null then b.key else a.key end) as key, "
            + "(case when a.key is null then b.cinema_status else a.cinema_status end) as cinema_status, "
            + "(case when a.key is null then b.cinema_code else a.cinema_code end) as cinema_code, "
            + "(case when a.key is null then b.screen_code else a.screen_code end) as screen_code, "
            + "(case when a.key is null then b.screen_name else a.screen_name end) as screen_name, "
<<<<<<< HEAD
            + "(case when a.key is null then b.film_code else a.film_code end) as film_code, "
            + "(case when a.key is null then b.film_name else a.film_name end) as film_name, "
=======
            + "(case when b.key is null then a.film_code else b.film_code end) as film_code, "
            + "(case when b.key is null then a.film_name else b.film_name end) as film_name, "
>>>>>>> add
            + "(case when a.key is null then b.session_code else a.session_code end) as session_code, "
            + "(case when a.key is null then b.session_datetime else a.session_datetime end) as session_datetime, "
            + "(case when b.key is null then a.local_sales_count else b.local_sales_count end) as local_sales_count, "
            + "(case when b.key is null then a.local_refund_count else b.local_refund_count end) as local_refund_count, "
            + "(case when b.key is null then a.local_refund else b.local_refund end) as local_refund, "
            + "(case when b.key is null then a.local_sales else b.local_sales end) as local_sales, "
            + "(case when b.key is null then a.online_sales_count else b.online_sales_count end) as online_sales_count, "
            + "(case when b.key is null then a.online_refund_count else b.online_refund_count end) as online_refund_count, "
            + "(case when b.key is null then a.online_refund else b.online_refund end) as online_refund, "
            + "(case when b.key is null then a.online_sales else b.online_sales end) as online_sales, "
            + "(case when b.key is null then a.past_sale_count else b.past_sale_count end) as past_sale_count, "
            + "(case when b.key is null then a.past_sales else b.past_sales end) as past_sales, "
            + "(case when a.key is null then b.region_code else a.region_code end) as region_code, "
            + "(case when a.key is null then b.region_name else a.region_name end) as region_name, "
            + "(case when a.key is null then b.province_code else a.province_code end) as province_code, "
            + "(case when a.key is null then b.province_name else a.province_name end) as province_name, "
            + "(case when a.key is null then b.city_code else a.city_code end) as city_code, "
            + "(case when a.key is null then b.city_name else a.city_name end) as city_name, "
            + "(case when a.key is null then b.area_code else a.area_code end) as area_code, "
            + "(case when a.key is null then b.area_name else a.area_name end) as area_name, "
            + "(case when a.key is null then b.seat_count else a.seat_count end) as seat_count, "
            + "(case when a.key is null then b.vendor_code else a.vendor_code end) as vendor_code, "
            + "(case when a.key is null then b.film_version else a.film_version end) as film_version, "
            + "(case when a.key is null then b.film_type else a.film_type end) as film_type, "
            + "(case when a.key is null then b.film_introduction else a.film_introduction end) as film_introduction, "
<<<<<<< HEAD
            + "(case when b.key is null then a.advance_sales_count else b.advance_sales_count end) as advance_sales_count, "
            + "(case when b.key is null then a.advance_sales else b.advance_sales end) as advance_sales, "
            + "(case when b.key is null then a.local_service else b.local_service end) as local_service, "
            + "(case when b.key is null then a.service else b.service end) as service, "
            + "(case when a.key is null then b.report_time else a.report_time end) as report_time, "
            + "(case when a.key is null then b.business_date else a.business_date end) as business_date "
            + "from (select * from mtms_rcsdp where business_date = '%s') as a "
            + "full outer join (select * from mtms_rcsmd where business_date = '%s') as b on a.key = b.key; ";
=======
            + "(case when b.key is null then a.local_service else b.local_service end) as local_service, "
            + "(case when b.key is null then a.service else b.service end) as service, "
            + "(case when b.key is null then a.report_time else b.report_time end) as report_time, "
            + "(case when a.key is null then b.business_date else a.business_date end) as business_date "
            + "from (select * from mtms_sdp where business_date = '%s') as a "
            + "full outer join (select * from mtms_smd where business_date = '%s') as b on a.key = b.key; ";
>>>>>>> add
}
