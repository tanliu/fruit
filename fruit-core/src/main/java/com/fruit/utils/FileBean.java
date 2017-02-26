package com.fruit.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileBean {
	
	private String name ;
	private Long size;
	private Date date;
	private Boolean isDirectory;
	
	public FileBean(File file){
		this.name=file.getName();
		this.size = file.getTotalSpace();
		this.date = new Date(file.lastModified());
		this.isDirectory= file.isDirectory();
	}
	
	public static List<FileBean> getFileList (File folder){
		
		File[] files = folder.listFiles();
		List<FileBean> fileList = new ArrayList<FileBean>(files.length);
		for(File f:files){
			fileList.add(new FileBean(f));
		}
		return fileList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getIsDirectory() {
		return isDirectory;
	}

	public void setIsDirectory(Boolean isDirectory) {
		this.isDirectory = isDirectory;
	}
	
	

}
