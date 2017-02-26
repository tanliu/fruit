package com.fruit.utils;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

public class JsonResult {
	private Map<String, Object> result = new HashMap<>();
	
	public JsonResult(int status,String meg){
		result.put("status",status);
		result.put("meg",meg);
	}
	
	
	
	public JsonResult() {
		super();
	}



	public void put(String key,Object value){
		result.put(key, value);
	}
	
	public Object get(String key){
		return result.get(key);
	}
	
	public String reset(int status,Object meg){
		result.put("status",status);
		result.put("meg",meg);
		return toString();
	}

	@Override
	public String toString() {
		String str = JSONArray.fromObject(result).toString();
		return str.substring(1,str.length()-1);
	}
	
	

}
