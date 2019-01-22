package com.unis.utils;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Modifier;

public class JsonUtils {
    public static JsonObject getJsonObjectFromInputSteam(InputStream inputStream, String enCode){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, enCode));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            line = sb.toString();
            return getJsonObjectFromString(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonObject();
    }
    public static JsonObject getJsonObjectFromString(String jsonstr){
        try{
            return new JsonParser().parse(jsonstr).getAsJsonObject();
        }catch (Exception e){
            return null;
        }
    }
    public static JsonArray getJsonArrayFromString(String jsonstr){
        try{
            return new JsonParser().parse(jsonstr).getAsJsonArray();
        }catch (Exception e){
            return null;
        }
    }
    public static JsonObject getJsonObjectFromObject(Object o){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.STATIC, Modifier.PROTECTED)
                .create();
        return getJsonObjectFromString(gson.toJson(o));
    }
    public static String getJsonString(JsonObject object,String key,String def){
        try{
            String value = object.get(key).getAsString();
            if(value != null ){
                return value;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return def;
    }
    public static int getJsonInt(JsonObject object,String key,int def){
        try{
            return  object.get(key).getAsInt();
        }catch (Exception e){
            e.printStackTrace();
        }
        return def;
    }
    public static double getJsonDouble(JsonObject object,String key,double def){
        try{
            return  object.get(key).getAsDouble();
        }catch (Exception e){
            e.printStackTrace();
        }
        return def;
    }
}
