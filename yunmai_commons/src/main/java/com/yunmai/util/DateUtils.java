package com.yunmai.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

public class DateUtils {
	static SimpleDateFormat lFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	//yyyy-MM-dd HH24:mi:ss
	public static final String lsForamt = "yyyy-MM-dd HH:mm:ss";
	public static final String medianFormat = "yyyy-MM-dd";

	public static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");

	/** 本地化 */
	private static Locale locale = Locale.SIMPLIFIED_CHINESE;

	/** 缺省的DateFormat对象，可以将一个java.util.Date格式化成 HH:mm:ss 输出 */
	private static DateFormat timeDF = DateFormat.getTimeInstance(
			DateFormat.MEDIUM, locale);

	/**
	 * 得到指定月后的日期
	 */
	public static String getCreateTime() {
		return Long.toString(new Date().getTime());
	}

	public static String getRandomNum() {
        int max=1000000000;
        Random random = new Random();
        int s = random.nextInt(max);
        return s+"";
	}
	/**
	 * 得到指定月后的日期
	 */
	public static String getAfterMonth(int month, String dateValue) {
		if (StringUtil.isNotEmpty(dateValue)) {
			if(dateValue.contains("/")){
				dateValue = dateValue.replace("/", "-");
			}
			Calendar c = Calendar.getInstance();// 获得一个日历的实例
			Date date = null;
			try {
				date = sFormat.parse(dateValue);// 初始日期
				c.setTime(date);// 设置日历时间
				c.add(Calendar.MONTH, month);// 在日历的月份上增加6个月
				String strDate = sFormat.format(c.getTime());// 的到你想要得6个月后的日期
				return strDate;

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

		} else {
			return null;
		}
	}

	/****
	 * 将时间转换成 yyyy-MM-dd hh:mm:ss的字符串 <br>
	 * author：xuxiaoai <br>
	 * date：Mar 5, 2012 10:43:34 AM <br>
	 * version：V1.0.0
	 * 
	 * @param date
	 * @return： <br>
	 *          ModifyRecord： <br>
	 *          1、Administrator - Mar 5, 2012 10:43:34 AM ：
	 */
	public static String formateDate(Date date) {
		return lFormat.format(date);
	}
	
	public static String formateShortDate(Date date) {
		return sFormat.format(date);
	}
	
	public static Date nowDate(){
		Date date =null;
		try {
			date= lFormat.parse(formateDate(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/***
	 * 获得24小时之前的时间 <br>
	 * author：xuxiaoai <br>
	 * date：Mar 5, 2012 10:46:30 AM <br>
	 * version：V1.0.0
	 * 
	 * @return： <br>
	 *          ModifyRecord： <br>
	 *          1、Administrator - Mar 5, 2012 10:46:30 AM ：
	 */
	public static Date get24BeforeTime() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date time = cal.getTime();
		return time;
	}

	/**
	 * 转换字符串类型的时间为指定格式的Date类型，
	 * 
	 * @param dateString
	 *            ：日期字符串
	 * @param formateString
	 *            :要转换的日期格式
	 * @return：
	 */
	public static Date formaDateByString(String dateString, String formateString) {
		SimpleDateFormat df = new SimpleDateFormat(formateString);
		try {
			Date date = df.parse(dateString);
			return date;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 将时间转换成指定格式的字符串 <br>
	 * 
	 * @param date
	 *            ：要转换的日期
	 * @param formateString
	 *            ：要转换的日期格式
	 * @return： <br>
	 */
	public static String formaDateToString(Date date, String formateString) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formateString);
			return sdf.format(date);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 获取指定日期N几年之后的日期 <br>
	 * author：xuxiaoai <br>
	 * date：Aug 17, 2012 11:46:35 AM <br>
	 * version：V1.0.0
	 * 
	 * @param dateString
	 * @param year
	 * @return： <br>
	 *          ModifyRecord： <br>
	 *          1、Administrator - Aug 17, 2012 11:46:35 AM ：
	 */
	public static String getYearsDate(String dateString, Long year) {
		Date priDate;
		Date afterDate = new Date();
		if (StringUtil.isNotEmpty(dateString)) {
			try {
				priDate = lFormat.parse(dateString + " 00:00:00");
				// System.out.println("查看添加n年后的时间:"+dateString);
				// System.out.println("查看原时间的:"+priDate.getTime());
				Long years = year * 60 * 60 * 24 * 365 * 1000;
				// System.out.println("2年:"+years);
				long afterTime = priDate.getTime() + years;
				// System.out.println("查看结束的:"+afterTime);
				afterDate.setTime(afterTime);

				String afterDateString = sFormat.format(afterDate);
				// System.out.println("查看添加n年后的时间:"+afterDateString);
				// 更换月和日
				StringBuffer sbf = new StringBuffer();
				// 到期的年+原来检查的月和日
				sbf.append(afterDateString.substring(0, 4))
						.append(dateString.substring(4, 10))
						.append(" 00:00:00");

				return sbf.toString();
			} catch (ParseException e) {
				return null;
			}
		} else {

			return null;
		}
	}
	/**
	 * 获取指定日期N几年之后的日期 <br>
	 * author：xuxiaoai <br>
	 * date：Aug 17, 2012 11:46:35 AM <br>
	 * version：V1.0.0
	 * 
	 * @param dateString
	 * @param year
	 * @return： <br>
	 *          ModifyRecord： <br>
	 *          1、Administrator - Aug 17, 2012 11:46:35 AM ：
	 */
	public static Date getAfterOneYearsDate() {
		String dateString=DateUtils.formaDateToString(get24BeforeTime(), "yyyy-MM-dd");
		Date priDate;
		Date afterDate = new Date();
		if (StringUtil.isNotEmpty(dateString)) {
			try {
				priDate = lFormat.parse(dateString + " 00:00:00");
				Long years = 1l * 60 * 60 * 24 * 365 * 1000;
				long afterTime = priDate.getTime() + years;
				afterDate.setTime(afterTime);
				String afterDateString = sFormat.format(afterDate);
//				 System.out.println("查看添加n年后的时间:"+afterDateString);
				// 更换月和日
				StringBuffer sbf = new StringBuffer();
				// 到期的年+原来检查的月和日
				sbf.append(afterDateString.substring(0, 4))
						.append(dateString.substring(4, 10))
						.append(" 23:59:59");

				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date=format.parse(sbf.toString());
//				System.err.println("ss:  "+format.format(date));
				return date;
			} catch (ParseException e) {
				return null;
			}
		} else {
			return null;
		}
	}
	/**
	 * 获得某时间几年之后的日期
	 * 
	 * @param date
	 * @param number
	 * @return
	 */
	public static String getAfterYear(String strDate, int number) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date StartDate = df.parse(strDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(StartDate);
			calendar.add(Calendar.YEAR, number);
			return df.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取指定日期1年之后的日期，如：2014/12/22 8:50:17 2015/12/21 23:59:59
	 * @param currentDate 若为空，为系统当前时间
	 * @return 1年之后的日期 （一年之后前一点23:59:59）
	 */
	public static Date getAfterOneYearsDate(Date currentDate) {
		String dateString = null;
		if (currentDate != null) {
			dateString = DateUtils.formaDateToString(currentDate, "yyyy-MM-dd");
		} else {
			dateString = DateUtils.formaDateToString(get24BeforeTime(), "yyyy-MM-dd");
		}
		Date priDate;
		Date afterDate = new Date();
		if (StringUtil.isNotEmpty(dateString)) {
			try {
				priDate = lFormat.parse(dateString + " 00:00:00");
				Long years = 1l * 60 * 60 * 24 * 365 * 1000;
				long afterTime = priDate.getTime() + years;
				afterDate.setTime(afterTime);
				String afterDateString = sFormat.format(afterDate);
				// 更换月和日
				StringBuffer sbf = new StringBuffer();
				// 到期的年+原来检查的月和日
				sbf.append(afterDateString.substring(0, 4)).append(dateString.substring(4, 10)).append(" 23:59:59");

				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = format.parse(sbf.toString());
				return date;
			} catch (ParseException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 计算指定日期，是否小于当前日期 <br>
	 * author：xuxiaoai <br>
	 * date：Sep 24, 2012 1:37:07 PM <br>
	 * version：V1.0.0
	 * 
	 * @param date
	 *            yyyy-MM-dd
	 * @return： <br>
	 *          ModifyRecord： <br>
	 *          1、Administrator - Sep 24, 2012 1:37:07 PM ：
	 */
	public static boolean isLessNowDate(String date) {
		if (StringUtil.isNotEmpty(date)) {
			try {
				if (sFormat.parse(date).getTime() < sFormat.parse(now())
						.getTime()) {
					return true;
				} else {
					return false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 比较两个时间的差距(yyyy-MM-dd)
	 * 
	 * @param date1
	 *            需要比较的时间 不能为空(null),需要正确的日期格式
	 * @param date2
	 *            被比较的时间 为空(null)则为当前时间
	 * @param stype
	 *            返回值类型 0为多少天，1为多少个月，2为多少年
	 * @return
	 */
	public static int compareDate(String date1, String date2, int stype) {
		int n = 0;

		String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";

		date2 = date2 == null ? now() : date2;

		DateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
		} catch (Exception e3) {
			System.out.println("wrong occured");
		}

		if (stype == 0) {
			try {
				int day = (int) (df.parse(date1).getTime() - df.parse(date2)
						.getTime()) / (24 * 60 * 60 * 1000);
				return Math.abs(day);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
				// 这里可以把间隔的日期存到数组中 打印出来
				n++;
				if (stype == 1) {
					c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
				} else {
					c1.add(Calendar.DATE, 1); // 比较天数，日期+1
				}
			}
		}

		n = n - 1;
		if (stype == 2) {
			n = (int) n / 365;
		}
		return n;
	}
	

	/**
	 * 获取当前日期
	 * 
	 * @return yyyy-MM-dd HH:mm:ss 格式的字符串
	 */
	public static String now() {
		Calendar cal = Calendar.getInstance();
		return lFormat.format(cal.getTime());
	}

	/**
	 * 获取一分钟前日期
	 * 
	 * @return
	 */
	public static String oneMinuteAgo() {
		Calendar can = Calendar.getInstance();
		can.add(Calendar.MINUTE, -1);
		return lFormat.format(can.getTime());
	}

	/**
	 * 获取五分钟前的日期
	 * 
	 * @return
	 */
	public static String fiveMinutesAgo() {
		Calendar can = Calendar.getInstance();
		can.add(Calendar.MINUTE, -5);
		Date d = can.getTime();
		String gRtnStr = lFormat.format(d);
		return gRtnStr;
	}

	/**
	 * 获取一个小时以前的日期
	 * 
	 * @return
	 */
	public static String oneHourAgo() {
		Calendar can = Calendar.getInstance();
		can.add(Calendar.HOUR, -1);
		Date d = can.getTime();
		String gRtnStr = lFormat.format(d);
		return gRtnStr;
	}

	/**
	 * 获取今天开始时间日期
	 * 
	 * @return
	 */
	public static String getTodayBeginTime() {
		Date date = new Date();
		date = new Date(date.getTime());
		return sFormat.format(date) + " 00:00:00";
	}

	/**
	 * 获取今天结束日期
	 */
	public static String getTodayEndTime() {
		Date date = new Date();
		date = new Date(date.getTime());
		return sFormat.format(date) + " 23:59:59";
	}

	/**
	 * 获得本周开始的日期
	 * 
	 * @return
	 */
	public static String getThisWeekBeginTime() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return sFormat.format(c.getTime()) + " 00:00:00";
	}

	/**
	 * 获得上周开始的日期
	 * 
	 * @return
	 */
	public static String getLastWeekBeginTime() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1 * 7);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return sFormat.format(c.getTime()) + " 00:00:00";
	}

	/**
	 * 获取昨天开始时间日期
	 * 
	 * @return
	 */
	public static String getYesterdayStartTime() {
		Date date = new Date();
		date = new Date(date.getTime() - 1000 * 60 * 60 * 24);
		return sFormat.format(date) + " 00:00:00";
	}

	/**
	 * 获取当月第一天
	 * 
	 * @return
	 */
	public static String getThisMonthBeginTime() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		return sFormat.format(lastDate.getTime()) + " 00:00:00";
	}

	/**
	 * 得到上个月的第一天
	 * 
	 * @return
	 */
	public static String getLastMonthBeginTime() {
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -1);
		Date theDate = calendar.getTime();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first_prevM = sFormat.format(gcLast.getTime());
		return day_first_prevM + " 00:00:00";
	}

	/**
	 * 上个月当前日期
	 * 
	 * @return
	 */
	public static String oneMonthAgo() {
		Calendar can = Calendar.getInstance();
		can.add(Calendar.MONTH, -1);
		Date d = can.getTime();
		return lFormat.format(d);
	}

	/**
	 * 获取上个月当前日期
	 * 
	 * @return
	 */
	public static String beforeMonth() {
		Date date = new Date();
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(date)) - 1;
		int day = Integer.parseInt(new SimpleDateFormat("dd").format(date));
		int hour = Integer.parseInt(new SimpleDateFormat("HH").format(date));
		int minute = Integer.parseInt(new SimpleDateFormat("mm").format(date));
		int second = Integer.parseInt(new SimpleDateFormat("ss").format(date));
		if (month == 0) {
			year -= 1;
			month = 12;
		} else if (day > 28) {
			if (month == 2) {
				if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
					day = 29;
				} else
					day = 28;
			} else if ((month == 4 || month == 6 || month == 9 || month == 11)
					&& day == 31) {
				day = 30;
			}
		}
		String y = year + "";
		String m = "";
		String d = "";
		String h = "";
		String mm = "";
		String ss = "";
		if (month < 10)
			m = "0" + month;
		else
			m = month + "";
		if (day < 10)
			d = "0" + day;
		else
			d = day + "";
		if (hour < 10)
			h = "0" + hour;
		else
			h = hour + "";
		if (minute < 10)
			mm = "0" + minute;
		else
			mm = minute + "";
		if (second < 10)
			ss = "0" + second;
		else
			ss = second + "";
		return y + "-" + m + "-" + d + " " + h + ":" + mm + ":" + ss;
	}

	/**
	 * 获取count个月前的当前时间
	 * 
	 * @param count
	 * @return
	 */
	public static String beforeMonth(int count) {
		Date date = new Date();
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(date))
				- count;
		int day = Integer.parseInt(new SimpleDateFormat("dd").format(date));
		int hour = Integer.parseInt(new SimpleDateFormat("HH").format(date));
		int minute = Integer.parseInt(new SimpleDateFormat("mm").format(date));
		int second = Integer.parseInt(new SimpleDateFormat("ss").format(date));
		if (month <= 0) {
			year -= 1;
			month = 12 - (count - Integer.parseInt(new SimpleDateFormat("MM")
					.format(date)));
		} else if (day > 28) {
			if (month == 2) {
				if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
					day = 29;
				} else
					day = 28;
			} else if ((month == 4 || month == 6 || month == 9 || month == 11)
					&& day == 31) {
				day = 30;
			}
		}
		String y = year + "";
		String m = "";
		String d = "";
		String h = "";
		String mm = "";
		String ss = "";
		if (month < 10)
			m = "0" + month;
		else
			m = month + "";
		if (day < 10)
			d = "0" + day;
		else
			d = day + "";
		if (hour < 10)
			h = "0" + hour;
		else
			h = hour + "";
		if (minute < 10)
			mm = "0" + minute;
		else
			mm = minute + "";
		if (second < 10)
			ss = "0" + second;
		else
			ss = second + "";
		return y + "-" + m + "-" + d + " " + h + ":" + mm + ":" + ss;
	}

	/**
	 * 返回一个当前的时间，并按格式转换为字符串 例：17:27:03
	 * 
	 * @return String
	 */
	public static String getTime() {
		GregorianCalendar gcNow = new GregorianCalendar();
		Date dNow = gcNow.getTime();
		return timeDF.format(dNow);
	}

	/**
	 * 返回当前年的年号
	 * 
	 * @return int
	 */
	public static int getYear() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.YEAR);
	}

	/**
	 * 返回本月月号：从 0 开始
	 * 
	 * @return int
	 */
	public static int getMonth() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.MONTH);
	}

	/**
	 * 返回今天是本月的第几天
	 * 
	 * @return int 从1开始
	 */
	public static int getToDayOfMonth() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.DAY_OF_MONTH);
	}

	/**
	 * 返回一格式化的时间
	 * 
	 * @param date
	 *            Date
	 * @return String hh:ss:mm 格式
	 */
	public static String formatTime(Date date) {
		return timeDF.format(date);
	}

	/**
	 * 返回一格式化的时间
	 * 
	 * @param date
	 * @return
	 */
	public static String formatTime(long date) {
		return formatTime(new Date(date));
	}

	/**
	 * 返回一格式化的日期时间
	 * 
	 * @param date
	 * @return yyyy-mm-dd HH:mm:ss 格式的字符串
	 */
	public static String formatDateTime(long date) {
		return lFormat.format(new Date(date));
	}

	/**
	 * 将字串转成日期，*
	 * 
	 * @param string
	 *            String
	 * @return Date yyyy-MM-dd格式
	 */
	public static Date toDate(String string) {
		try {
			return (Date) sFormat.parse(string);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 取值：某日期的年号
	 * 
	 * @param date
	 *            格式: yyyy/MM/dd
	 * @return
	 */
	public static int getYear(String date) {
		Date d = toDate(date);
		if (d == null)
			return 0;

		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(d);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 取值：某日期的月号
	 * 
	 * @param date
	 *            格式: yyyy/MM/dd
	 * @return
	 */
	public static int getMonth(String date) {
		Date d = toDate(date);
		if (d == null)
			return 0;

		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(d);
		return calendar.get(Calendar.MONTH);
	}

	/**
	 * 取值：某日期的日号
	 * 
	 * @param date
	 *            格式: yyyy/MM/dd
	 * @return 从1开始
	 */
	public static int getDayOfMonth(String date) {
		Date d = toDate(date);
		if (d == null)
			return 0;

		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(d);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 计算两个日期的年数差
	 * 
	 * @param one
	 *            格式: yyyy/MM/dd
	 * @param two
	 *            格式: yyyy/MM/dd
	 * @return
	 */
	public static int compareYear(String one, String two) {
		return getYear(one) - getYear(two);
	}

	/**
	 * 计算岁数
	 * 
	 * @param date
	 *            格式: yyyy/MM/dd
	 * @return
	 */
	public static int compareYear(String date) {
		return getYear() - getYear(date);
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = toDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	/**
	 * 根据一个Date，返回是星期几的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeek(Date date) throws Exception{
		// 再转换为时间
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		//modify by milk [指定环境，否则window和linux返回的值不一样]
		return new SimpleDateFormat("EEEE",Locale.CHINA).format(c.getTime());
	}

	public static int getDays(GregorianCalendar g1, GregorianCalendar g2) {
		int elapsed = 0;
		GregorianCalendar gc1, gc2;

		if (g2.after(g1)) {
			gc2 = (GregorianCalendar) g2.clone();
			gc1 = (GregorianCalendar) g1.clone();
		} else {
			gc2 = (GregorianCalendar) g1.clone();
			gc1 = (GregorianCalendar) g2.clone();
		}

		gc1.clear(Calendar.MILLISECOND);
		gc1.clear(Calendar.SECOND);
		gc1.clear(Calendar.MINUTE);
		gc1.clear(Calendar.HOUR_OF_DAY);

		gc2.clear(Calendar.MILLISECOND);
		gc2.clear(Calendar.SECOND);
		gc2.clear(Calendar.MINUTE);
		gc2.clear(Calendar.HOUR_OF_DAY);

		while (gc1.before(gc2)) {
			gc1.add(Calendar.DATE, 1);
			elapsed++;
		}
		return elapsed;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int diffdates(Date date1, Date date2) {
		int result = 0;

		GregorianCalendar g1 = new GregorianCalendar();
		GregorianCalendar g2 = new GregorianCalendar();

		g1.setTime(date1);
		g2.setTime(date2);

		result = getDays(g1, g2);

		return result;
	}

	public static synchronized String getTimestamp() {
		return getTimestamp("yyyyMMddHHmmss");
	}

	public static String getTimestamp(String format) {
		Date day = new Date();
		SimpleDateFormat f = new SimpleDateFormat(format);
		String time = f.format(day);
		return time;
	}

	public static String getLastMonthDay() {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH); // 上个月月份
		// 设置年月
		int year = 0;
		if (month == 0) {
			year = cal.get(Calendar.YEAR) - 1;
			month = 12;
		} else {
			year = cal.get(Calendar.YEAR);
		}
		// 设置天数
		String temp = year + "-" + month;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date d = null;
		try {
			d = format.parse(temp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.setTime(d);
		int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		String endDay = year + "-" + month + "-" + day;
		// return endDay;

		// System.out.println("###last month:" + month);
		// if (month == 0) {
		// year = cal.get(Calendar.YEAR) - 1;
		// month = 12;
		// } else {
		// year = cal.get(Calendar.YEAR);
		// }
		// String endDay = year + "-" + month + "-" + day;

		// enddate = year + month + Integer.toString(maxdate) + " 23:59:59";
		return endDay + " 23:59:59";
	}

	public static void main(String[] args) throws ParseException {
		// //System.out.println(getTime() + " " + formatTime(new Date()));
		//
		// String s2="2013-02-01 9:00:00";
		// String s1="2013-03-26 0:00:00";
		// int i=diffdates(sFormat.parse(s1),sFormat.parse(s2));
		// System.out.println(i);
		//
		// // Calendar c = Calendar.getInstance();
		// // c.add(Calendar.DATE, -1 * 7);
		// // c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// // String starttime=sFormat.format(c.getTime()) + " 00:00:00";
		// //
		// // Calendar c2 = Calendar.getInstance();
		// // c2.add(Calendar.DATE, -1 * 7);
		// // c2.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		// // String endtime=sFormat.format(c2.getTime()) + " 00:00:00";
		// // System.out.println(starttime+"----上周日---上周六--"+endtime);
		//
		//
		// Calendar calendar = Calendar.getInstance(); //得到前一个月
		// calendar.add(Calendar.MONTH, -1);
		// String starttimeString=sFormat.format(calendar.getTime());
		// String endtimeString=sFormat.format(new Date());
		// System.out.println(starttimeString+"----上月---本月--"+endtimeString);

		// Calendar calendar2=Calendar.getInstance(); //得到前一个月
		// calendar2.set(2013,9,25);
		// calendar2.add(Calendar.MONTH, -1);
		// calendar2.set(Calendar.DATE, -1);
		// System.out.println("-----"+sFormat.format(calendar2.getTime()));
		// calendar2.set(Calendar.DATE, 1);
		// System.out.println("-----"+sFormat.format(calendar2.getTime()));
		String t1 = "2014-01-09 10:12:40";
		String t2 = "2014-01-09 10:12:40";
//		try {
//			System.out.println(getBetweenDays(t1, t2));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		System.out.println(getAfterOneYearsDate());
		System.out.println(getAfterMonth(12,"2015/12/07"));
	}

	/**
	 * 计算两个日期之间的天数 <br/>
	 * @Title: getBetweenDays <br/>
	 * @Description: TODO <br/>
	 * @param @param t1 <br/>
	 * @param @param t2 <br/>
	 * @param @return <br/>
	 * @param @throws ParseException <br/>
	 * @return int <br/>
	 * @throws
	 */
	public static int getBetweenDays(String t1, String t2)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int betweenDays = 0;
		Date d1 = format.parse(t1);
		Date d2 = format.parse(t2);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		// 保证第二个时间一定大于第一个时间
		if (c1.after(c2)) {
			c1 = c2;
			c2.setTime(d1);
		}
		int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		betweenDays = c2.get(Calendar.DAY_OF_YEAR)
				- c1.get(Calendar.DAY_OF_YEAR);
		for (int i = 0; i < betweenYears; i++) {
			c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
			betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
		}
		return betweenDays;
	}
	/**
	 * 比较两个时间的差距(yyyy-MM-dd)
	 * 
	 * @param date1
	 *            需要比较的时间 不能为空(null),需要正确的日期格式
	 * @param date2
	 *            被比较的时间 为空(null)则为当前时间
	 * @param stype
	 *            返回值类型 0为多少天，1为多少个月，2为多少年
	 *            返回值有负数，不是绝对值
	 * @return
	 */
	public static int daysOfTwo(Date fDate, Date oDate) {
	       Calendar aCalendar = Calendar.getInstance();
	       aCalendar.setTime(fDate);
	       int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
	       int year1 = aCalendar.get(Calendar.YEAR); 
	       aCalendar.setTime(oDate);
	       int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
	       int year2 = aCalendar.get(Calendar.YEAR); 
	       
	       return (day2 - day1)+ 365*(year2-year1);
	    }
	
	/**
	 *  读取excel里的日期格式
	 * @param cell
	 * @return
	 */
	public static Date excelDate(final Cell cell) {
		Date date = null;
		if (HSSFDateUtil.isCellDateFormatted(cell)) {
			double d = cell.getNumericCellValue();
			if (d != 0.0) {
				date = HSSFDateUtil.getJavaDate(d);
			}
		}
		return date;
	}
	
	/**
	 * 获取年月日时分秒
	 * 格式：2014年11月1日14:30
	 * @author 林文彬
	 * @date Nov 21, 2014 9:33:24 AM
	 * @version V1.0.0
	 * @return
	 * @ModifyRecord：
	 * <br>1、林文彬; Nov 21, 2014 9:33:24 AM; 初始化
	 */
	public static String dateCN(Date date) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");  
        String dateCN = dateformat.format(date);
		return dateCN;
	}
	/**
	 * 获取年月日时分秒
	 * 格式：2014年11月1日14:30
	 * @author 林文彬
	 * @date Nov 21, 2014 9:33:24 AM
	 * @version V1.0.0
	 * @return
	 * @ModifyRecord：
	 * <br>1、林文彬; Nov 21, 2014 9:33:24 AM; 初始化
	 */
	public static String date14(Date date) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");  
        String dateCN = dateformat.format(date);
		return dateCN;
	}
	
	public static String date2String(Date date,String format) {
		 if(date == null)
			 return null;
		 SimpleDateFormat sdf = new SimpleDateFormat(format);
		 return sdf.format(date);
	}
	
	public static Date string2Date(String strDate,String format) throws ParseException{
		if(StringUtil.isEmpty(strDate))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(strDate);
	}
	
	/**
	 * 获取n分钟前的日期
	 * 
	 * @return
	 */
	public static Date minutesAgo(int n) {
		Calendar can = Calendar.getInstance();
		can.add(Calendar.MINUTE, -n);
		Date d = can.getTime();
		return d;
	}
	
}
