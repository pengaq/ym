package com.yunmai.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 字符串处理工具类
 * 
 * @author 张念礼
 * @date 2012-4-13 上午09:56:51
 * @version V1.0.0
 * @Copyright Copyright © 2011 Sides. All rights reserved.
 */
public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * 
	 * @author 张念礼
	 * @date 2012-4-13 上午10:05:55
	 * @version V1.0.0
	 * @param args
	 *            字符串
	 * @return 空 true,不为空 false
	 * @ModifyRecord 修改记录 <br>
	 *               1、张念礼 ; 2012-4-13 上午10:05:55; 初始化
	 */
	public static Boolean isEmpty(String args) {
		Boolean bool = false;
		// 空对象
		if (null == args) {
			bool = true;
		}
		// 空字符串
		else if ("".equals(args.trim())) {
			bool = true;
		}
		// 全角
		else if ("　".equals(args)) {
			bool = true;
		}
		// null字符串
		else if ("null".equals(args)) {
			bool = true;
		}
		return bool;
	}

	/**
	 * 判断字符串是否不为空
	 * 
	 * @author 张念礼
	 * @date 2012-4-13 上午10:14:29
	 * @version V1.0.0
	 * @param args
	 *            字符串
	 * @return 空 false,不为空 true
	 * @ModifyRecord 修改记录 <br>
	 *               1、张念礼 ; 2012-4-13 上午10:14:29; 初始化
	 */
	public static Boolean isNotEmpty(String args) {
		Boolean bool = true;
		// 空对象
		if (null == args) {
			bool = false;
		}
		// 空字符串
		else if ("".equals(args.trim())) {
			bool = false;
		}
		// 全角
		else if ("　".equals(args)) {
			bool = false;
		}
		// null字符串
		else if ("null".equals(args)) {
			bool = false;
		}
		return bool;
	}

	/**
	 * 验证数组数据是否为空
	 * 
	 * @author 张念礼
	 * @date 2012-4-13 上午10:43:48
	 * @version V1.0.0
	 * @param args
	 *            字符串数组
	 * @return 空 true,不为空 false
	 * @ModifyRecord 修改记录 <br>
	 *               1、张念礼 ; 2012-4-13 上午10:43:48; 初始化
	 */
	public static Boolean arrayIsEmpty(String[] args) {
		if (null == args) {
			return true;
		}
		for (int i = 0; i < args.length; i++) {
			if (isEmpty(args[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 验证数组数据是否不为空
	 * 
	 * @author 张念礼
	 * @date 2012-4-13 上午10:53:30
	 * @version V1.0.0
	 * @param args
	 *            字符串数组
	 * @return 空 false,不为空 true
	 * @ModifyRecord 修改记录 <br>
	 *               1、张念礼 ; 2012-4-13 上午10:53:30; 初始化
	 */
	public static Boolean arrayIsNotEmpty(String[] args) {
		if (null == args) {
			return false;
		}
		for (int i = 0; i < args.length; i++) {
			if (isEmpty(args[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 去除字符串中的空格（全角和半角）
	 * 
	 * @author 张珂
	 * @date 2012-5-7 下午01:10:43
	 * @version V1.0.0
	 * @param str
	 * @return
	 * @ModifyRecord 修改记录 <br>
	 *               1、张珂 ; 2012-5-7 下午01:10:43 ; 初始化
	 */
	public static String trimFullWidth(String str) {
		str = str.trim();
		while (str.startsWith("　")) {
			str = str.substring(1, str.length()).trim();
		}
		while (str.endsWith("　")) {
			str = str.substring(0, str.length() - 1).trim();
		}
		return str;
	}

	/**
	 * 将传入的每个汉字都完全转换为拼音
	 * 
	 * @param src
	 *            例如：北京天安门
	 * @return beijingtiananmen
	 */
	public static String getFull(String src) {

		char[] srcChar = src.toCharArray();
		String[] srcArry = new String[srcChar.length];
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		String result = "";
		try {
			for (int i = 0; i < srcChar.length; i++) {
				if (Character.toString(srcChar[i]).matches("[\\u4E00-\\u9FA5]+")) {
					srcArry = PinyinHelper.toHanyuPinyinStringArray(srcChar[i], format);
					result += srcArry[0];
				} else
					result += Character.toString(srcChar[i]);
			}
			return result;
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			e1.printStackTrace();
		}
		return result;
	}

	/**
	 * 将传入的每个汉字转换为拼音的首字母
	 * 
	 * @param str
	 *            例如：北京天安门
	 * @return bjtam
	 */
	public static String getFirst(String str) {

		String result = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				result += pinyinArray[0].charAt(0);
			} else {
				result += word;
			}
		}
		return result;
	}

	/**
	 * 解决读取Excel中的科学计数法文本
	 * 
	 * @return
	 */
	public static String converScientific(String account) {
		// 解决获取到的为科学计数
		// String account="解析出来的结果";

		String regex = "^((\\d+.?\\d+)[Ee]{1}(\\d+))$";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(account);
		// 如果是科学计数法表达式，则进行下面的转换
		if (m.matches()) {
			DecimalFormat df = new DecimalFormat("#.##");
			account = df.format(Double.parseDouble(account));
		}
		return account;
	}

//	/**
//	 * 读取excel里的日期格式
//	 * 
//	 * @param cell
//	 * @return
//	 */
//	public static String excelDateToString(final Cell cell) {
//		Date date = null;
//		if (HSSFDateUtil.isCellDateFormatted(cell)) {
//			double d = cell.getNumericCellValue();
//			if (d == 0.0) {
//				return "";
//			}
//			date = HSSFDateUtil.getJavaDate(d);
//		}
//		if (date != null) {
//			return converScientific(new SimpleDateFormat("yyyy-MM-dd").format(date));
//		} else {
//			return "";
//		}
//	}

	/**
	 * 判断字符串是否数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String numberStr) {
		// 表达式的功能：验证必须为数字（整数或小数）
		String pattern = "[0-9]+(.[0-9]+)?";
		// 对()的用法总结：将()中的表达式作为一个整体进行处理，必须满足他的整体结构才可以。
		// (.[0-9]+)? ：表示()中的整体出现一次或一次也不出现
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(numberStr);
		return m.matches();
	}
	/**
	 * 将传入的每个汉字转换为拼音的首字母
	 * @param str 例如：北京天安门
	 * @return          bjtam
	 */
	public static String getHead(String str) {

		String result = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				if (pinyinArray[0].equals("zhang3")) {
					result += pinyinArray[1].charAt(0);
				} else {
					result += pinyinArray[0].charAt(0);
				}
			} else {
				result += word;
			}
		}
		return result;
	}
	
	/**
	 * 把字符串转换数组
	 * 
	 * @param strSource
	 *            字符串
	 * @param strFrom
	 * 
	 * 
	 * @return
	 */
	public static Object[] strToObject(String str,String str2) {

		Object[] obj = str.substring(0, str.length()).split(str2);
		return obj;
	}
	
	/**
	 * 把字符串转换成集合
	 * 
	 * @param strSource字符串
	 * @return
	 */
	public static List<String> strToList(String str,String str2) {
		Object[] obj = strToObject(str,str2);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < obj.length; i++) {
			list.add(obj[i].toString());
		}
		return list;
	}
	
	public static void main(String[] args){
		try{
			
		System.out.println(strToList("1531552,1531553,1531554,1531555,1531556,1531557,1531558,1531559,1531560",","));
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 得到指定月后的日期
	 */
	public static String getCreateTime() {
		return Long.toString(new Date().getTime());
	}
	
	public boolean isMobileNO(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	public static Integer stringToInt(String str){
		if(isNotEmpty(str)){
			try {
				return Integer.parseInt(str);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	//主键更换转换
	public static Integer stringToInt2(String str){
		if(isNotEmpty(str)){
			try {
				return Integer.parseInt(str);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return 0;
			}
		}
		return null;
	}
	/**
	 * @param bts
	 * @return
	 */
	public static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	/**
	 * 查看编码
	 * 
	 * @param str
	 * @return
	 */
	public String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";
	}
	
	public static String judgeEmpty(String str){
		String str1="";
		if(str !=null && "" !=str.trim()){
			str1 = str.trim();
		}
		return str1;
	}
	/**
	 * 判断int是否为空
	 * @param i
	 * @return
	 */
	public static boolean intNotEmpty(Integer i){
		if(String.valueOf(i)!=null&&!"".equals(String.valueOf(i))&&!"null".equals(String.valueOf(i))&&!"undefined".equals(String.valueOf(i))){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * 验证电话号码
	 * @param str
	 * @return
	 */
	public static boolean isValidMobileNo(String str)
	  {
	    String regEx = "^1[3|4|5|7|8][0-9]{9}$";
	    Pattern pattern = Pattern.compile(regEx);
	    Matcher matcher = pattern.matcher(str);
	    boolean rs = matcher.matches();
	   return rs;
	  }

}
