package com.fruit.utils;

import java.util.ArrayList;
import java.util.List;

public class ImageBean {
	
	private String name;
	private String url;
	private String smallUrl;
	private String tinyUrl;
	public ImageBean(String name, String url,String smallUrl,String tinyUrl ) {
		super();
		this.name = name;
		this.url = url;
		this.smallUrl=smallUrl;
		this.tinyUrl=tinyUrl;
	}
	
	public ImageBean() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public String getSmallUrl() {
		return smallUrl;
	}
	
	public String getTinyUrl() {
		return tinyUrl;
	}

	public void setTinyUrl(String tinyUrl) {
		this.tinyUrl = tinyUrl;
	}

	public void setSmallUrl(String smallUrl) {
		this.smallUrl = smallUrl;
	}
	/**把一个例如：1.jpg;2.jpg;3.jpg封闭成对象，并对象列表
	 * @param images
	 * @return
	 */
	public static List<ImageBean> getImageList(String images){
		List<ImageBean> imglist = new ArrayList<>();
		
		if(images!=null){
			
		String[] imgs = images.split(";");
		String parantPath = "/getImage/";
		String smallPath="/getSmallImage/";
		String tinyPath = "/getTinyImage/";
		
		for(String img:imgs){
			if(img!=null&&!img.equals("")){
				imglist.add(new ImageBean(img, parantPath+img,smallPath+img,tinyPath+img));
			}
		}

		}
		return imglist;
	}

}
