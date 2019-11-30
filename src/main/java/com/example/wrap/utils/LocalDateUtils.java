package com.example.wrap.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LocalDateUtils {
	
	public static final DateTimeFormatter formatter_M_d = DateTimeFormatter.ofPattern("M.d");
	
	public static final DateTimeFormatter formatter_Md = DateTimeFormatter.ofPattern("M月d日");
	
	public static final DateTimeFormatter formatter_yyyyM = DateTimeFormatter.ofPattern("yyyy年M月");
	public static final DateTimeFormatter formatter_yyyyMM = DateTimeFormatter.ofPattern("yyyy-MM");
	
	public static final DateTimeFormatter formatter_MMdd = DateTimeFormatter.ofPattern("MM-dd");
	public static final DateTimeFormatter formatter_yyyyMMdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * 取日期范围集合
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<String> getDateStrBetween(String startDate, String endDate) {
		LocalDate startLocalDate = LocalDate.parse(startDate, formatter_yyyyMMdd);
		LocalDate endLocalDate = LocalDate.parse(endDate, formatter_yyyyMMdd);
		long numOfDaysBetween  = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
		List<String> collect = IntStream.iterate(0, i -> i + 1)
				.limit(numOfDaysBetween+1)
				.mapToObj(i -> startLocalDate.plusDays(i).toString())
				.collect(Collectors.toList());
		return collect;
	}
	
	/**
	 * 获取本周周一
	 * @param loalDate
	 * @return
	 */
	public static LocalDate getPreviousOrSame_MONDAY(LocalDate loalDate){
		return loalDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
	}
	
	/**
	 * 获取本周周日
	 * @param loalDate
	 * @return
	 */
	public static LocalDate getNextOrSame_SUNDAY(LocalDate loalDate){
		return loalDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
	}
	
	
	/**
	 * 获取本季度第一天
	 * @param loalDate
	 * @return
	 */
	public static LocalDate getQuarterFirstDay(LocalDate loalDate){
		int currentQuarterFirstMonthValue = YearMonth.from(loalDate).getMonth().firstMonthOfQuarter().getValue();
		return loalDate.withMonth(currentQuarterFirstMonthValue).withDayOfMonth(1);
	}
	/**
	 * 获取本季度最后一天
	 * @param loalDate
	 * @return
	 */
	public static LocalDate getQuarterLastDay(LocalDate loalDate){
		Month  quarterLastMonth = loalDate.getMonth().firstMonthOfQuarter().plus(2);
		return loalDate.withMonth(quarterLastMonth.getValue()).withDayOfMonth(quarterLastMonth.maxLength());
	}
	/**
	 * 获取时间范围
	 * @param periodStatus
	 * @param now
	 * @return
	 */
	public static String[] getPeriod(LocalDatePeriodStatus periodStatus, LocalDate now) {
		String[] arr = new String[2];
		switch (periodStatus) {
		case CURRENT_WEEK:
			arr[0] = getPreviousOrSame_MONDAY(now).toString();
			arr[1] = getNextOrSame_SUNDAY(now).toString();
			break;
		case  CURRENT_MONTH:
			arr[0] = now.with(TemporalAdjusters.firstDayOfMonth()).toString();
			arr[1] = now.with(TemporalAdjusters.lastDayOfMonth()).toString();
			break;
		case CURRENT_QUARTER:
			arr[0] = getQuarterFirstDay(now).toString();
			arr[1] = getQuarterLastDay(now).toString();
			break;
		case PREVIOUS_WEEK:
			LocalDate sunday = getPreviousOrSame_MONDAY(now).minusDays(1);
			arr[0] = getPreviousOrSame_MONDAY(sunday).toString();
			arr[1] = sunday.toString();
			break;
		case PREVIOUS_MONTH:
			LocalDate firstDayOfPerMonth = now.minusMonths(1).withDayOfMonth(1);
			arr[0] = firstDayOfPerMonth.toString();
			YearMonth ym = YearMonth.from(firstDayOfPerMonth);
			arr[1] =firstDayOfPerMonth.withDayOfMonth(ym.lengthOfMonth()).toString();
			break;
		case PREVIOUS_QUARTER:
			LocalDate lastDayOfPerQuarter = getQuarterFirstDay(now).minusDays(1);
			arr[0] =getQuarterFirstDay(lastDayOfPerQuarter).toString();
			arr[1] = lastDayOfPerQuarter.toString();
			break;
		case CURRENT_YEAR:
			arr[0] = now.withMonth(1).withDayOfMonth(1).toString();
			arr[1] = now.toString();
			break;
		case PREVIOUS_YEAR:
			arr[0] = now.minusYears(1).withMonth(1).withDayOfMonth(1).toString();
			arr[1] = now.minusYears(1).toString();
			break;
		case CHART_QUARTER:
			arr[0] = now.withMonth(1).withDayOfMonth(1).toString();
			arr[1] = now.withMonth(12).withDayOfMonth(31).toString();
			break;
		default:
			break;
		}
		return arr;
	}
	
	public static List<LocalDate>  getPeriodLocalDate(LocalDatePeriodStatus periodStatus, LocalDate now) {
		List<LocalDate> arr = new ArrayList<>();
		switch (periodStatus) {
		case CURRENT_WEEK:
			arr.add(getPreviousOrSame_MONDAY(now));
			arr.add(getNextOrSame_SUNDAY(now));
			break;
		case  CURRENT_MONTH:
			arr.add(now.with(TemporalAdjusters.firstDayOfMonth()));
			arr.add(now.with(TemporalAdjusters.lastDayOfMonth()));
			break;
		case CURRENT_QUARTER:
			arr.add(getQuarterFirstDay(now));
			arr.add(getQuarterLastDay(now));
			break;
		case PREVIOUS_WEEK:
			LocalDate sunday = getPreviousOrSame_MONDAY(now).minusDays(1);
			arr.add(getPreviousOrSame_MONDAY(sunday));
			arr.add(sunday);
			break;
		case PREVIOUS_MONTH:
			LocalDate firstDayOfPerMonth = now.minusMonths(1).withDayOfMonth(1);
			arr.add(firstDayOfPerMonth);
			YearMonth ym = YearMonth.from(firstDayOfPerMonth);
			arr.add(firstDayOfPerMonth.withDayOfMonth(ym.lengthOfMonth()));
			break;
		case PREVIOUS_QUARTER:
			LocalDate lastDayOfPerQuarter = getQuarterFirstDay(now).minusDays(1);
			arr.add(getQuarterFirstDay(lastDayOfPerQuarter));
			arr.add(lastDayOfPerQuarter);
			break;
		case CURRENT_YEAR:
			arr.add(now.withMonth(1).withDayOfMonth(1));
			arr.add(now);
			break;
		case PREVIOUS_YEAR:
			arr.add(now.minusYears(1).withMonth(1).withDayOfMonth(1));
			arr.add(now.minusYears(1).withMonth(12).withDayOfMonth(31));
			break;
		default:
			break;
		}
		return arr;
	}
	
	
	public static String formatTitle(LocalDatePeriodStatus staus, LocalDate localDate) {
		StringBuilder builder = new StringBuilder();
		switch (staus) {
		case CURRENT_WEEK:
			 builder.append("（").append(formatWeekOfYear(localDate))
			 .append("，")
			 .append(formatPeriod(getPeriod(staus, localDate))).append("）");
			break;
		case CURRENT_MONTH:
			builder.append("（").append(formatMonthOfYear(localDate))
			.append("，")
			 .append(formatPeriod(getPeriod(staus, localDate))).append("）");
			break;
		case CURRENT_QUARTER:
			builder.append("（").append(formatQuarterOfYear(localDate))
			.append("，")
			 .append(formatPeriod(getPeriod(staus, localDate))).append("）");
			break;
		default:
			break;
		}
		return builder.toString();
	}
	
	
	private static String formatQuarterOfYear(LocalDate localDate) {
		int quarter = (localDate.getMonthValue()+2)/3;
		return localDate.getYear()+"年第"+quarter+"季度";
	}
	private static String formatMonthOfYear(LocalDate localDate) {
		return formatter_yyyyM.format(localDate);
	}
	public static String formatWeekOfYear(LocalDate localDate){
	       int weekOfYear = localDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
	       return localDate.getYear()+"年第"+weekOfYear+"周";
	}
	private static String formatPeriod(String[] period) {
		return formatter_Md.format(LocalDate.parse(period[0]))+"至"+formatter_Md.format(LocalDate.parse(period[1]));
	}

	public static String formatChartTitle(LocalDatePeriodStatus status, LocalDate startTime) {
		String[] period = getPeriod(status, startTime);
		return formatter_M_d.format(LocalDate.parse(period[0]))+"-"+formatter_M_d.format(LocalDate.parse(period[1]));
	}
	
	
	

}
