package com.yunmai.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class AmountUtil {

	public static String amountFormatString1(BigDecimal v1, int scale){
		DecimalFormat myformat = new DecimalFormat();
		String pax ="##,###.";
		for(int i=0;i<scale;i++){
			pax +="0";
		}
		myformat.applyPattern(pax);
		return myformat.format(v1);
	}
	
	public static String amountFormatString2(BigDecimal v1){
		DecimalFormat myformat = new DecimalFormat();
		String pax ="##,###.";
		for(int i=0;i<2;i++){
			pax +="0";
		}
		myformat.applyPattern(pax);
		if(0==v1.intValue())
		{
			return "0"+myformat.format(v1);
		}
		else
			return myformat.format(v1);
	}
	
	public static void main(String[] args){
		BigDecimal test=new BigDecimal("00");
		try{
			
		System.out.println(amountFormatString1(test,2));
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
}
