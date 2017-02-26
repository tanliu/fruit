package com.fruit.utils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**用于读取配置文件，如数据库安装目录
 * @author 牵手无奈 
 *
 */
public class ConfigProperty {
 
	public static String initUrl=null;
	public static String qrCodePath = null;
	public static String apkPath = null;
	public static String uploadImagePath = null;
	public static String uploadSmallImagePath = null;
	public static String uploadTinyImagePath = null;
	public static String qrProjectPath = null;
	public static String path = null;
	public static String backupDatabasePath = null;
	//public static String projectPath = null;
 
 public static void testReadPropertiesFileInStaticMethod() throws IOException{
  InputStream in = ConfigProperty.class.getResourceAsStream("config.properties");
  Properties p = new Properties();
  p.load(in);
  initUrl=p.getProperty("initUrl");
  qrCodePath = p.getProperty("qrCodePath");
  apkPath = p.getProperty("apkPath");
  uploadImagePath = p.getProperty("uploadImagePath");
  uploadSmallImagePath = p.getProperty("uploadSmallImagePath");
  uploadTinyImagePath = p.getProperty("uploadTinyImagePath");
  qrProjectPath = p.getProperty("qrProjectPath");
  path = p.getProperty("path");
  //projectPath = p.getProperty("projectPath");
  backupDatabasePath=p.getProperty("backupDatabasePath");
 }
 
 
 static {
  
  try {
	  ConfigProperty.testReadPropertiesFileInStaticMethod();
  } catch (IOException e1) {
   e1.printStackTrace();
  }
  
 
 }
}

