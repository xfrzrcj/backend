package com.unis.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertyUtils {
	public static Properties getProperty(String path) {
		Properties properties = new Properties();
		InputStream in = PropertyUtils.class.getClassLoader().getResourceAsStream(path);
	    // 使用properties对象加载输入流
	    try {
			properties.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
	public static void main(String[] args) {
		Properties property = PropertyUtils.getProperty(Constants.XML_MAPPING);
		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);
		set.add(5);
		set.add(6);
		set.add(7);
		
		Map<String,List<String>> data = new HashMap<>();
		Iterator<Object> keysIt = property.keySet().iterator();
		while(keysIt.hasNext()) {
			String key = (String)keysIt.next();
			if(key.startsWith("group_")) {
				String name = key.substring(6);
				String value = (String)property.get(key);
				String[] pages = value.split(",");
				List<String> dataList = new ArrayList<>();
				for(int j = 0;j<pages.length;j++) {
					try {
						int page = Integer.parseInt(pages[j]);
						if(set.contains(page)) {
							dataList.add(property.getProperty(Constants.STR_PAGE+page));
						}
					}catch(NumberFormatException e) {
						e.printStackTrace();
					}
				}
				data.put(name, dataList);
			}
		}
		System.out.println(Constants.gson.toJson(data));
	}
}
