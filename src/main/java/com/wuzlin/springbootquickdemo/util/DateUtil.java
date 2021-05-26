package com.wuzlin.springbootquickdemo.util;
//

import com.asiainfo.bankverify.enums.DateStyle;
import com.asiainfo.bankverify.enums.Week;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

	private final static String DATE_FORMATE_STYLE = "yyyy-MM-dd";
	private final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	private final static String HH_MM_SS = "HH:mm:ss";
	private final static String HHMMSS = "HHmmss";
	private final static String yyyyMMddHH24miss="yyyyMMddHHmmss";
	private final static String yyyyMMdd="yyyyMMdd";

	 public final static char[] UPPER = "零一二三四五六七八九十".toCharArray();

	/**
     * @return 获得当前Calendar
     */
    public static Calendar getCalendar(){
        return Calendar.getInstance();
    }
    /**
     * @return 获得今年
     */
    public static int getThisYear(){
        return getCalendar().get(Calendar.YEAR);
    }
    /**
     * @return 获得本月
     */
    public static int getThisMonth(){
        return getCalendar().get(Calendar. MONTH)+1;
    }
    /**
     * 获取当天的日期
     * @return
     */
    public static int getThisDay(){
    	return getCalendar().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @return 获得当前时间
     */
    public static Date getNow(){
        return getCalendar().getTime();
    }

    /**
     * 根据传入的时间戳转换为Date对象
     * @param date
     * @return
     */
    public static Date getDate(long date){
    	return new Date(date);
    }

    /**
     * 将传入的时间戳转换为对应时间的字符串
     * @param date
     * @return
     */
    public static String getDateStr(long date){
    	return new SimpleDateFormat(DATE_FORMATE_STYLE).format(getDate(date));
    }

    public static String getDateStr(Date date){
    	return new SimpleDateFormat(DATE_FORMATE_STYLE).format(date);
    }

    public static String getYyyyMmDdHhMmSs(Date date){
    	return new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).format(date);
    }

    public static String getYyyyMmDdHhMmSs(){

		Date date = new Date();
    	return new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).format(date);
    }

	public static String getYyyyMmDdHhMmSs1(){

		Date date = new Date();
		return new SimpleDateFormat(yyyyMMddHH24miss).format(date);
	}

    /**
     * 获取yyyyMMdd格式的当前时间
     * @return
     */
    public static String getYMDStr(){
    	return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    /**
     * 将字符串yyyyMMdd，转换为yyyy-MM-dd格式
     * @param date 字符串格式yyyy-MM-dd
     * @return
     */
    public static String getYMDStr(String date) throws Exception{
    	Date time = StringToDate(date, "yyyyMMdd");
    	return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 将yyyyMMdd类的格式，转换为yyyy-MM-dd hh:mm:ss格式
     * @param date
     * @return
     */
    public static String getYMDOStr(String date) {
    	String dataType = "yyyy-MM-dd";
    	if (!StringUtils.isEmpty(date)) {
    		Date time = StringToDate(date, "yyyyMMdd");
    		if (date.length()==6) {
    			dataType = "yyyy-MM";
    			time = StringToDate(date, "yyyyMM");
    		} else if (date.length()==8) {
    			dataType = "yyyy-MM-dd";
    			time = StringToDate(date, "yyyyMMdd");
    		} else if (date.length()==10) {
    			dataType = "yyyy-MM-dd HH";
    			time = StringToDate(date, "yyyyMMddhh");
    		} else if (date.length()==12) {
    			dataType = "yyyy-MM-dd HH:mm";
    			time = StringToDate(date, "yyyyMMddHHmm");
    		} else if (date.length()==14) {
    			dataType = "yyyy-MM-dd HH:mm:ss";
    			time = StringToDate(date, "yyyyMMddHHmmss");
    		}
        	return new SimpleDateFormat(dataType).format(time);
    	}
    	return "";
    }


    /**
     * 将日期格式化为指定格式的字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String formateDate(Date date, String pattern){
    	if(date == null || pattern == null || pattern.length() == 0){
    		throw new NullPointerException("日期或者格式化的模式不能为空。");
    	}
    	return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 将日期格式化为指定格式的字符串
     * @param dateStr
     * @param pattern1 旧格式
     * @param pattern2 新格式
     * @return
     */
    public static String formateDate(String dateStr, String pattern1, String pattern2)throws Exception{
    	if(dateStr == null ||dateStr.length()==0 || pattern1 == null || pattern1.length() == 0){
    		throw new NullPointerException("日期或者格式化的模式不能为空。");
    	}
    	Date date1 = new SimpleDateFormat(pattern1).parse(dateStr);
    	return formateDate(date1, pattern2);
    }

    /**
     * 将字符串转换成时间
     * @param date				字符串时间
     * @param pattern			转换的时间格式
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date, String pattern) throws ParseException {
    	return new SimpleDateFormat(pattern).parse(date);
    }

    /**
     * begin of CustomerIncreaseController day, 2015-01-03 00:00:00
     * @param date
     * @return
     */
    public static Date getBeginTime(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		return c.getTime();
    }

    /**
     * end of CustomerIncreaseController day, 2015-01-03 23:59:59
     * @param date
     * @return
     */
    public static Date getEndTime(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);

		return c.getTime();
    }


    public static long getLongTime(){
    	return System.currentTimeMillis() / 1000;
	}

	public static int getIntTime(){
		return (int)(System.currentTimeMillis() / 1000);
	}

	/**
	 * 获取SimpleDateFormat
	 * @param parttern 日期格式
	 * @return SimpleDateFormat对象
	 * @throws RuntimeException 异常：非法日期格式
	 */
	private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {
		return new SimpleDateFormat(parttern);
	}

	/**
	 * 获取日期中的某数值。如获取月份
	 * @param date 日期
	 * @param dateType 日期格式
	 * @return 数值
	 */
	private static int getInteger(Date date, int dateType) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(dateType);
	}

	/**
	 * 增加日期中某类型的某数值。如增加日期
	 * @param date 日期字符串
	 * @param dateType 类型
	 * @param amount 数值
	 * @return 计算后日期字符串
	 */
	public static String addInteger(String date, int dateType, int amount)  throws Exception{
		String dateString = null;
		DateStyle dateStyle = getDateStyle(date);
		if (dateStyle != null) {
			Date myDate = StringToDate(date, dateStyle);
			myDate = addInteger(myDate, dateType, amount);
			dateString = DateToString(myDate, dateStyle);
		}
		return dateString;
	}

	/**
	 * 日期加减
	 * @param date
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static String addDays(String date,  int amount) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		calendar.add(calendar.DATE, amount);
		date1 = calendar.getTime();
		return sdf.format(date1);

	}

	/**
	 * 周的加减
	 * @param date
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static String addWeek(String date,  int amount) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		calendar.add(calendar.WEEK_OF_MONTH, amount);
		date1 = calendar.getTime();
		return sdf.format(date1);

	}

	/**
	 * 月的增加减少
	 * @param date
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static String addMonths(String date,  int amount) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		calendar.add(calendar.MONTH, amount);
		date1 = calendar.getTime();
		return sdf.format(date1);

	}





	/**
	 * 增加日期中某类型的某数值。如增加日期
	 * @param date 日期
	 * @param dateType 类型
	 * @param amount 数值
	 * @return 计算后日期
	 */
	public static Date addInteger(Date date, int dateType, int amount) {
		Date myDate = null;
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(dateType, amount);
			myDate = calendar.getTime();
		}
		return myDate;
	}

	/**
	 * 获取精确的日期
	 * @param timestamps 时间long集合
	 * @return 日期
	 */
	private static Date getAccurateDate(List<Long> timestamps) {
		Date date = null;
		long timestamp = 0;
		Map<Long, long[]> map = new HashMap<Long, long[]>();
		List<Long> absoluteValues = new ArrayList<Long>();

		if (timestamps != null && timestamps.size() > 0) {
			if (timestamps.size() > 1) {
				for (int i = 0; i < timestamps.size(); i++) {
					for (int j = i + 1; j < timestamps.size(); j++) {
						long absoluteValue = Math.abs(timestamps.get(i) - timestamps.get(j));
						absoluteValues.add(absoluteValue);
						long[] timestampTmp = { timestamps.get(i), timestamps.get(j) };
						map.put(absoluteValue, timestampTmp);
					}
				}

				// 有可能有相等的情况。如2012-11和2012-11-01。时间戳是相等的
				long minAbsoluteValue = -1;
				if (!absoluteValues.isEmpty()) {
					// 如果timestamps的size为2，这是差值只有一个，因此要给默认值
					minAbsoluteValue = absoluteValues.get(0);
				}
				for (int i = 0; i < absoluteValues.size(); i++) {
					for (int j = i + 1; j < absoluteValues.size(); j++) {
						if (absoluteValues.get(i) > absoluteValues.get(j)) {
							minAbsoluteValue = absoluteValues.get(j);
						} else {
							minAbsoluteValue = absoluteValues.get(i);
						}
					}
				}

				if (minAbsoluteValue != -1) {
					long[] timestampsLastTmp = map.get(minAbsoluteValue);
					if (absoluteValues.size() > 1) {
						timestamp = Math.max(timestampsLastTmp[0], timestampsLastTmp[1]);
					} else if (absoluteValues.size() == 1) {
						// 当timestamps的size为2，需要与当前时间作为参照
						long dateOne = timestampsLastTmp[0];
						long dateTwo = timestampsLastTmp[1];
						if ((Math.abs(dateOne - dateTwo)) < 100000000000L) {
							timestamp = Math.max(timestampsLastTmp[0], timestampsLastTmp[1]);
						} else {
							long now = new Date().getTime();
							if (Math.abs(dateOne - now) <= Math.abs(dateTwo - now)) {
								timestamp = dateOne;
							} else {
								timestamp = dateTwo;
							}
						}
					}
				}
			} else {
				timestamp = timestamps.get(0);
			}
		}

		if (timestamp != 0) {
			date = new Date(timestamp);
		}
		return date;
	}

	/**
	 * 判断字符串是否为日期字符串
	 * @param date 日期字符串
	 * @return true or false
	 */
	public static boolean isDate(String date)  throws Exception{
		boolean isDate = false;
		if (date != null) {
			if (StringToDate(date) != null) {
				isDate = true;
			}
		}
		return isDate;
	}

	/**
	 * 获取日期字符串的日期风格。失敗返回null。
	 * @param date 日期字符串
	 * @return 日期风格
	 */
	public static DateStyle getDateStyle(String date)  throws Exception{
		DateStyle dateStyle = null;
		Map<Long, DateStyle> map = new HashMap<Long, DateStyle>();
		List<Long> timestamps = new ArrayList<Long>();
		for (DateStyle style : DateStyle.values()) {
			Date dateTmp = StringToDate(date, style.getValue());
			if (dateTmp != null) {
				timestamps.add(dateTmp.getTime());
				map.put(dateTmp.getTime(), style);
			}
		}
		dateStyle = map.get(getAccurateDate(timestamps).getTime());
		return dateStyle;
	}

	/**
	 * 将日期字符串转化为日期。失败返回null。
	 * @param date 日期字符串
	 * @return 日期
	 */
	public static Date StringToDate(String date) throws Exception{
		DateStyle dateStyle = null;
		return StringToDate(date, dateStyle);
	}

	/**
	 * 将日期字符串转化为日期。失败返回null。
	 * @param date 日期字符串
	 * @param parttern 日期格式
	 * @return 日期
	 */
	public static Date StringToDate(String date, String parttern)  {
		Date myDate = null;
		if (date != null) {
			try {
				myDate = getDateFormat(parttern).parse(date);
			} catch (Exception e) {
			}
		}
		return myDate;
	}

	/**
	 * 将日期字符串转化为日期。失败返回null。
	 * @param date 日期字符串
	 * @param dateStyle 日期风格
	 * @return 日期
	 */
	public static Date StringToDate(String date, DateStyle dateStyle)  throws Exception{
		Date myDate = null;
		if (dateStyle == null) {
			List<Long> timestamps = new ArrayList<Long>();
			for (DateStyle style : DateStyle.values()) {
				Date dateTmp = StringToDate(date, style.getValue());
				if (dateTmp != null) {
					timestamps.add(dateTmp.getTime());
				}
			}
			myDate = getAccurateDate(timestamps);
		} else {
			myDate = StringToDate(date, dateStyle.getValue());
		}
		return myDate;
	}

	/**
	 * 将日期转化为日期字符串。失败返回null。
	 * @param date 日期
	 * @param parttern 日期格式
	 * @return 日期字符串
	 */
	public static String DateToString(Date date, String parttern) {
		String dateString = null;
		if (date != null) {
			try {
				dateString = getDateFormat(parttern).format(date);
			} catch (Exception e) {
			}
		}
		return dateString;
	}

	/**
	 * 将日期转化为日期字符串。失败返回null。
	 * @param date 日期
	 * @param dateStyle 日期风格
	 * @return 日期字符串
	 */
	public static String DateToString(Date date, DateStyle dateStyle) {

		String dateString = null;
		if (dateStyle != null) {
			dateString = DateToString(date, dateStyle.getValue());
		}
		return dateString;
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date 旧日期字符串
	 * @param parttern 新日期格式
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, String parttern) throws Exception{
		return StringToString(date, null, parttern);
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date 旧日期字符串
	 * @param dateStyle 新日期风格
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, DateStyle dateStyle)  throws Exception{
		return StringToString(date, null, dateStyle);
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date 旧日期字符串
	 * @param olddParttern 旧日期格式
	 * @param newParttern 新日期格式
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, String olddParttern, String newParttern) throws Exception {
		String dateString = null;
		if (olddParttern == null) {
			DateStyle style = getDateStyle(date);
			if (style != null) {
				Date myDate = StringToDate(date, style.getValue());
				dateString = DateToString(myDate, newParttern);
			}
		} else {
			Date myDate = StringToDate(date, olddParttern);
			dateString = DateToString(myDate, newParttern);
		}
		return dateString;
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date 旧日期字符串
	 * @param olddDteStyle 旧日期风格
	 * @param newDateStyle 新日期风格
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, DateStyle olddDteStyle, DateStyle newDateStyle) throws Exception{
		String dateString = null;
		if (olddDteStyle == null) {
			DateStyle style = getDateStyle(date);
			dateString = StringToString(date, style.getValue(), newDateStyle.getValue());
		} else {
			dateString = StringToString(date, olddDteStyle.getValue(), newDateStyle.getValue());
		}
		return dateString;
	}

	/**
	 * 增加日期的年份。失败返回null。
	 * @param date 日期
	 * @param yearAmount 增加数量。可为负数
	 * @return 增加年份后的日期字符串
	 */
	public static String addYear(String date, int yearAmount) throws Exception{
		return addInteger(date, Calendar.YEAR, yearAmount);
	}

	/**
	 * 增加日期的年份。失败返回null。
	 * @param date 日期
	 * @param yearAmount 增加数量。可为负数
	 * @return 增加年份后的日期
	 */
	public static Date addYear(Date date, int yearAmount) {
		return addInteger(date, Calendar.YEAR, yearAmount);
	}

	/**
	 * 增加日期的月份。失败返回null。
	 * @param date 日期
	 * @param yearAmount 增加数量。可为负数
	 * @return 增加月份后的日期字符串
	 */
	public static String addMonth(String date, int yearAmount) throws Exception{
		return addInteger(date, Calendar.MONTH, yearAmount);
	}

	/**
	 * 增加日期的月份。失败返回null。
	 * @param date 日期
	 * @param yearAmount 增加数量。可为负数
	 * @return 增加月份后的日期
	 */
	public static Date addMonth(Date date, int yearAmount) {
		return addInteger(date, Calendar.MONTH, yearAmount);
	}

	/**
	 * 增加日期的天数。失败返回null。
	 * @param date 日期字符串
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加天数后的日期字符串
	 */
	public static String addDay(String date, int dayAmount) throws Exception{
		return addInteger(date, Calendar.DATE, dayAmount);
	}

	/**
	 * 增加日期的天数。失败返回null。
	 * @param date 日期
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加天数后的日期
	 */
	public static Date addDay(Date date, int dayAmount) {
		return addInteger(date, Calendar.DATE, dayAmount);
	}

	/**
	 * 增加日期的小时。失败返回null。
	 * @param date 日期字符串
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加小时后的日期字符串
	 */
	public static String addHour(String date, int hourAmount) throws Exception{
		return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
	}

	/**
	 * 增加日期的小时。失败返回null。
	 * @param date 日期
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加小时后的日期
	 */
	public static Date addHour(Date date, int hourAmount) {
		return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
	}

	/**
	 * 增加日期的分钟。失败返回null。
	 * @param date 日期字符串
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加分钟后的日期字符串
	 */
	public static String addMinute(String date, int hourAmount) throws Exception{
		return addInteger(date, Calendar.MINUTE, hourAmount);
	}

	/**
	 * 增加日期的分钟。失败返回null。
	 * @param date 日期
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加分钟后的日期
	 */
	public static Date addMinute(Date date, int hourAmount) {
		return addInteger(date, Calendar.MINUTE, hourAmount);
	}

	/**
	 * 增加日期的秒钟。失败返回null。
	 * @param date 日期字符串
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加秒钟后的日期字符串
	 */
	public static String addSecond(String date, int hourAmount) throws Exception{
		return addInteger(date, Calendar.SECOND, hourAmount);
	}

	/**
	 * 增加日期的秒钟。失败返回null。
	 * @param date 日期
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加秒钟后的日期
	 */
	public static Date addSecond(Date date, int hourAmount) {
		return addInteger(date, Calendar.SECOND, hourAmount);
	}

	/**
	 * 获取日期的年份。失败返回0。
	 * @param date 日期字符串
	 * @return 年份
	 */
	public static int getYear(String date) throws Exception{
		return getYear(StringToDate(date));
	}

	/**
	 * 获取日期的年份。失败返回0。
	 * @param date 日期
	 * @return 年份
	 */
	public static int getYear(Date date) {
		return getInteger(date, Calendar.YEAR);
	}

	/**
	 * 获取日期的月份。失败返回0。
	 * @param date 日期字符串
	 * @return 月份
	 */
	public static int getMonth(String date) throws Exception{
		return getMonth(StringToDate(date));
	}

	/**
	 * 获取日期的月份。失败返回0。
	 * @param date 日期
	 * @return 月份
	 */
	public static int getMonth(Date date) {
		return getInteger(date, Calendar.MONTH);
	}

	/**
	 * 获取日期的天数。失败返回0。
	 * @param date 日期字符串
	 * @return 天
	 */
	public static int getDay(String date) throws Exception{
		return getDay(StringToDate(date));
	}

	/**
	 * 获取日期的天数。失败返回0。
	 * @param date 日期
	 * @return 天
	 */
	public static int getDay(Date date) {
		return getInteger(date, Calendar.DATE);
	}

	/**
	 * 获取日期的小时。失败返回0。
	 * @param date 日期字符串
	 * @return 小时
	 */
	public static int getHour(String date) throws Exception{
		return getHour(StringToDate(date));
	}

	/**
	 * 获取日期的小时。失败返回0。
	 * @param date 日期
	 * @return 小时
	 */
	public static int getHour(Date date) {
		return getInteger(date, Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取日期的分钟。失败返回0。
	 * @param date 日期字符串
	 * @return 分钟
	 */
	public static int getMinute(String date) throws Exception{
		return getMinute(StringToDate(date));
	}

	/**
	 * 获取日期的分钟。失败返回0。
	 * @param date 日期
	 * @return 分钟
	 */
	public static int getMinute(Date date) {
		return getInteger(date, Calendar.MINUTE);
	}

	/**
	 * 获取日期的秒钟。失败返回0。
	 * @param date 日期字符串
	 * @return 秒钟
	 */
	public static int getSecond(String date) throws Exception{
		return getSecond(StringToDate(date));
	}

	/**
	 * 获取日期的秒钟。失败返回0。
	 * @param date 日期
	 * @return 秒钟
	 */
	public static int getSecond(Date date) {
		return getInteger(date, Calendar.SECOND);
	}

	/**
	 * 获取日期的时间戳 失败返回0
	 * @param date 日期字符串，格式yyyy-MM-dd
	 * @return 时间戳
	 */
	public static long getMillis(String date) throws Exception{
		if (date==null || date.trim().equals("")) return 0;
		return getMillis(StringToDate(date,"yyyy-MM-dd"));
	}

	/**
	 * 获取日期的时间戳 失败返回0
	 * @param date 日期
	 * @return 时间戳
	 */
	public static long getMillis(Date date) {
		if (date==null) return 0;
		return date.getTime();
	}

	/**
	 * 获取日期 。默认yyyy-MM-dd格式。失败返回null。
	 * @param date 日期字符串
	 * @return 日期
	 */
	public static String getDate(String date) throws Exception{
		return StringToString(date, DateStyle.YYYY_MM_DD);
	}

	/**
	 * 获取日期。默认yyyy-MM-dd格式。失败返回null。
	 * @param date 日期
	 * @return 日期
	 */
	public static String getDate(Date date) {
		return DateToString(date, DateStyle.YYYY_MM_DD);
	}
	/**
	 * 获取日期。默认yyyy-MM-dd格式。失败返回null。
	 * @param date 日期
	 * @return 日期
	 */
	public static String getDate(Date date,String part) throws Exception{
		return DateToString(date, DateStyle.queryEnum(part));
	}

	/**
	 * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
	 * @param date 日期字符串
	 * @return 时间
	 */
	public static String getTime(String date) throws Exception{
		return StringToString(date, DateStyle.HH_MM_SS);
	}

	/**
	 * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
	 * @param date 日期
	 * @return 时间
	 */
	public static String getTime(Date date) throws Exception{
		return DateToString(date, DateStyle.HH_MM_SS);
	}

	/**
	 * 获取日期的星期。失败返回null。
	 * @param date 日期字符串
	 * @return 星期
	 */
	public static Week getWeek(String date) throws Exception{
		Week week = null;
		DateStyle dateStyle = getDateStyle(date);
		if (dateStyle != null) {
			Date myDate = StringToDate(date, dateStyle);
			week = getWeek(myDate);
		}
		return week;
	}

	/**
	 * 获取日期的星期。失败返回null。
	 * @param date 日期
	 * @return 星期
	 */
	public static Week getWeek(Date date) {
		Week week = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		switch (weekNumber) {
		case 0:
			week = Week.SUNDAY;
			break;
		case 1:
			week = Week.MONDAY;
			break;
		case 2:
			week = Week.TUESDAY;
			break;
		case 3:
			week = Week.WEDNESDAY;
			break;
		case 4:
			week = Week.THURSDAY;
			break;
		case 5:
			week = Week.FRIDAY;
			break;
		case 6:
			week = Week.SATURDAY;
			break;
		}
		return week;
	}

	/**
	 * 获取两个日期相差的天数
	 * @param date 日期字符串
	 * @param otherDate 另一个日期字符串
	 * @return 相差天数
	 */
	public static int getIntervalDays(String date, String otherDate) throws Exception{
		return getIntervalDays(StringToDate(date), StringToDate(otherDate));
	}


	/**
	 * 获取两个日期相差的天数 yyyy-MM-dd
	 * @param date 日期字符串
	 * @param otherDate 另一个日期字符串
	 * @return 相差天数
	 */
	public static int getIntervalBetweenDays(String date, String otherDate) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return getIntervalDays(sdf.parse(date), sdf.parse(otherDate));
	}

	/**
	 * @param date 日期
	 * @param otherDate 另一个日期
	 * @return 相差天数
	 */
	public static int getIntervalDays(Date date, Date otherDate) {
	//	date = DateUtil.StringToDate(DateUtil.getDate(date));
		long time = Math.abs(date.getTime() - otherDate.getTime());
		return (int)(time/(24 * 60 * 60 * 1000));
	}

	/**
	 * 两个时间相比较，若第一个大于第二个，则返回1，相等返回0，小于返回-1
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compare(Date date1, Date date2){
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		if(time1 == time2){
			return 0;
		}else if(time1 > time2){
			return 1;
		}else{
			return -1;
		}
	}

	/**
	 * 获取当月第一天
	 * @return
	 */
	public static String firstDayOnMonth(){
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		  Calendar c = Calendar.getInstance();
	      c.add(Calendar.MONTH, 0);
	      c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
	      String first = format.format(c.getTime());
	      return first;
	}

	/**
	 * 获取当月最后一天
	 * @return
	 */
	public static String lastDayOnMonth(){
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 Calendar ca = Calendar.getInstance();
	     ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
	     String last = format.format(ca.getTime());
	      return last;
	}

	/**
	 * 获取30天前的日期
	 * @return
	 */
	public static String getBeforThirtyDay(){
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 Calendar ca = Calendar.getInstance();
		 ca.add(Calendar.DATE, -30);
	     String beforThirtyDay = format.format(ca.getTime());
	      return beforThirtyDay;
	}

	/**
	 * 获取指定日期的指定天数前的日期字符串
	 * @param dateStr 当前日期字符串
	 * @param dayAmount 当前日期延后天数（可为负数）
	 * @return
	 */
	public static String getBeforDay(String dateStr, int dayAmount) throws Exception{
		Date date = DateUtil.StringToDate(dateStr,"yyyy-MM-dd");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DATE, dayAmount);
	    String beforThirtyDay = format.format(ca.getTime());
	    return beforThirtyDay;
	}

	/**
	 * 获取指定日期的指定天数前的日期字符串
	 * @param dateStr 当前日期字符串
	 * @param monthAmount 当前日期延后天数（可为负数）
	 * @return
	 */
	public static String getBeforMonth(String dateStr, int monthAmount){
		Date date = DateUtil.StringToDate(dateStr,"yyyy-MM-dd");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MONTH, monthAmount);
	    String beforThirtyDay = format.format(ca.getTime());
	    return beforThirtyDay;
	}

	/**
	 * 获取当前的日期
	 * @return
	 */
	public static String getToday(){
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 Calendar ca = Calendar.getInstance();
	     String beforThirtyDay = format.format(ca.getTime());
	      return beforThirtyDay;
	}


	/**
	 * 获取最近七天的日期
	 * @return
	 */
	public static List<String> getLastSevenDays() throws Exception{
		List<String> sevenDaysList = new ArrayList<>();
		for(int i = -6 ; i <= 0 ; i ++){
			String date = getDate(addDay(new Date(), i));
			sevenDaysList.add(date);
		}
		return sevenDaysList;
	}

	/**
     * 根据小写数字格式的日期转换成大写格式的日期
     * @param date 字符串格式的日期，支持 yyyy-MM-dd yyyyMMdd yyyy/MM/dd
     * @return
     */
    public static String getUpperDate(String date) {
        //支持yyyy-MM-dd、yyyy/MM/dd、yyyyMMdd等格式
        if(date == null) return null;
        //非数字的都去掉
        date = date.replaceAll("\\D", "");
        if(date.length() != 8) return null;
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<4;i++) {//年
            sb.append(UPPER[Integer.parseInt(date.substring(i, i+1))]);
        }
        sb.append("年");//拼接年
        int month = Integer.parseInt(date.substring(4, 6));
        if(month <= 10) {
            sb.append(UPPER[month]);
        } else {
            sb.append("十").append(UPPER[month%10]);
        }
        sb.append("月");//拼接月

        int day = Integer.parseInt(date.substring(6));
        if (day <= 10) {
            sb.append(UPPER[day]);
        } else if(day < 20) {
            sb.append("十").append(UPPER[day % 10]);
        } else {
            sb.append(UPPER[day / 10]).append("十");
            int tmp = day % 10;
            if (tmp != 0) sb.append(UPPER[tmp]);
        }
        sb.append("日");//拼接日
        return sb.toString();
    }

    /**
	 * 两个时间相比较，若第一个大于第二个，则返回1，相等返回0，小于返回-1
	 * @param date1 YYYY_MM_DD_HH_MM_SS
	 * @param date2 YYYY_MM_DD_HH_MM_SS
	 * @return
	 */
	public static int compare(String d1, String d2) throws Exception{
		Date date1 = StringToDate(d1, YYYY_MM_DD_HH_MM_SS);
		Date date2 = StringToDate(d2,YYYY_MM_DD_HH_MM_SS);
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		if(time1 == time2){
			return 0;
		}else if(time1 > time2){
			return 1;
		}else{
			return -1;
		}
	}


    /**
     *
     * @param
     * @return
     */
	public static String getTime(long l) throws Exception{
		Date date= new Date(l);
		return DateToString(date,DateUtil.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取过去第几天的日期(- 操作) 或者 未来 第几天的日期( + 操作)
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = format.format(today);
		return result;
	}

	/**
	 * 获取过去第几小时的日期( +操作)
	 * @param ihour
	 * @return
	 */
	public static String getBeforeHourTime(int ihour){
		String returnstr = "";
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - ihour);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		returnstr = df.format(calendar.getTime());

		return returnstr;
	}


	public static String getPastNewDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String result = format.format(today);
		return result;
	}

	/**
	 * 秒转时分秒格式
	 * @param time
	 * @return
	 */
	public static String timeSecondFormat(Integer time){
		if(time==null){
			return "";
		}else{
			int s = time % 60;
			int m = time / 60;
			int h = 0;
			if (m >= 60) {
				h = m / 60;
				m = s % 60;
			}
			String timeString = "";
			if (h != 0) {
				timeString = h + "小时" + m + "分" + s+"秒";
			} else if(h== 0&& m!=0){
				timeString = m + "分" + s+"秒";
			}else if (h== 0&& m==0){
				timeString=s+"秒";
			}
			return timeString;

		}
	}

    /**
     * 将yyyyMMddHHmmss 转换成 YYYY_MM_DD_HH_MM_SS
     * @param date
     * @return
     */
    public static String stringToStringByReg(String date){
        String reg = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
        date = date.replaceAll(reg, "$1-$2-$3 $4:$5:$6");
        return date;
    }

	/**
	 *
	 * @param date 原始日期时间
	 * @param clazz 结果日期时间类型  Data  Long String
	 * @param oldStyle 原始时间串格式
	 * @param newStyle 结果时间串格式
	 * @return Object 时间结果，调用结果做类型转换
	 */
    public static Object DateFormat(Object date, Class clazz, String oldStyle, String newStyle) throws Exception{
        SimpleDateFormat oldStyleFormat = new SimpleDateFormat(oldStyle==null?"":oldStyle);
        SimpleDateFormat newStyleFormat = new SimpleDateFormat(newStyle==null?"":newStyle);
		if ("String".equals(clazz.getSimpleName())){
			//时间对象转串
			if (date instanceof Date){
				return newStyleFormat.format(date);
			}
			//时间串转串
			if (date instanceof String){
				return newStyleFormat.format(oldStyleFormat.parse((String)date));
			}
			//时间戳转串
			if (date instanceof Long){
				return newStyleFormat.format(new Date(((Long) date).longValue()));
			}
		}
		else if ("Date".equals(clazz.getSimpleName())){
			//时间对象转时间
			if (date instanceof Date){
				return date;
			}
			//时间串转时间
			if (date instanceof String){
				return oldStyleFormat.parse((String)date);
			}
			//时间戳转时间
			if (date instanceof Long){
				return new Date(((Long) date).longValue());
			}
		}else if ("Long".equals(clazz.getSimpleName())){
			//时间对象转时间戳
			if (date instanceof Date){
				return ((Date) date).getTime();
			}
			//时间串转时间戳
			if (date instanceof String){
				return oldStyleFormat.parse((String)date).getTime();
			}
			//时间戳转时间戳
			if (date instanceof Long){
				return date;
			}
		}
		return null;
	}

	public static Integer getAddTime(String endDate, String startDate) throws Exception{
		long time = Math.abs(DateUtil.getMillis(endDate) - DateUtil.getMillis(startDate));
		return  (int)(time/(24 * 60 * 60 * 1000));
	}

	/**
	 * 返回当前月的202004格式
	 * @return
	 */
	public static String getQueryMonth(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
		return simpleDateFormat.format(new Date());
	}

	/**
	 * 返回date经过Calendar计算之后的202005格式
	 * @param date
	 * @param calendarType
	 * @param value
	 * @return
	 */
	public static String getQueryMonth(Date date,int calendarType,Integer value){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendarType, value);//当前时间减去一年，即一年前的时间    
		calendar.getTime();
		return simpleDateFormat.format(calendar.getTime());
	}

	/**
	 * 返回date经过Calendar计算之后的202005格式
	 * @param date
	 * @param calendarType
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	public static String getQueryMonth(String date,int calendarType,Integer value) throws ParseException {
		return queryCustom(date,"yyyyMM",calendarType,value);
	}


	public static String getQueryDay(String date,int calendarType,Integer value) throws ParseException {
		return queryCustom(date,"yyyyMMdd",calendarType,value);
	}

	public static String queryCustom(String date,String dateStyle,int calendarType,Integer value) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateStyle);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(simpleDateFormat.parse(date));
		calendar.add(calendarType, value);
		calendar.getTime();
		return simpleDateFormat.format(calendar.getTime());
	}

	/**
	 * 获得时间10分钟的整数时间点
	 * @return
	 */
	public static String getTenMinutePoint(String time) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date =sdf.parse(time);
		calendar.setTime(date);
		calendar.setTime(date);
		int minute = calendar.get(Calendar.MINUTE);
		//计算10的整数分钟
		minute = Math.round(minute/10*10);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, 0);
		String s = new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
		return s;
	}

	/**
	 * 获取时间值
	 * @param dataTime
	 * @return
	 */
	public static Timestamp getTimeStamp(String dataTime){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime ldt = LocalDateTime.parse(dataTime,dtf);
		DateTimeFormatter fa = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String datetime2 = ldt.format(fa);

		Timestamp ts=new Timestamp(System.currentTimeMillis());

		ts=ts.valueOf(datetime2);
		return ts;
	}

}
