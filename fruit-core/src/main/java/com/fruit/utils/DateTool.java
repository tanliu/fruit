package com.fruit.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTool {
	
	/**格式化当前日期
	 * @param format
	 * @return
	 */
	public static String formatDate(String format){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		
		return simpleDateFormat.format(new Date());
	}
	
	/**获取当前时间的前len天的日期
	 * @param len 天
	 * @return
	 */
	public static String getBeforeDate(int len){
		len = -len;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss"); 
		String result=null;
	    try { 
	        Calendar cal = Calendar.getInstance(); 
	        cal.setTime(date); 
	        cal.add(Calendar.DATE, len); 
	        result= sdf.format(cal.getTime()); 
	    } catch (Exception e) { 
	        e.printStackTrace();
	    } 
	    return result;
	}
	
	/**获取当前时间，格式 yy-MM-dd
	 * @return
	 */
	public static String getDate(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss"); 
		return sdf.format(date);
	}

}
