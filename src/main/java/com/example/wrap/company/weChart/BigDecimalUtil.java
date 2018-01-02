package com.example.wrap.company.weChart;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtil {

    /**
     * 计算BigDecimal 除法
     *
     * @param dividend 被除数
     * @param divisor 除数
     * @param decimal 小数位数
     * @return 计算结果，如果除数为0返回0
     */
    public static BigDecimal percentCompute(final BigDecimal dividend, final BigDecimal divisor, final int decimal) {
        if ((dividend != null) && (divisor != null)) {
            if (divisor.compareTo(BigDecimal.ZERO) != 0) {
                return dividend.divide(divisor, decimal, RoundingMode.HALF_UP);
            }
        }
        return new BigDecimal(0L);
    }

    public static BigDecimal percentCompute(final Long dividend, final Long divisor, final int decimal) {
        if ((dividend != null) && (divisor != null)) {
            if (divisor != 0) {
                return new BigDecimal(dividend / divisor).setScale(decimal, RoundingMode.HALF_UP);
            }
        }
        return new BigDecimal(0L);
    }

}
