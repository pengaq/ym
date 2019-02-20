//package com.yunmai.util;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//
//import org.apache.commons.lang.StringUtils;
//
//public class DateUtil {
//	
//	static SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	public static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
//	public static final String medianFormat = "yyyy-MM-dd";
//	
//	public static Date getNowDate(String format) throws ParseException  {
//        Date currentTime = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat(format);
//        String dateString = formatter.format(currentTime);
//        Date currentTime_2 = formatter.parse(dateString);
//        return currentTime_2;
//    }
//	
//	public static Date formatDate(Date date, String format) throws ParseException {
//		SimpleDateFormat formatter = new SimpleDateFormat(format);
//		Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//		return formatter.parse( formatter.format(calendar.getTime()));
//	}
//	
//	public static String formatStr(String date, String format) throws ParseException {
//		SimpleDateFormat formatter = new SimpleDateFormat(format);
//		Calendar calendar = Calendar.getInstance();
//        calendar.setTime(formatter.parse(date));
//		return formatter.format(calendar.getTime());
//	}
//	
//	public static Date strToDate(String strDate, String format) throws ParseException {
//		SimpleDateFormat formatter = new SimpleDateFormat(format);
//		Date strtodate  = formatter.parse(strDate);
//		return strtodate;
//	}
//	 
//	public static String dateToStr(Date date, String format) throws ParseException {
//		SimpleDateFormat formatter = new SimpleDateFormat(format);
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		String strtodate  = formatter.format(calendar.getTime());
//		return strtodate;
//	}
//	
//	
//	/*
//	 * 月最后天
//	 */
//	public static Date getLastDate(long day) {
//        Date date = new Date();
//        long longDate = date.getTime() - 3600000 * 34 * day;
//        Date lastDate = new Date(longDate);
//        return lastDate;
//    }
//    
//    /**
//     * 两个日期之间天数
//     * sj1 最后日期
//     * sj2 开始日期
//     */
//    public static int getBetweenDay(String sj1, String sj2) {
//    	if (sj1 == null || sj1.equals(""))
//            return 0;
//        if (sj2 == null || sj2.equals(""))
//        	return 0;
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        Long day = 0L;
//        try {
//            Date date1 = formatter.parse(sj1);
//            Date date2 = formatter.parse(sj2);
//            day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
//        } catch (Exception e) {
//            return 0;
//        }
//        return day.intValue();
//    }
//    
//    /**
//     * 月所在周天
//     * 
//     * @param sdate
//     * @return
//     * @throws ParseException 
//     */
//    public static String getWeek(String sdate) throws ParseException {
//        Date date = strToDate(sdate,"yyyy-MM-dd");
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        return new SimpleDateFormat("EEEE").format(c.getTime());
//    }
//
//   /* 日期偏移量
//    *  
//    * @param date 日期 
//    * @param calendarFiled Calendar偏移对象(DATE, MONTH,YEAR) 
//    * @param offset 偏移量
//    * @return 返回偏移后日期
//    */
//   public static Date dateOffset(Date date, int calendarFiled, int offset) {
//        if (date == null){
//            throw new IllegalArgumentException("日期不存在");
//        }
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(calendarFiled, offset);
//        return new Date(calendar.getTimeInMillis());
//    }
//    
//    
//    /**
//     * 是否闰年判断
//     * 
//     * @param ddate
//     * @return
//     * @throws ParseException 
//     */
//    public static boolean isLeapYear(String ddate) throws ParseException {
//        Date d = strToDate(ddate,"yyyy-MM-dd");
//        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
//        gc.setTime(d);
//        int year = gc.get(Calendar.YEAR);
//        if ((year % 400) == 0)
//            return true;
//        else if ((year % 4) == 0) {
//            if ((year % 100) == 0)
//                return false;
//            else
//                return true;
//        } else
//            return false;
//    }
//    
//    /**
//	 * 比较两个时间的差距(yyyy-MM-dd)
//	 * 
//	 * @param date1
//	 *            需要比较的时间 不能为空(null),需要正确的日期格式
//	 * @param date2
//	 *            被比较的时间 为空(null)则为当前时间
//	 * @param stype
//	 *            返回值类型 0为多少天，1为多少个月，2为多少年
//	 *            返回值有负数，不是绝对值
//	 * @return
//	 */
//	public static int daysOfTwo(Date fDate, Date oDate) {
//       Calendar aCalendar = Calendar.getInstance();
//       aCalendar.setTime(fDate);
//       int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
//       int year1 = aCalendar.get(Calendar.YEAR); 
//       aCalendar.setTime(oDate);
//       int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
//       int year2 = aCalendar.get(Calendar.YEAR); 
//       return (day2 - day1)+ 365*(year2-year1);
//    }
//	
//	/**
//	 * 获取当前日期
//	 * 
//	 * @return yyyy-MM-dd HH:mm:ss 格式的字符串
//	 */
//	public static String now() {
//		Calendar cal = Calendar.getInstance();
//		return lFormat.format(cal.getTime());
//	}
//	
//	/**
//	 * 得到指定月后的日期
//	 */
//	public static String getAfterMonth(int month, String dateValue) {
//		if (StringUtils.isNotEmpty(dateValue)) {
//			if(dateValue.contains("/")){
//				dateValue = dateValue.replace("/", "-");
//			}
//			Calendar c = Calendar.getInstance();// 获得一个日历的实例
//			Date date = null;
//			try {
//				date = sFormat.parse(dateValue);// 初始日期
//				c.setTime(date);// 设置日历时间
//				c.add(Calendar.MONTH, month);// 在日历的月份上增加6个月
//				String strDate = sFormat.format(c.getTime());// 的到你想要得6个月后的日期
//				return strDate;
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				return null;
//			}
//
//		} else {
//			return null;
//		}
//	}
//	
//	/**
//	 * 将时间转换成指定格式的字符串 <br>
//	 * 
//	 * @param date
//	 *            ：要转换的日期
//	 * @param formateString
//	 *            ：要转换的日期格式
//	 * @return： <br>
//	 */
//	public static String formaDateToString(Date date, String formateString) {
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat(formateString);
//			return sdf.format(date);
//		} catch (Exception ex) {
//			return null;
//		}
//	}
//	public static Long GeneratorDate(){
//		
//		 Long time = new Date().getTime();
//		 return time;
//	}
//	
//	
//	/**
//	 * 获取   时间 年月日加减
//	 * @param date
//	 * @param number
//	 * @return
//	 */
//	public static Date getAfterYear(Date date,int type, int number) {
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(date);
//			if(type==1){
//				calendar.add(Calendar.YEAR, number);
//			}else if(type==2){
//				calendar.add(Calendar.MONTH, number);
//			}else if(type==5){
//				calendar.add(Calendar.DATE, number);
//			}
//
//			
//			return calendar.getTime();
//	}
//	/**
//	 * 日期 年月日  加减
//	 * @param date
//	 * @param year
//	 * @param month
//	 * @param day
//	 * @return
//	 */
//	public static Date getAfterDate(Date date,int year,int month,int day){
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		calendar.add(Calendar.YEAR, year);
//		calendar.add(Calendar.MONTH, month);
//		calendar.add(Calendar.DATE, day);
//		return calendar.getTime();
//	}
//	
//}
