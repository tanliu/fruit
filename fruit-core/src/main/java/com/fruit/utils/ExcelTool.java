package com.fruit.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duowan.udb.util.string.Base64;
import com.fruit.entity.Fertilize;
import com.fruit.entity.Irrigation;
import com.fruit.entity.Pest;
import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.StringUtils;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;

public class ExcelTool {
	
	 private static List<Fertilize> fertilizes = new ArrayList();
	 private static List<Irrigation> irrigations = new ArrayList<>();
	 private static List<Pest> pests = new ArrayList<>();
	 private static JSONArray array = new JSONArray();
	 public static List<Fertilize> getFertilizes() {
		return fertilizes;
	}

	public static void setFertilizes(List<Fertilize> fertilizes) {
		ExcelTool.fertilizes = fertilizes;
	}

	public static List<Irrigation> getIrrigations() {
		return irrigations;
	}

	public static void setIrrigations(List<Irrigation> irrigations) {
		ExcelTool.irrigations = irrigations;
	}

	public static List<Pest> getPests() {
		return pests;
	}

	public static void setPests(List<Pest> pests) {
		ExcelTool.pests = pests;
	}

	public static JSONArray getArray() {
		return array;
	}

	public static void setArray(JSONArray array) {
		ExcelTool.array = array;
	}

	public static void createExcel(List<Map<String, Object>> list,String []keys,String columnNames[],HttpServletRequest request,HttpServletResponse response,String fileName) throws IOException{
		 ByteArrayOutputStream os = new ByteArrayOutputStream();
	        try {
	            createWorkBook(list,keys,columnNames).write(os);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);
	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//	        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
	        response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
	        ServletOutputStream out = response.getOutputStream();
	        BufferedInputStream bis = null;
	        BufferedOutputStream bos = null;
	        try {
	            bis = new BufferedInputStream(is);
	            bos = new BufferedOutputStream(out);
	            byte[] buff = new byte[2048];
	            int bytesRead;
	            // Simple read/write loop.
	            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	                bos.write(buff, 0, bytesRead);
	            }
	        } catch (final IOException e) {
	            throw e;
	        } finally {
	            if (bis != null)
	                bis.close();
	            if (bos != null)
	                bos.close();
	        }
	 }
	
	 public static Workbook createWorkBook(List<Map<String, Object>> list,String []keys,String columnNames[]) {
	        // 创建excel工作簿
	        Workbook wb = new HSSFWorkbook();
	        // 创建第一个sheet（页），并命名
	        Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
	        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
	        for(int i=0;i<keys.length;i++){
	            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
	        }

	        // 创建第一行
	        Row row = sheet.createRow((short) 0);

	        // 创建两种单元格格式
	        CellStyle cs = wb.createCellStyle();
	        CellStyle cs2 = wb.createCellStyle();

	        // 创建两种字体
	        Font f = wb.createFont();
	        Font f2 = wb.createFont();

	        // 创建第一种字体样式（用于列名）
	        f.setFontHeightInPoints((short) 10);
	        f.setColor(IndexedColors.BLACK.getIndex());
	        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

	        // 创建第二种字体样式（用于值）
	        f2.setFontHeightInPoints((short) 10);
	        f2.setColor(IndexedColors.BLACK.getIndex());

	        // 设置第一种单元格的样式（用于列名）
	        cs.setFont(f);
	        cs.setBorderLeft(CellStyle.BORDER_THIN);
	        cs.setBorderRight(CellStyle.BORDER_THIN);
	        cs.setBorderTop(CellStyle.BORDER_THIN);
	        cs.setBorderBottom(CellStyle.BORDER_THIN);
	        cs.setAlignment(CellStyle.ALIGN_CENTER);

	        // 设置第二种单元格的样式（用于值）
	        cs2.setFont(f2);
	        cs2.setBorderLeft(CellStyle.BORDER_THIN);
	        cs2.setBorderRight(CellStyle.BORDER_THIN);
	        cs2.setBorderTop(CellStyle.BORDER_THIN);
	        cs2.setBorderBottom(CellStyle.BORDER_THIN);
	        cs2.setAlignment(CellStyle.ALIGN_CENTER);
	        //设置列名
	        for(int i=0;i<columnNames.length;i++){
	            Cell cell = row.createCell(i);
	            cell.setCellValue(columnNames[i]);
	            cell.setCellStyle(cs);
	        }
	        //设置每行每列的值
	        for (short i = 1; i < list.size(); i++) {
	            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
	            // 创建一行，在页sheet上
	            Row row1 = sheet.createRow((short) i);
	            // 在row行上创建一个方格
	            for(short j=0;j<keys.length;j++){
	                Cell cell = row1.createCell(j);
	                cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
	                cell.setCellStyle(cs2);
	            }
	        }
	        return wb;
	    }
	 
	 //处理文件名字中文乱码
	 public static String encodeChineseDownloadFileName(  
	            HttpServletRequest request, String pFileName) throws UnsupportedEncodingException{  
	          
	         String filename = null;    
	            String agent = request.getHeader("USER-AGENT");    
	            if (null != agent){    
	                if (-1 != agent.indexOf("Firefox")) {//Firefox    
	                    filename = "=?UTF-8?B?" + (new String(Base64.encodeBase64(pFileName.getBytes("UTF-8"))))+ "?=";
	                }else if (-1 != agent.indexOf("Chrome")) {//Chrome    
	                    filename = new String(pFileName.getBytes(), "ISO8859-1");    
	                } else {//IE7+    
	                    filename = java.net.URLEncoder.encode(pFileName, "UTF-8");    
	                    filename = StringUtils.replace(filename, "+", "%20");//替换空格    
	                }    
	            } else {    
	                filename = pFileName;    
	            }    
	            return filename;   
	    } 
	 
		public void download(ByteArrayOutputStream os,HttpServletResponse response,String fileName)throws IOException{
			 byte[] content = os.toByteArray();
		        InputStream is = new ByteArrayInputStream(content);
		        // 设置response参数，可以打开下载页面
		        response.reset();
		        response.setContentType("application/vnd.ms-excel;charset=utf-8");
		        response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
		        ServletOutputStream out = response.getOutputStream();
		        BufferedInputStream bis = null;
		        BufferedOutputStream bos = null;
		        try {
		            bis = new BufferedInputStream(is);
		            bos = new BufferedOutputStream(out);
		            byte[] buff = new byte[2048];
		            int bytesRead;
		            // Simple read/write loop.
		            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
		                bos.write(buff, 0, bytesRead);
		            }
		        } catch (final IOException e) {
		            throw e;
		        } finally {
		            if (bis != null)
		                bis.close();
		            if (bos != null)
		                bos.close();
		        }
		       
		}
}
