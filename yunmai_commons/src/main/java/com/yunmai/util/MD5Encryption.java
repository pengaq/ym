package com.yunmai.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;

public class MD5Encryption {
	
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
        "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	// 返回形式为数字跟字符串
	private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return strDigits[d1] + strDigits[d2];
    }
	
	// 返回形式只为数字
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }
    
     // 转换字节数组为16进制字串
	 public static String byteArrayToString(byte[] b) {
	        StringBuffer resultSb = new StringBuffer();
	        for (int i = 0; i < b.length; i++) {
	            resultSb.append(byteToHexString(b[i]));
	        }
	        return resultSb.toString();
	 }
	 
	 /**
	  * 
	  * @param str
	  * @return
	  */
	 public static String MD5Encode(String str) {
	        String resultString = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            resultString = byteArrayToString(md.digest(str.getBytes("UTF-8")));
	        } catch (Exception ex) {
	        	ex.printStackTrace();
	        }
	        return resultString;
	    }
	 
	 /**
	  * 
	  * @param str
	  * @return
	  */
	 public static String MD5Encode(String str, String charset) {
	        String resultString = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            resultString = byteArrayToString(md.digest(str.getBytes(Charset.forName(charset))));
	        } catch (Exception ex) {
	        	ex.printStackTrace();
	        }
	        return resultString;
	    }
}
