package com.example.wrap.utils;

/**
 * LocalDate 市场简报 --时间范围枚举
 *
 * @author
 */
public enum LocalDatePeriodStatus {
	/**
	 * 本周
	 */
    CURRENT_WEEK, 
    /**
     * 上周
     */
    PREVIOUS_WEEK,
    /**
     * 本月
     */
    CURRENT_MONTH, 
    /**
     * 上月
     */
    PREVIOUS_MONTH,
    /**
     * 本季度
     */
    CURRENT_QUARTER, 
    /**
     * 上季度
     */
    PREVIOUS_QUARTER,
    /**
     * 本年
     */
    CURRENT_YEAR,
    /**
     * 前年
     */
    PREVIOUS_YEAR,
    /**
     * 图表季报要展示整12个月的
     */
    CHART_QUARTER 
}
