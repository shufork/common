package me.shufork.common.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.ReadableDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * @see org.joda.time.DateTime
 */
public abstract class DateTimeUtil {

	public static final String ISO8601_FORMAT="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final String ISO8601_SHORT_FORMAT="yyyyMMdd'T'HHmmss.SSS'Z'";
	public static final String DIGITAL_ONLY_FORMAT="yyyyMMddHHmmssSSS";

	/**
	 * 获取当前时间的时间戳(Unix Timestamp,UTC)
	 * @return
	 */
	public static long CurrentTimestamp(){
		return utc().getMillis();
	}

	/**
	 * 获取当前时间(UTC)
	 * @return
	 */
	public static DateTime utc(){
		return DateTime.now(DateTimeZone.UTC);
	}
	/**
	 * 获取当前时间(本地时间)
	 * @return
	 */
	public static LocalDateTime localTime(DateTime dateTime){
		return dateTime.toLocalDateTime();
	}

	/**
	 * 获取当前时间(本地时间)
	 * @return
	 */
	public static LocalDateTime localTime(){
		return localTime(utc());
	}

	/**
	 * 将org.joda.time.DateTime 类型转换为JDK内建的Date类型
	 * @param joda
	 * @return
	 */
	public static Date	ofJdkDate(DateTime joda){
		return joda.toDate();
	}

	/**
	 * 将org.joda.time.LocalDateTime 类型转换为JDK内建的Date类型
	 * @param joda
	 * @return
	 */
	public static Date	toJdkDate(LocalDateTime joda){
		return joda.toDate();
	}

	/**
	 * 格式化输出
	 * @param joda
	 * @param formatter
	 * @return
	 */
	public static String format(ReadableDateTime joda,DateTimeFormatter formatter){
		return formatter.print(joda);
	}

	/**
	 * 格式化输出(ISO8601),如 2012-01-01T23:01:01.999Z
	 * @param joda
	 * @return
	 */
	public static String formatISO8601(ReadableDateTime joda){
		return format(joda,DateTimeFormat.forPattern(ISO8601_FORMAT));
	}

	/**
	 * 格式化输出(ISO8601短格式),如 20120101T230101.999Z
	 * @param joda
	 * @return
	 */
	public static String formatISO8601Short(ReadableDateTime joda){
		return format(joda,DateTimeFormat.forPattern(ISO8601_SHORT_FORMAT));
	}

	/**
	 * 格式化输出(纯数字格式),如 20120101230101999
	 * @param joda
	 * @return
	 */
	public static String formatDigitalOnly(ReadableDateTime joda){
		return format(joda,DateTimeFormat.forPattern(DIGITAL_ONLY_FORMAT));
	}

	/**
	 * 从字符串解析日期对象
	 * @param source
	 * @param formatter
	 * @return
	 */
	public static DateTime parse(String source,DateTimeFormatter formatter){
		return formatter.parseDateTime(source);
	}

	/**
	 * 从字符串解析日期对象,必须是ISO8601格式的字符串,如 2012-01-01T23:01:01.999Z
	 * @param source
	 * @return
	 */
	public static DateTime parseISO8601(String source){
		return parse(source,DateTimeFormat.forPattern(ISO8601_FORMAT));
	}

	/**
	 * 从字符串解析日期对象,必须是ISO8601短格式的字符串,如 20120101T230101.999Z
	 * @param source
	 * @return
	 */
	public static DateTime parseISO8601Short(String source){
		return parse(source,DateTimeFormat.forPattern(ISO8601_SHORT_FORMAT));
	}


}
