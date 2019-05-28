package com.example.wrap.core_java_36_lectures;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author E0441
 *
 * Exception 与 Error 的区别
 **/
public class Notes02 {

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now().withDayOfMonth(11).withHour(0).withMinute(15);//LocalDateTime.now();
        LocalDate localDateNow = localDateTime.toLocalDate();
        LocalDate localDateBefore = localDateTime.minusMinutes(10).toLocalDate();
        if(!localDateBefore.isEqual(localDateNow)){
            String beforeSql = importLcSql.replaceAll("%s", localDateBefore.toString());
            System.out.println(beforeSql);
        }
        String sql = importLcSql.replaceAll("%s", localDateNow.toString());
        System.out.println(sql);
    }

    private static String importLcSql = "insert overwrite table mtms_logdp " +
            "PARTITION(operation_date) " +
            "SELECT " +
            "key," +
            "username," +
            "ip," +
            "timestamps," +
            "path," +
            "method," +
            "sub_system," +
            "status," +
            "operation_date " +
            "FROM mtms_logcd where operation_date = '%s'";

}
