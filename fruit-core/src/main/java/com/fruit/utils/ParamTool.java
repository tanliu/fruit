package com.fruit.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;

public class ParamTool {
	
	public static void main(String[] strs){
		StringBuffer pbs = new StringBuffer("http://localhost:8080/qualityinspector/index?id=189");
		pbs.delete(0, pbs.length());
		
	}
	
	/**字符串转换日期
	 * @param dateString
	 * @param format
	 * @return
	 */
	public static  Date String2Date(String dateString,String format){
		if(notEmpty(dateString)){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			   Date date = null;
			   try {
			    date = sdf.parse(dateString);
			   } catch (Exception e) {
			    e.printStackTrace();
			   }
			   return date;
		}else{
			return null;
		}
		
	}
	
	/**把一个字符转换成整数
	 * @param number
	 * @param init	如果字符串不能转换整数时，返回的整数
	 * @return
	 */
	public static Integer String2Integer(String number,Integer init){
		Integer result = init;
		if(number!=null&&!number.trim().equals("")){
			try {
				result=Integer.parseInt(number);
			} catch (Exception e) {
				
			}
		}
		return result;
	}
	
	
	/**判断一个字符串是否为空，空的标准时当字符为null或者全是空白字符
	 * @param str
	 * @return
	 */
	public static boolean notEmpty(String str){
		if(str==null||str.trim().equals(""))
			return false;
		return true;
	}
	
	/**判断一个字符串是否全为数字，空的标准时当字符为null或者全是空白字符
	 * @param str
	 * @return
	 */
	public static boolean IsInteger(String str){
		if(notEmpty(str)){
			Pattern p = Pattern.compile("^\\d+$");
			Matcher matcher = p.matcher(str);
			return matcher.find();
		}
		return false;
	}
	
	/**判断一个字符串是否为固定长度的数字字符串
	 * @param str	
	 * @param length 长度 
	 * @return
	 */
	public static boolean isInteger(String str,int length){
		if(str!=null){
			Pattern p = Pattern.compile("^\\d{"+length+"}$");
			Matcher matcher = p.matcher(str);
			return matcher.find();
		}
		return false;
	}
	
	
	/**判断密码是否合法,判断表达式为：^\\w{6,18}$
	 * @param str
	 * @return
	 */
	public static boolean isLegalPassword(String str){
		if(notEmpty(str)){
		Pattern p = Pattern.compile("^\\w{6,18}$");
		Matcher matcher = p.matcher(str);
		return matcher.find();
		}
		return false;
	}
	
	/**去掉左右一位
	 * @param str
	 * @return
	 */
	public static String subContent(String str){
		if(notEmpty(str)){
			return str.substring(1,str.length()-1);
		}
		return str;
	}
	
	/**合并两个字符串
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String mergeString(String str1,String str2){
		if(notEmpty(str1)&&notEmpty(str2)){
			return str1+str2;
		}else if(notEmpty(str1)&&!notEmpty(str2)){
			return str1;
		}else if(!notEmpty(str1)&&notEmpty(str2)){
			return str2;
		}else{
			return "";
		}
	}
	
	/**请求参数数组转个单个数
	 * @param params
	 * @return
	 */
	public static Map<String, String> map2Map(Map<String, String[]> params){
		Map<String, String> result = new HashMap<>();
		Set<String> keys = params.keySet();
		for(String key:keys){
			result.put(key, params.get(key)[0]);
		}
		return result;
	}
	
	/**打印Map
	 * @param params
	 */
	public static void printMap(Map<String, String> params){
		Set<String> keys = params.keySet();
		for(String key:keys){
		}
	}
	
	/**把请求参数转换成不长于195字符的字符串
	 * @param params
	 * @return
	 */
	public static String getRequestParamsLog(Map<String, String[]> params){
		Map<String, String> result =map2Map(params);
		result.remove("password");
		String str = JSONArray.fromObject(result).toString();
		if(str.length() > 195){
			str = str.substring(0, 195);
		}
		return str;
	}
	
	/**格式化当前日期
	 * @param format
	 * @return
	 */
	public static String formatDate(String format){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		
		return simpleDateFormat.format(new Date());
	}
	
	/**格式化日期
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date,String format){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		
		return simpleDateFormat.format(date);
	}
	
	/**格式化日期
	 * @param format
	 * @return
	 */
	public static String formatDate(String date,String format){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date mydate = String2Date(date, format);
		return simpleDateFormat.format(mydate);
	}
	
	/**通过空格分隔字符串，再从提取出参数
	 * @param urls
	 * @return
	 */
	public static List<String> getParamsByUrl(String urls){
		String[] urlArray = urls.trim().split("\\s+");
		List<String> result = new ArrayList<>(urlArray.length);
		int index = 0;
		for(String s:urlArray){
			if(ParamTool.notEmpty(s)&&(index = s.indexOf('=')) > 0){
				result.add(s.substring(index+1));
			}
		}
		return result;
	}
	
	/**通过空格分隔字符串
	 * @param content
	 * @return
	 */
	public static List<String> getParamsByString(String content){
		String[] urlArray = content.trim().split("\\s+");
		List<String> result = new ArrayList<>(urlArray.length);
		for(String s:urlArray){
			if(ParamTool.notEmpty(s)){
				result.add(s);
			}
		}
		return result;
	}
	
	/**把格式为xxx.jpg;xxx.jpg;格式化为/getImage/xxx.jpg|/getImage/xxx.jpg
	 * @param pictures
	 * @return
	 */
	public static String formatPictures(String pictures,boolean isBig){
		if(notEmpty(pictures)){
			String[] imgs = pictures.split(";");
			String path=  "/getImage/";
			StringBuffer sb = new StringBuffer();
			if(!isBig){
				path="/getSmallImage/";
			}
			for(String img:imgs){
				if(notEmpty(img)){
					sb.append(path).append(img).append("|");
				}
			}
			if(sb.length()>0){
				sb.deleteCharAt(sb.length()-1);
			}
			return sb.toString();
		}else{
			return "";
		}
		
	}

}
