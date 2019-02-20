package com.yunmai.util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class UUIDGenerator extends Thread{

    private static long orderNum = 0l;
    private static String date ;
  
    
    public static void main(String[] args) throws InterruptedException {
    	
    	for (int i = 0; i < 100; i++) {
    		System.out.println(UUIDGenerator.getOrderNo());
			Thread.sleep(1000);
		}
    }

    /**
     * 生成订单编号
     * @return
     */
    public static synchronized String getOrderNo() {
        String str = new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date());
        if(date==null||!date.equals(str)){
        	date = str;
        	orderNum  = 0l;
        }
        orderNum ++;
        
        int max=1000;
        int min=1;
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        
        long orderNo = Long.parseLong((date)) * max;
        orderNo += s;
        return orderNo+"";
    }
    /**
     * 生成订单编号
     * @return
     */
    public static synchronized String getOrderNo2() {
    	String str = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
    	if(date==null||!date.equals(str)){
    		date = str;
    		orderNum  = 0l;
    	}
    	orderNum ++;
    	
    	int max=1000000;
    	int min=1;
    	Random random = new Random();
    	int s = random.nextInt(max)%(max-min+1) + min;
    	
    	long orderNo = Long.parseLong((date)) * max;
    	orderNo += s;
    	return orderNo+"";
    }
    /**
     * 生成编号
     * @return
     */
    public static synchronized String getyhqNo() {
    	long aa=new Date().getTime()-1300000000000L;
      return aa+"";
    }
    
    /**
     * 生成编号
     * @return
     */
    public static synchronized String getyhqNoNew() {
    	long aa=new Date().getTime()-1300000000000L;
    	String sNum = Long.toString(aa).substring(9);
    	String base = "abcdefghijklmnopqrstuvwxyz";     
        Random random = new Random();     
        StringBuffer sb = new StringBuffer();     
        for (int i = 0; i < 3; i++) {     
            int number = random.nextInt(base.length());     
            sb.append(base.charAt(number));     
        }
        sNum = sb.toString() + sNum;
      //return aa+"";
        return sNum;
    }
    
    public static synchronized String getYunmaiUUID()
    {
      String UID = UUID.randomUUID().toString().toUpperCase();
      return UID;
    }
    
    /**
     * 生成投保地址ID
     */
    public static synchronized String getInsuranceUrlId(){
    	 String UID = UUID.randomUUID().toString().toUpperCase();
    	return UID;
    }
    
    /**
     * 
     * @param num
     * @return  生成大小写字母 加  数字
     *   lianchao
     */
    public static String createRedeenCode(int num) {  
   	 
 	   String str = "";
 	   for(int i=0;i < num;i++){  
            int intVal=(int)(Math.random()*58+65);
            if(intVal >= 91 && intVal <= 96){
         	   i--;
            }
            if(intVal < 91 || intVal > 96){
         	   if(intVal%2==0){
         		   str += (char)intVal;  
         	   }else{
         		   str += (int)(Math.random()*10);
         	   }
            }
        }  
 	   return str;
 	}  
    /**
     * 兑换券 兑换码 生成
     * @param num 生成大写字母加 数字
     * @return
     */
    public static synchronized String createRedeenCode2(int num) {
    	String[] pool={"A","B","C","D","E","F","G",
    			       "H","I","J","K","L","M","N",
    			       "O","P","Q","R","S","T","U",
    			       "V","W","X","Y","Z","0","1",
    			       "2","3","4","5","6","7","8",
    			       "9"};
    	
    	String str = "";
		for(int i=0;i < num;i++){
			int index=(int)(Math.random()*36);
			str+=pool[index];
		}  
		return str;
	}
    /**
     * 消费卡卡密生成 取消 字符O
     * @param num
     * @return
     */
    public static synchronized String createRedeenCode3(int num) {  
    	String[] pool={"A","B","C","D","E","F","G",
			       "H","I","J","K","L","M","N",
			       "P","Q","R","S","T","U","V",
			       "W","X","Y","Z","0","1","2",
			       "3","4","5","6","7","8","9"};
	
    	String str = "";
    	for(int i=0;i < num;i++){
    		int index=(int)(Math.random()*35);
    		str+=pool[index];
    	}  
    	return str;
	}
    
	/**
     * 随机车主商城   订单号   YYYYMMDDHHMMSS+5位 (大写字母数字)组合
     * @param num
     * @return
     */
    public static synchronized String createOrderCode() { 
		Date day = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = f.format(day);
    	String str = "";
		for(int i=0;i < 5;i++){  
			int intVal=(int)(Math.random()*26+65);
			if(intVal%2==0){
				str += (char)intVal;  
			}else{
				str += (int)(Math.random()*10);
			}
		}  
		return time+str;
	}
    
    /**
     * 获取UUID
     * @return
     */
    public static synchronized String getUUID(){ 
    	Date day = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = f.format(day);
    	String str = "";
		for(int i=0;i < 7;i++){  
			int intVal=(int)(Math.random()*26+65);
			if(intVal%2==0){
				str += (char)intVal;  
			}else{
				str += (int)(Math.random()*10);
			}
		}  
		return time+str; 
    } 
}