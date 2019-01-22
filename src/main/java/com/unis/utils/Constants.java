package com.unis.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Constants {
    public static Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	public static Map<String,String> sessionMap = new HashMap<String,String>();
	public static Map<String,Set<Integer>> pagesMap = new HashMap<String,Set<Integer>>();
	public static Map<String,Set<Integer>> channelsMap = new HashMap<String,Set<Integer>>();
	public static String XML_MAPPING = "XmlMapping.properties";
	public static String STR_PAGE = "page_";
}
