package com.fruit.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

/**
 * 一些数据工具
 * @author CSH
 *
 */
public class DataTool {

	//分页的默认初始页面
	public final static int page = 1;
	
	//分页的默认初始页面大小
	public final static int pageSize = 10; 

	//查找的照片最大的数量
	public final static int pictureMaxSize = 10;
	
	//查找产品的最大数量
	public final static int productMaxSize = 1000; 
	
	//施肥记录编码后缀编号
	public final static String TYPE_FERTILIZE = "01";
	
	//灌溉记录编码后缀编号
	public final static String TYPE_IRRIGATION = "02";
	
	//病虫害记录编码后缀编号
	public final static String TYPE_PEST = "03";
	
	//生长（栽培）记录编码后缀编号
	public final static String TYPE_CULTIVATE = "04";
	
	//成品质量检验记录编码后缀编号
	public final static String TYPE_QUALITY = "05";
	
	//物流运输记录编码后缀编号
	public final static String TYPE_TRANSPORT = "06";
	
	//加工记录编码后缀编号
	public final static String TYPE_PROCESS = "07";
	//销售记录编码后缀编号
	public final static String TYPE_SALE = "08";
	
	/**
	 * string类型转为Date
	 * @param dateStr
	 * @return
	 */
	public static Date stringToDate(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Date类型转为String
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	
	/**Date类型转为String
	 * @param date
	 * @param format	当前格式，如yyyy-MM-dd
	 * @return
	 */
	public static String dateToString(Date date,String format){
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 获取各自记录的编号 xxx-xx-xx
	 * @param productNumber		产品编号
	 * @param type				类型
	 * @return
	 */
	public static String getNumber(String productNumber, String type, String date){
		String[] str = date.split("-");
		String year = str[0].substring(2);
		StringBuilder sb = new StringBuilder();
		sb.append(productNumber).append("-").append(year).append("-").append(type);
		return sb.toString();
	}
	
	/**
	 * 获取各自记录的编号 xxx-xx-xx
	 * @param productNumber		产品编号
	 * @param type				类型
	 * @return
	 */
	public static String getNumber(String productNumber, String type){
		String year = DateTool.formatDate("yy");
		StringBuilder sb = new StringBuilder();
		sb.append(productNumber).append("-").append(year).append("-").append(type);
		return sb.toString();
	}
	
	
	/**
	 * 获取包装箱批次的编号xxx-xx-xx-x-xx
	 * @param productNumber
	 * @param gradeNumber
	 * @param batchNumber
	 * @return
	 */
//	public static String getPackageBatchNumber(String productNumber, String gradeNumber, String batchNumber, String date){
//		String[] str = date.split("-");
//		int month = Integer.parseInt(str[1]);
//		int year = Calendar.getInstance().get(Calendar.YEAR);
//		String yearStr = String.valueOf(year);
//		yearStr = yearStr.substring(yearStr.length()-2, yearStr.length());
//		StringBuilder sb = new StringBuilder();
//		sb.append(productNumber).append("-").append(yearStr).append("-").append(month<10 ? "0"+ month : month)
//		  .append("-").append(gradeNumber).append("-").append(batchNumber);
//		return sb.toString();
//	}
	
	/**删除项目的文件
	 * @param path
	 */
	public static void deleteFile (String path){
		path=path.replaceAll("~", "/");
		File file = new File(path);
	}
	
	/**
	 *	复制文件 
	 */
	public static void copyFile(String sourcePath,String targetPath){
		
		File t = new File(targetPath);
		
		File directory = t.getParentFile();
		if(!directory.exists()){
			directory.mkdirs();
		}
		
		File s = new File(sourcePath);
		
		FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;

        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	/**
	 * 封装JsonMap
	 * @param jsonMap
	 * @param success
	 * @param errcode
	 * @param errmsg
	 * @param data
	 */
	public static Map<String, Object> createJsonMap(boolean success, int errcode, String errmsg, Object data){
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("success", success);
		jsonMap.put("errmsg", errmsg);
		jsonMap.put("errcode", errcode);
		jsonMap.put("data", data);
		return jsonMap;
	}
	
	/**
	 * 转为json
	 * @param value
	 * @return
	 */
	public static String createJsonString(Object value) {
		Gson gson = new Gson();
        return gson.toJson(value);
	}
	
	/**生成批次，共47位，如果参数不符合要求，返回ull 
	 * @param productNumbber	产品编码
	 * @param date				日期，格式为yyyy-MM-dd
	 * @param orderNumber		当前各批次顺序码，如0001，0002....
	 * @return
	 */
	public static String getBatchNumber(String productNumbber,String date,String orderNumber){
		if(date==null||date.length()!=10){
			return null;
		}
		StringBuffer dateBuffer = new StringBuffer(date);
		dateBuffer.deleteCharAt(4);
		dateBuffer.deleteCharAt(6);
		date=dateBuffer.toString();
		String time = ParamTool.formatDate("HHmmss");
		if(isInteger(productNumbber, 29)&&isInteger(date, 8)&&isInteger(orderNumber, 4)){
			return productNumbber+date+time+orderNumber;
		}
		
		return null;
	}
	
	
	/**返回加工、质检、运输、销售记录里的二维码参数，不定长
	 * @param productId	产品id
	 * @param batchNumber	批次编码47位
	 * @return
	 */
	public static String getBarcodeNumber(Integer productId,String batchNumber){
		if(productId==null||batchNumber==null||batchNumber.length()!=47){
			return null;
		}
		String end=batchNumber.substring(31);
		
		return productId+end;
	}
	
	/**生成产品编码,共29位，如果参数不符合要求，返回ull 
	 * @param orchardNumber	果园编码
	 * @param productArea	产地编码	
	 * @param varietyNumber	品种编码
	 * @return
	 */
	public static String getProductNumber(String orchardNumber,String productArea,String varietyNumber){
		if(isInteger(orchardNumber, 15)&&isInteger(productArea, 6)&&isInteger(varietyNumber, 8)){
			return orchardNumber+productArea+varietyNumber;
		}
		
		return null;
	}
	
	
	
	
	/**生成生产档案编码，共37位，如果参数不符合要求，返回ull 
	 * @param productNumber	产品编码
	 * @param date	日期，10位，格式为yyyy-MM-dd
	 * @param type	类型，2位
	 * @return
	 */
	public static String getProductionRecordNumber(String productNumber,String date,String type){
		if(date==null||date.length()!=10){
			return null;
		}
		
		StringBuffer dateBuffer = new StringBuffer(date);
		dateBuffer.deleteCharAt(4);
		dateBuffer.deleteCharAt(6);
		date=dateBuffer.toString();
		if(isInteger(productNumber, 29)&&isInteger(date, 8)&&isInteger(type, 2)){
			return productNumber+date+type;
		}
		
		return null;
	}
	
	/**生成村庄编码，共12位，如果参数不符合要求，返回ull 
	 * @param regionNumber	镇编码，9位数字
	 * @param villageNumber	村代号，3位数字
	 * @return
	 */
	public static String getVillageNumber(String regionNumber,String villageNumber){
		if(isInteger(regionNumber, 9)&&isInteger(villageNumber, 3)){
			return regionNumber+villageNumber;
		}
		return null;
	}
	
	/**生成果园编码，共15位，如果参数不符合要求，返回ull 
	 * @param villageNumber	村编码，3位数字
	 * @param onumber	果园代号，3位
	 * @return
	 */
	public static String getOrchardNumber(String villageNumber,String onumber){
		if(isInteger(villageNumber, 12)&&isInteger(onumber, 3)){
			return villageNumber+onumber;
		}
		return null;
	}
	
	
	/**生成品种编码，共8位，如果参数不符合要求，返回ull 
	 * @param year	年份，两位
	 * @param varietyCode	品种代码，6位
	 * @return	返回品编码
	 */
	public static String getVarietyNumber(String year,String varietyCode){
		if(isInteger(year, 2)&&isInteger(varietyCode, 6)){
			return year+varietyCode;
		}
		return null;
	}
	
	/**所一个正整数转成定长的字符 串，如果位数不够，前面补0，如果位数超出，则返回null
	 * @param number	正整数
	 * @param lenght	格式化成的位数
	 * @return
	 */
	public static String formatNumber(Integer number,int lenght){
		if(number==null||number<0){
			return null;
		}
		StringBuffer pattern = new StringBuffer(lenght);
		for(int i=0;i<lenght;i++){
			pattern.append('0');
		}
		DecimalFormat df = new DecimalFormat(pattern.toString());
		return df.format(number);
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
	
	/*
	 * 用于获取编码的前面几位是产品ID
	 */
	public static int getProductId(String number){
		Integer value = null;
		int length = number.length();
		length -= 16;
		number = number.substring(0, length);
		value = Integer.parseInt(number);
		return value;
	}
	
	/*
	 * 移除掉前面的产品ID
	 */
	public static String removeId(String number){
		int length = number.length();
		int start = length - 16;
		return number.substring(start, length);
	}
	
	public static void main(String[] args){
	}
	
}
