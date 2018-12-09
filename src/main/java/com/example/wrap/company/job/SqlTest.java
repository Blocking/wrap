package com.example.wrap.company.job;

/**
 * 统计报表-售票系统统计票房查询-院线影片分影厅查询
 * job sql
 */
public class SqlTest {

    public static void main(String[] args) {
        final String importScdSql =
                String.format(importSql, "2018-12-01", "2018-12-09");
        System.out.println(importScdSql);
    }

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
}
