package com.fruit.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;



/**工具箱，常用方法都在这里
 * @author 牵手无奈 
 *
 */
public class MyTools {
	public static boolean startOk=false;

	public static  String ROOTPATH =null;

	
	public static Random rd= new Random(); 
	

	/**获取文件的类型
	 * @param fileName
	 * @return
	 */
	public static String getType(String fileName){
		String[] strs=fileName.split("\\.");
		return "."+strs[strs.length-1];
	}
	
	/**获取文件的名字
	 * @param fileName
	 * @param type
	 * @return
	 */
	public static String getName(String fileName,String type){
		return fileName.substring(0, fileName.length()-type.length()-1);
	}
	
	
	
	
	
	public static String getRandomFileName(String oldName){
		int num = rd.nextInt(900000)+100000;
		Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newName =sdf.format(date)+num+getType(oldName);
		return newName;
	}
	

	
	
}
